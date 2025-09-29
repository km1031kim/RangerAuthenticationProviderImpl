<img width="396" height="234" alt="image" src="https://github.com/user-attachments/assets/b9ef6979-3205-40fe-b7f8-c3ee216d1898" />

<img width="1052" height="108" alt="image" src="https://github.com/user-attachments/assets/00029e6a-9459-4a3c-8ab2-f82ba12c759a" />

AD/Kerberos 연동이 어려운 환경을 위해, Hive에 적용 가능한 커스텀 인증 프로바이더 샘플을 구현했습니다.  
현재 Ambari에서 Apache Ranger로 유저 생성 및 유저별 권한 관리가 가능하기에, 인증 또한 Ranger에 위임하였습니다.  
내부적으로 RestTemplate을 통해 Apache Ranger Rest API를 호출합니다.  

---

<img width="569" height="651" alt="image" src="https://github.com/user-attachments/assets/72c1b587-be63-48dc-8cba-c7ca34e7bcc1" />  

Ranger Admin 웹을 통한 테스트 유저 생성  

---

beeline 인증 실패  
```bash
[root@edge01 ~]# beeline -n customUser -u jdbc:hive2://`hostname -f`:10000 -p worngpassword
```

<img width="1626" height="138" alt="image" src="https://github.com/user-attachments/assets/d6fe5eb8-6a0c-44d9-a4a8-5ac31022c6fa" />

---

beeline 인증 성공
```bash
[root@edge01 ~]# beeline -n customUser -u jdbc:hive2://`hostname -f`:10000 -p rangeradmin123
```

<img width="1903" height="178" alt="image" src="https://github.com/user-attachments/assets/c347c514-2b34-4668-9aca-9d777993ff4f" />

이후 권한 적용.

---
  
TODO : RestTemplate으로 인한 Spring 의존성 제거 필요.
