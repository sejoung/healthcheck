# healthcheck

HealthCheckController 설정
---
> com.github.sejoung.api.controller.HealthCheckController

 - api 호출하는 방법
 - fixedDelay 마다 동작
   - fixedDelay = 60000 은 1분을 의미
   

HealthCheckService 설정
---
> com.github.sejoung.api.service.HealthCheckService

 - list.add("127.0.0.1");로 check 할 서버의 url 을 입력
   - 해당 서버에서는 해당 api 로 return 할 api 를 만들어둬야함.
   
Telegram 의 url 설정
---
> application.properties

 - telegram.url=https://api.telegram.org/  
Telegram으로 HealthCechk 결과 송출



