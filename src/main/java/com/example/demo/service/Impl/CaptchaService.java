package com.example.demo.service.Impl;

import com.example.demo.security.CaptchaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaService {
    @Value("${google.recaptcha.secret}")
    private String secretKey;


    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private final RestTemplate restTemplate;
    public boolean verify(String captchaToken) {
        if (captchaToken == null || captchaToken.isBlank()) return false;

        String url = VERIFY_URL + "?secret=" + secretKey + "&response=" + captchaToken;

        try {
            ResponseEntity<CaptchaResponse> resp =
                    restTemplate.postForEntity(url, null, CaptchaResponse.class);

            CaptchaResponse body = resp.getBody();
            boolean ok = body != null && body.isSuccess();

            if (!ok) {
                log.info("reCAPTCHA failed: {}", (body != null ? body.getErrorCodes() : "null"));
            }
            return ok;

        } catch (Exception ex) {
            log.warn("Error verifying reCAPTCHA: {}", ex.getMessage());
            return false;
        }
    }
}
