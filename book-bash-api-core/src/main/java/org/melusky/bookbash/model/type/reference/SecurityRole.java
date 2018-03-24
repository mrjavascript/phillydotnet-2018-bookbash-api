package org.melusky.bookbash.model.type.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mikem on 7/12/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityRole {

    private Long id;
    /** Field mapping. */
    private String roleDescription;
    /** Field mapping. */
    private String roleName;

}
