package com.university.presentation;

import com.university.application.ApplicationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Автор: Касьяненко Борис
 * Главный класс программы, которая работает с базой данных
 * и имеет функцию вывода записей с четным идентификатором и
 * удаления записей, идентификатор которых не является числом Фибоначчи
 */
public class Main {

    public static void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
        ApplicationService appService = (ApplicationService)applicationContext.getBean("ApplicationService");
        //appService.getEntitiesWithEvenId();
        //appService.removeEntitiesWithNotFibId();
    }
}
