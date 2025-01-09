package uz.pdp.anicinema.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.anicinema.entity.Attachment;
import uz.pdp.anicinema.payload.response.AttachmentResponse;

@Service
public interface AttachmentService {

    AttachmentResponse save(MultipartFile file);
    ResponseEntity<InputStreamResource> download(String filename);
    AttachmentResponse getById(Long id);
}
