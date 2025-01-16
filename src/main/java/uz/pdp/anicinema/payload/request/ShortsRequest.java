package uz.pdp.anicinema.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static uz.pdp.anicinema.utils.contant.MessageKey.ATTACHMENT_NOT_VALID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortsRequest {

    private String title;

    @NotNull(message = ATTACHMENT_NOT_VALID)
    private Long videoId;

}
