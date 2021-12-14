package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.Seller;
import com.waa.AmazonMini.domain.User;
import com.waa.AmazonMini.dto.SellerSaveDTO;
import com.waa.AmazonMini.repository.SellerRepository;
import com.waa.AmazonMini.service.interfaces.ISellerService;
import com.waa.AmazonMini.utils.enums.Status;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

        User u = new User();
        u.setEmail(dto.getEmailAddress());
        u.setFullName(dto.getFullName());
        u.setUsername(dto.getUserName());
        u.setIsDeleted(0);
        u.setPassword(dto.getPassword());
        u.setPhone(dto.getPhoneNo());

        userService.registerUser(u);

        Seller s = new Seller();
        s.setUser(u);
        s.setApprovalStatus(Status.NOTAPPROVEDYET);
        sellerRepository.save(s);

        return s;
    }

    public String ApproveSeller(long Id) {
        var s = sellerRepository.findById(Id);
        if (s.isPresent()) {
            s.get().setApprovalStatus(Status.APPROVED);
        }
        return "Seller Approved.";
    }

    public String RejectSeller(long Id) {
        var s = sellerRepository.findById(Id);
        if (s.isPresent()) {
            var seller = s.get();
            seller.setApprovalStatus(Status.REJECTED);
            sellerRepository.save(seller);
        }
        return "Seller Reject.";
    }

    public Page<Seller> findAll(@PageableDefault(size = 50) Pageable pageable) {
        return sellerRepository.findAll(pageable);
    }


}

