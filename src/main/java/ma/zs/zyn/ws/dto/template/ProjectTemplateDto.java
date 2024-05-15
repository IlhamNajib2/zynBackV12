package  ma.zs.zyn.ws.dto.template;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.zyn.ws.dto.project.ProjectDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectTemplateDto  extends AuditBaseDto {


    private TemplateDto template ;
    private ProjectDto project ;



    public ProjectTemplateDto(){
        super();
    }




    public TemplateDto getTemplate(){
        return this.template;
    }

    public void setTemplate(TemplateDto template){
        this.template = template;
    }
    public ProjectDto getProject(){
        return this.project;
    }

    public void setProject(ProjectDto project){
        this.project = project;
    }






}
