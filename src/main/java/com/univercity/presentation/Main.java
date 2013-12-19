package com.univercity.presentation;

import com.univercity.application.ApplicationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: KVB
 * Date: 15.12.13
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main() throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/spring.xml");
        ApplicationService service = (ApplicationService)applicationContext.getBean("ApplicationServiceImpl");
        service.getEntitiesWithEvenId();
        service.removeEntitiesWithNotFibId();
    }
}
