package org.melusky.bookbash.security.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.melusky.bookbash.Application;
import org.melusky.bookbash.config.H2JpaConfig;
import org.melusky.bookbash.security.transfer.JwtUserDto;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mikem on 6/21/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
public class JwtTokenGeneratorTest {

    @Test
    public void generateToken() throws Exception {

        JwtUserDto user = new JwtUserDto();
        user.setId(123L);
        user.setUsername("Pascal");
        user.setRole("admin");

        System.out.println("**************************************\n\n"
                + JwtTokenGenerator.generateToken(user, "my-very-secret-key")
                + "\n\n**************************************");

    }

}