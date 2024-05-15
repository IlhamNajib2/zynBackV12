package ma.zs.zyn.service.impl.member.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.PaimentCollaboratorState;
import ma.zs.zyn.dao.criteria.core.project.PaimentCollaboratorStateCriteria;
import ma.zs.zyn.dao.facade.core.project.PaimentCollaboratorStateDao;
import ma.zs.zyn.dao.specification.core.project.PaimentCollaboratorStateSpecification;
import ma.zs.zyn.service.facade.member.project.PaimentCollaboratorStateMemberService;
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
public class PaimentCollaboratorStateMemberServiceImpl implements PaimentCollaboratorStateMemberService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaboratorState update(PaimentCollaboratorState t) {
        PaimentCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCollaboratorState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCollaboratorState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCollaboratorState findOrSave(PaimentCollaboratorState t) {
        if (t != null) {
            PaimentCollaboratorState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<PaimentCollaboratorState> importData(List<PaimentCollaboratorState> items) {
        List<PaimentCollaboratorState> list = new ArrayList<>();
        for (PaimentCollaboratorState t : items) {
            PaimentCollaboratorState founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<PaimentCollaboratorState> findAll() {
        return dao.findAll();
    }

    public List<PaimentCollaboratorState> findByCriteria(PaimentCollaboratorStateCriteria criteria) {
        List<PaimentCollaboratorState> content = null;
        if (criteria != null) {
            PaimentCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentCollaboratorStateSpecification constructSpecification(PaimentCollaboratorStateCriteria criteria) {
        PaimentCollaboratorStateSpecification mySpecification =  (PaimentCollaboratorStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCollaboratorStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCollaboratorState> findPaginatedByCriteria(PaimentCollaboratorStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCollaboratorStateCriteria criteria) {
        PaimentCollaboratorStateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(PaimentCollaboratorState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaboratorState> delete(List<PaimentCollaboratorState> list) {
		List<PaimentCollaboratorState> result = new ArrayList();
        if (list != null) {
            for (PaimentCollaboratorState t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaboratorState create(PaimentCollaboratorState t) {
        PaimentCollaboratorState loaded = findByReferenceEntity(t);
        PaimentCollaboratorState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaboratorState> create(List<PaimentCollaboratorState> ts) {
        List<PaimentCollaboratorState> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaboratorState t : ts) {
				PaimentCollaboratorState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public PaimentCollaboratorState findWithAssociatedLists(Long id){
        PaimentCollaboratorState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaboratorState> update(List<PaimentCollaboratorState> ts, boolean createIfNotExist) {
        List<PaimentCollaboratorState> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaboratorState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCollaboratorState loadedItem = dao.findById(t.getId()).orElse(null);
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





    public PaimentCollaboratorState findByReferenceEntity(PaimentCollaboratorState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<PaimentCollaboratorState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentCollaboratorState>> getToBeSavedAndToBeDeleted(List<PaimentCollaboratorState> oldList, List<PaimentCollaboratorState> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<PaimentCollaboratorState> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired PaimentCollaboratorStateDao dao;


}
