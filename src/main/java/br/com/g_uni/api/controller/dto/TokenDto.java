package br.com.g_uni.api.controller.dto;

public class TokenDto {

    private String token;
    private String type;

    public TokenDto(String token, String type) {
        this.token = token;
        this.type = type;
    }

    // Getters
    public String getToken() {
        return token;
    }
    public String getType() {
        return type;
    }
}
