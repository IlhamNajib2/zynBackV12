package  ma.zs.zyn.dao.specification.core.inscription;

import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorTypeCriteria;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorType;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InscriptionCollaboratorTypeSpecification extends  AbstractSpecification<InscriptionCollaboratorTypeCriteria, InscriptionCollaboratorType>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public InscriptionCollaboratorTypeSpecification(InscriptionCollaboratorTypeCriteria criteria) {
        super(criteria);
    }

    public InscriptionCollaboratorTypeSpecification(InscriptionCollaboratorTypeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
