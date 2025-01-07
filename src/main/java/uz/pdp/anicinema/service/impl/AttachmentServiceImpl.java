package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.anicinema.entity.Attachment;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.repository.AttachmentRepository;
import uz.pdp.anicinema.service.AttachmentService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static uz.pdp.anicinema.exception.BadRequestException.fileNotValid;
import static uz.pdp.anicinema.utils.contant.AppConstant.BASE_ATTACHMENT_PATH;
import static uz.pdp.anicinema.utils.contant.AppConstant.BASE_URL;


@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public Attachment save(MultipartFile file) {

        try {

            String filename = UUID.randomUUID() + getExtension(file.getContentType());

            Path path = BASE_ATTACHMENT_PATH.resolve(filename);

            Attachment attachment = Attachment.builder()
                    .fileName(filename)
                    .path(path.toString())
                    .url(BASE_URL + "/api/v1/attachment/" + filename)
                    .build();
            if (!BASE_ATTACHMENT_PATH.toFile().exists()) {
                Files.createDirectories(BASE_ATTACHMENT_PATH);
            }

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return attachmentRepository.save(attachment);

        }catch (Exception e){
            throw fileNotValid();
        }

    }

    @Override
    public Attachment getByFilename(String filename) {

        return attachmentRepository.findByFileName(filename)
                .orElseThrow(BadRequestException::fileNotValid);

    }

    private String getExtension(String contentType) {
        if (contentType != null) {
            return "." + contentType.substring(contentType.indexOf("/") + 1);
        }
        return ".jpg";
    }

}
