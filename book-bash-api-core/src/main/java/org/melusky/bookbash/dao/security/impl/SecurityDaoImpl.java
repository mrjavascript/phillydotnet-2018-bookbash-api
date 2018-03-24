package org.melusky.bookbash.dao.security.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.melusky.bookbash.config.Constants;
import org.melusky.bookbash.dao.security.SecurityDao;
import org.melusky.bookbash.model.request.security.UserCreateRequest;
import org.melusky.bookbash.model.request.security.UserLoginRequest;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;
import org.melusky.bookbash.persistence.model.obj.bookBash.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static org.melusky.bookbash.persistence.model.obj.bookBash.QApplicationUser.applicationUser;

/**
 * Created by mikem on 7/10/2017.
 */
@Repository
public class SecurityDaoImpl implements SecurityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private Environment environment;

    // Constructor
    private PasswordEncoder passwordEncoder;

    private final String DEFAULT_PASSWORD = "abcde12345";

    public SecurityDaoImpl() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ApplicationUser createUserAccount(UserCreateRequest userCreateRequest) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(userCreateRequest.getUserName());
        applicationUser.setPasswordHash(passwordEncoder.encode(userCreateRequest.getPassword()));
        applicationUser.setEmailAddress(userCreateRequest.getEmailAddress());
        applicationUser.setDateCreated(new Date());
        applicationUser.setUserCreated(Constants.ApplicationUser.ADMINISTRATIVE_USER_ID);
        applicationUser.setDateUpdated(new Date());
        applicationUser.setUserUpdated(Constants.ApplicationUser.ADMINISTRATIVE_USER_ID);
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public ApplicationUser findUserByLoginDetails(UserLoginRequest userLoginRequest) {
        return new JPAQuery<Void>(entityManager)
                .select(applicationUser)
                .from(applicationUser)
                .where(applicationUser.username.equalsIgnoreCase(userLoginRequest.getUserName()))
                .fetchFirst();
    }

    @Override
    public ApplicationUser findUserByUserName(String userName) {
        return new JPAQuery<Void>(entityManager)
                .select(applicationUser)
                .from(applicationUser)
                .where(applicationUser.username.equalsIgnoreCase(userName))
                .fetchFirst();
    }

    @Override
    public ApplicationUser findUserByUserId(Long userId) {
        return applicationUserRepository.findOne(userId);
    }

    @Override
    public ApplicationUser findUserByEmailAddress(String emailAddress) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(applicationUser)
                .where(applicationUser.emailAddress.equalsIgnoreCase(emailAddress))
                .fetchOne();
    }

    @Override
    public ApplicationUser resetUserPassword(Long userId, String newPassword) {
        //
        //  Update the user record with the new password
        ApplicationUser applicationUser = applicationUserRepository.findOne(userId);
        applicationUser.setPasswordHash(passwordEncoder.encode(newPassword));
        applicationUser.setDateUpdated(new Date());
        applicationUser.setUserUpdated(userId);
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public ApplicationUser createUserAccountViaEmailAddress(String emailAddress) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(emailAddress);
        applicationUser.setPasswordHash(passwordEncoder.encode(DEFAULT_PASSWORD));
        applicationUser.setEmailAddress(emailAddress);
        applicationUser.setDateCreated(new Date());
        applicationUser.setUserCreated(Constants.ApplicationUser.ADMINISTRATIVE_USER_ID);
        applicationUser.setDateUpdated(new Date());
        applicationUser.setUserUpdated(Constants.ApplicationUser.ADMINISTRATIVE_USER_ID);
        return applicationUserRepository.save(applicationUser);
    }

}
