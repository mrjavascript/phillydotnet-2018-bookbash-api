package org.melusky.bookbash.service.security.impl;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.melusky.bookbash.dao.security.SecurityDao;
import org.melusky.bookbash.model.request.security.UserCreateRequest;
import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;
import org.melusky.bookbash.service.security.SecurityService;
import org.melusky.bookbash.utility.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mikem on 7/10/2017.
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @Autowired
    private SecurityDao securityDao;

    private PasswordEncoder passwordEncoder;

    public SecurityServiceImpl() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<String> validateUserAccountCreation(UserCreateRequest userCreateRequest) {

        //
        //  Validate form data
        if (userCreateRequest.getUserName() == null || userCreateRequest.getUserName().isEmpty()) {
            return Arrays.asList("User name is required");
        } else if (userCreateRequest.getPassword() == null || userCreateRequest.getPassword().isEmpty()) {
            return Arrays.asList("Password is required");
        } else if (userCreateRequest.getPasswordConfirm() == null || userCreateRequest.getPasswordConfirm().isEmpty()) {
            return Arrays.asList("Password confirmation is required");
        } else if (!userCreateRequest.getPassword().equals(userCreateRequest.getPasswordConfirm())) {
            return Arrays.asList("Password and confirmation do not match");
        } else if (userCreateRequest.getEmailAddress() == null || userCreateRequest.getEmailAddress().isEmpty()) {
            return Arrays.asList("Email address is required");
        }

        //
        //  Look for duplicate users
        ApplicationUser user = securityDao.findUserByUserName(userCreateRequest.getUserName());
        if (user != null && user.getId() > 0) {
            return Arrays.asList("An account currently exists with this user name");
        }

        //
        //  Validate the password
        return PasswordValidator.validate(userCreateRequest.getPassword());
    }

    @Override
    @Transactional(value = "transactionManager")
    public ApplicationUser createUserAccount(UserCreateRequest userCreateRequest) {

        //
        //  Validate
        List<String> errors = validateUserAccountCreation(userCreateRequest);
        if (errors != null && !errors.isEmpty()) {
            throw new IllegalStateException(Joiner.on(", ").join(errors));
        }
        return securityDao.createUserAccount(userCreateRequest);
    }

    @Override
    public ApplicationUser findUserByLoginDetails(UserLoginRequest userLoginRequest) {

        //
        //  Validate
        if (userLoginRequest.getUserName() == null || userLoginRequest.getUserName().isEmpty()) {
            throw new IllegalStateException("User name is required");
        } else if (userLoginRequest.getPassword() == null || userLoginRequest.getPassword().isEmpty()) {
            throw new IllegalStateException("Password is required");
        }

        ApplicationUser user = securityDao.findUserByLoginDetails(userLoginRequest);
        if (user == null) {
            throw new IllegalStateException("User not found");
        } else if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPasswordHash())) {
            throw new IllegalStateException("Passwords do not match");
        } else if (user.getDateDisabled() != null) {
            throw new IllegalStateException("Account is disabled");
        }

        return user;
    }

    @Override
    public ApplicationUser findUserByUserId(Long userId) {
        //
        //  Validate
        if (userId == null || userId < 0) {
            throw new IllegalStateException("User ID is required");
        }

        ApplicationUser user = securityDao.findUserByUserId(userId);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        return user;
    }

    @Override
    public ApplicationUser validatePasswordResetEmailAddress(String emailAddress) {

        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalStateException("Email address is required");
        }
        ApplicationUser applicationUser = securityDao.findUserByEmailAddress(emailAddress);
        if (applicationUser == null) {
            throw new IllegalStateException("User not found with email address " + emailAddress);
        }
        return applicationUser;
    }

    @Override
    public void resetUserPassword(Long userId, String newPassword) {
        //
        //  Validate
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalStateException("Password is required");
        }

        List<String> errors = PasswordValidator.validate(newPassword);
        if (errors != null && errors.size() > 0) {
            throw new IllegalStateException(Joiner.on(", ").join(errors));
        }

        //
        //  reset password
        securityDao.resetUserPassword(userId, newPassword);
    }

    @Override
    @Transactional(value = "transactionManager")
    public ApplicationUser findOrCreateAccountByEmailAddress(String emailAddress) {

        ApplicationUser response = securityDao.findUserByEmailAddress(emailAddress);
        if (response != null && response.getId() > 0) {
            return response;
        }
        return securityDao.createUserAccountViaEmailAddress(emailAddress);
    }

}
