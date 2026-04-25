package com.example.rest.endpoint;

import com.example.rest.dto.CaseDocumentDto;
import com.example.rest.dto.CourtSessionDto;
import com.example.rest.service.CaseDocumentService;
import com.example.rest.service.CourtSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CaseDocumentEndpoint {


    private final CaseDocumentService caseDocumentService;



    @GetMapping("/caseDocuments")
    public ResponseEntity<List<CaseDocumentDto>> getAllCaseDocuments() {
        return ResponseEntity.ok(caseDocumentService.findAll());
    }

    @PostMapping("/caseDocument")
    public ResponseEntity<CaseDocumentDto> createCaseDocument(@RequestBody CaseDocumentDto caseDocumentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(caseDocumentService.save(caseDocumentDto));
    }

    @GetMapping("/caseDocuments/{id}")
    public ResponseEntity<CaseDocumentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(caseDocumentService.findById(id));
    }

    @DeleteMapping("caseDocument/{id}")
    public ResponseEntity<Void> deleteCaseDocument(@PathVariable long id) {
        caseDocumentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}











