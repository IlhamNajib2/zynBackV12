package ma.zs.zyn.bean.core.coupon;

import java.util.Objects;





import ma.zs.zyn.bean.core.packaging.Packaging;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "coupon_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="coupon_detail_seq",sequenceName="coupon_detail_seq",allocationSize=1, initialValue = 1)
public class CouponDetail  extends BaseEntity     {

    private Long id;



    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal amountGivenInfluencer = BigDecimal.ZERO;

    private BigDecimal usingNumber = BigDecimal.ZERO;

    private BigDecimal maxUsingNumber = BigDecimal.ZERO;

    private Packaging packaging ;
    private Coupon coupon ;


    public CouponDetail(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="coupon_detail_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging")
    public Packaging getPackaging(){
        return this.packaging;
    }
    public void setPackaging(Packaging packaging){
        this.packaging = packaging;
    }
    public BigDecimal getDiscount(){
        return this.discount;
    }
    public void setDiscount(BigDecimal discount){
        this.discount = discount;
    }
    public BigDecimal getAmountGivenInfluencer(){
        return this.amountGivenInfluencer;
    }
    public void setAmountGivenInfluencer(BigDecimal amountGivenInfluencer){
        this.amountGivenInfluencer = amountGivenInfluencer;
    }
    public BigDecimal getUsingNumber(){
        return this.usingNumber;
    }
    public void setUsingNumber(BigDecimal usingNumber){
        this.usingNumber = usingNumber;
    }
    public BigDecimal getMaxUsingNumber(){
        return this.maxUsingNumber;
    }
    public void setMaxUsingNumber(BigDecimal maxUsingNumber){
        this.maxUsingNumber = maxUsingNumber;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon")
    public Coupon getCoupon(){
        return this.coupon;
    }
    public void setCoupon(Coupon coupon){
        this.coupon = coupon;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponDetail couponDetail = (CouponDetail) o;
        return id != null && id.equals(couponDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

