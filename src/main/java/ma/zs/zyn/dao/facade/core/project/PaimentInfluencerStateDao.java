package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.PaimentInfluencerState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.project.PaimentInfluencerState;
import java.util.List;


@Repository
public interface PaimentInfluencerStateDao extends AbstractRepository<PaimentInfluencerState,Long>  {
    PaimentInfluencerState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW PaimentInfluencerState(item.id,item.code) FROM PaimentInfluencerState item")
    List<PaimentInfluencerState> findAllOptimized();

}
