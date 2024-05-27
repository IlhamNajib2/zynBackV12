package  ma.zs.zyn.ws.facade.collaborator.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.dao.criteria.core.template.TemplateCriteria;
import ma.zs.zyn.service.facade.collaborator.template.TemplateCollaboratorService;
import ma.zs.zyn.ws.converter.template.TemplateConverter;
import ma.zs.zyn.ws.dto.template.TemplateDto;
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
@RequestMapping("/api/collaborator/template/")
public class TemplateRestCollaborator {




    @Operation(summary = "Finds a list of all templates")
    @GetMapping("")
    public ResponseEntity<List<TemplateDto>> findAll() throws Exception {
        ResponseEntity<List<TemplateDto>> res = null;
        List<Template> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<TemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all templates")
    @GetMapping("optimized")
    public ResponseEntity<List<TemplateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TemplateDto>> res = null;
        List<Template> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<TemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a template by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TemplateDto> findById(@PathVariable Long id) {
        Template t = service.findById(id);
        if (t != null) {
            converter.init(true);
            TemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a template by name")
    @GetMapping("name/{name}")
    public ResponseEntity<TemplateDto> findByName(@PathVariable String name) {
	    Template t = service.findByReferenceEntity(new Template(name));
        if (t != null) {
            converter.init(true);
            TemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  template")
    @PostMapping("")
    public ResponseEntity<TemplateDto> save(@RequestBody TemplateDto dto) throws Exception {
        if(dto!=null){
            dto.getMember().setAccountNonExpired(true);
            dto.getMember().setAccountNonLocked(true);
            dto.getMember().setCredentialsNonExpired(true);
            dto.getMember().setEnabled(true);
            dto.getMember().setPasswordChanged(false);
            converter.init(true);
            Template myT = converter.toItem(dto);
            Template t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TemplateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  template")
    @PutMapping("")
    public ResponseEntity<TemplateDto> update(@RequestBody TemplateDto dto) throws Exception {
        ResponseEntity<TemplateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Template t = service.findById(dto.getId());
            converter.copy(dto,t);
            Template updated = service.update(t);
            TemplateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of template")
    @PostMapping("multiple")
    public ResponseEntity<List<TemplateDto>> delete(@RequestBody List<TemplateDto> dtos) throws Exception {
        ResponseEntity<List<TemplateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Template> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified template")
    @DeleteMapping("")
    public ResponseEntity<TemplateDto> delete(@RequestBody TemplateDto dto) throws Exception {
		ResponseEntity<TemplateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Template t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified template")
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
    @Operation(summary = "Delete multiple templates by ids")
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


    @Operation(summary = "find by categoryTemplate id")
    @GetMapping("categoryTemplate/id/{id}")
    public List<TemplateDto> findByCategoryTemplateId(@PathVariable Long id){
        return findDtos(service.findByCategoryTemplateId(id));
    }
    @Operation(summary = "delete by categoryTemplate id")
    @DeleteMapping("categoryTemplate/id/{id}")
    public int deleteByCategoryTemplateId(@PathVariable Long id){
        return service.deleteByCategoryTemplateId(id);
    }
    @Operation(summary = "find by typeTemplate id")
    @GetMapping("typeTemplate/id/{id}")
    public List<TemplateDto> findByTypeTemplateId(@PathVariable Long id){
        return findDtos(service.findByTypeTemplateId(id));
    }
    @Operation(summary = "delete by typeTemplate id")
    @DeleteMapping("typeTemplate/id/{id}")
    public int deleteByTypeTemplateId(@PathVariable Long id){
        return service.deleteByTypeTemplateId(id);
    }
    @Operation(summary = "find by levelTemplate id")
    @GetMapping("levelTemplate/id/{id}")
    public List<TemplateDto> findByLevelTemplateId(@PathVariable Long id){
        return findDtos(service.findByLevelTemplateId(id));
    }
    @Operation(summary = "delete by levelTemplate id")
    @DeleteMapping("levelTemplate/id/{id}")
    public int deleteByLevelTemplateId(@PathVariable Long id){
        return service.deleteByLevelTemplateId(id);
    }
    @Operation(summary = "find by domainTemplate id")
    @GetMapping("domainTemplate/id/{id}")
    public List<TemplateDto> findByDomainTemplateId(@PathVariable Long id){
        return findDtos(service.findByDomainTemplateId(id));
    }
    @Operation(summary = "delete by domainTemplate id")
    @DeleteMapping("domainTemplate/id/{id}")
    public int deleteByDomainTemplateId(@PathVariable Long id){
        return service.deleteByDomainTemplateId(id);
    }
    @Operation(summary = "find by member id")
    @GetMapping("member/id/{id}")
    public List<TemplateDto> findByMemberId(@PathVariable Long id){
        return findDtos(service.findByMemberId(id));
    }
    @Operation(summary = "delete by member id")
    @DeleteMapping("member/id/{id}")
    public int deleteByMemberId(@PathVariable Long id){
        return service.deleteByMemberId(id);
    }
    @Operation(summary = "find by technology id")
    @GetMapping("technology/id/{id}")
    public List<TemplateDto> findByTechnologyId(@PathVariable Long id){
        return findDtos(service.findByTechnologyId(id));
    }
    @Operation(summary = "delete by technology id")
    @DeleteMapping("technology/id/{id}")
    public int deleteByTechnologyId(@PathVariable Long id){
        return service.deleteByTechnologyId(id);
    }

    @Operation(summary = "Finds a template and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TemplateDto> findWithAssociatedLists(@PathVariable Long id) {
        Template loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        TemplateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds templates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TemplateDto>> findByCriteria(@RequestBody TemplateCriteria criteria) throws Exception {
        ResponseEntity<List<TemplateDto>> res = null;
        List<Template> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<TemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated templates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TemplateCriteria criteria) throws Exception {
        List<Template> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<TemplateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets template data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TemplateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TemplateDto> findDtos(List<Template> list){
        converter.initObject(true);
        List<TemplateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TemplateDto> getDtoResponseEntity(TemplateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TemplateCollaboratorService service;
    @Autowired private TemplateConverter converter;





}
