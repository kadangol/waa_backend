package com.waa.AmazonMini.service;

import com.waa.AmazonMini.auth.model.ERole;
import com.waa.AmazonMini.auth.model.Role;
import com.waa.AmazonMini.auth.payload.response.MessageResponse;
import com.waa.AmazonMini.auth.repository.RoleRepository;
import com.waa.AmazonMini.auth.repository.UserRepository;
import com.waa.AmazonMini.domain.Product;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SellerService implements ISellerService {

    @Autowired
    private UserService userService;
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Seller registerSeller(SellerSaveDTO dto) {
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
        Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(sellerRole);

        u.setRoles(roles);
        userRepository.save(u);


        Seller s = new Seller();
        s.setUser(u);
        s.setApprovalStatus(Status.NOTAPPROVEDYET);
        sellerRepository.save(s);

        return s;
    }


    @Override
    public String ApproveSeller(long Id) {
        var s = sellerRepository.findById(Id);
        if (s.isPresent()) {
            s.get().setApprovalStatus(Status.APPROVED);
        }
        return "Seller Approved.";
    }

    @Override
    public String RejectSeller(long Id) {
        var s = sellerRepository.findById(Id);
        if (s.isPresent()) {
            var seller = s.get();
            seller.setApprovalStatus(Status.REJECTED);
            sellerRepository.save(seller);
        }
        return "Seller Reject.";
    }

    @Override
    public Page<Seller> findAll(@PageableDefault(size = 50) Pageable pageable) {
        return sellerRepository.findAll(pageable);
    }

    @Override
    public Seller getSeller(long id) {
        var s = sellerRepository.findById(id);
        if (s.isPresent())
            return s.get();
        else
            return new Seller();
    }

    public Seller getSellerByUserId(long userId) {
        var s = sellerRepository.findByUserId1(userId);
        if (s != null)
            return s;
        else
            return new Seller();
    }

    public List<Seller> getUnApprovedSeller() {
        var s = sellerRepository.findSellerByApprovalStatus(Status.NOTAPPROVEDYET);
        return  s;
    }

    public List<Seller> getApprovedSeller() {
        var s = sellerRepository.findSellerByApprovalStatus(Status.APPROVED);
        return  s;
    }

    public List<Product> findProductBySeller(long sellerId) {
        var s = sellerRepository.findProductBySeller(sellerId);
        return  s;
    }





}

