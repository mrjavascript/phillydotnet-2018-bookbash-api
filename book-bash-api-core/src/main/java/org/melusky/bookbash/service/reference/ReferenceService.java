package org.melusky.bookbash.service.reference;

import org.melusky.bookbash.persistence.model.obj.bookBash.SecurityRole;

import java.util.List;

/**
 * Created by mikem on 7/9/2017.
 */
public interface ReferenceService {
    List<SecurityRole> getSecurityRoles();
}
