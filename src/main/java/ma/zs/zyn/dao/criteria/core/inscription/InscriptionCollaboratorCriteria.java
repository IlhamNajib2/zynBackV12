package  ma.zs.zyn.dao.criteria.core.inscription;


import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class InscriptionCollaboratorCriteria extends  BaseCriteria  {

    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private LocalDateTime renewDate;
    private LocalDateTime renewDateFrom;
    private LocalDateTime renewDateTo;
    private String consumedEntity;
    private String consumedEntityMin;
    private String consumedEntityMax;
    private String consumedProjet;
    private String consumedProjetMin;
    private String consumedProjetMax;
    private String consumedAttribut;
    private String consumedAttributMin;
    private String consumedAttributMax;
    private String consumedIndicator;
    private String consumedIndicatorMin;
    private String consumedIndicatorMax;

    private PackagingCriteria packaging ;
    private List<PackagingCriteria> packagings ;
    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private InscriptionCollaboratorStateCriteria inscriptionCollaboratorState ;
    private List<InscriptionCollaboratorStateCriteria> inscriptionCollaboratorStates ;
    private InscriptionCollaboratorTypeCriteria inscriptionCollaboratorType ;
    private List<InscriptionCollaboratorTypeCriteria> inscriptionCollaboratorTypes ;


    public InscriptionCollaboratorCriteria(){}

    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getStartDateFrom(){
        return this.startDateFrom;
    }
    public void setStartDateFrom(LocalDateTime startDateFrom){
        this.startDateFrom = startDateFrom;
    }
    public LocalDateTime getStartDateTo(){
        return this.startDateTo;
    }
    public void setStartDateTo(LocalDateTime startDateTo){
        this.startDateTo = startDateTo;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getEndDateFrom(){
        return this.endDateFrom;
    }
    public void setEndDateFrom(LocalDateTime endDateFrom){
        this.endDateFrom = endDateFrom;
    }
    public LocalDateTime getEndDateTo(){
        return this.endDateTo;
    }
    public void setEndDateTo(LocalDateTime endDateTo){
        this.endDateTo = endDateTo;
    }
    public LocalDateTime getRenewDate(){
        return this.renewDate;
    }
    public void setRenewDate(LocalDateTime renewDate){
        this.renewDate = renewDate;
    }
    public LocalDateTime getRenewDateFrom(){
        return this.renewDateFrom;
    }
    public void setRenewDateFrom(LocalDateTime renewDateFrom){
        this.renewDateFrom = renewDateFrom;
    }
    public LocalDateTime getRenewDateTo(){
        return this.renewDateTo;
    }
    public void setRenewDateTo(LocalDateTime renewDateTo){
        this.renewDateTo = renewDateTo;
    }
    public String getConsumedEntity(){
        return this.consumedEntity;
    }
    public void setConsumedEntity(String consumedEntity){
        this.consumedEntity = consumedEntity;
    }   
    public String getConsumedEntityMin(){
        return this.consumedEntityMin;
    }
    public void setConsumedEntityMin(String consumedEntityMin){
        this.consumedEntityMin = consumedEntityMin;
    }
    public String getConsumedEntityMax(){
        return this.consumedEntityMax;
    }
    public void setConsumedEntityMax(String consumedEntityMax){
        this.consumedEntityMax = consumedEntityMax;
    }
      
    public String getConsumedProjet(){
        return this.consumedProjet;
    }
    public void setConsumedProjet(String consumedProjet){
        this.consumedProjet = consumedProjet;
    }   
    public String getConsumedProjetMin(){
        return this.consumedProjetMin;
    }
    public void setConsumedProjetMin(String consumedProjetMin){
        this.consumedProjetMin = consumedProjetMin;
    }
    public String getConsumedProjetMax(){
        return this.consumedProjetMax;
    }
    public void setConsumedProjetMax(String consumedProjetMax){
        this.consumedProjetMax = consumedProjetMax;
    }
      
    public String getConsumedAttribut(){
        return this.consumedAttribut;
    }
    public void setConsumedAttribut(String consumedAttribut){
        this.consumedAttribut = consumedAttribut;
    }   
    public String getConsumedAttributMin(){
        return this.consumedAttributMin;
    }
    public void setConsumedAttributMin(String consumedAttributMin){
        this.consumedAttributMin = consumedAttributMin;
    }
    public String getConsumedAttributMax(){
        return this.consumedAttributMax;
    }
    public void setConsumedAttributMax(String consumedAttributMax){
        this.consumedAttributMax = consumedAttributMax;
    }
      
    public String getConsumedIndicator(){
        return this.consumedIndicator;
    }
    public void setConsumedIndicator(String consumedIndicator){
        this.consumedIndicator = consumedIndicator;
    }   
    public String getConsumedIndicatorMin(){
        return this.consumedIndicatorMin;
    }
    public void setConsumedIndicatorMin(String consumedIndicatorMin){
        this.consumedIndicatorMin = consumedIndicatorMin;
    }
    public String getConsumedIndicatorMax(){
        return this.consumedIndicatorMax;
    }
    public void setConsumedIndicatorMax(String consumedIndicatorMax){
        this.consumedIndicatorMax = consumedIndicatorMax;
    }
      

    public PackagingCriteria getPackaging(){
        return this.packaging;
    }

    public void setPackaging(PackagingCriteria packaging){
        this.packaging = packaging;
    }
    public List<PackagingCriteria> getPackagings(){
        return this.packagings;
    }

    public void setPackagings(List<PackagingCriteria> packagings){
        this.packagings = packagings;
    }
    public CollaboratorCriteria getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorCriteria collaborator){
        this.collaborator = collaborator;
    }
    public List<CollaboratorCriteria> getCollaborators(){
        return this.collaborators;
    }

    public void setCollaborators(List<CollaboratorCriteria> collaborators){
        this.collaborators = collaborators;
    }
    public InscriptionCollaboratorStateCriteria getInscriptionCollaboratorState(){
        return this.inscriptionCollaboratorState;
    }

    public void setInscriptionCollaboratorState(InscriptionCollaboratorStateCriteria inscriptionCollaboratorState){
        this.inscriptionCollaboratorState = inscriptionCollaboratorState;
    }
    public List<InscriptionCollaboratorStateCriteria> getInscriptionCollaboratorStates(){
        return this.inscriptionCollaboratorStates;
    }

    public void setInscriptionCollaboratorStates(List<InscriptionCollaboratorStateCriteria> inscriptionCollaboratorStates){
        this.inscriptionCollaboratorStates = inscriptionCollaboratorStates;
    }
    public InscriptionCollaboratorTypeCriteria getInscriptionCollaboratorType(){
        return this.inscriptionCollaboratorType;
    }

    public void setInscriptionCollaboratorType(InscriptionCollaboratorTypeCriteria inscriptionCollaboratorType){
        this.inscriptionCollaboratorType = inscriptionCollaboratorType;
    }
    public List<InscriptionCollaboratorTypeCriteria> getInscriptionCollaboratorTypes(){
        return this.inscriptionCollaboratorTypes;
    }

    public void setInscriptionCollaboratorTypes(List<InscriptionCollaboratorTypeCriteria> inscriptionCollaboratorTypes){
        this.inscriptionCollaboratorTypes = inscriptionCollaboratorTypes;
    }
}
