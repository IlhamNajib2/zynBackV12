package  ma.zs.zyn.ws.facade.member.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.template.TypeTemplate;
import ma.zs.zyn.dao.criteria.core.template.TypeTemplateCriteria;
import ma.zs.zyn.service.facade.member.template.TypeTemplateMemberService;
import ma.zs.zyn.ws.converter.template.TypeTemplateConverter;
import ma.zs.zyn.ws.dto.template.TypeTemplateDto;
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
@RequestMapping("/api/member/typeTemplate/")
public class TypeTemplateRestMember {




    @Operation(summary = "Finds a list of all typeTemplates")
    @GetMapping("")
    public ResponseEntity<List<TypeTemplateDto>> findAll() throws Exception {
        ResponseEntity<List<TypeTemplateDto>> res = null;
        List<TypeTemplate> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeTemplates")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeTemplateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeTemplateDto>> res = null;
        List<TypeTemplate> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeTemplate by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeTemplateDto> findById(@PathVariable Long id) {
        TypeTemplate t = service.findById(id);
        if (t != null) {
            TypeTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeTemplate by name")
    @GetMapping("name/{name}")
    public ResponseEntity<TypeTemplateDto> findByName(@PathVariable String name) {
	    TypeTemplate t = service.findByReferenceEntity(new TypeTemplate(name));
        if (t != null) {
            TypeTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeTemplate")
    @PostMapping("")
    public ResponseEntity<TypeTemplateDto> save(@RequestBody TypeTemplateDto dto) throws Exception {
        if(dto!=null){
            TypeTemplate myT = converter.toItem(dto);
            TypeTemplate t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeTemplateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeTemplate")
    @PutMapping("")
    public ResponseEntity<TypeTemplateDto> update(@RequestBody TypeTemplateDto dto) throws Exception {
        ResponseEntity<TypeTemplateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeTemplate t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeTemplate updated = service.update(t);
            TypeTemplateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeTemplate")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeTemplateDto>> delete(@RequestBody List<TypeTemplateDto> dtos) throws Exception {
        ResponseEntity<List<TypeTemplateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeTemplate> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeTemplate")
    @DeleteMapping("")
    public ResponseEntity<TypeTemplateDto> delete(@RequestBody TypeTemplateDto dto) throws Exception {
		ResponseEntity<TypeTemplateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeTemplate t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeTemplate")
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
    @Operation(summary = "Delete multiple typeTemplates by ids")
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



    @Operation(summary = "Finds a typeTemplate and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeTemplateDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeTemplate loaded =  service.findWithAssociatedLists(id);
        TypeTemplateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeTemplates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeTemplateDto>> findByCriteria(@RequestBody TypeTemplateCriteria criteria) throws Exception {
        ResponseEntity<List<TypeTemplateDto>> res = null;
        List<TypeTemplate> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeTemplates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeTemplateCriteria criteria) throws Exception {
        List<TypeTemplate> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeTemplateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeTemplate data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeTemplateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeTemplateDto> findDtos(List<TypeTemplate> list){
        List<TypeTemplateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeTemplateDto> getDtoResponseEntity(TypeTemplateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeTemplateMemberService service;
    @Autowired private TypeTemplateConverter converter;





}
