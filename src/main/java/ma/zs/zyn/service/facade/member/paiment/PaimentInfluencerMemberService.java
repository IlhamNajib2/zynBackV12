package ma.zs.zyn.service.facade.member.paiment;

import java.util.List;
import ma.zs.zyn.bean.core.paiment.PaimentInfluencer;
import ma.zs.zyn.dao.criteria.core.paiment.PaimentInfluencerCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PaimentInfluencerMemberService {



    List<PaimentInfluencer> findByInfluencerId(Long id);
    int deleteByInfluencerId(Long id);
    long countByInfluencerId(Long id);
    List<PaimentInfluencer> findByCouponId(Long id);
    int deleteByCouponId(Long id);
    long countByCouponCode(String code);
    List<PaimentInfluencer> findByPaimentInfluencerStateCode(String code);
    int deleteByPaimentInfluencerStateCode(String code);
    long countByPaimentInfluencerStateCode(String code);




	PaimentInfluencer create(PaimentInfluencer t);

    PaimentInfluencer update(PaimentInfluencer t);

    List<PaimentInfluencer> update(List<PaimentInfluencer> ts,boolean createIfNotExist);

    PaimentInfluencer findById(Long id);

    PaimentInfluencer findOrSave(PaimentInfluencer t);

    PaimentInfluencer findByReferenceEntity(PaimentInfluencer t);

    PaimentInfluencer findWithAssociatedLists(Long id);

    List<PaimentInfluencer> findAllOptimized();

    List<PaimentInfluencer> findAll();

    List<PaimentInfluencer> findByCriteria(PaimentInfluencerCriteria criteria);

    List<PaimentInfluencer> findPaginatedByCriteria(PaimentInfluencerCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentInfluencerCriteria criteria);

    List<PaimentInfluencer> delete(List<PaimentInfluencer> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<PaimentInfluencer>> getToBeSavedAndToBeDeleted(List<PaimentInfluencer> oldList, List<PaimentInfluencer> newList);

    List<PaimentInfluencer> importData(List<PaimentInfluencer> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<PaimentInfluencer> importExcel(MultipartFile file);

}
