package es.eroski.phermesback.config;

import es.eroski.oinarri.library.ldaplogin.lib.event.AddUsersEvent;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;


@Component
// @Slf4j
public class CustomAddUsersListener implements ApplicationListener<AddUsersEvent> {

    @Value("${eroski.stratio.user.username}")
	private String stratioUsername;

    @Value("${eroski.stratio.user.password}")
	private String stratioPassword;

    @Value("${eroski.stratio.user.email}")
	private String stratioEmail;

    @Value("${eroski.stratio.user.name}")
	private String stratioName;

    @Value("${eroski.stratio.role.code}")
	private String stratioRole;

    @Override
    public void onApplicationEvent(AddUsersEvent event) {
        /*
            This event allows to create system users in Oinarri. User roles are users that will be created on startup and can't be modified nor deleted.
            Create them only if the application has some user-related logic hardcoded or you want to have an specific user always available.

            To add system users to Oinarri, call event.addUser inside this method, specifying the user name, surname, username, email, password, ldapflag, and the list of roles (code field of the role) that we want to set to the user.

            System users can't be deleted using the API endpoints, if you want to remove an already created system role, remove the call to event.addRole and Oinarri will remove the role and its relationships on startup.

            There is an example commented below of how to add a system user

            As a good practice the username and its roles "code" should be declared publicly as a constant somewhere to reference it in several places, including here.
            Keep in mind that the users password shouldn't be harcoded: define it in the yml properties of the project, and remember to have it declared as an environmental variable, at least, for productive environments.
         */

        /*
        List<String> userRoles = new ArrayList<>();
        userRoles.add("example_role");

        event.addUser("name", "surname", "username", "email@oftheuser.com", "secure_password", 0, userRoles);
         */

        List<String> userRoles = new ArrayList<>();
        userRoles.add(stratioRole);
        event.addUser(stratioName, stratioName, stratioUsername, stratioEmail, stratioPassword, 0, userRoles);
    }
}
