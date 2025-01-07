package uz.pdp.anicinema.entity;


import uz.pdp.anicinema.utils.enums.Role;
import uz.pdp.anicinema.utils.enums.SubscriptionStatus;
import uz.pdp.anicinema.utils.enums.UserStatus;


public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Attachment photo;
    private UserStatus status;
    private Subscription subscription;
}
