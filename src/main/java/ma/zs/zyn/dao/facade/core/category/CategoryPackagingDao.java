package ma.zs.zyn.dao.facade.core.category;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.category.CategoryPackaging;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CategoryPackagingDao extends AbstractRepository<CategoryPackaging,Long>  {


    @Query("SELECT NEW CategoryPackaging(item.id,item.name) FROM CategoryPackaging item")
    List<CategoryPackaging> findAllOptimized();

}
