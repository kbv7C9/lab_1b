package com.university.application;

import java.util.List;
import com.university.domain.Payment;
import com.university.domain.PaymentRepository;

/**
 * Автор: Касьяненко Борис
 * Интерфейс, который имеет методы удаления записей,
 * идентификатор которых не есть числом Фибоначчи и
 * метод получения всех записей с четными идентификаторами
 */

public interface ApplicationService {
    /**
     * Метод, который позволяет удалить все записи,
     * у которых ID не есть числом Фибоначчи
     */
    void removeEntitiesWithNotFibId();

    /**
     * Метод, позволяющий получить все записи с четными идентификаторами
     * @return возвращает список, содержащий записи, которые имеют
     * четные идентификаторы
     */
    List<Payment> getEntitiesWithEvenId();
    void setDataSource(PaymentRepository repo);
}
