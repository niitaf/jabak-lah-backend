package org.spring.jabaklah.service;

import org.spring.jabaklah.entity.JwtRequest;
import org.spring.jabaklah.entity.JwtResponse;
import org.spring.jabaklah.entity.User;

import org.spring.jabaklah.repository.AgentRepository;

import org.spring.jabaklah.repository.ClientRepository;
import org.spring.jabaklah.repository.UserRepository;
import org.spring.jabaklah.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Transactional
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientRepository clientRepository;


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUsername();
        System.out.println("username="+username);
        String userPassword = jwtRequest.getUserPassword();
        System.out.println("userPassword="+userPassword);
        authenticate(username, userPassword);

        UserDetails userDetails = loadUserByUsername(username);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findById(username).get();
        System.out.println("User user = userRepository.findById(username).get()="+user);

        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with Username: " + username);
        }
    }

    private Collection<GrantedAuthority> getAuthority(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleName()));
        return authorities;
    }

    private void authenticate(String userPhone, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userPhone, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    public void resetPassword(String userPassword, String username) {
        User user = this.userRepository.findUserByUsername(username);
        user.setUserPassword(passwordEncoder.encode(userPassword));
        if (user.getRoleName().equals("Agent")) {
            this.agentRepository.UpdateFirstConnectionAgentByid(username);


        } else if (user.getRoleName().equals("Client")) {
            this.clientRepository.UpdateFirstConnectionAgentByid(username);
        }
        this.userRepository.save(user);
    }


    public void ChangePassword(String Password, String ConfirmPassword, String username) throws Exception {
        if (Password == "" || ConfirmPassword == "" || username == "") {
            throw new RuntimeException("u should field all inputs");
        } else {
            User user = this.userRepository.findUserByUsername(username);
            System.out.println(Password + "nhnnnn");
            System.out.println(user.getUserPassword());
            System.out.println(passwordEncoder.encode(Password));
           /* if ((user.getUserPassword()).equals(passwordEncoder.encode("1"))) {
                user.setUserPassword(ConfirmPassword);
                this.userRepository.save(user);
            }
            else{throw  new RuntimeException("Password incorrect");}

            */
        }

    }

}
