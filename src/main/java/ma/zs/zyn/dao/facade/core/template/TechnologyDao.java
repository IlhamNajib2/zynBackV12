package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.Technology;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TechnologyDao extends AbstractRepository<Technology,Long>  {

    List<Technology> findByTypeTemplateId(Long id);
    int deleteByTypeTemplateId(Long id);
    long countByTypeTemplateId(Long id);

    @Query("SELECT NEW Technology(item.id,item.name) FROM Technology item")
    List<Technology> findAllOptimized();

}
