package  ma.zs.zyn.ws.converter.inscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.zyn.zynerator.util.ListUtil;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.ws.converter.inscription.InscriptionMembreConverter;
import ma.zs.zyn.ws.converter.inscription.InscriptionCollaboratorTypeConverter;
import ma.zs.zyn.ws.converter.inscription.InscriptionMembreStateConverter;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;
import ma.zs.zyn.ws.converter.inscription.InscriptionCollaboratorStateConverter;
import ma.zs.zyn.ws.converter.collaborator.MemberConverter;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorDto;

@Component
public class InscriptionCollaboratorConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private InscriptionMembreConverter inscriptionMembreConverter ;
    @Autowired
    private InscriptionCollaboratorTypeConverter inscriptionCollaboratorTypeConverter ;
    @Autowired
    private InscriptionMembreStateConverter inscriptionMembreStateConverter ;
    @Autowired
    private PackagingConverter packagingConverter ;
    @Autowired
    private InscriptionCollaboratorStateConverter inscriptionCollaboratorStateConverter ;
    @Autowired
    private MemberConverter memberConverter ;
    private boolean packaging;
    private boolean collaborator;
    private boolean inscriptionCollaboratorState;
    private boolean inscriptionCollaboratorType;
    private boolean inscriptionMembres;

    public  InscriptionCollaboratorConverter() {
        init(true);
    }


    public InscriptionCollaborator toItem(InscriptionCollaboratorDto dto) {
        if (dto == null) {
            return null;
        } else {
        InscriptionCollaborator item = new InscriptionCollaborator();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(StringUtil.isNotEmpty(dto.getRenewDate()))
                item.setRenewDate(DateUtil.stringEnToDate(dto.getRenewDate()));
            if(StringUtil.isNotEmpty(dto.getConsumedEntity()))
                item.setConsumedEntity(dto.getConsumedEntity());
            if(StringUtil.isNotEmpty(dto.getConsumedProjet()))
                item.setConsumedProjet(dto.getConsumedProjet());
            if(StringUtil.isNotEmpty(dto.getConsumedAttribut()))
                item.setConsumedAttribut(dto.getConsumedAttribut());
            if(StringUtil.isNotEmpty(dto.getConsumedIndicator()))
                item.setConsumedIndicator(dto.getConsumedIndicator());
            if(this.packaging && dto.getPackaging()!=null)
                item.setPackaging(packagingConverter.toItem(dto.getPackaging())) ;

            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.inscriptionCollaboratorState && dto.getInscriptionCollaboratorState()!=null)
                item.setInscriptionCollaboratorState(inscriptionCollaboratorStateConverter.toItem(dto.getInscriptionCollaboratorState())) ;

            if(this.inscriptionCollaboratorType && dto.getInscriptionCollaboratorType()!=null)
                item.setInscriptionCollaboratorType(inscriptionCollaboratorTypeConverter.toItem(dto.getInscriptionCollaboratorType())) ;


            if(this.inscriptionMembres && ListUtil.isNotEmpty(dto.getInscriptionMembres()))
                item.setInscriptionMembres(inscriptionMembreConverter.toItem(dto.getInscriptionMembres()));


        return item;
        }
    }


    public InscriptionCollaboratorDto toDto(InscriptionCollaborator item) {
        if (item == null) {
            return null;
        } else {
            InscriptionCollaboratorDto dto = new InscriptionCollaboratorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(item.getRenewDate()!=null)
                dto.setRenewDate(DateUtil.dateTimeToString(item.getRenewDate()));
            if(StringUtil.isNotEmpty(item.getConsumedEntity()))
                dto.setConsumedEntity(item.getConsumedEntity());
            if(StringUtil.isNotEmpty(item.getConsumedProjet()))
                dto.setConsumedProjet(item.getConsumedProjet());
            if(StringUtil.isNotEmpty(item.getConsumedAttribut()))
                dto.setConsumedAttribut(item.getConsumedAttribut());
            if(StringUtil.isNotEmpty(item.getConsumedIndicator()))
                dto.setConsumedIndicator(item.getConsumedIndicator());
            if(this.packaging && item.getPackaging()!=null) {
                dto.setPackaging(packagingConverter.toDto(item.getPackaging())) ;

            }
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.inscriptionCollaboratorState && item.getInscriptionCollaboratorState()!=null) {
                dto.setInscriptionCollaboratorState(inscriptionCollaboratorStateConverter.toDto(item.getInscriptionCollaboratorState())) ;

            }
            if(this.inscriptionCollaboratorType && item.getInscriptionCollaboratorType()!=null) {
                dto.setInscriptionCollaboratorType(inscriptionCollaboratorTypeConverter.toDto(item.getInscriptionCollaboratorType())) ;

            }
        if(this.inscriptionMembres && ListUtil.isNotEmpty(item.getInscriptionMembres())){
            inscriptionMembreConverter.init(true);
            inscriptionMembreConverter.setInscriptionCollaborator(false);
            dto.setInscriptionMembres(inscriptionMembreConverter.toDto(item.getInscriptionMembres()));
            inscriptionMembreConverter.setInscriptionCollaborator(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.inscriptionMembres = value;
    }
    public void initObject(boolean value) {
        this.packaging = value;
        this.collaborator = value;
        this.inscriptionCollaboratorState = value;
        this.inscriptionCollaboratorType = value;
    }
	
    public List<InscriptionCollaborator> toItem(List<InscriptionCollaboratorDto> dtos) {
        List<InscriptionCollaborator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionCollaboratorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionCollaboratorDto> toDto(List<InscriptionCollaborator> items) {
        List<InscriptionCollaboratorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InscriptionCollaborator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionCollaboratorDto dto, InscriptionCollaborator t) {
		//BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (StringUtil.isNotEmpty(dto.getStartDate()))
            t.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
        if (StringUtil.isNotEmpty(dto.getEndDate()))
            t.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
        if (StringUtil.isNotEmpty(dto.getRenewDate()))
            t.setRenewDate(DateUtil.stringEnToDate(dto.getRenewDate()));

        if (StringUtil.isNotEmpty(dto.getId()))
            t.setId(dto.getId());

        if (StringUtil.isNotEmpty(dto.getConsumedAttribut()))
            t.setConsumedAttribut(dto.getConsumedAttribut());

        if (StringUtil.isNotEmpty(dto.getConsumedProjet()))
            t.setConsumedProjet(dto.getConsumedProjet());

        if (StringUtil.isNotEmpty(dto.getConsumedEntity()))
            t.setConsumedEntity(dto.getConsumedEntity());

        if (StringUtil.isNotEmpty(dto.getConsumedIndicator()))
            t.setConsumedIndicator(dto.getConsumedIndicator());

        if (dto.getPackaging() != null)
        packagingConverter.copy(dto.getPackaging(), t.getPackaging());
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getInscriptionCollaboratorState() != null)
        inscriptionCollaboratorStateConverter.copy(dto.getInscriptionCollaboratorState(), t.getInscriptionCollaboratorState());
        if (dto.getInscriptionCollaboratorType() != null)
        inscriptionCollaboratorTypeConverter.copy(dto.getInscriptionCollaboratorType(), t.getInscriptionCollaboratorType());
        if (dto.getInscriptionMembres() != null)
            t.setInscriptionMembres(inscriptionMembreConverter.copy(dto.getInscriptionMembres()));
    }

    public List<InscriptionCollaborator> copy(List<InscriptionCollaboratorDto> dtos) {
        List<InscriptionCollaborator> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionCollaboratorDto dto : dtos) {
                InscriptionCollaborator instance = new InscriptionCollaborator();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CollaboratorConverter getCollaboratorConverter(){
        return this.collaboratorConverter;
    }
    public void setCollaboratorConverter(CollaboratorConverter collaboratorConverter ){
        this.collaboratorConverter = collaboratorConverter;
    }
    public InscriptionMembreConverter getInscriptionMembreConverter(){
        return this.inscriptionMembreConverter;
    }
    public void setInscriptionMembreConverter(InscriptionMembreConverter inscriptionMembreConverter ){
        this.inscriptionMembreConverter = inscriptionMembreConverter;
    }
    public InscriptionCollaboratorTypeConverter getInscriptionCollaboratorTypeConverter(){
        return this.inscriptionCollaboratorTypeConverter;
    }
    public void setInscriptionCollaboratorTypeConverter(InscriptionCollaboratorTypeConverter inscriptionCollaboratorTypeConverter ){
        this.inscriptionCollaboratorTypeConverter = inscriptionCollaboratorTypeConverter;
    }
    public InscriptionMembreStateConverter getInscriptionMembreStateConverter(){
        return this.inscriptionMembreStateConverter;
    }
    public void setInscriptionMembreStateConverter(InscriptionMembreStateConverter inscriptionMembreStateConverter ){
        this.inscriptionMembreStateConverter = inscriptionMembreStateConverter;
    }
    public PackagingConverter getPackagingConverter(){
        return this.packagingConverter;
    }
    public void setPackagingConverter(PackagingConverter packagingConverter ){
        this.packagingConverter = packagingConverter;
    }
    public InscriptionCollaboratorStateConverter getInscriptionCollaboratorStateConverter(){
        return this.inscriptionCollaboratorStateConverter;
    }
    public void setInscriptionCollaboratorStateConverter(InscriptionCollaboratorStateConverter inscriptionCollaboratorStateConverter ){
        this.inscriptionCollaboratorStateConverter = inscriptionCollaboratorStateConverter;
    }
    public MemberConverter getMemberConverter(){
        return this.memberConverter;
    }
    public void setMemberConverter(MemberConverter memberConverter ){
        this.memberConverter = memberConverter;
    }
    public boolean  isPackaging(){
        return this.packaging;
    }
    public void  setPackaging(boolean packaging){
        this.packaging = packaging;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isInscriptionCollaboratorState(){
        return this.inscriptionCollaboratorState;
    }
    public void  setInscriptionCollaboratorState(boolean inscriptionCollaboratorState){
        this.inscriptionCollaboratorState = inscriptionCollaboratorState;
    }
    public boolean  isInscriptionCollaboratorType(){
        return this.inscriptionCollaboratorType;
    }
    public void  setInscriptionCollaboratorType(boolean inscriptionCollaboratorType){
        this.inscriptionCollaboratorType = inscriptionCollaboratorType;
    }
    public boolean  isInscriptionMembres(){
        return this.inscriptionMembres ;
    }
    public void  setInscriptionMembres(boolean inscriptionMembres ){
        this.inscriptionMembres  = inscriptionMembres ;
    }
}
