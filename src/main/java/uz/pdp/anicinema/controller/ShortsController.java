package uz.pdp.anicinema.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.anicinema.payload.request.ShortsRequest;
import uz.pdp.anicinema.payload.response.MessageResponse;
import uz.pdp.anicinema.service.ShortsService;

import static uz.pdp.anicinema.payload.response.ResponseData.ok;
import static uz.pdp.anicinema.utils.contant.AppConstant.API_V1;
import static uz.pdp.anicinema.utils.contant.AppConstant.SHORTS;
import static uz.pdp.anicinema.utils.contant.MessageKey.SUCCESS_MESSAGE;
import static uz.pdp.anicinema.utils.enums.Code.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + SHORTS)
public class ShortsController {

    private final ShortsService shortsService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<?> save(@RequestBody @Valid ShortsRequest request){
        shortsService.save(request);
        return ok(SUCCESS, new MessageResponse(SUCCESS_MESSAGE));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<?> getAll(){
        return ok(SUCCESS, shortsService.getAll());
    }

    @GetMapping("/list/randomly")
    public ResponseEntity<?> getAllForUsers(){
        return ok(SUCCESS, shortsService.getAllForUsers());
    }

}
