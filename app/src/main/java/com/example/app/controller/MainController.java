package com.example.app.controller;

import com.example.app.service.SpringUser;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;


    @Controller
    @RequiredArgsConstructor
    public class MainController {

        @Value("${legal.system.upload.image.directory.path}")
        private String imageDirectoryPath;

        private final PasswordEncoder passwordEncoder;
        private final UserService userService;

        @GetMapping("/")
        public String mainPage(@AuthenticationPrincipal SpringUser userPrincipal,
                               ModelMap modelMap) {
            if (userPrincipal != null) {
                modelMap.addAttribute("user", userPrincipal.getUser());
            }
            return "index";
        }

        @GetMapping("/successLogin")
        public String successLogin(@AuthenticationPrincipal SpringUser springUser) {
            if (springUser != null
                    && springUser.getUser().getRole() == UserRole.ADMIN) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/";
            }
        }


        @GetMapping("/loginPage")
        public String loginPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
            modelMap.addAttribute("msg", msg);
            return "loginPage";
        }

        @GetMapping("/registerPage")
        public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
            modelMap.addAttribute("msg", msg);
            return "registerPage";
        }

        @PostMapping("/register")
        public String register(@ModelAttribute User registeredUser) {
            if (userService.findByUsername(registeredUser.getUsername()).isPresent()) {
                return "redirect:/registerPage?msg=Username already exists!";
            }
            registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
            userService.save(registeredUser);
            return "redirect:/loginPage?msg=Registration successful, pls login!";
        }


        @GetMapping("/user/verify")
        public String verifyUserPage(@RequestParam("email") String email, ModelMap modelMap) {
            modelMap.addAttribute("email", email);
            return "verifyUser";
        }

        @PostMapping("/user/verify")
        public String verifyUser(@RequestParam("email") String email, @RequestParam("verifyCode") String verifyCode) {
            boolean isVerified = userService.verifyUser(email, verifyCode);
            if (isVerified) {
                return "redirect:/loginPage?msg=User verified successfully, pls Login!";
            }
            return "redirect:/loginPage?msg=Verification code is invalid!";


        }




    @GetMapping("/image/get")
        public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) {
            File file = new File(imageDirectoryPath + picName);
            if (file.exists() && file.isFile()) {
                try {
                    return FileUtils.readFileToByteArray(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    }

