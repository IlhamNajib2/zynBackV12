package ma.zs.zyn.service.facade.collaborator.inscription;

import java.util.List;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorType;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorTypeCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface InscriptionCollaboratorTypeCollaboratorService {







	InscriptionCollaboratorType create(InscriptionCollaboratorType t);

    InscriptionCollaboratorType update(InscriptionCollaboratorType t);

    List<InscriptionCollaboratorType> update(List<InscriptionCollaboratorType> ts,boolean createIfNotExist);

    InscriptionCollaboratorType findById(Long id);

    InscriptionCollaboratorType findOrSave(InscriptionCollaboratorType t);

    InscriptionCollaboratorType findByReferenceEntity(InscriptionCollaboratorType t);

    InscriptionCollaboratorType findWithAssociatedLists(Long id);

    List<InscriptionCollaboratorType> findAllOptimized();

    List<InscriptionCollaboratorType> findAll();

    List<InscriptionCollaboratorType> findByCriteria(InscriptionCollaboratorTypeCriteria criteria);

    List<InscriptionCollaboratorType> findPaginatedByCriteria(InscriptionCollaboratorTypeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionCollaboratorTypeCriteria criteria);

    List<InscriptionCollaboratorType> delete(List<InscriptionCollaboratorType> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<InscriptionCollaboratorType>> getToBeSavedAndToBeDeleted(List<InscriptionCollaboratorType> oldList, List<InscriptionCollaboratorType> newList);

    List<InscriptionCollaboratorType> importData(List<InscriptionCollaboratorType> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<InscriptionCollaboratorType> importExcel(MultipartFile file);

    InscriptionCollaboratorType findByName(String name);
}
