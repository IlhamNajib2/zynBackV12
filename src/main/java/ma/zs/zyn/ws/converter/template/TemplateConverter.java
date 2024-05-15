package  ma.zs.zyn.ws.converter.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.template.CategoryTemplateConverter;
import ma.zs.zyn.ws.converter.template.TechnologyConverter;
import ma.zs.zyn.ws.converter.template.LevelTemplateConverter;
import ma.zs.zyn.ws.converter.template.TypeTemplateConverter;
import ma.zs.zyn.ws.converter.template.DomainTemplateConverter;
import ma.zs.zyn.ws.converter.collaborator.MemberConverter;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.ws.dto.template.TemplateDto;

@Component
public class TemplateConverter {

    @Autowired
    private CategoryTemplateConverter categoryTemplateConverter ;
    @Autowired
    private TechnologyConverter technologyConverter ;
    @Autowired
    private LevelTemplateConverter levelTemplateConverter ;
    @Autowired
    private TypeTemplateConverter typeTemplateConverter ;
    @Autowired
    private DomainTemplateConverter domainTemplateConverter ;
    @Autowired
    private MemberConverter memberConverter ;
    private boolean categoryTemplate;
    private boolean typeTemplate;
    private boolean levelTemplate;
    private boolean domainTemplate;
    private boolean member;
    private boolean technology;

    public  TemplateConverter() {
        initObject(true);
    }


    public Template toItem(TemplateDto dto) {
        if (dto == null) {
            return null;
        } else {
        Template item = new Template();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getAddingDate()))
                item.setAddingDate(DateUtil.stringEnToDate(dto.getAddingDate()));
            if(StringUtil.isNotEmpty(dto.getLastUpdateDate()))
                item.setLastUpdateDate(DateUtil.stringEnToDate(dto.getLastUpdateDate()));
            if(StringUtil.isNotEmpty(dto.getTemplateTags()))
                item.setTemplateTags(dto.getTemplateTags());
            if(StringUtil.isNotEmpty(dto.getPrice()))
                item.setPrice(dto.getPrice());
            if(this.categoryTemplate && dto.getCategoryTemplate()!=null)
                item.setCategoryTemplate(categoryTemplateConverter.toItem(dto.getCategoryTemplate())) ;

            if(this.typeTemplate && dto.getTypeTemplate()!=null)
                item.setTypeTemplate(typeTemplateConverter.toItem(dto.getTypeTemplate())) ;

            if(this.levelTemplate && dto.getLevelTemplate()!=null)
                item.setLevelTemplate(levelTemplateConverter.toItem(dto.getLevelTemplate())) ;

            if(this.domainTemplate && dto.getDomainTemplate()!=null)
                item.setDomainTemplate(domainTemplateConverter.toItem(dto.getDomainTemplate())) ;

            if(this.member && dto.getMember()!=null)
                item.setMember(memberConverter.toItem(dto.getMember())) ;

            if(this.technology && dto.getTechnology()!=null)
                item.setTechnology(technologyConverter.toItem(dto.getTechnology())) ;




