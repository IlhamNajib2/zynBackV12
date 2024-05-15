package  ma.zs.zyn.ws.dto.paiment;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.coupon.CouponDto;
import ma.zs.zyn.ws.dto.coupon.InfluencerDto;
import ma.zs.zyn.ws.dto.project.PaimentInfluencerStateDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaimentInfluencerDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private String code  ;
    private BigDecimal total  ;
    private BigDecimal nbrUtilisation  ;
    private String datePaiement ;

    private InfluencerDto influencer ;
    private CouponDto coupon ;
    private PaimentInfluencerStateDto paimentInfluencerState ;



    public PaimentInfluencerDto(){
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
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }

    @Log
    public BigDecimal getNbrUtilisation(){
        return this.nbrUtilisation;
    }
    public void setNbrUtilisation(BigDecimal nbrUtilisation){
        this.nbrUtilisation = nbrUtilisation;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDatePaiement(){
        return this.datePaiement;
    }
    public void setDatePaiement(String datePaiement){
        this.datePaiement = datePaiement;
    }


    public InfluencerDto getInfluencer(){
        return this.influencer;
    }

    public void setInfluencer(InfluencerDto influencer){
        this.influencer = influencer;
    }
    public CouponDto getCoupon(){
        return this.coupon;
    }

    public void setCoupon(CouponDto coupon){
        this.coupon = coupon;
    }
    public PaimentInfluencerStateDto getPaimentInfluencerState(){
        return this.paimentInfluencerState;
    }

    public void setPaimentInfluencerState(PaimentInfluencerStateDto paimentInfluencerState){
        this.paimentInfluencerState = paimentInfluencerState;
    }






}
