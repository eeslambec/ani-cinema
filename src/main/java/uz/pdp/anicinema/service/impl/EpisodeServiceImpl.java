package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.Episode;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.payload.EpisodeDto;
import uz.pdp.anicinema.repository.EpisodeRepository;
import uz.pdp.anicinema.service.AttachmentService;
import uz.pdp.anicinema.service.EpisodeService;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final AttachmentService attachmentService;

    @Override
    public Episode save(EpisodeDto episodeDto) {

        Episode episode = Episode.builder()
                .number(episodeDto.getNumber())
                .title(episodeDto.getTitle())
                .duration(episodeDto.getDuration())
                .video(attachmentService.findById(episodeDto.getVideoId()))
                .build();

        return episodeRepository.save(episode);
    }

    @Override
    public Episode getById(Long id) {
        return episodeRepository.findById(id)
                .orElseThrow(BadRequestException::episodeNotFound);
    }

}
