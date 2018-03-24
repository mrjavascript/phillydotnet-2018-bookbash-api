package org.melusky.bookbash.controller.security;

import com.google.common.base.Joiner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.melusky.bookbash.model.request.security.UserCreateRequest;
import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.melusky.bookbash.model.response.security.ApplicationUserResponse;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;
import org.melusky.bookbash.security.transfer.JwtUserDto;
import org.melusky.bookbash.security.util.JwtTokenGenerator;
import org.melusky.bookbash.service.security.SecurityService;
import org.melusky.bookbash.utility.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mikem on 7/10/2017.
 */
@RestController
@Api(value = "bookbash_api", description = "Request methods used to modify and retrieve account details.")
public class SecurityRestController {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "/user/create")
    @ApiOperation(value = "Creates an account", response = ApplicationUserResponse.class)
    public ApplicationUserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {

        //
        //  Make sure the confirmation email is sent out via unit tests
        if (userCreateRequest.getSendConfirmationEmail() != Boolean.FALSE) {
            userCreateRequest.setSendConfirmationEmail(Boolean.TRUE);
        }

        //
        //  Validate account creation
        List<String> errors = securityService.validateUserAccountCreation(userCreateRequest);
        if (errors != null && !errors.isEmpty()) {
            throw new IllegalArgumentException(Joiner.on(",").join(errors));
        }

        //
        //  Create account
        ApplicationUser user = securityService.createUserAccount(userCreateRequest);
        if (user == null || user.getId() == null || user.getId() <= 0) {
            throw new IllegalArgumentException("There were errors creating the account");
        }

        //
        //  Return response object
        ApplicationUserResponse response = new ApplicationUserResponse();
        response.setUserId(user.getId());
        response.setUserName(user.getUsername());
        response.setJwt(createJwtToken(user));
        return response;
    }

    @PostMapping(value = "/user/login")
    @ApiOperation(value = "Logs in a user", response = ApplicationUserResponse.class)
    public ApplicationUserResponse loginUser(@RequestBody UserLoginRequest userLoginRequest) {

        //
        //  Login account
        ApplicationUser user = securityService.findUserByLoginDetails(userLoginRequest);
        if (user == null || user.getId() == null || user.getId() <= 0) {
            throw new IllegalArgumentException("The account was not found or the password was incorrect");
        } else if (user != null && user.getDateDisabled() != null) {
            throw new IllegalArgumentException("The account is currently inactive and cannot be used.");
        }

        //
        //  Return response object
        ApplicationUserResponse response = new ApplicationUserResponse();
        response.setUserId(user.getId());
        response.setUserName(user.getUsername());
        response.setJwt(createJwtToken(user));
        return response;
    }

    @PostMapping(value = "/api/user/password/reset")
    @ApiOperation(value = "Resets a user's password", response = String.class)
    public String resetUserPassword(@RequestParam String newPassword) {
        securityService.resetUserPassword(SecurityHelper.getCurrentUserId(), newPassword);
        return "Your password was successfully reset";
    }

    @PostMapping(value = "/user/facebook-oauth-callback")
    @ApiOperation(value = "Finds or creates an account via email address on Facebook login", response = ApplicationUserResponse.class)
    public ApplicationUserResponse facebookOauthCallback(@RequestParam String emailAddress) {

        //
        //  Validate
        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("Email address is required!");
        }

        //
        //  Service call
        ApplicationUser applicationUser = securityService.findOrCreateAccountByEmailAddress(emailAddress);
        if (applicationUser == null || applicationUser.getId() == null) {
            throw new IllegalStateException("Unable to find user via email!");
        }

        //
        //  Return response object
        ApplicationUserResponse response = new ApplicationUserResponse();
        response.setUserId(applicationUser.getId());
        response.setUserName(applicationUser.getUsername());
        response.setJwt(createJwtToken(applicationUser));
        return response;
    }

    private String createJwtToken(ApplicationUser user) {
        JwtUserDto dto = new JwtUserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole("user");    // TODO change me when security is implemented!
        return JwtTokenGenerator.generateToken(dto, secret);
    }

}
