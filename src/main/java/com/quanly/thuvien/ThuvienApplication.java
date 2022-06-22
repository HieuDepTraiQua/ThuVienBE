package com.quanly.thuvien;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quanly.thuvien.model.AccountModel;
import com.quanly.thuvien.repository.AccountRepository;

@SpringBootApplication
//extends SpringBootServletInitializer 
public class ThuvienApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ThuvienApplication.class, args);
	}

	@Autowired
    AccountRepository accountRepository;
	
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
//			List<RoleModel> adminRole = roleRepository.findByTitleCode("ADMIN");
//			if (adminRole.size() == 0) {
//				RoleModel newAdmin = new RoleModel("ADMIN");
//				roleRepository.save(newAdmin);
//			}
//
//			List<RoleModel> cskhRole = roleRepository.findByTitleCode("CSKH");
//			if (cskhRole.size() == 0) {
//				RoleModel newCSKH = new RoleModel("CSKH");
//				roleRepository.save(newCSKH);
//			}
//
//			List<RoleModel> userRole = roleRepository.findByTitleCode("KTV");
//			if (userRole.size() == 0) {
//				RoleModel newEmployee = new RoleModel("KTV");
//				roleRepository.save(newEmployee);
//			}
//
//			List<RoleModel> viewRole = roleRepository.findByTitleCode("VIEW");
//			if (viewRole.size() == 0) {
//				RoleModel newEmployee = new RoleModel("VIEW");
//				roleRepository.save(newEmployee);
//			}

			Optional<AccountModel> adminAccount = accountRepository.findByUsername("admin");
			if (!adminAccount.isPresent()) {
				AccountModel newAdminAccount = new AccountModel();
				newAdminAccount.setUsername("admin");
				newAdminAccount.setPassword(passwordEncoder.encode("123456"));
				accountRepository.save(newAdminAccount);
			}

//			Optional<AccountModel> cskhAccount = accountRepository.findByUsername("cskh");
//			if (!cskhAccount.isPresent()) {
//				AccountModel newCSKHAccount = new AccountModel();
//				newCSKHAccount.setUsername("cskh");
//				newCSKHAccount.setPassword(bCryptPasswordEncoder.encode("12345678"));
//				newCSKHAccount.setName("CSKH");
//				newCSKHAccount.setRoleId(roleRepository.findByTitleRole("CSKH").get().getId());
//				accountRepository.save(newCSKHAccount);
//			}
//
//			Optional<AccountModel> viewhAccount = accountRepository.findByUsername("view");
//			if (!viewhAccount.isPresent()) {
//				AccountModel newCSKHAccount = new AccountModel();
//				newCSKHAccount.setUsername("view");
//				newCSKHAccount.setPassword(bCryptPasswordEncoder.encode("12345678"));
//				newCSKHAccount.setName("VIEW");
//				newCSKHAccount.setRoleId(roleRepository.findByTitleRole("VIEW").get().getId());
//				accountRepository.save(newCSKHAccount);
//			}
//
//			excelService.initUploadFile();
		};
	}
}
