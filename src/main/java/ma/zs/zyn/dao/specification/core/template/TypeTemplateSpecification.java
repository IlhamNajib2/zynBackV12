package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.TypeTemplateCriteria;
import ma.zs.zyn.bean.core.template.TypeTemplate;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class TypeTemplateSpecification extends  AbstractSpecification<TypeTemplateCriteria, TypeTemplate>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public TypeTemplateSpecification(TypeTemplateCriteria criteria) {
        super(criteria);
    }

    public TypeTemplateSpecification(TypeTemplateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
