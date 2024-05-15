package  ma.zs.zyn.ws.converter.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.template.TypeTemplateConverter;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.ws.dto.template.TechnologyDto;

@Component
public class TechnologyConverter {

    @Autowired
    private TypeTemplateConverter typeTemplateConverter ;
    private boolean typeTemplate;

    public  TechnologyConverter() {
        initObject(true);
    }


    public Technology toItem(TechnologyDto dto) {
        if (dto == null) {
            return null;
        } else {
        Technology item = new Technology();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getLogo()))
                item.setLogo(dto.getLogo());
            if(this.typeTemplate && dto.getTypeTemplate()!=null)
                item.setTypeTemplate(typeTemplateConverter.toItem(dto.getTypeTemplate())) ;




        return item;
        }
    }


    public TechnologyDto toDto(Technology item) {
        if (item == null) {
            return null;
        } else {
            TechnologyDto dto = new TechnologyDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getLogo()))
                dto.setLogo(item.getLogo());
            if(this.typeTemplate && item.getTypeTemplate()!=null) {
                dto.setTypeTemplate(typeTemplateConverter.toDto(item.getTypeTemplate())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.typeTemplate = value;
    }
	
    public List<Technology> toItem(List<TechnologyDto> dtos) {
        List<Technology> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TechnologyDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TechnologyDto> toDto(List<Technology> items) {
        List<TechnologyDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Technology item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TechnologyDto dto, Technology t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTypeTemplate() != null)
        typeTemplateConverter.copy(dto.getTypeTemplate(), t.getTypeTemplate());
    }

    public List<Technology> copy(List<TechnologyDto> dtos) {
        List<Technology> result = new ArrayList<>();
        if (dtos != null) {
            for (TechnologyDto dto : dtos) {
                Technology instance = new Technology();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeTemplateConverter getTypeTemplateConverter(){
        return this.typeTemplateConverter;
    }
    public void setTypeTemplateConverter(TypeTemplateConverter typeTemplateConverter ){
        this.typeTemplateConverter = typeTemplateConverter;
    }
    public boolean  isTypeTemplate(){
        return this.typeTemplate;
    }
    public void  setTypeTemplate(boolean typeTemplate){
        this.typeTemplate = typeTemplate;
    }
}
