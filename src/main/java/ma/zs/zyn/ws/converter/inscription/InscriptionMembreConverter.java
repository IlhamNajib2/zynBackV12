package  ma.zs.zyn.ws.converter.inscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.inscription.InscriptionMembreStateConverter;
import ma.zs.zyn.ws.converter.inscription.InscriptionCollaboratorConverter;
import ma.zs.zyn.ws.converter.collaborator.MemberConverter;

import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;


import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.inscription.InscriptionMembre;
import ma.zs.zyn.ws.dto.inscription.InscriptionMembreDto;

@Component
public class InscriptionMembreConverter {

    @Autowired
    private InscriptionMembreStateConverter inscriptionMembreStateConverter ;
    @Autowired
    private InscriptionCollaboratorConverter inscriptionCollaboratorConverter ;
    @Autowired
    private MemberConverter memberConverter ;
    private boolean member;
    private boolean inscriptionMembreState;
    private boolean inscriptionCollaborator;

    public  InscriptionMembreConverter() {
        initObject(true);
    }


    public InscriptionMembre toItem(InscriptionMembreDto dto) {
        if (dto == null) {
            return null;
        } else {
        InscriptionMembre item = new InscriptionMembre();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getInscriptionDate()))
                item.setInscriptionDate(DateUtil.stringEnToDate(dto.getInscriptionDate()));
            if(StringUtil.isNotEmpty(dto.getConsumedEntity()))
                item.setConsumedEntity(dto.getConsumedEntity());
            if(StringUtil.isNotEmpty(dto.getConsumedProjet()))
                item.setConsumedProjet(dto.getConsumedProjet());
            if(StringUtil.isNotEmpty(dto.getConsumedAttribut()))
                item.setConsumedAttribut(dto.getConsumedAttribut());
            if(StringUtil.isNotEmpty(dto.getConsumedIndicator()))
                item.setConsumedIndicator(dto.getConsumedIndicator());
            if(StringUtil.isNotEmpty(dto.getAffectedEntity()))
                item.setAffectedEntity(dto.getAffectedEntity());
            if(StringUtil.isNotEmpty(dto.getAffectedProjet()))
                item.setAffectedProjet(dto.getAffectedProjet());
            if(StringUtil.isNotEmpty(dto.getAffectedAttribut()))
                item.setAffectedAttribut(dto.getAffectedAttribut());
            if(StringUtil.isNotEmpty(dto.getAffectedIndicator()))
                item.setAffectedIndicator(dto.getAffectedIndicator());
            if(this.member && dto.getMember()!=null)
                item.setMember(memberConverter.toItem(dto.getMember())) ;

            if(this.inscriptionMembreState && dto.getInscriptionMembreState()!=null)
                item.setInscriptionMembreState(inscriptionMembreStateConverter.toItem(dto.getInscriptionMembreState())) ;

            if(dto.getInscriptionCollaborator() != null && dto.getInscriptionCollaborator().getId() != null){
                item.setInscriptionCollaborator(new InscriptionCollaborator());
                item.getInscriptionCollaborator().setId(dto.getInscriptionCollaborator().getId());
                item.getInscriptionCollaborator().setId(dto.getInscriptionCollaborator().getId());
            }




        return item;
        }
    }


    public InscriptionMembreDto toDto(InscriptionMembre item) {
        if (item == null) {
            return null;
        } else {
            InscriptionMembreDto dto = new InscriptionMembreDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getInscriptionDate()!=null)
                dto.setInscriptionDate(DateUtil.dateTimeToString(item.getInscriptionDate()));
            if(StringUtil.isNotEmpty(item.getConsumedEntity()))
                dto.setConsumedEntity(item.getConsumedEntity());
            if(StringUtil.isNotEmpty(item.getConsumedProjet()))
                dto.setConsumedProjet(item.getConsumedProjet());
            if(StringUtil.isNotEmpty(item.getConsumedAttribut()))
                dto.setConsumedAttribut(item.getConsumedAttribut());
            if(StringUtil.isNotEmpty(item.getConsumedIndicator()))
                dto.setConsumedIndicator(item.getConsumedIndicator());
            if(StringUtil.isNotEmpty(item.getAffectedEntity()))
                dto.setAffectedEntity(item.getAffectedEntity());
            if(StringUtil.isNotEmpty(item.getAffectedProjet()))
                dto.setAffectedProjet(item.getAffectedProjet());
            if(StringUtil.isNotEmpty(item.getAffectedAttribut()))
                dto.setAffectedAttribut(item.getAffectedAttribut());
            if(StringUtil.isNotEmpty(item.getAffectedIndicator()))
                dto.setAffectedIndicator(item.getAffectedIndicator());
            if(this.member && item.getMember()!=null) {
                dto.setMember(memberConverter.toDto(item.getMember())) ;

            }
            if(this.inscriptionMembreState && item.getInscriptionMembreState()!=null) {
                dto.setInscriptionMembreState(inscriptionMembreStateConverter.toDto(item.getInscriptionMembreState())) ;

            }
            if(this.inscriptionCollaborator && item.getInscriptionCollaborator()!=null) {
                dto.setInscriptionCollaborator(inscriptionCollaboratorConverter.toDto(item.getInscriptionCollaborator())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.member = value;
        this.inscriptionMembreState = value;
        this.inscriptionCollaborator = value;
    }
	
    public List<InscriptionMembre> toItem(List<InscriptionMembreDto> dtos) {
        List<InscriptionMembre> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionMembreDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionMembreDto> toDto(List<InscriptionMembre> items) {
        List<InscriptionMembreDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InscriptionMembre item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionMembreDto dto, InscriptionMembre t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getMember() != null)
        memberConverter.copy(dto.getMember(), t.getMember());
        if (dto.getInscriptionMembreState() != null)
        inscriptionMembreStateConverter.copy(dto.getInscriptionMembreState(), t.getInscriptionMembreState());
        if (dto.getInscriptionCollaborator() != null)
        inscriptionCollaboratorConverter.copy(dto.getInscriptionCollaborator(), t.getInscriptionCollaborator());
    }

    public List<InscriptionMembre> copy(List<InscriptionMembreDto> dtos) {
        List<InscriptionMembre> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionMembreDto dto : dtos) {
                InscriptionMembre instance = new InscriptionMembre();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public InscriptionMembreStateConverter getInscriptionMembreStateConverter(){
        return this.inscriptionMembreStateConverter;
    }
    public void setInscriptionMembreStateConverter(InscriptionMembreStateConverter inscriptionMembreStateConverter ){
        this.inscriptionMembreStateConverter = inscriptionMembreStateConverter;
    }
    public InscriptionCollaboratorConverter getInscriptionCollaboratorConverter(){
        return this.inscriptionCollaboratorConverter;
    }
    public void setInscriptionCollaboratorConverter(InscriptionCollaboratorConverter inscriptionCollaboratorConverter ){
        this.inscriptionCollaboratorConverter = inscriptionCollaboratorConverter;
    }
    public MemberConverter getMemberConverter(){
        return this.memberConverter;
    }
    public void setMemberConverter(MemberConverter memberConverter ){
        this.memberConverter = memberConverter;
    }
    public boolean  isMember(){
        return this.member;
    }
    public void  setMember(boolean member){
        this.member = member;
    }
    public boolean  isInscriptionMembreState(){
        return this.inscriptionMembreState;
    }
    public void  setInscriptionMembreState(boolean inscriptionMembreState){
        this.inscriptionMembreState = inscriptionMembreState;
    }
    public boolean  isInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }
    public void  setInscriptionCollaborator(boolean inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }
}
