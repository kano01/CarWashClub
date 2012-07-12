package au.com.carwashclub

import org.springframework.security.core.authority.GrantedAuthorityImpl

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled

	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
    String authorities

	static constraints = {
		username blank: false, unique: true
        enabled()
        accountExpired(display: false)
        accountLocked(display: false)
        passwordExpired(display: false)
        password blanks: false, password: true


	}

	static mapping = {
		password column: '`password`'
	}

	Set<GrantedAuthorityImpl> getRoles() {
		 (authorities ?: '').split(',').collect { new GrantedAuthorityImpl(it.trim()) }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

    public String toString(){
        return getUsername();
    }
}
