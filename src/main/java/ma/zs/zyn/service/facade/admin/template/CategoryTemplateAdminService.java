package ma.zs.zyn.service.facade.admin.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.CategoryTemplate;
import ma.zs.zyn.dao.criteria.core.template.CategoryTemplateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CategoryTemplateAdminService {







	CategoryTemplate create(CategoryTemplate t);

    CategoryTemplate update(CategoryTemplate t);

    List<CategoryTemplate> update(List<CategoryTemplate> ts,boolean createIfNotExist);

    CategoryTemplate findById(Long id);

    CategoryTemplate findOrSave(CategoryTemplate t);

    CategoryTemplate findByReferenceEntity(CategoryTemplate t);

    CategoryTemplate findWithAssociatedLists(Long id);

    List<CategoryTemplate> findAllOptimized();

    List<CategoryTemplate> findAll();

    List<CategoryTemplate> findByCriteria(CategoryTemplateCriteria criteria);

    List<CategoryTemplate> findPaginatedByCriteria(CategoryTemplateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CategoryTemplateCriteria criteria);

    List<CategoryTemplate> delete(List<CategoryTemplate> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<CategoryTemplate>> getToBeSavedAndToBeDeleted(List<CategoryTemplate> oldList, List<CategoryTemplate> newList);

    List<CategoryTemplate> importData(List<CategoryTemplate> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<CategoryTemplate> importExcel(MultipartFile file);

}
