package com.inventory.serviceImpl;

import com.inventory.entity.User;
import com.inventory.repository.UserRepository;
import com.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public User addUser(User user) throws Exception{
        User existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser==null){
            return userRepository.save(user);
        }
        else{
            throw new Exception("User already Exists!!");
        }
    }
}
