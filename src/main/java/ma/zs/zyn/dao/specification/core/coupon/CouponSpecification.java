package  ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CouponSpecification extends  AbstractSpecification<CouponCriteria, Coupon>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("dateStart", criteria.getDateStart(), criteria.getDateStartFrom(), criteria.getDateStartTo());
        addPredicate("dateEnd", criteria.getDateEnd(), criteria.getDateEndFrom(), criteria.getDateEndTo());
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicateFk("influencer","id", criteria.getInfluencer()==null?null:criteria.getInfluencer().getId());
        addPredicateFk("influencer","id", criteria.getInfluencers());
    }

    public CouponSpecification(CouponCriteria criteria) {
        super(criteria);
    }

    public CouponSpecification(CouponCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
