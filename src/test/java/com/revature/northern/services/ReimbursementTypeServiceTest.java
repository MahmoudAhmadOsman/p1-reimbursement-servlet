package com.revature.northern.services;

import com.revature.northern.daos.ReimbursementTypeDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ReimbursementTypeServiceTest {
    private ReimbursementTypeService sut;
    private final ReimbursementTypeDAO mockReimbursementTypeDAO = mock(ReimbursementTypeDAO.class);

    @Before
    public void setup() {
        sut = new ReimbursementTypeService(mockReimbursementTypeDAO);
    }

    @Test
    public void test_isValid_type_givenCorrectType() {
        //A1 - Arrange
        String validType = "CAR RENTAL EXPENSES";
        //A2 - Act
        boolean flag = sut.isValidType(validType);
        //A3 - Assert
        Assert.assertTrue(flag);
    }
}