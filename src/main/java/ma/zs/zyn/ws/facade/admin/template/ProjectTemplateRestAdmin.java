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

import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.dao.criteria.core.template.ProjectTemplateCriteria;
import ma.zs.zyn.service.facade.admin.template.ProjectTemplateAdminService;
import ma.zs.zyn.ws.converter.template.ProjectTemplateConverter;
import ma.zs.zyn.ws.dto.template.ProjectTemplateDto;
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
@RequestMapping("/api/admin/projectTemplate/")
public class ProjectTemplateRestAdmin {




    @Operation(summary = "Finds a list of all projectTemplates")
    @GetMapping("")
    public ResponseEntity<List<ProjectTemplateDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectTemplateDto>> res = null;
        List<ProjectTemplate> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ProjectTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a projectTemplate by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectTemplateDto> findById(@PathVariable Long id) {
        ProjectTemplate t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjectTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  projectTemplate")
    @PostMapping("")
    public ResponseEntity<ProjectTemplateDto> save(@RequestBody ProjectTemplateDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ProjectTemplate myT = converter.toItem(dto);
            ProjectTemplate t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectTemplateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectTemplate")
    @PutMapping("")
    public ResponseEntity<ProjectTemplateDto> update(@RequestBody ProjectTemplateDto dto) throws Exception {
        ResponseEntity<ProjectTemplateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectTemplate t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectTemplate updated = service.update(t);
            ProjectTemplateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectTemplate")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectTemplateDto>> delete(@RequestBody List<ProjectTemplateDto> dtos) throws Exception {
        ResponseEntity<List<ProjectTemplateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ProjectTemplate> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified projectTemplate")
    @DeleteMapping("")
    public ResponseEntity<ProjectTemplateDto> delete(@RequestBody ProjectTemplateDto dto) throws Exception {
		ResponseEntity<ProjectTemplateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            ProjectTemplate t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectTemplate")
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
    @Operation(summary = "Delete multiple projectTemplates by ids")
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



    @Operation(summary = "Finds a projectTemplate and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectTemplateDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectTemplate loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjectTemplateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectTemplates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectTemplateDto>> findByCriteria(@RequestBody ProjectTemplateCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectTemplateDto>> res = null;
        List<ProjectTemplate> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjectTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectTemplates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectTemplateCriteria criteria) throws Exception {
        List<ProjectTemplate> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ProjectTemplateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectTemplate data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectTemplateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectTemplateDto> findDtos(List<ProjectTemplate> list){
        converter.initObject(true);
        List<ProjectTemplateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectTemplateDto> getDtoResponseEntity(ProjectTemplateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjectTemplateAdminService service;
    @Autowired private ProjectTemplateConverter converter;





}
