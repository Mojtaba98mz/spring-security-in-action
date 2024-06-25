package com.example.portalsecurity.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Objects;

//start 3.3.2
public class InMemoryUserDetailsServiceImpl implements UserDetailsService {

    private final List<UserDetails> users;

    public InMemoryUserDetailsServiceImpl(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(Objects::nonNull).filter(u -> u.getUsername().equals(username))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
