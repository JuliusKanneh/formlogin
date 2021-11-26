package com.wisdom.formlogin.security;

import com.wisdom.formlogin.models.Attempts;
import com.wisdom.formlogin.models.User;
import com.wisdom.formlogin.repositories.AttemptsRepository;
import com.wisdom.formlogin.repositories.UserRepository;
import com.wisdom.formlogin.security.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {
    private static final int ATTEMPTS_LIMIT = 3;

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AttemptsRepository attemptsRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserDetails userDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        @Component
        class AuthProvider1 implements AuthenticationProvider { //use my description here
            private static final int ATTEMPTS_LIMIT = 3;

            @Autowired
            private SecurityUserDetailsService userDetailsService;

            @Autowired
            private PasswordEncoder passwordEncoder;

            @Autowired
            private AttemptsRepository attemptsRepository;

            @Autowired
            private UserRepository userRepository;

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException{
                String username = authentication.getName();
                Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
                if (userAttempts.isPresent()){
                    Attempts attempts = userAttempts.get();
                    attempts.setAttempts(0);
                    attemptsRepository.save(attempts);
                }
//                I could return object of authentication {analyze}
                return null; //added on my own
            }


//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return null;
//            }

            private void processFailedAttempts(String username, User user){
                Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
                if (userAttempts.isEmpty()){
                    Attempts attempts = new Attempts();
                    attempts.setUsername(username);
                    attempts.setAttempts(1);
                    attemptsRepository.save(attempts);
                }else {
                    Attempts attempts = userAttempts.get();
                    attempts.setAttempts(attempts.getAttempts() + 1); //incrementing attempts
                    attemptsRepository.save(attempts);

                    if (attempts.getAttempts() + 1 > ATTEMPTS_LIMIT){
                        user.setAccountNonLocked(false);
                        userRepository.save(user);
                        throw  new LockedException("To many invalid attempts. Account is locked!!");
                    }
                }
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        }
        return authentication; //added on my own
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
