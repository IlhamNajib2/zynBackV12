package ma.zs.zyn.service.facade.member.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.dao.criteria.core.template.TemplateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TemplateMemberService {



    List<Template> findByCategoryTemplateId(Long id);
    int deleteByCategoryTemplateId(Long id);
    long countByCategoryTemplateId(Long id);
    List<Template> findByTypeTemplateId(Long id);
    int deleteByTypeTemplateId(Long id);
    long countByTypeTemplateId(Long id);
    List<Template> findByLevelTemplateId(Long id);
    int deleteByLevelTemplateId(Long id);
    long countByLevelTemplateId(Long id);
    List<Template> findByDomainTemplateId(Long id);
    int deleteByDomainTemplateId(Long id);
    long countByDomainTemplateId(Long id);
    List<Template> findByMemberId(Long id);
    int deleteByMemberId(Long id);
    long countByMemberId(Long id);
    List<Template> findByTechnologyId(Long id);
    int deleteByTechnologyId(Long id);
    long countByTechnologyId(Long id);




	Template create(Template t);

    Template update(Template t);

    List<Template> update(List<Template> ts,boolean createIfNotExist);

    Template findById(Long id);

    Template findOrSave(Template t);

    Template findByReferenceEntity(Template t);

    Template findWithAssociatedLists(Long id);

    List<Template> findAllOptimized();

    List<Template> findAll();

    List<Template> findByCriteria(TemplateCriteria criteria);

    List<Template> findPaginatedByCriteria(TemplateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TemplateCriteria criteria);

    List<Template> delete(List<Template> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Template>> getToBeSavedAndToBeDeleted(List<Template> oldList, List<Template> newList);

    List<Template> importData(List<Template> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Template> importExcel(MultipartFile file);

}
