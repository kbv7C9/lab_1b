package com.university.application;

import java.util.ArrayList;
import java.util.List;
import com.university.domain.Payment;
import com.university.domain.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Автор: Касьяненко Борис
 * Класс, реализующий интерфейс ApplicationService и соответствующие
 * методы: удаления записей с идентификатором, который не является
 * числом Фибоначчи
 */

@Service(value = "ApplicationService")
@Scope("singleton")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    PaymentRepository repository;

    /**
     * Метод, который удаляет записи, идентификатор которых
     * не является числом Фибоначчи
     */
    @Override
    public void removeEntitiesWithNotFibId(){

        List <Payment> list = repository.getPayments();

        ArrayList fibNumbers;
        boolean flag = false;

        if (list.isEmpty()==true){
            fibNumbers = new ArrayList();
        } else{
            fibNumbers = getFibonacciNumbers(list.get(list.size()-1).getId());
        }

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

    /**
     * Метод, который возвращает записи с четным идентификатором
     * @return возвращает список записей с четным идентификатором
     */
    @Override
    public List<Payment> getEntitiesWithEvenId(){

        List<Payment> list = repository.getPayments();
        List<Payment> listWithEvenId = new ArrayList<Payment>();

        for (Payment payment : list){
            if (payment.getId()%2 == 0){
                listWithEvenId.add(payment);
            }
        }
        return listWithEvenId;
    }

    /**
     * Метод, который вычисляет числа Фибоначчи до заданного числа
     * @param lastNumber - параметр, задающий число, до которого
     *                   следует считать числа Фибоначчи
     * @return возвращает массив чисел Фибоначчи до заданого числа
     */
    private ArrayList getFibonacciNumbers(long lastNumber){

        int tmpFib,tmpFib_1,tmpFib_2;
        tmpFib_2=1;
        tmpFib_1=0;
        ArrayList fibNumbers = new ArrayList();

        for(int cnt=1; cnt<lastNumber; cnt++){
            tmpFib=tmpFib_1+tmpFib_2;
            fibNumbers.add(tmpFib);
            tmpFib_1=tmpFib_2;
            tmpFib_2=tmpFib;
        }
        return fibNumbers;
    }

    public void setDataSource(PaymentRepository repo){
        this.repository = repo;
    }
}
