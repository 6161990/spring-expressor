package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 Controller"}) //해당 api controller에 대한 태그를 달아 정보 제공
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/plus/{x}")
    public int plus(
                    @ApiParam(value = "x값")
                    @PathVariable int x,

                    @ApiParam(value = "y값")
                    @RequestParam int y){
        return x+y;
    }

    //하나씩 받아주는 방법도 있지만 object 형태로 받을 수도 있음
    @ApiImplicitParams({
                    @ApiImplicitParam(name="x", value = "x값", required = true, dataType = "int", paramType = "path"),
                    @ApiImplicitParam(name="y", value = "y값", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/plus2/{x}")
    public int plus2(@PathVariable int x, @RequestParam int y){
        return x+y;
    }




    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일 때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드")
    @GetMapping("/user")
    public UserRes user(UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }
}
