package ma.zs.zyn.service.facade.admin.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.dao.criteria.core.template.ProjectTemplateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ProjectTemplateAdminService {



    List<ProjectTemplate> findByTemplateId(Long id);
    int deleteByTemplateId(Long id);
    long countByTemplateCode(String code);
    List<ProjectTemplate> findByProjectId(Long id);
    int deleteByProjectId(Long id);
    long countByProjectCode(String code);




	ProjectTemplate create(ProjectTemplate t);

    ProjectTemplate update(ProjectTemplate t);

    List<ProjectTemplate> update(List<ProjectTemplate> ts,boolean createIfNotExist);

    ProjectTemplate findById(Long id);

    ProjectTemplate findOrSave(ProjectTemplate t);

    ProjectTemplate findByReferenceEntity(ProjectTemplate t);

    ProjectTemplate findWithAssociatedLists(Long id);

    List<ProjectTemplate> findAllOptimized();

    List<ProjectTemplate> findAll();

    List<ProjectTemplate> findByCriteria(ProjectTemplateCriteria criteria);

    List<ProjectTemplate> findPaginatedByCriteria(ProjectTemplateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectTemplateCriteria criteria);

    List<ProjectTemplate> delete(List<ProjectTemplate> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<ProjectTemplate>> getToBeSavedAndToBeDeleted(List<ProjectTemplate> oldList, List<ProjectTemplate> newList);

    List<ProjectTemplate> importData(List<ProjectTemplate> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<ProjectTemplate> importExcel(MultipartFile file);

}
