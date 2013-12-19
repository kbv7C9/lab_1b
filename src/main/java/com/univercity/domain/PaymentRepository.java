package com.univercity.domain;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KVB
 * Date: 14.12.13
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentRepository {

    Payment getEntity(long id) throws SQLException;
    //void editPayment(Payment updatedEntity);
    void removePayment(Payment payment) throws SQLException;
    List <Payment> getPayments() throws SQLException;

}
