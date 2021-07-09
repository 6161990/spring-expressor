package com.kh.test01.presentation;

import com.kh.test01.application.form.BoardForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/board")
    public ModelAndView viewBoard(ModelAndView modelAndView){
        modelAndView.setViewName("board");
        //board라고 하는 설정 경로를 찾게됨
        modelAndView.addObject("boardForm", new BoardForm());
        // addObject 타임리프에 데이터를 전달하는 역할
        return modelAndView;
    }


    @PostMapping("/board")
    public ModelAndView postBoard(@Validated @ModelAttribute BoardForm board,
                            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/board");
            modelAndView.addObject("boardForm", board);
            return modelAndView;   //modelAndView.getViewName 으로하면 밑에서 ModelAndView 인스턴스 생성 필요없음
        }
        return new ModelAndView("redirect:/board");

        //PRG 패턴에서 REDIRECT 해서 mapping된 '/board'로 넘어가도록.


        // 📌 PRG 패턴 POST REDIRECT GET
        //전송 버튼을 두 번 누르면 두 번 전송이 되는데 이를 제약할 수 있는 패턴.
        //이중 입력을 방지한다.
        //POST로 한번 던지면 GET가게끔 만들어(redirect) 다시 POST에 있는 메소드나 비즈니스 로직이 두 번 실행되지 않도록 함.
        //GET에는 아무 것도 없기 때문에 아무런 동작도 일어나지 않는다.
        //-> DATA가 다시 저장되는 것을 방지
        //
        //브라우저에 캐시가 남아있거나, 전송이 계속 전송될 수 있는데 전혀 상관없는 페이지로 보내버려 중복을 막는 방식.

        //실제로 전송버튼을 누르면 network에 상태값이 302이다. 임시로 redirect를 보냈다는 의미.
        //data 전송 후 post에서 302로 redirect를 하게되면, 다음 board는 get으로 받아 끝내버린다.
    }

}
