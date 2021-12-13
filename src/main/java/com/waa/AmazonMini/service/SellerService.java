package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.domain.Users;
import com.waa.AmazonMini.dto.SellerSaveDTO;
import com.waa.AmazonMini.repository.SellerRepository;
import com.waa.AmazonMini.service.interfaces.ISellerService;
import com.waa.AmazonMini.utils.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SellerService implements ISellerService {

    @Autowired
    private UserService userService;
    @Autowired
    private SellerRepository sellerRepository;


    @Override
    public Seller registerSeller(SellerSaveDTO dto) {

        Users u = new Users();
        u.setEmailAddress(dto.getEmailAddress());
        u.setFullName(dto.getFullName());
        u.setUserName(dto.getUserName());
        u.setIsDeleted(false);
        u.setPassword(dto.getPassword());
        u.setPhoneNo(dto.getPhoneNo());

        userService.registerUser(u);

        Seller s = new Seller();
        s.setUser(u);
        s.setApprovalStatus(Status.NOTAPPROVEDYET);
        sellerRepository.save(s);

        return  s;
    }


}
