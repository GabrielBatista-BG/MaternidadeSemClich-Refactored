package com.maternidade.maternidadeBackendApp.application.usecase;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.maternidade.maternidadeBackendApp.application.dto.request.GoogleTokenRequest;
import com.maternidade.maternidadeBackendApp.application.dto.response.GoogleUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoogleAuthUseCase {

    @Value("${google.client-id}")
    private String googleClientId;

    public GoogleUserResponse execute(GoogleTokenRequest request) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(request.getTokenID());

            if (idToken == null) {
                throw new SecurityException("Token Google inválido.");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();

            return GoogleUserResponse.builder()
                    .nome((String) payload.get("name"))
                    .email(payload.getEmail())
                    .userId("")
                    .picture((String) payload.get("picture"))
                    .build();

        } catch (SecurityException e) {
            throw e;
        } catch (Exception e) {
            throw new SecurityException("Erro ao verificar token Google: " + e.getMessage());
        }
    }
}
