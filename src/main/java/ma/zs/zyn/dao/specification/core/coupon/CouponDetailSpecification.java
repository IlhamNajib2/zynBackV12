package  ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.CouponDetailCriteria;
import ma.zs.zyn.bean.core.coupon.CouponDetail;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CouponDetailSpecification extends  AbstractSpecification<CouponDetailCriteria, CouponDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("discount", criteria.getDiscount(), criteria.getDiscountMin(), criteria.getDiscountMax());
        addPredicateBigDecimal("amountGivenInfluencer", criteria.getAmountGivenInfluencer(), criteria.getAmountGivenInfluencerMin(), criteria.getAmountGivenInfluencerMax());
        addPredicateBigDecimal("usingNumber", criteria.getUsingNumber(), criteria.getUsingNumberMin(), criteria.getUsingNumberMax());
        addPredicateBigDecimal("maxUsingNumber", criteria.getMaxUsingNumber(), criteria.getMaxUsingNumberMin(), criteria.getMaxUsingNumberMax());
        addPredicateFk("packaging","id", criteria.getPackaging()==null?null:criteria.getPackaging().getId());
        addPredicateFk("packaging","id", criteria.getPackagings());
        addPredicateFk("coupon","id", criteria.getCoupon()==null?null:criteria.getCoupon().getId());
        addPredicateFk("coupon","id", criteria.getCoupons());
        addPredicateFk("coupon","code", criteria.getCoupon()==null?null:criteria.getCoupon().getCode());
    }

    public CouponDetailSpecification(CouponDetailCriteria criteria) {
        super(criteria);
    }

    public CouponDetailSpecification(CouponDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
