package com.quanly.thuvien;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quanly.thuvien.model.AccountModel;
import com.quanly.thuvien.model.RoleModel;
import com.quanly.thuvien.repository.AccountRepository;
import com.quanly.thuvien.repository.RoleRepository;

@SpringBootApplication
public class ThuvienApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ThuvienApplication.class, args);
	}

	@Autowired
    AccountRepository accountRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
    @Autowired
    PasswordEncoder passwordEncoder;
    
//    @Override
//    public void run(String... args) throws Exception {
//        AccountModel user = new AccountModel();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("123456"));
//        accountRepository.save(user);
//    }
    
    
    @Bean
	CommandLineRunner init(AccountRepository accountRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			List<RoleModel> adminRole = roleRepository.findByTitleCode("ADMIN");
			if (adminRole.size() == 0) {
				RoleModel newAdmin = new RoleModel("ADMIN");
				roleRepository.save(newAdmin);
			}

			List<RoleModel> userRole = roleRepository.findByTitleCode("USER");
			if (userRole.size() == 0) {
				RoleModel newCSKH = new RoleModel("USER");
				roleRepository.save(newCSKH);
			}

			Optional<AccountModel> adminAccount = accountRepository.findByUsername("admin");
			if (!adminAccount.isPresent()) {
				AccountModel newAdminAccount = new AccountModel();
				newAdminAccount.setUsername("admin");
				newAdminAccount.setPassword(passwordEncoder.encode("123456"));
				accountRepository.save(newAdminAccount);
			}
		};
	}
}
