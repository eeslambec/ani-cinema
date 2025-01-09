package uz.pdp.anicinema.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.User;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.repository.UserRepository;
import uz.pdp.anicinema.utils.DateUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class CustomUserDetailsService implements UserDetailsService {

    @Lazy
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(BadRequestException::userNotFound);

        user.setLastLogin(DateUtils.toMillis(LocalDateTime.now()));

        user = userRepository.save(user);

        return CustomUserDetails.from(user);
    }
}