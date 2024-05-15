package ma.zs.zyn.service.facade.member.packaging;

import java.util.List;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.dao.criteria.core.packaging.PackagingCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PackagingMemberService {



    List<Packaging> findByCategoryPackagingId(Long id);
    int deleteByCategoryPackagingId(Long id);
    long countByCategoryPackagingId(Long id);




	Packaging create(Packaging t);

    Packaging update(Packaging t);

    List<Packaging> update(List<Packaging> ts,boolean createIfNotExist);

    Packaging findById(Long id);

    Packaging findOrSave(Packaging t);

    Packaging findByReferenceEntity(Packaging t);

    Packaging findWithAssociatedLists(Long id);

    List<Packaging> findAllOptimized();

    List<Packaging> findAll();

    List<Packaging> findByCriteria(PackagingCriteria criteria);

    List<Packaging> findPaginatedByCriteria(PackagingCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PackagingCriteria criteria);

    List<Packaging> delete(List<Packaging> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Packaging>> getToBeSavedAndToBeDeleted(List<Packaging> oldList, List<Packaging> newList);

    List<Packaging> importData(List<Packaging> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Packaging> importExcel(MultipartFile file);

}
