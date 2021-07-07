package com.kh.test01.presentation;

import com.kh.test01.application.form.BoardForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public String postBoard(@ModelAttribute BoardForm board){
        return "board";
    }

}
