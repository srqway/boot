package idv.hsiehpinghan.springcloudstarteroauth2boot.redis.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springcloudstarteroauth2boot.redis.entity.UserEntity;
import idv.hsiehpinghan.springcloudstarteroauth2boot.redis.repository.UserRepository;
import idv.hsiehpinghan.springcloudstarteroauth2boot.redis.utility.ConvertUtility;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findById(username).get();
		String password = userEntity.getPassword();
		Boolean enabled = userEntity.getEnabled();
		Boolean accountNonExpired = userEntity.getAccountNonExpired();
		Boolean credentialsNonExpired = userEntity.getCredentialsNonExpired();
		Boolean accountNonLocked = userEntity.getAccountNonLocked();
		Collection<? extends GrantedAuthority> authorities = ConvertUtility
				.convertToGrantedAuthorities(userEntity.getRoles());
		return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
	}

}
