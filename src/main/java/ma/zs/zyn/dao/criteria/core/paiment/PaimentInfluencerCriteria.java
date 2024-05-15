package  ma.zs.zyn.dao.criteria.core.paiment;


import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.dao.criteria.core.coupon.InfluencerCriteria;
import ma.zs.zyn.dao.criteria.core.project.PaimentInfluencerStateCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PaimentInfluencerCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private String code;
    private String codeLike;
    private String total;
    private String totalMin;
    private String totalMax;
    private String nbrUtilisation;
    private String nbrUtilisationMin;
    private String nbrUtilisationMax;
    private LocalDateTime datePaiement;
    private LocalDateTime datePaiementFrom;
    private LocalDateTime datePaiementTo;

    private InfluencerCriteria influencer ;
    private List<InfluencerCriteria> influencers ;
    private CouponCriteria coupon ;
    private List<CouponCriteria> coupons ;
    private PaimentInfluencerStateCriteria paimentInfluencerState ;
    private List<PaimentInfluencerStateCriteria> paimentInfluencerStates ;


    public PaimentInfluencerCriteria(){}

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getTotal(){
        return this.total;
    }
    public void setTotal(String total){
        this.total = total;
    }   
    public String getTotalMin(){
        return this.totalMin;
    }
    public void setTotalMin(String totalMin){
        this.totalMin = totalMin;
    }
    public String getTotalMax(){
        return this.totalMax;
    }
    public void setTotalMax(String totalMax){
        this.totalMax = totalMax;
    }
      
    public String getNbrUtilisation(){
        return this.nbrUtilisation;
    }
    public void setNbrUtilisation(String nbrUtilisation){
        this.nbrUtilisation = nbrUtilisation;
    }   
    public String getNbrUtilisationMin(){
        return this.nbrUtilisationMin;
    }
    public void setNbrUtilisationMin(String nbrUtilisationMin){
        this.nbrUtilisationMin = nbrUtilisationMin;
    }
    public String getNbrUtilisationMax(){
        return this.nbrUtilisationMax;
    }
    public void setNbrUtilisationMax(String nbrUtilisationMax){
        this.nbrUtilisationMax = nbrUtilisationMax;
    }
      
    public LocalDateTime getDatePaiement(){
        return this.datePaiement;
    }
    public void setDatePaiement(LocalDateTime datePaiement){
        this.datePaiement = datePaiement;
    }
    public LocalDateTime getDatePaiementFrom(){
        return this.datePaiementFrom;
    }
    public void setDatePaiementFrom(LocalDateTime datePaiementFrom){
        this.datePaiementFrom = datePaiementFrom;
    }
    public LocalDateTime getDatePaiementTo(){
        return this.datePaiementTo;
    }
    public void setDatePaiementTo(LocalDateTime datePaiementTo){
        this.datePaiementTo = datePaiementTo;
    }

    public InfluencerCriteria getInfluencer(){
        return this.influencer;
    }

    public void setInfluencer(InfluencerCriteria influencer){
        this.influencer = influencer;
    }
    public List<InfluencerCriteria> getInfluencers(){
        return this.influencers;
    }

    public void setInfluencers(List<InfluencerCriteria> influencers){
        this.influencers = influencers;
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
    public PaimentInfluencerStateCriteria getPaimentInfluencerState(){
        return this.paimentInfluencerState;
    }

    public void setPaimentInfluencerState(PaimentInfluencerStateCriteria paimentInfluencerState){
        this.paimentInfluencerState = paimentInfluencerState;
    }
    public List<PaimentInfluencerStateCriteria> getPaimentInfluencerStates(){
        return this.paimentInfluencerStates;
    }

    public void setPaimentInfluencerStates(List<PaimentInfluencerStateCriteria> paimentInfluencerStates){
        this.paimentInfluencerStates = paimentInfluencerStates;
    }
}
