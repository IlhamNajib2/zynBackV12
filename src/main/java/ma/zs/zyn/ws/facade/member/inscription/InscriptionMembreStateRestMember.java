package  ma.zs.zyn.ws.facade.member.inscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.inscription.InscriptionMembreState;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreStateCriteria;
import ma.zs.zyn.service.facade.member.inscription.InscriptionMembreStateMemberService;
import ma.zs.zyn.ws.converter.inscription.InscriptionMembreStateConverter;
import ma.zs.zyn.ws.dto.inscription.InscriptionMembreStateDto;
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
@RequestMapping("/api/member/inscriptionMembreState/")
public class InscriptionMembreStateRestMember {




    @Operation(summary = "Finds a list of all inscriptionMembreStates")
    @GetMapping("")
    public ResponseEntity<List<InscriptionMembreStateDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionMembreStateDto>> res = null;
        List<InscriptionMembreState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionMembreStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all inscriptionMembreStates")
    @GetMapping("optimized")
    public ResponseEntity<List<InscriptionMembreStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<InscriptionMembreStateDto>> res = null;
        List<InscriptionMembreState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionMembreStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a inscriptionMembreState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionMembreStateDto> findById(@PathVariable Long id) {
        InscriptionMembreState t = service.findById(id);
        if (t != null) {
            InscriptionMembreStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a inscriptionMembreState by name")
    @GetMapping("name/{name}")
    public ResponseEntity<InscriptionMembreStateDto> findByName(@PathVariable String name) {
	    InscriptionMembreState t = service.findByReferenceEntity(new InscriptionMembreState(name));
        if (t != null) {
            InscriptionMembreStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  inscriptionMembreState")
    @PostMapping("")
    public ResponseEntity<InscriptionMembreStateDto> save(@RequestBody InscriptionMembreStateDto dto) throws Exception {
        if(dto!=null){
            InscriptionMembreState myT = converter.toItem(dto);
            InscriptionMembreState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionMembreStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscriptionMembreState")
    @PutMapping("")
    public ResponseEntity<InscriptionMembreStateDto> update(@RequestBody InscriptionMembreStateDto dto) throws Exception {
        ResponseEntity<InscriptionMembreStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            InscriptionMembreState t = service.findById(dto.getId());
            converter.copy(dto,t);
            InscriptionMembreState updated = service.update(t);
            InscriptionMembreStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscriptionMembreState")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionMembreStateDto>> delete(@RequestBody List<InscriptionMembreStateDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionMembreStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<InscriptionMembreState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified inscriptionMembreState")
    @DeleteMapping("")
    public ResponseEntity<InscriptionMembreStateDto> delete(@RequestBody InscriptionMembreStateDto dto) throws Exception {
		ResponseEntity<InscriptionMembreStateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            InscriptionMembreState t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscriptionMembreState")
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
    @Operation(summary = "Delete multiple inscriptionMembreStates by ids")
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



    @Operation(summary = "Finds a inscriptionMembreState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionMembreStateDto> findWithAssociatedLists(@PathVariable Long id) {
        InscriptionMembreState loaded =  service.findWithAssociatedLists(id);
        InscriptionMembreStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptionMembreStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionMembreStateDto>> findByCriteria(@RequestBody InscriptionMembreStateCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionMembreStateDto>> res = null;
        List<InscriptionMembreState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InscriptionMembreStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptionMembreStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionMembreStateCriteria criteria) throws Exception {
        List<InscriptionMembreState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<InscriptionMembreStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscriptionMembreState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionMembreStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionMembreStateDto> findDtos(List<InscriptionMembreState> list){
        List<InscriptionMembreStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionMembreStateDto> getDtoResponseEntity(InscriptionMembreStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private InscriptionMembreStateMemberService service;
    @Autowired private InscriptionMembreStateConverter converter;





}
