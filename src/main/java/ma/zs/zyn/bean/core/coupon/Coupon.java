package ma.zs.zyn.bean.core.coupon;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.packaging.Packaging;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coupon")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="coupon_seq",sequenceName="coupon_seq",allocationSize=1, initialValue = 1)
public class Coupon  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    private LocalDateTime dateStart ;

    private LocalDateTime dateEnd ;

    @Column(length = 500)
    private String name;

    private Influencer influencer ;

    private List<CouponDetail> couponDetails ;

    public Coupon(){
        super();
    }

    public Coupon(Long id,String name){
        this.id = id;
        this.name = name ;
    }
    public Coupon(String name){
        this.name = name ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="coupon_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public LocalDateTime getDateStart(){
        return this.dateStart;
    }
    public void setDateStart(LocalDateTime dateStart){
        this.dateStart = dateStart;
    }
    public LocalDateTime getDateEnd(){
        return this.dateEnd;
    }
    public void setDateEnd(LocalDateTime dateEnd){
        this.dateEnd = dateEnd;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    @OneToMany(mappedBy = "coupon")
    public List<CouponDetail> getCouponDetails(){
        return this.couponDetails;
    }

    public void setCouponDetails(List<CouponDetail> couponDetails){
        this.couponDetails = couponDetails;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer")
    public Influencer getInfluencer(){
        return this.influencer;
    }
    public void setInfluencer(Influencer influencer){
        this.influencer = influencer;
    }

    @Transient
    public String getLabel() {
        label = name;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return id != null && id.equals(coupon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

