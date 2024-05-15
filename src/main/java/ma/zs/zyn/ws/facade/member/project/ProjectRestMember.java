package  ma.zs.zyn.ws.facade.member.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.dao.criteria.core.project.ProjectCriteria;
import ma.zs.zyn.service.facade.member.project.ProjectMemberService;
import ma.zs.zyn.ws.converter.project.ProjectConverter;
import ma.zs.zyn.ws.dto.project.ProjectDto;
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
@RequestMapping("/api/member/project/")
public class ProjectRestMember {




    @Operation(summary = "Finds a list of all projects")
    @GetMapping("")
    public ResponseEntity<List<ProjectDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectDto>> res = null;
        List<Project> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<ProjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projects")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectDto>> res = null;
        List<Project> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a project by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        Project t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a project by code")
    @GetMapping("code/{code}")
    public ResponseEntity<ProjectDto> findByCode(@PathVariable String code) {
	    Project t = service.findByReferenceEntity(new Project(code));
        if (t != null) {
            converter.init(true);
            ProjectDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  project")
    @PostMapping("")
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Project myT = converter.toItem(dto);
            Project t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  project")
    @PutMapping("")
    public ResponseEntity<ProjectDto> update(@RequestBody ProjectDto dto) throws Exception {
        ResponseEntity<ProjectDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Project t = service.findById(dto.getId());
            converter.copy(dto,t);
            Project updated = service.update(t);
            ProjectDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of project")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectDto>> delete(@RequestBody List<ProjectDto> dtos) throws Exception {
        ResponseEntity<List<ProjectDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Project> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified project")
    @DeleteMapping("")
    public ResponseEntity<ProjectDto> delete(@RequestBody ProjectDto dto) throws Exception {
		ResponseEntity<ProjectDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Project t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified project")
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
    @Operation(summary = "Delete multiple projects by ids")
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


    @Operation(summary = "find by projectState code")
    @GetMapping("projectState/code/{code}")
    public List<ProjectDto> findByProjectStateCode(@PathVariable String code){
        return findDtos(service.findByProjectStateCode(code));
    }
    @Operation(summary = "delete by projectState code")
    @DeleteMapping("projectState/code/{code}")
    public int deleteByProjectStateCode(@PathVariable String code){
        return service.deleteByProjectStateCode(code);
    }
    @Operation(summary = "find by inscriptionMembre id")
    @GetMapping("inscriptionMembre/id/{id}")
    public List<ProjectDto> findByInscriptionMembreId(@PathVariable Long id){
        return findDtos(service.findByInscriptionMembreId(id));
    }
    @Operation(summary = "delete by inscriptionMembre id")
    @DeleteMapping("inscriptionMembre/id/{id}")
    public int deleteByInscriptionMembreId(@PathVariable Long id){
        return service.deleteByInscriptionMembreId(id);
    }
    @Operation(summary = "find by domainTemplate id")
    @GetMapping("domainTemplate/id/{id}")
    public List<ProjectDto> findByDomainTemplateId(@PathVariable Long id){
        return findDtos(service.findByDomainTemplateId(id));
    }
    @Operation(summary = "delete by domainTemplate id")
    @DeleteMapping("domainTemplate/id/{id}")
    public int deleteByDomainTemplateId(@PathVariable Long id){
        return service.deleteByDomainTemplateId(id);
    }

    @Operation(summary = "Finds a project and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectDto> findWithAssociatedLists(@PathVariable Long id) {
        Project loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjectDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projects by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectDto>> findByCriteria(@RequestBody ProjectCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectDto>> res = null;
        List<Project> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projects by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectCriteria criteria) throws Exception {
        List<Project> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets project data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectDto> findDtos(List<Project> list){
        converter.initList(false);
        converter.initObject(true);
        List<ProjectDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectDto> getDtoResponseEntity(ProjectDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjectMemberService service;
    @Autowired private ProjectConverter converter;





}
