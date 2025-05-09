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
     *
     * @param addAddressDTO 用户地址信息
     */
    @PostMapping
    public void addAddress(@RequestBody AddAddressDTO addAddressDTO) {
        addressService.addAddress(addAddressDTO);
    }

    /**
     * 更新用户地址
     *
     * @param updateAddressDTO
     */
    @PostMapping("/update")
    public void setDefaultAddress(@RequestBody UpdateAddressDTO updateAddressDTO) {
        addressService.updateMemberAddress(updateAddressDTO);
    }

    /**
     * 获取用户地址列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<AddressVO>> getMemberAddressList() {
        return addressService.getMemberAddressList();
    }

    /**
     * 根据地址id获取地址信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<AddressVO> getAddressById(@PathVariable Long id) {
        return addressService.getMemberAddressById(id);
    }

    /**
     * 根据地址id删除地址
     * @param id
     */
    @DeleteMapping("/{id}")
    public void removeAddressById(@PathVariable Long id) {
      addressService.removeAddressById(id);
    }

    /**
     * 获取用户的默认地址信息
     * @return
     */
    @GetMapping("/default")
    public Result<AddressVO> getDefaultAddress() {
       return addressService.getMemberDefaultAddress();
    }
}
