package com.example.ISA2020.entity;


//DTO klasa enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {

    private String jwtAccessToken;
    private Long expiresIn;

    public UserTokenState() {
        this.jwtAccessToken = null;
        this.expiresIn = null;
    }

    public UserTokenState(String jwtAccessToken, long expiresIn) {
        this.jwtAccessToken = jwtAccessToken;
        this.expiresIn = expiresIn;
    }

    public void setJwtAccessToken(String jwtAccessToken) {
        this.jwtAccessToken = jwtAccessToken;
    }

    public String getJwtAccessToken() {
        return jwtAccessToken;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    @Override
    public String toString() {
        return "UserTokenState{" +
                "jwtAccessToken='" + jwtAccessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }

}
