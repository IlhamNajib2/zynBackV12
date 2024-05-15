package  ma.zs.zyn.dao.criteria.core.packaging;


import ma.zs.zyn.dao.criteria.core.category.CategoryPackagingCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PackagingCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String code;
    private String codeLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime dateStart;
    private LocalDateTime dateStartFrom;
    private LocalDateTime dateStartTo;
    private LocalDateTime dateEnd;
    private LocalDateTime dateEndFrom;
    private LocalDateTime dateEndTo;
    private String price;
    private String priceMin;
    private String priceMax;
    private String maxEntity;
    private String maxEntityMin;
    private String maxEntityMax;
    private String maxProjet;
    private String maxProjetMin;
    private String maxProjetMax;
    private String maxAttribut;
    private String maxAttributMin;
    private String maxAttributMax;
    private String maxIndicator;
    private String maxIndicatorMin;
    private String maxIndicatorMax;

    private CategoryPackagingCriteria categoryPackaging ;
    private List<CategoryPackagingCriteria> categoryPackagings ;


    public PackagingCriteria(){}

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
    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }   
    public String getPriceMin(){
        return this.priceMin;
    }
    public void setPriceMin(String priceMin){
        this.priceMin = priceMin;
    }
    public String getPriceMax(){
        return this.priceMax;
    }
    public void setPriceMax(String priceMax){
        this.priceMax = priceMax;
    }
      
    public String getMaxEntity(){
        return this.maxEntity;
    }
    public void setMaxEntity(String maxEntity){
        this.maxEntity = maxEntity;
    }   
    public String getMaxEntityMin(){
        return this.maxEntityMin;
    }
    public void setMaxEntityMin(String maxEntityMin){
        this.maxEntityMin = maxEntityMin;
    }
    public String getMaxEntityMax(){
        return this.maxEntityMax;
    }
    public void setMaxEntityMax(String maxEntityMax){
        this.maxEntityMax = maxEntityMax;
    }
      
    public String getMaxProjet(){
        return this.maxProjet;
    }
    public void setMaxProjet(String maxProjet){
        this.maxProjet = maxProjet;
    }   
    public String getMaxProjetMin(){
        return this.maxProjetMin;
    }
    public void setMaxProjetMin(String maxProjetMin){
        this.maxProjetMin = maxProjetMin;
    }
    public String getMaxProjetMax(){
        return this.maxProjetMax;
    }
    public void setMaxProjetMax(String maxProjetMax){
        this.maxProjetMax = maxProjetMax;
    }
      
    public String getMaxAttribut(){
        return this.maxAttribut;
    }
    public void setMaxAttribut(String maxAttribut){
        this.maxAttribut = maxAttribut;
    }   
    public String getMaxAttributMin(){
        return this.maxAttributMin;
    }
    public void setMaxAttributMin(String maxAttributMin){
        this.maxAttributMin = maxAttributMin;
    }
    public String getMaxAttributMax(){
        return this.maxAttributMax;
    }
    public void setMaxAttributMax(String maxAttributMax){
        this.maxAttributMax = maxAttributMax;
    }
      
    public String getMaxIndicator(){
        return this.maxIndicator;
    }
    public void setMaxIndicator(String maxIndicator){
        this.maxIndicator = maxIndicator;
    }   
    public String getMaxIndicatorMin(){
        return this.maxIndicatorMin;
    }
    public void setMaxIndicatorMin(String maxIndicatorMin){
        this.maxIndicatorMin = maxIndicatorMin;
    }
    public String getMaxIndicatorMax(){
        return this.maxIndicatorMax;
    }
    public void setMaxIndicatorMax(String maxIndicatorMax){
        this.maxIndicatorMax = maxIndicatorMax;
    }
      

    public CategoryPackagingCriteria getCategoryPackaging(){
        return this.categoryPackaging;
    }

    public void setCategoryPackaging(CategoryPackagingCriteria categoryPackaging){
        this.categoryPackaging = categoryPackaging;
    }
    public List<CategoryPackagingCriteria> getCategoryPackagings(){
        return this.categoryPackagings;
    }

    public void setCategoryPackagings(List<CategoryPackagingCriteria> categoryPackagings){
        this.categoryPackagings = categoryPackagings;
    }
}
