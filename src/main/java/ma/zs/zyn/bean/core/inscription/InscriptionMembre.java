package ma.zs.zyn.bean.core.inscription;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.collaborator.Member;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "inscription_membre")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="inscription_membre_seq",sequenceName="inscription_membre_seq",allocationSize=1, initialValue = 1)
public class InscriptionMembre  extends BaseEntity     {

    private Long id;



    private LocalDateTime inscriptionDate ;

    private BigDecimal consumedEntity = BigDecimal.ZERO;

    private BigDecimal consumedProjet = BigDecimal.ZERO;

    private BigDecimal consumedAttribut = BigDecimal.ZERO;

    private BigDecimal consumedIndicator = BigDecimal.ZERO;

    private BigDecimal affectedEntity = BigDecimal.ZERO;

    private BigDecimal affectedProjet = BigDecimal.ZERO;

    private BigDecimal affectedAttribut = BigDecimal.ZERO;

    private BigDecimal affectedIndicator = BigDecimal.ZERO;

    private Member member ;
    private InscriptionMembreState inscriptionMembreState ;
    private InscriptionCollaborator inscriptionCollaborator ;


    public InscriptionMembre(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="inscription_membre_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public LocalDateTime getInscriptionDate(){
        return this.inscriptionDate;
    }
    public void setInscriptionDate(LocalDateTime inscriptionDate){
        this.inscriptionDate = inscriptionDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    public Member getMember(){
        return this.member;
    }
    public void setMember(Member member){
        this.member = member;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_membre_state")
    public InscriptionMembreState getInscriptionMembreState(){
        return this.inscriptionMembreState;
    }
    public void setInscriptionMembreState(InscriptionMembreState inscriptionMembreState){
        this.inscriptionMembreState = inscriptionMembreState;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_collaborator")
    public InscriptionCollaborator getInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }
    public void setInscriptionCollaborator(InscriptionCollaborator inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }
    public BigDecimal getConsumedEntity(){
        return this.consumedEntity;
    }
    public void setConsumedEntity(BigDecimal consumedEntity){
        this.consumedEntity = consumedEntity;
    }
    public BigDecimal getConsumedProjet(){
        return this.consumedProjet;
    }
    public void setConsumedProjet(BigDecimal consumedProjet){
        this.consumedProjet = consumedProjet;
    }
    public BigDecimal getConsumedAttribut(){
        return this.consumedAttribut;
    }
    public void setConsumedAttribut(BigDecimal consumedAttribut){
        this.consumedAttribut = consumedAttribut;
    }
    public BigDecimal getConsumedIndicator(){
        return this.consumedIndicator;
    }
    public void setConsumedIndicator(BigDecimal consumedIndicator){
        this.consumedIndicator = consumedIndicator;
    }
    public BigDecimal getAffectedEntity(){
        return this.affectedEntity;
    }
    public void setAffectedEntity(BigDecimal affectedEntity){
        this.affectedEntity = affectedEntity;
    }
    public BigDecimal getAffectedProjet(){
        return this.affectedProjet;
    }
    public void setAffectedProjet(BigDecimal affectedProjet){
        this.affectedProjet = affectedProjet;
    }
    public BigDecimal getAffectedAttribut(){
        return this.affectedAttribut;
    }
    public void setAffectedAttribut(BigDecimal affectedAttribut){
        this.affectedAttribut = affectedAttribut;
    }
    public BigDecimal getAffectedIndicator(){
        return this.affectedIndicator;
    }
    public void setAffectedIndicator(BigDecimal affectedIndicator){
        this.affectedIndicator = affectedIndicator;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscriptionMembre inscriptionMembre = (InscriptionMembre) o;
        return id != null && id.equals(inscriptionMembre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

