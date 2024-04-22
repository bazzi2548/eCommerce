package com.example.ecommerce.service;

import com.example.ecommerce.domain.Address;
import com.example.ecommerce.domain.Goods;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.Wishlist;
import com.example.ecommerce.dto.request.AddWishlistRequest;
import com.example.ecommerce.dto.request.SaveUserRequest;
import com.example.ecommerce.dto.request.UpdateUserRequest;
import com.example.ecommerce.dto.response.MyPageResponse;
import com.example.ecommerce.dto.response.WishlistResponse;
import com.example.ecommerce.repository.GoodsRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.WishlistRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final GoodsRepository goodsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final WishlistRepository wishlistRepository;

    public UserService(UserRepository userRepository, GoodsRepository goodsRepository, BCryptPasswordEncoder bCryptPasswordEncoder, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.goodsRepository = goodsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.wishlistRepository = wishlistRepository;
    }

    public void saveUser(SaveUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ;
        }
        User user = new User(request, bCryptPasswordEncoder);
        Address address = new Address(request);
        address.setUser(user);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public MyPageResponse myPage(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName());
        return new MyPageResponse(user);
    }


    public void updateUser(Authentication auth, UpdateUserRequest updateInfo) {
        User user = userRepository.findByEmail(auth.getName());
        user.update(updateInfo, bCryptPasswordEncoder);
    }

    public void addWishList(Authentication auth, AddWishlistRequest request) {
        User user = userRepository.findByEmail(auth.getName());
        Goods goods = goodsRepository.findById(request.getGoodsId()).orElseThrow(()
                -> new IllegalArgumentException("해당 상품이 없습니다."));

        wishlistRepository.save(new Wishlist(user.getUserId(), goods, request.getCount()));
    }

    public List<WishlistResponse> getWishlist(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName());

        return wishlistRepository.findAllByUserId(user.getUserId())
                .stream()
                .map(WishlistResponse::new)
                .toList();
    }

    public ResponseEntity<?> getWishlistGoods(long id) {
        Long goodsId = wishlistRepository.findById(id).get().getGoods().getGoodsId();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/goods/"+goodsId));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}

