package ma.zs.zyn.service.impl.member.paiment;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.paiment.PaimentInfluencer;
import ma.zs.zyn.dao.criteria.core.paiment.PaimentInfluencerCriteria;
import ma.zs.zyn.dao.facade.core.paiment.PaimentInfluencerDao;
import ma.zs.zyn.dao.specification.core.paiment.PaimentInfluencerSpecification;
import ma.zs.zyn.service.facade.member.paiment.PaimentInfluencerMemberService;
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

import ma.zs.zyn.service.facade.member.coupon.CouponMemberService ;
import ma.zs.zyn.bean.core.coupon.Coupon ;
import ma.zs.zyn.service.facade.member.coupon.InfluencerMemberService ;
import ma.zs.zyn.bean.core.coupon.Influencer ;
import ma.zs.zyn.service.facade.member.project.PaimentInfluencerStateMemberService ;
import ma.zs.zyn.bean.core.project.PaimentInfluencerState ;

import java.util.List;
@Service
public class PaimentInfluencerMemberServiceImpl implements PaimentInfluencerMemberService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentInfluencer update(PaimentInfluencer t) {
        PaimentInfluencer loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentInfluencer.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentInfluencer findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentInfluencer findOrSave(PaimentInfluencer t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PaimentInfluencer result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<PaimentInfluencer> importData(List<PaimentInfluencer> items) {
        List<PaimentInfluencer> list = new ArrayList<>();
        for (PaimentInfluencer t : items) {
            PaimentInfluencer founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<PaimentInfluencer> findAll() {
        return dao.findAll();
    }

    public List<PaimentInfluencer> findByCriteria(PaimentInfluencerCriteria criteria) {
        List<PaimentInfluencer> content = null;
        if (criteria != null) {
            PaimentInfluencerSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentInfluencerSpecification constructSpecification(PaimentInfluencerCriteria criteria) {
        PaimentInfluencerSpecification mySpecification =  (PaimentInfluencerSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentInfluencerSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentInfluencer> findPaginatedByCriteria(PaimentInfluencerCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentInfluencerSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentInfluencerCriteria criteria) {
        PaimentInfluencerSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<PaimentInfluencer> findByInfluencerId(Long id){
        return dao.findByInfluencerId(id);
    }
    public int deleteByInfluencerId(Long id){
        return dao.deleteByInfluencerId(id);
    }
    public long countByInfluencerId(Long id){
        return dao.countByInfluencerId(id);
    }
    public List<PaimentInfluencer> findByCouponId(Long id){
        return dao.findByCouponId(id);
    }
    public int deleteByCouponId(Long id){
        return dao.deleteByCouponId(id);
    }
    public long countByCouponCode(String code){
        return dao.countByCouponCode(code);
    }
    public List<PaimentInfluencer> findByPaimentInfluencerStateCode(String code){
        return dao.findByPaimentInfluencerStateCode(code);
    }
    public int deleteByPaimentInfluencerStateCode(String code){
        return dao.deleteByPaimentInfluencerStateCode(code);
    }
    public long countByPaimentInfluencerStateCode(String code){
        return dao.countByPaimentInfluencerStateCode(code);
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
    public int delete(PaimentInfluencer t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentInfluencer> delete(List<PaimentInfluencer> list) {
		List<PaimentInfluencer> result = new ArrayList();
        if (list != null) {
            for (PaimentInfluencer t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentInfluencer create(PaimentInfluencer t) {
        PaimentInfluencer loaded = findByReferenceEntity(t);
        PaimentInfluencer saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentInfluencer> create(List<PaimentInfluencer> ts) {
        List<PaimentInfluencer> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentInfluencer t : ts) {
				PaimentInfluencer created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public PaimentInfluencer findWithAssociatedLists(Long id){
        PaimentInfluencer result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentInfluencer> update(List<PaimentInfluencer> ts, boolean createIfNotExist) {
        List<PaimentInfluencer> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentInfluencer t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentInfluencer loadedItem = dao.findById(t.getId()).orElse(null);
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





    public PaimentInfluencer findByReferenceEntity(PaimentInfluencer t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(PaimentInfluencer t){
        if( t != null) {
            t.setInfluencer(influencerService.findOrSave(t.getInfluencer()));
            t.setCoupon(couponService.findOrSave(t.getCoupon()));
            t.setPaimentInfluencerState(paimentInfluencerStateService.findOrSave(t.getPaimentInfluencerState()));
        }
    }



    public List<PaimentInfluencer> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentInfluencer>> getToBeSavedAndToBeDeleted(List<PaimentInfluencer> oldList, List<PaimentInfluencer> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<PaimentInfluencer> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private CouponMemberService couponService ;
    @Autowired
    private InfluencerMemberService influencerService ;
    @Autowired
    private PaimentInfluencerStateMemberService paimentInfluencerStateService ;

    private @Autowired PaimentInfluencerDao dao;


}
