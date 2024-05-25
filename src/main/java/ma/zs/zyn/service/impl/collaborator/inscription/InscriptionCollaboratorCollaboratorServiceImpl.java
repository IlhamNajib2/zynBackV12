package ma.zs.zyn.service.impl.collaborator.inscription;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaborator;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorCriteria;
import ma.zs.zyn.dao.facade.core.inscription.InscriptionCollaboratorDao;
import ma.zs.zyn.dao.specification.core.inscription.InscriptionCollaboratorSpecification;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorCollaboratorService;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import ma.zs.zyn.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

import ma.zs.zyn.service.facade.collaborator.collaborator.CollaboratorCollaboratorService ;
import ma.zs.zyn.bean.core.collaborator.Collaborator ;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionMembreCollaboratorService ;
import ma.zs.zyn.bean.core.inscription.InscriptionMembre ;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorTypeCollaboratorService ;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorType ;
import ma.zs.zyn.service.facade.collaborator.packaging.PackagingCollaboratorService ;
import ma.zs.zyn.bean.core.packaging.Packaging ;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorStateCollaboratorService ;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState ;

import java.util.List;
@Service
public class InscriptionCollaboratorCollaboratorServiceImpl implements InscriptionCollaboratorCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaborator update(InscriptionCollaborator t) {
        InscriptionCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InscriptionCollaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public InscriptionCollaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public InscriptionCollaborator findOrSave(InscriptionCollaborator t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            InscriptionCollaborator result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<InscriptionCollaborator> importData(List<InscriptionCollaborator> items) {
        List<InscriptionCollaborator> list = new ArrayList<>();
        for (InscriptionCollaborator t : items) {
            InscriptionCollaborator founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<InscriptionCollaborator> findAll() {
        return dao.findAll();
    }

    public List<InscriptionCollaborator> findByCriteria(InscriptionCollaboratorCriteria criteria) {
        List<InscriptionCollaborator> content = null;
        if (criteria != null) {
            InscriptionCollaboratorSpecification mySpecification = constructSpecification(criteria);
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


    private InscriptionCollaboratorSpecification constructSpecification(InscriptionCollaboratorCriteria criteria) {
        InscriptionCollaboratorSpecification mySpecification =  (InscriptionCollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionCollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<InscriptionCollaborator> findPaginatedByCriteria(InscriptionCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionCollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionCollaboratorCriteria criteria) {
        InscriptionCollaboratorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<InscriptionCollaborator> findByPackagingId(Long id){
        return dao.findByPackagingId(id);
    }
    public int deleteByPackagingId(Long id){
        return dao.deleteByPackagingId(id);
    }
    public long countByPackagingId(Long id){
        return dao.countByPackagingId(id);
    }
    public List<InscriptionCollaborator> findByCollaboratorId(Long id){
        return dao.findByCollaboratorId(id);
    }
    public int deleteByCollaboratorId(Long id){
        return dao.deleteByCollaboratorId(id);
    }
    public long countByCollaboratorId(Long id){
        return dao.countByCollaboratorId(id);
    }
    public List<InscriptionCollaborator> findByInscriptionCollaboratorStateId(Long id){
        return dao.findByInscriptionCollaboratorStateId(id);
    }
    public int deleteByInscriptionCollaboratorStateId(Long id){
        return dao.deleteByInscriptionCollaboratorStateId(id);
    }
    public long countByInscriptionCollaboratorStateId(Long id){
        return dao.countByInscriptionCollaboratorStateId(id);
    }
    public List<InscriptionCollaborator> findByInscriptionCollaboratorTypeId(Long id){
        return dao.findByInscriptionCollaboratorTypeId(id);
    }
    public int deleteByInscriptionCollaboratorTypeId(Long id){
        return dao.deleteByInscriptionCollaboratorTypeId(id);
    }
    public long countByInscriptionCollaboratorTypeId(Long id){
        return dao.countByInscriptionCollaboratorTypeId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
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
    public int delete(InscriptionCollaborator t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        inscriptionMembreService.deleteByInscriptionCollaboratorId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaborator> delete(List<InscriptionCollaborator> list) {
		List<InscriptionCollaborator> result = new ArrayList();
        if (list != null) {
            for (InscriptionCollaborator t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }
/*
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaborator create(InscriptionCollaborator t) {
        InscriptionCollaborator loaded = findByReferenceEntity(t);
        InscriptionCollaborator saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getInscriptionMembres() != null) {
                t.getInscriptionMembres().forEach(element-> {
                    element.setInscriptionCollaborator(saved);
                    inscriptionMembreService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }


 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public InscriptionCollaborator create(InscriptionCollaborator t) {
    t.setStartDate(LocalDateTime.now());
    t.setEndDate(LocalDateTime.now().plus(1, ChronoUnit.MONTHS));
    t.setRenewDate(null);
    InscriptionCollaboratorState inscriptionCollaboratorState =inscriptionCollaboratorStateService.findByCode("c3");
    t.setInscriptionCollaboratorState(inscriptionCollaboratorState);

    InscriptionCollaboratorType inscriptionCollaboratorType = inscriptionCollaboratorTypeService.findByName(t.getInscriptionCollaboratorType().getName());
    t.setInscriptionCollaboratorType(inscriptionCollaboratorType);
    InscriptionCollaborator saved= dao.save(t);
    if (t.getInscriptionMembres() != null) {
        t.getInscriptionMembres().forEach(element-> {
            element.setInscriptionCollaborator(saved);
            inscriptionMembreService.create(element);
        });
    }
    return saved;

}



	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaborator> create(List<InscriptionCollaborator> ts) {
        List<InscriptionCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaborator t : ts) {
				InscriptionCollaborator created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public InscriptionCollaborator findWithAssociatedLists(Long id){
        InscriptionCollaborator result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setInscriptionMembres(inscriptionMembreService.findByInscriptionCollaboratorId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaborator> update(List<InscriptionCollaborator> ts, boolean createIfNotExist) {
        List<InscriptionCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InscriptionCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(InscriptionCollaborator inscriptionCollaborator){
    if(inscriptionCollaborator !=null && inscriptionCollaborator.getId() != null){
        List<List<InscriptionMembre>> resultInscriptionMembres= inscriptionMembreService.getToBeSavedAndToBeDeleted(inscriptionMembreService.findByInscriptionCollaboratorId(inscriptionCollaborator.getId()),inscriptionCollaborator.getInscriptionMembres());
            inscriptionMembreService.delete(resultInscriptionMembres.get(1));
        ListUtil.emptyIfNull(resultInscriptionMembres.get(0)).forEach(e -> e.setInscriptionCollaborator(inscriptionCollaborator));
        inscriptionMembreService.update(resultInscriptionMembres.get(0),true);
        }
    }




    public InscriptionCollaborator findByReferenceEntity(InscriptionCollaborator t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(InscriptionCollaborator t){
        if( t != null) {
            t.setPackaging(packagingService.findOrSave(t.getPackaging()));
            t.setCollaborator(collaboratorService.findOrSave(t.getCollaborator()));
            t.setInscriptionCollaboratorState(inscriptionCollaboratorStateService.findOrSave(t.getInscriptionCollaboratorState()));
            t.setInscriptionCollaboratorType(inscriptionCollaboratorTypeService.findOrSave(t.getInscriptionCollaboratorType()));
        }
    }



    public List<InscriptionCollaborator> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<InscriptionCollaborator>> getToBeSavedAndToBeDeleted(List<InscriptionCollaborator> oldList, List<InscriptionCollaborator> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<InscriptionCollaborator> importExcel(MultipartFile file) {
        return null;
    }


/*
   //ajouter
    @Override
    public InscriptionCollaborator update(InscriptionCollaborator inscriptionCollaborator) {
        if (!(inscriptionCollaborator.getConsumedEntity().equals(0)) && inscriptionCollaborator.getMaxEntity().compareTo(inscriptionCollaborator.getConsumedEntity()) > 0 && inscriptionCollaborator.getEndDate().isBefore(LocalDateTime.now())) {
            InscriptionCollaboratorState inscriptionCollaboratorState = inscriptionCollaboratorStateService.findByCode("etat1");
            inscriptionCollaborator.setInscriptionCollaboratorState(inscriptionCollaboratorState);
            BigDecimal restEntity = inscriptionCollaborator.getMaxEntity().subtract(inscriptionCollaborator.getConsumedEntity());
            inscriptionCollaborator.setMaxEntity(restEntity);
        } else if (inscriptionCollaborator.getConsumedEntity().compareTo(inscriptionCollaborator.getMaxEntity()) == 0 && inscriptionCollaborator.getEndDate().isAfter(LocalDateTime.now())) {
            inscriptionCollaborator.getCollaborator().setEnabled(false);
            inscriptionCollaborator.getCollaborator().setAccountNonExpired(false);
            paimentCollaboratorService.makePaiment(inscriptionCollaborator);
            inscriptionCollaborator.getCollaborator().setEnabled(true);
            inscriptionCollaborator.getCollaborator().setAccountNonExpired(true);
            inscriptionCollaborator.setMaxEntity(inscriptionCollaborator.getPackaging().getMaxEntity());
        }else if (inscriptionCollaborator.getCollaborator().getEnabled() == false && inscriptionCollaborator.getCollaborator().getAccountNonExpired() == false && inscriptionCollaborator.getEndDate().isAfter(LocalDateTime.now())) {
            paimentCollaboratorService.makePaiment(inscriptionCollaborator);
            inscriptionCollaborator.getCollaborator().setEnabled(true);
            inscriptionCollaborator.getCollaborator().setAccountNonExpired(true);
            InscriptionCollaboratorState inscriptionCollaboratorState = inscriptionCollaboratorStateService.findByCode("etat1");
            inscriptionCollaborator.setInscriptionCollaboratorState(inscriptionCollaboratorState);
        }else {
            InscriptionCollaboratorState inscriptionCollaboratorState = inscriptionCollaboratorStateService.findByCode("etat2");
            inscriptionCollaborator.setInscriptionCollaboratorState(inscriptionCollaboratorState);
        }
        InscriptionCollaborator updateed =super.update(inscriptionCollaborator);
        return updateed;
    }
 */

    /*
    @Scheduled(cron = "0 0 0 * * *")
    public void verification() {
        LocalDate currentDate = LocalDate.now();
        List<InscriptionCollaborator> inscriptionCollaborators = dao.findAll();

        for (InscriptionCollaborator : inscriptionCollaborators) {
            LocalDate endDate = inscriptionCollaborator.getEndDate();
            if (endDate != null && endDate.isEqual(currentDate)) {
                inscriptionCollaborator.getCollaborator().setAccountNonExpired(false);
                inscriptionCollaborator.getCollaborator().setEnabled(false);
              // memberService . findByCollaboratorUsername String username
               List<Member> members = memberService.findByCollaboratorUsername( inscriptionCollaborator.getCollaborator().getUsername)
               for member in members {
                    member.setAccountNonExpired(false);
                    member.setEnabled(false);
                    memberService.update member
                    }
                dao.save(inscriptionCollaborator); // Assuming CollaboratorRepository has a save method to update collaborator
            }
        }
    }*/




    @Autowired
    private CollaboratorCollaboratorService collaboratorService ;
    @Autowired
    private InscriptionMembreCollaboratorService inscriptionMembreService ;
    @Autowired
    private InscriptionCollaboratorTypeCollaboratorService inscriptionCollaboratorTypeService ;
    @Autowired
    private PackagingCollaboratorService packagingService ;
    @Autowired
    private InscriptionCollaboratorStateCollaboratorService inscriptionCollaboratorStateService ;

    private @Autowired InscriptionCollaboratorDao dao;


}
