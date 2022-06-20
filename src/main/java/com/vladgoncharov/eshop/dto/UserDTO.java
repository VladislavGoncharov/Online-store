package com.vladgoncharov.eshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String matchingPassword;

}
