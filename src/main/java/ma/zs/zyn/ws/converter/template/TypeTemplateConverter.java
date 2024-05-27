package  ma.zs.zyn.ws.converter.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.template.TypeTemplate;
import ma.zs.zyn.ws.dto.template.TypeTemplateDto;

@Component
public class TypeTemplateConverter {


    public  TypeTemplateConverter() {
    }


    public TypeTemplate toItem(TypeTemplateDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeTemplate item = new TypeTemplate();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public TypeTemplateDto toDto(TypeTemplate item) {
        if (item == null) {
            return null;
        } else {
            TypeTemplateDto dto = new TypeTemplateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<TypeTemplate> toItem(List<TypeTemplateDto> dtos) {
        List<TypeTemplate> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeTemplateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeTemplateDto> toDto(List<TypeTemplate> items) {
        List<TypeTemplateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeTemplate item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeTemplateDto dto, TypeTemplate t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));


    }

    public List<TypeTemplate> copy(List<TypeTemplateDto> dtos) {
        List<TypeTemplate> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeTemplateDto dto : dtos) {
                TypeTemplate instance = new TypeTemplate();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
