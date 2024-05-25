package ma.zs.zyn.dao.facade.core.paiment;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.paiment.PaimentCollaborator;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PaimentCollaboratorDao extends AbstractRepository<PaimentCollaborator,Long>  {

    @Query("SELECT SUM(p.total) FROM PaimentCollaborator p WHERE p.paiementDate >= :startDate AND p.paiementDate < :endDate")
    Long sumPaymentsByDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @Query("SELECT SUM(p.total) FROM PaimentCollaborator p WHERE p.paiementDate >= :startDate AND p.paiementDate < :endDate")
    Long sumPaymentsByMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(p.total) FROM PaimentCollaborator p WHERE p.paiementDate >= :startDate AND p.paiementDate < :endDate")
    Long sumPaymentsByWeek(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(p.total) FROM PaimentCollaborator p WHERE p.paiementDate >= :startDate AND p.paiementDate < :endDate")
    Long sumPaymentsByYear(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);




    List<PaimentCollaborator> findByCouponDetailId(Long id);
    int deleteByCouponDetailId(Long id);
    long countByCouponDetailId(Long id);
    List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorId(Long id);
    List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code);
            public int deleteByPaimentCollaboratorStateCode(String code);
    long countByPaimentCollaboratorStateCode(String code);

    @Query("SELECT NEW PaimentCollaborator(item.id,item.name) FROM PaimentCollaborator item")
    List<PaimentCollaborator> findAllOptimized();

}
