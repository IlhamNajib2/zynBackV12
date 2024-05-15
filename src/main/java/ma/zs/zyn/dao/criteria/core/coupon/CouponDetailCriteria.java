package  ma.zs.zyn.dao.criteria.core.coupon;


import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;

public class CouponDetailCriteria extends  BaseCriteria  {

    private String discount;
    private String discountMin;
    private String discountMax;
    private String amountGivenInfluencer;
    private String amountGivenInfluencerMin;
    private String amountGivenInfluencerMax;
    private String usingNumber;
    private String usingNumberMin;
    private String usingNumberMax;
    private String maxUsingNumber;
    private String maxUsingNumberMin;
    private String maxUsingNumberMax;

    private PackagingCriteria packaging ;
    private List<PackagingCriteria> packagings ;
    private CouponCriteria coupon ;
    private List<CouponCriteria> coupons ;


    public CouponDetailCriteria(){}

    public String getDiscount(){
        return this.discount;
    }
    public void setDiscount(String discount){
        this.discount = discount;
    }   
    public String getDiscountMin(){
        return this.discountMin;
    }
    public void setDiscountMin(String discountMin){
        this.discountMin = discountMin;
    }
    public String getDiscountMax(){
        return this.discountMax;
    }
    public void setDiscountMax(String discountMax){
        this.discountMax = discountMax;
    }
      
    public String getAmountGivenInfluencer(){
        return this.amountGivenInfluencer;
    }
    public void setAmountGivenInfluencer(String amountGivenInfluencer){
        this.amountGivenInfluencer = amountGivenInfluencer;
    }   
    public String getAmountGivenInfluencerMin(){
        return this.amountGivenInfluencerMin;
    }
    public void setAmountGivenInfluencerMin(String amountGivenInfluencerMin){
        this.amountGivenInfluencerMin = amountGivenInfluencerMin;
    }
    public String getAmountGivenInfluencerMax(){
        return this.amountGivenInfluencerMax;
    }
    public void setAmountGivenInfluencerMax(String amountGivenInfluencerMax){
        this.amountGivenInfluencerMax = amountGivenInfluencerMax;
    }
      
    public String getUsingNumber(){
        return this.usingNumber;
    }
    public void setUsingNumber(String usingNumber){
        this.usingNumber = usingNumber;
    }   
    public String getUsingNumberMin(){
        return this.usingNumberMin;
    }
    public void setUsingNumberMin(String usingNumberMin){
        this.usingNumberMin = usingNumberMin;
    }
    public String getUsingNumberMax(){
        return this.usingNumberMax;
    }
    public void setUsingNumberMax(String usingNumberMax){
        this.usingNumberMax = usingNumberMax;
    }
      
    public String getMaxUsingNumber(){
        return this.maxUsingNumber;
    }
    public void setMaxUsingNumber(String maxUsingNumber){
        this.maxUsingNumber = maxUsingNumber;
    }   
    public String getMaxUsingNumberMin(){
        return this.maxUsingNumberMin;
    }
    public void setMaxUsingNumberMin(String maxUsingNumberMin){
        this.maxUsingNumberMin = maxUsingNumberMin;
    }
    public String getMaxUsingNumberMax(){
        return this.maxUsingNumberMax;
    }
    public void setMaxUsingNumberMax(String maxUsingNumberMax){
        this.maxUsingNumberMax = maxUsingNumberMax;
    }
      

    public PackagingCriteria getPackaging(){
        return this.packaging;
    }

    public void setPackaging(PackagingCriteria packaging){
        this.packaging = packaging;
    }
    public List<PackagingCriteria> getPackagings(){
        return this.packagings;
    }

    public void setPackagings(List<PackagingCriteria> packagings){
        this.packagings = packagings;
    }
    public CouponCriteria getCoupon(){
        return this.coupon;
    }

    public void setCoupon(CouponCriteria coupon){
        this.coupon = coupon;
    }
    public List<CouponCriteria> getCoupons(){
        return this.coupons;
    }

    public void setCoupons(List<CouponCriteria> coupons){
        this.coupons = coupons;
    }
}
