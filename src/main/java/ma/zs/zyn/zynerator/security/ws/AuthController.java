package ma.zs.zyn.zynerator.security.ws;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import ma.zs.zyn.zynerator.security.bean.EmailRequest;
import ma.zs.zyn.zynerator.security.bean.Role;
import ma.zs.zyn.zynerator.security.bean.RoleUser;
import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.common.SecurityParams;
import ma.zs.zyn.zynerator.security.dao.facade.core.RoleDao;
import ma.zs.zyn.zynerator.security.dao.facade.core.UserDao;
import ma.zs.zyn.zynerator.security.jwt.JwtUtils;
import ma.zs.zyn.zynerator.security.payload.request.ChangePasswordRequest;
import ma.zs.zyn.zynerator.security.payload.request.ForgetPasswordRequest;
import ma.zs.zyn.zynerator.security.payload.request.LoginRequest;
import ma.zs.zyn.zynerator.security.payload.response.JwtResponse;
import ma.zs.zyn.zynerator.security.service.facade.RoleService;
import ma.zs.zyn.zynerator.security.service.facade.RoleUserService;
import ma.zs.zyn.zynerator.security.service.facade.UserService;
import ma.zs.zyn.zynerator.security.service.impl.EmailService;
import ma.zs.zyn.zynerator.security.service.impl.UserServiceImpl;
import ma.zs.zyn.zynerator.security.ws.converter.RoleUserConverter;
import ma.zs.zyn.zynerator.security.ws.converter.UserConverter;
import ma.zs.zyn.zynerator.security.ws.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthSpinnerUI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;




//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserDao userRepository;

  @Autowired
  RoleDao roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  EmailService emailService;

  @Autowired
  UserService userService;
  @Autowired
  UserServiceImpl userServiceimpl;

  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  RoleUserService roleUserService;
  @Autowired
  RoleService roleService;
  @Autowired
  RoleUserConverter roleUserConverter;
  @Autowired
  UserConverter userConverter;


  @PostMapping("login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    User user1 = userService.findByUsername(loginRequest.getUsername());
    if (!user1.getEnabled()){
      return ResponseEntity
              .badRequest()
              .body(Collections.singletonMap("error", "you have to active your account from link that had been sending to your gmail ,the link is unvalid if he passed one hour after benn sending "));
    }
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    User userDetails = (User) authentication.getPrincipal();
    /*List<String> roles = userDetails.getRoleUsers().stream()
            .map(item -> item.getRole().getAuthority())
            .collect(Collectors.toList());*/
    List<String> roles = userDetails.getRoleUsers().stream()
            .filter(item -> item.getRole() != null) // Add a filter to exclude null roles
            .map(item -> item.getRole().getAuthority())
            .filter(Objects::nonNull) // Filter out null authorities
            .collect(Collectors.toList());
    HttpHeaders headers = new HttpHeaders();
    headers.add(SecurityParams.JWT_HEADER_NAME,SecurityParams.HEADER_PREFIX+jwt);

    return ResponseEntity.ok()
            .headers(headers)
            .body(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles)); }


