package  ma.zs.zyn.ws.dto.coupon;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.ws.dto.packaging.PackagingDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponDto  extends AuditBaseDto {

    private String code  ;
    private String dateStart ;
    private String dateEnd ;
    private String name  ;

    private InfluencerDto influencer ;

    private List<CouponDetailDto> couponDetails ;


    public CouponDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateStart(){
        return this.dateStart;
    }
    public void setDateStart(String dateStart){
        this.dateStart = dateStart;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateEnd(){
        return this.dateEnd;
    }
    public void setDateEnd(String dateEnd){
        this.dateEnd = dateEnd;
    }

    @Log
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public InfluencerDto getInfluencer(){
        return this.influencer;
    }

    public void setInfluencer(InfluencerDto influencer){
        this.influencer = influencer;
    }



    public List<CouponDetailDto> getCouponDetails(){
        return this.couponDetails;
    }

    public void setCouponDetails(List<CouponDetailDto> couponDetails){
        this.couponDetails = couponDetails;
    }



}
