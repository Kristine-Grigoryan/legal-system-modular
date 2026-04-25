package com.example.rest.endpoint;
import com.example.model.LegalCase;
import com.example.rest.dto.LegalCaseDto;
import com.example.rest.exception.LegalCaseNotFoundException;
import com.example.rest.service.LegalCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LegalCaseEndpoint {


    private final LegalCaseService legalCaseService;




    @GetMapping("/legalCases")
    public ResponseEntity<List<LegalCaseDto>> getAllCases() {
        return ResponseEntity.ok(legalCaseService.findAll());
    }


    @GetMapping("/legalaCases/{id}")
    public LegalCaseDto getLegalCaseById(@PathVariable long id) {
        LegalCaseDto caseDto = legalCaseService.findById(id);
        LegalCaseDto byId = legalCaseService.findById(id);
        if (byId == null) {
            throw new LegalCaseNotFoundException("LegalCase with " + id + " does not exists");
        }
        return byId;
    }


    @PostMapping("/legalCase")
    public ResponseEntity<LegalCaseDto> createLegalCase(@RequestBody LegalCaseDto legalCaseDto) {
        return ResponseEntity.ok(legalCaseService.save(legalCaseDto));
    }


    @DeleteMapping("/legalCase/{id}")
    public ResponseEntity<Void> deleteLegalCase(@PathVariable long id) {
        legalCaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}