package  ma.zs.zyn.dao.specification.core.collaborator;

import ma.zs.zyn.dao.criteria.core.collaborator.MemberCriteria;
import ma.zs.zyn.bean.core.collaborator.Member;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class MemberSpecification extends  AbstractSpecification<MemberCriteria, Member>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
    }

    public MemberSpecification(MemberCriteria criteria) {
        super(criteria);
    }

    public MemberSpecification(MemberCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
