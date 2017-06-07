package kz.allpay.soap.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by aigerim on 6/7/17.
 */
@WebListener
public class LifeCycleListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent event) {
        try {
            DataBase.readFromStorageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            DataBase.writeToStorageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
