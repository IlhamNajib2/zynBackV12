package ma.zs.zyn.service.impl.collaborator.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.dao.criteria.core.template.TemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.TemplateDao;
import ma.zs.zyn.dao.specification.core.template.TemplateSpecification;
import ma.zs.zyn.service.facade.collaborator.template.TemplateCollaboratorService;
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

import ma.zs.zyn.service.facade.collaborator.template.CategoryTemplateCollaboratorService ;
import ma.zs.zyn.bean.core.template.CategoryTemplate ;
import ma.zs.zyn.service.facade.collaborator.template.TechnologyCollaboratorService ;
import ma.zs.zyn.bean.core.template.Technology ;
import ma.zs.zyn.service.facade.collaborator.template.LevelTemplateCollaboratorService ;
import ma.zs.zyn.bean.core.template.LevelTemplate ;
import ma.zs.zyn.service.facade.collaborator.template.TypeTemplateCollaboratorService ;
import ma.zs.zyn.bean.core.template.TypeTemplate ;
import ma.zs.zyn.service.facade.collaborator.template.DomainTemplateCollaboratorService ;
import ma.zs.zyn.bean.core.template.DomainTemplate ;
import ma.zs.zyn.service.facade.collaborator.collaborator.MemberCollaboratorService ;
import ma.zs.zyn.bean.core.collaborator.Member ;

import java.util.List;
@Service
public class TemplateCollaboratorServiceImpl implements TemplateCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Template update(Template t) {
        Template loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Template.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Template findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Template findOrSave(Template t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Template result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Template> importData(List<Template> items) {
        List<Template> list = new ArrayList<>();
        for (Template t : items) {
            Template founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Template> findAll() {
        return dao.findAll();
    }

    public List<Template> findByCriteria(TemplateCriteria criteria) {
        List<Template> content = null;
        if (criteria != null) {
            TemplateSpecification mySpecification = constructSpecification(criteria);
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


    private TemplateSpecification constructSpecification(TemplateCriteria criteria) {
        TemplateSpecification mySpecification =  (TemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(TemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<Template> findPaginatedByCriteria(TemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        TemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TemplateCriteria criteria) {
        TemplateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Template> findByCategoryTemplateId(Long id){
        return dao.findByCategoryTemplateId(id);
    }
    public int deleteByCategoryTemplateId(Long id){
        return dao.deleteByCategoryTemplateId(id);
    }
    public long countByCategoryTemplateId(Long id){
        return dao.countByCategoryTemplateId(id);
    }
    public List<Template> findByTypeTemplateId(Long id){
        return dao.findByTypeTemplateId(id);
    }
    public int deleteByTypeTemplateId(Long id){
        return dao.deleteByTypeTemplateId(id);
    }
    public long countByTypeTemplateId(Long id){
        return dao.countByTypeTemplateId(id);
    }
    public List<Template> findByLevelTemplateId(Long id){
        return dao.findByLevelTemplateId(id);
    }
    public int deleteByLevelTemplateId(Long id){
        return dao.deleteByLevelTemplateId(id);
    }
    public long countByLevelTemplateId(Long id){
        return dao.countByLevelTemplateId(id);
    }
    public List<Template> findByDomainTemplateId(Long id){
        return dao.findByDomainTemplateId(id);
    }
    public int deleteByDomainTemplateId(Long id){
        return dao.deleteByDomainTemplateId(id);
    }
    public long countByDomainTemplateId(Long id){
        return dao.countByDomainTemplateId(id);
    }
    public List<Template> findByMemberId(Long id){
        return dao.findByMemberId(id);
    }
    public int deleteByMemberId(Long id){
        return dao.deleteByMemberId(id);
    }
    public long countByMemberId(Long id){
        return dao.countByMemberId(id);
    }
    public List<Template> findByTechnologyId(Long id){
        return dao.findByTechnologyId(id);
    }
    public int deleteByTechnologyId(Long id){
        return dao.deleteByTechnologyId(id);
    }
    public long countByTechnologyId(Long id){
        return dao.countByTechnologyId(id);
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
    public int delete(Template t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Template> delete(List<Template> list) {
		List<Template> result = new ArrayList();
        if (list != null) {
            for (Template t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Template create(Template t) {
        Template loaded = findByReferenceEntity(t);
        Template saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Template> create(List<Template> ts) {
        List<Template> result = new ArrayList<>();
        if (ts != null) {
            for (Template t : ts) {
				Template created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Template findWithAssociatedLists(Long id){
        Template result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Template> update(List<Template> ts, boolean createIfNotExist) {
        List<Template> result = new ArrayList<>();
        if (ts != null) {
            for (Template t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Template loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Template findByReferenceEntity(Template t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Template t){
        if( t != null) {
            t.setCategoryTemplate(categoryTemplateService.findOrSave(t.getCategoryTemplate()));
            t.setTypeTemplate(typeTemplateService.findOrSave(t.getTypeTemplate()));
            t.setLevelTemplate(levelTemplateService.findOrSave(t.getLevelTemplate()));
            t.setDomainTemplate(domainTemplateService.findOrSave(t.getDomainTemplate()));
            t.setMember(memberService.findOrSave(t.getMember()));
            t.setTechnology(technologyService.findOrSave(t.getTechnology()));
        }
    }



    public List<Template> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Template>> getToBeSavedAndToBeDeleted(List<Template> oldList, List<Template> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Template> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private CategoryTemplateCollaboratorService categoryTemplateService ;
    @Autowired
    private TechnologyCollaboratorService technologyService ;
    @Autowired
    private LevelTemplateCollaboratorService levelTemplateService ;
    @Autowired
    private TypeTemplateCollaboratorService typeTemplateService ;
    @Autowired
    private DomainTemplateCollaboratorService domainTemplateService ;
    @Autowired
    private MemberCollaboratorService memberService ;

    private @Autowired TemplateDao dao;


}
