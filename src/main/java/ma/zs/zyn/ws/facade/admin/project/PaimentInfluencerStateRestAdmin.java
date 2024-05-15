package  ma.zs.zyn.ws.facade.admin.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.project.PaimentInfluencerState;
import ma.zs.zyn.dao.criteria.core.project.PaimentInfluencerStateCriteria;
import ma.zs.zyn.service.facade.admin.project.PaimentInfluencerStateAdminService;
import ma.zs.zyn.ws.converter.project.PaimentInfluencerStateConverter;
import ma.zs.zyn.ws.dto.project.PaimentInfluencerStateDto;
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
@RequestMapping("/api/admin/paimentInfluencerState/")
public class PaimentInfluencerStateRestAdmin {




    @Operation(summary = "Finds a list of all paimentInfluencerStates")
    @GetMapping("")
    public ResponseEntity<List<PaimentInfluencerStateDto>> findAll() throws Exception {
        ResponseEntity<List<PaimentInfluencerStateDto>> res = null;
        List<PaimentInfluencerState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentInfluencerStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paimentInfluencerStates")
    @GetMapping("optimized")
    public ResponseEntity<List<PaimentInfluencerStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaimentInfluencerStateDto>> res = null;
        List<PaimentInfluencerState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentInfluencerStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paimentInfluencerState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaimentInfluencerStateDto> findById(@PathVariable Long id) {
        PaimentInfluencerState t = service.findById(id);
        if (t != null) {
            PaimentInfluencerStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paimentInfluencerState by code")
    @GetMapping("code/{code}")
    public ResponseEntity<PaimentInfluencerStateDto> findByCode(@PathVariable String code) {
	    PaimentInfluencerState t = service.findByReferenceEntity(new PaimentInfluencerState(code));
        if (t != null) {
            PaimentInfluencerStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paimentInfluencerState")
    @PostMapping("")
    public ResponseEntity<PaimentInfluencerStateDto> save(@RequestBody PaimentInfluencerStateDto dto) throws Exception {
        if(dto!=null){
            PaimentInfluencerState myT = converter.toItem(dto);
            PaimentInfluencerState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaimentInfluencerStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paimentInfluencerState")
    @PutMapping("")
    public ResponseEntity<PaimentInfluencerStateDto> update(@RequestBody PaimentInfluencerStateDto dto) throws Exception {
        ResponseEntity<PaimentInfluencerStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaimentInfluencerState t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaimentInfluencerState updated = service.update(t);
            PaimentInfluencerStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paimentInfluencerState")
    @PostMapping("multiple")
    public ResponseEntity<List<PaimentInfluencerStateDto>> delete(@RequestBody List<PaimentInfluencerStateDto> dtos) throws Exception {
        ResponseEntity<List<PaimentInfluencerStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<PaimentInfluencerState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified paimentInfluencerState")
    @DeleteMapping("")
    public ResponseEntity<PaimentInfluencerStateDto> delete(@RequestBody PaimentInfluencerStateDto dto) throws Exception {
		ResponseEntity<PaimentInfluencerStateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            PaimentInfluencerState t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified paimentInfluencerState")
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
    @Operation(summary = "Delete multiple paimentInfluencerStates by ids")
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



    @Operation(summary = "Finds a paimentInfluencerState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaimentInfluencerStateDto> findWithAssociatedLists(@PathVariable Long id) {
        PaimentInfluencerState loaded =  service.findWithAssociatedLists(id);
        PaimentInfluencerStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paimentInfluencerStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaimentInfluencerStateDto>> findByCriteria(@RequestBody PaimentInfluencerStateCriteria criteria) throws Exception {
        ResponseEntity<List<PaimentInfluencerStateDto>> res = null;
        List<PaimentInfluencerState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaimentInfluencerStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paimentInfluencerStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaimentInfluencerStateCriteria criteria) throws Exception {
        List<PaimentInfluencerState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PaimentInfluencerStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paimentInfluencerState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaimentInfluencerStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaimentInfluencerStateDto> findDtos(List<PaimentInfluencerState> list){
        List<PaimentInfluencerStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaimentInfluencerStateDto> getDtoResponseEntity(PaimentInfluencerStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PaimentInfluencerStateAdminService service;
    @Autowired private PaimentInfluencerStateConverter converter;





}
