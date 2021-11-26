package com.wisdom.formlogin.security;

import com.wisdom.formlogin.models.MyUserDetails;
import com.wisdom.formlogin.models.User;
import com.wisdom.formlogin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
////        User user = userRepository.findUserByUsername(username).orElseThrow(() -< new UsernameNotFoundException("User not present"));
//        User user = userRepository.findUserByUsername(username).orElseThrow();
//        return user;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User not found" +user));
//        User user = userRepository.findUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not present")); //initial sign -<

//        return userRepository.findUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
//        return user;
        return user.map(MyUserDetails::new).get();
    }


    public void createUser(User user){
        userRepository.save(user);
    }
}
