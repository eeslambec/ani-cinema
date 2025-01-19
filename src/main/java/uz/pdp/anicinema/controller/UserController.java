package uz.pdp.anicinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.anicinema.service.UserService;
import uz.pdp.anicinema.utils.enums.Code;

import static uz.pdp.anicinema.payload.response.ResponseData.ok;
import static uz.pdp.anicinema.utils.contant.AppConstant.API_V1;
import static uz.pdp.anicinema.utils.contant.AppConstant.USER;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + USER)
public class UserController {

    private final UserService userService;

    @GetMapping("/subscribed")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> subscribed(){
        return ok(Code.SUCCESS, userService.getAllSubscribed());
    }

    @GetMapping("/list-admins")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> listAdmins(){
        return ok(Code.SUCCESS, userService.getAllAdmins());
    }
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> getAll(){
        return ok(Code.SUCCESS, userService.getAll());
    }

    @GetMapping("/list-liked")
    public ResponseEntity<?> listLiked(){
        return ok(Code.SUCCESS, userService.countAllLikedMovies());
    }
}
