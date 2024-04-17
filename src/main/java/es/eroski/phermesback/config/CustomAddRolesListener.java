package es.eroski.phermesback.config;

import es.eroski.oinarri.library.ldaplogin.lib.event.AddRolesEvent;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Component
// @Slf4j
public class CustomAddRolesListener implements ApplicationListener<AddRolesEvent> {

    @Value("${eroski.stratio.permission.code}")
	private String stratioPermission;

    @Value("${eroski.stratio.role.code}")
	private String stratioCode;

    @Value("${eroski.stratio.role.name}")
	private String stratioName;

    @Value("${eroski.stratio.role.description}")
	private String stratioDescription;

    @Override
    public void onApplicationEvent(AddRolesEvent event) {
        /*
            This event allows to create system roles in Oinarri. System roles are roles that will be created on startup and can't be modified nor deleted.
            Create them only if the application has some role-related logic hardcoded, and it has to always have those roles created.

            To add system roles to Oinarri, call event.addRole inside this method, specifying the role code, name, its description, and the list of permissions (code field of the permission) that we want to set to the role.

            System roles can't be deleted using the API endpoints, if you want to remove an already created system role, remove the call to event.addRole and Oinarri will remove the role and its relationships on startup.

            There is an example commented below of how to add a system role with the code "role_code", with the name "Name of the role", with the description "Test role" and with the permission "example_permission" to the application

            As a good practice the role and permission "code" should be declared publicly as a constant somewhere to reference it in several places, including here
         */


        /*
        List<String> rolePermissions = new ArrayList<>();
        rolePermissions.add("example_permission");

        event.addRole("role_code", "Name of the role", "Description of the role", rolePermissions);
         */

        List<String> rolePermissions = new ArrayList<>();
        rolePermissions.add(stratioPermission);
        event.addRole(stratioCode, stratioName, stratioDescription, rolePermissions);

    }


}
