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
import ma.zs.zyn.bean.core.template.CategoryTemplate;
import ma.zs.zyn.ws.dto.template.CategoryTemplateDto;

@Component
public class CategoryTemplateConverter {


    public  CategoryTemplateConverter() {
    }


    public CategoryTemplate toItem(CategoryTemplateDto dto) {
        if (dto == null) {
            return null;
        } else {
        CategoryTemplate item = new CategoryTemplate();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public CategoryTemplateDto toDto(CategoryTemplate item) {
        if (item == null) {
            return null;
        } else {
            CategoryTemplateDto dto = new CategoryTemplateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<CategoryTemplate> toItem(List<CategoryTemplateDto> dtos) {
        List<CategoryTemplate> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CategoryTemplateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CategoryTemplateDto> toDto(List<CategoryTemplate> items) {
        List<CategoryTemplateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CategoryTemplate item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CategoryTemplateDto dto, CategoryTemplate t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CategoryTemplate> copy(List<CategoryTemplateDto> dtos) {
        List<CategoryTemplate> result = new ArrayList<>();
        if (dtos != null) {
            for (CategoryTemplateDto dto : dtos) {
                CategoryTemplate instance = new CategoryTemplate();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
