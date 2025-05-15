package rca.restapi.year2.hotelbookingms.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String name;
    private String email;
    private String password;
}
