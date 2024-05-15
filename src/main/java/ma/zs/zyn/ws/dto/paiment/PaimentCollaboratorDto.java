package  ma.zs.zyn.ws.dto.paiment;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.coupon.CouponDetailDto;
import ma.zs.zyn.ws.dto.project.PaimentCollaboratorStateDto;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaimentCollaboratorDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private String code  ;
    private BigDecimal amountToPaid  ;
    private BigDecimal total  ;
    private BigDecimal discount  ;
    private BigDecimal remaining  ;
    private String paiementDate ;

    private CouponDetailDto couponDetail ;
    private InscriptionCollaboratorDto inscriptionCollaborator ;
    private PaimentCollaboratorStateDto paimentCollaboratorState ;



    public PaimentCollaboratorDto(){
        super();
    }



    @Log
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public BigDecimal getAmountToPaid(){
        return this.amountToPaid;
    }
    public void setAmountToPaid(BigDecimal amountToPaid){
        this.amountToPaid = amountToPaid;
    }

    @Log
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }

    @Log
    public BigDecimal getDiscount(){
        return this.discount;
    }
    public void setDiscount(BigDecimal discount){
        this.discount = discount;
    }

    @Log
    public BigDecimal getRemaining(){
        return this.remaining;
    }
    public void setRemaining(BigDecimal remaining){
        this.remaining = remaining;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getPaiementDate(){
        return this.paiementDate;
    }
    public void setPaiementDate(String paiementDate){
        this.paiementDate = paiementDate;
    }


    public CouponDetailDto getCouponDetail(){
        return this.couponDetail;
    }

    public void setCouponDetail(CouponDetailDto couponDetail){
        this.couponDetail = couponDetail;
    }
    public InscriptionCollaboratorDto getInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }

    public void setInscriptionCollaborator(InscriptionCollaboratorDto inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }
    public PaimentCollaboratorStateDto getPaimentCollaboratorState(){
        return this.paimentCollaboratorState;
    }

    public void setPaimentCollaboratorState(PaimentCollaboratorStateDto paimentCollaboratorState){
        this.paimentCollaboratorState = paimentCollaboratorState;
    }






}
