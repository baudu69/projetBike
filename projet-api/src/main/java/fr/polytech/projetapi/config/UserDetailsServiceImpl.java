package fr.polytech.projetapi.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && username.equals("userepul")) {
            return new UserDetailsImpl("userepul");
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
