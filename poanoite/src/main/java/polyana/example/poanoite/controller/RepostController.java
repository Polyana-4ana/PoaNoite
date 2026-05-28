package polyana.example.poanoite.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.RepostRequest;
import polyana.example.poanoite.dto.RepostResponse;
import polyana.example.poanoite.service.RepostService;

@RestController
@RequestMapping("/reposts")
public class RepostController {

    private final RepostService repostService;

    public RepostController(RepostService repostService) {
        this.repostService = repostService;
    }

    @PostMapping
    public ResponseEntity<RepostResponse> repostar(
            @Valid @RequestBody RepostRequest request
    ) {

        return ResponseEntity.ok(
                repostService.repostar(request)
        );
    }
}