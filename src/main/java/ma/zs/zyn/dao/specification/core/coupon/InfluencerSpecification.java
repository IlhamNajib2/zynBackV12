package  ma.zs.zyn.dao.specification.core.coupon;

import ma.zs.zyn.dao.criteria.core.coupon.InfluencerCriteria;
import ma.zs.zyn.bean.core.coupon.Influencer;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InfluencerSpecification extends  AbstractSpecification<InfluencerCriteria, Influencer>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nickName", criteria.getNickName(),criteria.getNickNameLike());
        addPredicate("rib", criteria.getRib(),criteria.getRibLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public InfluencerSpecification(InfluencerCriteria criteria) {
        super(criteria);
    }

    public InfluencerSpecification(InfluencerCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
