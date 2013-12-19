package com.univercity.application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.univercity.domain.Payment;
import com.univercity.domain.PaymentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Олег
 * Date: 15.12.13
 * Time: 9:01
 * To change this template use File | Settings | File Templates.
 */

@Service(value = "ApplicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Resource(name = "paymentRepository")
    private PaymentRepository repository;

    @Override
    public void removeEntitiesWithNotFibId() throws SQLException {

        List <Payment> list = repository.getPayments();
        ArrayList fibNumbers = new ArrayList();
        boolean flag = false;

        fibNumbers = getFibonacciNumbers(list.size());

        for(Payment payment : list){
            for (Object num : fibNumbers){
                if(payment.getId() == Integer.parseInt(num.toString())){
                    flag = true;
                }
            }
            if (flag == false){
                repository.removePayment(payment);
            }
            flag = false;
        }
    }

    @Override
    public List <Payment> getEntitiesWithEvenId() throws SQLException {

        List <Payment> list = repository.getPayments();
        List <Payment> listWithEvenId = repository.getPayments();

        for (Payment payment : list){
            if (payment.getId()%2 == 1){
                listWithEvenId.remove(payment);
            }
        }
        return listWithEvenId;
    }

    private ArrayList getFibonacciNumbers(int lastNumber){

        int tmpFib,tmpFib_1,tmpFib_2;
        tmpFib_1=1;
        tmpFib_2=0;
        ArrayList fibNumbers = new ArrayList();

        for(int cnt=1; cnt<lastNumber; cnt++){
            tmpFib=tmpFib_1+tmpFib_2;
            fibNumbers.add(tmpFib);
            tmpFib_2=tmpFib_1;
            tmpFib_1=tmpFib;
        }
        return fibNumbers;
    }
}
