package ma.zs.zyn.service.facade.influencer.inscription;

import java.util.List;
import ma.zs.zyn.bean.core.inscription.InscriptionMembreState;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreStateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface InscriptionMembreStateInfluencerService {







	InscriptionMembreState create(InscriptionMembreState t);

    InscriptionMembreState update(InscriptionMembreState t);

    List<InscriptionMembreState> update(List<InscriptionMembreState> ts,boolean createIfNotExist);

    InscriptionMembreState findById(Long id);

    InscriptionMembreState findOrSave(InscriptionMembreState t);

    InscriptionMembreState findByReferenceEntity(InscriptionMembreState t);

    InscriptionMembreState findWithAssociatedLists(Long id);

    List<InscriptionMembreState> findAllOptimized();

    List<InscriptionMembreState> findAll();

    List<InscriptionMembreState> findByCriteria(InscriptionMembreStateCriteria criteria);

    List<InscriptionMembreState> findPaginatedByCriteria(InscriptionMembreStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionMembreStateCriteria criteria);

    List<InscriptionMembreState> delete(List<InscriptionMembreState> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<InscriptionMembreState>> getToBeSavedAndToBeDeleted(List<InscriptionMembreState> oldList, List<InscriptionMembreState> newList);

    List<InscriptionMembreState> importData(List<InscriptionMembreState> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<InscriptionMembreState> importExcel(MultipartFile file);

}
