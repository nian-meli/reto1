package com.reto1.demo.Controller.Controller;

import com.reto1.demo.Exception.OrderNotFoundException;
import com.reto1.demo.Exception.UserException.SameIdException;
import com.reto1.demo.Exception.UserException.UserAlreadyFollowException;
import com.reto1.demo.Exception.UserException.UserIdNotFoundException;
import com.reto1.demo.Exception.UserException.UserNotFollowException;
import com.reto1.demo.Model.DTO.UserObjets.UserDTOCount;
import com.reto1.demo.Model.DTO.UserObjets.UserDTOFolloweds;
import com.reto1.demo.Model.DTO.UserObjets.UserDTOFollowers;
import com.reto1.demo.Model.DTO.UserObjets.UserRequest;
import com.reto1.demo.Service.IFollowService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class FollowController {

    @Autowired
    IFollowService iFollowService;

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    @ApiOperation("Follow user by id")
    @ApiResponses({ @ApiResponse(code=200, message = "The user was follow",
                    examples = @Example(value = {@ExampleProperty( mediaType = "*/*" , value = "Following to UserName")})),
                    @ApiResponse(code=400, message="The user wasnt follow, there")})
    public ResponseEntity<String> followOtherUser(@ApiParam(value = "User id that follow", example = "1235") @PathVariable int userId,
                                                  @ApiParam(value = "User id to follow",   example = "1570") @PathVariable int userIdToFollow)
            throws UserAlreadyFollowException, UserIdNotFoundException, SameIdException {
        String nameUser = iFollowService.followOtherUser(userId, userIdToFollow);
        return new ResponseEntity("Following to " + nameUser, HttpStatus.OK);
    }

    @ApiOperation("How many followers has a user")
    @ApiResponses({ @ApiResponse(code=200, message = "Ok"),
                    @ApiResponse(code=400, message="Bad request")})
    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity<UserDTOCount> countFollower(
            @ApiParam(value = "User id to get count followers") @PathVariable int userId) throws UserIdNotFoundException, UserNotFollowException {
        return new ResponseEntity<>(iFollowService.countFollowers(userId), HttpStatus.OK);
    }


    @GetMapping("/users/{userId}/followers/list")
    @ApiOperation("Followers user, Order ASC default value")
    @ApiResponses({ @ApiResponse(code=200, message = "Ok"),
                    @ApiResponse(code=400, message="Bad request")})
    public ResponseEntity<UserDTOFollowers> orderFollowerList(@ApiParam(value = "User id to get list followers",   example = "1570") @PathVariable int userId,
                                                              @RequestParam(required = false, defaultValue = "name_asc") String order)
                                                                throws UserNotFollowException, UserIdNotFoundException, OrderNotFoundException {
        return new ResponseEntity(iFollowService.orderListFollowers(order, userId), HttpStatus.OK);
    }


    @GetMapping("/users/{userId}/followed/list")
    @ApiOperation("Who user follow, Order ASC default value")
    @ApiResponses({ @ApiResponse(code=200, message = "Ok"),
                    @ApiResponse(code=400, message="Bad request")})
    public ResponseEntity<UserDTOFolloweds> listFolloweds(@ApiParam(value = "User id to get followed",   example = "1570") @PathVariable int userId,
                                                          @RequestParam(required = false,defaultValue = "name_asc") String order)
            throws UserIdNotFoundException, UserNotFollowException, OrderNotFoundException {
        return new ResponseEntity<>(iFollowService.orderListFolloweds(order, userId), HttpStatus.OK);
    }


    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    @ApiOperation("Unfollow user with id")
    @ApiResponses({ @ApiResponse(code=200, message = "Ok"),
                    @ApiResponse(code=400, message="Bad request")})
    public ResponseEntity<String>  unFollow(@ApiParam(value = "User id that unfollow",   example = "1235") @PathVariable int userId,
                                            @ApiParam(value = "User id to unfollow", example = "1570") @PathVariable int userIdToUnfollow)
            throws UserNotFollowException, UserIdNotFoundException {
        String userName = iFollowService.unFollow(userId, userIdToUnfollow);
        return new ResponseEntity("Has unfollow to "+userName,HttpStatus.OK);
    }

    @GetMapping("/users/create")
    @ApiOperation("Created user")
    @ApiResponse(code=200, message = "Ok")
    public ResponseEntity<String> create(@RequestBody UserRequest user){
        String name = user.getName()+" id: "+iFollowService.createUser(user);
        return new ResponseEntity<>("User created "+name,HttpStatus.ACCEPTED);
    }

}





