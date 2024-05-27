package  ma.zs.zyn.ws.converter.packaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.category.CategoryPackagingConverter;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.ws.dto.packaging.PackagingDto;

@Component
public class PackagingConverter {

    @Autowired
    private CategoryPackagingConverter categoryPackagingConverter ;
    private boolean categoryPackaging;

    public  PackagingConverter() {
        initObject(true);
    }


    public Packaging toItem(PackagingDto dto) {
        if (dto == null) {
            return null;
        } else {
        Packaging item = new Packaging();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getDateStart()))
                item.setDateStart(DateUtil.stringEnToDate(dto.getDateStart()));
            if(StringUtil.isNotEmpty(dto.getDateEnd()))
                item.setDateEnd(DateUtil.stringEnToDate(dto.getDateEnd()));
            if(StringUtil.isNotEmpty(dto.getPrice()))
                item.setPrice(dto.getPrice());
            if(StringUtil.isNotEmpty(dto.getMaxEntity()))
                item.setMaxEntity(dto.getMaxEntity());
            if(StringUtil.isNotEmpty(dto.getMaxProjet()))
                item.setMaxProjet(dto.getMaxProjet());
            if(StringUtil.isNotEmpty(dto.getMaxAttribut()))
                item.setMaxAttribut(dto.getMaxAttribut());
            if(StringUtil.isNotEmpty(dto.getMaxIndicator()))
                item.setMaxIndicator(dto.getMaxIndicator());
            if(this.categoryPackaging && dto.getCategoryPackaging()!=null)
                item.setCategoryPackaging(categoryPackagingConverter.toItem(dto.getCategoryPackaging())) ;




        return item;
        }
    }


    public PackagingDto toDto(Packaging item) {
        if (item == null) {
            return null;
        } else {
            PackagingDto dto = new PackagingDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getDateStart()!=null)
                dto.setDateStart(DateUtil.dateTimeToString(item.getDateStart()));
            if(item.getDateEnd()!=null)
                dto.setDateEnd(DateUtil.dateTimeToString(item.getDateEnd()));
            if(StringUtil.isNotEmpty(item.getPrice()))
                dto.setPrice(item.getPrice());
            if(StringUtil.isNotEmpty(item.getMaxEntity()))
                dto.setMaxEntity(item.getMaxEntity());
            if(StringUtil.isNotEmpty(item.getMaxProjet()))
                dto.setMaxProjet(item.getMaxProjet());
            if(StringUtil.isNotEmpty(item.getMaxAttribut()))
                dto.setMaxAttribut(item.getMaxAttribut());
            if(StringUtil.isNotEmpty(item.getMaxIndicator()))
                dto.setMaxIndicator(item.getMaxIndicator());
            if(this.categoryPackaging && item.getCategoryPackaging()!=null) {
                dto.setCategoryPackaging(categoryPackagingConverter.toDto(item.getCategoryPackaging())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.categoryPackaging = value;
    }
	
    public List<Packaging> toItem(List<PackagingDto> dtos) {
        List<Packaging> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PackagingDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PackagingDto> toDto(List<Packaging> items) {
        List<PackagingDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Packaging item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PackagingDto dto, Packaging t) {
		//BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (StringUtil.isNotEmpty(dto.getDateStart()))
            t.setDateStart(DateUtil.stringEnToDate(dto.getDateStart()));

        if (StringUtil.isNotEmpty(dto.getDateEnd()))
            t.setDateEnd(DateUtil.stringEnToDate(dto.getDateEnd()));


        if (StringUtil.isNotEmpty(dto.getDescription()))
            t.setDescription(dto.getDescription());

        if (StringUtil.isNotEmpty(dto.getCode()))
            t.setCode(dto.getCode());

        if (StringUtil.isNotEmpty(dto.getId()))
            t.setId(dto.getId());

        if (StringUtil.isNotEmpty(dto.getMaxAttribut()))
            t.setMaxAttribut(dto.getMaxAttribut());

        if (StringUtil.isNotEmpty(dto.getMaxEntity()))
            t.setMaxEntity(dto.getMaxEntity());

        if (StringUtil.isNotEmpty(dto.getMaxProjet()))
            t.setMaxProjet(dto.getMaxProjet());

        if (StringUtil.isNotEmpty(dto.getMaxIndicator()))
            t.setMaxIndicator(dto.getMaxIndicator());


        if (StringUtil.isNotEmpty(dto.getPrice()))
            t.setPrice(dto.getPrice());

        if (StringUtil.isNotEmpty(dto.getName()))
            t.setName(dto.getName());


        if (dto.getCategoryPackaging() != null)
        categoryPackagingConverter.copy(dto.getCategoryPackaging(), t.getCategoryPackaging());
    }

    public List<Packaging> copy(List<PackagingDto> dtos) {
        List<Packaging> result = new ArrayList<>();
        if (dtos != null) {
            for (PackagingDto dto : dtos) {
                Packaging instance = new Packaging();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CategoryPackagingConverter getCategoryPackagingConverter(){
        return this.categoryPackagingConverter;
    }
    public void setCategoryPackagingConverter(CategoryPackagingConverter categoryPackagingConverter ){
        this.categoryPackagingConverter = categoryPackagingConverter;
    }
    public boolean  isCategoryPackaging(){
        return this.categoryPackaging;
    }
    public void  setCategoryPackaging(boolean categoryPackaging){
        this.categoryPackaging = categoryPackaging;
    }
}
