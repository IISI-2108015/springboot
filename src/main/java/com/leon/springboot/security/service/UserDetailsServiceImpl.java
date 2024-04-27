package com.leon.springboot.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leon.springboot.db1.dao.UserRepository;
import com.leon.springboot.db1.model.User;
import com.leon.springboot.db1.model.UserAuth;
import com.leon.springboot.security.config.UserAuthority;
import com.leon.springboot.security.model.AppUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find user: " + username);
        }
        String encodedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(user.getUserId());
        userAuth.setUserName(user.getUserName());
        userAuth.setPassword(encodedPwd);
        userAuth.setAuthority(UserAuthority.valueOf(user.getAuthority()));
        userAuth.setEnabled(true);
        userAuth.setPremium(true);
        userAuth.setTrailExpiration(user.getTrailExpiration());

        return new AppUserDetails(userAuth);
    }
	
}
