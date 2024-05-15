package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.LevelTemplateCriteria;
import ma.zs.zyn.bean.core.template.LevelTemplate;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class LevelTemplateSpecification extends  AbstractSpecification<LevelTemplateCriteria, LevelTemplate>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
    }

    public LevelTemplateSpecification(LevelTemplateCriteria criteria) {
        super(criteria);
    }

    public LevelTemplateSpecification(LevelTemplateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
