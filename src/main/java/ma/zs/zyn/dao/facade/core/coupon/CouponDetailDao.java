package ma.zs.zyn.dao.facade.core.coupon;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.coupon.CouponDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CouponDetailDao extends AbstractRepository<CouponDetail,Long>  {

    List<CouponDetail> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingId(Long id);
    List<CouponDetail> findByCouponId(Long id);
    int deleteByCouponId(Long id);
    long countByCouponCode(String code);


}
