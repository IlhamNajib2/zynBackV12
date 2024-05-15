package ma.zs.zyn.dao.facade.core.inscription;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.inscription.InscriptionMembre;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InscriptionMembreDao extends AbstractRepository<InscriptionMembre,Long>  {

    List<InscriptionMembre> findByMemberId(Long id);
    int deleteByMemberId(Long id);
    long countByMemberId(Long id);
    List<InscriptionMembre> findByInscriptionMembreStateId(Long id);
    int deleteByInscriptionMembreStateId(Long id);
    long countByInscriptionMembreStateId(Long id);
    List<InscriptionMembre> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorId(Long id);


}
