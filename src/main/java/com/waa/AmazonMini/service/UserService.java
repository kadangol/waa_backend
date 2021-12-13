package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.Users;
import com.waa.AmazonMini.repository.UsersRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private  UsersRepository usersRepository;


    public Users registerUser(Users user){
        var result = usersRepository.save(user);
        return result;
    }

    public Users updateUser(Users user){
        return  usersRepository.save(user);
    }

    public  Users deleteUser(long Id)
    {
        var u = usersRepository.getById(Id);
        u.setIsDeleted(true);
        return  usersRepository.save(u);
    }

}
