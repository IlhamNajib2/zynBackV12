package  ma.zs.zyn.ws.facade.member.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.category.CategoryPackaging;
import ma.zs.zyn.dao.criteria.core.category.CategoryPackagingCriteria;
import ma.zs.zyn.service.facade.member.category.CategoryPackagingMemberService;
import ma.zs.zyn.ws.converter.category.CategoryPackagingConverter;
import ma.zs.zyn.ws.dto.category.CategoryPackagingDto;
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
@RequestMapping("/api/member/categoryPackaging/")
public class CategoryPackagingRestMember {




    @Operation(summary = "Finds a list of all categoryPackagings")
    @GetMapping("")
    public ResponseEntity<List<CategoryPackagingDto>> findAll() throws Exception {
        ResponseEntity<List<CategoryPackagingDto>> res = null;
        List<CategoryPackaging> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryPackagingDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categoryPackagings")
    @GetMapping("optimized")
    public ResponseEntity<List<CategoryPackagingDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CategoryPackagingDto>> res = null;
        List<CategoryPackaging> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryPackagingDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a categoryPackaging by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategoryPackagingDto> findById(@PathVariable Long id) {
        CategoryPackaging t = service.findById(id);
        if (t != null) {
            CategoryPackagingDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a categoryPackaging by name")
    @GetMapping("name/{name}")
    public ResponseEntity<CategoryPackagingDto> findByName(@PathVariable String name) {
	    CategoryPackaging t = service.findByReferenceEntity(new CategoryPackaging(name));
        if (t != null) {
            CategoryPackagingDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  categoryPackaging")
    @PostMapping("")
    public ResponseEntity<CategoryPackagingDto> save(@RequestBody CategoryPackagingDto dto) throws Exception {
        if(dto!=null){
            CategoryPackaging myT = converter.toItem(dto);
            CategoryPackaging t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CategoryPackagingDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  categoryPackaging")
    @PutMapping("")
    public ResponseEntity<CategoryPackagingDto> update(@RequestBody CategoryPackagingDto dto) throws Exception {
        ResponseEntity<CategoryPackagingDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CategoryPackaging t = service.findById(dto.getId());
            converter.copy(dto,t);
            CategoryPackaging updated = service.update(t);
            CategoryPackagingDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of categoryPackaging")
    @PostMapping("multiple")
    public ResponseEntity<List<CategoryPackagingDto>> delete(@RequestBody List<CategoryPackagingDto> dtos) throws Exception {
        ResponseEntity<List<CategoryPackagingDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CategoryPackaging> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified categoryPackaging")
    @DeleteMapping("")
    public ResponseEntity<CategoryPackagingDto> delete(@RequestBody CategoryPackagingDto dto) throws Exception {
		ResponseEntity<CategoryPackagingDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            CategoryPackaging t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified categoryPackaging")
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
    @Operation(summary = "Delete multiple categoryPackagings by ids")
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



    @Operation(summary = "Finds a categoryPackaging and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategoryPackagingDto> findWithAssociatedLists(@PathVariable Long id) {
        CategoryPackaging loaded =  service.findWithAssociatedLists(id);
        CategoryPackagingDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds categoryPackagings by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategoryPackagingDto>> findByCriteria(@RequestBody CategoryPackagingCriteria criteria) throws Exception {
        ResponseEntity<List<CategoryPackagingDto>> res = null;
        List<CategoryPackaging> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryPackagingDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated categoryPackagings by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategoryPackagingCriteria criteria) throws Exception {
        List<CategoryPackaging> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CategoryPackagingDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets categoryPackaging data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategoryPackagingCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CategoryPackagingDto> findDtos(List<CategoryPackaging> list){
        List<CategoryPackagingDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategoryPackagingDto> getDtoResponseEntity(CategoryPackagingDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CategoryPackagingMemberService service;
    @Autowired private CategoryPackagingConverter converter;





}
