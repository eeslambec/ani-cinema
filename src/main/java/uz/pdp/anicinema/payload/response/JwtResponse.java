package uz.pdp.anicinema.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {

    private String token;

    private String refreshToken;

}
