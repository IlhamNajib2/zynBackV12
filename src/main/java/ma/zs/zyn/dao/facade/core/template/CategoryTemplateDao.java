package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.CategoryTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CategoryTemplateDao extends AbstractRepository<CategoryTemplate,Long>  {


    @Query("SELECT NEW CategoryTemplate(item.id,item.name) FROM CategoryTemplate item")
    List<CategoryTemplate> findAllOptimized();

}
