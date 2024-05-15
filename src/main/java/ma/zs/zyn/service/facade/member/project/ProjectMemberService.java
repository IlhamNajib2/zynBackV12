package ma.zs.zyn.service.facade.member.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ProjectMemberService {



    List<Project> findByProjectStateCode(String code);
    int deleteByProjectStateCode(String code);
    long countByProjectStateCode(String code);
    List<Project> findByInscriptionMembreId(Long id);
    int deleteByInscriptionMembreId(Long id);
    long countByInscriptionMembreId(Long id);
    List<Project> findByDomainTemplateId(Long id);
    int deleteByDomainTemplateId(Long id);
    long countByDomainTemplateId(Long id);




	Project create(Project t);

    Project update(Project t);

    List<Project> update(List<Project> ts,boolean createIfNotExist);

    Project findById(Long id);

    Project findOrSave(Project t);

    Project findByReferenceEntity(Project t);

    Project findWithAssociatedLists(Long id);

    List<Project> findAllOptimized();

    List<Project> findAll();

    List<Project> findByCriteria(ProjectCriteria criteria);

    List<Project> findPaginatedByCriteria(ProjectCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectCriteria criteria);

    List<Project> delete(List<Project> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Project>> getToBeSavedAndToBeDeleted(List<Project> oldList, List<Project> newList);

    List<Project> importData(List<Project> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Project> importExcel(MultipartFile file);

}
