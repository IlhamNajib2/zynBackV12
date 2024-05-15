package ma.zs.zyn.service.facade.influencer.collaborator;

import java.util.List;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.dao.criteria.core.collaborator.CollaboratorCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CollaboratorInfluencerService {


    Collaborator findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Collaborator create(Collaborator t);

    Collaborator update(Collaborator t);

    List<Collaborator> update(List<Collaborator> ts,boolean createIfNotExist);

    Collaborator findById(Long id);

    Collaborator findOrSave(Collaborator t);

    Collaborator findByReferenceEntity(Collaborator t);

    Collaborator findWithAssociatedLists(Long id);

    List<Collaborator> findAllOptimized();

    List<Collaborator> findAll();

    List<Collaborator> findByCriteria(CollaboratorCriteria criteria);

    List<Collaborator> findPaginatedByCriteria(CollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CollaboratorCriteria criteria);

    List<Collaborator> delete(List<Collaborator> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Collaborator>> getToBeSavedAndToBeDeleted(List<Collaborator> oldList, List<Collaborator> newList);

    List<Collaborator> importData(List<Collaborator> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Collaborator> importExcel(MultipartFile file);

}
