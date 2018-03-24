package org.melusky.bookbash.persistence.model.obj.bookBash.repository;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUserSecurity;

/** 
 * Spring Data Repository for table: application_user_security.
 * @author autogenerated/custom
 */ 
public interface ApplicationUserSecurityRepository extends JpaRepository<ApplicationUserSecurity, Long>, QueryDslPredicateExecutor<ApplicationUserSecurity> {

	// Add any extra methods here. This file will not get overwritten unlike any other generated file
}