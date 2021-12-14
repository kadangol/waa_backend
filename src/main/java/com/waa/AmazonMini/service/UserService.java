package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.User;
import com.waa.AmazonMini.repository.UsersRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private  UsersRepository usersRepository;


    public User registerUser(User user){
        var result = usersRepository.save(user);
        return result;
    }

    public User updateUser(User user){
        return  usersRepository.save(user);
    }

    public User deleteUser(long Id)
    {
        var u = usersRepository.getById(Id);
        u.setIsDeleted(1);
        return  usersRepository.save(u);
    }

}
