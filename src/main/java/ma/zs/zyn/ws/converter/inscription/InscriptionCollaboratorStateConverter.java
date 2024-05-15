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
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorStateDto;

@Component
public class InscriptionCollaboratorStateConverter {


    public  InscriptionCollaboratorStateConverter() {
    }


    public InscriptionCollaboratorState toItem(InscriptionCollaboratorStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        InscriptionCollaboratorState item = new InscriptionCollaboratorState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public InscriptionCollaboratorStateDto toDto(InscriptionCollaboratorState item) {
        if (item == null) {
            return null;
        } else {
            InscriptionCollaboratorStateDto dto = new InscriptionCollaboratorStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<InscriptionCollaboratorState> toItem(List<InscriptionCollaboratorStateDto> dtos) {
        List<InscriptionCollaboratorState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionCollaboratorStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionCollaboratorStateDto> toDto(List<InscriptionCollaboratorState> items) {
        List<InscriptionCollaboratorStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InscriptionCollaboratorState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionCollaboratorStateDto dto, InscriptionCollaboratorState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<InscriptionCollaboratorState> copy(List<InscriptionCollaboratorStateDto> dtos) {
        List<InscriptionCollaboratorState> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionCollaboratorStateDto dto : dtos) {
                InscriptionCollaboratorState instance = new InscriptionCollaboratorState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
