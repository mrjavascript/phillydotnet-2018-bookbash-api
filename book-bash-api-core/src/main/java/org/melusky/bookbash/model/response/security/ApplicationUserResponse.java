package org.melusky.bookbash.model.response.security;

import lombok.Data;

/**
 * Created by mikem on 7/10/2017.
 */
@Data
public class ApplicationUserResponse {

    private Long userId;
    private String userName;
    private String emailAddress;
    private String jwt;
}
