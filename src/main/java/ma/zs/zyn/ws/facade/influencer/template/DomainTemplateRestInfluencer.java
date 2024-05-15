package  ma.zs.zyn.ws.facade.influencer.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.template.DomainTemplate;
import ma.zs.zyn.dao.criteria.core.template.DomainTemplateCriteria;
import ma.zs.zyn.service.facade.influencer.template.DomainTemplateInfluencerService;
import ma.zs.zyn.ws.converter.template.DomainTemplateConverter;
import ma.zs.zyn.ws.dto.template.DomainTemplateDto;
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
@RequestMapping("/api/influencer/domainTemplate/")
public class DomainTemplateRestInfluencer {




    @Operation(summary = "Finds a list of all domainTemplates")
    @GetMapping("")
    public ResponseEntity<List<DomainTemplateDto>> findAll() throws Exception {
        ResponseEntity<List<DomainTemplateDto>> res = null;
        List<DomainTemplate> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomainTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all domainTemplates")
    @GetMapping("optimized")
    public ResponseEntity<List<DomainTemplateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DomainTemplateDto>> res = null;
        List<DomainTemplate> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomainTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a domainTemplate by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DomainTemplateDto> findById(@PathVariable Long id) {
        DomainTemplate t = service.findById(id);
        if (t != null) {
            DomainTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a domainTemplate by name")
    @GetMapping("name/{name}")
    public ResponseEntity<DomainTemplateDto> findByName(@PathVariable String name) {
	    DomainTemplate t = service.findByReferenceEntity(new DomainTemplate(name));
        if (t != null) {
            DomainTemplateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  domainTemplate")
    @PostMapping("")
    public ResponseEntity<DomainTemplateDto> save(@RequestBody DomainTemplateDto dto) throws Exception {
        if(dto!=null){
            DomainTemplate myT = converter.toItem(dto);
            DomainTemplate t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DomainTemplateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  domainTemplate")
    @PutMapping("")
    public ResponseEntity<DomainTemplateDto> update(@RequestBody DomainTemplateDto dto) throws Exception {
        ResponseEntity<DomainTemplateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DomainTemplate t = service.findById(dto.getId());
            converter.copy(dto,t);
            DomainTemplate updated = service.update(t);
            DomainTemplateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of domainTemplate")
    @PostMapping("multiple")
    public ResponseEntity<List<DomainTemplateDto>> delete(@RequestBody List<DomainTemplateDto> dtos) throws Exception {
        ResponseEntity<List<DomainTemplateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DomainTemplate> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified domainTemplate")
    @DeleteMapping("")
    public ResponseEntity<DomainTemplateDto> delete(@RequestBody DomainTemplateDto dto) throws Exception {
		ResponseEntity<DomainTemplateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            DomainTemplate t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified domainTemplate")
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
    @Operation(summary = "Delete multiple domainTemplates by ids")
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



    @Operation(summary = "Finds a domainTemplate and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DomainTemplateDto> findWithAssociatedLists(@PathVariable Long id) {
        DomainTemplate loaded =  service.findWithAssociatedLists(id);
        DomainTemplateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds domainTemplates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DomainTemplateDto>> findByCriteria(@RequestBody DomainTemplateCriteria criteria) throws Exception {
        ResponseEntity<List<DomainTemplateDto>> res = null;
        List<DomainTemplate> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomainTemplateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated domainTemplates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DomainTemplateCriteria criteria) throws Exception {
        List<DomainTemplate> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DomainTemplateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets domainTemplate data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DomainTemplateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DomainTemplateDto> findDtos(List<DomainTemplate> list){
        List<DomainTemplateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DomainTemplateDto> getDtoResponseEntity(DomainTemplateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DomainTemplateInfluencerService service;
    @Autowired private DomainTemplateConverter converter;





}
