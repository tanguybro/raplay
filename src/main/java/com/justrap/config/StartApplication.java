package com.justrap.config;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class StartApplication implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        System.out.println("************ Starting Raplay ************");
        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(StartApplication.class);
        webCtx.setServletContext(context);
        ServletRegistration.Dynamic servlet = context.addServlet("springapp", new DispatcherServlet(webCtx));
        servlet.setLoadOnStartup(1);
        try {
			initFirebase(context);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void initFirebase(ServletContext context) throws IOException { 
    	System.out.println("\n"+context.getRealPath("/WEB-INF/serviceAccountKey.json")+"\n"+"\n");
        FileInputStream serviceAccount = new FileInputStream(context.getRealPath("/WEB-INF/serviceAccountKey.json"));
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		  .build();
		FirebaseApp.initializeApp(options);

    }

}