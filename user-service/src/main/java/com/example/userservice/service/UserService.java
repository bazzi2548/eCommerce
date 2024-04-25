package com.example.userservice.service;

import com.example.userservice.domain.Address;
import com.example.userservice.domain.Goods;
import com.example.userservice.domain.User;
import com.example.userservice.domain.Wishlist;
import com.example.userservice.dto.request.AddWishlistRequest;
import com.example.userservice.dto.request.SaveUserRequest;
import com.example.userservice.dto.request.UpdateUserRequest;
import com.example.userservice.dto.request.UpdateWishlistRequest;
import com.example.userservice.dto.response.MyPageResponse;
import com.example.userservice.dto.response.WishlistResponse;
import com.example.userservice.repository.GoodsRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.WishlistRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
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

    @Transactional(readOnly = true)
    public List<WishlistResponse> getWishlist(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName());

        return wishlistRepository.findAllByUserId(user.getUserId())
                .stream()
                .map(WishlistResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getWishlistGoods(long wishlistId) {
        Long goodsId = wishlistRepository.findById(wishlistId).get().getGoods().getGoodsId();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/goods/"+goodsId));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    public void deleteWishlist(long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    public void updateWishlist(long wishlistId, UpdateWishlistRequest request) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).get();
        wishlist.updateCount(request.getCount());
    }
}

