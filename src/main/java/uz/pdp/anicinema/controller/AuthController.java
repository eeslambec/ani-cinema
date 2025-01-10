package uz.pdp.anicinema.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.anicinema.payload.request.EmailVerificationRequest;
import uz.pdp.anicinema.payload.request.LoginRequest;
import uz.pdp.anicinema.payload.request.RegisterRequest;
import uz.pdp.anicinema.payload.response.MessageResponse;
import uz.pdp.anicinema.service.UserService;
import uz.pdp.anicinema.utils.contant.MessageKey;
import uz.pdp.anicinema.utils.enums.Code;

import static uz.pdp.anicinema.payload.response.ResponseData.*;
import static uz.pdp.anicinema.utils.contant.AppConstant.API_V1;
import static uz.pdp.anicinema.utils.contant.AppConstant.AUTH;


@RestController
@RequestMapping(API_V1 + AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {

        userService.register(request);

        return ok(Code.SUCCESS, new MessageResponse(MessageKey.SUCCESS_MESSAGE));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return ok(Code.SUCCESS, userService.login(request));
    }

    @PutMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody @Valid EmailVerificationRequest request){
        return ok(Code.SUCCESS, userService.verify(request));
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam("refreshToken") String refreshToken){
        return ok(Code.SUCCESS, userService.refresh(refreshToken));
    }

}
