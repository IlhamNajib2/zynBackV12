package  ma.zs.zyn.ws.facade.member.coupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.coupon.CouponDetail;
import ma.zs.zyn.dao.criteria.core.coupon.CouponDetailCriteria;
import ma.zs.zyn.service.facade.member.coupon.CouponDetailMemberService;
import ma.zs.zyn.ws.converter.coupon.CouponDetailConverter;
import ma.zs.zyn.ws.dto.coupon.CouponDetailDto;
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
@RequestMapping("/api/member/couponDetail/")
public class CouponDetailRestMember {




    @Operation(summary = "Finds a list of all couponDetails")
    @GetMapping("")
    public ResponseEntity<List<CouponDetailDto>> findAll() throws Exception {
        ResponseEntity<List<CouponDetailDto>> res = null;
        List<CouponDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CouponDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a couponDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CouponDetailDto> findById(@PathVariable Long id) {
        CouponDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CouponDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  couponDetail")
    @PostMapping("")
    public ResponseEntity<CouponDetailDto> save(@RequestBody CouponDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            CouponDetail myT = converter.toItem(dto);
            CouponDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CouponDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  couponDetail")
    @PutMapping("")
    public ResponseEntity<CouponDetailDto> update(@RequestBody CouponDetailDto dto) throws Exception {
        ResponseEntity<CouponDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CouponDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            CouponDetail updated = service.update(t);
            CouponDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of couponDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<CouponDetailDto>> delete(@RequestBody List<CouponDetailDto> dtos) throws Exception {
        ResponseEntity<List<CouponDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<CouponDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified couponDetail")
    @DeleteMapping("")
    public ResponseEntity<CouponDetailDto> delete(@RequestBody CouponDetailDto dto) throws Exception {
		ResponseEntity<CouponDetailDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            CouponDetail t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified couponDetail")
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
    @Operation(summary = "Delete multiple couponDetails by ids")
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


    @Operation(summary = "find by packaging id")
    @GetMapping("packaging/id/{id}")
    public List<CouponDetailDto> findByPackagingId(@PathVariable Long id){
        return findDtos(service.findByPackagingId(id));
    }
    @Operation(summary = "delete by packaging id")
    @DeleteMapping("packaging/id/{id}")
    public int deleteByPackagingId(@PathVariable Long id){
        return service.deleteByPackagingId(id);
    }
    @Operation(summary = "find by coupon id")
    @GetMapping("coupon/id/{id}")
    public List<CouponDetailDto> findByCouponId(@PathVariable Long id){
        return findDtos(service.findByCouponId(id));
    }
    @Operation(summary = "delete by coupon id")
    @DeleteMapping("coupon/id/{id}")
    public int deleteByCouponId(@PathVariable Long id){
        return service.deleteByCouponId(id);
    }

    @Operation(summary = "Finds a couponDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CouponDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        CouponDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CouponDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds couponDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CouponDetailDto>> findByCriteria(@RequestBody CouponDetailCriteria criteria) throws Exception {
        ResponseEntity<List<CouponDetailDto>> res = null;
        List<CouponDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CouponDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated couponDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CouponDetailCriteria criteria) throws Exception {
        List<CouponDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CouponDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets couponDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CouponDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CouponDetailDto> findDtos(List<CouponDetail> list){
        converter.initObject(true);
        List<CouponDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CouponDetailDto> getDtoResponseEntity(CouponDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CouponDetailMemberService service;
    @Autowired private CouponDetailConverter converter;





}
