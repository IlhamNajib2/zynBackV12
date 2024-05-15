package  ma.zs.zyn.ws.converter.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.category.CategoryPackaging;
import ma.zs.zyn.ws.dto.category.CategoryPackagingDto;

@Component
public class CategoryPackagingConverter {


    public  CategoryPackagingConverter() {
    }


    public CategoryPackaging toItem(CategoryPackagingDto dto) {
        if (dto == null) {
            return null;
        } else {
        CategoryPackaging item = new CategoryPackaging();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public CategoryPackagingDto toDto(CategoryPackaging item) {
        if (item == null) {
            return null;
        } else {
            CategoryPackagingDto dto = new CategoryPackagingDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<CategoryPackaging> toItem(List<CategoryPackagingDto> dtos) {
        List<CategoryPackaging> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CategoryPackagingDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CategoryPackagingDto> toDto(List<CategoryPackaging> items) {
        List<CategoryPackagingDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CategoryPackaging item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CategoryPackagingDto dto, CategoryPackaging t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CategoryPackaging> copy(List<CategoryPackagingDto> dtos) {
        List<CategoryPackaging> result = new ArrayList<>();
        if (dtos != null) {
            for (CategoryPackagingDto dto : dtos) {
                CategoryPackaging instance = new CategoryPackaging();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
