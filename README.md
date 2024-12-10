api명세서
기능              Method    URL                  request                  Response      상태코드
일정 생성        POST     /api/schedule          요청 body                등록 정보      200:정상등록
일정 전체 조회    GET     /api/schedule          요청 param               다건 응답 정보  200: 정상조회
일정 단건 조회    GET     /api/schedule/{id}     경로 Variable            단건 응답 정보  200: 정상조회
일정 수정         PUT     /api/schedule/{id}     경로 Variable,요청 body  수정 정보      200: 정상수정
일정 삭제         DELETE  /api/schedule/{id}     경로 variable,요청 body    -           200: 정상삭제



erd:https://www.erdcloud.com/p/FyG2YuP8nK6P6wnEn

