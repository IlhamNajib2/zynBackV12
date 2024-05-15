package ma.zs.zyn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.service.facade.admin.collaborator.CollaboratorAdminService;
import ma.zs.zyn.bean.core.collaborator.Member;
import ma.zs.zyn.service.facade.admin.collaborator.MemberAdminService;
import ma.zs.zyn.bean.core.coupon.Influencer;
import ma.zs.zyn.service.facade.admin.coupon.InfluencerAdminService;
import ma.zs.zyn.zynerator.security.bean.*;
import ma.zs.zyn.zynerator.security.common.AuthoritiesConstants;
import ma.zs.zyn.zynerator.security.service.facade.*;


import ma.zs.zyn.zynerator.security.bean.User;
import ma.zs.zyn.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class ZynApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx=SpringApplication.run(ZynApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , CollaboratorAdminService collaboratorService, MemberAdminService memberService, InfluencerAdminService influencerService) {
    return (args) -> {
        if(true){


        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Collaborator
        Collaborator userForCollaborator = new Collaborator("collaborator");
		userForCollaborator.setPassword("123");
		// Role Collaborator
		Role roleForCollaborator = new Role();
		roleForCollaborator.setAuthority(AuthoritiesConstants.COLLABORATOR);
		roleForCollaborator.setCreatedAt(LocalDateTime.now());
		Role roleForCollaboratorSaved = roleService.create(roleForCollaborator);
		RoleUser roleUserForCollaborator = new RoleUser();
		roleUserForCollaborator.setRole(roleForCollaboratorSaved);
		if (userForCollaborator.getRoleUsers() == null)
			userForCollaborator.setRoleUsers(new ArrayList<>());

		userForCollaborator.getRoleUsers().add(roleUserForCollaborator);
		if (userForCollaborator.getModelPermissionUsers() == null)
			userForCollaborator.setModelPermissionUsers(new ArrayList<>());


        userForCollaborator.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        collaboratorService.create(userForCollaborator);

		// User Member
        Member userForMember = new Member("member");
		userForMember.setPassword("123");
		// Role Member
		Role roleForMember = new Role();
		roleForMember.setAuthority(AuthoritiesConstants.MEMBER);
		roleForMember.setCreatedAt(LocalDateTime.now());
		Role roleForMemberSaved = roleService.create(roleForMember);
		RoleUser roleUserForMember = new RoleUser();
		roleUserForMember.setRole(roleForMemberSaved);
		if (userForMember.getRoleUsers() == null)
			userForMember.setRoleUsers(new ArrayList<>());

		userForMember.getRoleUsers().add(roleUserForMember);
		if (userForMember.getModelPermissionUsers() == null)
			userForMember.setModelPermissionUsers(new ArrayList<>());


        userForMember.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        memberService.create(userForMember);

		// User Influencer
        Influencer userForInfluencer = new Influencer("influencer");
		userForInfluencer.setPassword("123");
		// Role Influencer
		Role roleForInfluencer = new Role();
		roleForInfluencer.setAuthority(AuthoritiesConstants.INFLUENCER);
		roleForInfluencer.setCreatedAt(LocalDateTime.now());
		Role roleForInfluencerSaved = roleService.create(roleForInfluencer);
		RoleUser roleUserForInfluencer = new RoleUser();
		roleUserForInfluencer.setRole(roleForInfluencerSaved);
		if (userForInfluencer.getRoleUsers() == null)
			userForInfluencer.setRoleUsers(new ArrayList<>());

		userForInfluencer.getRoleUsers().add(roleUserForInfluencer);
		if (userForInfluencer.getModelPermissionUsers() == null)
			userForInfluencer.setModelPermissionUsers(new ArrayList<>());


        userForInfluencer.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        influencerService.create(userForInfluencer);

            }
        };
    }




    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("CategoryTemplate"));
        modelPermissions.add(new ModelPermission("PaimentCollaboratorState"));
        modelPermissions.add(new ModelPermission("InscriptionCollaborator"));
        modelPermissions.add(new ModelPermission("ProjectState"));
        modelPermissions.add(new ModelPermission("Influencer"));
        modelPermissions.add(new ModelPermission("Coupon"));
        modelPermissions.add(new ModelPermission("TypeTemplate"));
        modelPermissions.add(new ModelPermission("InscriptionMembreState"));
        modelPermissions.add(new ModelPermission("PaimentInfluencer"));
        modelPermissions.add(new ModelPermission("InscriptionCollaboratorState"));
        modelPermissions.add(new ModelPermission("DomainTemplate"));
        modelPermissions.add(new ModelPermission("Packaging"));
        modelPermissions.add(new ModelPermission("Member"));
        modelPermissions.add(new ModelPermission("InscriptionMembre"));
        modelPermissions.add(new ModelPermission("ProjectTemplate"));
        modelPermissions.add(new ModelPermission("InscriptionCollaboratorType"));
        modelPermissions.add(new ModelPermission("CategoryPackaging"));
        modelPermissions.add(new ModelPermission("LevelTemplate"));
        modelPermissions.add(new ModelPermission("Technology"));
        modelPermissions.add(new ModelPermission("Template"));
        modelPermissions.add(new ModelPermission("PaimentInfluencerState"));
        modelPermissions.add(new ModelPermission("PaimentCollaborator"));
        modelPermissions.add(new ModelPermission("Collaborator"));
        modelPermissions.add(new ModelPermission("CouponDetail"));
        modelPermissions.add(new ModelPermission("Project"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


}


