package com.quanly.thuvien.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quanly.thuvien.model.AccountModel;
import com.quanly.thuvien.model.RoleModel;
import com.quanly.thuvien.reponse.ResourceNotFoundException;
import com.quanly.thuvien.repository.AccountRepository;
import com.quanly.thuvien.repository.RoleRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountModel account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		if (account == null) {
			throw new UsernameNotFoundException("User not found with username : " + username);
		}
		List<RoleModel> roles = roleRepository.findByIdOne(account.getRoleId());
		return UserPrincipal.create(account, roles);

	}

	@Transactional
	public UserDetails loadUserById(String userId) {
		AccountModel user = accountRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		List<RoleModel> roles = roleRepository.findByIdOne(user.getRoleId());
		return UserPrincipal.create(user, roles);
	}
}
