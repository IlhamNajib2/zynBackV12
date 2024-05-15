package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.TemplateCriteria;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class TemplateSpecification extends  AbstractSpecification<TemplateCriteria, Template>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("addingDate", criteria.getAddingDate(), criteria.getAddingDateFrom(), criteria.getAddingDateTo());
        addPredicate("lastUpdateDate", criteria.getLastUpdateDate(), criteria.getLastUpdateDateFrom(), criteria.getLastUpdateDateTo());
        addPredicate("templateTags", criteria.getTemplateTags(),criteria.getTemplateTagsLike());
        addPredicateBigDecimal("price", criteria.getPrice(), criteria.getPriceMin(), criteria.getPriceMax());
        addPredicateFk("categoryTemplate","id", criteria.getCategoryTemplate()==null?null:criteria.getCategoryTemplate().getId());
        addPredicateFk("categoryTemplate","id", criteria.getCategoryTemplates());
        addPredicateFk("typeTemplate","id", criteria.getTypeTemplate()==null?null:criteria.getTypeTemplate().getId());
        addPredicateFk("typeTemplate","id", criteria.getTypeTemplates());
        addPredicateFk("levelTemplate","id", criteria.getLevelTemplate()==null?null:criteria.getLevelTemplate().getId());
        addPredicateFk("levelTemplate","id", criteria.getLevelTemplates());
        addPredicateFk("domainTemplate","id", criteria.getDomainTemplate()==null?null:criteria.getDomainTemplate().getId());
        addPredicateFk("domainTemplate","id", criteria.getDomainTemplates());
        addPredicateFk("member","id", criteria.getMember()==null?null:criteria.getMember().getId());
        addPredicateFk("member","id", criteria.getMembers());
        addPredicateFk("technology","id", criteria.getTechnology()==null?null:criteria.getTechnology().getId());
        addPredicateFk("technology","id", criteria.getTechnologys());
    }

    public TemplateSpecification(TemplateCriteria criteria) {
        super(criteria);
    }

    public TemplateSpecification(TemplateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
