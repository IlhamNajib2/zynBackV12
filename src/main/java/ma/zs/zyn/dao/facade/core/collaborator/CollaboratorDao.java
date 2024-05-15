package ma.zs.zyn.dao.facade.core.collaborator;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CollaboratorDao extends AbstractRepository<Collaborator,Long>  {

    Collaborator findByUsername(String username);

    @Query("SELECT NEW Collaborator(item.id,item.description) FROM Collaborator item")
    List<Collaborator> findAllOptimized();

}
