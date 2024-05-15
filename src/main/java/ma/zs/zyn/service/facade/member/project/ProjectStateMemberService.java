package ma.zs.zyn.service.facade.member.project;

import java.util.List;
import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.dao.criteria.core.project.ProjectStateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ProjectStateMemberService {







	ProjectState create(ProjectState t);

    ProjectState update(ProjectState t);

    List<ProjectState> update(List<ProjectState> ts,boolean createIfNotExist);

    ProjectState findById(Long id);

    ProjectState findOrSave(ProjectState t);

    ProjectState findByReferenceEntity(ProjectState t);

    ProjectState findWithAssociatedLists(Long id);

    List<ProjectState> findAllOptimized();

    List<ProjectState> findAll();

    List<ProjectState> findByCriteria(ProjectStateCriteria criteria);

    List<ProjectState> findPaginatedByCriteria(ProjectStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjectStateCriteria criteria);

    List<ProjectState> delete(List<ProjectState> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<ProjectState>> getToBeSavedAndToBeDeleted(List<ProjectState> oldList, List<ProjectState> newList);

    List<ProjectState> importData(List<ProjectState> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<ProjectState> importExcel(MultipartFile file);

}
