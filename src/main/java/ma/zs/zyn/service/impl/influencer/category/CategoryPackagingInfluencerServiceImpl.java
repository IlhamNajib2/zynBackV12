package ma.zs.zyn.service.impl.influencer.category;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.category.CategoryPackaging;
import ma.zs.zyn.dao.criteria.core.category.CategoryPackagingCriteria;
import ma.zs.zyn.dao.facade.core.category.CategoryPackagingDao;
import ma.zs.zyn.dao.specification.core.category.CategoryPackagingSpecification;
import ma.zs.zyn.service.facade.influencer.category.CategoryPackagingInfluencerService;
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


import java.util.List;
@Service
public class CategoryPackagingInfluencerServiceImpl implements CategoryPackagingInfluencerService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategoryPackaging update(CategoryPackaging t) {
        CategoryPackaging loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CategoryPackaging.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CategoryPackaging findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CategoryPackaging findOrSave(CategoryPackaging t) {
        if (t != null) {
            CategoryPackaging result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<CategoryPackaging> importData(List<CategoryPackaging> items) {
        List<CategoryPackaging> list = new ArrayList<>();
        for (CategoryPackaging t : items) {
            CategoryPackaging founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<CategoryPackaging> findAll() {
        return dao.findAll();
    }

    public List<CategoryPackaging> findByCriteria(CategoryPackagingCriteria criteria) {
        List<CategoryPackaging> content = null;
        if (criteria != null) {
            CategoryPackagingSpecification mySpecification = constructSpecification(criteria);
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


    private CategoryPackagingSpecification constructSpecification(CategoryPackagingCriteria criteria) {
        CategoryPackagingSpecification mySpecification =  (CategoryPackagingSpecification) RefelexivityUtil.constructObjectUsingOneParam(CategoryPackagingSpecification.class, criteria);
        return mySpecification;
    }

    public List<CategoryPackaging> findPaginatedByCriteria(CategoryPackagingCriteria criteria, int page, int pageSize, String order, String sortField) {
        CategoryPackagingSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CategoryPackagingCriteria criteria) {
        CategoryPackagingSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(CategoryPackaging t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategoryPackaging> delete(List<CategoryPackaging> list) {
		List<CategoryPackaging> result = new ArrayList();
        if (list != null) {
            for (CategoryPackaging t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategoryPackaging create(CategoryPackaging t) {
        CategoryPackaging loaded = findByReferenceEntity(t);
        CategoryPackaging saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategoryPackaging> create(List<CategoryPackaging> ts) {
        List<CategoryPackaging> result = new ArrayList<>();
        if (ts != null) {
            for (CategoryPackaging t : ts) {
				CategoryPackaging created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public CategoryPackaging findWithAssociatedLists(Long id){
        CategoryPackaging result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategoryPackaging> update(List<CategoryPackaging> ts, boolean createIfNotExist) {
        List<CategoryPackaging> result = new ArrayList<>();
        if (ts != null) {
            for (CategoryPackaging t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CategoryPackaging loadedItem = dao.findById(t.getId()).orElse(null);
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





    public CategoryPackaging findByReferenceEntity(CategoryPackaging t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<CategoryPackaging> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CategoryPackaging>> getToBeSavedAndToBeDeleted(List<CategoryPackaging> oldList, List<CategoryPackaging> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<CategoryPackaging> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired CategoryPackagingDao dao;


}
