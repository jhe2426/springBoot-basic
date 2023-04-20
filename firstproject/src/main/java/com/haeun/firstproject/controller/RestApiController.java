package com.haeun.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// Rest API를 위한 Controller임을 명시해주는 어노테이션
// @Controller + @ResponseBody = @RestController
// Response는 HTML을 제외한 MIME type을 반환
@RestController
// URL path 패턴을 지정해서 해당 패턴이면 지정한 클래스에서 처리하도록 함
@RequestMapping("api")
public class RestApiController {
  
    @RequestMapping(method = RequestMethod.GET, value = "hello2") //이러한 방식은 가독성이 떨어져서 잘 사용하지 않는다고 함
    public String hello2() {
        return "hello2";
    }

    // GET Method @GetMapping
    // GET Method : 클라이언트가 서버에게 데이터를 받기 위한 요청의 Method
    // @RequestMapping(method =  RequestMethod.GET, value = "get-method")
    @GetMapping("get-method") // value속성은 생략 가능
    public String getMethod() {
        return "Response of Get Request";
    }

    // POST Method @PostMapping
    // POST Method : 클라이언트가 서버에 데이터를 작성하기 위한 요청의 Method
    // @RequestMapping(method =  RequestMethod.POST, value = "post-method")
    //* Method를 잘 못 입력하면 405에러가 발생하게 됨(405 : 요청한 리소스를 서버가 인식하지만 사용할 수 없음)
    @PostMapping("post-method")
    public String postMethod() {
        return "Response of Post Request";
    }

    // PATCH Method @PatchMapping
    // PATCH Method : 클라이언트가 서버에 데이터를 일부만 수정하기 위한 요청의 Method
    // @RequestMapping(method =  RequestMethod.PATCH, value = "patch-method")
    //* Patch와 Put은 Post와 같이 RequestBody에 담겨서 감
    @PatchMapping("patch-method")
    public String patchMethod() {
        return "Response of Patch Request";
    }    

    // DELETE Method @DeleteMapping
    // DELETE Method : 클라이언트가 서버에 데이터를 삭제하기 위한 요청의 Method
    // @RequestMapping(method =  RequestMethod.DELETE, value = "delete-method")
    //* URL 부분에(Path 부분에) 띄어쓰기는 -를 작성해서 표현 함
    //* Delete는 URL에 RequestHeader에 담겨서 감 스프링에서는 DeleteMapping을 사용할 때 RequestBody에 데이터를 담으면 담지를 못 함
    @DeleteMapping("delete-method")
    public String deleteMethod() {
        return "Response of Delete Request";
    }

    // PathVariable()로 GET, DELETE Method에서 데이터 받기 
    // PathVariable : 리소스에 지정한 패턴에 맞춰서 요청의 URL을 지정한다면 패턴에 맞춰서 데이터를 받아오는 형식
    //* PathVariable을 사용할 때는 입력 받는 데이터의 타입이 아주 중요하다. 타입이 맞지 않는 데이터가 입력되면 400오류가 발생하게 됨 (400 : 요청시의 문법상 오류로 요청을 이해하지 못함)
    //* PathVariable을 사용하면 필수로 데이터 값이 입력되어야 URL을 인식할 수 있음
    @GetMapping({"path-variable/{data1}", "path-variable/{data1}/{data2}"})//* Path로 입력 받는 데이터 개수를 달리 받는 방법(꼭 입력 값이 들어오지 않을 수도 있는 변수에는 required=false 속성을 지정해줘야함)
    public String pathVariable(
        @PathVariable("data1") String dataA, //* value는 생략 가능 value의 값은 Path변수명과 같아야 함 변수명은 Path변수명과 같지 않아도 됨  
        @PathVariable(value = "data2", required = false) String dataB //* required = false 항상 데이터 값을 입력받지 않아도 된다.
    ) {
        return dataA + dataB + " 데이터를 입력받았습니다.";
    }



}
