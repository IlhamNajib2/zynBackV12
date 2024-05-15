package ma.zs.zyn.service.impl.admin.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.dao.criteria.core.template.ProjectTemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.ProjectTemplateDao;
import ma.zs.zyn.dao.specification.core.template.ProjectTemplateSpecification;
import ma.zs.zyn.service.facade.admin.template.ProjectTemplateAdminService;
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

import ma.zs.zyn.service.facade.admin.project.ProjectAdminService ;
import ma.zs.zyn.bean.core.project.Project ;
import ma.zs.zyn.service.facade.admin.template.TemplateAdminService ;
import ma.zs.zyn.bean.core.template.Template ;

import java.util.List;
@Service
public class ProjectTemplateAdminServiceImpl implements ProjectTemplateAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTemplate update(ProjectTemplate t) {
        ProjectTemplate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectTemplate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectTemplate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectTemplate findOrSave(ProjectTemplate t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ProjectTemplate result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<ProjectTemplate> importData(List<ProjectTemplate> items) {
        List<ProjectTemplate> list = new ArrayList<>();
        for (ProjectTemplate t : items) {
            ProjectTemplate founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<ProjectTemplate> findAll() {
        return dao.findAll();
    }

    public List<ProjectTemplate> findByCriteria(ProjectTemplateCriteria criteria) {
        List<ProjectTemplate> content = null;
        if (criteria != null) {
            ProjectTemplateSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectTemplateSpecification constructSpecification(ProjectTemplateCriteria criteria) {
        ProjectTemplateSpecification mySpecification =  (ProjectTemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectTemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectTemplate> findPaginatedByCriteria(ProjectTemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectTemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectTemplateCriteria criteria) {
        ProjectTemplateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ProjectTemplate> findByTemplateId(Long id){
        return dao.findByTemplateId(id);
    }
    public int deleteByTemplateId(Long id){
        return dao.deleteByTemplateId(id);
    }
    public long countByTemplateCode(String code){
        return dao.countByTemplateCode(code);
    }
    public List<ProjectTemplate> findByProjectId(Long id){
        return dao.findByProjectId(id);
    }
    public int deleteByProjectId(Long id){
        return dao.deleteByProjectId(id);
    }
    public long countByProjectCode(String code){
        return dao.countByProjectCode(code);
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
    public int delete(ProjectTemplate t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTemplate> delete(List<ProjectTemplate> list) {
		List<ProjectTemplate> result = new ArrayList();
        if (list != null) {
            for (ProjectTemplate t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTemplate create(ProjectTemplate t) {
        ProjectTemplate loaded = findByReferenceEntity(t);
        ProjectTemplate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTemplate> create(List<ProjectTemplate> ts) {
        List<ProjectTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectTemplate t : ts) {
				ProjectTemplate created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public ProjectTemplate findWithAssociatedLists(Long id){
        ProjectTemplate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTemplate> update(List<ProjectTemplate> ts, boolean createIfNotExist) {
        List<ProjectTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectTemplate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectTemplate loadedItem = dao.findById(t.getId()).orElse(null);
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





    public ProjectTemplate findByReferenceEntity(ProjectTemplate t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ProjectTemplate t){
        if( t != null) {
            t.setTemplate(templateService.findOrSave(t.getTemplate()));
            t.setProject(projectService.findOrSave(t.getProject()));
        }
    }



    public List<ProjectTemplate> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ProjectTemplate>> getToBeSavedAndToBeDeleted(List<ProjectTemplate> oldList, List<ProjectTemplate> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<ProjectTemplate> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ProjectAdminService projectService ;
    @Autowired
    private TemplateAdminService templateService ;

    private @Autowired ProjectTemplateDao dao;


}
