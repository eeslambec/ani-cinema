package uz.pdp.anicinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.anicinema.entity.User;
import uz.pdp.anicinema.payload.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "photo", expression = "java(user.getPhoto().getUrl())")
    @Mapping(target = "subscriptionStatus", expression = "java(user.getSubscription() != null ? user.getSubscription().getStatus().name() : null)")
    @Mapping(target = "subscriptionStartDate", expression = "java(user.getSubscription() != null ? uz.pdp.anicinema.utils.DateUtils.toLocalDateTime(user.getSubscription().getStartDate()).toString() : null)")
    @Mapping(target = "subscriptionEndDate", expression = "java(user.getSubscription() != null ? uz.pdp.anicinema.utils.DateUtils.toLocalDateTime(user.getSubscription().getEndDate()).toString() : null)")
    @Mapping(target = "plan", expression = "java(user.getSubscription() != null ? user.getSubscription().getPlan() : null)")
    @Mapping(target = "joinedDate", expression = "java(uz.pdp.anicinema.utils.DateUtils.toLocalDateTime(user.getCreatedDate()).toString())")
    UserResponse toResponse(User user);

}
