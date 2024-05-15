package ma.zs.zyn.service.impl.collaborator.paiment;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.paiment.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.paiment.PaimentCollaboratorCriteria;
import ma.zs.zyn.dao.facade.core.paiment.PaimentCollaboratorDao;
import ma.zs.zyn.dao.specification.core.paiment.PaimentCollaboratorSpecification;
import ma.zs.zyn.service.facade.collaborator.paiment.PaimentCollaboratorCollaboratorService;
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
import ma.zs.zyn.service.facade.collaborator.project.PaimentCollaboratorStateCollaboratorService ;
import ma.zs.zyn.bean.core.project.PaimentCollaboratorState ;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorCollaboratorService ;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator ;

import java.util.List;
@Service
public class PaimentCollaboratorCollaboratorServiceImpl implements PaimentCollaboratorCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaborator update(PaimentCollaborator t) {
        PaimentCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCollaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCollaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCollaborator findOrSave(PaimentCollaborator t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PaimentCollaborator result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<PaimentCollaborator> importData(List<PaimentCollaborator> items) {
        List<PaimentCollaborator> list = new ArrayList<>();
        for (PaimentCollaborator t : items) {
            PaimentCollaborator founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<PaimentCollaborator> findAll() {
        return dao.findAll();
    }

    public List<PaimentCollaborator> findByCriteria(PaimentCollaboratorCriteria criteria) {
        List<PaimentCollaborator> content = null;
        if (criteria != null) {
            PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentCollaboratorSpecification constructSpecification(PaimentCollaboratorCriteria criteria) {
        PaimentCollaboratorSpecification mySpecification =  (PaimentCollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCollaborator> findPaginatedByCriteria(PaimentCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCollaboratorCriteria criteria) {
        PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<PaimentCollaborator> findByCouponDetailId(Long id){
        return dao.findByCouponDetailId(id);
    }
    public int deleteByCouponDetailId(Long id){
        return dao.deleteByCouponDetailId(id);
    }
    public long countByCouponDetailId(Long id){
        return dao.countByCouponDetailId(id);
    }
    public List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id){
        return dao.findByInscriptionCollaboratorId(id);
    }
    public int deleteByInscriptionCollaboratorId(Long id){
        return dao.deleteByInscriptionCollaboratorId(id);
    }
    public long countByInscriptionCollaboratorId(Long id){
        return dao.countByInscriptionCollaboratorId(id);
    }
    public List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code){
        return dao.findByPaimentCollaboratorStateCode(code);
    }
    public int deleteByPaimentCollaboratorStateCode(String code){
        return dao.deleteByPaimentCollaboratorStateCode(code);
    }
    public long countByPaimentCollaboratorStateCode(String code){
        return dao.countByPaimentCollaboratorStateCode(code);
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
    public int delete(PaimentCollaborator t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaborator> delete(List<PaimentCollaborator> list) {
		List<PaimentCollaborator> result = new ArrayList();
        if (list != null) {
            for (PaimentCollaborator t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaborator create(PaimentCollaborator t) {
        PaimentCollaborator loaded = findByReferenceEntity(t);
        PaimentCollaborator saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaborator> create(List<PaimentCollaborator> ts) {
        List<PaimentCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaborator t : ts) {
				PaimentCollaborator created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public PaimentCollaborator findWithAssociatedLists(Long id){
        PaimentCollaborator result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaborator> update(List<PaimentCollaborator> ts, boolean createIfNotExist) {
        List<PaimentCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
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





    public PaimentCollaborator findByReferenceEntity(PaimentCollaborator t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(PaimentCollaborator t){
        if( t != null) {
            t.setCouponDetail(couponDetailService.findOrSave(t.getCouponDetail()));
            t.setInscriptionCollaborator(inscriptionCollaboratorService.findOrSave(t.getInscriptionCollaborator()));
            t.setPaimentCollaboratorState(paimentCollaboratorStateService.findOrSave(t.getPaimentCollaboratorState()));
        }
    }



    public List<PaimentCollaborator> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentCollaborator>> getToBeSavedAndToBeDeleted(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<PaimentCollaborator> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private CouponDetailCollaboratorService couponDetailService ;
    @Autowired
    private PaimentCollaboratorStateCollaboratorService paimentCollaboratorStateService ;
    @Autowired
    private InscriptionCollaboratorCollaboratorService inscriptionCollaboratorService ;

    private @Autowired PaimentCollaboratorDao dao;


}
