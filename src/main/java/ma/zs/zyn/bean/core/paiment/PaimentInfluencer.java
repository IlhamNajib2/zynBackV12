package ma.zs.zyn.bean.core.paiment;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.bean.core.coupon.Influencer;
import ma.zs.zyn.bean.core.project.PaimentInfluencerState;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "paiment_influencer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="paiment_influencer_seq",sequenceName="paiment_influencer_seq",allocationSize=1, initialValue = 1)
public class PaimentInfluencer  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String code;

    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal nbrUtilisation = BigDecimal.ZERO;

    private LocalDateTime datePaiement ;

    private Influencer influencer ;
    private Coupon coupon ;
    private PaimentInfluencerState paimentInfluencerState ;


    public PaimentInfluencer(){
        super();
    }

    public PaimentInfluencer(Long id,String name){
        this.id = id;
        this.name = name ;
    }
    public PaimentInfluencer(String name){
        this.name = name ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="paiment_influencer_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "influencer")
    public Influencer getInfluencer(){
        return this.influencer;
    }
    public void setInfluencer(Influencer influencer){
        this.influencer = influencer;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon")
    public Coupon getCoupon(){
        return this.coupon;
    }
    public void setCoupon(Coupon coupon){
        this.coupon = coupon;
    }
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }
    public BigDecimal getNbrUtilisation(){
        return this.nbrUtilisation;
    }
    public void setNbrUtilisation(BigDecimal nbrUtilisation){
        this.nbrUtilisation = nbrUtilisation;
    }
    public LocalDateTime getDatePaiement(){
        return this.datePaiement;
    }
    public void setDatePaiement(LocalDateTime datePaiement){
        this.datePaiement = datePaiement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paiment_influencer_state")
    public PaimentInfluencerState getPaimentInfluencerState(){
        return this.paimentInfluencerState;
    }
    public void setPaimentInfluencerState(PaimentInfluencerState paimentInfluencerState){
        this.paimentInfluencerState = paimentInfluencerState;
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
        PaimentInfluencer paimentInfluencer = (PaimentInfluencer) o;
        return id != null && id.equals(paimentInfluencer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

