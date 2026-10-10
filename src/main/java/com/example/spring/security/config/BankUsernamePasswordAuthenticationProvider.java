package com.example.spring.security.config;

import com.example.spring.security.model.User;
import com.example.spring.security.repository.UserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component // tell to container this is Bean
public class BankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        List<User> users = userRepo.findByEmail(username);
        if(users.size() > 0){
            System.out.println("Login email: " + username);
            System.out.println("User found: " + !users.isEmpty());
            if (passwordEncoder.matches(pwd,users.get(0).getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add((new SimpleGrantedAuthority(users.get(0).getRole())));
                return new UsernamePasswordAuthenticationToken(username,pwd,authorities);

            }else {
                throw new BadCredentialsException("Invalid Password");
            }

        }else {
            throw new BadCredentialsException("No user Registered with this details");
        }
    }

    // username, password auth token
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
