package  ma.zs.zyn.dao.criteria.core.template;


import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;

public class ProjectTemplateCriteria extends  BaseCriteria  {


    private TemplateCriteria template ;
    private List<TemplateCriteria> templates ;
    private ProjectCriteria project ;
    private List<ProjectCriteria> projects ;


    public ProjectTemplateCriteria(){}


    public TemplateCriteria getTemplate(){
        return this.template;
    }

    public void setTemplate(TemplateCriteria template){
        this.template = template;
    }
    public List<TemplateCriteria> getTemplates(){
        return this.templates;
    }

    public void setTemplates(List<TemplateCriteria> templates){
        this.templates = templates;
    }
    public ProjectCriteria getProject(){
        return this.project;
    }

    public void setProject(ProjectCriteria project){
        this.project = project;
    }
    public List<ProjectCriteria> getProjects(){
        return this.projects;
    }

    public void setProjects(List<ProjectCriteria> projects){
        this.projects = projects;
    }
}
