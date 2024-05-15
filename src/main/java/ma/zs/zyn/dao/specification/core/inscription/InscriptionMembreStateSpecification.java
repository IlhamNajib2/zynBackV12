package  ma.zs.zyn.dao.specification.core.inscription;

import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreStateCriteria;
import ma.zs.zyn.bean.core.inscription.InscriptionMembreState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InscriptionMembreStateSpecification extends  AbstractSpecification<InscriptionMembreStateCriteria, InscriptionMembreState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public InscriptionMembreStateSpecification(InscriptionMembreStateCriteria criteria) {
        super(criteria);
    }

    public InscriptionMembreStateSpecification(InscriptionMembreStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
