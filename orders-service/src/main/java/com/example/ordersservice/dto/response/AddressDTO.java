package com.example.ordersservice.dto.response;

import com.example.ordersservice.domain.Address;
import lombok.Getter;

@Getter
public class AddressDTO {
    private String addressMain;
    private String addressSub;
    private String zipcode;

    public AddressDTO(Address address) {
        this.addressMain = address.getAddressMain();
        this.addressSub = address.getAddressSub();
        this.zipcode = address.getZipcode();
    }
}
