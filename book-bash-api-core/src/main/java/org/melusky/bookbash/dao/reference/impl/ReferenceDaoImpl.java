package org.melusky.bookbash.dao.reference.impl;

import com.querydsl.jpa.impl.JPAQuery;
import org.melusky.bookbash.dao.reference.ReferenceDao;
import org.melusky.bookbash.persistence.model.obj.bookBash.SecurityRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.melusky.bookbash.persistence.model.obj.bookBash.QSecurityRole.securityRole;


/**
 * Created by mikem on 7/9/2017.
 */
@Repository
public class ReferenceDaoImpl implements ReferenceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SecurityRole> getSecurityRoles() {
        return new JPAQuery<Void>(entityManager)
                .select(securityRole)
                .from(securityRole)
                .where(securityRole.dateDisabled.isNull())
                .fetch();
    }
}
