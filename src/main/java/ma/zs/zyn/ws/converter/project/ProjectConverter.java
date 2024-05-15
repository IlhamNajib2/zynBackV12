package  ma.zs.zyn.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.zyn.zynerator.util.ListUtil;

import ma.zs.zyn.ws.converter.inscription.InscriptionMembreConverter;
import ma.zs.zyn.ws.converter.project.ProjectStateConverter;
import ma.zs.zyn.ws.converter.template.TemplateConverter;
import ma.zs.zyn.ws.converter.template.DomainTemplateConverter;
import ma.zs.zyn.ws.converter.template.ProjectTemplateConverter;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.ws.dto.project.ProjectDto;

@Component
public class ProjectConverter {

    @Autowired
    private InscriptionMembreConverter inscriptionMembreConverter ;
    @Autowired
    private ProjectStateConverter projectStateConverter ;
    @Autowired
    private TemplateConverter templateConverter ;
    @Autowired
    private DomainTemplateConverter domainTemplateConverter ;
    @Autowired
    private ProjectTemplateConverter projectTemplateConverter ;
    private boolean projectState;
    private boolean inscriptionMembre;
    private boolean domainTemplate;
    private boolean projectTemplates;

    public  ProjectConverter() {
        init(true);
    }


    public Project toItem(ProjectDto dto) {
        if (dto == null) {
            return null;
        } else {
        Project item = new Project();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getGeneratedDate()))
                item.setGeneratedDate(DateUtil.stringEnToDate(dto.getGeneratedDate()));
            if(StringUtil.isNotEmpty(dto.getYaml()))
                item.setYaml(dto.getYaml());
            if(this.projectState && dto.getProjectState()!=null)
                item.setProjectState(projectStateConverter.toItem(dto.getProjectState())) ;

            if(this.inscriptionMembre && dto.getInscriptionMembre()!=null)
                item.setInscriptionMembre(inscriptionMembreConverter.toItem(dto.getInscriptionMembre())) ;

            if(this.domainTemplate && dto.getDomainTemplate()!=null)
                item.setDomainTemplate(domainTemplateConverter.toItem(dto.getDomainTemplate())) ;


            if(this.projectTemplates && ListUtil.isNotEmpty(dto.getProjectTemplates()))
                item.setProjectTemplates(projectTemplateConverter.toItem(dto.getProjectTemplates()));


        return item;
        }
    }


    public ProjectDto toDto(Project item) {
        if (item == null) {
            return null;
        } else {
            ProjectDto dto = new ProjectDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(item.getGeneratedDate()!=null)
                dto.setGeneratedDate(DateUtil.dateTimeToString(item.getGeneratedDate()));
            if(StringUtil.isNotEmpty(item.getYaml()))
                dto.setYaml(item.getYaml());
            if(this.projectState && item.getProjectState()!=null) {
                dto.setProjectState(projectStateConverter.toDto(item.getProjectState())) ;

            }
            if(this.inscriptionMembre && item.getInscriptionMembre()!=null) {
                dto.setInscriptionMembre(inscriptionMembreConverter.toDto(item.getInscriptionMembre())) ;

            }
            if(this.domainTemplate && item.getDomainTemplate()!=null) {
                dto.setDomainTemplate(domainTemplateConverter.toDto(item.getDomainTemplate())) ;

            }
        if(this.projectTemplates && ListUtil.isNotEmpty(item.getProjectTemplates())){
            projectTemplateConverter.init(true);
            projectTemplateConverter.setProject(false);
            dto.setProjectTemplates(projectTemplateConverter.toDto(item.getProjectTemplates()));
            projectTemplateConverter.setProject(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.projectTemplates = value;
    }
    public void initObject(boolean value) {
        this.projectState = value;
        this.inscriptionMembre = value;
        this.domainTemplate = value;
    }
	
    public List<Project> toItem(List<ProjectDto> dtos) {
        List<Project> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectDto> toDto(List<Project> items) {
        List<ProjectDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Project item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectDto dto, Project t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getProjectState() != null)
        projectStateConverter.copy(dto.getProjectState(), t.getProjectState());
        if (dto.getInscriptionMembre() != null)
        inscriptionMembreConverter.copy(dto.getInscriptionMembre(), t.getInscriptionMembre());
        if (dto.getDomainTemplate() != null)
        domainTemplateConverter.copy(dto.getDomainTemplate(), t.getDomainTemplate());
        if (dto.getProjectTemplates() != null)
            t.setProjectTemplates(projectTemplateConverter.copy(dto.getProjectTemplates()));
    }

    public List<Project> copy(List<ProjectDto> dtos) {
        List<Project> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectDto dto : dtos) {
                Project instance = new Project();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public InscriptionMembreConverter getInscriptionMembreConverter(){
        return this.inscriptionMembreConverter;
    }
    public void setInscriptionMembreConverter(InscriptionMembreConverter inscriptionMembreConverter ){
        this.inscriptionMembreConverter = inscriptionMembreConverter;
    }
    public ProjectStateConverter getProjectStateConverter(){
        return this.projectStateConverter;
    }
    public void setProjectStateConverter(ProjectStateConverter projectStateConverter ){
        this.projectStateConverter = projectStateConverter;
    }
    public TemplateConverter getTemplateConverter(){
        return this.templateConverter;
    }
    public void setTemplateConverter(TemplateConverter templateConverter ){
        this.templateConverter = templateConverter;
    }
    public DomainTemplateConverter getDomainTemplateConverter(){
        return this.domainTemplateConverter;
    }
    public void setDomainTemplateConverter(DomainTemplateConverter domainTemplateConverter ){
        this.domainTemplateConverter = domainTemplateConverter;
    }
    public ProjectTemplateConverter getProjectTemplateConverter(){
        return this.projectTemplateConverter;
    }
    public void setProjectTemplateConverter(ProjectTemplateConverter projectTemplateConverter ){
        this.projectTemplateConverter = projectTemplateConverter;
    }
    public boolean  isProjectState(){
        return this.projectState;
    }
    public void  setProjectState(boolean projectState){
        this.projectState = projectState;
    }
    public boolean  isInscriptionMembre(){
        return this.inscriptionMembre;
    }
    public void  setInscriptionMembre(boolean inscriptionMembre){
        this.inscriptionMembre = inscriptionMembre;
    }
    public boolean  isDomainTemplate(){
        return this.domainTemplate;
    }
    public void  setDomainTemplate(boolean domainTemplate){
        this.domainTemplate = domainTemplate;
    }
    public boolean  isProjectTemplates(){
        return this.projectTemplates ;
    }
    public void  setProjectTemplates(boolean projectTemplates ){
        this.projectTemplates  = projectTemplates ;
    }
}
