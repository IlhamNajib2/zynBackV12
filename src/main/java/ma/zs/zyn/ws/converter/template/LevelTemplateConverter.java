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
import ma.zs.zyn.bean.core.template.LevelTemplate;
import ma.zs.zyn.ws.dto.template.LevelTemplateDto;

@Component
public class LevelTemplateConverter {


    public  LevelTemplateConverter() {
    }


    public LevelTemplate toItem(LevelTemplateDto dto) {
        if (dto == null) {
            return null;
        } else {
        LevelTemplate item = new LevelTemplate();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public LevelTemplateDto toDto(LevelTemplate item) {
        if (item == null) {
            return null;
        } else {
            LevelTemplateDto dto = new LevelTemplateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<LevelTemplate> toItem(List<LevelTemplateDto> dtos) {
        List<LevelTemplate> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LevelTemplateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LevelTemplateDto> toDto(List<LevelTemplate> items) {
        List<LevelTemplateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (LevelTemplate item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LevelTemplateDto dto, LevelTemplate t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));



    }

    public List<LevelTemplate> copy(List<LevelTemplateDto> dtos) {
        List<LevelTemplate> result = new ArrayList<>();
        if (dtos != null) {
            for (LevelTemplateDto dto : dtos) {
                LevelTemplate instance = new LevelTemplate();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
