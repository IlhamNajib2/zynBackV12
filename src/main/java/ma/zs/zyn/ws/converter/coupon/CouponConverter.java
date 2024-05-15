package  ma.zs.zyn.ws.converter.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.zyn.zynerator.util.ListUtil;

import ma.zs.zyn.ws.converter.coupon.CouponDetailConverter;
import ma.zs.zyn.ws.converter.coupon.InfluencerConverter;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.ws.dto.coupon.CouponDto;

@Component
public class CouponConverter {

    @Autowired
    private CouponDetailConverter couponDetailConverter ;
    @Autowired
    private InfluencerConverter influencerConverter ;
    @Autowired
    private PackagingConverter packagingConverter ;
    private boolean influencer;
    private boolean couponDetails;

    public  CouponConverter() {
        init(true);
    }


    public Coupon toItem(CouponDto dto) {
        if (dto == null) {
            return null;
        } else {
        Coupon item = new Coupon();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDateStart()))
                item.setDateStart(DateUtil.stringEnToDate(dto.getDateStart()));
            if(StringUtil.isNotEmpty(dto.getDateEnd()))
                item.setDateEnd(DateUtil.stringEnToDate(dto.getDateEnd()));
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(this.influencer && dto.getInfluencer()!=null)
                item.setInfluencer(influencerConverter.toItem(dto.getInfluencer())) ;


            if(this.couponDetails && ListUtil.isNotEmpty(dto.getCouponDetails()))
                item.setCouponDetails(couponDetailConverter.toItem(dto.getCouponDetails()));


        return item;
        }
    }


    public CouponDto toDto(Coupon item) {
        if (item == null) {
            return null;
        } else {
            CouponDto dto = new CouponDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(item.getDateStart()!=null)
                dto.setDateStart(DateUtil.dateTimeToString(item.getDateStart()));
            if(item.getDateEnd()!=null)
                dto.setDateEnd(DateUtil.dateTimeToString(item.getDateEnd()));
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(this.influencer && item.getInfluencer()!=null) {
                dto.setInfluencer(influencerConverter.toDto(item.getInfluencer())) ;

            }
        if(this.couponDetails && ListUtil.isNotEmpty(item.getCouponDetails())){
            couponDetailConverter.init(true);
            couponDetailConverter.setCoupon(false);
            dto.setCouponDetails(couponDetailConverter.toDto(item.getCouponDetails()));
            couponDetailConverter.setCoupon(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.couponDetails = value;
    }
    public void initObject(boolean value) {
        this.influencer = value;
    }
	
    public List<Coupon> toItem(List<CouponDto> dtos) {
        List<Coupon> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CouponDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CouponDto> toDto(List<Coupon> items) {
        List<CouponDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Coupon item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CouponDto dto, Coupon t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getCouponDetails() != null)
            t.setCouponDetails(couponDetailConverter.copy(dto.getCouponDetails()));
        if (dto.getInfluencer() != null)
        influencerConverter.copy(dto.getInfluencer(), t.getInfluencer());
    }

    public List<Coupon> copy(List<CouponDto> dtos) {
        List<Coupon> result = new ArrayList<>();
        if (dtos != null) {
            for (CouponDto dto : dtos) {
                Coupon instance = new Coupon();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CouponDetailConverter getCouponDetailConverter(){
        return this.couponDetailConverter;
    }
    public void setCouponDetailConverter(CouponDetailConverter couponDetailConverter ){
        this.couponDetailConverter = couponDetailConverter;
    }
    public InfluencerConverter getInfluencerConverter(){
        return this.influencerConverter;
    }
    public void setInfluencerConverter(InfluencerConverter influencerConverter ){
        this.influencerConverter = influencerConverter;
    }
    public PackagingConverter getPackagingConverter(){
        return this.packagingConverter;
    }
    public void setPackagingConverter(PackagingConverter packagingConverter ){
        this.packagingConverter = packagingConverter;
    }
    public boolean  isInfluencer(){
        return this.influencer;
    }
    public void  setInfluencer(boolean influencer){
        this.influencer = influencer;
    }
    public boolean  isCouponDetails(){
        return this.couponDetails ;
    }
    public void  setCouponDetails(boolean couponDetails ){
        this.couponDetails  = couponDetails ;
    }
}
