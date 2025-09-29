<img width="396" height="234" alt="image" src="https://github.com/user-attachments/assets/b9ef6979-3205-40fe-b7f8-c3ee216d1898" />
<img width="1052" height="108" alt="image" src="https://github.com/user-attachments/assets/00029e6a-9459-4a3c-8ab2-f82ba12c759a" />

하이브 커스텀 인증 클래스.

유저 인증 시, AD 서버 연동이나 Kerberos 적용, 혹은 PasswdAuthenticationProvider 를 구현하면 된다.

현재 Ambari에서 Apache Ranger로 유저 생성 및 유저별 권한 관리가 가능하기에, 인증 또한 Ranger에 위임한 PasswdAuthenticationProvider 구현체이다.

내부적으로 RestTemplate을 통해 Apache Ranger Rest API를 호출한다.

TODO : RestTemplate으로 인한 Spring 의존성 제거 필요.
