package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.ProjectTemplateCriteria;
import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class ProjectTemplateSpecification extends  AbstractSpecification<ProjectTemplateCriteria, ProjectTemplate>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("template","id", criteria.getTemplate()==null?null:criteria.getTemplate().getId());
        addPredicateFk("template","id", criteria.getTemplates());
        addPredicateFk("template","code", criteria.getTemplate()==null?null:criteria.getTemplate().getCode());
        addPredicateFk("project","id", criteria.getProject()==null?null:criteria.getProject().getId());
        addPredicateFk("project","id", criteria.getProjects());
        addPredicateFk("project","code", criteria.getProject()==null?null:criteria.getProject().getCode());
    }

    public ProjectTemplateSpecification(ProjectTemplateCriteria criteria) {
        super(criteria);
    }

    public ProjectTemplateSpecification(ProjectTemplateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
