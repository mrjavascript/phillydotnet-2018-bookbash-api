package org.melusky.bookbash.controller.base;

import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;
import org.melusky.bookbash.security.model.AuthenticatedUser;
import org.melusky.bookbash.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mikem on 7/11/2017.
 */
@Component
public class RestControllerTest {

    @Value("${jwt.secret}")
    private String secret;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    protected MockMvc mockMvc;

    protected HttpMessageConverter mappingJackson2HttpMessageConverter;

    protected void setUp() throws Exception {

        //
        //  add stuff to me
    }

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    protected void setAuthenticationUserById(Long userId) {
        ApplicationUser user = securityService.findUserByUserId(userId);
        setSecurityAuthenticationUser(user);
    }

    protected void setAuthenticationUserByUserNamePassword(String userName, String password) {
        ApplicationUser user = securityService.findUserByLoginDetails(new UserLoginRequest(userName, password));
        setSecurityAuthenticationUser(user);
    }

    private void setSecurityAuthenticationUser(ApplicationUser user) {

        //
        //  Mock a user for testing purposes
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user.getId(), user.getUsername(), null, null);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authenticatedUser, null));
    }
}
