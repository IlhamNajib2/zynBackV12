package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.TypeTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TypeTemplateDao extends AbstractRepository<TypeTemplate,Long>  {


    @Query("SELECT NEW TypeTemplate(item.id,item.name) FROM TypeTemplate item")
    List<TypeTemplate> findAllOptimized();

}
