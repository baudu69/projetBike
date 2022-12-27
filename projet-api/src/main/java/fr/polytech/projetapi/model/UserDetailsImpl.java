package fr.polytech.projetapi.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
	private final String username;
	private final String password;
	private int numUtil;


	public UserDetailsImpl(Utilisateur utilisateur) {
		this.username = utilisateur.getLogin();
		this.password = utilisateur.getPassword();
		this.numUtil = utilisateur.getId();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public int getNumUtil() {
		return numUtil;
	}
}
