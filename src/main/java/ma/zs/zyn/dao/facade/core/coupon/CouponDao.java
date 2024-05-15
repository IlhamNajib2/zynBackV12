package ma.zs.zyn.dao.facade.core.coupon;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.Coupon;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.coupon.Coupon;
import java.util.List;


@Repository
public interface CouponDao extends AbstractRepository<Coupon,Long>  {
    Coupon findByCode(String code);
    int deleteByCode(String code);

    List<Coupon> findByInfluencerId(Long id);
    int deleteByInfluencerId(Long id);
    long countByInfluencerId(Long id);

    @Query("SELECT NEW Coupon(item.id,item.name) FROM Coupon item")
    List<Coupon> findAllOptimized();

}
