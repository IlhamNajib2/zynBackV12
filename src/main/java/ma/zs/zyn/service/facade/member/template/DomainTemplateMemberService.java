package ma.zs.zyn.service.facade.member.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.DomainTemplate;
import ma.zs.zyn.dao.criteria.core.template.DomainTemplateCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DomainTemplateMemberService {







	DomainTemplate create(DomainTemplate t);

    DomainTemplate update(DomainTemplate t);

    List<DomainTemplate> update(List<DomainTemplate> ts,boolean createIfNotExist);

    DomainTemplate findById(Long id);

    DomainTemplate findOrSave(DomainTemplate t);

    DomainTemplate findByReferenceEntity(DomainTemplate t);

    DomainTemplate findWithAssociatedLists(Long id);

    List<DomainTemplate> findAllOptimized();

    List<DomainTemplate> findAll();

    List<DomainTemplate> findByCriteria(DomainTemplateCriteria criteria);

    List<DomainTemplate> findPaginatedByCriteria(DomainTemplateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DomainTemplateCriteria criteria);

    List<DomainTemplate> delete(List<DomainTemplate> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DomainTemplate>> getToBeSavedAndToBeDeleted(List<DomainTemplate> oldList, List<DomainTemplate> newList);

    List<DomainTemplate> importData(List<DomainTemplate> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DomainTemplate> importExcel(MultipartFile file);

}
