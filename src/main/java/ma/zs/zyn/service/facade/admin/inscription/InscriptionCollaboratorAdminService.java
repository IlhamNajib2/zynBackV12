package ma.zs.zyn.service.facade.admin.inscription;

import java.time.LocalDate;
import java.util.List;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface InscriptionCollaboratorAdminService {



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




	InscriptionCollaborator create(InscriptionCollaborator t);


    Long countInscriptionsByDay(LocalDate date);

    Long countInscriptionsByMonth(LocalDate date);

    Long countInscriptionsByWeek(LocalDate date);

    Long countInscriptionsByYear(LocalDate date);

    InscriptionCollaborator update(InscriptionCollaborator t);

    List<InscriptionCollaborator> update(List<InscriptionCollaborator> ts,boolean createIfNotExist);

    InscriptionCollaborator findById(Long id);

    InscriptionCollaborator findOrSave(InscriptionCollaborator t);

    InscriptionCollaborator findByReferenceEntity(InscriptionCollaborator t);

    InscriptionCollaborator findWithAssociatedLists(Long id);

    List<InscriptionCollaborator> findAllOptimized();

    List<InscriptionCollaborator> findAll();

    List<InscriptionCollaborator> findByCriteria(InscriptionCollaboratorCriteria criteria);

    List<InscriptionCollaborator> findPaginatedByCriteria(InscriptionCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionCollaboratorCriteria criteria);

    List<InscriptionCollaborator> delete(List<InscriptionCollaborator> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<InscriptionCollaborator>> getToBeSavedAndToBeDeleted(List<InscriptionCollaborator> oldList, List<InscriptionCollaborator> newList);

    List<InscriptionCollaborator> importData(List<InscriptionCollaborator> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<InscriptionCollaborator> importExcel(MultipartFile file);

}
