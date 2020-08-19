package com.cloud.auth.config.auth;
import com.cloud.auth.config.auth.model.Auth;
import com.cloud.auth.config.auth.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = new User();
        user.setUserName(userName);
        user.setPassword("123456");
        List<Auth> list = new ArrayList<>();
        list.add(new Auth("admin"));
        user.setList(list);
        return user;
    }
}
