package com.app.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTTokenDTO {
    private String token;
    private String tokenType;
}
