package es.eroski.phermesback.config;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import es.eroski.oinarri.library.ldaplogin.lib.event.AddPermissionsEvent;
import org.springframework.beans.factory.annotation.Value;

@Component
// @Slf4j
public class CustomAddPermissionsListener implements ApplicationListener<AddPermissionsEvent> {

    @Value("${eroski.stratio.permission.code}")
	private String stratioCode;

    @Value("${eroski.stratio.permission.name}")
	private String stratioName;

    @Value("${eroski.stratio.permission.description}")
	private String stratioDescription;

    @Override
    public void onApplicationEvent(AddPermissionsEvent event) {
        /*
            To add permissions to Oinarri, call event.addPermission inside this method, specifying the permission code, name and its description.
            To remove an existing permission created in this application, just remove the event.addPermission call and Oinarri will handle its deletion on startup.
            Oinarri libraries will add permissions to the application. Those permissions should never be modified since they are internally used by the libraries

            There is an example commented below of how to add a permission with the name "permission_name" with the description "Description of the permission" to the application

            As a good practice the permission code should be declared publicly as a constant somewhere to reference it in several places, including here
         */

        event.addPermission(stratioCode, stratioName, stratioDescription);
    }


}
