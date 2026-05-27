package com.smartjobportal.dto;

import jakarta.validation.constraints.NotBlank;

public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public TokenRefreshRequest() {}

    public TokenRefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static TokenRefreshRequestBuilder builder() { return new TokenRefreshRequestBuilder(); }

    public static class TokenRefreshRequestBuilder {
        private String refreshToken;

        public TokenRefreshRequestBuilder refreshToken(String refreshToken) { this.refreshToken = refreshToken; return this; }

        public TokenRefreshRequest build() {
            TokenRefreshRequest instance = new TokenRefreshRequest();
            instance.refreshToken = this.refreshToken;
            return instance;
        }
    }
}
