package com.jackson.controller;

import com.jackson.dto.AddAddressDTO;
import com.jackson.dto.UpdateAddressDTO;
import com.jackson.result.Result;
import com.jackson.service.AddressService;
import com.jackson.vo.AddressVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    /**
     * 新增用户地址
     * @param addAddressDTO 用户地址信息
     */
    @PostMapping
    public void addAddress(@RequestBody AddAddressDTO addAddressDTO) {
        addressService.addAddress(addAddressDTO);
    }

    /**
     * 更新用户地址
     * @param updateAddressDTO
     */
    @PostMapping("/update")
    public void setDefaultAddress(@RequestBody UpdateAddressDTO updateAddressDTO) {
        addressService.updateMemberAddress(updateAddressDTO);
    }

    /**
     * 获取用户地址列表
     * @return
     */
    @GetMapping("/list")
    public Result<List<AddressVO>> getMemberAddressList() {
        return addressService.getMemberAddressList();
    }
}
