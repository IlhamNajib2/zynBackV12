package  ma.zs.zyn.ws.dto.template;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.collaborator.MemberDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateDto  extends AuditBaseDto {

    private String code  ;
    private String name  ;
    private String description  ;
    private String addingDate ;
    private String lastUpdateDate ;
    private String templateTags  ;
    private BigDecimal price  ;

    private CategoryTemplateDto categoryTemplate ;
    private TypeTemplateDto typeTemplate ;
    private LevelTemplateDto levelTemplate ;
    private DomainTemplateDto domainTemplate ;
    private MemberDto member ;
    private TechnologyDto technology ;



    public TemplateDto(){
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getAddingDate(){
        return this.addingDate;
    }
    public void setAddingDate(String addingDate){
        this.addingDate = addingDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(String lastUpdateDate){
        this.lastUpdateDate = lastUpdateDate;
    }

    @Log
    public String getTemplateTags(){
        return this.templateTags;
    }
    public void setTemplateTags(String templateTags){
        this.templateTags = templateTags;
    }

    @Log
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }


    public CategoryTemplateDto getCategoryTemplate(){
        return this.categoryTemplate;
    }

    public void setCategoryTemplate(CategoryTemplateDto categoryTemplate){
        this.categoryTemplate = categoryTemplate;
    }
    public TypeTemplateDto getTypeTemplate(){
        return this.typeTemplate;
    }

    public void setTypeTemplate(TypeTemplateDto typeTemplate){
        this.typeTemplate = typeTemplate;
    }
    public LevelTemplateDto getLevelTemplate(){
        return this.levelTemplate;
    }

    public void setLevelTemplate(LevelTemplateDto levelTemplate){
        this.levelTemplate = levelTemplate;
    }
    public DomainTemplateDto getDomainTemplate(){
        return this.domainTemplate;
    }

    public void setDomainTemplate(DomainTemplateDto domainTemplate){
        this.domainTemplate = domainTemplate;
    }
    public MemberDto getMember(){
        return this.member;
    }

    public void setMember(MemberDto member){
        this.member = member;
    }
    public TechnologyDto getTechnology(){
        return this.technology;
    }

    public void setTechnology(TechnologyDto technology){
        this.technology = technology;
    }






}
