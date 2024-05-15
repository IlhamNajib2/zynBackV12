package ma.zs.zyn.service.impl.admin.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.dao.criteria.core.project.ProjectStateCriteria;
import ma.zs.zyn.dao.facade.core.project.ProjectStateDao;
import ma.zs.zyn.dao.specification.core.project.ProjectStateSpecification;
import ma.zs.zyn.service.facade.admin.project.ProjectStateAdminService;
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
public class ProjectStateAdminServiceImpl implements ProjectStateAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectState update(ProjectState t) {
        ProjectState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectState findOrSave(ProjectState t) {
        if (t != null) {
            ProjectState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<ProjectState> importData(List<ProjectState> items) {
        List<ProjectState> list = new ArrayList<>();
        for (ProjectState t : items) {
            ProjectState founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<ProjectState> findAll() {
        return dao.findAll();
    }

    public List<ProjectState> findByCriteria(ProjectStateCriteria criteria) {
        List<ProjectState> content = null;
        if (criteria != null) {
            ProjectStateSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectStateSpecification constructSpecification(ProjectStateCriteria criteria) {
        ProjectStateSpecification mySpecification =  (ProjectStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectState> findPaginatedByCriteria(ProjectStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectStateCriteria criteria) {
        ProjectStateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(ProjectState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectState> delete(List<ProjectState> list) {
		List<ProjectState> result = new ArrayList();
        if (list != null) {
            for (ProjectState t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectState create(ProjectState t) {
        ProjectState loaded = findByReferenceEntity(t);
        ProjectState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectState> create(List<ProjectState> ts) {
        List<ProjectState> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectState t : ts) {
				ProjectState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public ProjectState findWithAssociatedLists(Long id){
        ProjectState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectState> update(List<ProjectState> ts, boolean createIfNotExist) {
        List<ProjectState> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectState loadedItem = dao.findById(t.getId()).orElse(null);
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





    public ProjectState findByReferenceEntity(ProjectState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProjectState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProjectState>> getToBeSavedAndToBeDeleted(List<ProjectState> oldList, List<ProjectState> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<ProjectState> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired ProjectStateDao dao;


}
