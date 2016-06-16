package com.seri.web.security;

import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 07/04/16.
 */
public class LoginAuthProvider implements AuthenticationProvider {

    GlobalFunUtils globalFunUtil = new GlobalFunUtils();
    UserDaoImpl userDao = new UserDaoImpl();

    public LoginAuthProvider(){}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {

            String userName = authentication.getName();
            String password = globalFunUtil.getMd5Hex(authentication.getCredentials().toString());
            User userDetails = userDao.getAuth(userName, password);

            Map<String, String> map = new HashMap<String, String>();
            map.put("userEmail", userDetails.getLogin());
            map.put("userFirstName", userDetails.getfName());
            map.put("userMidName", userDetails.getmName());
            map.put("userLastName", userDetails.getlName());
            map.put("userId", String.valueOf(userDetails.getUserId()));
            map.put("firstLogin", String.valueOf(userDetails.getFirstReset()));
            map.put("role", userDetails.getRole());

            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

            grantedAuths.add(new SimpleGrantedAuthority(userDetails.getRole()));
            Authentication auth = new SeriAuthentication(userName, grantedAuths, map);

            return auth;


        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
