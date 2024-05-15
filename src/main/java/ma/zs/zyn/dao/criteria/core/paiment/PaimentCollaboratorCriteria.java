package  ma.zs.zyn.dao.criteria.core.paiment;


import ma.zs.zyn.dao.criteria.core.coupon.CouponDetailCriteria;
import ma.zs.zyn.dao.criteria.core.project.PaimentCollaboratorStateCriteria;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PaimentCollaboratorCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private String code;
    private String codeLike;
    private String amountToPaid;
    private String amountToPaidMin;
    private String amountToPaidMax;
    private String total;
    private String totalMin;
    private String totalMax;
    private String discount;
    private String discountMin;
    private String discountMax;
    private String remaining;
    private String remainingMin;
    private String remainingMax;
    private LocalDateTime paiementDate;
    private LocalDateTime paiementDateFrom;
    private LocalDateTime paiementDateTo;

    private CouponDetailCriteria couponDetail ;
    private List<CouponDetailCriteria> couponDetails ;
    private InscriptionCollaboratorCriteria inscriptionCollaborator ;
    private List<InscriptionCollaboratorCriteria> inscriptionCollaborators ;
    private PaimentCollaboratorStateCriteria paimentCollaboratorState ;
    private List<PaimentCollaboratorStateCriteria> paimentCollaboratorStates ;


    public PaimentCollaboratorCriteria(){}

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

    public String getAmountToPaid(){
        return this.amountToPaid;
    }
    public void setAmountToPaid(String amountToPaid){
        this.amountToPaid = amountToPaid;
    }   
    public String getAmountToPaidMin(){
        return this.amountToPaidMin;
    }
    public void setAmountToPaidMin(String amountToPaidMin){
        this.amountToPaidMin = amountToPaidMin;
    }
    public String getAmountToPaidMax(){
        return this.amountToPaidMax;
    }
    public void setAmountToPaidMax(String amountToPaidMax){
        this.amountToPaidMax = amountToPaidMax;
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
      
    public String getRemaining(){
        return this.remaining;
    }
    public void setRemaining(String remaining){
        this.remaining = remaining;
    }   
    public String getRemainingMin(){
        return this.remainingMin;
    }
    public void setRemainingMin(String remainingMin){
        this.remainingMin = remainingMin;
    }
    public String getRemainingMax(){
        return this.remainingMax;
    }
    public void setRemainingMax(String remainingMax){
        this.remainingMax = remainingMax;
    }
      
    public LocalDateTime getPaiementDate(){
        return this.paiementDate;
    }
    public void setPaiementDate(LocalDateTime paiementDate){
        this.paiementDate = paiementDate;
    }
    public LocalDateTime getPaiementDateFrom(){
        return this.paiementDateFrom;
    }
    public void setPaiementDateFrom(LocalDateTime paiementDateFrom){
        this.paiementDateFrom = paiementDateFrom;
    }
    public LocalDateTime getPaiementDateTo(){
        return this.paiementDateTo;
    }
    public void setPaiementDateTo(LocalDateTime paiementDateTo){
        this.paiementDateTo = paiementDateTo;
    }

    public CouponDetailCriteria getCouponDetail(){
        return this.couponDetail;
    }

    public void setCouponDetail(CouponDetailCriteria couponDetail){
        this.couponDetail = couponDetail;
    }
    public List<CouponDetailCriteria> getCouponDetails(){
        return this.couponDetails;
    }

    public void setCouponDetails(List<CouponDetailCriteria> couponDetails){
        this.couponDetails = couponDetails;
    }
    public InscriptionCollaboratorCriteria getInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }

    public void setInscriptionCollaborator(InscriptionCollaboratorCriteria inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }
    public List<InscriptionCollaboratorCriteria> getInscriptionCollaborators(){
        return this.inscriptionCollaborators;
    }

    public void setInscriptionCollaborators(List<InscriptionCollaboratorCriteria> inscriptionCollaborators){
        this.inscriptionCollaborators = inscriptionCollaborators;
    }
    public PaimentCollaboratorStateCriteria getPaimentCollaboratorState(){
        return this.paimentCollaboratorState;
    }

    public void setPaimentCollaboratorState(PaimentCollaboratorStateCriteria paimentCollaboratorState){
        this.paimentCollaboratorState = paimentCollaboratorState;
    }
    public List<PaimentCollaboratorStateCriteria> getPaimentCollaboratorStates(){
        return this.paimentCollaboratorStates;
    }

    public void setPaimentCollaboratorStates(List<PaimentCollaboratorStateCriteria> paimentCollaboratorStates){
        this.paimentCollaboratorStates = paimentCollaboratorStates;
    }
}
