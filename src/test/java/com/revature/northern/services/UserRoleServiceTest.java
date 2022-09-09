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
        String validRoleId = "1234667744665";

        //Act
        boolean flag = sut.isValidRole(validRoleId);

        //Assert
        Assert.assertTrue(flag);

    }



}