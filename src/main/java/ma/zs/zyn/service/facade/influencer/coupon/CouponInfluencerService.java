package ma.zs.zyn.service.facade.influencer.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CouponInfluencerService {



    List<Coupon> findByInfluencerId(Long id);
    int deleteByInfluencerId(Long id);
    long countByInfluencerId(Long id);




	Coupon create(Coupon t);

    Coupon update(Coupon t);

    List<Coupon> update(List<Coupon> ts,boolean createIfNotExist);

    Coupon findById(Long id);

    Coupon findOrSave(Coupon t);

    Coupon findByReferenceEntity(Coupon t);

    Coupon findWithAssociatedLists(Long id);

    List<Coupon> findAllOptimized();

    List<Coupon> findAll();

    List<Coupon> findByCriteria(CouponCriteria criteria);

    List<Coupon> findPaginatedByCriteria(CouponCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CouponCriteria criteria);

    List<Coupon> delete(List<Coupon> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Coupon>> getToBeSavedAndToBeDeleted(List<Coupon> oldList, List<Coupon> newList);

    List<Coupon> importData(List<Coupon> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Coupon> importExcel(MultipartFile file);

}
