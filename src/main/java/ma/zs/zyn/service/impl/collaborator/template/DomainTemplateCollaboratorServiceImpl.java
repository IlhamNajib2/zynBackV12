package ma.zs.zyn.service.impl.collaborator.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.DomainTemplate;
import ma.zs.zyn.dao.criteria.core.template.DomainTemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.DomainTemplateDao;
import ma.zs.zyn.dao.specification.core.template.DomainTemplateSpecification;
import ma.zs.zyn.service.facade.collaborator.template.DomainTemplateCollaboratorService;
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
public class DomainTemplateCollaboratorServiceImpl implements DomainTemplateCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DomainTemplate update(DomainTemplate t) {
        DomainTemplate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DomainTemplate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DomainTemplate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DomainTemplate findOrSave(DomainTemplate t) {
        if (t != null) {
            DomainTemplate result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DomainTemplate> importData(List<DomainTemplate> items) {
        List<DomainTemplate> list = new ArrayList<>();
        for (DomainTemplate t : items) {
            DomainTemplate founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DomainTemplate> findAll() {
        return dao.findAll();
    }

    public List<DomainTemplate> findByCriteria(DomainTemplateCriteria criteria) {
        List<DomainTemplate> content = null;
        if (criteria != null) {
            DomainTemplateSpecification mySpecification = constructSpecification(criteria);
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


    private DomainTemplateSpecification constructSpecification(DomainTemplateCriteria criteria) {
        DomainTemplateSpecification mySpecification =  (DomainTemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(DomainTemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<DomainTemplate> findPaginatedByCriteria(DomainTemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        DomainTemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DomainTemplateCriteria criteria) {
        DomainTemplateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(DomainTemplate t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DomainTemplate> delete(List<DomainTemplate> list) {
		List<DomainTemplate> result = new ArrayList();
        if (list != null) {
            for (DomainTemplate t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DomainTemplate create(DomainTemplate t) {
        DomainTemplate loaded = findByReferenceEntity(t);
        DomainTemplate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DomainTemplate> create(List<DomainTemplate> ts) {
        List<DomainTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (DomainTemplate t : ts) {
				DomainTemplate created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DomainTemplate findWithAssociatedLists(Long id){
        DomainTemplate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DomainTemplate> update(List<DomainTemplate> ts, boolean createIfNotExist) {
        List<DomainTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (DomainTemplate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DomainTemplate loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DomainTemplate findByReferenceEntity(DomainTemplate t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<DomainTemplate> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DomainTemplate>> getToBeSavedAndToBeDeleted(List<DomainTemplate> oldList, List<DomainTemplate> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DomainTemplate> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired DomainTemplateDao dao;


}
