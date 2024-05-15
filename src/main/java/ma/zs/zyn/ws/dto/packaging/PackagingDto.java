package  ma.zs.zyn.ws.dto.packaging;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.category.CategoryPackagingDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PackagingDto  extends AuditBaseDto {

    private String name  ;
    private String code  ;
    private String description  ;
    private String dateStart ;
    private String dateEnd ;
    private BigDecimal price  ;
    private BigDecimal maxEntity  ;
    private BigDecimal maxProjet  ;
    private BigDecimal maxAttribut  ;
    private BigDecimal maxIndicator  ;

    private CategoryPackagingDto categoryPackaging ;



    public PackagingDto(){
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
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
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
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    @Log
    public BigDecimal getMaxEntity(){
        return this.maxEntity;
    }
    public void setMaxEntity(BigDecimal maxEntity){
        this.maxEntity = maxEntity;
    }

    @Log
    public BigDecimal getMaxProjet(){
        return this.maxProjet;
    }
    public void setMaxProjet(BigDecimal maxProjet){
        this.maxProjet = maxProjet;
    }

    @Log
    public BigDecimal getMaxAttribut(){
        return this.maxAttribut;
    }
    public void setMaxAttribut(BigDecimal maxAttribut){
        this.maxAttribut = maxAttribut;
    }

    @Log
    public BigDecimal getMaxIndicator(){
        return this.maxIndicator;
    }
    public void setMaxIndicator(BigDecimal maxIndicator){
        this.maxIndicator = maxIndicator;
    }


    public CategoryPackagingDto getCategoryPackaging(){
        return this.categoryPackaging;
    }

    public void setCategoryPackaging(CategoryPackagingDto categoryPackaging){
        this.categoryPackaging = categoryPackaging;
    }






}
