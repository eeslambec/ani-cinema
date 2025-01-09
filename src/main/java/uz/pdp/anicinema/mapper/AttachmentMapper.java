package uz.pdp.anicinema.mapper;

import org.mapstruct.Mapper;
import uz.pdp.anicinema.entity.Attachment;
import uz.pdp.anicinema.payload.response.AttachmentResponse;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

    AttachmentResponse toResponse(Attachment attachment);

}
