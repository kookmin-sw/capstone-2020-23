<img src="https://user-images.githubusercontent.com/56514477/82647157-bf5bc280-9c50-11ea-9f95-b20e0f247980.png">

<br />

[![teampage](https://img.shields.io/badge/-TeamPage-181717?logo=GitHub)](https://kookmin-sw.github.io/capstone-2020-23/)
<br /><br />
[![slack](https://img.shields.io/badge/chat%20on-Slack-4A154B)](https://capstone23workspace.slack.com/)
[![notion](https://img.shields.io/badge/spec-Notion-282828)](https://www.notion.so/capstone23workspace)

<br /><br />

# 1. 프로젝트 소개

  <br />

* ## 개요

  관심 있는 걸 모아서 보고, 내 관심사를 모아서 표출하는 것은 우리에게 아주 익숙하고 중요한 일이다.

  매일매일 SNS에 접속해서 마음에 드는 게시물에 좋아요를 누르고, 재미있어 보이는 게시물을 보는 것은 우리에게 아주 익숙하다. 이런 SNS 중 요새 가장 대세인 걸 꼽으라면, 단연 **인스타그램**이다.

  그런데 인스타그램은 게시물을 올리기에는 적합하지만, 내가 관심 있는 것을 모아서 보거나 저장하기에는 적절하지 않다.

  검색 탭에서 무언가를 검색하면 관련 없는 피드가 너무 많고, 게시물을 저장해도 분류를 할 수 없고 공유도 할 수 없기 떄문에 저장 기능은 잘 쓰이지 않는다.

  무엇보다 여러 검색어를 동시에 넣어 검색할 수 없다는 것은 큰 아쉬움으로 남는다.

  그래서 우리는 카테고리를 분류해서 검색하고, 검색한 결과를 모아 도감의 형태로 보여주고 공유할 수 있는 서비스 '모아요'를 만들기로 했다.

  <br />

  프로젝트 '모아요'는 사용자에게 인스타그램 태그 검색에 대해 보다 정교한 검색 기능을 간편하게 제공하는 것을 목표로 한다.

  이는 사용자가 직접 만든 카테고리, 즉 계층 (Hierarchy) 구조로 게시물을 검색하게 함으로써 이루어지는데, 계층 구조로 분류된 검색 결과는 단일 태그 검색에 비해 정확하고 체계적인 검색을 가능하게 한다.

  계층구조 검색을 통해 최신 정보들을 실시간으로 가져오며, 이러한 카테고리 형태의 계층구조를 우리는 ‘도감’이라 정의한다. 
  즉, 사용자에 의해 ‘도감’을 생성/관리하고, 검색하는 서비스를 하나의 플랫폼으로 제공하며, 타 사용자들과 공유할 수 있는 퍼블리싱(Publishing) 기능까지 제공하는 것이 해당 프로젝트의 목표이다.

   
  
  1. 검색 목표
  
     A. 사용자가 작성한 카테고리에서 메타 검색을 위한 유의어 크롤
  
     B. 카테고리와 얻어낸 유의어를 가지고 인스타그램 내부 게시물 크롤
  
     C. 얻어낸 게시물을 사용자에게 제공

   

  2. 카테고리 목표

     A. 사용자가 입력한 태그를 통한 계층 구조의 도감 생성

     B. 생성된 도감의 수정 및 삭제

     C. 도감을 통해 검색된 게시물을 저장

  

  3. 공유 목표

     A. 사용자의 도감을 공유 서버 내부에 저장

     B. 공유 서버 내부 정보를 사용자에게 제공 

<br />

<br />

# 2. *Abstract*

It is very familiar and important to us to gather and see what interests you, and to gather and express my interests.

It is very familiar to us to connect to SNS every day, like the posts you like, and see the posts that look interesting. If you choose the most popular one among these SNS, it is definitely **Instagram**.

However, Instagram is suitable for posting posts, but not for collecting and viewing or saving what I am interested in.

If you search for something on the Search tab, there are too many irrelevant feeds, and even if you save a post, you won't be able to categorize it, and you won't be able to share it.

Most of all, it is a pity that you cannot search by putting multiple search terms at the same time.

So, we decided to create a service called 'MoaYo', which categorizes and searches categories, and collects and displays the search results in the form of illustrated books.

<br />

This project aims to provide users with more sophisticated search capabilities for Instagram tag searches.

This is done by allowing users to search for postings in their own categories, i.e. hierarchical structures, which enable accurate and systematic searches compared to single tag searches.

Hierarchical search brings up-to-date information in real time, and this category-type hierarchy is defined by us as a 'Dogam'.
The goal of the project is to create/manage 'Dogam' by the user, provide a search service as a platform, and even provide publishing capabilities that can be shared with other users.



  1. Search Objective

     A. Crawling significance for searching meta in categories created by users
   
     B. Crawl internal posts on Instagram with categories and significant words obtained
   
     C. Provide users with obtained posts



  2. Category Objectives

     A. Creating a diagram of the hierarchy using tags that you enter
   
     B. Modifying and deleting the generated drawings
   
     C. Store posts retrieved from the diagram



  3. Sharing Objectives

     A. Store your drawings inside a shared server
   
     B. Providing Users with Internal Information on Shared Servers

<br />

# 3. 소개 영상

- 제안서 발표 소개 영상

[![intro](https://user-images.githubusercontent.com/29545214/80134505-d10e6380-85d9-11ea-908e-0eeb7d07616b.png)](https://youtu.be/prg8dxmJ_Wk)



- 중간 평가 발표 영상

[![medium](https://user-images.githubusercontent.com/29545214/80134624-f69b6d00-85d9-11ea-98c0-842ecc99f720.png)](https://youtu.be/WNz_1Nq_59w)

<br /><br />

- 주요 화면 스크린샷
<img src="doc/image/스크린샷 2020-05-28 오후 9.59.18.png">

# 4. 팀 소개

### 김혁만 교수님

<img src="https://i.ya-webdesign.com/images/light-bulb-icon-png-2.png" width="300" height="300">

(교수님의 요청으로 아이콘으로 대체합니다.)

:heavy_check_mark: 팀 지도, 아이디어 컨펌 및 조언, 구현 방향 조언

<br />

### 맹산하

<img src="https://user-images.githubusercontent.com/29545214/77186349-14c30880-6b16-11ea-8e8d-629b71268499.jpeg" width="300" height="300">
<br />

:heavy_check_mark: 팀장, 인스타그램 메타 검색 API 서버 개발, AWS 배포

:heavy_check_mark: [Github](https://github.com/joshua-dev)

:heavy_check_mark: msh01175@gmail.com

<br />

### 강길웅

<img src="https://user-images.githubusercontent.com/29545214/77186529-5eabee80-6b16-11ea-8482-6ab68f04daa5.jpeg" width="300" height="300">
<br />

:heavy_check_mark: 공유 API 서버 구축 및 앱 데이터 로직 개발

:heavy_check_mark: [Github](https://github.com/wooooong9)

:heavy_check_mark: smostr@kookmin.ac.kr

<br />

### 김사라

<img src="https://user-images.githubusercontent.com/29545214/77186575-71bebe80-6b16-11ea-8496-cb7997b8718a.jpeg" width="300" height="300">
<br />

:heavy_check_mark: UI/UX 디자인 기획 및 설계, 프론트엔드 이벤트 핸들링

:heavy_check_mark: [Github](https://github.com/20162819)

:heavy_check_mark: goddnsdl0716@kookmin.ac.kr

<br />

### 이정현

<img src="https://user-images.githubusercontent.com/29545214/77186609-80a57100-6b16-11ea-9f42-a52effe15266.jpeg" width="300" height="300">
<br />

:heavy_check_mark: UI/UX 디자인 적용 및 구현, 프론트엔드 이벤트 핸들링

:heavy_check_mark: [Github](https://github.com/labiss96)

:heavy_check_mark: labiss96@kookmin.ac.kr

<br />

### 정준권

<img src="https://user-images.githubusercontent.com/29545214/77186635-8b600600-6b16-11ea-9a7e-2f529acca19a.jpeg" width="300" height="300">
<br />

:heavy_check_mark: 안드로이드 백엔드 로직 설계 및 구현

:heavy_check_mark: [Github](https://github.com/script-brew)

:heavy_check_mark: junkwon96@kookmin.ac.kr

