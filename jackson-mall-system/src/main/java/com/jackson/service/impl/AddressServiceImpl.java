package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.context.BaseContext;
import com.jackson.dto.AddAddressDTO;
import com.jackson.dto.UpdateAddressDTO;
import com.jackson.entity.ShopAddress;
import com.jackson.repository.AddressRepository;
import com.jackson.result.Result;
import com.jackson.service.AddressService;
import com.jackson.vo.AddressVO;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressRepository addressRepository;

    /**
     * 用户新增地址
     *
     * @param addAddressDTO 地址相关信息
     */
    public void addAddress(AddAddressDTO addAddressDTO) {
        ShopAddress shopAddress = BeanUtil.copyProperties(addAddressDTO, ShopAddress.class);
        Long userId = BaseContext.getCurrentId();
        shopAddress.setMemberId(userId);
        if (shopAddress.getIsDefault() == 0) {
            setAddressDefault(userId);
        }
        addressRepository.save(shopAddress);
    }

    /**
     * 更改用户地址
     *
     * @param updateAddressDTO 保存默认地址信息
     */
    // TODO: 改成修改地址信息
    public void updateMemberAddress(UpdateAddressDTO updateAddressDTO) {
        Long userId = BaseContext.getCurrentId();
        // 通过id获取用户地址信息
        ShopAddress shopAddress = addressRepository.findById(updateAddressDTO.getAddressId()).get();
        // 如果用户更改了地址收货人名称
        String name = updateAddressDTO.getName();
        if (!StringUtil.isNullOrEmpty(name) && !name.equals(shopAddress.getName())) {
            shopAddress.setName(name);
        }
        // 如果用户更改了地址的省份
        String province = updateAddressDTO.getProvince();
        if (!StringUtil.isNullOrEmpty(province) && !province.equals(shopAddress.getProvince())) {
            shopAddress.setProvince(province);
        }
        // 如果用户更改了地址的城市 -> 有些是没有城市的
        String city = updateAddressDTO.getCity();
        if(city != null){
            if (!city.equals(shopAddress.getCity())) {
                shopAddress.setCity(city);
            }
        }else {
            shopAddress.setCity("");
        }
        // 如果用户更改了地址的乡镇
        String county = updateAddressDTO.getCounty();
        if (!StringUtil.isNullOrEmpty(county) && !county.equals(shopAddress.getCounty())) {
            shopAddress.setCounty(county);
        }
        // 如果用户更改了地址的详细地址
        String addressDetail = updateAddressDTO.getAddressDetail();
        if (!StringUtil.isNullOrEmpty(addressDetail) && !addressDetail.equals(shopAddress.getAddressDetail())) {
            shopAddress.setAddressDetail(addressDetail);
        }
        // 如果用户更改了地址的地区编码
        String areaCode = updateAddressDTO.getAreaCode();
        if (!StringUtil.isNullOrEmpty(areaCode) && !areaCode.equals(shopAddress.getAreaCode())) {
            shopAddress.setAreaCode(areaCode);
        }
        // 如果用户更改了地址的邮政编码
        String postalCode = updateAddressDTO.getPostalCode();
        if (!StringUtil.isNullOrEmpty(postalCode) && !postalCode.equals(shopAddress.getPostalCode())) {
            shopAddress.setPostalCode(postalCode);
        }
        // 如果用户更改了地址收货人的电话号码
        String tel = updateAddressDTO.getTel();
        if (!StringUtil.isNullOrEmpty(tel) && !tel.equals(shopAddress.getTel())) {
            shopAddress.setTel(tel);
        }
        // 如果用户更改了地址的标签
        String tag = updateAddressDTO.getTag();
        if (tag == null) {
            shopAddress.setTag("");
        } else {
            if (!tag.equals(shopAddress.getTag())) {
                shopAddress.setTag(tag);
            }
        }
        // 如果用户修改了地址是否为默认地址
        Short isDefault = updateAddressDTO.getIsDefault();
        if (!isDefault.equals(shopAddress.getIsDefault())) {
            // 判断用户是将该地址设为默认地址还是取消默认地址
            if (isDefault == 0) {
                // 设置为默认地址 -> 找到原先的默认地址, 将其取消默认地址
                setAddressDefault(userId);
                shopAddress.setIsDefault(isDefault);
            } else {
                // 将地址取消设置为默认地址
                shopAddress.setIsDefault(isDefault);
            }
        }
        // 更新用户地址
        addressRepository.saveAndFlush(shopAddress);
    }


    /**
     * 获取用户地址列表
     *
     * @return
     */
    public Result<List<AddressVO>> getMemberAddressList() {
        List<AddressVO> addressVOList = addressRepository.findAllByMemberId(BaseContext.getCurrentId())
                .stream()
                .map(shopAddress -> BeanUtil.copyProperties(shopAddress, AddressVO.class))
                .sorted((Comparator<AddressVO>) Comparator.comparingInt(AddressVO::getIsDefault))
                .toList();
        return Result.success(addressVOList);
    }

    /**
     * 根据id获取地址数据
     *
     * @param id
     * @return
     */
    public Result<AddressVO> getMemberAddressById(Long id) {
        ShopAddress shopAddress = addressRepository.findById(id).get();
        AddressVO addressVO = BeanUtil.copyProperties(shopAddress, AddressVO.class);
        return Result.success(addressVO);
    }

    /**
     * 根据地址id删除地址
     * @param id
     */
    public void removeAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    /**
     * 将原本为默认的地址取消默认地址状态
     *
     * @param userId 用户id
     */
    public void setAddressDefault(Long userId) {
        ShopAddress defaultShopAddress = addressRepository.findByIsDefaultAndMemberId((short) 0, userId);
        // 如果先前没有设置默认地址的话,那么不需要更改之前的默认地址
        if (defaultShopAddress == null) {
            return;
        }
        defaultShopAddress.setIsDefault((short) 1);
        addressRepository.saveAndFlush(defaultShopAddress);
    }
}
