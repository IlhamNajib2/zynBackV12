package  ma.zs.zyn.dao.specification.core.paiment;

import ma.zs.zyn.dao.criteria.core.paiment.PaimentCollaboratorCriteria;
import ma.zs.zyn.bean.core.paiment.PaimentCollaborator;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentCollaboratorSpecification extends  AbstractSpecification<PaimentCollaboratorCriteria, PaimentCollaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicateBigDecimal("amountToPaid", criteria.getAmountToPaid(), criteria.getAmountToPaidMin(), criteria.getAmountToPaidMax());
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("discount", criteria.getDiscount(), criteria.getDiscountMin(), criteria.getDiscountMax());
        addPredicateBigDecimal("remaining", criteria.getRemaining(), criteria.getRemainingMin(), criteria.getRemainingMax());
        addPredicate("paiementDate", criteria.getPaiementDate(), criteria.getPaiementDateFrom(), criteria.getPaiementDateTo());
        addPredicateFk("couponDetail","id", criteria.getCouponDetail()==null?null:criteria.getCouponDetail().getId());
        addPredicateFk("couponDetail","id", criteria.getCouponDetails());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborator()==null?null:criteria.getInscriptionCollaborator().getId());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborators());
        addPredicateFk("paimentCollaboratorState","id", criteria.getPaimentCollaboratorState()==null?null:criteria.getPaimentCollaboratorState().getId());
        addPredicateFk("paimentCollaboratorState","id", criteria.getPaimentCollaboratorStates());
        addPredicateFk("paimentCollaboratorState","code", criteria.getPaimentCollaboratorState()==null?null:criteria.getPaimentCollaboratorState().getCode());
    }

    public PaimentCollaboratorSpecification(PaimentCollaboratorCriteria criteria) {
        super(criteria);
    }

    public PaimentCollaboratorSpecification(PaimentCollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
