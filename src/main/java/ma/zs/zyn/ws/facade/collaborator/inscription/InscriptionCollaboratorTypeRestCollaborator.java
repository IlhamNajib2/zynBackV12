package  ma.zs.zyn.ws.facade.collaborator.inscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.inscription.InscriptionCollaboratorType;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionCollaboratorTypeCriteria;
import ma.zs.zyn.service.facade.collaborator.inscription.InscriptionCollaboratorTypeCollaboratorService;
import ma.zs.zyn.ws.converter.inscription.InscriptionCollaboratorTypeConverter;
import ma.zs.zyn.ws.dto.inscription.InscriptionCollaboratorTypeDto;
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
@RequestMapping("/api/collaborator/inscriptionCollaboratorType/")
public class InscriptionCollaboratorTypeRestCollaborator {




    @Operation(summary = "Finds a list of all inscriptionCollaboratorTypes")
    @GetMapping("")
    public ResponseEntity<List<InscriptionCollaboratorTypeDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionCollaboratorTypeDto>> res = null;
        List<InscriptionCollaboratorType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all inscriptionCollaboratorTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<InscriptionCollaboratorTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<InscriptionCollaboratorTypeDto>> res = null;
        List<InscriptionCollaboratorType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a inscriptionCollaboratorType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionCollaboratorTypeDto> findById(@PathVariable Long id) {
        InscriptionCollaboratorType t = service.findById(id);
        if (t != null) {
            InscriptionCollaboratorTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a inscriptionCollaboratorType by name")
    @GetMapping("name/{name}")
    public ResponseEntity<InscriptionCollaboratorTypeDto> findByName(@PathVariable String name) {
	    InscriptionCollaboratorType t = service.findByReferenceEntity(new InscriptionCollaboratorType(name));
        if (t != null) {
            InscriptionCollaboratorTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  inscriptionCollaboratorType")
    @PostMapping("")
    public ResponseEntity<InscriptionCollaboratorTypeDto> save(@RequestBody InscriptionCollaboratorTypeDto dto) throws Exception {
        if(dto!=null){
            InscriptionCollaboratorType myT = converter.toItem(dto);
            InscriptionCollaboratorType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionCollaboratorTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscriptionCollaboratorType")
    @PutMapping("")
    public ResponseEntity<InscriptionCollaboratorTypeDto> update(@RequestBody InscriptionCollaboratorTypeDto dto) throws Exception {
        ResponseEntity<InscriptionCollaboratorTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            InscriptionCollaboratorType t = service.findById(dto.getId());
            converter.copy(dto,t);
            InscriptionCollaboratorType updated = service.update(t);
            InscriptionCollaboratorTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscriptionCollaboratorType")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionCollaboratorTypeDto>> delete(@RequestBody List<InscriptionCollaboratorTypeDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<InscriptionCollaboratorType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified inscriptionCollaboratorType")
    @DeleteMapping("")
    public ResponseEntity<InscriptionCollaboratorTypeDto> delete(@RequestBody InscriptionCollaboratorTypeDto dto) throws Exception {
		ResponseEntity<InscriptionCollaboratorTypeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            InscriptionCollaboratorType t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscriptionCollaboratorType")
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
    @Operation(summary = "Delete multiple inscriptionCollaboratorTypes by ids")
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



    @Operation(summary = "Finds a inscriptionCollaboratorType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionCollaboratorTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        InscriptionCollaboratorType loaded =  service.findWithAssociatedLists(id);
        InscriptionCollaboratorTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptionCollaboratorTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionCollaboratorTypeDto>> findByCriteria(@RequestBody InscriptionCollaboratorTypeCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionCollaboratorTypeDto>> res = null;
        List<InscriptionCollaboratorType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionCollaboratorTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptionCollaboratorTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionCollaboratorTypeCriteria criteria) throws Exception {
        List<InscriptionCollaboratorType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<InscriptionCollaboratorTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscriptionCollaboratorType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionCollaboratorTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionCollaboratorTypeDto> findDtos(List<InscriptionCollaboratorType> list){
        List<InscriptionCollaboratorTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionCollaboratorTypeDto> getDtoResponseEntity(InscriptionCollaboratorTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private InscriptionCollaboratorTypeCollaboratorService service;
    @Autowired private InscriptionCollaboratorTypeConverter converter;





}
