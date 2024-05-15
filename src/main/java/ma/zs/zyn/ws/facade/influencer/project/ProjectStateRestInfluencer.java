package  ma.zs.zyn.ws.facade.influencer.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.dao.criteria.core.project.ProjectStateCriteria;
import ma.zs.zyn.service.facade.influencer.project.ProjectStateInfluencerService;
import ma.zs.zyn.ws.converter.project.ProjectStateConverter;
import ma.zs.zyn.ws.dto.project.ProjectStateDto;
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
@RequestMapping("/api/influencer/projectState/")
public class ProjectStateRestInfluencer {




    @Operation(summary = "Finds a list of all projectStates")
    @GetMapping("")
    public ResponseEntity<List<ProjectStateDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectStateDto>> res = null;
        List<ProjectState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projectStates")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectStateDto>> res = null;
        List<ProjectState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a projectState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectStateDto> findById(@PathVariable Long id) {
        ProjectState t = service.findById(id);
        if (t != null) {
            ProjectStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projectState by code")
    @GetMapping("code/{code}")
    public ResponseEntity<ProjectStateDto> findByCode(@PathVariable String code) {
	    ProjectState t = service.findByReferenceEntity(new ProjectState(code));
        if (t != null) {
            ProjectStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projectState")
    @PostMapping("")
    public ResponseEntity<ProjectStateDto> save(@RequestBody ProjectStateDto dto) throws Exception {
        if(dto!=null){
            ProjectState myT = converter.toItem(dto);
            ProjectState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectState")
    @PutMapping("")
    public ResponseEntity<ProjectStateDto> update(@RequestBody ProjectStateDto dto) throws Exception {
        ResponseEntity<ProjectStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectState t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectState updated = service.update(t);
            ProjectStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectState")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectStateDto>> delete(@RequestBody List<ProjectStateDto> dtos) throws Exception {
        ResponseEntity<List<ProjectStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProjectState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified projectState")
    @DeleteMapping("")
    public ResponseEntity<ProjectStateDto> delete(@RequestBody ProjectStateDto dto) throws Exception {
		ResponseEntity<ProjectStateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            ProjectState t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectState")
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
    @Operation(summary = "Delete multiple projectStates by ids")
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



    @Operation(summary = "Finds a projectState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectStateDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectState loaded =  service.findWithAssociatedLists(id);
        ProjectStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectStateDto>> findByCriteria(@RequestBody ProjectStateCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectStateDto>> res = null;
        List<ProjectState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectStateCriteria criteria) throws Exception {
        List<ProjectState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProjectStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectStateDto> findDtos(List<ProjectState> list){
        List<ProjectStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectStateDto> getDtoResponseEntity(ProjectStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjectStateInfluencerService service;
    @Autowired private ProjectStateConverter converter;





}
