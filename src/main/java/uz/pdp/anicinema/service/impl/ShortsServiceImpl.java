package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.Shorts;
import uz.pdp.anicinema.mapper.CommentMapper;
import uz.pdp.anicinema.mapper.ShortsMapper;
import uz.pdp.anicinema.payload.request.ShortsRequest;
import uz.pdp.anicinema.payload.response.ShortsResponse;
import uz.pdp.anicinema.repository.ShortsRepository;
import uz.pdp.anicinema.service.AttachmentService;
import uz.pdp.anicinema.service.ShortsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortsServiceImpl implements ShortsService {

    private final ShortsMapper shortsMapper;
    private final CommentMapper commentMapper;
    private final ShortsRepository shortsRepository;
    private final AttachmentService attachmentService;


    @Override
    public void save(ShortsRequest request) {

        Shorts shorts = shortsMapper.toEntity(request, attachmentService);

        shortsRepository.save(shorts);

    }

    @Override
    public List<ShortsResponse> getAll() {
        return shortsRepository.findAll()
                .stream()
                .map(shorts -> shortsMapper.toResponse(shorts,commentMapper))
                .toList();
    }

    @Override
    public List<ShortsResponse> getAllForUsers() {
        return shortsRepository.findAllRandom()
                .stream()
                .map(shorts -> shortsMapper.toResponse(shorts,commentMapper))
                .toList();
    }
}
