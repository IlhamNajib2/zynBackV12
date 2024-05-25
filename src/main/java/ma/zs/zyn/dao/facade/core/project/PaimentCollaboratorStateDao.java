package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.PaimentCollaboratorState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.project.PaimentCollaboratorState;
import java.util.List;


@Repository
public interface PaimentCollaboratorStateDao extends AbstractRepository<PaimentCollaboratorState,Long>  {
    PaimentCollaboratorState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW PaimentCollaboratorState(item.id,item.code) FROM PaimentCollaboratorState item")
    List<PaimentCollaboratorState> findAllOptimized();

    PaimentCollaboratorState findBylibelle(String libelle);

}
