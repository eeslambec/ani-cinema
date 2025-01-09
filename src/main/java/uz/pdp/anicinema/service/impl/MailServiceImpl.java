package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.service.MailService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailSender mailSender;

    @Async
    @Override
    public void sendVerificationCode(String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("  -----<Project name>----- ");
        Integer code = generatePassword();
        message.setText("Your verification code: -> " + code);
        mailSender.send(message);

        VERIFICATION_CODES.put(email,String.valueOf(code));

    }

    public boolean verify(String email, String code) {

        String existCode = VERIFICATION_CODES.get(email);

        if (code == null || existCode == null) {
            return false;
        }

        return existCode.equals(code);

    }

    private Integer generatePassword() {
        return new Random().nextInt(9000) + 1000;
    }


}
