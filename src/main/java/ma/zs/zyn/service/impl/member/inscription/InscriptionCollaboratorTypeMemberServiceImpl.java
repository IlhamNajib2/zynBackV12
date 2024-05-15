package ma.zs.zyn.service.impl.member.inscription;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorType;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorTypeCriteria;
import ma.zs.zyn.dao.facade.core.inscription.InscriptionCollaboratorTypeDao;
import ma.zs.zyn.dao.specification.core.inscription.InscriptionCollaboratorTypeSpecification;
import ma.zs.zyn.service.facade.member.inscription.InscriptionCollaboratorTypeMemberService;
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
public class InscriptionCollaboratorTypeMemberServiceImpl implements InscriptionCollaboratorTypeMemberService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaboratorType update(InscriptionCollaboratorType t) {
        InscriptionCollaboratorType loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{InscriptionCollaboratorType.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public InscriptionCollaboratorType findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public InscriptionCollaboratorType findOrSave(InscriptionCollaboratorType t) {
        if (t != null) {
            InscriptionCollaboratorType result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<InscriptionCollaboratorType> importData(List<InscriptionCollaboratorType> items) {
        List<InscriptionCollaboratorType> list = new ArrayList<>();
        for (InscriptionCollaboratorType t : items) {
            InscriptionCollaboratorType founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<InscriptionCollaboratorType> findAll() {
        return dao.findAll();
    }

    public List<InscriptionCollaboratorType> findByCriteria(InscriptionCollaboratorTypeCriteria criteria) {
        List<InscriptionCollaboratorType> content = null;
        if (criteria != null) {
            InscriptionCollaboratorTypeSpecification mySpecification = constructSpecification(criteria);
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


    private InscriptionCollaboratorTypeSpecification constructSpecification(InscriptionCollaboratorTypeCriteria criteria) {
        InscriptionCollaboratorTypeSpecification mySpecification =  (InscriptionCollaboratorTypeSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionCollaboratorTypeSpecification.class, criteria);
        return mySpecification;
    }

    public List<InscriptionCollaboratorType> findPaginatedByCriteria(InscriptionCollaboratorTypeCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionCollaboratorTypeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionCollaboratorTypeCriteria criteria) {
        InscriptionCollaboratorTypeSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(InscriptionCollaboratorType t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaboratorType> delete(List<InscriptionCollaboratorType> list) {
		List<InscriptionCollaboratorType> result = new ArrayList();
        if (list != null) {
            for (InscriptionCollaboratorType t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public InscriptionCollaboratorType create(InscriptionCollaboratorType t) {
        InscriptionCollaboratorType loaded = findByReferenceEntity(t);
        InscriptionCollaboratorType saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaboratorType> create(List<InscriptionCollaboratorType> ts) {
        List<InscriptionCollaboratorType> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaboratorType t : ts) {
				InscriptionCollaboratorType created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public InscriptionCollaboratorType findWithAssociatedLists(Long id){
        InscriptionCollaboratorType result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<InscriptionCollaboratorType> update(List<InscriptionCollaboratorType> ts, boolean createIfNotExist) {
        List<InscriptionCollaboratorType> result = new ArrayList<>();
        if (ts != null) {
            for (InscriptionCollaboratorType t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    InscriptionCollaboratorType loadedItem = dao.findById(t.getId()).orElse(null);
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





    public InscriptionCollaboratorType findByReferenceEntity(InscriptionCollaboratorType t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<InscriptionCollaboratorType> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<InscriptionCollaboratorType>> getToBeSavedAndToBeDeleted(List<InscriptionCollaboratorType> oldList, List<InscriptionCollaboratorType> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<InscriptionCollaboratorType> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired InscriptionCollaboratorTypeDao dao;


}
