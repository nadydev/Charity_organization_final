// package com.example.CharityOrganization.controller;
// import com.example.CharityOrganization.model.JwtToken;
// import com.example.CharityOrganization.model.Users;
// import com.example.CharityOrganization.repository.JwtTokenRepository;
// import com.example.CharityOrganization.service.User.UserService;
// import com.example.CharityOrganization.service.UserLoginGuard.UsersDetailsService;
// import com.example.CharityOrganization.utils.JWT_tokens.JwtTokenUtil;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class LoginController {

//     @Autowired
//     private UsersDetailsService userService;

//     @Autowired
//     private JwtTokenUtil jwtTokenUtil;

//     @Autowired
//     private JwtTokenRepository jwtTokenRepository;

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Users user) {
//         Users authenticatedUser = userService.login(user);
//         if (authenticatedUser != null) {
//             String username = authenticatedUser.getUsername();
//             String token = jwtTokenUtil.generateToken(username);

//             JwtToken jwtToken = new JwtToken();
//             jwtToken.setUsername(username);
//             jwtToken.setToken(token);
//             jwtTokenRepository.save(jwtToken);

//             return ResponseEntity.ok(token);
//         } else {
//             Users existingUser = userService.findUserByUsername(user.getUsername());
//             if (existingUser != null) {
//                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
//             } else {
//                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
//             }
//         }
//     }
// }

