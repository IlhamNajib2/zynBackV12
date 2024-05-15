package ma.zs.zyn.service.facade.admin.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.TypeTemplate;
import ma.zs.zyn.dao.criteria.core.template.TypeTemplateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeTemplateAdminService {







	TypeTemplate create(TypeTemplate t);

    TypeTemplate update(TypeTemplate t);

    List<TypeTemplate> update(List<TypeTemplate> ts,boolean createIfNotExist);

    TypeTemplate findById(Long id);

    TypeTemplate findOrSave(TypeTemplate t);

    TypeTemplate findByReferenceEntity(TypeTemplate t);

    TypeTemplate findWithAssociatedLists(Long id);

    List<TypeTemplate> findAllOptimized();

    List<TypeTemplate> findAll();

    List<TypeTemplate> findByCriteria(TypeTemplateCriteria criteria);

    List<TypeTemplate> findPaginatedByCriteria(TypeTemplateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeTemplateCriteria criteria);

    List<TypeTemplate> delete(List<TypeTemplate> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeTemplate>> getToBeSavedAndToBeDeleted(List<TypeTemplate> oldList, List<TypeTemplate> newList);

    List<TypeTemplate> importData(List<TypeTemplate> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeTemplate> importExcel(MultipartFile file);

}
