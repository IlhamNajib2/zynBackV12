package  ma.zs.zyn.dao.criteria.core.template;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;

public class TechnologyCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String name;
    private String nameLike;
    private String logo;
    private String logoLike;

    private TypeTemplateCriteria typeTemplate ;
    private List<TypeTemplateCriteria> typeTemplates ;


    public TechnologyCriteria(){}

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

    public String getLogo(){
        return this.logo;
    }
    public void setLogo(String logo){
        this.logo = logo;
    }
    public String getLogoLike(){
        return this.logoLike;
    }
    public void setLogoLike(String logoLike){
        this.logoLike = logoLike;
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
}
