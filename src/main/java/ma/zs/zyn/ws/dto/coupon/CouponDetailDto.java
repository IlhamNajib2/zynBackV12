package  ma.zs.zyn.ws.dto.coupon;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.packaging.PackagingDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponDetailDto  extends AuditBaseDto {

    private BigDecimal discount  ;
    private BigDecimal amountGivenInfluencer  ;
    private BigDecimal usingNumber  ;
    private BigDecimal maxUsingNumber  ;

    private PackagingDto packaging ;
    private CouponDto coupon ;



    public CouponDetailDto(){
        super();
    }



    @Log
    public BigDecimal getDiscount(){
        return this.discount;
    }
    public void setDiscount(BigDecimal discount){
        this.discount = discount;
    }

    @Log
    public BigDecimal getAmountGivenInfluencer(){
        return this.amountGivenInfluencer;
    }
    public void setAmountGivenInfluencer(BigDecimal amountGivenInfluencer){
        this.amountGivenInfluencer = amountGivenInfluencer;
    }

    @Log
    public BigDecimal getUsingNumber(){
        return this.usingNumber;
    }
    public void setUsingNumber(BigDecimal usingNumber){
        this.usingNumber = usingNumber;
    }

    @Log
    public BigDecimal getMaxUsingNumber(){
        return this.maxUsingNumber;
    }
    public void setMaxUsingNumber(BigDecimal maxUsingNumber){
        this.maxUsingNumber = maxUsingNumber;
    }


    public PackagingDto getPackaging(){
        return this.packaging;
    }

    public void setPackaging(PackagingDto packaging){
        this.packaging = packaging;
    }
    public CouponDto getCoupon(){
        return this.coupon;
    }

    public void setCoupon(CouponDto coupon){
        this.coupon = coupon;
    }






}