/*
  @PostMapping("register")
  public ResponseEntity<Map<String, String>> register(@RequestBody UserDto userDto){
    if(userRepository.findByUsername(userDto.getUsername())!=null){
      return ResponseEntity
              .badRequest()
              .body(Collections.singletonMap("error", "This username has already been taken"));
    }
    User user = new User();
    user.setUsername(userDto.getUsername());
    user.setEmail(userDto.getEmail());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPhone(userDto.getPhone());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setEnabled(false);
    LocalDateTime expirationDate = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
    user.setExpirationLinkDate(expirationDate);

    /*
     RoleUser roleUser = new RoleUser();
     Role roles =  roleRepository.findByLabel("Admin");
     if (roles == null) {
         return new ResponseEntity<>("role not found", HttpStatus.BAD_REQUEST);
     }
     roleUser.setRole(roles);
     roleUser.setUser(user);
     user.setRoleUsers(Collections.singletonList(roleUser));

    user.setCode(user.getUsername()+userService.generateCode(8));
    EmailRequest emailRequest = new EmailRequest();
    emailRequest.setFrom("najibilham841@gmail.com");
    emailRequest.setBcc(user.getEmail());
    emailRequest.setCc(user.getEmail());
    emailRequest.setTo(user.getEmail());
    emailRequest.setSubject("Verify your email");
    emailRequest.setBody("Link to verify your email and it will expired by 24h from now : http://localhost:8036/verify?code=" + user.getCode());
    userRepository.save(user);
    emailService.sendSimpleMessage(emailRequest);

    Map<String, String> response = new HashMap<>();
    response.put("message", "you have registered successfully");
    return ResponseEntity.ok(response);}



*/


  @PostMapping("register")
  public ResponseEntity<Map<String, String>> register(@RequestBody UserDto userDto){
    if(userRepository.findByUsername(userDto.getUsername()) != null){
      return ResponseEntity
              .badRequest()
              .body(Collections.singletonMap("error", "This username has already been taken"));
    }


    LocalDateTime expirationDate = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
    userDto.setEnabled(false);
    userDto.setExpirationLinkDate(expirationDate);
    userDto.setCode(userDto.getUsername() + userService.generateCode(8));
    EmailRequest emailRequest = new EmailRequest();
    emailRequest.setFrom("najibilham841@gmail.com");
    emailRequest.setBcc(userDto.getEmail());
    emailRequest.setCc(userDto.getEmail());
    emailRequest.setTo(userDto.getEmail());
    emailRequest.setSubject("Verify your email");
    emailRequest.setBody("Link to verify your email and it will expire in 24 hours: http://localhost:8036/verify?code=" + userDto.getCode());
    User user = userConverter.toItem(userDto);
    userServiceimpl.create(user);
    emailService.sendSimpleMessage(emailRequest);

    Map<String, String> response = new HashMap<>();
    response.put("message", "You have registered successfully");
    return ResponseEntity.ok(response);
  }






  @PutMapping("forgetPassword")
  public ResponseEntity<Map<String, String>> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
    User user = userRepository.findByEmail(forgetPasswordRequest.getEmail());
    user.setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
    userService.update(user);
    if(user!=null ){

      EmailRequest emailRequest = new EmailRequest();
      emailRequest.setFrom("najibilham841@gmail.com");
      emailRequest.setBcc(user.getEmail());
      emailRequest.setCc(user.getEmail());
      emailRequest.setTo(user.getEmail());
      emailRequest.setSubject("Verify your email");
      emailRequest.setBody("Link  to change your password : http://localhost:8036/changePassword?code=" + user.getCode());
      emailService.sendSimpleMessage(emailRequest);
      Map<String, String> response = new HashMap<>();
      response.put("message", "check your email to change your password");
      return ResponseEntity.ok(response);
    }
    return ResponseEntity
            .badRequest()
            .body(Collections.singletonMap("error", "Your email  is uncorrect"));}


  @GetMapping("changePassword")
  public ResponseEntity<?>  ChangePassword(@RequestParam("code") String code, HttpServletResponse response){
    User user = userService.findByCode(code);
    if (user != null) {
      String activationLink = "http://localhost:4200/";
      String htmlResponse = "<html><body><a href=\"" + activationLink + "\" style=\"font-size: 30px;\">Click here to return to application </a></body></html>";
      return ResponseEntity.ok(htmlResponse);
    }
    return ResponseEntity.badRequest().body("Invalid verification code");
  }




  // Dans votre méthode verifyUser
  @GetMapping("verify")
  public ResponseEntity<?> verifyUser(@RequestParam("code") String code, HttpServletResponse response) {
    User user = userService.findByCode(code);
    if (user != null) {
      if (isActivationLinkValid(user)) { // Vérifier la validité du lien
        user.setEnabled(true);
        userService.update(user);

        String activationLink = "http://localhost:4200/activateAccount";
        String htmlResponse = "<html><body><a href=\"" + activationLink + "\" style=\"font-size: 30px;\">Cliquer ici pour activer votre compte</a></body></html>";
        return ResponseEntity.ok(htmlResponse);
      } else {
        return ResponseEntity.badRequest().body("Le lien d'activation du compte a expiré.");
      }
    }
    return ResponseEntity.badRequest().body("Code de vérification invalide.");
  }

  private boolean isActivationLinkValid(User user) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime expiry = user.getExpirationLinkDate();
    return expiry != null && now.isBefore(expiry);
  }


/*
  @GetMapping("verify")
  public ResponseEntity<?> verifyUser(@RequestParam("code") String code, HttpServletResponse response) {
    User user = userService.findByCode(code);
    if (user != null) {
      user.setEnabled(true);
      userService.update(user);


      String activationLink = "http://localhost:4200/activateAccount";
      String htmlResponse = "<html><body><a href=\"" + activationLink + "\" style=\"font-size: 30px;\">Cliquer ici pour activer votre compte</a></body></html>";
      return ResponseEntity.ok(htmlResponse);

    }
    return ResponseEntity.badRequest().body("Invalid verification code");
  }



*/





















}


