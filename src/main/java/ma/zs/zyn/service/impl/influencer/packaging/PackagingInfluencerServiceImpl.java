package ma.zs.zyn.service.impl.influencer.packaging;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;
import ma.zs.zyn.dao.facade.core.packaging.PackagingDao;
import ma.zs.zyn.dao.specification.core.packaging.PackagingSpecification;
import ma.zs.zyn.service.facade.influencer.packaging.PackagingInfluencerService;
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

import ma.zs.zyn.service.facade.influencer.category.CategoryPackagingInfluencerService ;
import ma.zs.zyn.bean.core.category.CategoryPackaging ;

import java.util.List;
@Service
public class PackagingInfluencerServiceImpl implements PackagingInfluencerService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Packaging update(Packaging t) {
        Packaging loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Packaging.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Packaging findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Packaging findOrSave(Packaging t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Packaging result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Packaging> importData(List<Packaging> items) {
        List<Packaging> list = new ArrayList<>();
        for (Packaging t : items) {
            Packaging founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Packaging> findAll() {
        return dao.findAll();
    }

    public List<Packaging> findByCriteria(PackagingCriteria criteria) {
        List<Packaging> content = null;
        if (criteria != null) {
            PackagingSpecification mySpecification = constructSpecification(criteria);
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


    private PackagingSpecification constructSpecification(PackagingCriteria criteria) {
        PackagingSpecification mySpecification =  (PackagingSpecification) RefelexivityUtil.constructObjectUsingOneParam(PackagingSpecification.class, criteria);
        return mySpecification;
    }

    public List<Packaging> findPaginatedByCriteria(PackagingCriteria criteria, int page, int pageSize, String order, String sortField) {
        PackagingSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PackagingCriteria criteria) {
        PackagingSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Packaging> findByCategoryPackagingId(Long id){
        return dao.findByCategoryPackagingId(id);
    }
    public int deleteByCategoryPackagingId(Long id){
        return dao.deleteByCategoryPackagingId(id);
    }
    public long countByCategoryPackagingId(Long id){
        return dao.countByCategoryPackagingId(id);
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
    public int delete(Packaging t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Packaging> delete(List<Packaging> list) {
		List<Packaging> result = new ArrayList();
        if (list != null) {
            for (Packaging t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Packaging create(Packaging t) {
        Packaging loaded = findByReferenceEntity(t);
        Packaging saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Packaging> create(List<Packaging> ts) {
        List<Packaging> result = new ArrayList<>();
        if (ts != null) {
            for (Packaging t : ts) {
				Packaging created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Packaging findWithAssociatedLists(Long id){
        Packaging result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Packaging> update(List<Packaging> ts, boolean createIfNotExist) {
        List<Packaging> result = new ArrayList<>();
        if (ts != null) {
            for (Packaging t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Packaging loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Packaging findByReferenceEntity(Packaging t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Packaging t){
        if( t != null) {
            t.setCategoryPackaging(categoryPackagingService.findOrSave(t.getCategoryPackaging()));
        }
    }



    public List<Packaging> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Packaging>> getToBeSavedAndToBeDeleted(List<Packaging> oldList, List<Packaging> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Packaging> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private CategoryPackagingInfluencerService categoryPackagingService ;

    private @Autowired PackagingDao dao;


}
