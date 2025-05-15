package rca.restapi.year2.hotelbookingms.dtos;

import lombok.Data;
import rca.restapi.year2.hotelbookingms.enums.UserRoles;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private UserRoles role;
}

