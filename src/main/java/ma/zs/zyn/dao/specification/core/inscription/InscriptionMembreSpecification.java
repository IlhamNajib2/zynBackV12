package  ma.zs.zyn.dao.specification.core.inscription;

import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreCriteria;
import ma.zs.zyn.bean.core.inscription.InscriptionMembre;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InscriptionMembreSpecification extends  AbstractSpecification<InscriptionMembreCriteria, InscriptionMembre>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("inscriptionDate", criteria.getInscriptionDate(), criteria.getInscriptionDateFrom(), criteria.getInscriptionDateTo());
        addPredicateBigDecimal("consumedEntity", criteria.getConsumedEntity(), criteria.getConsumedEntityMin(), criteria.getConsumedEntityMax());
        addPredicateBigDecimal("consumedProjet", criteria.getConsumedProjet(), criteria.getConsumedProjetMin(), criteria.getConsumedProjetMax());
        addPredicateBigDecimal("consumedAttribut", criteria.getConsumedAttribut(), criteria.getConsumedAttributMin(), criteria.getConsumedAttributMax());
        addPredicateBigDecimal("consumedIndicator", criteria.getConsumedIndicator(), criteria.getConsumedIndicatorMin(), criteria.getConsumedIndicatorMax());
        addPredicateBigDecimal("affectedEntity", criteria.getAffectedEntity(), criteria.getAffectedEntityMin(), criteria.getAffectedEntityMax());
        addPredicateBigDecimal("affectedProjet", criteria.getAffectedProjet(), criteria.getAffectedProjetMin(), criteria.getAffectedProjetMax());
        addPredicateBigDecimal("affectedAttribut", criteria.getAffectedAttribut(), criteria.getAffectedAttributMin(), criteria.getAffectedAttributMax());
        addPredicateBigDecimal("affectedIndicator", criteria.getAffectedIndicator(), criteria.getAffectedIndicatorMin(), criteria.getAffectedIndicatorMax());
        addPredicateFk("member","id", criteria.getMember()==null?null:criteria.getMember().getId());
        addPredicateFk("member","id", criteria.getMembers());
        addPredicateFk("inscriptionMembreState","id", criteria.getInscriptionMembreState()==null?null:criteria.getInscriptionMembreState().getId());
        addPredicateFk("inscriptionMembreState","id", criteria.getInscriptionMembreStates());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborator()==null?null:criteria.getInscriptionCollaborator().getId());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborators());
    }

    public InscriptionMembreSpecification(InscriptionMembreCriteria criteria) {
        super(criteria);
    }

    public InscriptionMembreSpecification(InscriptionMembreCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
