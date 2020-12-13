# Account Service

플랜잇 서비스중 회원 관리를 담당하는 서비스 입니다.

### 1. 기능

#### 1. 유저

- 나의 프로필 관리 기능

  \- 프로필 이미지, 아이디, 등급과 해야 할 일의 수, 팔로워/팔로잉 수를 조회할 수 있다.

  \- 프로필 이미지, 이름, 생년월일, 성별, 소개 글 정보를 수정할 수 있다.

  \- 계정 공개 여부를 설정할 수 있다.

  \- 로그아웃 및 탈퇴를 할 수 있다.

- 나의 계획 관리 기능

  \- 계획 완료 여부와 마감 여부에 따라 ‘해야 할 일’, ‘그동안 한 일’, ‘하지 못한 일’로 구분하여 계획을 관리할 수 있으며, 달력으로 조회할 수 있다.

  \- 계획 완료 여부를 체크할 수 있다.

  \- 나의 점수를 일자 별로 heatmap을 통해 조회할 수 있다.

  \- 새로운 알림 정보를 조회할 수 있다.

- 다른 사용자 조회 기능

  \- 계정의 공개/비공개, 팔로우 여부에 따라 계정을 조회할 수 있다.

  \- 계정을 팔로우 할 수 있다.

#### 2. 팔로우

- 나의 팔로워 리스트, 팔로잉 리스트 관리 기능

  \- 나와 상대방의 팔로잉 또는 팔로워 관계가 업데이트되는 즉시, 업데이트된 팔로우 상태에 따라 나와 상대방의 팔로워, 팔로잉 리스트에 실시간 반영된다.

  \- 내 팔로워 리스트에서 나는 상대방을 팔로잉하고 있지 않다면 여기에서도 상대방에게 팔로잉 신청이 가능하다.

  \- 나를 팔로잉하거나 팔로잉하는 사람들 가운데 계획 교류를 원하지 않는 사람을 팔로잉 삭제할 수 있다.

- 팔로잉 신청 수락/거절 기능

  \- 내가 비공개 계정일 경우 실시간으로 요청 알림 받은 팔로잉 신청 데이터를 수락하면 나의 팔로워 목록에 즉시 추가, 반영되며 상대방이 나의 프로필 페이지를 조회하여 todo를 보거나 상대방의 메인 페이지에서 나의 todo를 볼 수 있다.

  \- 거절한다면 팔로잉 신청 기록이 사라지고 당연히 상대방은 나의 todo도 볼 수 없다. 거절 처리 됐어도 상대방은 나에게 다시 팔로잉 신청이 가능하다.

* 팔로잉, 팔로워 계정 프로필, todo 조회 기능

  \- 검색없이 내가 팔로잉하는 사람의 프로필페이지나 todo를 조회할 수 있다.

  \- 나와 상대방이 맞팔로우하고 있는 상태가 아니고 상대방만 나를 일방적으로 팔로잉하고 있는 상태이고 상대방이 비공개 계정일 경우엔 불가능하다.

- 팔로잉 요청 실시간 알림 기능

  \- 나에게 온 상대방의 팔로잉 신청을 실시간으로 알림 받을 수 있다.

### 2. 도메인 모델링

![AccountModel](readmeImg/AccountModel.png)

### 3. 테이블 구조

![AccountTable](readmeImg/AccountTable.png)
