package com.revature.northern.services;


import com.revature.northern.daos.UserRoleDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;


public class UserRoleServiceTest {

    private UserRoleService sut;
    private final UserRoleDAO mockUserRoleDAO = mock(UserRoleDAO.class);


    @Before
    public void setup() {
        sut = new UserRoleService(mockUserRoleDAO);
    }


    @Test
    public void test_isValidRoleId_givenCorrectRoleId() {

        //A1- 1. Arrange
        String validRoleId = "993b60a8-0ab7-49ef-975f-01100e27cabc";

        //Act
        boolean flag = sut.isValidRole(validRoleId);

        //Assert
        Assert.assertTrue(flag);

    }

    @Test
    public void test_isValidRole_givenCorrectRole() {
        String validRole = "EMPLOYEE";

        boolean flag = sut.isValidRole(validRole);
        Assert.assertTrue(flag);
    }

}