package org.melusky.bookbash.persistence.model.obj.bookBash;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Parameter;
import org.hibernate.proxy.HibernateProxy;
import org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUserSecurity;
import org.melusky.bookbash.persistence.model.obj.bookBash.BookBacklog;
import org.melusky.bookbash.persistence.model.obj.bookBash.iface.IApplicationUser;
import org.melusky.bookbash.persistence.util.IPojoGenEntity;

/**
 * Object mapping for hibernate-handled table: application_user.
 * 
 *
 * @author autogenerated
 */
		

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;

@Entity
@Table(name = "application_user", schema = "book_bash")
@Cache(region = "org.melusky.bookbash.persistence.model.obj.bookBash.ApplicationUser", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationUser implements Cloneable, Serializable, IPojoGenEntity, IApplicationUser {

	/** Serial Version UID. */
	private static final long serialVersionUID = 5712276346750826725L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Long> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<>());
	
	/** hashCode temporary storage. */
	private volatile Long hashCode;
	

	/** Field mapping. */
	private Set<ApplicationUserSecurity> applicationUserSecurities = new HashSet<>();

	/** Field mapping. */
	private Set<BookBacklog> bookBacklogs = new HashSet<>();

	/** Field mapping. */
	private Date dateCreated;
	/** Field mapping. */
	private Date dateDisabled;
	/** Field mapping. */
	private Date dateEmailConfirmed;
	/** Field mapping. */
	private Date dateUpdated;
	/** Field mapping. */
	private String emailAddress;
	/** Field mapping. */
	private Long id;
	/** Field mapping. */
	private String passwordHash;
	/** Field mapping. */
	private String username;
	/** Field mapping. */
	private Long userCreated;
	/** Field mapping. */
	private Long userUpdated;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public ApplicationUser() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public ApplicationUser(Long id) {
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return ApplicationUser.class;
	}


	 /**
	 * Return the value associated with the column: applicationUserSecurity.
	 * @return A Set&lt;ApplicationUserSecurity&gt; object (this.applicationUserSecurity)
	 */
	@JsonManagedReference @OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "user"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( name = "user_id", nullable = false  )
	public Set<ApplicationUserSecurity> getApplicationUserSecurities() {
		return this.applicationUserSecurities;
		
	}
	
	/**
	 * Adds a bi-directional link of type ApplicationUserSecurity to the applicationUserSecurities set.
	 * @param applicationUserSecurity item to add
	 */
	public void addApplicationUserSecurity(ApplicationUserSecurity applicationUserSecurity) {
		applicationUserSecurity.setUser(this);
		this.applicationUserSecurities.add(applicationUserSecurity);
	}

  
	 /**  
	 * Set the value related to the column: applicationUserSecurity.
	 * @param applicationUserSecurity the applicationUserSecurity value you wish to set
	 */
	public void setApplicationUserSecurities(final Set<ApplicationUserSecurity> applicationUserSecurity) {
		this.applicationUserSecurities = applicationUserSecurity;
	}

	 /**
	 * Return the value associated with the column: bookBacklog.
	 * @return A Set&lt;BookBacklog&gt; object (this.bookBacklog)
	 */
	@JsonManagedReference @OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "user"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( name = "user_id", nullable = false  )
	public Set<BookBacklog> getBookBacklogs() {
		return this.bookBacklogs;
		
	}
	
	/**
	 * Adds a bi-directional link of type BookBacklog to the bookBacklogs set.
	 * @param bookBacklog item to add
	 */
	public void addBookBacklog(BookBacklog bookBacklog) {
		bookBacklog.setUser(this);
		this.bookBacklogs.add(bookBacklog);
	}

  
	 /**  
	 * Set the value related to the column: bookBacklog.
	 * @param bookBacklog the bookBacklog value you wish to set
	 */
	public void setBookBacklogs(final Set<BookBacklog> bookBacklog) {
		this.bookBacklogs = bookBacklog;
	}

	 /**
	 * Return the value associated with the column: dateCreated.
	 * @return A Date object (this.dateCreated)
	 */
	@Basic( optional = true )
	@Column( name = "date_created"  )
	public Date getDateCreated() {
		return this.dateCreated;
		
	}
	

  
	 /**  
	 * Set the value related to the column: dateCreated.
	 * @param dateCreated the dateCreated value you wish to set
	 */
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	 /**
	 * Return the value associated with the column: dateDisabled.
	 * @return A Date object (this.dateDisabled)
	 */
	@Basic( optional = true )
	@Column( name = "date_disabled"  )
	public Date getDateDisabled() {
		return this.dateDisabled;
		
	}
	

  
	 /**  
	 * Set the value related to the column: dateDisabled.
	 * @param dateDisabled the dateDisabled value you wish to set
	 */
	public void setDateDisabled(final Date dateDisabled) {
		this.dateDisabled = dateDisabled;
	}

	 /**
	 * Return the value associated with the column: dateEmailConfirmed.
	 * @return A Date object (this.dateEmailConfirmed)
	 */
	@Basic( optional = true )
	@Column( name = "date_email_confirmed"  )
	public Date getDateEmailConfirmed() {
		return this.dateEmailConfirmed;
		
	}
	

  
	 /**  
	 * Set the value related to the column: dateEmailConfirmed.
	 * @param dateEmailConfirmed the dateEmailConfirmed value you wish to set
	 */
	public void setDateEmailConfirmed(final Date dateEmailConfirmed) {
		this.dateEmailConfirmed = dateEmailConfirmed;
	}

	 /**
	 * Return the value associated with the column: dateUpdated.
	 * @return A Date object (this.dateUpdated)
	 */
	@Basic( optional = true )
	@Column( name = "date_updated"  )
	public Date getDateUpdated() {
		return this.dateUpdated;
		
	}
	

  
	 /**  
	 * Set the value related to the column: dateUpdated.
	 * @param dateUpdated the dateUpdated value you wish to set
	 */
	public void setDateUpdated(final Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	 /**
	 * Return the value associated with the column: emailAddress.
	 * @return A String object (this.emailAddress)
	 */
	@Basic( optional = true )
	@Column( name = "email_address", length = 2147483647  )
	public String getEmailAddress() {
		return this.emailAddress;
		
	}
	

  
	 /**  
	 * Set the value related to the column: emailAddress.
	 * @param emailAddress the emailAddress value you wish to set
	 */
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	 /**
	 * Return the value associated with the column: id.
	 * @return A Long object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicationUserUser_idGenerator")
	@Basic( optional = false )
	@Column( name = "user_id", nullable = false  )
	@SequenceGenerator(allocationSize = 1, name = "applicationUserUser_idGenerator", sequenceName = "book_bash.application_user_id_seq", schema = "book_bash", catalog = "book_bash")
	public Long getId() {
		return this.id;
		
	}
	

  
	 /**  
	 * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final Long id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null || this.id == 0L) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}

	 /**
	 * Return the value associated with the column: passwordHash.
	 * @return A String object (this.passwordHash)
	 */
	@Basic( optional = true )
	@Column( name = "password_hash", length = 2147483647  )
	public String getPasswordHash() {
		return this.passwordHash;
		
	}
	

  
	 /**  
	 * Set the value related to the column: passwordHash.
	 * @param passwordHash the passwordHash value you wish to set
	 */
	public void setPasswordHash(final String passwordHash) {
		this.passwordHash = passwordHash;
	}

	 /**
	 * Return the value associated with the column: username.
	 * @return A String object (this.username)
	 */
	@Basic( optional = true )
	@Column( length = 2147483647  )
	public String getUsername() {
		return this.username;
		
	}
	

  
	 /**  
	 * Set the value related to the column: username.
	 * @param username the username value you wish to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	 /**
	 * Return the value associated with the column: userCreated.
	 * @return A Long object (this.userCreated)
	 */
	@Basic( optional = true )
	@Column( name = "user_created"  )
	public Long getUserCreated() {
		return this.userCreated;
		
	}
	

  
	 /**  
	 * Set the value related to the column: userCreated.
	 * @param userCreated the userCreated value you wish to set
	 */
	public void setUserCreated(final Long userCreated) {
		this.userCreated = userCreated;
	}

	 /**
	 * Return the value associated with the column: userUpdated.
	 * @return A Long object (this.userUpdated)
	 */
	@Basic( optional = true )
	@Column( name = "user_updated"  )
	public Long getUserUpdated() {
		return this.userUpdated;
		
	}
	

  
	 /**  
	 * Set the value related to the column: userUpdated.
	 * @param userUpdated the userUpdated value you wish to set
	 */
	public void setUserUpdated(final Long userUpdated) {
		this.userUpdated = userUpdated;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public ApplicationUser clone() throws CloneNotSupportedException {
		
        final ApplicationUser copy = (ApplicationUser)super.clone();

		if (this.getApplicationUserSecurities() != null) {
			copy.getApplicationUserSecurities().addAll(this.getApplicationUserSecurities());
		}
		if (this.getBookBacklogs() != null) {
			copy.getBookBacklogs().addAll(this.getBookBacklogs());
		}
 		copy.setDateCreated(this.getDateCreated());
 		copy.setDateDisabled(this.getDateDisabled());
 		copy.setDateEmailConfirmed(this.getDateEmailConfirmed());
 		copy.setDateUpdated(this.getDateUpdated());
 		copy.setEmailAddress(this.getEmailAddress());
 		copy.setId(this.getId());
 		copy.setPasswordHash(this.getPasswordHash());
 		copy.setUsername(this.getUsername());
 		copy.setUserCreated(this.getUserCreated());
 		copy.setUserUpdated(this.getUserUpdated());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("dateCreated: " + this.getDateCreated() + ", ");
		sb.append("dateDisabled: " + this.getDateDisabled() + ", ");
		sb.append("dateEmailConfirmed: " + this.getDateEmailConfirmed() + ", ");
		sb.append("dateUpdated: " + this.getDateUpdated() + ", ");
		sb.append("emailAddress: " + this.getEmailAddress() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("passwordHash: " + this.getPasswordHash() + ", ");
		sb.append("username: " + this.getUsername() + ", ");
		sb.append("userCreated: " + this.getUserCreated() + ", ");
		sb.append("userUpdated: " + this.getUserUpdated());
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final ApplicationUser that; 
		try {
			that = (ApplicationUser) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getDateCreated() == null) && (that.getDateCreated() == null)) || (getDateCreated() != null && getDateCreated().equals(that.getDateCreated())));
		result = result && (((getDateDisabled() == null) && (that.getDateDisabled() == null)) || (getDateDisabled() != null && getDateDisabled().equals(that.getDateDisabled())));
		result = result && (((getDateEmailConfirmed() == null) && (that.getDateEmailConfirmed() == null)) || (getDateEmailConfirmed() != null && getDateEmailConfirmed().equals(that.getDateEmailConfirmed())));
		result = result && (((getDateUpdated() == null) && (that.getDateUpdated() == null)) || (getDateUpdated() != null && getDateUpdated().equals(that.getDateUpdated())));
		result = result && (((getEmailAddress() == null) && (that.getEmailAddress() == null)) || (getEmailAddress() != null && getEmailAddress().equals(that.getEmailAddress())));
		result = result && (((getPasswordHash() == null) && (that.getPasswordHash() == null)) || (getPasswordHash() != null && getPasswordHash().equals(that.getPasswordHash())));
		result = result && (((getUsername() == null) && (that.getUsername() == null)) || (getUsername() != null && getUsername().equals(that.getUsername())));
		result = result && (((getUserCreated() == null) && (that.getUserCreated() == null)) || (getUserCreated() != null && getUserCreated().equals(that.getUserCreated())));
		result = result && (((getUserUpdated() == null) && (that.getUserUpdated() == null)) || (getUserUpdated() != null && getUserUpdated().equals(that.getUserUpdated())));
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
		if ( this.hashCode == null ) {
			synchronized ( this ) {
				if ( this.hashCode == null ) {
					Long newHashCode = null;

					if ( getId() != null ) {
					newHashCode = SAVED_HASHES.get( getId() );
					}
					
					if ( newHashCode == null ) {
						if ( getId() != null && getId() != 0L) {
							newHashCode = getId();
						} else {
							newHashCode = (long) super.hashCode();

						}
					}
					
					this.hashCode = newHashCode;
				}
			}
		}
		return (int) (this.hashCode & 0xffffff);
	}
	

	
	@PreUpdate
	public void preUpdate() {
		this.dateUpdated = new Date();
	}
}