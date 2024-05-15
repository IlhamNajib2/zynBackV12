package  ma.zs.zyn.ws.converter.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.coupon.CouponConverter;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;

import ma.zs.zyn.bean.core.coupon.Coupon;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.coupon.CouponDetail;
import ma.zs.zyn.ws.dto.coupon.CouponDetailDto;

@Component
public class CouponDetailConverter {

    @Autowired
    private CouponConverter couponConverter ;
    @Autowired
    private PackagingConverter packagingConverter ;
    private boolean packaging;
    private boolean coupon;

    public  CouponDetailConverter() {
        initObject(true);
    }


    public CouponDetail toItem(CouponDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        CouponDetail item = new CouponDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDiscount()))
                item.setDiscount(dto.getDiscount());
            if(StringUtil.isNotEmpty(dto.getAmountGivenInfluencer()))
                item.setAmountGivenInfluencer(dto.getAmountGivenInfluencer());
            if(StringUtil.isNotEmpty(dto.getUsingNumber()))
                item.setUsingNumber(dto.getUsingNumber());
            if(StringUtil.isNotEmpty(dto.getMaxUsingNumber()))
                item.setMaxUsingNumber(dto.getMaxUsingNumber());
            if(this.packaging && dto.getPackaging()!=null)
                item.setPackaging(packagingConverter.toItem(dto.getPackaging())) ;

            if(dto.getCoupon() != null && dto.getCoupon().getId() != null){
                item.setCoupon(new Coupon());
                item.getCoupon().setId(dto.getCoupon().getId());
                item.getCoupon().setName(dto.getCoupon().getName());
            }




        return item;
        }
    }


    public CouponDetailDto toDto(CouponDetail item) {
        if (item == null) {
            return null;
        } else {
            CouponDetailDto dto = new CouponDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDiscount()))
                dto.setDiscount(item.getDiscount());
            if(StringUtil.isNotEmpty(item.getAmountGivenInfluencer()))
                dto.setAmountGivenInfluencer(item.getAmountGivenInfluencer());
            if(StringUtil.isNotEmpty(item.getUsingNumber()))
                dto.setUsingNumber(item.getUsingNumber());
            if(StringUtil.isNotEmpty(item.getMaxUsingNumber()))
                dto.setMaxUsingNumber(item.getMaxUsingNumber());
            if(this.packaging && item.getPackaging()!=null) {
                dto.setPackaging(packagingConverter.toDto(item.getPackaging())) ;

            }
            if(this.coupon && item.getCoupon()!=null) {
                dto.setCoupon(couponConverter.toDto(item.getCoupon())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.packaging = value;
        this.coupon = value;
    }
	
    public List<CouponDetail> toItem(List<CouponDetailDto> dtos) {
        List<CouponDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CouponDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CouponDetailDto> toDto(List<CouponDetail> items) {
        List<CouponDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CouponDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CouponDetailDto dto, CouponDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getPackaging() != null)
        packagingConverter.copy(dto.getPackaging(), t.getPackaging());
        if (dto.getCoupon() != null)
        couponConverter.copy(dto.getCoupon(), t.getCoupon());
    }

    public List<CouponDetail> copy(List<CouponDetailDto> dtos) {
        List<CouponDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (CouponDetailDto dto : dtos) {
                CouponDetail instance = new CouponDetail();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CouponConverter getCouponConverter(){
        return this.couponConverter;
    }
    public void setCouponConverter(CouponConverter couponConverter ){
        this.couponConverter = couponConverter;
    }
    public PackagingConverter getPackagingConverter(){
        return this.packagingConverter;
    }
    public void setPackagingConverter(PackagingConverter packagingConverter ){
        this.packagingConverter = packagingConverter;
    }
    public boolean  isPackaging(){
        return this.packaging;
    }
    public void  setPackaging(boolean packaging){
        this.packaging = packaging;
    }
    public boolean  isCoupon(){
        return this.coupon;
    }
    public void  setCoupon(boolean coupon){
        this.coupon = coupon;
    }
}
