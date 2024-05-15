package  ma.zs.zyn.ws.dto.inscription;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.collaborator.MemberDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionMembreDto  extends AuditBaseDto {

    private String inscriptionDate ;
    private BigDecimal consumedEntity  ;
    private BigDecimal consumedProjet  ;
    private BigDecimal consumedAttribut  ;
    private BigDecimal consumedIndicator  ;
    private BigDecimal affectedEntity  ;
    private BigDecimal affectedProjet  ;
    private BigDecimal affectedAttribut  ;
    private BigDecimal affectedIndicator  ;

    private MemberDto member ;
    private InscriptionMembreStateDto inscriptionMembreState ;
    private InscriptionCollaboratorDto inscriptionCollaborator ;



    public InscriptionMembreDto(){
        super();
    }



    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getInscriptionDate(){
        return this.inscriptionDate;
    }
    public void setInscriptionDate(String inscriptionDate){
        this.inscriptionDate = inscriptionDate;
    }

    @Log
    public BigDecimal getConsumedEntity(){
        return this.consumedEntity;
    }
    public void setConsumedEntity(BigDecimal consumedEntity){
        this.consumedEntity = consumedEntity;
    }

    @Log
    public BigDecimal getConsumedProjet(){
        return this.consumedProjet;
    }
    public void setConsumedProjet(BigDecimal consumedProjet){
        this.consumedProjet = consumedProjet;
    }

    @Log
    public BigDecimal getConsumedAttribut(){
        return this.consumedAttribut;
    }
    public void setConsumedAttribut(BigDecimal consumedAttribut){
        this.consumedAttribut = consumedAttribut;
    }

    @Log
    public BigDecimal getConsumedIndicator(){
        return this.consumedIndicator;
    }
    public void setConsumedIndicator(BigDecimal consumedIndicator){
        this.consumedIndicator = consumedIndicator;
    }

    @Log
    public BigDecimal getAffectedEntity(){
        return this.affectedEntity;
    }
    public void setAffectedEntity(BigDecimal affectedEntity){
        this.affectedEntity = affectedEntity;
    }

    @Log
    public BigDecimal getAffectedProjet(){
        return this.affectedProjet;
    }
    public void setAffectedProjet(BigDecimal affectedProjet){
        this.affectedProjet = affectedProjet;
    }

    @Log
    public BigDecimal getAffectedAttribut(){
        return this.affectedAttribut;
    }
    public void setAffectedAttribut(BigDecimal affectedAttribut){
        this.affectedAttribut = affectedAttribut;
    }

    @Log
    public BigDecimal getAffectedIndicator(){
        return this.affectedIndicator;
    }
    public void setAffectedIndicator(BigDecimal affectedIndicator){
        this.affectedIndicator = affectedIndicator;
    }


    public MemberDto getMember(){
        return this.member;
    }

    public void setMember(MemberDto member){
        this.member = member;
    }
    public InscriptionMembreStateDto getInscriptionMembreState(){
        return this.inscriptionMembreState;
    }

    public void setInscriptionMembreState(InscriptionMembreStateDto inscriptionMembreState){
        this.inscriptionMembreState = inscriptionMembreState;
    }
    public InscriptionCollaboratorDto getInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }

    public void setInscriptionCollaborator(InscriptionCollaboratorDto inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }






}
