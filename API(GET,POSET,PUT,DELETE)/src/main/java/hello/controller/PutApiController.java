package hello.controller;


import hello.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/put-api")
public class PutApiController {

    @PutMapping("put")
    public void put(@RequestBody PutRequestDto requestDto) {
        System.out.println(requestDto);
    }

    //resoponse
    @PutMapping("put2")
    public PutRequestDto put2(@RequestBody PutRequestDto requestDto) {
        System.out.println(requestDto);  //json naming 룰에 따라서 스네이크 케이스로 자동 변환됨
        return requestDto;
    }

    //PathVariable 받기
    @PutMapping("put3/{userId}")
    public PutRequestDto put3(@RequestBody PutRequestDto requestDto, @PathVariable(name = "userId") Long id) {
        System.out.println(id);
        return requestDto;
    }

}
