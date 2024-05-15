package ma.zs.zyn.dao.facade.core.packaging;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.packaging.Packaging;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PackagingDao extends AbstractRepository<Packaging,Long>  {

    List<Packaging> findByCategoryPackagingId(Long id);
    int deleteByCategoryPackagingId(Long id);
    long countByCategoryPackagingId(Long id);


}
