//package com.example.expense_management.sercurity;
//
//import com.example.expense_management.model.Role;
//import com.example.expense_management.model.User;
//import com.example.expense_management.repository.IRoleRepository;
//import com.example.expense_management.repository.IUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//@Component
//public class AutomationDefault implements ApplicationListener<ContextRefreshedEvent> {
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Autowired
//    private IRoleRepository roleRepository;
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (roleRepository.findByName("ROLE_ADMIN") == null) {
//            roleRepository.save(new Role("ROLE_ADMIN"));
//        }
//
//        if (roleRepository.findByName("ROLE_USER") == null) {
//            roleRepository.save(new Role("ROLE_USER"));
//        }
//
//        //them admin
//        if (userRepository.findByEmail("admin@gmail.com") == null) {
//            User admin = new User();
//            admin.setEmail("admin@gmail.com");
//            // mã hóa mật khẩu
//            admin.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123456"));
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleRepository.findByName("ROLE_ADMIN"));
//            roles.add(roleRepository.findByName("ROLE_USER"));
//            admin.setRole(roles);
//            userRepository.save(admin);
//        }
//
//        //Them user
//        if (userRepository.findByEmail("member@gmail.com") == null) {
//            User user = new User();
//            user.setEmail("member@gmail.com");
//            // mã hóa mật khẩu
//            user.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123456"));
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleRepository.findByName("ROLE_USER"));
//            user.setRole(roles);
//            userRepository.save(user);
//        }
//    }
//
//    @Override
//    public boolean supportsAsyncExecution() {
//        return ApplicationListener.super.supportsAsyncExecution();
//    }
//}
