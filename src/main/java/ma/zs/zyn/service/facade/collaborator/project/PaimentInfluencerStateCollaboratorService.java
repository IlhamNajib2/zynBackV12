package ma.zs.zyn.service.facade.collaborator.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.PaimentInfluencerState;
import ma.zs.zyn.dao.criteria.core.project.PaimentInfluencerStateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PaimentInfluencerStateCollaboratorService {







	PaimentInfluencerState create(PaimentInfluencerState t);

    PaimentInfluencerState update(PaimentInfluencerState t);

    List<PaimentInfluencerState> update(List<PaimentInfluencerState> ts,boolean createIfNotExist);

    PaimentInfluencerState findById(Long id);

    PaimentInfluencerState findOrSave(PaimentInfluencerState t);

    PaimentInfluencerState findByReferenceEntity(PaimentInfluencerState t);

    PaimentInfluencerState findWithAssociatedLists(Long id);

    List<PaimentInfluencerState> findAllOptimized();

    List<PaimentInfluencerState> findAll();

    List<PaimentInfluencerState> findByCriteria(PaimentInfluencerStateCriteria criteria);

    List<PaimentInfluencerState> findPaginatedByCriteria(PaimentInfluencerStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentInfluencerStateCriteria criteria);

    List<PaimentInfluencerState> delete(List<PaimentInfluencerState> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<PaimentInfluencerState>> getToBeSavedAndToBeDeleted(List<PaimentInfluencerState> oldList, List<PaimentInfluencerState> newList);

    List<PaimentInfluencerState> importData(List<PaimentInfluencerState> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<PaimentInfluencerState> importExcel(MultipartFile file);

}
