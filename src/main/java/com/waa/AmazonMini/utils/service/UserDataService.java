package com.waa.AmazonMini.utils.service;

import com.waa.AmazonMini.auth.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    public Long getUserId(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getId();
    }
}
