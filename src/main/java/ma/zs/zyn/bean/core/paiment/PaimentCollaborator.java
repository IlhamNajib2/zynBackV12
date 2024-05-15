package ma.zs.zyn.bean.core.paiment;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.coupon.CouponDetail;
import ma.zs.zyn.bean.core.project.PaimentCollaboratorState;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "paiment_collaborator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="paiment_collaborator_seq",sequenceName="paiment_collaborator_seq",allocationSize=1, initialValue = 1)
public class PaimentCollaborator  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String code;

    private BigDecimal amountToPaid = BigDecimal.ZERO;

    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal remaining = BigDecimal.ZERO;

    private LocalDateTime paiementDate ;

    private CouponDetail couponDetail ;
    private InscriptionCollaborator inscriptionCollaborator ;
    private PaimentCollaboratorState paimentCollaboratorState ;


    public PaimentCollaborator(){
        super();
    }

    public PaimentCollaborator(Long id,String name){
        this.id = id;
        this.name = name ;
    }
    public PaimentCollaborator(String name){
        this.name = name ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="paiment_collaborator_seq")
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
    @JoinColumn(name = "coupon_detail")
    public CouponDetail getCouponDetail(){
        return this.couponDetail;
    }
    public void setCouponDetail(CouponDetail couponDetail){
        this.couponDetail = couponDetail;
    }
    public BigDecimal getAmountToPaid(){
        return this.amountToPaid;
    }
    public void setAmountToPaid(BigDecimal amountToPaid){
        this.amountToPaid = amountToPaid;
    }
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }
    public BigDecimal getDiscount(){
        return this.discount;
    }
    public void setDiscount(BigDecimal discount){
        this.discount = discount;
    }
    public BigDecimal getRemaining(){
        return this.remaining;
    }
    public void setRemaining(BigDecimal remaining){
        this.remaining = remaining;
    }
    public LocalDateTime getPaiementDate(){
        return this.paiementDate;
    }
    public void setPaiementDate(LocalDateTime paiementDate){
        this.paiementDate = paiementDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_collaborator")
    public InscriptionCollaborator getInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }
    public void setInscriptionCollaborator(InscriptionCollaborator inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paiment_collaborator_state")
    public PaimentCollaboratorState getPaimentCollaboratorState(){
        return this.paimentCollaboratorState;
    }
    public void setPaimentCollaboratorState(PaimentCollaboratorState paimentCollaboratorState){
        this.paimentCollaboratorState = paimentCollaboratorState;
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
        PaimentCollaborator paimentCollaborator = (PaimentCollaborator) o;
        return id != null && id.equals(paimentCollaborator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

