package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.LevelTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LevelTemplateDao extends AbstractRepository<LevelTemplate,Long>  {


    @Query("SELECT NEW LevelTemplate(item.id,item.name) FROM LevelTemplate item")
    List<LevelTemplate> findAllOptimized();

}
