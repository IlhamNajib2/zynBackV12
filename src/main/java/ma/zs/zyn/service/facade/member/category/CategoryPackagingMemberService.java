package ma.zs.zyn.service.facade.member.category;

import java.util.List;
import ma.zs.zyn.bean.core.category.CategoryPackaging;
import ma.zs.zyn.dao.criteria.core.category.CategoryPackagingCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CategoryPackagingMemberService {







	CategoryPackaging create(CategoryPackaging t);

    CategoryPackaging update(CategoryPackaging t);

    List<CategoryPackaging> update(List<CategoryPackaging> ts,boolean createIfNotExist);

    CategoryPackaging findById(Long id);

    CategoryPackaging findOrSave(CategoryPackaging t);

    CategoryPackaging findByReferenceEntity(CategoryPackaging t);

    CategoryPackaging findWithAssociatedLists(Long id);

    List<CategoryPackaging> findAllOptimized();

    List<CategoryPackaging> findAll();

    List<CategoryPackaging> findByCriteria(CategoryPackagingCriteria criteria);

    List<CategoryPackaging> findPaginatedByCriteria(CategoryPackagingCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CategoryPackagingCriteria criteria);

    List<CategoryPackaging> delete(List<CategoryPackaging> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<CategoryPackaging>> getToBeSavedAndToBeDeleted(List<CategoryPackaging> oldList, List<CategoryPackaging> newList);

    List<CategoryPackaging> importData(List<CategoryPackaging> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<CategoryPackaging> importExcel(MultipartFile file);

}
