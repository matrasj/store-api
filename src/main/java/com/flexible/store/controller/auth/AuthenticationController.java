package com.flexible.store.controller.auth;

import com.flexible.store.payload.auth.AuthenticationRequestPayload;
import com.flexible.store.payload.auth.RegistrationRequestPayload;
import com.flexible.store.payload.auth.AuthenticationResponsePayload;
import com.flexible.store.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponsePayload> register(@RequestBody RegistrationRequestPayload requestPayload) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.authenticationService.register(requestPayload));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponsePayload> authenticate(@RequestBody AuthenticationRequestPayload requestPayload) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.authenticationService.authenticate(requestPayload));
    }

    @GetMapping("/confirmation/token/{tokenId}")
    public ResponseEntity<String> confirmAccount(@PathVariable Long tokenId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.authenticationService.confirmToken(tokenId));
    }

}
