package ma.zs.zyn.service.impl.member.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.dao.criteria.core.template.TechnologyCriteria;
import ma.zs.zyn.dao.facade.core.template.TechnologyDao;
import ma.zs.zyn.dao.specification.core.template.TechnologySpecification;
import ma.zs.zyn.service.facade.member.template.TechnologyMemberService;
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

import ma.zs.zyn.service.facade.member.template.TypeTemplateMemberService ;
import ma.zs.zyn.bean.core.template.TypeTemplate ;

import java.util.List;
@Service
public class TechnologyMemberServiceImpl implements TechnologyMemberService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Technology update(Technology t) {
        Technology loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Technology.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Technology findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Technology findOrSave(Technology t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Technology result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Technology> importData(List<Technology> items) {
        List<Technology> list = new ArrayList<>();
        for (Technology t : items) {
            Technology founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Technology> findAll() {
        return dao.findAll();
    }

    public List<Technology> findByCriteria(TechnologyCriteria criteria) {
        List<Technology> content = null;
        if (criteria != null) {
            TechnologySpecification mySpecification = constructSpecification(criteria);
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


    private TechnologySpecification constructSpecification(TechnologyCriteria criteria) {
        TechnologySpecification mySpecification =  (TechnologySpecification) RefelexivityUtil.constructObjectUsingOneParam(TechnologySpecification.class, criteria);
        return mySpecification;
    }

    public List<Technology> findPaginatedByCriteria(TechnologyCriteria criteria, int page, int pageSize, String order, String sortField) {
        TechnologySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TechnologyCriteria criteria) {
        TechnologySpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Technology> findByTypeTemplateId(Long id){
        return dao.findByTypeTemplateId(id);
    }
    public int deleteByTypeTemplateId(Long id){
        return dao.deleteByTypeTemplateId(id);
    }
    public long countByTypeTemplateId(Long id){
        return dao.countByTypeTemplateId(id);
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
    public int delete(Technology t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Technology> delete(List<Technology> list) {
		List<Technology> result = new ArrayList();
        if (list != null) {
            for (Technology t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Technology create(Technology t) {
        Technology loaded = findByReferenceEntity(t);
        Technology saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Technology> create(List<Technology> ts) {
        List<Technology> result = new ArrayList<>();
        if (ts != null) {
            for (Technology t : ts) {
				Technology created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Technology findWithAssociatedLists(Long id){
        Technology result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Technology> update(List<Technology> ts, boolean createIfNotExist) {
        List<Technology> result = new ArrayList<>();
        if (ts != null) {
            for (Technology t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Technology loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Technology findByReferenceEntity(Technology t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Technology t){
        if( t != null) {
            t.setTypeTemplate(typeTemplateService.findOrSave(t.getTypeTemplate()));
        }
    }



    public List<Technology> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Technology>> getToBeSavedAndToBeDeleted(List<Technology> oldList, List<Technology> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Technology> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TypeTemplateMemberService typeTemplateService ;

    private @Autowired TechnologyDao dao;


}
