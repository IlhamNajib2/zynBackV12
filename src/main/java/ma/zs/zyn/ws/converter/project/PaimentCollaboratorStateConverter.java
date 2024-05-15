package  ma.zs.zyn.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.project.PaimentCollaboratorState;
import ma.zs.zyn.ws.dto.project.PaimentCollaboratorStateDto;

@Component
public class PaimentCollaboratorStateConverter {


    public  PaimentCollaboratorStateConverter() {
    }


    public PaimentCollaboratorState toItem(PaimentCollaboratorStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaimentCollaboratorState item = new PaimentCollaboratorState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public PaimentCollaboratorStateDto toDto(PaimentCollaboratorState item) {
        if (item == null) {
            return null;
        } else {
            PaimentCollaboratorStateDto dto = new PaimentCollaboratorStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<PaimentCollaboratorState> toItem(List<PaimentCollaboratorStateDto> dtos) {
        List<PaimentCollaboratorState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaimentCollaboratorStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaimentCollaboratorStateDto> toDto(List<PaimentCollaboratorState> items) {
        List<PaimentCollaboratorStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaimentCollaboratorState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaimentCollaboratorStateDto dto, PaimentCollaboratorState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<PaimentCollaboratorState> copy(List<PaimentCollaboratorStateDto> dtos) {
        List<PaimentCollaboratorState> result = new ArrayList<>();
        if (dtos != null) {
            for (PaimentCollaboratorStateDto dto : dtos) {
                PaimentCollaboratorState instance = new PaimentCollaboratorState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
