package ma.zs.zyn.service.impl.member.inscription;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.inscription.InscriptionMembre;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreCriteria;
import ma.zs.zyn.dao.facade.core.inscription.InscriptionMembreDao;
import ma.zs.zyn.dao.specification.core.inscription.InscriptionMembreSpecification;
import ma.zs.zyn.service.facade.member.inscription.InscriptionMembreMemberService;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import ma.zs.zyn.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.service.facade.member.inscription.InscriptionMembreStateMemberService ;
import ma.zs.zyn.bean.core.inscription.InscriptionMembreState ;
import ma.zs.zyn.service.facade.member.inscription.InscriptionCollaboratorMemberService ;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator ;
import ma.zs.zyn.service.facade.member.collaborator.MemberMemberService ;
import ma.zs.zyn.bean.core.collaborator.Member ;

import java.util.List;
@Service
public class InscriptionMembreMemberServiceImpl implements InscriptionMembreMemberService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionMembre update(InscriptionMembre t) {
        InscriptionMembre loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InscriptionMembre.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public InscriptionMembre findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public InscriptionMembre findOrSave(InscriptionMembre t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            InscriptionMembre result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<InscriptionMembre> importData(List<InscriptionMembre> items) {
        List<InscriptionMembre> list = new ArrayList<>();
        for (InscriptionMembre t : items) {
            InscriptionMembre founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<InscriptionMembre> findAll() {
        return dao.findAll();
    }

    public List<InscriptionMembre> findByCriteria(InscriptionMembreCriteria criteria) {
        List<InscriptionMembre> content = null;
        if (criteria != null) {
            InscriptionMembreSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private InscriptionMembreSpecification constructSpecification(InscriptionMembreCriteria criteria) {
        InscriptionMembreSpecification mySpecification =  (InscriptionMembreSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionMembreSpecification.class, criteria);
        return mySpecification;
    }

    public List<InscriptionMembre> findPaginatedByCriteria(InscriptionMembreCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionMembreSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionMembreCriteria criteria) {
        InscriptionMembreSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<InscriptionMembre> findByMemberId(Long id){
        return dao.findByMemberId(id);
    }
    public int deleteByMemberId(Long id){
        return dao.deleteByMemberId(id);
    }
    public long countByMemberId(Long id){
        return dao.countByMemberId(id);
    }
    public List<InscriptionMembre> findByInscriptionMembreStateId(Long id){
        return dao.findByInscriptionMembreStateId(id);
    }
    public int deleteByInscriptionMembreStateId(Long id){
        return dao.deleteByInscriptionMembreStateId(id);
    }
    public long countByInscriptionMembreStateId(Long id){
        return dao.countByInscriptionMembreStateId(id);
    }
    public List<InscriptionMembre> findByInscriptionCollaboratorId(Long id){
        return dao.findByInscriptionCollaboratorId(id);
    }
    public int deleteByInscriptionCollaboratorId(Long id){
        return dao.deleteByInscriptionCollaboratorId(id);
    }
    public long countByInscriptionCollaboratorId(Long id){
        return dao.countByInscriptionCollaboratorId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(InscriptionMembre t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionMembre> delete(List<InscriptionMembre> list) {
		List<InscriptionMembre> result = new ArrayList();
        if (list != null) {
            for (InscriptionMembre t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionMembre create(InscriptionMembre t) {
        InscriptionMembre loaded = findByReferenceEntity(t);
        InscriptionMembre saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionMembre> create(List<InscriptionMembre> ts) {
        List<InscriptionMembre> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionMembre t : ts) {
				InscriptionMembre created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public InscriptionMembre findWithAssociatedLists(Long id){
        InscriptionMembre result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionMembre> update(List<InscriptionMembre> ts, boolean createIfNotExist) {
        List<InscriptionMembre> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionMembre t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InscriptionMembre loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }





    public InscriptionMembre findByReferenceEntity(InscriptionMembre t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(InscriptionMembre t){
        if( t != null) {
            t.setMember(memberService.findOrSave(t.getMember()));
            t.setInscriptionMembreState(inscriptionMembreStateService.findOrSave(t.getInscriptionMembreState()));
            t.setInscriptionCollaborator(inscriptionCollaboratorService.findOrSave(t.getInscriptionCollaborator()));
        }
    }



    public List<InscriptionMembre> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<InscriptionMembre>> getToBeSavedAndToBeDeleted(List<InscriptionMembre> oldList, List<InscriptionMembre> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<InscriptionMembre> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private InscriptionMembreStateMemberService inscriptionMembreStateService ;
    @Autowired
    private InscriptionCollaboratorMemberService inscriptionCollaboratorService ;
    @Autowired
    private MemberMemberService memberService ;

    private @Autowired InscriptionMembreDao dao;


}
