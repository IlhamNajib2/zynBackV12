package ma.zs.zyn.service.impl.influencer.project;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.project.PaimentInfluencerState;
import ma.zs.zyn.dao.criteria.core.project.PaimentInfluencerStateCriteria;
import ma.zs.zyn.dao.facade.core.project.PaimentInfluencerStateDao;
import ma.zs.zyn.dao.specification.core.project.PaimentInfluencerStateSpecification;
import ma.zs.zyn.service.facade.influencer.project.PaimentInfluencerStateInfluencerService;
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
public class PaimentInfluencerStateInfluencerServiceImpl implements PaimentInfluencerStateInfluencerService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentInfluencerState update(PaimentInfluencerState t) {
        PaimentInfluencerState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentInfluencerState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentInfluencerState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentInfluencerState findOrSave(PaimentInfluencerState t) {
        if (t != null) {
            PaimentInfluencerState result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<PaimentInfluencerState> importData(List<PaimentInfluencerState> items) {
        List<PaimentInfluencerState> list = new ArrayList<>();
        for (PaimentInfluencerState t : items) {
            PaimentInfluencerState founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<PaimentInfluencerState> findAll() {
        return dao.findAll();
    }

    public List<PaimentInfluencerState> findByCriteria(PaimentInfluencerStateCriteria criteria) {
        List<PaimentInfluencerState> content = null;
        if (criteria != null) {
            PaimentInfluencerStateSpecification mySpecification = constructSpecification(criteria);
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


    private PaimentInfluencerStateSpecification constructSpecification(PaimentInfluencerStateCriteria criteria) {
        PaimentInfluencerStateSpecification mySpecification =  (PaimentInfluencerStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentInfluencerStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentInfluencerState> findPaginatedByCriteria(PaimentInfluencerStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentInfluencerStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentInfluencerStateCriteria criteria) {
        PaimentInfluencerStateSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(PaimentInfluencerState t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentInfluencerState> delete(List<PaimentInfluencerState> list) {
		List<PaimentInfluencerState> result = new ArrayList();
        if (list != null) {
            for (PaimentInfluencerState t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentInfluencerState create(PaimentInfluencerState t) {
        PaimentInfluencerState loaded = findByReferenceEntity(t);
        PaimentInfluencerState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentInfluencerState> create(List<PaimentInfluencerState> ts) {
        List<PaimentInfluencerState> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentInfluencerState t : ts) {
				PaimentInfluencerState created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public PaimentInfluencerState findWithAssociatedLists(Long id){
        PaimentInfluencerState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentInfluencerState> update(List<PaimentInfluencerState> ts, boolean createIfNotExist) {
        List<PaimentInfluencerState> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentInfluencerState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentInfluencerState loadedItem = dao.findById(t.getId()).orElse(null);
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





    public PaimentInfluencerState findByReferenceEntity(PaimentInfluencerState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<PaimentInfluencerState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentInfluencerState>> getToBeSavedAndToBeDeleted(List<PaimentInfluencerState> oldList, List<PaimentInfluencerState> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<PaimentInfluencerState> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired PaimentInfluencerStateDao dao;


}
