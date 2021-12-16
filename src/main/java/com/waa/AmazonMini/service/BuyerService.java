package com.waa.AmazonMini.service;

import com.waa.AmazonMini.auth.model.ERole;
import com.waa.AmazonMini.auth.model.Role;
import com.waa.AmazonMini.auth.repository.RoleRepository;
import com.waa.AmazonMini.auth.repository.UserRepository;
import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.domain.Buyer;
import com.waa.AmazonMini.domain.User;
import com.waa.AmazonMini.dto.BuyerSaveDTO;
import com.waa.AmazonMini.dto.BuyerUpdateDTO;
import com.waa.AmazonMini.dto.BuyerSaveDTO;
import com.waa.AmazonMini.repository.BuyerRepository;
import com.waa.AmazonMini.service.interfaces.IBuyerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.Status;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Struct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BuyerService implements IBuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public Buyer registerBuyer(BuyerSaveDTO dto)  {
        if (userRepository.existsByUsername(dto.getUserName())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(dto.getEmailAddress())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User u = new User();
        u.setEmail(dto.getEmailAddress());
        u.setFullName(dto.getFullName());
        u.setUsername(dto.getUserName());
        u.setIsDeleted(0);
        u.setPassword(encoder.encode(dto.getPassword()));
        u.setPhone(dto.getPhoneNo());

        Set<Role> roles = new HashSet<>();
        Role BuyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(BuyerRole);

        u.setRoles(roles);
        userRepository.save(u);


        Buyer s = new Buyer();
        s.setUser(u);
        s.setShippingAddress(dto.getShippingAddress());
        s.setPoints(0);
        buyerRepository.save(s);

        return s;
    }


    @Override
    public ResponseMessage update(BuyerUpdateDTO buyerUpdateDTO) {
        var buyer = buyerRepository.findById(buyerUpdateDTO.getId());
        if(buyer.isPresent()){
            var b = buyer.get();
            b.setPoints(buyerUpdateDTO.getPoints());
            b.setShippingAddress(buyerUpdateDTO.getShippingAddress());
            return new ResponseMessage("Buyer Update", HttpStatus.OK, b);
        }

        return  new ResponseMessage("Buyer not found", HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseMessage delete(Long id) {
        var buyer = buyerRepository.findById(id);
        if (buyer.isPresent()) {
            buyer.get().getUser().setIsDeleted(1);
        }
       return  new ResponseMessage("Buyer Deleted", HttpStatus.OK);
    }


    @Override
    public Buyer findById(Long id) {
        var buyer = buyerRepository.findById(id);
        if (buyer.isPresent())
            return buyer.get();
        else
            return new Buyer();
    }

    @Override
    public Page<Buyer> findAll(@PageableDefault(size = 50) Pageable pageable) {
        return buyerRepository.findAll(pageable);
    }


}
