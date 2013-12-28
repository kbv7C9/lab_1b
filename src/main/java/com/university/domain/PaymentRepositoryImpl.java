package com.university.domain;

import org.hibernate.HibernateException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.university.util.HibernateUtil;

/**
 * Автор: Касьяненко Борис
 * Класс, который реализует интерфейс доступа к базе данных
 */

@Repository(value = "PaymentRepository")
@Scope("singleton")
public class PaymentRepositoryImpl implements PaymentRepository{

    /**
     * Метод, позволяющий получить запись по заданному идентификатору
     * @param id - идентификатор записи
     * @return объект типа Payment, который представляет
     * собой соответствующую запись
     */
    public Payment getEntity(long id){
        Session session = null;
        Payment payment = null;
        try {
                session = HibernateUtil.getSessionFactory().openSession();
                payment = (Payment) session.load(Payment.class, id);
            } catch (HibernateException e) {
                System.out.print("I/O Error!");
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        return payment;
    }

    /**
     * Метод, который удаляет указанную запись
     * @param payment - запись, подлежащая удалению
     */
    @Override
    public void removePayment(Payment payment){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(payment);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.print("I/O Error!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Метод, позволяющий получить все записи из базы данных
     * @return возвращает список объектов, представляющих собой
     * все записи из базы данных
     */
    @Override
    public List<Payment> getPayments(){
        Session session = null;
        List<Payment> payments = new ArrayList<Payment>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            payments = session.createCriteria(Payment.class).list();
        } catch (HibernateException e) {
            System.out.print("I/O Error!");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return payments;
    }
}
