package uz.pdp.anicinema.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttachmentResponse {

    private Long id;

    private String fileName;

    private String url;

}
