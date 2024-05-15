package ma.zs.zyn.service.facade.member.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.dao.criteria.core.template.TechnologyCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TechnologyMemberService {



    List<Technology> findByTypeTemplateId(Long id);
    int deleteByTypeTemplateId(Long id);
    long countByTypeTemplateId(Long id);




	Technology create(Technology t);

    Technology update(Technology t);

    List<Technology> update(List<Technology> ts,boolean createIfNotExist);

    Technology findById(Long id);

    Technology findOrSave(Technology t);

    Technology findByReferenceEntity(Technology t);

    Technology findWithAssociatedLists(Long id);

    List<Technology> findAllOptimized();

    List<Technology> findAll();

    List<Technology> findByCriteria(TechnologyCriteria criteria);

    List<Technology> findPaginatedByCriteria(TechnologyCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TechnologyCriteria criteria);

    List<Technology> delete(List<Technology> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Technology>> getToBeSavedAndToBeDeleted(List<Technology> oldList, List<Technology> newList);

    List<Technology> importData(List<Technology> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Technology> importExcel(MultipartFile file);

}
