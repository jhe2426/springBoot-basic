package com.haeun.firstproject.service;

//* 서비스 인터페이스가 필요한 경우 : 아래의 선언만 해놓고 구현체에서 구현을 하지 않아도 컨트롤러에서 사용이 가능하고 
//* 서버의 작동에 아무런 문제를 일으키지 않으므로 (IDE에서는 구현부가 없다고 컴파일 오류가 발생되어 있지만, 서버를 돌리는 데에는 아무런 문제도 발생하지 않음)
//*     독립적으로 개발자들이 각 각의 컨트롤 서비스 같은 객체들을 개발을 해도 프로그램 실행에는 아무런 문제가 없기 때문에 인터페이스를 꼭 사용해야하는 것이다.
public interface RestApiService {
    public String getMethod();
    public String postMethod();
    public String patchMethod();
    public String deleteMethod();
    //인테페이스를 작성 할 떄 인풋, 아웃풋에 대해 작성만 해준다고 함
}
