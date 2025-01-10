package uz.pdp.anicinema.service;

import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.Episode;
import uz.pdp.anicinema.payload.EpisodeDto;

@Service
public interface EpisodeService {

    Episode save(EpisodeDto episodeDto);

    Episode getById(Long id);

}
