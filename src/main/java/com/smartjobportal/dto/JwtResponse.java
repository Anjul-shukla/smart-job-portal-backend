package com.smartjobportal.dto;


public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String email;
    private String role;

    public JwtResponse(String accessToken, String refreshToken, Long id, String email, String role) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public JwtResponse() {}



    public static JwtResponseBuilder builder() { return new JwtResponseBuilder(); }

    public static class JwtResponseBuilder {
        private String token;
        private String refreshToken;
        private Long id;
        private String email;
        private String role;

        public JwtResponseBuilder token(String token) { this.token = token; return this; }
        public JwtResponseBuilder refreshToken(String refreshToken) { this.refreshToken = refreshToken; return this; }
        public JwtResponseBuilder id(Long id) { this.id = id; return this; }
        public JwtResponseBuilder email(String email) { this.email = email; return this; }
        public JwtResponseBuilder role(String role) { this.role = role; return this; }

        public JwtResponse build() {
            JwtResponse instance = new JwtResponse();
            instance.token = this.token;
            instance.refreshToken = this.refreshToken;
            instance.id = this.id;
            instance.email = this.email;
            instance.role = this.role;
            return instance;
        }
    }
}
