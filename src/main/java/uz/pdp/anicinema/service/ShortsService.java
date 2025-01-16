package uz.pdp.anicinema.service;

import org.springframework.stereotype.Service;
import uz.pdp.anicinema.payload.request.ShortsRequest;
import uz.pdp.anicinema.payload.response.ShortsResponse;

import java.util.List;

@Service
public interface ShortsService {

    void save(ShortsRequest request);

    List<ShortsResponse> getAll();

    List<ShortsResponse> getAllForUsers();

}
