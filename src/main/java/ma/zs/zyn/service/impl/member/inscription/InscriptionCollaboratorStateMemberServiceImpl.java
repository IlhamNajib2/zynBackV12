package ma.zs.zyn.service.impl.member.inscription;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorStateCriteria;
import ma.zs.zyn.dao.facade.core.inscription.InscriptionCollaboratorStateDao;
import ma.zs.zyn.dao.specification.core.inscription.InscriptionCollaboratorStateSpecification;
import ma.zs.zyn.service.facade.member.inscription.InscriptionCollaboratorStateMemberService;
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


import java.util.List;
@Service
public class InscriptionCollaboratorStateMemberServiceImpl implements InscriptionCollaboratorStateMemberService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaboratorState update(InscriptionCollaboratorState t) {
        InscriptionCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InscriptionCollaboratorState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public InscriptionCollaboratorState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public InscriptionCollaboratorState findOrSave(InscriptionCollaboratorState t) {
        if (t != null) {
            InscriptionCollaboratorState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<InscriptionCollaboratorState> importData(List<InscriptionCollaboratorState> items) {
        List<InscriptionCollaboratorState> list = new ArrayList<>();
        for (InscriptionCollaboratorState t : items) {
            InscriptionCollaboratorState founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<InscriptionCollaboratorState> findAll() {
        return dao.findAll();
    }

    public List<InscriptionCollaboratorState> findByCriteria(InscriptionCollaboratorStateCriteria criteria) {
        List<InscriptionCollaboratorState> content = null;
        if (criteria != null) {
            InscriptionCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
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


    private InscriptionCollaboratorStateSpecification constructSpecification(InscriptionCollaboratorStateCriteria criteria) {
        InscriptionCollaboratorStateSpecification mySpecification =  (InscriptionCollaboratorStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionCollaboratorStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<InscriptionCollaboratorState> findPaginatedByCriteria(InscriptionCollaboratorStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionCollaboratorStateCriteria criteria) {
        InscriptionCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(InscriptionCollaboratorState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaboratorState> delete(List<InscriptionCollaboratorState> list) {
		List<InscriptionCollaboratorState> result = new ArrayList();
        if (list != null) {
            for (InscriptionCollaboratorState t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaboratorState create(InscriptionCollaboratorState t) {
        InscriptionCollaboratorState loaded = findByReferenceEntity(t);
        InscriptionCollaboratorState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaboratorState> create(List<InscriptionCollaboratorState> ts) {
        List<InscriptionCollaboratorState> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaboratorState t : ts) {
				InscriptionCollaboratorState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public InscriptionCollaboratorState findWithAssociatedLists(Long id){
        InscriptionCollaboratorState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaboratorState> update(List<InscriptionCollaboratorState> ts, boolean createIfNotExist) {
        List<InscriptionCollaboratorState> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaboratorState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InscriptionCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
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





    public InscriptionCollaboratorState findByReferenceEntity(InscriptionCollaboratorState t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<InscriptionCollaboratorState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<InscriptionCollaboratorState>> getToBeSavedAndToBeDeleted(List<InscriptionCollaboratorState> oldList, List<InscriptionCollaboratorState> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<InscriptionCollaboratorState> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired InscriptionCollaboratorStateDao dao;


}
