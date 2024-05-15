package ma.zs.zyn.service.impl.member.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.CategoryTemplate;
import ma.zs.zyn.dao.criteria.core.template.CategoryTemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.CategoryTemplateDao;
import ma.zs.zyn.dao.specification.core.template.CategoryTemplateSpecification;
import ma.zs.zyn.service.facade.member.template.CategoryTemplateMemberService;
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
public class CategoryTemplateMemberServiceImpl implements CategoryTemplateMemberService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategoryTemplate update(CategoryTemplate t) {
        CategoryTemplate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CategoryTemplate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CategoryTemplate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CategoryTemplate findOrSave(CategoryTemplate t) {
        if (t != null) {
            CategoryTemplate result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<CategoryTemplate> importData(List<CategoryTemplate> items) {
        List<CategoryTemplate> list = new ArrayList<>();
        for (CategoryTemplate t : items) {
            CategoryTemplate founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<CategoryTemplate> findAll() {
        return dao.findAll();
    }

    public List<CategoryTemplate> findByCriteria(CategoryTemplateCriteria criteria) {
        List<CategoryTemplate> content = null;
        if (criteria != null) {
            CategoryTemplateSpecification mySpecification = constructSpecification(criteria);
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


    private CategoryTemplateSpecification constructSpecification(CategoryTemplateCriteria criteria) {
        CategoryTemplateSpecification mySpecification =  (CategoryTemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(CategoryTemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<CategoryTemplate> findPaginatedByCriteria(CategoryTemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        CategoryTemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CategoryTemplateCriteria criteria) {
        CategoryTemplateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(CategoryTemplate t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategoryTemplate> delete(List<CategoryTemplate> list) {
		List<CategoryTemplate> result = new ArrayList();
        if (list != null) {
            for (CategoryTemplate t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategoryTemplate create(CategoryTemplate t) {
        CategoryTemplate loaded = findByReferenceEntity(t);
        CategoryTemplate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategoryTemplate> create(List<CategoryTemplate> ts) {
        List<CategoryTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (CategoryTemplate t : ts) {
				CategoryTemplate created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public CategoryTemplate findWithAssociatedLists(Long id){
        CategoryTemplate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategoryTemplate> update(List<CategoryTemplate> ts, boolean createIfNotExist) {
        List<CategoryTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (CategoryTemplate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CategoryTemplate loadedItem = dao.findById(t.getId()).orElse(null);
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





    public CategoryTemplate findByReferenceEntity(CategoryTemplate t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<CategoryTemplate> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CategoryTemplate>> getToBeSavedAndToBeDeleted(List<CategoryTemplate> oldList, List<CategoryTemplate> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<CategoryTemplate> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired CategoryTemplateDao dao;


}