        return item;
        }
    }


    public TemplateDto toDto(Template item) {
        if (item == null) {
            return null;
        } else {
            TemplateDto dto = new TemplateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getAddingDate()!=null)
                dto.setAddingDate(DateUtil.dateTimeToString(item.getAddingDate()));
            if(item.getLastUpdateDate()!=null)
                dto.setLastUpdateDate(DateUtil.dateTimeToString(item.getLastUpdateDate()));
            if(StringUtil.isNotEmpty(item.getTemplateTags()))
                dto.setTemplateTags(item.getTemplateTags());
            if(StringUtil.isNotEmpty(item.getPrice()))
                dto.setPrice(item.getPrice());
            if(this.categoryTemplate && item.getCategoryTemplate()!=null) {
                dto.setCategoryTemplate(categoryTemplateConverter.toDto(item.getCategoryTemplate())) ;

            }
            if(this.typeTemplate && item.getTypeTemplate()!=null) {
                dto.setTypeTemplate(typeTemplateConverter.toDto(item.getTypeTemplate())) ;

            }
            if(this.levelTemplate && item.getLevelTemplate()!=null) {
                dto.setLevelTemplate(levelTemplateConverter.toDto(item.getLevelTemplate())) ;

            }
            if(this.domainTemplate && item.getDomainTemplate()!=null) {
                dto.setDomainTemplate(domainTemplateConverter.toDto(item.getDomainTemplate())) ;

            }
            if(this.member && item.getMember()!=null) {
                dto.setMember(memberConverter.toDto(item.getMember())) ;

            }
            if(this.technology && item.getTechnology()!=null) {
                dto.setTechnology(technologyConverter.toDto(item.getTechnology())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.categoryTemplate = value;
        this.typeTemplate = value;
        this.levelTemplate = value;
        this.domainTemplate = value;
        this.member = value;
        this.technology = value;
    }
	
    public List<Template> toItem(List<TemplateDto> dtos) {
        List<Template> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TemplateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TemplateDto> toDto(List<Template> items) {
        List<TemplateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Template item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TemplateDto dto, Template t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getCategoryTemplate() != null)
        categoryTemplateConverter.copy(dto.getCategoryTemplate(), t.getCategoryTemplate());
        if (dto.getTypeTemplate() != null)
        typeTemplateConverter.copy(dto.getTypeTemplate(), t.getTypeTemplate());
        if (dto.getLevelTemplate() != null)
        levelTemplateConverter.copy(dto.getLevelTemplate(), t.getLevelTemplate());
        if (dto.getDomainTemplate() != null)
        domainTemplateConverter.copy(dto.getDomainTemplate(), t.getDomainTemplate());
        if (dto.getMember() != null)
        memberConverter.copy(dto.getMember(), t.getMember());
        if (dto.getTechnology() != null)
        technologyConverter.copy(dto.getTechnology(), t.getTechnology());
    }

    public List<Template> copy(List<TemplateDto> dtos) {
        List<Template> result = new ArrayList<>();
        if (dtos != null) {
            for (TemplateDto dto : dtos) {
                Template instance = new Template();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CategoryTemplateConverter getCategoryTemplateConverter(){
        return this.categoryTemplateConverter;
    }
    public void setCategoryTemplateConverter(CategoryTemplateConverter categoryTemplateConverter ){
        this.categoryTemplateConverter = categoryTemplateConverter;
    }
    public TechnologyConverter getTechnologyConverter(){
        return this.technologyConverter;
    }
    public void setTechnologyConverter(TechnologyConverter technologyConverter ){
        this.technologyConverter = technologyConverter;
    }
    public LevelTemplateConverter getLevelTemplateConverter(){
        return this.levelTemplateConverter;
    }
    public void setLevelTemplateConverter(LevelTemplateConverter levelTemplateConverter ){
        this.levelTemplateConverter = levelTemplateConverter;
    }
    public TypeTemplateConverter getTypeTemplateConverter(){
        return this.typeTemplateConverter;
    }
    public void setTypeTemplateConverter(TypeTemplateConverter typeTemplateConverter ){
        this.typeTemplateConverter = typeTemplateConverter;
    }
    public DomainTemplateConverter getDomainTemplateConverter(){
        return this.domainTemplateConverter;
    }
    public void setDomainTemplateConverter(DomainTemplateConverter domainTemplateConverter ){
        this.domainTemplateConverter = domainTemplateConverter;
    }
    public MemberConverter getMemberConverter(){
        return this.memberConverter;
    }
    public void setMemberConverter(MemberConverter memberConverter ){
        this.memberConverter = memberConverter;
    }
    public boolean  isCategoryTemplate(){
        return this.categoryTemplate;
    }
    public void  setCategoryTemplate(boolean categoryTemplate){
        this.categoryTemplate = categoryTemplate;
    }
    public boolean  isTypeTemplate(){
        return this.typeTemplate;
    }
    public void  setTypeTemplate(boolean typeTemplate){
        this.typeTemplate = typeTemplate;
    }
    public boolean  isLevelTemplate(){
        return this.levelTemplate;
    }
    public void  setLevelTemplate(boolean levelTemplate){
        this.levelTemplate = levelTemplate;
    }
    public boolean  isDomainTemplate(){
        return this.domainTemplate;
    }
    public void  setDomainTemplate(boolean domainTemplate){
        this.domainTemplate = domainTemplate;
    }
    public boolean  isMember(){
        return this.member;
    }
    public void  setMember(boolean member){
        this.member = member;
    }
    public boolean  isTechnology(){
        return this.technology;
    }
    public void  setTechnology(boolean technology){
        this.technology = technology;
    }
}
