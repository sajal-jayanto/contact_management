package com.cramstack.contact_management.Services;

import com.cramstack.contact_management.Models.User;
import com.cramstack.contact_management.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Collections;

@Service
@Qualifier("userDetails")
public class LoginService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(s);

        if(user == null) throw new UsernameNotFoundException("This user is not there");

        return new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(new SimpleGrantedAuthority("USER"));
            }

            @Override
            public String getPassword() {
                final String password = user.getPassword();
                return password;

            }

            @Override
            public String getUsername() {
                final String username = user.getUsername();
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
        };
    }
}
