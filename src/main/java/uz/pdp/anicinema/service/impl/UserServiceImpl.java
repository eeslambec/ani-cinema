package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.User;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.mapper.UserMapper;
import uz.pdp.anicinema.payload.request.EmailVerificationRequest;
import uz.pdp.anicinema.payload.request.LoginRequest;
import uz.pdp.anicinema.payload.request.RegisterRequest;
import uz.pdp.anicinema.payload.response.JwtResponse;
import uz.pdp.anicinema.payload.response.UserResponse;
import uz.pdp.anicinema.repository.UserRepository;
import uz.pdp.anicinema.security.CustomUserDetails;
import uz.pdp.anicinema.security.JwtProvider;
import uz.pdp.anicinema.service.AttachmentService;
import uz.pdp.anicinema.service.MailService;
import uz.pdp.anicinema.service.UserService;
import uz.pdp.anicinema.utils.DateUtils;
import uz.pdp.anicinema.utils.enums.Role;
import uz.pdp.anicinema.utils.enums.Status;
import uz.pdp.anicinema.utils.validator.RegexValidator;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtProvider jwtProvider;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AttachmentService attachmentService;
    private final UserMapper userMapper;

    @Override
    public void register(RegisterRequest request) {

        if (RegexValidator.email(request.getEmail())) {
            throw BadRequestException.emailNoValid();
        }

        if (RegexValidator.password(request.getPassword())) {
            throw BadRequestException.passwordNoValid();
        }

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if(user.getStatus() == Status.NOT_VERIFIED) {

                mailService.sendVerificationCode(request.getEmail());

                return;

            }

            throw BadRequestException.userAlreadyExists();

        }

        optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if(user.getStatus() == Status.NOT_VERIFIED) {

                mailService.sendVerificationCode(request.getEmail());

                return;

            }

            throw BadRequestException.userAlreadyExists();

        }


        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .gender(request.getGender())
                .role(Role.USER)
                .photo(attachmentService.getDefaultUserPic())
                .lvl(0L)
                .status(Status.NOT_VERIFIED)
                .balance(BigDecimal.ZERO)
                .build();

        userRepository.save(user);

        mailService.sendVerificationCode(request.getEmail());

    }

    @Override
    public JwtResponse verify(EmailVerificationRequest request) {

        if (mailService.verify(request.getEmail(),request.getCode())) {

            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(BadRequestException::userNotFound);

            user.setStatus(Status.ACTIVE);

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

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(BadRequestException::userNotFound);
    }

    @Override
    public List<UserResponse> getAllSubscribed() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getSubscription() != null)
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getAllAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(user->!user.getRole().equals(Role.USER))
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Long countAllLikedMovies() {
        return userRepository.findAll()
                .stream()
                .mapToLong(user->user.getLikedMovies().size())
                .sum();
    }
}
