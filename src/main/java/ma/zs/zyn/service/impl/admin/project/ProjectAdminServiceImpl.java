package ma.zs.zyn.service.impl.admin.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.dao.facade.core.project.ProjectDao;
import ma.zs.zyn.dao.specification.core.project.ProjectSpecification;
import ma.zs.zyn.service.facade.admin.project.ProjectAdminService;
import ma.zs.zyn.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.service.facade.admin.inscription.InscriptionMembreAdminService ;
import ma.zs.zyn.service.facade.admin.project.ProjectStateAdminService ;
import ma.zs.zyn.service.facade.admin.template.DomainTemplateAdminService ;
import ma.zs.zyn.service.facade.admin.template.ProjectTemplateAdminService ;
import ma.zs.zyn.bean.core.template.ProjectTemplate ;

@Service
public class ProjectAdminServiceImpl implements ProjectAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Project update(Project t) {
        Project loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Project.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }





    @Override
    public Map<String, Long> getProjectsByMonth(int year) {
        Map<String, Long> projectsByMonth = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
            LocalDateTime endDate = LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59, 59);

            long count = dao.findByGeneratedDateBetween(startDate, endDate).size();
            projectsByMonth.put(Month.of(month).toString(), count);
        }
        return projectsByMonth;
    }





    @Override
    public Long countProjectsByDay(LocalDate date) {
        LocalDateTime startOfDay = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(date, LocalTime.MAX);
        return dao.count((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("generatedDate"), startOfDay, endOfDay));
    }


    @Override
    public Long countProjectsByWeek(LocalDate date) {
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        return dao.count((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("generatedDate"), startOfWeek, endOfWeek));
    }

    @Override
    public Long countProjectsByMonth(LocalDate date) {
        LocalDate startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        return dao.count((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("generatedDate"), startOfMonth, endOfMonth));
    }

    @Override
    public Long countProjectsByYear(LocalDate date) {
        LocalDate startOfYear = date.with(TemporalAdjusters.firstDayOfYear());
        LocalDate endOfYear = date.with(TemporalAdjusters.lastDayOfYear());

        return dao.count((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("generatedDate"), startOfYear, endOfYear));
    }








    public Project findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Project findOrSave(Project t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Project result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Project> importData(List<Project> items) {
        List<Project> list = new ArrayList<>();
        for (Project t : items) {
            Project founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Project> findAll() {
        return dao.findAll();
    }

    public List<Project> findByCriteria(ProjectCriteria criteria) {
        List<Project> content = null;
        if (criteria != null) {
            ProjectSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectSpecification constructSpecification(ProjectCriteria criteria) {
        ProjectSpecification mySpecification =  (ProjectSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectSpecification.class, criteria);
        return mySpecification;
    }

    public List<Project> findPaginatedByCriteria(ProjectCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectCriteria criteria) {
        ProjectSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Project> findByProjectStateCode(String code){
        return dao.findByProjectStateCode(code);
    }
    public int deleteByProjectStateCode(String code){
        return dao.deleteByProjectStateCode(code);
    }
    public long countByProjectStateCode(String code){
        return dao.countByProjectStateCode(code);
    }
    public List<Project> findByInscriptionMembreId(Long id){
        return dao.findByInscriptionMembreId(id);
    }
    public int deleteByInscriptionMembreId(Long id){
        return dao.deleteByInscriptionMembreId(id);
    }
    public long countByInscriptionMembreId(Long id){
        return dao.countByInscriptionMembreId(id);
    }
    public List<Project> findByDomainTemplateId(Long id){
        return dao.findByDomainTemplateId(id);
    }
    public int deleteByDomainTemplateId(Long id){
        return dao.deleteByDomainTemplateId(id);
    }
    public long countByDomainTemplateId(Long id){
        return dao.countByDomainTemplateId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
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
    public int delete(Project t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        projectTemplateService.deleteByProjectId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Project> delete(List<Project> list) {
		List<Project> result = new ArrayList();
        if (list != null) {
            for (Project t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Project create(Project t) {
        Project loaded = findByReferenceEntity(t);
        Project saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getProjectTemplates() != null) {
                t.getProjectTemplates().forEach(element-> {
                    element.setProject(saved);
                    projectTemplateService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Project> create(List<Project> ts) {
        List<Project> result = new ArrayList<>();
        if (ts != null) {
            for (Project t : ts) {
				Project created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Project findWithAssociatedLists(Long id){
        Project result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setProjectTemplates(projectTemplateService.findByProjectId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Project> update(List<Project> ts, boolean createIfNotExist) {
        List<Project> result = new ArrayList<>();
        if (ts != null) {
            for (Project t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Project loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Project project){
    if(project !=null && project.getId() != null){
        List<List<ProjectTemplate>> resultProjectTemplates= projectTemplateService.getToBeSavedAndToBeDeleted(projectTemplateService.findByProjectId(project.getId()),project.getProjectTemplates());
            projectTemplateService.delete(resultProjectTemplates.get(1));
        ListUtil.emptyIfNull(resultProjectTemplates.get(0)).forEach(e -> e.setProject(project));
        projectTemplateService.update(resultProjectTemplates.get(0),true);
        }
    }




    public Project findByReferenceEntity(Project t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Project t){
        if( t != null) {
            t.setProjectState(projectStateService.findOrSave(t.getProjectState()));
            t.setInscriptionMembre(inscriptionMembreService.findOrSave(t.getInscriptionMembre()));
            t.setDomainTemplate(domainTemplateService.findOrSave(t.getDomainTemplate()));
        }
    }



    public List<Project> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Project>> getToBeSavedAndToBeDeleted(List<Project> oldList, List<Project> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Project> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private InscriptionMembreAdminService inscriptionMembreService ;
    @Autowired
    private ProjectStateAdminService projectStateService ;
    @Autowired
    private DomainTemplateAdminService domainTemplateService ;
    @Autowired
    private ProjectTemplateAdminService projectTemplateService ;

    private @Autowired ProjectDao dao;


}
