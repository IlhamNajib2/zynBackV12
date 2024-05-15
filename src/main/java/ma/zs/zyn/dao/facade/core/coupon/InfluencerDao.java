package ma.zs.zyn.dao.facade.core.coupon;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.Influencer;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InfluencerDao extends AbstractRepository<Influencer,Long>  {

    Influencer findByUsername(String username);


}
