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

import ma.zs.zyn.bean.core.template.LevelTemplate;
import ma.zs.zyn.dao.criteria.core.template.LevelTemplateCriteria;
import ma.zs.zyn.service.facade.admin.template.LevelTemplateAdminService;
import ma.zs.zyn.ws.converter.template.LevelTemplateConverter;
import ma.zs.zyn.ws.dto.template.LevelTemplateDto;
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
@RequestMapping("/api/admin/levelTemplate/")
public class LevelTemplateRestAdmin {




    @Operation(summary = "Finds a list of all levelTemplates")
    @GetMapping("")
    public ResponseEntity<List<LevelTemplateDto>> findAll() throws Exception {
        ResponseEntity<List<LevelTemplateDto>> res = null;
        List<LevelTemplate> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LevelTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all levelTemplates")
    @GetMapping("optimized")
    public ResponseEntity<List<LevelTemplateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<LevelTemplateDto>> res = null;
        List<LevelTemplate> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LevelTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a levelTemplate by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LevelTemplateDto> findById(@PathVariable Long id) {
        LevelTemplate t = service.findById(id);
        if (t != null) {
            LevelTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a levelTemplate by name")
    @GetMapping("name/{name}")
    public ResponseEntity<LevelTemplateDto> findByName(@PathVariable String name) {
	    LevelTemplate t = service.findByReferenceEntity(new LevelTemplate(name));
        if (t != null) {
            LevelTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  levelTemplate")
    @PostMapping("")
    public ResponseEntity<LevelTemplateDto> save(@RequestBody LevelTemplateDto dto) throws Exception {
        if(dto!=null){
            LevelTemplate myT = converter.toItem(dto);
            LevelTemplate t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                LevelTemplateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  levelTemplate")
    @PutMapping("")
    public ResponseEntity<LevelTemplateDto> update(@RequestBody LevelTemplateDto dto) throws Exception {
        ResponseEntity<LevelTemplateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            LevelTemplate t = service.findById(dto.getId());
            converter.copy(dto,t);
            LevelTemplate updated = service.update(t);
            LevelTemplateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of levelTemplate")
    @PostMapping("multiple")
    public ResponseEntity<List<LevelTemplateDto>> delete(@RequestBody List<LevelTemplateDto> dtos) throws Exception {
        ResponseEntity<List<LevelTemplateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<LevelTemplate> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified levelTemplate")
    @DeleteMapping("")
    public ResponseEntity<LevelTemplateDto> delete(@RequestBody LevelTemplateDto dto) throws Exception {
		ResponseEntity<LevelTemplateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            LevelTemplate t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified levelTemplate")
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
    @Operation(summary = "Delete multiple levelTemplates by ids")
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



    @Operation(summary = "Finds a levelTemplate and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LevelTemplateDto> findWithAssociatedLists(@PathVariable Long id) {
        LevelTemplate loaded =  service.findWithAssociatedLists(id);
        LevelTemplateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds levelTemplates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<LevelTemplateDto>> findByCriteria(@RequestBody LevelTemplateCriteria criteria) throws Exception {
        ResponseEntity<List<LevelTemplateDto>> res = null;
        List<LevelTemplate> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LevelTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated levelTemplates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody LevelTemplateCriteria criteria) throws Exception {
        List<LevelTemplate> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<LevelTemplateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets levelTemplate data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody LevelTemplateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<LevelTemplateDto> findDtos(List<LevelTemplate> list){
        List<LevelTemplateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<LevelTemplateDto> getDtoResponseEntity(LevelTemplateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private LevelTemplateAdminService service;
    @Autowired private LevelTemplateConverter converter;





}
