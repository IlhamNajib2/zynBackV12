package  ma.zs.zyn.ws.facade.collaborator.paiment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.paiment.PaimentInfluencer;
import ma.zs.zyn.dao.criteria.core.paiment.PaimentInfluencerCriteria;
import ma.zs.zyn.service.facade.collaborator.paiment.PaimentInfluencerCollaboratorService;
import ma.zs.zyn.ws.converter.paiment.PaimentInfluencerConverter;
import ma.zs.zyn.ws.dto.paiment.PaimentInfluencerDto;
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
@RequestMapping("/api/collaborator/paimentInfluencer/")
public class PaimentInfluencerRestCollaborator {




    @Operation(summary = "Finds a list of all paimentInfluencers")
    @GetMapping("")
    public ResponseEntity<List<PaimentInfluencerDto>> findAll() throws Exception {
        ResponseEntity<List<PaimentInfluencerDto>> res = null;
        List<PaimentInfluencer> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<PaimentInfluencerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paimentInfluencers")
    @GetMapping("optimized")
    public ResponseEntity<List<PaimentInfluencerDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaimentInfluencerDto>> res = null;
        List<PaimentInfluencer> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaimentInfluencerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paimentInfluencer by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaimentInfluencerDto> findById(@PathVariable Long id) {
        PaimentInfluencer t = service.findById(id);
        if (t != null) {
            converter.init(true);
            PaimentInfluencerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paimentInfluencer by name")
    @GetMapping("name/{name}")
    public ResponseEntity<PaimentInfluencerDto> findByName(@PathVariable String name) {
	    PaimentInfluencer t = service.findByReferenceEntity(new PaimentInfluencer(name));
        if (t != null) {
            converter.init(true);
            PaimentInfluencerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paimentInfluencer")
    @PostMapping("")
    public ResponseEntity<PaimentInfluencerDto> save(@RequestBody PaimentInfluencerDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            PaimentInfluencer myT = converter.toItem(dto);
            PaimentInfluencer t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaimentInfluencerDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paimentInfluencer")
    @PutMapping("")
    public ResponseEntity<PaimentInfluencerDto> update(@RequestBody PaimentInfluencerDto dto) throws Exception {
        ResponseEntity<PaimentInfluencerDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaimentInfluencer t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaimentInfluencer updated = service.update(t);
            PaimentInfluencerDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paimentInfluencer")
    @PostMapping("multiple")
    public ResponseEntity<List<PaimentInfluencerDto>> delete(@RequestBody List<PaimentInfluencerDto> dtos) throws Exception {
        ResponseEntity<List<PaimentInfluencerDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<PaimentInfluencer> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified paimentInfluencer")
    @DeleteMapping("")
    public ResponseEntity<PaimentInfluencerDto> delete(@RequestBody PaimentInfluencerDto dto) throws Exception {
		ResponseEntity<PaimentInfluencerDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            PaimentInfluencer t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified paimentInfluencer")
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
    @Operation(summary = "Delete multiple paimentInfluencers by ids")
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


    @Operation(summary = "find by influencer id")
    @GetMapping("influencer/id/{id}")
    public List<PaimentInfluencerDto> findByInfluencerId(@PathVariable Long id){
        return findDtos(service.findByInfluencerId(id));
    }
    @Operation(summary = "delete by influencer id")
    @DeleteMapping("influencer/id/{id}")
    public int deleteByInfluencerId(@PathVariable Long id){
        return service.deleteByInfluencerId(id);
    }
    @Operation(summary = "find by coupon id")
    @GetMapping("coupon/id/{id}")
    public List<PaimentInfluencerDto> findByCouponId(@PathVariable Long id){
        return findDtos(service.findByCouponId(id));
    }
    @Operation(summary = "delete by coupon id")
    @DeleteMapping("coupon/id/{id}")
    public int deleteByCouponId(@PathVariable Long id){
        return service.deleteByCouponId(id);
    }

    @Operation(summary = "Finds a paimentInfluencer and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaimentInfluencerDto> findWithAssociatedLists(@PathVariable Long id) {
        PaimentInfluencer loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        PaimentInfluencerDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paimentInfluencers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaimentInfluencerDto>> findByCriteria(@RequestBody PaimentInfluencerCriteria criteria) throws Exception {
        ResponseEntity<List<PaimentInfluencerDto>> res = null;
        List<PaimentInfluencer> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<PaimentInfluencerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paimentInfluencers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaimentInfluencerCriteria criteria) throws Exception {
        List<PaimentInfluencer> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<PaimentInfluencerDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paimentInfluencer data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaimentInfluencerCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaimentInfluencerDto> findDtos(List<PaimentInfluencer> list){
        converter.initObject(true);
        List<PaimentInfluencerDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaimentInfluencerDto> getDtoResponseEntity(PaimentInfluencerDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PaimentInfluencerCollaboratorService service;
    @Autowired private PaimentInfluencerConverter converter;





}
