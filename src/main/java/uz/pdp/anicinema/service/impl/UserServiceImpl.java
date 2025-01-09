package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.User;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.payload.request.EmailVerificationRequest;
import uz.pdp.anicinema.payload.request.LoginRequest;
import uz.pdp.anicinema.payload.request.RegisterRequest;
import uz.pdp.anicinema.payload.response.JwtResponse;
import uz.pdp.anicinema.repository.UserRepository;
import uz.pdp.anicinema.security.CustomUserDetails;
import uz.pdp.anicinema.security.JwtProvider;
import uz.pdp.anicinema.service.MailService;
import uz.pdp.anicinema.service.UserService;
import uz.pdp.anicinema.utils.enums.Role;
import uz.pdp.anicinema.utils.enums.UserStatus;
import uz.pdp.anicinema.utils.validator.RegexValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MailService mailService;

    @Override
    public void register(RegisterRequest request) {


        if (RegexValidator.email(request.getEmail())) {
            throw BadRequestException.emailNoValid();
        }

        if (RegexValidator.password(request.getPassword())) {
            throw BadRequestException.passwordNoValid();
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            throw BadRequestException.userAlreadyExists();

        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .gender(request.getGender())
                .role(Role.USER)
                .lvl(0L)
                .status(UserStatus.NOT_VERIFIED)
                .balance(BigDecimal.ZERO)
                .build();

        userRepository.save(user);

        mailService.sendVerificationCode(request.getEmail());

    }

    @Override
    public JwtResponse verify(EmailVerificationRequest request) {

        if (mailService.verify(request.getEmail(),request.getCode())) {

            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(BadRequestException::userNotFound);

            user.setStatus(UserStatus.ACTIVE);

            user = userRepository.save(user);

            String token = jwtProvider.generateToken(CustomUserDetails.from(user));

            String refreshToken = jwtProvider.generateRefreshToken(CustomUserDetails.from(user));

            return new JwtResponse(token,refreshToken);

        }

        throw BadRequestException.verificationCodeInvalid();

    }

    @Override
    public JwtResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(BadRequestException::userNotFound);

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            String token = jwtProvider.generateToken(CustomUserDetails.from(user));

            String refreshToken = jwtProvider.generateRefreshToken(CustomUserDetails.from(user));

            return new JwtResponse(token,refreshToken);

        }

        throw BadRequestException.passwordNoValid();

    }

    @Override
    public JwtResponse refresh(String refreshToken) {

        if (jwtProvider.isTokenExpired(refreshToken)) {
            throw BadRequestException.tokenIsExpired();
        }

        String username = jwtProvider.extractUsername(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(BadRequestException::userNotFound);

        String token = jwtProvider.generateToken(CustomUserDetails.from(user));

        String newRefreshToken = jwtProvider.generateRefreshToken(CustomUserDetails.from(user));

        return new JwtResponse(token,newRefreshToken);

    }
}
