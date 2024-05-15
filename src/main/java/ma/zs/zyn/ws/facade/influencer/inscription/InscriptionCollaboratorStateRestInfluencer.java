package  ma.zs.zyn.ws.facade.influencer.inscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorState;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorStateCriteria;
import ma.zs.zyn.service.facade.influencer.inscription.InscriptionCollaboratorStateInfluencerService;
import ma.zs.zyn.ws.converter.inscription.InscriptionCollaboratorStateConverter;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorStateDto;
import ma.zs.zyn.zynerator.controller.AbstractController;
import ma.zs.zyn.zynerator.dto.AuditEntityDto;
import ma.zs.zyn.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.zyn.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.zyn.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/influencer/inscriptionCollaboratorState/")
public class InscriptionCollaboratorStateRestInfluencer {




    @Operation(summary = "Finds a list of all inscriptionCollaboratorStates")
    @GetMapping("")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res = null;
        List<InscriptionCollaboratorState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all inscriptionCollaboratorStates")
    @GetMapping("optimized")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res = null;
        List<InscriptionCollaboratorState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a inscriptionCollaboratorState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionCollaboratorStateDto> findById(@PathVariable Long id) {
        InscriptionCollaboratorState t = service.findById(id);
        if (t != null) {
            InscriptionCollaboratorStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a inscriptionCollaboratorState by name")
    @GetMapping("name/{name}")
    public ResponseEntity<InscriptionCollaboratorStateDto> findByName(@PathVariable String name) {
	    InscriptionCollaboratorState t = service.findByReferenceEntity(new InscriptionCollaboratorState(name));
        if (t != null) {
            InscriptionCollaboratorStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  inscriptionCollaboratorState")
    @PostMapping("")
    public ResponseEntity<InscriptionCollaboratorStateDto> save(@RequestBody InscriptionCollaboratorStateDto dto) throws Exception {
        if(dto!=null){
            InscriptionCollaboratorState myT = converter.toItem(dto);
            InscriptionCollaboratorState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionCollaboratorStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscriptionCollaboratorState")
    @PutMapping("")
    public ResponseEntity<InscriptionCollaboratorStateDto> update(@RequestBody InscriptionCollaboratorStateDto dto) throws Exception {
        ResponseEntity<InscriptionCollaboratorStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            InscriptionCollaboratorState t = service.findById(dto.getId());
            converter.copy(dto,t);
            InscriptionCollaboratorState updated = service.update(t);
            InscriptionCollaboratorStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscriptionCollaboratorState")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> delete(@RequestBody List<InscriptionCollaboratorStateDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<InscriptionCollaboratorState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified inscriptionCollaboratorState")
    @DeleteMapping("")
    public ResponseEntity<InscriptionCollaboratorStateDto> delete(@RequestBody InscriptionCollaboratorStateDto dto) throws Exception {
		ResponseEntity<InscriptionCollaboratorStateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            InscriptionCollaboratorState t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscriptionCollaboratorState")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple inscriptionCollaboratorStates by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }



    @Operation(summary = "Finds a inscriptionCollaboratorState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionCollaboratorStateDto> findWithAssociatedLists(@PathVariable Long id) {
        InscriptionCollaboratorState loaded =  service.findWithAssociatedLists(id);
        InscriptionCollaboratorStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptionCollaboratorStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionCollaboratorStateDto>> findByCriteria(@RequestBody InscriptionCollaboratorStateCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorStateDto>> res = null;
        List<InscriptionCollaboratorState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptionCollaboratorStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionCollaboratorStateCriteria criteria) throws Exception {
        List<InscriptionCollaboratorState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<InscriptionCollaboratorStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscriptionCollaboratorState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionCollaboratorStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionCollaboratorStateDto> findDtos(List<InscriptionCollaboratorState> list){
        List<InscriptionCollaboratorStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionCollaboratorStateDto> getDtoResponseEntity(InscriptionCollaboratorStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private InscriptionCollaboratorStateInfluencerService service;
    @Autowired private InscriptionCollaboratorStateConverter converter;





}
