package pl.hackyeah.bumboxqueue.security;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.entity.UserEntity;
import pl.hackyeah.bumboxqueue.error.NotFoundException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByUsername(username)
        .orElseThrow(
            () -> new NotFoundException(String.format("User with username=%s not found", username),
                ServiceErrorCode.USER_NOT_FOUND));

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
    return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }
}