package ma.zs.zyn.dao.facade.core.paiment;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.paiment.PaimentInfluencer;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PaimentInfluencerDao extends AbstractRepository<PaimentInfluencer,Long>  {

    List<PaimentInfluencer> findByInfluencerId(Long id);
    int deleteByInfluencerId(Long id);
    long countByInfluencerId(Long id);
    List<PaimentInfluencer> findByCouponId(Long id);
    int deleteByCouponId(Long id);
    long countByCouponCode(String code);
    List<PaimentInfluencer> findByPaimentInfluencerStateCode(String code);
            public int deleteByPaimentInfluencerStateCode(String code);
    long countByPaimentInfluencerStateCode(String code);

    @Query("SELECT NEW PaimentInfluencer(item.id,item.name) FROM PaimentInfluencer item")
    List<PaimentInfluencer> findAllOptimized();

}
