package uz.pdp.anicinema.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.anicinema.payload.request.MovieCreateRequest;
import uz.pdp.anicinema.payload.request.MovieUpdateRequest;
import uz.pdp.anicinema.payload.response.MessageResponse;
import uz.pdp.anicinema.service.MovieService;
import uz.pdp.anicinema.utils.contant.MessageKey;
import uz.pdp.anicinema.utils.enums.Code;
import uz.pdp.anicinema.utils.enums.MovieStatus;

import static uz.pdp.anicinema.payload.response.ResponseData.ok;
import static uz.pdp.anicinema.utils.contant.AppConstant.API_V1;
import static uz.pdp.anicinema.utils.contant.AppConstant.MOVIE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_V1 + MOVIE)
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<?> save(@RequestBody @Valid MovieCreateRequest request){
        movieService.save(request);
        return ok(Code.SUCCESS, new MessageResponse(MessageKey.SUCCESS_MESSAGE));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN','MODERATOR')")
    public ResponseEntity<?> update(@RequestBody @Valid MovieUpdateRequest request){
        movieService.update(request);
        return ok(Code.SUCCESS, new MessageResponse(MessageKey.SUCCESS_MESSAGE));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN','MODERATOR')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        movieService.delete(id);
        return ok(Code.SUCCESS, new MessageResponse(MessageKey.SUCCESS_MESSAGE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ok(Code.SUCCESS, movieService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        return ok(Code.SUCCESS, movieService.getAll());
    }

    @GetMapping("/list/soon")
    public ResponseEntity<?> getAllSoon(){
        return ok(Code.SUCCESS, movieService.getAllSoon());
    }

    @GetMapping("/list/{status}")
    public ResponseEntity<?> getAllByStatus(@PathVariable MovieStatus status){
            return ok(Code.SUCCESS, movieService.getAllByStatus(status));
    }

    @GetMapping("/list/top-rated")
    public ResponseEntity<?> getAllTopRated(){
        return ok(Code.SUCCESS, movieService.getAllTopRated());
    }

}
