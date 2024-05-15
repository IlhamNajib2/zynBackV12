package ma.zs.zyn.dao.facade.core.collaborator;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.collaborator.Member;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MemberDao extends AbstractRepository<Member,Long>  {

    List<Member> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    Member findByUsername(String username);


}
