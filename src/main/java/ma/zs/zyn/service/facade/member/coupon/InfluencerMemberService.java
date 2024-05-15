package ma.zs.zyn.service.facade.member.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.Influencer;
import ma.zs.zyn.dao.criteria.core.coupon.InfluencerCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface InfluencerMemberService {


    Influencer findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Influencer create(Influencer t);

    Influencer update(Influencer t);

    List<Influencer> update(List<Influencer> ts,boolean createIfNotExist);

    Influencer findById(Long id);

    Influencer findOrSave(Influencer t);

    Influencer findByReferenceEntity(Influencer t);

    Influencer findWithAssociatedLists(Long id);

    List<Influencer> findAllOptimized();

    List<Influencer> findAll();

    List<Influencer> findByCriteria(InfluencerCriteria criteria);

    List<Influencer> findPaginatedByCriteria(InfluencerCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InfluencerCriteria criteria);

    List<Influencer> delete(List<Influencer> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Influencer>> getToBeSavedAndToBeDeleted(List<Influencer> oldList, List<Influencer> newList);

    List<Influencer> importData(List<Influencer> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Influencer> importExcel(MultipartFile file);

}
