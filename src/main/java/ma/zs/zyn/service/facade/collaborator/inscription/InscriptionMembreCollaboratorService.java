package ma.zs.zyn.service.facade.collaborator.inscription;

import java.util.List;
import ma.zs.zyn.bean.core.inscription.InscriptionMembre;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface InscriptionMembreCollaboratorService {



    List<InscriptionMembre> findByMemberId(Long id);
    int deleteByMemberId(Long id);
    long countByMemberId(Long id);
    List<InscriptionMembre> findByInscriptionMembreStateId(Long id);
    int deleteByInscriptionMembreStateId(Long id);
    long countByInscriptionMembreStateId(Long id);
    List<InscriptionMembre> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorId(Long id);




	InscriptionMembre create(InscriptionMembre t);

    InscriptionMembre update(InscriptionMembre t);

    List<InscriptionMembre> update(List<InscriptionMembre> ts,boolean createIfNotExist);

    InscriptionMembre findById(Long id);

    InscriptionMembre findOrSave(InscriptionMembre t);

    InscriptionMembre findByReferenceEntity(InscriptionMembre t);

    InscriptionMembre findWithAssociatedLists(Long id);

    List<InscriptionMembre> findAllOptimized();

    List<InscriptionMembre> findAll();

    List<InscriptionMembre> findByCriteria(InscriptionMembreCriteria criteria);

    List<InscriptionMembre> findPaginatedByCriteria(InscriptionMembreCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionMembreCriteria criteria);

    List<InscriptionMembre> delete(List<InscriptionMembre> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<InscriptionMembre>> getToBeSavedAndToBeDeleted(List<InscriptionMembre> oldList, List<InscriptionMembre> newList);

    List<InscriptionMembre> importData(List<InscriptionMembre> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<InscriptionMembre> importExcel(MultipartFile file);

}
