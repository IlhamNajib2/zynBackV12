package ma.zs.zyn.service.impl.collaborator.coupon;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.CouponDetail;
import ma.zs.zyn.dao.criteria.core.coupon.CouponDetailCriteria;
import ma.zs.zyn.dao.facade.core.coupon.CouponDetailDao;
import ma.zs.zyn.dao.specification.core.coupon.CouponDetailSpecification;
import ma.zs.zyn.service.facade.collaborator.coupon.CouponDetailCollaboratorService;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import ma.zs.zyn.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.service.facade.collaborator.coupon.CouponCollaboratorService ;
import ma.zs.zyn.bean.core.coupon.Coupon ;
import ma.zs.zyn.service.facade.collaborator.packaging.PackagingCollaboratorService ;
import ma.zs.zyn.bean.core.packaging.Packaging ;

import java.util.List;
@Service
public class CouponDetailCollaboratorServiceImpl implements CouponDetailCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CouponDetail update(CouponDetail t) {
        CouponDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CouponDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CouponDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CouponDetail findOrSave(CouponDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CouponDetail result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<CouponDetail> importData(List<CouponDetail> items) {
        List<CouponDetail> list = new ArrayList<>();
        for (CouponDetail t : items) {
            CouponDetail founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<CouponDetail> findAll() {
        return dao.findAll();
    }

    public List<CouponDetail> findByCriteria(CouponDetailCriteria criteria) {
        List<CouponDetail> content = null;
        if (criteria != null) {
            CouponDetailSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CouponDetailSpecification constructSpecification(CouponDetailCriteria criteria) {
        CouponDetailSpecification mySpecification =  (CouponDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(CouponDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<CouponDetail> findPaginatedByCriteria(CouponDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        CouponDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CouponDetailCriteria criteria) {
        CouponDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<CouponDetail> findByPackagingId(Long id){
        return dao.findByPackagingId(id);
    }
    public int deleteByPackagingId(Long id){
        return dao.deleteByPackagingId(id);
    }
    public long countByPackagingId(Long id){
        return dao.countByPackagingId(id);
    }
    public List<CouponDetail> findByCouponId(Long id){
        return dao.findByCouponId(id);
    }
    public int deleteByCouponId(Long id){
        return dao.deleteByCouponId(id);
    }
    public long countByCouponCode(String code){
        return dao.countByCouponCode(code);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(CouponDetail t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CouponDetail> delete(List<CouponDetail> list) {
		List<CouponDetail> result = new ArrayList();
        if (list != null) {
            for (CouponDetail t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CouponDetail create(CouponDetail t) {
        CouponDetail loaded = findByReferenceEntity(t);
        CouponDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CouponDetail> create(List<CouponDetail> ts) {
        List<CouponDetail> result = new ArrayList<>();
        if (ts != null) {
            for (CouponDetail t : ts) {
				CouponDetail created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public CouponDetail findWithAssociatedLists(Long id){
        CouponDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CouponDetail> update(List<CouponDetail> ts, boolean createIfNotExist) {
        List<CouponDetail> result = new ArrayList<>();
        if (ts != null) {
            for (CouponDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CouponDetail loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }





    public CouponDetail findByReferenceEntity(CouponDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(CouponDetail t){
        if( t != null) {
            t.setPackaging(packagingService.findOrSave(t.getPackaging()));
            t.setCoupon(couponService.findOrSave(t.getCoupon()));
        }
    }



    public List<CouponDetail> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<CouponDetail>> getToBeSavedAndToBeDeleted(List<CouponDetail> oldList, List<CouponDetail> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<CouponDetail> importExcel(MultipartFile file) {
        return null;
    }

    @Override
    public CouponDetail findByCouponName(String name) {
        return dao.findByCouponName(name);
    }


    @Autowired
    private CouponCollaboratorService couponService ;
    @Autowired
    private PackagingCollaboratorService packagingService ;

    private @Autowired CouponDetailDao dao;


}
