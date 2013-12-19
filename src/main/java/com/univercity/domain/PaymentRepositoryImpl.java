package com.univercity.domain;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.univercity.util.HibernateUtil;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: KVB
 * Date: 14.12.13
 * Time: 20:15
 * To change this template use File | Settings | File Templates.
 */

@Repository(value = "PaymentRepository")
public class PaymentRepositoryImpl implements PaymentRepository{
    @Override
    public Payment getEntity(long id) throws SQLException {
        Session session = null;
        Payment payment = null;
        try {
                session = HibernateUtil.getSessionFactory().openSession();
                payment = (Payment) session.load(Payment.class, id);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        return payment;
    }

    @Override
    public void removePayment(Payment payment) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Payment> getPayments() throws SQLException {
        Session session = null;
        List<Payment> payments = new ArrayList<Payment>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            payments = session.createCriteria(Payment.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return payments;
    }
}
