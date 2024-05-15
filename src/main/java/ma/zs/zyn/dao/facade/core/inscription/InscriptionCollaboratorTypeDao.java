package ma.zs.zyn.dao.facade.core.inscription;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorType;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InscriptionCollaboratorTypeDao extends AbstractRepository<InscriptionCollaboratorType,Long>  {


    @Query("SELECT NEW InscriptionCollaboratorType(item.id,item.name) FROM InscriptionCollaboratorType item")
    List<InscriptionCollaboratorType> findAllOptimized();

}
