package pl.hackyeah.bumboxqueue.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.bumboxqueue.dto.input.PatientInputDto;
import pl.hackyeah.bumboxqueue.security.SecurityService;
import pl.hackyeah.bumboxqueue.service.UserService;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/api/users")
public class UserEndpoint {

  private final UserService userService;
  private final SecurityService securityService;

  UserEndpoint(UserService userService,
      SecurityService securityService) {
    this.userService = userService;
    this.securityService = securityService;
  }

  @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> registration(HttpServletRequest request,
      @RequestParam("username") String username, @RequestParam("password") String password,
      @RequestBody PatientInputDto patientInputDto) {
    String sessionId = request.getSession().getId();
    log.debug("Received POST registration, sessionId={}", username, sessionId);
    userService.saveUser(username, password, patientInputDto);
    securityService.autologin(username, password);
    log.info("Registration successful, username={}, sessionId={}", username, sessionId);
    return new ResponseEntity<>(sessionId, HttpStatus.CREATED);
  }

  @PostMapping(value = "/login")
  public ResponseEntity<String> login(HttpServletRequest request,
      @RequestParam("username") String username, @RequestParam("password") String password) {
    String sessionId = request.getSession().getId();
    log.debug("Received POST login, username={}, sessionId={}", username, sessionId);
    securityService.autologin(username, password);
    log.info("Login successful, username={}, sessionId={}", username, sessionId);
    return new ResponseEntity<>(sessionId, HttpStatus.OK);
  }

  @PreAuthorize("isFullyAuthenticated()")
  @PostMapping(value = "/logout")
  public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
    String sessionId = request.getSession().getId();
    log.debug("Received POST logout, username={}, sessionId={}", request.getRemoteUser(),
        sessionId);
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
      UserDetails userDetails = (UserDetails) auth.getPrincipal();
      log.info("Logout successful, username={}", userDetails.getUsername());
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

}