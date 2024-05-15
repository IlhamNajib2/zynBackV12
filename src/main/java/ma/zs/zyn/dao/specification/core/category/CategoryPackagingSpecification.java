package  ma.zs.zyn.dao.specification.core.category;

import ma.zs.zyn.dao.criteria.core.category.CategoryPackagingCriteria;
import ma.zs.zyn.bean.core.category.CategoryPackaging;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CategoryPackagingSpecification extends  AbstractSpecification<CategoryPackagingCriteria, CategoryPackaging>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public CategoryPackagingSpecification(CategoryPackagingCriteria criteria) {
        super(criteria);
    }

    public CategoryPackagingSpecification(CategoryPackagingCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
