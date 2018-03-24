package org.melusky.bookbash.security.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple placeholder for info extracted from the JWT
 *
 * @author pascal alma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDto {

    private Long id;
    private String username;
    private String role;
}