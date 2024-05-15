package  ma.zs.zyn.ws.facade.admin.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.template.CategoryTemplate;
import ma.zs.zyn.dao.criteria.core.template.CategoryTemplateCriteria;
import ma.zs.zyn.service.facade.admin.template.CategoryTemplateAdminService;
import ma.zs.zyn.ws.converter.template.CategoryTemplateConverter;
import ma.zs.zyn.ws.dto.template.CategoryTemplateDto;
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
@RequestMapping("/api/admin/categoryTemplate/")
public class CategoryTemplateRestAdmin {




    @Operation(summary = "Finds a list of all categoryTemplates")
    @GetMapping("")
    public ResponseEntity<List<CategoryTemplateDto>> findAll() throws Exception {
        ResponseEntity<List<CategoryTemplateDto>> res = null;
        List<CategoryTemplate> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categoryTemplates")
    @GetMapping("optimized")
    public ResponseEntity<List<CategoryTemplateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CategoryTemplateDto>> res = null;
        List<CategoryTemplate> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a categoryTemplate by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategoryTemplateDto> findById(@PathVariable Long id) {
        CategoryTemplate t = service.findById(id);
        if (t != null) {
            CategoryTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a categoryTemplate by name")
    @GetMapping("name/{name}")
    public ResponseEntity<CategoryTemplateDto> findByName(@PathVariable String name) {
	    CategoryTemplate t = service.findByReferenceEntity(new CategoryTemplate(name));
        if (t != null) {
            CategoryTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  categoryTemplate")
    @PostMapping("")
    public ResponseEntity<CategoryTemplateDto> save(@RequestBody CategoryTemplateDto dto) throws Exception {
        if(dto!=null){
            CategoryTemplate myT = converter.toItem(dto);
            CategoryTemplate t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CategoryTemplateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  categoryTemplate")
    @PutMapping("")
    public ResponseEntity<CategoryTemplateDto> update(@RequestBody CategoryTemplateDto dto) throws Exception {
        ResponseEntity<CategoryTemplateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CategoryTemplate t = service.findById(dto.getId());
            converter.copy(dto,t);
            CategoryTemplate updated = service.update(t);
            CategoryTemplateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of categoryTemplate")
    @PostMapping("multiple")
    public ResponseEntity<List<CategoryTemplateDto>> delete(@RequestBody List<CategoryTemplateDto> dtos) throws Exception {
        ResponseEntity<List<CategoryTemplateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CategoryTemplate> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified categoryTemplate")
    @DeleteMapping("")
    public ResponseEntity<CategoryTemplateDto> delete(@RequestBody CategoryTemplateDto dto) throws Exception {
		ResponseEntity<CategoryTemplateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            CategoryTemplate t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified categoryTemplate")
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
    @Operation(summary = "Delete multiple categoryTemplates by ids")
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



    @Operation(summary = "Finds a categoryTemplate and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategoryTemplateDto> findWithAssociatedLists(@PathVariable Long id) {
        CategoryTemplate loaded =  service.findWithAssociatedLists(id);
        CategoryTemplateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds categoryTemplates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategoryTemplateDto>> findByCriteria(@RequestBody CategoryTemplateCriteria criteria) throws Exception {
        ResponseEntity<List<CategoryTemplateDto>> res = null;
        List<CategoryTemplate> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated categoryTemplates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategoryTemplateCriteria criteria) throws Exception {
        List<CategoryTemplate> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CategoryTemplateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets categoryTemplate data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategoryTemplateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CategoryTemplateDto> findDtos(List<CategoryTemplate> list){
        List<CategoryTemplateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategoryTemplateDto> getDtoResponseEntity(CategoryTemplateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CategoryTemplateAdminService service;
    @Autowired private CategoryTemplateConverter converter;





}
