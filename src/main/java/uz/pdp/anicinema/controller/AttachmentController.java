package uz.pdp.anicinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.anicinema.service.AttachmentService;

import static uz.pdp.anicinema.payload.response.ResponseData.ok;
import static uz.pdp.anicinema.utils.enums.Code.SUCCESS;

@RestController
@RequestMapping("/api/v1/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(MultipartFile file) {
        return ok(SUCCESS, attachmentService.save(file));
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        return attachmentService.download(filename);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ok(SUCCESS,attachmentService.getById(id));
    }

}
