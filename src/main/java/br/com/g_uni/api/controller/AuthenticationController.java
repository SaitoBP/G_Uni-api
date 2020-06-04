package br.com.g_uni.api.controller;

import br.com.g_uni.api.config.security.TokenService;
import br.com.g_uni.api.controller.dto.TokenDto;
import br.com.g_uni.api.controller.form.AuthenticationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
@RestController @RequestMapping("/auth")
public class AuthenticationController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private TokenService tokenService;

    @PostMapping @Transactional
    public ResponseEntity<TokenDto> auth(@RequestBody @Valid AuthenticationForm authenticationForm) {
        UsernamePasswordAuthenticationToken loginData = authenticationForm.convert();

        try {
            Authentication authentication = authManager.authenticate(loginData);

           // Geração do token
           String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
