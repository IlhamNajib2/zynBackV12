package  ma.zs.zyn.dao.criteria.core.project;


import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreCriteria;
import ma.zs.zyn.dao.criteria.core.template.DomainTemplateCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ProjectCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String name;
    private String nameLike;
    private LocalDateTime generatedDate;
    private LocalDateTime generatedDateFrom;
    private LocalDateTime generatedDateTo;
    private String yaml;
    private String yamlLike;

    private ProjectStateCriteria projectState ;
    private List<ProjectStateCriteria> projectStates ;
    private InscriptionMembreCriteria inscriptionMembre ;
    private List<InscriptionMembreCriteria> inscriptionMembres ;
    private DomainTemplateCriteria domainTemplate ;
    private List<DomainTemplateCriteria> domainTemplates ;


    public ProjectCriteria(){}

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

    public LocalDateTime getGeneratedDate(){
        return this.generatedDate;
    }
    public void setGeneratedDate(LocalDateTime generatedDate){
        this.generatedDate = generatedDate;
    }
    public LocalDateTime getGeneratedDateFrom(){
        return this.generatedDateFrom;
    }
    public void setGeneratedDateFrom(LocalDateTime generatedDateFrom){
        this.generatedDateFrom = generatedDateFrom;
    }
    public LocalDateTime getGeneratedDateTo(){
        return this.generatedDateTo;
    }
    public void setGeneratedDateTo(LocalDateTime generatedDateTo){
        this.generatedDateTo = generatedDateTo;
    }
    public String getYaml(){
        return this.yaml;
    }
    public void setYaml(String yaml){
        this.yaml = yaml;
    }
    public String getYamlLike(){
        return this.yamlLike;
    }
    public void setYamlLike(String yamlLike){
        this.yamlLike = yamlLike;
    }


    public ProjectStateCriteria getProjectState(){
        return this.projectState;
    }

    public void setProjectState(ProjectStateCriteria projectState){
        this.projectState = projectState;
    }
    public List<ProjectStateCriteria> getProjectStates(){
        return this.projectStates;
    }

    public void setProjectStates(List<ProjectStateCriteria> projectStates){
        this.projectStates = projectStates;
    }
    public InscriptionMembreCriteria getInscriptionMembre(){
        return this.inscriptionMembre;
    }

    public void setInscriptionMembre(InscriptionMembreCriteria inscriptionMembre){
        this.inscriptionMembre = inscriptionMembre;
    }
    public List<InscriptionMembreCriteria> getInscriptionMembres(){
        return this.inscriptionMembres;
    }

    public void setInscriptionMembres(List<InscriptionMembreCriteria> inscriptionMembres){
        this.inscriptionMembres = inscriptionMembres;
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
}
