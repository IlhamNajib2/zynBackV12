package  ma.zs.zyn.dao.specification.core.paiment;

import ma.zs.zyn.dao.criteria.core.paiment.PaimentInfluencerCriteria;
import ma.zs.zyn.bean.core.paiment.PaimentInfluencer;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentInfluencerSpecification extends  AbstractSpecification<PaimentInfluencerCriteria, PaimentInfluencer>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("nbrUtilisation", criteria.getNbrUtilisation(), criteria.getNbrUtilisationMin(), criteria.getNbrUtilisationMax());
        addPredicate("datePaiement", criteria.getDatePaiement(), criteria.getDatePaiementFrom(), criteria.getDatePaiementTo());
        addPredicateFk("influencer","id", criteria.getInfluencer()==null?null:criteria.getInfluencer().getId());
        addPredicateFk("influencer","id", criteria.getInfluencers());
        addPredicateFk("coupon","id", criteria.getCoupon()==null?null:criteria.getCoupon().getId());
        addPredicateFk("coupon","id", criteria.getCoupons());
        addPredicateFk("coupon","code", criteria.getCoupon()==null?null:criteria.getCoupon().getCode());
        addPredicateFk("paimentInfluencerState","id", criteria.getPaimentInfluencerState()==null?null:criteria.getPaimentInfluencerState().getId());
        addPredicateFk("paimentInfluencerState","id", criteria.getPaimentInfluencerStates());
        addPredicateFk("paimentInfluencerState","code", criteria.getPaimentInfluencerState()==null?null:criteria.getPaimentInfluencerState().getCode());
    }

    public PaimentInfluencerSpecification(PaimentInfluencerCriteria criteria) {
        super(criteria);
    }

    public PaimentInfluencerSpecification(PaimentInfluencerCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
