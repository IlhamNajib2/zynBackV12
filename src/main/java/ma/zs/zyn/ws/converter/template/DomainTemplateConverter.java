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
import ma.zs.zyn.bean.core.template.DomainTemplate;
import ma.zs.zyn.ws.dto.template.DomainTemplateDto;

@Component
public class DomainTemplateConverter {


    public  DomainTemplateConverter() {
    }


    public DomainTemplate toItem(DomainTemplateDto dto) {
        if (dto == null) {
            return null;
        } else {
        DomainTemplate item = new DomainTemplate();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public DomainTemplateDto toDto(DomainTemplate item) {
        if (item == null) {
            return null;
        } else {
            DomainTemplateDto dto = new DomainTemplateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<DomainTemplate> toItem(List<DomainTemplateDto> dtos) {
        List<DomainTemplate> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DomainTemplateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DomainTemplateDto> toDto(List<DomainTemplate> items) {
        List<DomainTemplateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DomainTemplate item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DomainTemplateDto dto, DomainTemplate t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DomainTemplate> copy(List<DomainTemplateDto> dtos) {
        List<DomainTemplate> result = new ArrayList<>();
        if (dtos != null) {
            for (DomainTemplateDto dto : dtos) {
                DomainTemplate instance = new DomainTemplate();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
