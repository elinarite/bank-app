package com.example.bankApp.controller;

import com.example.bankApp.entity.AuthRequest;
import com.example.bankApp.entity.UserInfo;
import com.example.bankApp.entity.UserInfoDetails;
import com.example.bankApp.entity.UserInfoService;
import com.example.bankApp.entity.enums.Roles;
import com.example.bankApp.sequiringweb.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/auth")
    public class UserController {

    @Autowired
//    @Qualifier("userInfoService")
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('user')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('admin')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

//    @PostMapping("/generateToken")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getPassword(), authRequest.getPassword()));
//        if (authentication.isAuthenticated()) {
//
//            //todo getUserName
//            return jwtService.generateToken(authRequest.getEmail());
//        } else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//    }
//}
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
            List<SimpleGrantedAuthority> authorities = authRequest.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword(), authorities)
            );

            if (authentication.isAuthenticated()) {
                // todo getUserName
                return jwtService.generateToken(authRequest.getEmail());
            } else {
                throw new UsernameNotFoundException("Invalid user request!");
            }
        }

    }



//    @GetMapping
//    public Roles auth() {
//        return userInfoService.getAuthorizedUserRole();
//    }
//    }


