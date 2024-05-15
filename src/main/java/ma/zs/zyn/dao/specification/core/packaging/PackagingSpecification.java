package  ma.zs.zyn.dao.specification.core.packaging;

import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PackagingSpecification extends  AbstractSpecification<PackagingCriteria, Packaging>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("dateStart", criteria.getDateStart(), criteria.getDateStartFrom(), criteria.getDateStartTo());
        addPredicate("dateEnd", criteria.getDateEnd(), criteria.getDateEndFrom(), criteria.getDateEndTo());
        addPredicateBigDecimal("price", criteria.getPrice(), criteria.getPriceMin(), criteria.getPriceMax());
        addPredicateBigDecimal("maxEntity", criteria.getMaxEntity(), criteria.getMaxEntityMin(), criteria.getMaxEntityMax());
        addPredicateBigDecimal("maxProjet", criteria.getMaxProjet(), criteria.getMaxProjetMin(), criteria.getMaxProjetMax());
        addPredicateBigDecimal("maxAttribut", criteria.getMaxAttribut(), criteria.getMaxAttributMin(), criteria.getMaxAttributMax());
        addPredicateBigDecimal("maxIndicator", criteria.getMaxIndicator(), criteria.getMaxIndicatorMin(), criteria.getMaxIndicatorMax());
        addPredicateFk("categoryPackaging","id", criteria.getCategoryPackaging()==null?null:criteria.getCategoryPackaging().getId());
        addPredicateFk("categoryPackaging","id", criteria.getCategoryPackagings());
    }

    public PackagingSpecification(PackagingCriteria criteria) {
        super(criteria);
    }

    public PackagingSpecification(PackagingCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
