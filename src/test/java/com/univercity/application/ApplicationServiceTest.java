package com.univercity.application;

import com.univercity.domain.Payment;
import com.univercity.domain.PaymentRepository;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Acer
 * Date: 18.12.13
 * Time: 1:42
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationServiceTest extends TestCase {

    @Autowired
    ApplicationService testAppService;
    private List<Payment> testPayList = new ArrayList();

    @Before
    public void prepareToTesting(){
        List<Payment> testPayList = new ArrayList();
        Payment pay1 = new Payment();
        pay1.setId(1);
        pay1.setNumber(2000);
        pay1.setPayer("user1");
        pay1.setRecipient("user2");
        testPayList.add(1,pay1);

        Payment pay2 = new Payment();
        pay1.setId(1);
        pay1.setNumber(2000);
        pay1.setPayer("user1");
        pay1.setRecipient("user2");
        testPayList.add(2,pay2);

        Payment pay3 = new Payment();
        pay1.setId(1);
        pay1.setNumber(2000);
        pay1.setPayer("user1");
        pay1.setRecipient("user2");
        testPayList.add(3,pay3);

        Payment pay4 = new Payment();
        pay1.setId(1);
        pay1.setNumber(2000);
        pay1.setPayer("user1");
        pay1.setRecipient("user2");
        testPayList.add(4,pay4);

        Payment pay5 = new Payment();
        pay1.setId(1);
        pay1.setNumber(2000);
        pay1.setPayer("user1");
        pay1.setRecipient("user2");
        testPayList.add(5,pay5);

        Payment pay6 = new Payment();
        pay1.setId(1);
        pay1.setNumber(2000);
        pay1.setPayer("user1");
        pay1.setRecipient("user2");
        testPayList.add(6,pay6);
    }

    @Test
    public void testRemoveEntitiesWithNotFibId_normalMode() throws SQLException {
        PaymentRepository repositoryMock = Mockito.mock(PaymentRepository.class);
        Mockito.when(repositoryMock.getPayments()).thenReturn(testPayList);
        testAppService.removeEntitiesWithNotFibId();
        Mockito.verify(repositoryMock).removePayment(testPayList.get(4));
        Mockito.verify(repositoryMock).removePayment(testPayList.get(6));
        Mockito.verify(repositoryMock, Mockito.times(2));
    }

    @Test
    public void testRemoveEntitiesWithNotFibId_onlyFibEntities() throws SQLException {
        PaymentRepository repositoryMock = Mockito.mock(PaymentRepository.class);
        List<Payment> listWithFibId = testPayList;
        listWithFibId.remove(4);
        listWithFibId.remove(6);
        Mockito.when(repositoryMock.getPayments()).thenReturn(listWithFibId);
        testAppService.removeEntitiesWithNotFibId();
        Mockito.verify(repositoryMock, Mockito.never());
    }

    @Test
    public void testRemoveEntitiesWithNotFibId_emptyList() throws SQLException {
        PaymentRepository repositoryMock = Mockito.mock(PaymentRepository.class);
        List<Payment> emptyList = new ArrayList<Payment>();
        Mockito.when(repositoryMock.getPayments()).thenReturn(emptyList);
        testAppService.removeEntitiesWithNotFibId();
        Mockito.verify(repositoryMock, Mockito.never());
    }

    @Test
    public void testGetEntitiesWithEvenId_normalMode() throws SQLException {
        PaymentRepository repositoryMock = Mockito.mock(PaymentRepository.class);
        List<Payment> expectedList = testPayList;
        expectedList.remove(1);
        expectedList.remove(3);
        expectedList.remove(5);
        Mockito.when(repositoryMock.getPayments()).thenReturn(testPayList);
        List<Payment> testResult = testAppService.getEntitiesWithEvenId();
        assertSame(expectedList,testResult);
    }

    @Test
    public void testGetEntitiesWithEvenId_onlyNotEvenId() throws SQLException {
        PaymentRepository repositoryMock = Mockito.mock(PaymentRepository.class);
        List<Payment> expectedList = new ArrayList<Payment>();
        List<Payment> testListWithNoEvenId = testPayList;
        testListWithNoEvenId.remove(2);
        testListWithNoEvenId.remove(4);
        testListWithNoEvenId.remove(6);
        Mockito.when(repositoryMock.getPayments()).thenReturn(testListWithNoEvenId);
        List<Payment> testResult = testAppService.getEntitiesWithEvenId();
        assertSame(expectedList,testResult);
    }

    @Test
    public void testGetEntitiesWithEvenId_emptyList() throws SQLException {
        PaymentRepository repositoryMock = Mockito.mock(PaymentRepository.class);
        List<Payment> expectedList = new ArrayList<Payment>();
        List<Payment> emptyList = new ArrayList<Payment>();
        Mockito.when(repositoryMock.getPayments()).thenReturn(emptyList);
        List<Payment> testResult = testAppService.getEntitiesWithEvenId();
        assertSame(expectedList,testResult);
    }
}
