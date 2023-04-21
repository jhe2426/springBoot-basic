package com.haeun.firstproject.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.haeun.firstproject.dto.request.ExampleDto;
import com.haeun.firstproject.dto.response.ExampleResponseDto;


class ParamDto {
    String data1;
    String data2;

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData1() {
       return data1;
    }

    public String getData2() {
        return data2;
    }
}

//* api : 다른 앱플리케이션과 우리 앱플리케이션과의 약속
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

    // @PathVariable()로 GET, DELETE Method에서 데이터 받기 
    // @PathVariable : 리소스에 지정한 패턴에 맞춰서 요청의 URL을 지정한다면 패턴에 맞춰서 데이터를 받아오는 형식
    //* @PathVariable을 사용할 때는 입력 받는 데이터의 타입이 아주 중요하다. 타입이 맞지 않는 데이터가 입력되면 400오류가 발생하게 됨 (400 : 요청시의 문법상 오류로 요청을 이해하지 못함)
    //* @PathVariable을 사용하면 필수로 데이터 값이 입력되어야 URL을 인식할 수 있음
    //* @PathVariable은 path의 변수의 순서로 해당 값을 구분함
    @GetMapping({"path-variable/{data1}", "path-variable/{data1}/{data2}"})//* Path로 입력 받는 데이터 개수를 달리 받는 방법(꼭 입력 값이 들어오지 않을 수도 있는 변수에는 required=false 속성을 지정해줘야함)
    public String pathVariable(
        @PathVariable("data1") String dataA, //* value는 생략 가능 value의 값은 Path변수명과 같아야 함 변수명은 Path변수명과 같지 않아도 됨  
        @PathVariable(value = "data2", required = false) String dataB //* required = false 항상 데이터 값을 입력받지 않아도 된다.
    ) {
        return dataA + dataB + " 데이터를 입력받았습니다.";
    }

    // @RequestParam로 GET, DELETE Method에서 데이터 받기
    // @RequestParam : 완전 path  뒤에 ?name=value&name=value[&...] 형식에 맞춰 name에 해당하는 value를 받아오는 형식
    //* '?'뒤에는 path로 인식하지 않고 데이터로 인식을 함
    //* 요청시 name의 이름을 잘못 작성하면 400오류가 발생 됨
    @GetMapping("request-param") 
    public String requestParam(
        @RequestParam String data1,
        @RequestParam String data2
    ) {
        return data1 + data2 + " 데이터를 입력받았습니다.";
    }
    //* key:value로 넘어 오는 형태는 오브젝트(객체)라서 객체로 받을 수가 있음
    //* URL에 데이터를 입력하는데에는 길이의 제한이 있으므로 이런식을 잘 사용하지는 않는다고 함
    // @GetMapping("request-param") 
    // public String requestParam(ParamDto dto) {
    //     return dto.getData1 + dto.getData2 + " 데이터를 입력받았습니다.";
    // }

    // @RequestBody로 POST, PUT, PATCH Method에서 데이터 받기
    // @RequestBody : RequestBody에 있는 데이터를 받기 위한 어노테이션
    //* form-data 형식의 body 데이터는 객체 형태로 데이터를 보냄 그래서 받을 때 객체형으로 받아야 함
    //* JSON의 형태는 {"키":"값"} 문자열이라서  String타입으로도 받을 수 있다.
    @PostMapping("request-body")
    public String requestBody(
        //@RequestBody String data
        @RequestBody ParamDto dto
    ) {
        return dto.getData1() + dto.getData2() + " 데이터를 받았습니다.";
    }

    @PostMapping("request-body2")
    public ParamDto requestBody2(
        //@RequestBody String data
        @RequestBody ParamDto dto
    ) {
        return dto;
    }

    // ResponseEntity
    // Response의 헤더, 응답 코드, 상태 및 데이터를 포함하여 반환할 수 있도록 해주는 클래스이다. 
    // 데이터 뿐만 아니라 헤더, 응답 코드, 상태를 포함하여 전송할 수 있다는 장점 때문에 REST API에서 유용하게 사용된
    @PostMapping("request-body3")
    public ResponseEntity<ParamDto> requestBody3(
        //@RequestBody String data
        @RequestBody ParamDto dto
    ) {
        return ResponseEntity.status(408).body(dto);
    }


    @PostMapping("lombok")
    public String lombok(@RequestBody ExampleDto requestBody) {
        return requestBody.toString();
    }

    @PostMapping("lombok2")
    public ExampleResponseDto lombok2(@Valid @RequestBody ExampleDto requestBody) {
        String data1 = requestBody.getParameter1();
        String data2 = requestBody.getParameter2();
        String data3 = requestBody.getParameter3();

        //ExampleResponseDto responseData = new ExampleResponseDto(data1, data2, data3);

        //builder 인스턴스를 생성해주는 메서드
        ExampleResponseDto responseData = 
            ExampleResponseDto.builder().data1(data1).build();

        return responseData; //여기 리턴해주는 것이 responseData클래스의 변수를 가져와야지 리턴을 해줄 수 있는 거 그래서 getter거 responseData클래스에 필요한 것이다.
    }



}
