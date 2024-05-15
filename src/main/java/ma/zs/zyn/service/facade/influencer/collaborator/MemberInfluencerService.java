package ma.zs.zyn.service.facade.influencer.collaborator;

import java.util.List;
import ma.zs.zyn.bean.core.collaborator.Member;
import ma.zs.zyn.dao.criteria.core.collaborator.MemberCriteria;
import ma.zs.zyn.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface MemberInfluencerService {


    Member findByUsername(String username);
    boolean changePassword(String username, String newPassword);

    List<Member> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);




	Member create(Member t);

    Member update(Member t);

    List<Member> update(List<Member> ts,boolean createIfNotExist);

    Member findById(Long id);

    Member findOrSave(Member t);

    Member findByReferenceEntity(Member t);

    Member findWithAssociatedLists(Long id);

    List<Member> findAllOptimized();

    List<Member> findAll();

    List<Member> findByCriteria(MemberCriteria criteria);

    List<Member> findPaginatedByCriteria(MemberCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MemberCriteria criteria);

    List<Member> delete(List<Member> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Member>> getToBeSavedAndToBeDeleted(List<Member> oldList, List<Member> newList);

    List<Member> importData(List<Member> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Member> importExcel(MultipartFile file);

}
