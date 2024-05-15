package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.ProjectState;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.project.ProjectState;
import java.util.List;


@Repository
public interface ProjectStateDao extends AbstractRepository<ProjectState,Long>  {
    ProjectState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProjectState(item.id,item.code) FROM ProjectState item")
    List<ProjectState> findAllOptimized();

}
