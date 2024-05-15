package ma.zs.zyn.dao.facade.core.inscription;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InscriptionCollaboratorDao extends AbstractRepository<InscriptionCollaborator,Long>  {

    List<InscriptionCollaborator> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingId(Long id);
    List<InscriptionCollaborator> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<InscriptionCollaborator> findByInscriptionCollaboratorStateId(Long id);
    int deleteByInscriptionCollaboratorStateId(Long id);
    long countByInscriptionCollaboratorStateId(Long id);
    List<InscriptionCollaborator> findByInscriptionCollaboratorTypeId(Long id);
    int deleteByInscriptionCollaboratorTypeId(Long id);
    long countByInscriptionCollaboratorTypeId(Long id);


}
