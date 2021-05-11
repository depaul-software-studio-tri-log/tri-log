package com.depaul.trilog.authentication;

import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DatabaseUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (!userOptional.isPresent()) {
      throw new UsernameNotFoundException("User " + username + " not found");
    }
    User user = userOptional.get();
    return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(user.getPassword())
            .roles(new String[] {"USER"})
            .build();
  }
}
