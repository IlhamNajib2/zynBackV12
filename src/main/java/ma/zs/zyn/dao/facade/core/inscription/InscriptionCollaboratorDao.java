package ma.zs.zyn.dao.facade.core.inscription;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface InscriptionCollaboratorDao extends AbstractRepository<InscriptionCollaborator,Long>  {








    @Query("SELECT COUNT(ic) FROM InscriptionCollaborator ic WHERE ic.startDate >= :startDate AND ic.startDate < :endDate")
    Long countInscriptionsByDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(ic) FROM InscriptionCollaborator ic WHERE ic.startDate >= :startDate AND ic.startDate < :endDate")
    Long countInscriptionsByMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @Query("SELECT COUNT(ic) FROM InscriptionCollaborator ic WHERE ic.startDate >= :startDate AND ic.startDate < :endDate")
    Long countInscriptionsByWeek(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);



    @Query("SELECT COUNT(ic) FROM InscriptionCollaborator ic WHERE ic.startDate >= :startDate AND ic.startDate < :endDate")
    Long countInscriptionsByYear(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);



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
