package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.anicinema.entity.Attachment;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.mapper.AttachmentMapper;
import uz.pdp.anicinema.payload.response.AttachmentResponse;
import uz.pdp.anicinema.repository.AttachmentRepository;
import uz.pdp.anicinema.service.AttachmentService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static uz.pdp.anicinema.exception.BadRequestException.fileNotValid;
import static uz.pdp.anicinema.utils.contant.AppConstant.*;


@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    public AttachmentResponse save(MultipartFile file) {

        try {

            String filename = UUID.randomUUID() + getExtension(file.getContentType());

            Path path = BASE_ATTACHMENT_PATH.resolve(filename);

            Attachment attachment = Attachment.builder()
                    .fileName(filename)
                    .type(file.getContentType())
                    .path(path.toString())
                    .url(ATTACHMENT_URL + filename)
                    .build();
            if (!BASE_ATTACHMENT_PATH.toFile().exists()) {
                Files.createDirectories(BASE_ATTACHMENT_PATH);
            }

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return attachmentMapper.toResponse(attachmentRepository.save(attachment));

        }catch (Exception e){
            throw fileNotValid();
        }

    }

    @Override
    public ResponseEntity<InputStreamResource> download(String filename) {

        try {
            Attachment attachment = attachmentRepository.findByFileName(filename)
                    .orElseThrow(BadRequestException::fileNotValid);

            Path path = Path.of(attachment.getPath());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(attachment.getType()));
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename(attachment.getFileName())
                    .build());

            InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
            return ResponseEntity.ok().headers(headers).body(resource);

        }catch (Exception e){
            throw fileNotValid();
        }
    }

    @Override
    public AttachmentResponse getById(Long id) {
        return attachmentMapper.toResponse(attachmentRepository.findById(id)
                .orElseThrow(BadRequestException::fileNotValid));
    }

    private String getExtension(String contentType) {
        if (contentType != null) {
            return "." + contentType.substring(contentType.indexOf("/") + 1);
        }
        return ".jpg";
    }

}
