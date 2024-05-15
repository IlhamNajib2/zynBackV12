package  ma.zs.zyn.dao.specification.core.inscription;

import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorStateCriteria;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InscriptionCollaboratorStateSpecification extends  AbstractSpecification<InscriptionCollaboratorStateCriteria, InscriptionCollaboratorState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public InscriptionCollaboratorStateSpecification(InscriptionCollaboratorStateCriteria criteria) {
        super(criteria);
    }

    public InscriptionCollaboratorStateSpecification(InscriptionCollaboratorStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
