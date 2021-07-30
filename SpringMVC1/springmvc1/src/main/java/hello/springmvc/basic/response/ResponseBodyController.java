package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@ResponseBody 클래스에 걸면 모든 메소드에 장작됨. 물론 이 애노테이션이 필요없는 메소드는 거름
//@Controller와 @ResponseBody를 합친게 @RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * ResponseEntity는 HttpEntity를 상속받았는데, HttpEntity는 HTTP 메시지의 헤더, 바디 정보를 가지고 있음
     * ResponseEntity는 여기에 더해 HTTP 응답 코드를 설정할 수 있음
     * @return
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV1() throws IOException{
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @ResponseBody를 사용하면 VIEW를 사용하지 않고, HTTP 메시지 컨버터를 통해서 메시지를 직접 입력할 수 있음
     * ResponseEntity도 동일한 방식으로 동작
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }

    /**
     * ResponseEntity를 반환. HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어 반환
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData,HttpStatus.OK);
    }

    /**
     * 위 메소드와 같은 코드
     * 위 메소드는 HttpStatus를 return에 지정
     * 아래 메소드는 HttpStatus를 애노테이션으로 지정
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(29);
        return helloData;
    }
}
