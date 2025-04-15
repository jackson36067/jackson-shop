package com.jackson.service;

import com.jackson.dto.AddAddressDTO;
import com.jackson.dto.UpdateAddressDTO;
import com.jackson.result.Result;
import com.jackson.vo.AddressVO;

import java.util.List;

public interface AddressService {
    void addAddress(AddAddressDTO addAddressDTO);

    void updateMemberAddress(UpdateAddressDTO updateAddressDTO);

    Result<List<AddressVO>> getMemberAddressList();

    Result<AddressVO> getMemberAddressById(Long id);

    void removeAddressById(Long id);

    Result<AddressVO> getMemberDefaultAddress();
}
