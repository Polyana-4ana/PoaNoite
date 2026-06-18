package polyana.example.poanoite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.RepostRequest;
import polyana.example.poanoite.dto.RepostResponse;
import polyana.example.poanoite.service.RepostService;

@RestController
@RequestMapping("/reposts")
@RequiredArgsConstructor
public class RepostController {

    private final RepostService service;

    @PostMapping
    public ResponseEntity<RepostResponse> repostar(
            @Valid @RequestBody RepostRequest request) {
        return ResponseEntity.ok(service.repostar(request));
    }
}