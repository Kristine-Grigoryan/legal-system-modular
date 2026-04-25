package com.example.rest.endpoint;

import com.example.rest.dto.CourtSessionDto;
import com.example.rest.dto.PaymentDto;
import com.example.rest.service.CourtSessionService;
import com.example.rest.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourtSessionEndpoint {


    private final CourtSessionService courtSessionService;


    @GetMapping("/courtSessions")
    public ResponseEntity<List<CourtSessionDto>> getAllCourtSessions() {
        return ResponseEntity.ok(courtSessionService.findAll());
    }

    @PostMapping("/courtSession")
    public ResponseEntity<CourtSessionDto> createCourtSession(@RequestBody @Valid CourtSessionDto courtSessionDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courtSessionService.save(courtSessionDto));
    }

    @GetMapping("/courtSessions/{id}")
    public ResponseEntity<CourtSessionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courtSessionService.findById(id));
    }

    @PutMapping("courtSession/{id}")
    public ResponseEntity<  CourtSessionDto> updateCourtSession(@PathVariable Long id, @RequestBody CourtSessionDto courtSessionDto) {
        CourtSessionDto updatedCourtSession = courtSessionService.update(id, courtSessionDto);
        return ResponseEntity.ok(updatedCourtSession);

}
    @DeleteMapping("courtSession/{id}")
    public ResponseEntity<Void> deleteCourtSession(@PathVariable long id) {
        courtSessionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}











