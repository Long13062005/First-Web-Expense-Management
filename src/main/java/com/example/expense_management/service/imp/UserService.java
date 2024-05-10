package com.example.expense_management.service.imp;

import com.example.expense_management.config.LoginMessage;
import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.exception.RecordNotFoundException;
import com.example.expense_management.model.Role;
import com.example.expense_management.model.User;
import com.example.expense_management.repository.IRoleRepository;
import com.example.expense_management.repository.IUserRepository;
import com.example.expense_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<User> findUserByUsernameContaining(String username, Pageable pageable){
        return iUserRepository.findUserByUsernameContaining(username,pageable);
    }

    @Override
    public void delete(int id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return iUserRepository.findUserByUsername(username);
    }

    @Override
    public User findById(int id) {
        return iUserRepository.findById(id);
    }

    @Override
    public LoginMessage login(LoginDTO loginDTO) {
        User user1 = iUserRepository.findUserByUsername(loginDTO.getUsername());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = iUserRepository.findUserByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage(" Invalid username and password ", false);
        }
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return iUserRepository.findUserByUsernameAndPassword(username,password);
    }

    //register addUser default 2 = user,1 = admin
    @Override
    public boolean save(User user) throws RecordNotFoundException {
        User udto = iUserRepository.findUserByUsername(user.getUsername());

        if(udto == null) {
            User userDto = new User(
                    user.getId(),
                    user.getUsername(),
                    this.passwordEncoder.encode(user.getPassword())
            );
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            userDto.setRole(roles);
            userDto.setRoleName("User");
            iUserRepository.save(userDto);
            return true;
        }else {
            return false;
        }
    }


}
