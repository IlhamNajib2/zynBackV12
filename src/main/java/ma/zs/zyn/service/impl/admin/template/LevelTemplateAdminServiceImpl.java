package ma.zs.zyn.service.impl.admin.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.LevelTemplate;
import ma.zs.zyn.dao.criteria.core.template.LevelTemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.LevelTemplateDao;
import ma.zs.zyn.dao.specification.core.template.LevelTemplateSpecification;
import ma.zs.zyn.service.facade.admin.template.LevelTemplateAdminService;
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
public class LevelTemplateAdminServiceImpl implements LevelTemplateAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public LevelTemplate update(LevelTemplate t) {
        LevelTemplate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{LevelTemplate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public LevelTemplate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public LevelTemplate findOrSave(LevelTemplate t) {
        if (t != null) {
            LevelTemplate result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<LevelTemplate> importData(List<LevelTemplate> items) {
        List<LevelTemplate> list = new ArrayList<>();
        for (LevelTemplate t : items) {
            LevelTemplate founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<LevelTemplate> findAll() {
        return dao.findAll();
    }

    public List<LevelTemplate> findByCriteria(LevelTemplateCriteria criteria) {
        List<LevelTemplate> content = null;
        if (criteria != null) {
            LevelTemplateSpecification mySpecification = constructSpecification(criteria);
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


    private LevelTemplateSpecification constructSpecification(LevelTemplateCriteria criteria) {
        LevelTemplateSpecification mySpecification =  (LevelTemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(LevelTemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<LevelTemplate> findPaginatedByCriteria(LevelTemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        LevelTemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LevelTemplateCriteria criteria) {
        LevelTemplateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(LevelTemplate t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<LevelTemplate> delete(List<LevelTemplate> list) {
		List<LevelTemplate> result = new ArrayList();
        if (list != null) {
            for (LevelTemplate t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public LevelTemplate create(LevelTemplate t) {
        LevelTemplate loaded = findByReferenceEntity(t);
        LevelTemplate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<LevelTemplate> create(List<LevelTemplate> ts) {
        List<LevelTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (LevelTemplate t : ts) {
				LevelTemplate created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public LevelTemplate findWithAssociatedLists(Long id){
        LevelTemplate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<LevelTemplate> update(List<LevelTemplate> ts, boolean createIfNotExist) {
        List<LevelTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (LevelTemplate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    LevelTemplate loadedItem = dao.findById(t.getId()).orElse(null);
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





    public LevelTemplate findByReferenceEntity(LevelTemplate t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<LevelTemplate> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<LevelTemplate>> getToBeSavedAndToBeDeleted(List<LevelTemplate> oldList, List<LevelTemplate> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<LevelTemplate> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired LevelTemplateDao dao;


}
