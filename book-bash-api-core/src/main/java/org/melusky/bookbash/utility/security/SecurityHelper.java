package org.melusky.bookbash.utility.security;

import org.melusky.bookbash.security.model.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by mikem on 6/1/2017.
 */
public class SecurityHelper {

    public static Long getCurrentUserId() {
        AuthenticatedUser currentUser = getCurrentUser();
        return currentUser != null ? currentUser.getId() : null;
    }

    public static AuthenticatedUser getCurrentUser() {
        return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
