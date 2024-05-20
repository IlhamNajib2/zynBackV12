package ma.zs.zyn.dao.facade.core.project;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.project.Project;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.project.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ProjectDao extends AbstractRepository<Project,Long>  {
    Project findByCode(String code);

    @Query("SELECT COUNT(p) FROM Project p WHERE FUNCTION('YEAR', p.generatedDate) = :year")
    Long countProjectsByYear(@Param("year") int year);

    @Query("SELECT COUNT(p) FROM Project p WHERE FUNCTION('MONTH', p.generatedDate) = :month AND FUNCTION('YEAR', p.generatedDate) = :year")
    Long countProjectsByMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT COUNT(p) FROM Project p WHERE FUNCTION('WEEK', p.generatedDate) = :week AND FUNCTION('YEAR', p.generatedDate) = :year")
    Long countProjectsByWeek(@Param("week") int week, @Param("year") int year);



    @Query("SELECT COUNT(p) FROM Project p WHERE p.generatedDate >= :startDate AND p.generatedDate < :endDate")
    Long countProjectsByDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Project> findByGeneratedDateBetween(LocalDateTime startDate, LocalDateTime endDate);




    List<Project> findByProjectStateCode(String code);

   public int deleteByProjectStateCode(String code);
    long countByProjectStateCode(String code);

    List<Project> findByInscriptionMembreId(Long id);

    int deleteByInscriptionMembreId(Long id);
    long countByInscriptionMembreId(Long id);
    List<Project> findByDomainTemplateId(Long id);
    int deleteByDomainTemplateId(Long id);
    long countByDomainTemplateId(Long id);

    @Query("SELECT NEW Project(item.id,item.code) FROM Project item")
    List<Project> findAllOptimized();

}
