package org.melusky.bookbash.service.reference.impl;

import org.melusky.bookbash.dao.reference.ReferenceDao;
import org.melusky.bookbash.persistence.model.obj.bookBash.SecurityRole;
import org.melusky.bookbash.service.reference.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mikem on 7/9/2017.
 */
@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    private ReferenceDao referenceDao;

    @Override
    public List<SecurityRole> getSecurityRoles() {
        return referenceDao.getSecurityRoles();
    }
}
