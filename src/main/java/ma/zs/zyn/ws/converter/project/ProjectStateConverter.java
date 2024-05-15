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
import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.ws.dto.project.ProjectStateDto;

@Component
public class ProjectStateConverter {


    public  ProjectStateConverter() {
    }


    public ProjectState toItem(ProjectStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectState item = new ProjectState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public ProjectStateDto toDto(ProjectState item) {
        if (item == null) {
            return null;
        } else {
            ProjectStateDto dto = new ProjectStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<ProjectState> toItem(List<ProjectStateDto> dtos) {
        List<ProjectState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectStateDto> toDto(List<ProjectState> items) {
        List<ProjectStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectStateDto dto, ProjectState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProjectState> copy(List<ProjectStateDto> dtos) {
        List<ProjectState> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectStateDto dto : dtos) {
                ProjectState instance = new ProjectState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
