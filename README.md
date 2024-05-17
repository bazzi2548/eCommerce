# eCommerce 프로젝트

## 🖥️ 프로젝트 소개
eCommerce 플랫폼으로 원하는 상품을 위시리스트에 담아 다수의 사용자가 동시에 편리하게 구매할 수 있는 프로젝트 입니다.

## 📚 API 문서
[ API (POSTMAN) ](https://documenter.getpostman.com/view/31375689/2sA3JT2JFy)

## 📌 주요 기능
- 유저 관리: 사용자는 회원가입과, 마이페이지를 통해 개인정보를 수정 할 수 있습니다.
- 상품 관리: 상품을 등록하고, 상품에 대해 상세한 정보를 확인할 수 있습니다.
- 주문 기능: 많은 유저들이 동시에 주문을 하더라도 문제없이 주문 할 수 있습니다.

## 🛠 기술 스택
- Spring Boot
- Spring Security
- JPA / Hibernate
- MySQL
- Redis
- Docker / Docker Compose

## 🚧 프로젝트 아키텍처
프로젝트의 전체적인 아키텍처는 마이크로서비스 아키텍처를 따르며, 각 기능별로 분리된 서비스들이 REST API를 통해 서로 통신합니다. Docker를 사용하여 각 서비스들을 컨테이너화하였으며, MySQL과 Redis를 사용하여 데이터의 지속성을 관리합니다.

## 📈 기술적 고민 및 트러블슈팅
프로젝트 개발 과정에서 성능을 위한 기술적 고민과, 트러블슈팅 사례를 공유합니다. 이는 프로젝트 진행 중 직면한 기술적 문제를 해결하고, 프로젝트의 전반적인 성능을 향상시킨 경험을 공유하는 자료입니다.

### 기술적 고민 사례
- [일괄 처리](https://pinto-parka-203.notion.site/ca68ba6f1f5e42cc8db4099f7c3da474?pvs=4): 회원들이 주문한 상품들의 상태를 변경하기 위해 스케쥴링을 사용해 일괄 처리 하였습니다.
- [API Gateway 도입](https://pinto-parka-203.notion.site/API-Gateway-199692dc9c93431689353718a0b3e89a?pvs=4): 시스템의 안정성과 서비스 관리의 용이성을 위해 API Gateway를 추가했습니다.
- [MSA에서 JPA 연관관계](https://pinto-parka-203.notion.site/MSA-JPA-d6cbc03dcede42668a1377b677f90d49?pvs=4): 모놀리식 구조의 JPA 연관관계로 이루어진 Entity들을 각각의 Micro Service에서 어떻게 처리할 지 고민해보았습니다.
- [MSA의 독립성](https://pinto-parka-203.notion.site/Micro-Service-e3e6f8ba89124749bf83c4f7326ca650?pvs=4): Micro Service의 독립성에 대해 고민해보았습니다.

### 트러블슈팅 경험
- [Micro Service 통신 장애 문제 해결](https://pinto-parka-203.notion.site/Open-Feign-405-error-80155c4e72e143339fd7e231938b25b9?pvs=4): 다른 Micro Service의 API를 사용할 때 발생한 문제를 해결했습니다.
- [redis 직렬화 오류 해결](https://pinto-parka-203.notion.site/redis-e963f592fd2348569ba8a44f3cb7d4e5?pvs=4): redis를 사용하며 발생한 문제점에 대해 해결했습니다.
- [재고관리 동시성 문제 해결](https://pinto-parka-203.notion.site/655a047c998844a1b5c0aabdce1f5d9c?pvs=4): 동시에 많은 사용자의 요청을 받아 재고관리에서의 문제를 redis를 이용해 해결했습니다.
- [redis 장애시 백업](https://pinto-parka-203.notion.site/redis-7885d8d41eae4ae79269bc6d8d674ca2?pvs=4): 재고관리를 redis에서 하기 때문에 백업이 중요하다 판단하고, 백업 방식을 결정했습니다.