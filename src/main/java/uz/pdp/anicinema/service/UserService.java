package uz.pdp.anicinema.service;

import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.User;
import uz.pdp.anicinema.payload.request.EmailVerificationRequest;
import uz.pdp.anicinema.payload.request.LoginRequest;
import uz.pdp.anicinema.payload.request.RegisterRequest;
import uz.pdp.anicinema.payload.response.JwtResponse;

import java.util.List;

@Service
public interface UserService {

    void register(RegisterRequest request);

    JwtResponse verify(EmailVerificationRequest request);

    JwtResponse login(LoginRequest request);

    JwtResponse refresh(String refreshToken);

    User findById(Long id);

    List<User> findAllSubscribed();

    List<User> findAllAdmins();

    List<User> findAllActiveUsers();

    Long countAllLikedMovies();
}
