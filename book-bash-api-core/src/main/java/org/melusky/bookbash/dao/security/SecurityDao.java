package org.melusky.bookbash.dao.security;

import org.melusky.bookbash.model.request.security.UserCreateRequest;
import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;

/**
 * Created by mikem on 7/10/2017.
 */
public interface SecurityDao {
    ApplicationUser createUserAccount(UserCreateRequest userCreateRequest);

    ApplicationUser findUserByLoginDetails(UserLoginRequest userLoginRequest);

    ApplicationUser findUserByUserName(String userName);

    ApplicationUser findUserByUserId(Long userId);

    ApplicationUser findUserByEmailAddress(String emailAddress);

    ApplicationUser resetUserPassword(Long userId, String newPassword);

    ApplicationUser createUserAccountViaEmailAddress(String emailAddress);
}
