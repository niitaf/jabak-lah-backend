package org.spring.jabaklah.controller;


import lombok.extern.slf4j.Slf4j;
import org.spring.jabaklah.entity.*;
import org.spring.jabaklah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody @Valid JwtRequest jwtRequest) throws Exception {
        log.info("User " + jwtRequest.getUsername() + " has authenticate");
        System.out.println(jwtRequest);
        return userService.createJwtToken(jwtRequest);
    }


    @PutMapping("/resetpassword/{username}")
    public ResponseEntity<String> resetPassword(@PathVariable("username") String username, @Valid @RequestBody User user) {
        try {
            System.out.printf(user.getUserPassword() + "hhhhhhhhh");
            userService.resetPassword(user.getUserPassword(), username);
            return ResponseEntity.ok().body("password reset");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("cannot modify passwor ");
        }
    }

    @PutMapping("/changePassword/{username}")
    //@PreAuthorize("hasAnyRole('Agent','Client')")
    public ResponseEntity<String> ChangePassword(@PathVariable("username") String username, @Valid @RequestBody Password password) {
        try {
            System.out.println(password.getPassword() + "  " + password.getConfirmPassword() + "  " + username);

            userService.ChangePassword(password.getPassword(), password.getConfirmPassword(), username);
            return ResponseEntity.ok().body("Password changed");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }


}
