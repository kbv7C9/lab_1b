package com.university.application;

import com.university.domain.Payment;
import com.university.domain.PaymentRepository;
import org.junit.Test;
import org.junit.Before;
import org.mockito.*;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * Автор: Касьяненко Борис
 * Тесты для класса ApplicationService, в том числе для
 * методов удаления записей, идентификатор которых не является
 * числом Фибоначчи а также получения записей с четным идентификатором
 */
public class ApplicationServiceTest{

    @Mock
    private PaymentRepository repositoryMock;
    private ApplicationService testAppService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        testAppService = new ApplicationServiceImpl();
        testAppService.setDataSource(repositoryMock);
    }

    @Test
    public void testRemoveEntitiesWithNotFibId_normalMode(){
        List<Payment> paymentList = new ArrayList<Payment>();
        Payment pay = new Payment(); pay.setId(1); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1000"); paymentList.add(pay);
        pay = new Payment(); pay.setId(2); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1200"); paymentList.add(pay);
        pay = new Payment(); pay.setId(3); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1040"); paymentList.add(pay);
        pay = new Payment(); pay.setId(4); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("2230"); paymentList.add(pay);
        pay = new Payment(); pay.setId(5); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1400"); paymentList.add(pay);
        pay = new Payment(); pay.setId(6); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("900"); paymentList.add(pay);
        Mockito.when(repositoryMock.getPayments()).thenReturn(paymentList);
        testAppService.removeEntitiesWithNotFibId();
        Mockito.verify(repositoryMock).removePayment(paymentList.get(3));
        Mockito.verify(repositoryMock).removePayment(paymentList.get(5));
        Mockito.verify(repositoryMock, Mockito.times(2)).removePayment((Payment)Mockito.any());
    }

    @Test
    public void testRemoveEntitiesWithNotFibId_onlyFibEntities(){
        List<Payment> paymentList = new ArrayList<Payment>();
        Payment pay = new Payment(); pay.setId(1); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1000"); paymentList.add(pay);
        pay = new Payment(); pay.setId(2); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1200"); paymentList.add(pay);
        pay = new Payment(); pay.setId(3); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1040"); paymentList.add(pay);
        pay = new Payment(); pay.setId(5); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1400"); paymentList.add(pay);
        Mockito.when(repositoryMock.getPayments()).thenReturn(paymentList);
        testAppService.removeEntitiesWithNotFibId();
        Mockito.verify(repositoryMock, Mockito.never()).removePayment((Payment) Mockito.any());
    }

    @Test
    public void testRemoveEntitiesWithNotFibId_emptyList(){
        List<Payment> paymentList = new ArrayList<Payment>();
        Mockito.when(repositoryMock.getPayments()).thenReturn(paymentList);
        testAppService.removeEntitiesWithNotFibId();
        Mockito.verify(repositoryMock, Mockito.never()).removePayment((Payment) Mockito.any());
    }

    @Test
    public void testGetEntitiesWithEvenId_normalMode(){
        List<Payment> paymentList = new ArrayList<Payment>();
        Payment pay = new Payment(); pay.setId(1); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1000"); paymentList.add(pay);
        pay = new Payment(); pay.setId(2); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1200"); paymentList.add(pay);
        pay = new Payment(); pay.setId(3); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1040"); paymentList.add(pay);
        pay = new Payment(); pay.setId(4); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("2230"); paymentList.add(pay);
        pay = new Payment(); pay.setId(5); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1400"); paymentList.add(pay);
        pay = new Payment(); pay.setId(6); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("900"); paymentList.add(pay);
        List<Payment> expectedList = new ArrayList<Payment>();
        pay = new Payment(); pay.setId(2); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1200"); expectedList.add(pay);
        pay = new Payment(); pay.setId(4); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("2230"); expectedList.add(pay);
        pay = new Payment(); pay.setId(6); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("900"); expectedList.add(pay);
        Mockito.when(repositoryMock.getPayments()).thenReturn(paymentList);
        List<Payment> result = testAppService.getEntitiesWithEvenId();
        for (int cnt=0;cnt<expectedList.size()-1;cnt++){
            assertEquals(result.get(cnt).getId(), expectedList.get(cnt).getId());
        }
        assertEquals(expectedList.size(),result.size());
    }

    @Test
    public void testGetEntitiesWithEvenId_onlyNotEvenId(){
        List<Payment> paymentList = new ArrayList<Payment>();
        Payment pay = new Payment(); pay.setId(1); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1000"); paymentList.add(pay);
        pay = new Payment(); pay.setId(3); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1040"); paymentList.add(pay);
        pay = new Payment(); pay.setId(5); pay.setPayer("user1"); pay.setRecipient("user2"); pay.setNumber("1400"); paymentList.add(pay);
        List<Payment> expectedList = new ArrayList<Payment>();
        Mockito.when(repositoryMock.getPayments()).thenReturn(paymentList);
        List<Payment> testResult = testAppService.getEntitiesWithEvenId();
        assertArrayEquals(expectedList.toArray(), testResult.toArray());
    }

    @Test
    public void testGetEntitiesWithEvenId_emptyList(){
        List<Payment> expectedList = new ArrayList<Payment>();
        List<Payment> paymentList = new ArrayList<Payment>();
        Mockito.when(repositoryMock.getPayments()).thenReturn(paymentList);
        List<Payment> testResult = testAppService.getEntitiesWithEvenId();
        assertArrayEquals(expectedList.toArray(), testResult.toArray());
    }
}
