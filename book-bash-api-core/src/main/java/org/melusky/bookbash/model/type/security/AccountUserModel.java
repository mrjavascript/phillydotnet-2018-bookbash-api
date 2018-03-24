package org.melusky.bookbash.model.type.security;

import lombok.Data;

@Data
public class AccountUserModel {

    private Long userId;
    private String emailAddress;
    private String userName;

}
