package com.reto1.demo.Controller.Controller;

import com.reto1.demo.Exception.UserException.SameIdException;
import com.reto1.demo.Exception.UserException.UserAlreadyFollowException;
import com.reto1.demo.Exception.UserException.UserIdNotFoundException;
import com.reto1.demo.Service.IFollowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FollowControllerTest {

    @Mock
    IFollowService iFollowService;

    @InjectMocks
    FollowController followController;

    @Test
    void followOtherUser() throws UserAlreadyFollowException, UserIdNotFoundException, SameIdException {

        //Arrange
        String getUserName = "Mega SAS";
        String outputExpected = "Following to Mega SAS";
        HttpStatus statusExpected = HttpStatus.OK;
        when(iFollowService.followOtherUser(1235, 1570)).thenReturn(getUserName);

        //Act

        ResponseEntity<String> response = followController.followOtherUser(1235, 1570);
        String output = response.getBody();
        HttpStatus outputstatus = response.getStatusCode();

        //Assert
        assertEquals(outputExpected, output);
        assertEquals(statusExpected, outputstatus);
    }
}