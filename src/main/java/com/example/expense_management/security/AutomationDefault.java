package com.example.expense_management.security;

import com.example.expense_management.model.Role;
import com.example.expense_management.model.User;
import com.example.expense_management.repository.IRoleRepository;
import com.example.expense_management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AutomationDefault implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_USER") == null) {
            roleRepository.save(new Role("ROLE_USER"));
        }

        //them admin
        if (userRepository.findUserByUsername("LongAdmin") == null) {
            User admin = new User();
            admin.setUsername("LongAdmin");
            // mã hóa mật khẩu
            admin.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("Hungvulong1"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_USER"));
            admin.setRole(roles);
            admin.setRoleName("Admin");
            userRepository.save(admin);
        }

        //Them user
        if (userRepository.findUserByUsername("Longmember") == null) {
            User user = new User();
            user.setUsername("Longmember");
            // mã hóa mật khẩu
            user.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("Hungvulong1"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            user.setRole(roles);
            user.setRoleName("User");
            userRepository.save(user);
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
