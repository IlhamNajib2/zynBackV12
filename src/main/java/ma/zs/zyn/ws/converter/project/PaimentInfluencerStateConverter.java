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
import ma.zs.zyn.bean.core.project.PaimentInfluencerState;
import ma.zs.zyn.ws.dto.project.PaimentInfluencerStateDto;

@Component
public class PaimentInfluencerStateConverter {


    public  PaimentInfluencerStateConverter() {
    }


    public PaimentInfluencerState toItem(PaimentInfluencerStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaimentInfluencerState item = new PaimentInfluencerState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public PaimentInfluencerStateDto toDto(PaimentInfluencerState item) {
        if (item == null) {
            return null;
        } else {
            PaimentInfluencerStateDto dto = new PaimentInfluencerStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<PaimentInfluencerState> toItem(List<PaimentInfluencerStateDto> dtos) {
        List<PaimentInfluencerState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaimentInfluencerStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaimentInfluencerStateDto> toDto(List<PaimentInfluencerState> items) {
        List<PaimentInfluencerStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaimentInfluencerState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaimentInfluencerStateDto dto, PaimentInfluencerState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<PaimentInfluencerState> copy(List<PaimentInfluencerStateDto> dtos) {
        List<PaimentInfluencerState> result = new ArrayList<>();
        if (dtos != null) {
            for (PaimentInfluencerStateDto dto : dtos) {
                PaimentInfluencerState instance = new PaimentInfluencerState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
