package  ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.PaimentInfluencerStateCriteria;
import ma.zs.zyn.bean.core.project.PaimentInfluencerState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentInfluencerStateSpecification extends  AbstractSpecification<PaimentInfluencerStateCriteria, PaimentInfluencerState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public PaimentInfluencerStateSpecification(PaimentInfluencerStateCriteria criteria) {
        super(criteria);
    }

    public PaimentInfluencerStateSpecification(PaimentInfluencerStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
