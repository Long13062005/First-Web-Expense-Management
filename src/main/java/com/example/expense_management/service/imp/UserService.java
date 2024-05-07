package com.example.expense_management.service.imp;

import com.example.expense_management.config.LoginMessage;
import com.example.expense_management.dto.LoginDTO;
import com.example.expense_management.dto.UserDTO;
import com.example.expense_management.exception.RecordNotFoundException;
import com.example.expense_management.model.User;
import com.example.expense_management.repository.IUserRepository;
import com.example.expense_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Page<User> findUserByEmailContaining(String name, Pageable pageable) {
        return iUserRepository.findUserByEmailContaining(name,pageable);
    }

    @Override
    public void delete(int id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(int id) {
        return iUserRepository.findById(id);
    }
    //register addUser default 2 = user,1 = admin
    @Override
    public void save(User user) throws RecordNotFoundException {
        User udto = iUserRepository.findByEmail(user.getEmail());

        if(udto == null) {
            User userDto = new User(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    this.passwordEncoder.encode(user.getPassword()),
                    user.getPhoneNumber(),
                    user.getRoleId(),
                    user.getRoleName()
            );
            iUserRepository.save(userDto);
        }else {
            throw new RecordNotFoundException("Email is already Registered..");
        }

    }
    //Login
    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = iUserRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = iUserRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }
    }

    @Override
    public User findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }

}
