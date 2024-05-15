package ma.zs.zyn.service.facade.member.coupon;

import java.util.List;
import ma.zs.zyn.bean.core.coupon.CouponDetail;
import ma.zs.zyn.dao.criteria.core.coupon.CouponDetailCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CouponDetailMemberService {



    List<CouponDetail> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingId(Long id);
    List<CouponDetail> findByCouponId(Long id);
    int deleteByCouponId(Long id);
    long countByCouponCode(String code);




	CouponDetail create(CouponDetail t);

    CouponDetail update(CouponDetail t);

    List<CouponDetail> update(List<CouponDetail> ts,boolean createIfNotExist);

    CouponDetail findById(Long id);

    CouponDetail findOrSave(CouponDetail t);

    CouponDetail findByReferenceEntity(CouponDetail t);

    CouponDetail findWithAssociatedLists(Long id);

    List<CouponDetail> findAllOptimized();

    List<CouponDetail> findAll();

    List<CouponDetail> findByCriteria(CouponDetailCriteria criteria);

    List<CouponDetail> findPaginatedByCriteria(CouponDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CouponDetailCriteria criteria);

    List<CouponDetail> delete(List<CouponDetail> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<CouponDetail>> getToBeSavedAndToBeDeleted(List<CouponDetail> oldList, List<CouponDetail> newList);

    List<CouponDetail> importData(List<CouponDetail> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<CouponDetail> importExcel(MultipartFile file);

}
