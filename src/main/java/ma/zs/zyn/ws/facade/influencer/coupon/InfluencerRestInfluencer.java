package  ma.zs.zyn.ws.facade.influencer.coupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.coupon.Influencer;
import ma.zs.zyn.dao.criteria.core.coupon.InfluencerCriteria;
import ma.zs.zyn.service.facade.influencer.coupon.InfluencerInfluencerService;
import ma.zs.zyn.ws.converter.coupon.InfluencerConverter;
import ma.zs.zyn.ws.dto.coupon.InfluencerDto;
import ma.zs.zyn.zynerator.controller.AbstractController;
import ma.zs.zyn.zynerator.dto.AuditEntityDto;
import ma.zs.zyn.zynerator.util.PaginatedList;


import ma.zs.zyn.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.zyn.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.zyn.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/influencer/influencer/")
public class InfluencerRestInfluencer {




    @Operation(summary = "Finds a list of all influencers")
    @GetMapping("")
    public ResponseEntity<List<InfluencerDto>> findAll() throws Exception {
        ResponseEntity<List<InfluencerDto>> res = null;
        List<Influencer> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InfluencerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a influencer by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InfluencerDto> findById(@PathVariable Long id) {
        Influencer t = service.findById(id);
        if (t != null) {
            InfluencerDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  influencer")
    @PostMapping("")
    public ResponseEntity<InfluencerDto> save(@RequestBody InfluencerDto dto) throws Exception {
        if(dto!=null){
            Influencer myT = converter.toItem(dto);
            Influencer t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InfluencerDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  influencer")
    @PutMapping("")
    public ResponseEntity<InfluencerDto> update(@RequestBody InfluencerDto dto) throws Exception {
        ResponseEntity<InfluencerDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Influencer t = service.findById(dto.getId());
            converter.copy(dto,t);
            Influencer updated = service.update(t);
            InfluencerDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of influencer")
    @PostMapping("multiple")
    public ResponseEntity<List<InfluencerDto>> delete(@RequestBody List<InfluencerDto> dtos) throws Exception {
        ResponseEntity<List<InfluencerDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Influencer> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified influencer")
    @DeleteMapping("")
    public ResponseEntity<InfluencerDto> delete(@RequestBody InfluencerDto dto) throws Exception {
		ResponseEntity<InfluencerDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Influencer t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified influencer")
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
    @Operation(summary = "Delete multiple influencers by ids")
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



    @Operation(summary = "Finds a influencer and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InfluencerDto> findWithAssociatedLists(@PathVariable Long id) {
        Influencer loaded =  service.findWithAssociatedLists(id);
        InfluencerDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds influencers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InfluencerDto>> findByCriteria(@RequestBody InfluencerCriteria criteria) throws Exception {
        ResponseEntity<List<InfluencerDto>> res = null;
        List<Influencer> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InfluencerDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated influencers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InfluencerCriteria criteria) throws Exception {
        List<Influencer> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<InfluencerDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets influencer data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InfluencerCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InfluencerDto> findDtos(List<Influencer> list){
        List<InfluencerDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InfluencerDto> getDtoResponseEntity(InfluencerDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private InfluencerInfluencerService service;
    @Autowired private InfluencerConverter converter;





}
