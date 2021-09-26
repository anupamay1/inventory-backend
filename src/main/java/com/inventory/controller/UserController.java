package com.inventory.controller;

import com.inventory.entity.User;
import com.inventory.service.UserService;
import com.inventory.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody User user) throws Exception{
      try{
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
      );
      }
      catch (Exception ex){
          throw new Exception("Invalid Username/password");
      }
      return jwtUtil.generateToken(user.getUsername());
    }
}
