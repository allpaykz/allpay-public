package kz.allpay.soap.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by aigerim on 6/7/17.
 */
@WebListener
public class LifeCycleListener implements ServletContextListener{

    private static final Log logger = LogFactory.getLog(LifeCycleListener.class.getName());

    public void contextInitialized(ServletContextEvent event) {
        try {
            DataBase.readFromStorageFile();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            DataBase.writeToStorageFile();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
