package  ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.ProjectStateCriteria;
import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectStateSpecification extends  AbstractSpecification<ProjectStateCriteria, ProjectState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public ProjectStateSpecification(ProjectStateCriteria criteria) {
        super(criteria);
    }

    public ProjectStateSpecification(ProjectStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
