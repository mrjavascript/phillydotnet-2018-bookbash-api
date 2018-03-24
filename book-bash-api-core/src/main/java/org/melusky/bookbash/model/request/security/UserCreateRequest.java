package org.melusky.bookbash.model.request.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mikem on 7/10/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String userName;
    private String emailAddress;
    private String password;
    private String passwordConfirm;
    private Boolean sendConfirmationEmail = null;

}
