package org.melusky.bookbash.persistence.model.obj.bookBash.repository;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.melusky.bookbash.persistence.model.obj.bookBash.BookBacklog;

/** 
 * Spring Data Repository for table: book_backlog.
 * @author autogenerated/custom
 */ 
public interface BookBacklogRepository extends JpaRepository<BookBacklog, Long>, QueryDslPredicateExecutor<BookBacklog> {

	// Add any extra methods here. This file will not get overwritten unlike any other generated file
}
