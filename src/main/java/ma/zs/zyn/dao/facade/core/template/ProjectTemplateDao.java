package ma.zs.zyn.dao.facade.core.template;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.ProjectTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProjectTemplateDao extends AbstractRepository<ProjectTemplate,Long>  {

    List<ProjectTemplate> findByTemplateId(Long id);
    int deleteByTemplateId(Long id);
    long countByTemplateCode(String code);
    List<ProjectTemplate> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectCode(String code);


}
