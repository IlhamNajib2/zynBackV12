package ma.zs.zyn.service.facade.admin.paiment;

import java.time.LocalDate;
import java.util.List;
import ma.zs.zyn.bean.core.paiment.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.paiment.PaimentCollaboratorCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PaimentCollaboratorAdminService {



    List<PaimentCollaborator> findByCouponDetailId(Long id);
    int deleteByCouponDetailId(Long id);
    long countByCouponDetailId(Long id);
    List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorId(Long id);
    List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code);
    int deleteByPaimentCollaboratorStateCode(String code);
    long countByPaimentCollaboratorStateCode(String code);




	PaimentCollaborator create(PaimentCollaborator t);

    Long sumPaymentsByDay(LocalDate date);

    Long sumPaymentsByMonth(LocalDate date);

    Long sumPaymentsByWeek(LocalDate date);

    Long sumPaymentsByYear(LocalDate date);

    PaimentCollaborator update(PaimentCollaborator t);

    List<PaimentCollaborator> update(List<PaimentCollaborator> ts,boolean createIfNotExist);

    PaimentCollaborator findById(Long id);

    PaimentCollaborator findOrSave(PaimentCollaborator t);

    PaimentCollaborator findByReferenceEntity(PaimentCollaborator t);

    PaimentCollaborator findWithAssociatedLists(Long id);

    List<PaimentCollaborator> findAllOptimized();

    List<PaimentCollaborator> findAll();

    List<PaimentCollaborator> findByCriteria(PaimentCollaboratorCriteria criteria);

    List<PaimentCollaborator> findPaginatedByCriteria(PaimentCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentCollaboratorCriteria criteria);

    List<PaimentCollaborator> delete(List<PaimentCollaborator> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<PaimentCollaborator>> getToBeSavedAndToBeDeleted(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList);

    List<PaimentCollaborator> importData(List<PaimentCollaborator> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<PaimentCollaborator> importExcel(MultipartFile file);

}
