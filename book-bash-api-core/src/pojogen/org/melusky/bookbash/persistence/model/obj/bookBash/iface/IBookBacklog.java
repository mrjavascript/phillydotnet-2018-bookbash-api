package org.melusky.bookbash.persistence.model.obj.bookBash.iface;
import java.util.Date;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser;
import org.melusky.bookbash.persistence.model.obj.bookBash.BacklogStatus;
import org.melusky.bookbash.persistence.model.obj.bookBash.Book;
import org.melusky.bookbash.persistence.util.IPojoGenEntity;


/** 
 * Object interface mapping for hibernate-handled table: book_backlog.
 * @author autogenerated
 */

public interface IBookBacklog {



    /**
     * Return the value associated with the column: book.
	 * @return A Book object (this.book)
	 */
	Book getBook();
	

  
    /**  
     * Set the value related to the column: book.
	 * @param book the book value you wish to set
	 */
	void setBook(final Book book);

    /**
     * Return the value associated with the column: dateCreated.
	 * @return A Date object (this.dateCreated)
	 */
	Date getDateCreated();
	

  
    /**  
     * Set the value related to the column: dateCreated.
	 * @param dateCreated the dateCreated value you wish to set
	 */
	void setDateCreated(final Date dateCreated);

    /**
     * Return the value associated with the column: dateDisabled.
	 * @return A Date object (this.dateDisabled)
	 */
	Date getDateDisabled();
	

  
    /**  
     * Set the value related to the column: dateDisabled.
	 * @param dateDisabled the dateDisabled value you wish to set
	 */
	void setDateDisabled(final Date dateDisabled);

    /**
     * Return the value associated with the column: dateUpdated.
	 * @return A Date object (this.dateUpdated)
	 */
	Date getDateUpdated();
	

  
    /**  
     * Set the value related to the column: dateUpdated.
	 * @param dateUpdated the dateUpdated value you wish to set
	 */
	void setDateUpdated(final Date dateUpdated);

    /**
     * Return the value associated with the column: id.
	 * @return A Long object (this.id)
	 */
	Long getId();
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	void setId(final Long id);

    /**
     * Return the value associated with the column: rating.
	 * @return A java.math.BigDecimal object (this.rating)
	 */
	java.math.BigDecimal getRating();
	

  
    /**  
     * Set the value related to the column: rating.
	 * @param rating the rating value you wish to set
	 */
	void setRating(final java.math.BigDecimal rating);

    /**
     * Return the value associated with the column: status.
	 * @return A BacklogStatus object (this.status)
	 */
	BacklogStatus getStatus();
	

  
    /**  
     * Set the value related to the column: status.
	 * @param status the status value you wish to set
	 */
	void setStatus(final BacklogStatus status);

    /**
     * Return the value associated with the column: user.
	 * @return A ApplicationUser object (this.user)
	 */
	ApplicationUser getUser();
	

  
    /**  
     * Set the value related to the column: user.
	 * @param user the user value you wish to set
	 */
	void setUser(final ApplicationUser user);

    /**
     * Return the value associated with the column: userCreated.
	 * @return A Long object (this.userCreated)
	 */
	Long getUserCreated();
	

  
    /**  
     * Set the value related to the column: userCreated.
	 * @param userCreated the userCreated value you wish to set
	 */
	void setUserCreated(final Long userCreated);

    /**
     * Return the value associated with the column: userUpdated.
	 * @return A Long object (this.userUpdated)
	 */
	Long getUserUpdated();
	

  
    /**  
     * Set the value related to the column: userUpdated.
	 * @param userUpdated the userUpdated value you wish to set
	 */
	void setUserUpdated(final Long userUpdated);

	// end of interface
}