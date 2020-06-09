### Share Server Manual

#### Git Repo

https://github.com/gilwoong/moayoShare.git

#### 재 배포

프로젝트의 수정 사항을 위 깃 레포에 적용한 뒤 EC2 내부 `app/moayo/deploy.sh` 를 실행하면 자동 재 배포 수행됩니다. 

#### AWS - EC2 

- Public DNS(IPv4) : ec2-13-125-216-209.ap-northeast2.compute.amazonaws.com

#### AWS - RDS

- Endpoint : moayoshare-rds.co6itctfgrzw.ap-northeast-2.rds.amazonaws.com
- Port : 3306

#### Log

로그는 EC2의 `app/moayo/nohup` 에 저장 됩니다. `tail -f nuhup` 명령으로 실시간 로그를 확인 할 수 있으며,  `nano`,`vim` 과 같은 에디터로도 확인 가능합니다. 
