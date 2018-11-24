package pl.hackyeah.bumboxqueue.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.entity.UserEntity;

@Slf4j
@Service
public class SecurityService {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;

  public SecurityService(
      AuthenticationManager authenticationManager,
      UserDetailsService userDetailsService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
  }

  public String findLoggedInUsername() {
    Object user = SecurityContextHolder.getContext().getAuthentication().getDetails();
    if (user instanceof UserEntity) {
      return ((UserDetails) user).getUsername();
    }
    return null;
  }

  public void autologin(String username, String password) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, password,
            userDetails.getAuthorities());

    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
  }
}