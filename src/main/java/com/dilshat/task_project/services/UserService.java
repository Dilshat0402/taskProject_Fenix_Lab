package com.dilshat.task_project.services;

import com.dilshat.task_project.dtos.RegistrationUserDTO;
import com.dilshat.task_project.exceptions.UserAlreadyExistException;
import com.dilshat.task_project.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    public User registerNewUserAccount(RegistrationUserDTO userDto) throws UserAlreadyExistException;

    User getUserById(Long id);

    List<User> getAllUsers();
}
