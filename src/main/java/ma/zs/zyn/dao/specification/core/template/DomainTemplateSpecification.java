package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.DomainTemplateCriteria;
import ma.zs.zyn.bean.core.template.DomainTemplate;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class DomainTemplateSpecification extends  AbstractSpecification<DomainTemplateCriteria, DomainTemplate>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public DomainTemplateSpecification(DomainTemplateCriteria criteria) {
        super(criteria);
    }

    public DomainTemplateSpecification(DomainTemplateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
