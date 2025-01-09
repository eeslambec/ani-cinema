package uz.pdp.anicinema.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
public class JwtProps {

    @Value("${token.secret.key}")
    private String jwtSecretKey;

    @Value("${token.expiration}")
    private String jwtExpiration;

    @Value("${token.refresh.expiration}")
    private String jwtRefreshExpiration;

}