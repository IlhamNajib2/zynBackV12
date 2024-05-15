package  ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.PaimentCollaboratorStateCriteria;
import ma.zs.zyn.bean.core.project.PaimentCollaboratorState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentCollaboratorStateSpecification extends  AbstractSpecification<PaimentCollaboratorStateCriteria, PaimentCollaboratorState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public PaimentCollaboratorStateSpecification(PaimentCollaboratorStateCriteria criteria) {
        super(criteria);
    }

    public PaimentCollaboratorStateSpecification(PaimentCollaboratorStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
