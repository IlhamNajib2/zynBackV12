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
import ma.zs.zyn.bean.core.inscription.InscriptionMembreState;
import ma.zs.zyn.ws.dto.inscription.InscriptionMembreStateDto;

@Component
public class InscriptionMembreStateConverter {


    public  InscriptionMembreStateConverter() {
    }


    public InscriptionMembreState toItem(InscriptionMembreStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        InscriptionMembreState item = new InscriptionMembreState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());



        return item;
        }
    }


    public InscriptionMembreStateDto toDto(InscriptionMembreState item) {
        if (item == null) {
            return null;
        } else {
            InscriptionMembreStateDto dto = new InscriptionMembreStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());


        return dto;
        }
    }


	
    public List<InscriptionMembreState> toItem(List<InscriptionMembreStateDto> dtos) {
        List<InscriptionMembreState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionMembreStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionMembreStateDto> toDto(List<InscriptionMembreState> items) {
        List<InscriptionMembreStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InscriptionMembreState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionMembreStateDto dto, InscriptionMembreState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<InscriptionMembreState> copy(List<InscriptionMembreStateDto> dtos) {
        List<InscriptionMembreState> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionMembreStateDto dto : dtos) {
                InscriptionMembreState instance = new InscriptionMembreState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
