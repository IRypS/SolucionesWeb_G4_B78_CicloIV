package com.soluciones.web.appGrupo4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soluciones.web.appGrupo4.model.entities.E_Rol;
import com.soluciones.web.appGrupo4.model.entities.E_User;
import com.soluciones.web.appGrupo4.repository.I_user_db;



@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
	private I_user_db userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		E_User user = userRepository.findByEmail(email);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (E_Rol role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		return new User(user.getEmail(), user.getPassword(), true, true, true, true,
				authorities);
	}
    
}
