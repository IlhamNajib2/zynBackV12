package ma.zs.zyn.service.impl.collaborator.coupon;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.dao.facade.core.coupon.CouponDao;
import ma.zs.zyn.dao.specification.core.coupon.CouponSpecification;
import ma.zs.zyn.service.facade.collaborator.coupon.CouponCollaboratorService;
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

import ma.zs.zyn.service.facade.collaborator.coupon.CouponDetailCollaboratorService ;
import ma.zs.zyn.bean.core.coupon.CouponDetail ;
import ma.zs.zyn.service.facade.collaborator.coupon.InfluencerCollaboratorService ;
import ma.zs.zyn.bean.core.coupon.Influencer ;

import java.util.List;
@Service
public class CouponCollaboratorServiceImpl implements CouponCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Coupon update(Coupon t) {
        Coupon loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Coupon.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Coupon findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Coupon findOrSave(Coupon t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Coupon result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Coupon> importData(List<Coupon> items) {
        List<Coupon> list = new ArrayList<>();
        for (Coupon t : items) {
            Coupon founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Coupon> findAll() {
        return dao.findAll();
    }

    public List<Coupon> findByCriteria(CouponCriteria criteria) {
        List<Coupon> content = null;
        if (criteria != null) {
            CouponSpecification mySpecification = constructSpecification(criteria);
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


    private CouponSpecification constructSpecification(CouponCriteria criteria) {
        CouponSpecification mySpecification =  (CouponSpecification) RefelexivityUtil.constructObjectUsingOneParam(CouponSpecification.class, criteria);
        return mySpecification;
    }

    public List<Coupon> findPaginatedByCriteria(CouponCriteria criteria, int page, int pageSize, String order, String sortField) {
        CouponSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CouponCriteria criteria) {
        CouponSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Coupon> findByInfluencerId(Long id){
        return dao.findByInfluencerId(id);
    }
    public int deleteByInfluencerId(Long id){
        return dao.deleteByInfluencerId(id);
    }
    public long countByInfluencerId(Long id){
        return dao.countByInfluencerId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
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
    public int delete(Coupon t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        couponDetailService.deleteByCouponId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Coupon> delete(List<Coupon> list) {
		List<Coupon> result = new ArrayList();
        if (list != null) {
            for (Coupon t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Coupon create(Coupon t) {
        Coupon loaded = findByReferenceEntity(t);
        Coupon saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getCouponDetails() != null) {
                t.getCouponDetails().forEach(element-> {
                    element.setCoupon(saved);
                    couponDetailService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Coupon> create(List<Coupon> ts) {
        List<Coupon> result = new ArrayList<>();
        if (ts != null) {
            for (Coupon t : ts) {
				Coupon created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Coupon findWithAssociatedLists(Long id){
        Coupon result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setCouponDetails(couponDetailService.findByCouponId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Coupon> update(List<Coupon> ts, boolean createIfNotExist) {
        List<Coupon> result = new ArrayList<>();
        if (ts != null) {
            for (Coupon t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Coupon loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Coupon coupon){
    if(coupon !=null && coupon.getId() != null){
        List<List<CouponDetail>> resultCouponDetails= couponDetailService.getToBeSavedAndToBeDeleted(couponDetailService.findByCouponId(coupon.getId()),coupon.getCouponDetails());
            couponDetailService.delete(resultCouponDetails.get(1));
        ListUtil.emptyIfNull(resultCouponDetails.get(0)).forEach(e -> e.setCoupon(coupon));
        couponDetailService.update(resultCouponDetails.get(0),true);
        }
    }




    public Coupon findByReferenceEntity(Coupon t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Coupon t){
        if( t != null) {
            t.setInfluencer(influencerService.findOrSave(t.getInfluencer()));
        }
    }



    public List<Coupon> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Coupon>> getToBeSavedAndToBeDeleted(List<Coupon> oldList, List<Coupon> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Coupon> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private CouponDetailCollaboratorService couponDetailService ;
    @Autowired
    private InfluencerCollaboratorService influencerService ;

    private @Autowired CouponDao dao;


}
