package  ma.zs.zyn.dao.specification.core.project;

import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectSpecification extends  AbstractSpecification<ProjectCriteria, Project>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("generatedDate", criteria.getGeneratedDate(), criteria.getGeneratedDateFrom(), criteria.getGeneratedDateTo());
        addPredicateFk("projectState","id", criteria.getProjectState()==null?null:criteria.getProjectState().getId());
        addPredicateFk("projectState","id", criteria.getProjectStates());
        addPredicateFk("projectState","code", criteria.getProjectState()==null?null:criteria.getProjectState().getCode());
        addPredicateFk("inscriptionMembre","id", criteria.getInscriptionMembre()==null?null:criteria.getInscriptionMembre().getId());
        addPredicateFk("inscriptionMembre","id", criteria.getInscriptionMembres());
        addPredicateFk("domainTemplate","id", criteria.getDomainTemplate()==null?null:criteria.getDomainTemplate().getId());
        addPredicateFk("domainTemplate","id", criteria.getDomainTemplates());
    }

    public ProjectSpecification(ProjectCriteria criteria) {
        super(criteria);
    }

    public ProjectSpecification(ProjectCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
