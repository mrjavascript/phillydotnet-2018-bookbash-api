package org.melusky.bookbash.model.request.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mikem on 7/10/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    private String userName;
    private String password;
}
