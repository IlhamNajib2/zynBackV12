package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.TechnologyCriteria;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class TechnologySpecification extends  AbstractSpecification<TechnologyCriteria, Technology>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("logo", criteria.getLogo(),criteria.getLogoLike());
        addPredicateFk("typeTemplate","id", criteria.getTypeTemplate()==null?null:criteria.getTypeTemplate().getId());
        addPredicateFk("typeTemplate","id", criteria.getTypeTemplates());
    }

    public TechnologySpecification(TechnologyCriteria criteria) {
        super(criteria);
    }

    public TechnologySpecification(TechnologyCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
