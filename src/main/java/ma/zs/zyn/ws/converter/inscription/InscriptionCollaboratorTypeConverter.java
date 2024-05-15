package  ma.zs.zyn.ws.converter.inscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorType;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorTypeDto;

@Component
public class InscriptionCollaboratorTypeConverter {


    public  InscriptionCollaboratorTypeConverter() {
    }


    public InscriptionCollaboratorType toItem(InscriptionCollaboratorTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        InscriptionCollaboratorType item = new InscriptionCollaboratorType();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public InscriptionCollaboratorTypeDto toDto(InscriptionCollaboratorType item) {
        if (item == null) {
            return null;
        } else {
            InscriptionCollaboratorTypeDto dto = new InscriptionCollaboratorTypeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<InscriptionCollaboratorType> toItem(List<InscriptionCollaboratorTypeDto> dtos) {
        List<InscriptionCollaboratorType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionCollaboratorTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionCollaboratorTypeDto> toDto(List<InscriptionCollaboratorType> items) {
        List<InscriptionCollaboratorTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InscriptionCollaboratorType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionCollaboratorTypeDto dto, InscriptionCollaboratorType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<InscriptionCollaboratorType> copy(List<InscriptionCollaboratorTypeDto> dtos) {
        List<InscriptionCollaboratorType> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionCollaboratorTypeDto dto : dtos) {
                InscriptionCollaboratorType instance = new InscriptionCollaboratorType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
