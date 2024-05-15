package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.CategoryTemplateCriteria;
import ma.zs.zyn.bean.core.template.CategoryTemplate;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CategoryTemplateSpecification extends  AbstractSpecification<CategoryTemplateCriteria, CategoryTemplate>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public CategoryTemplateSpecification(CategoryTemplateCriteria criteria) {
        super(criteria);
    }

    public CategoryTemplateSpecification(CategoryTemplateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
