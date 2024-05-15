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

import ma.zs.zyn.bean.core.inscription.InscriptionMembre;
import ma.zs.zyn.dao.criteria.core.inscription.InscriptionMembreCriteria;
import ma.zs.zyn.service.facade.influencer.inscription.InscriptionMembreInfluencerService;
import ma.zs.zyn.ws.converter.inscription.InscriptionMembreConverter;
import ma.zs.zyn.ws.dto.inscription.InscriptionMembreDto;
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
@RequestMapping("/api/influencer/inscriptionMembre/")
public class InscriptionMembreRestInfluencer {




    @Operation(summary = "Finds a list of all inscriptionMembres")
    @GetMapping("")
    public ResponseEntity<List<InscriptionMembreDto>> findAll() throws Exception {
        ResponseEntity<List<InscriptionMembreDto>> res = null;
        List<InscriptionMembre> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<InscriptionMembreDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a inscriptionMembre by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InscriptionMembreDto> findById(@PathVariable Long id) {
        InscriptionMembre t = service.findById(id);
        if (t != null) {
            converter.init(true);
            InscriptionMembreDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  inscriptionMembre")
    @PostMapping("")
    public ResponseEntity<InscriptionMembreDto> save(@RequestBody InscriptionMembreDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            InscriptionMembre myT = converter.toItem(dto);
            InscriptionMembre t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InscriptionMembreDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  inscriptionMembre")
    @PutMapping("")
    public ResponseEntity<InscriptionMembreDto> update(@RequestBody InscriptionMembreDto dto) throws Exception {
        ResponseEntity<InscriptionMembreDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            InscriptionMembre t = service.findById(dto.getId());
            converter.copy(dto,t);
            InscriptionMembre updated = service.update(t);
            InscriptionMembreDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of inscriptionMembre")
    @PostMapping("multiple")
    public ResponseEntity<List<InscriptionMembreDto>> delete(@RequestBody List<InscriptionMembreDto> dtos) throws Exception {
        ResponseEntity<List<InscriptionMembreDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<InscriptionMembre> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified inscriptionMembre")
    @DeleteMapping("")
    public ResponseEntity<InscriptionMembreDto> delete(@RequestBody InscriptionMembreDto dto) throws Exception {
		ResponseEntity<InscriptionMembreDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            InscriptionMembre t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified inscriptionMembre")
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
    @Operation(summary = "Delete multiple inscriptionMembres by ids")
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


    @Operation(summary = "find by member id")
    @GetMapping("member/id/{id}")
    public List<InscriptionMembreDto> findByMemberId(@PathVariable Long id){
        return findDtos(service.findByMemberId(id));
    }
    @Operation(summary = "delete by member id")
    @DeleteMapping("member/id/{id}")
    public int deleteByMemberId(@PathVariable Long id){
        return service.deleteByMemberId(id);
    }
    @Operation(summary = "find by inscriptionMembreState id")
    @GetMapping("inscriptionMembreState/id/{id}")
    public List<InscriptionMembreDto> findByInscriptionMembreStateId(@PathVariable Long id){
        return findDtos(service.findByInscriptionMembreStateId(id));
    }
    @Operation(summary = "delete by inscriptionMembreState id")
    @DeleteMapping("inscriptionMembreState/id/{id}")
    public int deleteByInscriptionMembreStateId(@PathVariable Long id){
        return service.deleteByInscriptionMembreStateId(id);
    }
    @Operation(summary = "find by inscriptionCollaborator id")
    @GetMapping("inscriptionCollaborator/id/{id}")
    public List<InscriptionMembreDto> findByInscriptionCollaboratorId(@PathVariable Long id){
        return findDtos(service.findByInscriptionCollaboratorId(id));
    }
    @Operation(summary = "delete by inscriptionCollaborator id")
    @DeleteMapping("inscriptionCollaborator/id/{id}")
    public int deleteByInscriptionCollaboratorId(@PathVariable Long id){
        return service.deleteByInscriptionCollaboratorId(id);
    }

    @Operation(summary = "Finds a inscriptionMembre and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InscriptionMembreDto> findWithAssociatedLists(@PathVariable Long id) {
        InscriptionMembre loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        InscriptionMembreDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds inscriptionMembres by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InscriptionMembreDto>> findByCriteria(@RequestBody InscriptionMembreCriteria criteria) throws Exception {
        ResponseEntity<List<InscriptionMembreDto>> res = null;
        List<InscriptionMembre> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InscriptionMembreDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated inscriptionMembres by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InscriptionMembreCriteria criteria) throws Exception {
        List<InscriptionMembre> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<InscriptionMembreDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets inscriptionMembre data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InscriptionMembreCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InscriptionMembreDto> findDtos(List<InscriptionMembre> list){
        converter.initObject(true);
        List<InscriptionMembreDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InscriptionMembreDto> getDtoResponseEntity(InscriptionMembreDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private InscriptionMembreInfluencerService service;
    @Autowired private InscriptionMembreConverter converter;





}
