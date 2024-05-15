package  ma.zs.zyn.dao.specification.core.collaborator;

import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CollaboratorSpecification extends  AbstractSpecification<CollaboratorCriteria, Collaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicate("rib", criteria.getRib(),criteria.getRibLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public CollaboratorSpecification(CollaboratorCriteria criteria) {
        super(criteria);
    }

    public CollaboratorSpecification(CollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
