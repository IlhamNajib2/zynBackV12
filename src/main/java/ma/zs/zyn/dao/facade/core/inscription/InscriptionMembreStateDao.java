package ma.zs.zyn.dao.facade.core.inscription;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.inscription.InscriptionMembreState;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InscriptionMembreStateDao extends AbstractRepository<InscriptionMembreState,Long>  {


    @Query("SELECT NEW InscriptionMembreState(item.id,item.name) FROM InscriptionMembreState item")
    List<InscriptionMembreState> findAllOptimized();

}
