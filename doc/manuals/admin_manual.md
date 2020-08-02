#### MoaYo Share Server Manual

- GitHub Repo

  https://github.com/gilwoong/moayoShare.git

- 재배포

  프로젝트의 수정 사항을 위 깃 레포에 적용한 뒤 EC2 내부 `app/moayo/deploy.sh` 를 실행하면 자동 재배포됩니다. 

- AWS - EC2 

  - Public DNS(IPv4) : ec2-13-125-216-209.ap-northeast2.compute.amazonaws.com

- AWS - RDS

  - Endpoint : moayoshare-rds.co6itctfgrzw.ap-northeast-2.rds.amazonaws.com
  - Port : 3306

- Log

  로그는 EC2의 `app/moayo/nohup` 에 저장 됩니다. `tail -f nuhup` 명령으로 실시간 로그를 확인 할 수 있으며,  `nano`,`vim` 과 같은 에디터로도 확인 가능합니다. 

<hr>

#### MoaYo Search Server Manual

- 검색 서버 GitHub Repo
  
  `https://github.com/joshua-dev/instacrawler`

- 검색 서버 재배포

  &ast; 인증서가 필요합니다. 인증서는 `msh01175@gmail.com`으로 요청해주세요.

  

  1. 인증서를 ~/.ssh에 위치시킵니다. 이 매뉴얼에서는 인증서를 편의상 a.pem이라 부르겠습니다.

     ```bash
     $ mv a.pem ~/.ssh
     ```

     

  2. 검색 서버를 빌드하여 실행 파일을 생성합니다. 이 때, EC2 서버의 OS는 Linux이므로 Linux에 맞게 go env 옵션을 줍니다.

     ```bash
     $ cd your_working_directory
     $ GOOS=linux GOARCH=amd64 go build -o instacrawler -v .
     ```

     

  3. 생성된 실행 파일을 인증서를 이용하여 EC2 서버로 보냅니다.

     ```bash
     $ scp -i ~/.ssh/a.pem instacrawler ubuntu@ec2-13-125-96-172.ap-northeast-2.compute.amazonaws.com:~
     ```

  

  4. 인증서를 이용하여 EC2 서버에 접속합니다.

     ```bash
     $ ssh -i ~/.ssh/a.pem ubuntu@ec2-13-125-96-172.ap-northeast-2.compute.amazonaws.com
     ```

     <img alt="enter" width="800" src="https://user-images.githubusercontent.com/62831866/84170542-d144be80-aab4-11ea-9d01-5dd2e6b51a71.png">

  5. 서버를 재시작합니다. 필요할 경우 기존 서버를 종료시킵니다. (편의상 기존 서버의 process id를 old_PID라 하겠습니다.)

    ```bash
    $ ps -ax | grep instacrawler
    $ kill old_PID
    $ sudo chmod +x instacrawler
    $ nohup ./instacrawler &
    ```

   

  6. `ctrl + D`를 눌러 로그아웃합니다. 서버를 백그라운드로 실행시켰으므로 로그아웃하더라도 서버 프로세스는 종료되지 않습니다.
  
- Log
  
  로그 파일은 EC2 서버의 `~/nohup.out`에 저장됩니다. `tail -f nohup.out`명령으로 로그를 확인하실 수 있습니다.

<hr>

### MoaYo Client Manual

- ##### 비즈니스 로직 처리

  - 사용자가 요청한 모든 데이터는 Service Package에서 관리합니다. Service Package는 interface와 구현한 Implementaion, dto 등을 관리합니다.

- ##### 저장관련 처리

  - Service Package에서 내부 데이터베이스에 저장하기 위한 dto 변환, dao 접근을 담당하는 패키지입니다. 또한, HashMap 자료구조를 사용하여 데이터를 관리합니다.

- ##### 서버와의 통신 처리

  - Retrofit2와 GsonConverter를 사용하여 통신합니다. GsonConverter는 객체 형태를 알맞은 Json 형태로 변환해주는 라이브러리입니다. 객체를 Json으로 변환하기 위해서 @SerializedName 어노테이션과 Getter, Setter 메소드를 추가로 입력하면 됩니다. Util에 retrofit 패키지에 있습니다.

- ##### 엔티티 및 dto, dao 관리

  - 데이터베이스에서 관리하는 데이터 클래스인 엔티티와 실제 비즈니스 로직에서 사용되는 dto, 데이터베이스에 저장하기 위한 dao 등은 각각 entity, service, dao 패키지에서 관리하고 있습니다.

- ##### 유틸

  - 각종 Custom Exception과 서버와의 통신을 위한 비동기 처리 유틸 등을 포함하고 있습니다.

- ##### 어플리케이션 의존성 및 버전 업데이트

  - build.gradle의 dependencies에서 원하는 모듈을 추가할 수 있습니다. 또한, versionCode, versionName을 이용하여 버전을 관리할 수 있습니다.
