package ma.zs.zyn.service.facade.member.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.LevelTemplate;
import ma.zs.zyn.dao.criteria.core.template.LevelTemplateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface LevelTemplateMemberService {







	LevelTemplate create(LevelTemplate t);

    LevelTemplate update(LevelTemplate t);

    List<LevelTemplate> update(List<LevelTemplate> ts,boolean createIfNotExist);

    LevelTemplate findById(Long id);

    LevelTemplate findOrSave(LevelTemplate t);

    LevelTemplate findByReferenceEntity(LevelTemplate t);

    LevelTemplate findWithAssociatedLists(Long id);

    List<LevelTemplate> findAllOptimized();

    List<LevelTemplate> findAll();

    List<LevelTemplate> findByCriteria(LevelTemplateCriteria criteria);

    List<LevelTemplate> findPaginatedByCriteria(LevelTemplateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LevelTemplateCriteria criteria);

    List<LevelTemplate> delete(List<LevelTemplate> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<LevelTemplate>> getToBeSavedAndToBeDeleted(List<LevelTemplate> oldList, List<LevelTemplate> newList);

    List<LevelTemplate> importData(List<LevelTemplate> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<LevelTemplate> importExcel(MultipartFile file);

}
