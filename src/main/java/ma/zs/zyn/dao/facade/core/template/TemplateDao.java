package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.Template;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.template.Template;
import java.util.List;


@Repository
public interface TemplateDao extends AbstractRepository<Template,Long>  {
    Template findByCode(String code);
    int deleteByCode(String code);

    List<Template> findByCategoryTemplateId(Long id);
    int deleteByCategoryTemplateId(Long id);
    long countByCategoryTemplateId(Long id);
    List<Template> findByTypeTemplateId(Long id);
    int deleteByTypeTemplateId(Long id);
    long countByTypeTemplateId(Long id);
    List<Template> findByLevelTemplateId(Long id);
    int deleteByLevelTemplateId(Long id);
    long countByLevelTemplateId(Long id);
    List<Template> findByDomainTemplateId(Long id);
    int deleteByDomainTemplateId(Long id);
    long countByDomainTemplateId(Long id);
    List<Template> findByMemberId(Long id);
    int deleteByMemberId(Long id);
    long countByMemberId(Long id);
    List<Template> findByTechnologyId(Long id);
    int deleteByTechnologyId(Long id);
    long countByTechnologyId(Long id);

    @Query("SELECT NEW Template(item.id,item.name) FROM Template item")
    List<Template> findAllOptimized();

}
