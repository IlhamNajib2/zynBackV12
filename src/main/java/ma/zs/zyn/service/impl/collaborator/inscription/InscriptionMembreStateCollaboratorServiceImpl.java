package ma.zs.zyn.service.impl.collaborator.inscription;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.inscription.InscriptionMembreState;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreStateCriteria;
import ma.zs.zyn.dao.facade.core.inscription.InscriptionMembreStateDao;
import ma.zs.zyn.dao.specification.core.inscription.InscriptionMembreStateSpecification;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionMembreStateCollaboratorService;
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
public class InscriptionMembreStateCollaboratorServiceImpl implements InscriptionMembreStateCollaboratorService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionMembreState update(InscriptionMembreState t) {
        InscriptionMembreState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InscriptionMembreState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public InscriptionMembreState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public InscriptionMembreState findOrSave(InscriptionMembreState t) {
        if (t != null) {
            InscriptionMembreState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<InscriptionMembreState> importData(List<InscriptionMembreState> items) {
        List<InscriptionMembreState> list = new ArrayList<>();
        for (InscriptionMembreState t : items) {
            InscriptionMembreState founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<InscriptionMembreState> findAll() {
        return dao.findAll();
    }

    public List<InscriptionMembreState> findByCriteria(InscriptionMembreStateCriteria criteria) {
        List<InscriptionMembreState> content = null;
        if (criteria != null) {
            InscriptionMembreStateSpecification mySpecification = constructSpecification(criteria);
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


    private InscriptionMembreStateSpecification constructSpecification(InscriptionMembreStateCriteria criteria) {
        InscriptionMembreStateSpecification mySpecification =  (InscriptionMembreStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionMembreStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<InscriptionMembreState> findPaginatedByCriteria(InscriptionMembreStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionMembreStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionMembreStateCriteria criteria) {
        InscriptionMembreStateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(InscriptionMembreState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionMembreState> delete(List<InscriptionMembreState> list) {
		List<InscriptionMembreState> result = new ArrayList();
        if (list != null) {
            for (InscriptionMembreState t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionMembreState create(InscriptionMembreState t) {
        InscriptionMembreState loaded = findByReferenceEntity(t);
        InscriptionMembreState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionMembreState> create(List<InscriptionMembreState> ts) {
        List<InscriptionMembreState> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionMembreState t : ts) {
				InscriptionMembreState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public InscriptionMembreState findWithAssociatedLists(Long id){
        InscriptionMembreState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionMembreState> update(List<InscriptionMembreState> ts, boolean createIfNotExist) {
        List<InscriptionMembreState> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionMembreState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InscriptionMembreState loadedItem = dao.findById(t.getId()).orElse(null);
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





    public InscriptionMembreState findByReferenceEntity(InscriptionMembreState t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<InscriptionMembreState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<InscriptionMembreState>> getToBeSavedAndToBeDeleted(List<InscriptionMembreState> oldList, List<InscriptionMembreState> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<InscriptionMembreState> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired InscriptionMembreStateDao dao;


}
