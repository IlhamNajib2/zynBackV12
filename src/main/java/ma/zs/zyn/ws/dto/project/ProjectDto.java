package  ma.zs.zyn.ws.dto.project;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.ws.dto.inscription.InscriptionMembreDto;
import ma.zs.zyn.ws.dto.template.TemplateDto;
import ma.zs.zyn.ws.dto.template.DomainTemplateDto;
import ma.zs.zyn.ws.dto.template.ProjectTemplateDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto  extends AuditBaseDto {

    private String code  ;
    private String name  ;
    private String generatedDate ;
    private String yaml  ;

    private ProjectStateDto projectState ;
    private InscriptionMembreDto inscriptionMembre ;
    private DomainTemplateDto domainTemplate ;

    private List<ProjectTemplateDto> projectTemplates ;


    public ProjectDto(){
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getGeneratedDate(){
        return this.generatedDate;
    }
    public void setGeneratedDate(String generatedDate){
        this.generatedDate = generatedDate;
    }

    @Log
    public String getYaml(){
        return this.yaml;
    }
    public void setYaml(String yaml){
        this.yaml = yaml;
    }


    public ProjectStateDto getProjectState(){
        return this.projectState;
    }

    public void setProjectState(ProjectStateDto projectState){
        this.projectState = projectState;
    }
    public InscriptionMembreDto getInscriptionMembre(){
        return this.inscriptionMembre;
    }

    public void setInscriptionMembre(InscriptionMembreDto inscriptionMembre){
        this.inscriptionMembre = inscriptionMembre;
    }
    public DomainTemplateDto getDomainTemplate(){
        return this.domainTemplate;
    }

    public void setDomainTemplate(DomainTemplateDto domainTemplate){
        this.domainTemplate = domainTemplate;
    }



    public List<ProjectTemplateDto> getProjectTemplates(){
        return this.projectTemplates;
    }

    public void setProjectTemplates(List<ProjectTemplateDto> projectTemplates){
        this.projectTemplates = projectTemplates;
    }



}
