package  ma.zs.zyn.dao.criteria.core.inscription;


import ma.zs.zyn.dao.criteria.core.collaborator.MemberCriteria;

import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class InscriptionMembreCriteria extends  BaseCriteria  {

    private LocalDateTime inscriptionDate;
    private LocalDateTime inscriptionDateFrom;
    private LocalDateTime inscriptionDateTo;
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
    private String affectedEntity;
    private String affectedEntityMin;
    private String affectedEntityMax;
    private String affectedProjet;
    private String affectedProjetMin;
    private String affectedProjetMax;
    private String affectedAttribut;
    private String affectedAttributMin;
    private String affectedAttributMax;
    private String affectedIndicator;
    private String affectedIndicatorMin;
    private String affectedIndicatorMax;

    private MemberCriteria member ;
    private List<MemberCriteria> members ;
    private InscriptionMembreStateCriteria inscriptionMembreState ;
    private List<InscriptionMembreStateCriteria> inscriptionMembreStates ;
    private InscriptionCollaboratorCriteria inscriptionCollaborator ;
    private List<InscriptionCollaboratorCriteria> inscriptionCollaborators ;


    public InscriptionMembreCriteria(){}

    public LocalDateTime getInscriptionDate(){
        return this.inscriptionDate;
    }
    public void setInscriptionDate(LocalDateTime inscriptionDate){
        this.inscriptionDate = inscriptionDate;
    }
    public LocalDateTime getInscriptionDateFrom(){
        return this.inscriptionDateFrom;
    }
    public void setInscriptionDateFrom(LocalDateTime inscriptionDateFrom){
        this.inscriptionDateFrom = inscriptionDateFrom;
    }
    public LocalDateTime getInscriptionDateTo(){
        return this.inscriptionDateTo;
    }
    public void setInscriptionDateTo(LocalDateTime inscriptionDateTo){
        this.inscriptionDateTo = inscriptionDateTo;
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
      
    public String getAffectedEntity(){
        return this.affectedEntity;
    }
    public void setAffectedEntity(String affectedEntity){
        this.affectedEntity = affectedEntity;
    }   
    public String getAffectedEntityMin(){
        return this.affectedEntityMin;
    }
    public void setAffectedEntityMin(String affectedEntityMin){
        this.affectedEntityMin = affectedEntityMin;
    }
    public String getAffectedEntityMax(){
        return this.affectedEntityMax;
    }
    public void setAffectedEntityMax(String affectedEntityMax){
        this.affectedEntityMax = affectedEntityMax;
    }
      
    public String getAffectedProjet(){
        return this.affectedProjet;
    }
    public void setAffectedProjet(String affectedProjet){
        this.affectedProjet = affectedProjet;
    }   
    public String getAffectedProjetMin(){
        return this.affectedProjetMin;
    }
    public void setAffectedProjetMin(String affectedProjetMin){
        this.affectedProjetMin = affectedProjetMin;
    }
    public String getAffectedProjetMax(){
        return this.affectedProjetMax;
    }
    public void setAffectedProjetMax(String affectedProjetMax){
        this.affectedProjetMax = affectedProjetMax;
    }
      
    public String getAffectedAttribut(){
        return this.affectedAttribut;
    }
    public void setAffectedAttribut(String affectedAttribut){
        this.affectedAttribut = affectedAttribut;
    }   
    public String getAffectedAttributMin(){
        return this.affectedAttributMin;
    }
    public void setAffectedAttributMin(String affectedAttributMin){
        this.affectedAttributMin = affectedAttributMin;
    }
    public String getAffectedAttributMax(){
        return this.affectedAttributMax;
    }
    public void setAffectedAttributMax(String affectedAttributMax){
        this.affectedAttributMax = affectedAttributMax;
    }
      
    public String getAffectedIndicator(){
        return this.affectedIndicator;
    }
    public void setAffectedIndicator(String affectedIndicator){
        this.affectedIndicator = affectedIndicator;
    }   
    public String getAffectedIndicatorMin(){
        return this.affectedIndicatorMin;
    }
    public void setAffectedIndicatorMin(String affectedIndicatorMin){
        this.affectedIndicatorMin = affectedIndicatorMin;
    }
    public String getAffectedIndicatorMax(){
        return this.affectedIndicatorMax;
    }
    public void setAffectedIndicatorMax(String affectedIndicatorMax){
        this.affectedIndicatorMax = affectedIndicatorMax;
    }
      

    public MemberCriteria getMember(){
        return this.member;
    }

    public void setMember(MemberCriteria member){
        this.member = member;
    }
    public List<MemberCriteria> getMembers(){
        return this.members;
    }

    public void setMembers(List<MemberCriteria> members){
        this.members = members;
    }
    public InscriptionMembreStateCriteria getInscriptionMembreState(){
        return this.inscriptionMembreState;
    }

    public void setInscriptionMembreState(InscriptionMembreStateCriteria inscriptionMembreState){
        this.inscriptionMembreState = inscriptionMembreState;
    }
    public List<InscriptionMembreStateCriteria> getInscriptionMembreStates(){
        return this.inscriptionMembreStates;
    }

    public void setInscriptionMembreStates(List<InscriptionMembreStateCriteria> inscriptionMembreStates){
        this.inscriptionMembreStates = inscriptionMembreStates;
    }
    public InscriptionCollaboratorCriteria getInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }

    public void setInscriptionCollaborator(InscriptionCollaboratorCriteria inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }
    public List<InscriptionCollaboratorCriteria> getInscriptionCollaborators(){
        return this.inscriptionCollaborators;
    }

    public void setInscriptionCollaborators(List<InscriptionCollaboratorCriteria> inscriptionCollaborators){
        this.inscriptionCollaborators = inscriptionCollaborators;
    }
}
