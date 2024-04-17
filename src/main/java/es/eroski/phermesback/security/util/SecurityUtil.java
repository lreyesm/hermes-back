package es.eroski.phermesback.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Auditable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.eroski.oinarri.library.ldaplogin.lib.user.domain.User;
import es.eroski.oinarri.library.ldaplogin.lib.user.domain.UserRepository;
import es.eroski.phermesback.model.embedded.AuditableEntity;

@Service
public class SecurityUtil {
	
	@Autowired
	private UserRepository userRepository;
	
	public AuditableEntity generateAuditable() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsernameIgnoreCase(authentication.getName()).get();  
		AuditableEntity auditable = new AuditableEntity(user);
		
		return auditable;
	}
	
	public User getUserAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsernameIgnoreCase(authentication.getName()).get();  
	
		return user;
	}

}
