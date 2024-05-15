package  ma.zs.zyn.dao.criteria.core.template;


import ma.zs.zyn.dao.criteria.core.collaborator.MemberCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class TemplateCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String name;
    private String nameLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime addingDate;
    private LocalDateTime addingDateFrom;
    private LocalDateTime addingDateTo;
    private LocalDateTime lastUpdateDate;
    private LocalDateTime lastUpdateDateFrom;
    private LocalDateTime lastUpdateDateTo;
    private String templateTags;
    private String templateTagsLike;
    private String price;
    private String priceMin;
    private String priceMax;

    private CategoryTemplateCriteria categoryTemplate ;
    private List<CategoryTemplateCriteria> categoryTemplates ;
    private TypeTemplateCriteria typeTemplate ;
    private List<TypeTemplateCriteria> typeTemplates ;
    private LevelTemplateCriteria levelTemplate ;
    private List<LevelTemplateCriteria> levelTemplates ;
    private DomainTemplateCriteria domainTemplate ;
    private List<DomainTemplateCriteria> domainTemplates ;
    private MemberCriteria member ;
    private List<MemberCriteria> members ;
    private TechnologyCriteria technology ;
    private List<TechnologyCriteria> technologys ;


    public TemplateCriteria(){}

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

    public LocalDateTime getAddingDate(){
        return this.addingDate;
    }
    public void setAddingDate(LocalDateTime addingDate){
        this.addingDate = addingDate;
    }
    public LocalDateTime getAddingDateFrom(){
        return this.addingDateFrom;
    }
    public void setAddingDateFrom(LocalDateTime addingDateFrom){
        this.addingDateFrom = addingDateFrom;
    }
    public LocalDateTime getAddingDateTo(){
        return this.addingDateTo;
    }
    public void setAddingDateTo(LocalDateTime addingDateTo){
        this.addingDateTo = addingDateTo;
    }
    public LocalDateTime getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(LocalDateTime lastUpdateDate){
        this.lastUpdateDate = lastUpdateDate;
    }
    public LocalDateTime getLastUpdateDateFrom(){
        return this.lastUpdateDateFrom;
    }
    public void setLastUpdateDateFrom(LocalDateTime lastUpdateDateFrom){
        this.lastUpdateDateFrom = lastUpdateDateFrom;
    }
    public LocalDateTime getLastUpdateDateTo(){
        return this.lastUpdateDateTo;
    }
    public void setLastUpdateDateTo(LocalDateTime lastUpdateDateTo){
        this.lastUpdateDateTo = lastUpdateDateTo;
    }
    public String getTemplateTags(){
        return this.templateTags;
    }
    public void setTemplateTags(String templateTags){
        this.templateTags = templateTags;
    }
    public String getTemplateTagsLike(){
        return this.templateTagsLike;
    }
    public void setTemplateTagsLike(String templateTagsLike){
        this.templateTagsLike = templateTagsLike;
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
      

    public CategoryTemplateCriteria getCategoryTemplate(){
        return this.categoryTemplate;
    }

    public void setCategoryTemplate(CategoryTemplateCriteria categoryTemplate){
        this.categoryTemplate = categoryTemplate;
    }
    public List<CategoryTemplateCriteria> getCategoryTemplates(){
        return this.categoryTemplates;
    }

    public void setCategoryTemplates(List<CategoryTemplateCriteria> categoryTemplates){
        this.categoryTemplates = categoryTemplates;
    }
    public TypeTemplateCriteria getTypeTemplate(){
        return this.typeTemplate;
    }

    public void setTypeTemplate(TypeTemplateCriteria typeTemplate){
        this.typeTemplate = typeTemplate;
    }
    public List<TypeTemplateCriteria> getTypeTemplates(){
        return this.typeTemplates;
    }

    public void setTypeTemplates(List<TypeTemplateCriteria> typeTemplates){
        this.typeTemplates = typeTemplates;
    }
    public LevelTemplateCriteria getLevelTemplate(){
        return this.levelTemplate;
    }

    public void setLevelTemplate(LevelTemplateCriteria levelTemplate){
        this.levelTemplate = levelTemplate;
    }
    public List<LevelTemplateCriteria> getLevelTemplates(){
        return this.levelTemplates;
    }

    public void setLevelTemplates(List<LevelTemplateCriteria> levelTemplates){
        this.levelTemplates = levelTemplates;
    }
    public DomainTemplateCriteria getDomainTemplate(){
        return this.domainTemplate;
    }

    public void setDomainTemplate(DomainTemplateCriteria domainTemplate){
        this.domainTemplate = domainTemplate;
    }
    public List<DomainTemplateCriteria> getDomainTemplates(){
        return this.domainTemplates;
    }

    public void setDomainTemplates(List<DomainTemplateCriteria> domainTemplates){
        this.domainTemplates = domainTemplates;
    }
    public MemberCriteria getMember(){
        return this.member;
    }

    public void setMember(MemberCriteria member){
        this.member = member;
    }
    public List<MemberCriteria> getMembers(){
        return this.members;
    }

    public void setMembers(List<MemberCriteria> members){
        this.members = members;
    }
    public TechnologyCriteria getTechnology(){
        return this.technology;
    }

    public void setTechnology(TechnologyCriteria technology){
        this.technology = technology;
    }
    public List<TechnologyCriteria> getTechnologys(){
        return this.technologys;
    }

    public void setTechnologys(List<TechnologyCriteria> technologys){
        this.technologys = technologys;
    }
}
