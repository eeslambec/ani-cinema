package uz.pdp.anicinema.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public interface MailService {

    Map<String,String> VERIFICATION_CODES = new HashMap<>();

    void sendVerificationCode(String email);

    boolean verify(String email, String code);



}
