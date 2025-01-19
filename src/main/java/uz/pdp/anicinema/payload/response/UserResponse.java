package uz.pdp.anicinema.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.anicinema.entity.Plan;
import uz.pdp.anicinema.utils.enums.Role;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String photo;

    private String joinedDate;

    private Role role;

    private String subscriptionStartDate;

    private String subscriptionEndDate;

    private Plan plan;

    private String subscriptionStatus;

}
