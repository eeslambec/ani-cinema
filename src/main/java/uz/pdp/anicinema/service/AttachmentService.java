package uz.pdp.anicinema.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.anicinema.entity.Attachment;

@Service
public interface AttachmentService {

    Attachment save(MultipartFile file);
    Attachment getByFilename(String filename);
}
