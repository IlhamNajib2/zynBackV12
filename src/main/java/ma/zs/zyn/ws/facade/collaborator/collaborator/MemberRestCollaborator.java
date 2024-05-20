package  ma.zs.zyn.ws.facade.collaborator.collaborator;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.zyn.zynerator.security.bean.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;

import ma.zs.zyn.bean.core.collaborator.Member;
import ma.zs.zyn.dao.criteria.core.collaborator.MemberCriteria;
import ma.zs.zyn.service.facade.collaborator.collaborator.MemberCollaboratorService;
import ma.zs.zyn.ws.converter.collaborator.MemberConverter;
import ma.zs.zyn.ws.dto.collaborator.MemberDto;
import ma.zs.zyn.zynerator.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/collaborator/member/")
public class MemberRestCollaborator {




    @Operation(summary = "Finds a list of all members")
    @GetMapping("")
    public ResponseEntity<List<MemberDto>> findAll() throws Exception {
        ResponseEntity<List<MemberDto>> res = null;
        List<Member> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<MemberDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a member by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MemberDto> findById(@PathVariable Long id) {
        Member t = service.findById(id);
        if (t != null) {
            converter.init(true);
            MemberDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  member")
    @PostMapping("")
    public ResponseEntity<MemberDto> save(@RequestBody MemberDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Member myT = converter.toItem(dto);
            Member t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MemberDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  member")
    @PutMapping("")
    public ResponseEntity<MemberDto> update(@RequestBody MemberDto dto) throws Exception {
        ResponseEntity<MemberDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Member t = service.findById(dto.getId());
            converter.copy(dto,t);
            Member updated = service.update(t);
            MemberDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of member")
    @PostMapping("multiple")
    public ResponseEntity<List<MemberDto>> delete(@RequestBody List<MemberDto> dtos) throws Exception {
        ResponseEntity<List<MemberDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Member> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified member")
    @DeleteMapping("")
    public ResponseEntity<MemberDto> delete(@RequestBody MemberDto dto) throws Exception {
		ResponseEntity<MemberDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Member t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified member")
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
    @Operation(summary = "Delete multiple members by ids")
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



    @Operation(summary = "Finds a member and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MemberDto> findWithAssociatedLists(@PathVariable Long id) {
        Member loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        MemberDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds members by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MemberDto>> findByCriteria(@RequestBody MemberCriteria criteria) throws Exception {
        ResponseEntity<List<MemberDto>> res = null;
        List<Member> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MemberDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated members by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MemberCriteria criteria) throws Exception {
        List<Member> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<MemberDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets member data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MemberCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MemberDto> findDtos(List<Member> list){
        converter.initObject(true);
        List<MemberDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MemberDto> getDtoResponseEntity(MemberDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private MemberCollaboratorService service;
    @Autowired private MemberConverter converter;





}
