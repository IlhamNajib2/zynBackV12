package ma.zs.zyn.service.impl.admin.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.TypeTemplate;
import ma.zs.zyn.dao.criteria.core.template.TypeTemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.TypeTemplateDao;
import ma.zs.zyn.dao.specification.core.template.TypeTemplateSpecification;
import ma.zs.zyn.service.facade.admin.template.TypeTemplateAdminService;
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
public class TypeTemplateAdminServiceImpl implements TypeTemplateAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeTemplate update(TypeTemplate t) {
        TypeTemplate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeTemplate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeTemplate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeTemplate findOrSave(TypeTemplate t) {
        if (t != null) {
            TypeTemplate result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeTemplate> importData(List<TypeTemplate> items) {
        List<TypeTemplate> list = new ArrayList<>();
        for (TypeTemplate t : items) {
            TypeTemplate founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeTemplate> findAll() {
        return dao.findAll();
    }

    public List<TypeTemplate> findByCriteria(TypeTemplateCriteria criteria) {
        List<TypeTemplate> content = null;
        if (criteria != null) {
            TypeTemplateSpecification mySpecification = constructSpecification(criteria);
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


    private TypeTemplateSpecification constructSpecification(TypeTemplateCriteria criteria) {
        TypeTemplateSpecification mySpecification =  (TypeTemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeTemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeTemplate> findPaginatedByCriteria(TypeTemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeTemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeTemplateCriteria criteria) {
        TypeTemplateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(TypeTemplate t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeTemplate> delete(List<TypeTemplate> list) {
		List<TypeTemplate> result = new ArrayList();
        if (list != null) {
            for (TypeTemplate t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeTemplate create(TypeTemplate t) {
        TypeTemplate loaded = findByReferenceEntity(t);
        TypeTemplate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeTemplate> create(List<TypeTemplate> ts) {
        List<TypeTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (TypeTemplate t : ts) {
				TypeTemplate created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeTemplate findWithAssociatedLists(Long id){
        TypeTemplate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeTemplate> update(List<TypeTemplate> ts, boolean createIfNotExist) {
        List<TypeTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (TypeTemplate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeTemplate loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeTemplate findByReferenceEntity(TypeTemplate t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<TypeTemplate> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeTemplate>> getToBeSavedAndToBeDeleted(List<TypeTemplate> oldList, List<TypeTemplate> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeTemplate> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeTemplateDao dao;


}
