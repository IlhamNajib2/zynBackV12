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

import ma.zs.zyn.bean.core.coupon.Coupon;
import ma.zs.zyn.dao.criteria.core.coupon.CouponCriteria;
import ma.zs.zyn.service.facade.influencer.coupon.CouponInfluencerService;
import ma.zs.zyn.ws.converter.coupon.CouponConverter;
import ma.zs.zyn.ws.dto.coupon.CouponDto;
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
@RequestMapping("/api/influencer/coupon/")
public class CouponRestInfluencer {




    @Operation(summary = "Finds a list of all coupons")
    @GetMapping("")
    public ResponseEntity<List<CouponDto>> findAll() throws Exception {
        ResponseEntity<List<CouponDto>> res = null;
        List<Coupon> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<CouponDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all coupons")
    @GetMapping("optimized")
    public ResponseEntity<List<CouponDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CouponDto>> res = null;
        List<Coupon> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<CouponDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a coupon by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CouponDto> findById(@PathVariable Long id) {
        Coupon t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CouponDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a coupon by name")
    @GetMapping("name/{name}")
    public ResponseEntity<CouponDto> findByName(@PathVariable String name) {
	    Coupon t = service.findByReferenceEntity(new Coupon(name));
        if (t != null) {
            converter.init(true);
            CouponDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  coupon")
    @PostMapping("")
    public ResponseEntity<CouponDto> save(@RequestBody CouponDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Coupon myT = converter.toItem(dto);
            Coupon t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CouponDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  coupon")
    @PutMapping("")
    public ResponseEntity<CouponDto> update(@RequestBody CouponDto dto) throws Exception {
        ResponseEntity<CouponDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Coupon t = service.findById(dto.getId());
            converter.copy(dto,t);
            Coupon updated = service.update(t);
            CouponDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of coupon")
    @PostMapping("multiple")
    public ResponseEntity<List<CouponDto>> delete(@RequestBody List<CouponDto> dtos) throws Exception {
        ResponseEntity<List<CouponDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Coupon> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified coupon")
    @DeleteMapping("")
    public ResponseEntity<CouponDto> delete(@RequestBody CouponDto dto) throws Exception {
		ResponseEntity<CouponDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Coupon t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified coupon")
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
    @Operation(summary = "Delete multiple coupons by ids")
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
    public List<CouponDto> findByInfluencerId(@PathVariable Long id){
        return findDtos(service.findByInfluencerId(id));
    }
    @Operation(summary = "delete by influencer id")
    @DeleteMapping("influencer/id/{id}")
    public int deleteByInfluencerId(@PathVariable Long id){
        return service.deleteByInfluencerId(id);
    }

    @Operation(summary = "Finds a coupon and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CouponDto> findWithAssociatedLists(@PathVariable Long id) {
        Coupon loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CouponDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds coupons by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CouponDto>> findByCriteria(@RequestBody CouponCriteria criteria) throws Exception {
        ResponseEntity<List<CouponDto>> res = null;
        List<Coupon> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<CouponDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated coupons by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CouponCriteria criteria) throws Exception {
        List<Coupon> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<CouponDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets coupon data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CouponCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CouponDto> findDtos(List<Coupon> list){
        converter.initList(false);
        converter.initObject(true);
        List<CouponDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CouponDto> getDtoResponseEntity(CouponDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CouponInfluencerService service;
    @Autowired private CouponConverter converter;





}
