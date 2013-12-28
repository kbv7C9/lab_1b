package com.university.domain;

import java.util.List;

/**
 * Автор: Касьяненко Борис
 * Ниже представлен интерфейс, который имеет методы для
 * работы с базой данных, а именно: удаление записи с
 * заданным идентификатором, получение списка всех записей,
 * получение записи с заданным идентификатором
 */
public interface PaymentRepository {
    //Payment getEntity(long id) throws SQLException;
    //void editPayment(Payment updatedEntity);
    void removePayment(Payment payment);
    List <Payment> getPayments();
}
