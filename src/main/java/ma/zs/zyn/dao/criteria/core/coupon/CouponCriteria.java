package  ma.zs.zyn.dao.criteria.core.coupon;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CouponCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private LocalDateTime dateStart;
    private LocalDateTime dateStartFrom;
    private LocalDateTime dateStartTo;
    private LocalDateTime dateEnd;
    private LocalDateTime dateEndFrom;
    private LocalDateTime dateEndTo;
    private String name;
    private String nameLike;

    private InfluencerCriteria influencer ;
    private List<InfluencerCriteria> influencers ;


    public CouponCriteria(){}

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

    public LocalDateTime getDateStart(){
        return this.dateStart;
    }
    public void setDateStart(LocalDateTime dateStart){
        this.dateStart = dateStart;
    }
    public LocalDateTime getDateStartFrom(){
        return this.dateStartFrom;
    }
    public void setDateStartFrom(LocalDateTime dateStartFrom){
        this.dateStartFrom = dateStartFrom;
    }
    public LocalDateTime getDateStartTo(){
        return this.dateStartTo;
    }
    public void setDateStartTo(LocalDateTime dateStartTo){
        this.dateStartTo = dateStartTo;
    }
    public LocalDateTime getDateEnd(){
        return this.dateEnd;
    }
    public void setDateEnd(LocalDateTime dateEnd){
        this.dateEnd = dateEnd;
    }
    public LocalDateTime getDateEndFrom(){
        return this.dateEndFrom;
    }
    public void setDateEndFrom(LocalDateTime dateEndFrom){
        this.dateEndFrom = dateEndFrom;
    }
    public LocalDateTime getDateEndTo(){
        return this.dateEndTo;
    }
    public void setDateEndTo(LocalDateTime dateEndTo){
        this.dateEndTo = dateEndTo;
    }
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
}
