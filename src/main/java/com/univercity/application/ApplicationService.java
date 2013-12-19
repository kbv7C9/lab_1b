package com.univercity.application;

import java.sql.SQLException;
import java.util.List;
import com.univercity.domain.Payment;

/**
 * Created with IntelliJ IDEA.
 * User: Олег
 * Date: 15.12.13
 * Time: 1:31
 * To change this template use File | Settings | File Templates.
 */

public interface ApplicationService {
    void removeEntitiesWithNotFibId() throws SQLException;  // Удаление всех записей, у которых ID не есть числом Фибоначчи
    List<Payment> getEntitiesWithEvenId() throws SQLException;  // Удаление всех записей с четными ID
}
