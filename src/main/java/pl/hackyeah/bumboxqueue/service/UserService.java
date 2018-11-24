package pl.hackyeah.bumboxqueue.service;

import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.hackyeah.bumboxqueue.converter.Mapper;
import pl.hackyeah.bumboxqueue.dto.input.PatientInputDto;
import pl.hackyeah.bumboxqueue.dto.output.PatientOutputDto;
import pl.hackyeah.bumboxqueue.entity.UserEntity;
import pl.hackyeah.bumboxqueue.error.ConflictException;
import pl.hackyeah.bumboxqueue.error.NotFoundException;
import pl.hackyeah.bumboxqueue.error.ServiceErrorCode;
import pl.hackyeah.bumboxqueue.repository.UserRepository;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PatientService patientService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final Mapper mapper;

  public UserService(UserRepository userRepository,
      PatientService patientService,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      Mapper mapper) {
    this.userRepository = userRepository;
    this.patientService = patientService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.mapper = mapper;
  }

  @Transactional
  public void saveUser(String username, String password, PatientInputDto patientInputDto) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new ConflictException(
          String.format("Can not register user, username=%s is existed", username),
          ServiceErrorCode.USER_ALREADY_EXISTED);
    }
    PatientOutputDto patientOutputDto = patientService.savePatient(patientInputDto);
    UserEntity user = new UserEntity(username, password, patientOutputDto.getId());
    user.setPassword(bCryptPasswordEncoder.encode(password));
    userRepository.save(user);
  }

  public UserEntity findUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(
            () -> new NotFoundException(String.format("User with username=%s not found", username),
                ServiceErrorCode.USER_NOT_FOUND));
  }
}