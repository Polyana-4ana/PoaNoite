package polyana.example.poanoite.service;

import polyana.example.poanoite.dto.RepostRequest;
import polyana.example.poanoite.dto.RepostResponse;

public interface RepostService {

    RepostResponse repostar(RepostRequest request);
}