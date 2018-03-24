package org.melusky.bookbash.service.security;

import org.melusky.bookbash.model.request.security.UserCreateRequest;
import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;

import java.util.List;

/**
 * Created by mikem on 7/10/2017.
 */
public interface SecurityService {
    List<String> validateUserAccountCreation(UserCreateRequest userCreateRequest);

    ApplicationUser createUserAccount(UserCreateRequest userCreateRequest);

    ApplicationUser findUserByLoginDetails(UserLoginRequest userLoginRequest);

    ApplicationUser findUserByUserId(Long userId);

    ApplicationUser validatePasswordResetEmailAddress(String emailAddress);

    void resetUserPassword(Long userId, String newPassword);

    ApplicationUser findOrCreateAccountByEmailAddress(String emailAddress);
}
