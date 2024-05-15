package  ma.zs.zyn.ws.converter.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.project.ProjectConverter;
import ma.zs.zyn.ws.converter.template.TemplateConverter;

import ma.zs.zyn.bean.core.project.Project;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.ws.dto.template.ProjectTemplateDto;

@Component
public class ProjectTemplateConverter {

    @Autowired
    private ProjectConverter projectConverter ;
    @Autowired
    private TemplateConverter templateConverter ;
    private boolean template;
    private boolean project;

    public  ProjectTemplateConverter() {
        initObject(true);
    }


    public ProjectTemplate toItem(ProjectTemplateDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectTemplate item = new ProjectTemplate();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.template && dto.getTemplate()!=null)
                item.setTemplate(templateConverter.toItem(dto.getTemplate())) ;

            if(dto.getProject() != null && dto.getProject().getId() != null){
                item.setProject(new Project());
                item.getProject().setId(dto.getProject().getId());
                item.getProject().setCode(dto.getProject().getCode());
            }




        return item;
        }
    }


    public ProjectTemplateDto toDto(ProjectTemplate item) {
        if (item == null) {
            return null;
        } else {
            ProjectTemplateDto dto = new ProjectTemplateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.template && item.getTemplate()!=null) {
                dto.setTemplate(templateConverter.toDto(item.getTemplate())) ;

            }
            if(this.project && item.getProject()!=null) {
                dto.setProject(projectConverter.toDto(item.getProject())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.template = value;
        this.project = value;
    }
	
    public List<ProjectTemplate> toItem(List<ProjectTemplateDto> dtos) {
        List<ProjectTemplate> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectTemplateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectTemplateDto> toDto(List<ProjectTemplate> items) {
        List<ProjectTemplateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectTemplate item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectTemplateDto dto, ProjectTemplate t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTemplate() != null)
        templateConverter.copy(dto.getTemplate(), t.getTemplate());
        if (dto.getProject() != null)
        projectConverter.copy(dto.getProject(), t.getProject());
    }

    public List<ProjectTemplate> copy(List<ProjectTemplateDto> dtos) {
        List<ProjectTemplate> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectTemplateDto dto : dtos) {
                ProjectTemplate instance = new ProjectTemplate();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjectConverter getProjectConverter(){
        return this.projectConverter;
    }
    public void setProjectConverter(ProjectConverter projectConverter ){
        this.projectConverter = projectConverter;
    }
    public TemplateConverter getTemplateConverter(){
        return this.templateConverter;
    }
    public void setTemplateConverter(TemplateConverter templateConverter ){
        this.templateConverter = templateConverter;
    }
    public boolean  isTemplate(){
        return this.template;
    }
    public void  setTemplate(boolean template){
        this.template = template;
    }
    public boolean  isProject(){
        return this.project;
    }
    public void  setProject(boolean project){
        this.project = project;
    }
}
