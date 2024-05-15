package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.DomainTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DomainTemplateDao extends AbstractRepository<DomainTemplate,Long>  {


    @Query("SELECT NEW DomainTemplate(item.id,item.name) FROM DomainTemplate item")
    List<DomainTemplate> findAllOptimized();

}
