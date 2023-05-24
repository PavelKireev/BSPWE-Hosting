package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.config.AuthUser;
import cz.upce.bpswehosting.model.user.LoginModel;
import cz.upce.bpswehosting.model.user.RegistrationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtEncoder encoder;
    private final UserService userService;

    @Override
    public String signIn(Authentication authentication, LoginModel model) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(authentication.getName())
            .claim("scope", scope)
            .claim("userId", authUser.getId())
            .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public void signUp(RegistrationModel model) {
        userService.create(model);
    }
}
