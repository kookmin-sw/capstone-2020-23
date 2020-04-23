<img src="https://user-images.githubusercontent.com/29545214/77552135-7f998880-6ef6-11ea-8796-d9bd3bdc992b.png" width="720" height="405">

[![teampage](https://img.shields.io/badge/-TeamPage-181717?logo=GitHub)](https://kookmin-sw.github.io/capstone-2020-23/)

[![slack](https://img.shields.io/badge/chat%20on-Slack-4A154B)](https://capstone23workspace.slack.com/)
[![notion](https://img.shields.io/badge/spec-Notion-282828)](https://www.notion.so/capstone23workspace)

<br/>

# 1. 프로젝트 소개

- ## 개요

  관심 있는 걸 모아서 보고, 내 관심사를 모아서 표출하는 것은 우리에게 아주 익숙하고 중요한 일이다. 매일매일 SNS에 접속해서 마음에 드는 게시물에 좋아요를 누르고, 재미있어 보이는 게시물을 보는 것은 우리에게 아주 익숙하다. 이런 SNS 중 요새 가장 대세인 걸 꼽으라면, 단연 **인스타그램**이다. 최근 SNS의 사용 목적으로 떠오르는 정보 검색은 SNS 사용을 더 확장시키는 요인이다.

   

  하지만 인스타그램은 단일 태그, 분류할 수 없는 게시물, 태그와 관련 없는 게시물 등 정보 검색에 있어서는 적절하지 않다. 그래서 우리는 태그를 1개만 입력할 수 있는 인스타그램의 한정된 검색 기능을 개선하여 다중 복합 연산을 통한 메타 검색 서비스를 제공하려 한다. 계층적인 구조를 가지는 카테고리를 사용자가 입력하여 생성하고, 시스템은 사용자가 입력한 카테고리를 가지고 인스타그램에서 메타 검색을 실시한다.

   

  일반적으로 여러 검색 엔진에 질의를 던져 얻은 결과물을 모아 한꺼번에 제공하는 검색 방식을 *‘메타 검색’* (meta search) 이라 한다. 여기서는 인스타그램이라는 하나의 사이트에 동시에 여러 개의 질의를 던져 얻은 결과를 종합하여 사용자에게 제공하는데, 이를 통해 ***‘작은 의미의 메타 검색’*** 이라 칭한다. 편의상 후술할 메타 검색이란 이 ‘작은 의미의 메타 검색’을 지칭하는 것이다.

   

  사용자는 검색 결과에서 마음에 드는 게시물을 사용자가 만든 계층적 구조의 카테고리에 저장할 수 있는데, 이렇게 게시물을 저장할 수 있는 계층적인 카테고리를 ***‘도감’*** 이라고 한다. 한번 생성한 카테고리와 저장된 게시물은 도감의 형태로 저장되어 언제든지 확인 가능하며, 새로운 게시물을 보고싶을 때마다 기존 인스타그램의 번거로운 절차 없이 본 시스템에 저장된 도감의 카테고리를 클릭만 하면 바로 확인할 수 있다.

   

  ‘모아요’(‘MoaYo’) 는 사용자가 관심있는 카테고리에 대해 게시물을 모은다는 의미와 나의(‘My’) 라는 의미를 담고있다. 

  ‘모아요’는 다음과 같은 서비스를 제공할 것을 목표로 한다.

  <br />

  <br />

  **1. 다중 태그 기반의 메타** **검색**

      A. 각각의 태그 및 그 유의어 추출

      B. 인스타그램의 게시물 중 1.A로부터 얻은 데이터의 교집합을 검색

  <br />

  **2. 사용자가 직접 만드는 계층 구조의 도감**

      A. 계층 구조로 된 입력창에 사용자가 원하는 태그를 입력하면 완성되는 도감

      B. 태그 입력시 키워드에 대한 연관 태그 제안

      C. 재검색을 위해 다시 검색창에 입력할 필요 없이 도감을 클릭하면 해당 태그들을 검색

  <br />

  **3. 만든 도감을 다른 사람들과 공유**

      A. 각각의 사용자들이 만든 도감을 다른 사람들과 공유

<br/>



- ## 구성

  - ### 도감 생성

    <img src="https://user-images.githubusercontent.com/42925501/77553810-a062dd80-6ef8-11ea-9dc4-56253b96bd11.png" width="640" height="360">

    <br/>
    <br/>

    - 도감 생성 메뉴를 통해 유저의 커스텀 도감을 **카테고리 별로** 만들 수 있다.
    - 카테고리 입력란에 원하는 키워드를 입력 시, 관련된 **추천 키워드**가 표시된다.
    - 1차로 생성된 카테고리에 2, 3차의 하위 계층 카테고리를 생성할 수 있으며, 1차를 제외한 계층에서는 같은 레벨의 카테고리를 추가 할 수 있다.
    - 모든 작업이 완료되었을 때, 도감생성 버튼을 통해 도감이 저장된다. 생성된 도감은 도감 관리 메뉴에서 확인이 가능하다.

    <br/>

  - ### 도감 관리

    - ### 도감 관리 메뉴

      <img src="https://user-images.githubusercontent.com/42925501/77553921-c4262380-6ef8-11ea-99e6-ca082c95d898.png" width="640" height="360">

        <br/>
        <br/>
      
      - 도감관리 메뉴 접근 시, 유저가 생성한 도감 리스트와 스크랩 한 컬렉션들을 확인할 수 있다.
      - 도감 리스트에 표시되는 각 도감의 명칭은 사용자가 지정한 1차 키워드이다.
      - 한개의 도감을 선택하면 **카테고리 계층구조**가 시각적으로 표시되고, 각 요소를 클릭하면 **게시물 리스트**가 표시된다.
      - 선택된 각 도감의 상세 페이지에서 도감 **수정**과 **삭제**가 가능하다.

        <br/>

    - ### 게시물 리스트

      <img src="https://user-images.githubusercontent.com/42925501/77553868-b375ad80-6ef8-11ea-8017-c42cdaf3480d.png" width="640" height="360">

        <br/>
        <br/>

      - 게시물들은 **카드 형식**으로 나열된다.
      - 상단 뷰에는 사용자가 **스크랩한 게시물**들이 최근 저장한 순서로 보여진다.
      - 하단 뷰에는 **키워드로 검색된** 인스타그램 게시물들이 보여진다.
      - 좌측 네비게이션 메뉴를 통해 선택한 도감이 **트리 뷰**로 표시되며, 다른 키워드를 클릭 시 해당 키워드로 재검색된 게시물 리스트가 보여진다.
      - 게시물을 *스크랩*하면 썸네일 이미지와 게시글 내용이 도감에 저장된다.
      - 각각의 게시물들을 클릭 시, 인스타그램의 게시물 **원본 페이지로** 연결된다.

  <br/>

  - ### 도감 공유

    <img src="https://user-images.githubusercontent.com/42925501/77553943-c9836e00-6ef8-11ea-805a-de8b5bda1575.png" width="640" height="360">

    <br/>
    <br/>

    - 도감 관리 메뉴의 공유하기 탭을 통해 유저의 도감을 공유할 수 있으며, 공유된 도감 리스트를 관리 할 수 있다.
    - 타 유저들이 공유한 도감 리스트를 확인 할 수 있으며, 도감 리스트는 최신순/인기순으로 정렬된다.
    - 다른유저들의 도감에 좋아요가 가능하며, 좋아요 수는 인기순 정렬에 반영된다.

<br/>

# 2. _Abstract_

It is very familiar and important for us to collect and see what we are interested in. It is very familiar for us to log on to SNS every day, click like on a post we like, and see a post that looks fun. One of the most popular social networking sites these days is by far **Instagram**. Searching for information that has recently emerged for the purpose of using SNS is a factor that further expands the use of SNS.



Instagram, however, is not appropriate when it comes to searching for information, such as single tags, unclassifiable postings and posts unrelated to tags. So we're trying to improve Instagram's limited search capability, which allows only one tag to be entered, to provide a meta-search service through multiple compound operations. Categories with hierarchical structures are created by user input, and the system performs a meta-search on Instagram with categories entered by the user.



In general, a search method that collects the results obtained by questioning several search engines and provides them all at once is called *'meta search'*. Here, it provides users with a combination of results obtained by throwing multiple queries at a single site called Instagram at the same time, which is called a ***'micro meta-search'***. Meta-search, which will be described for convenience, refers to this 'micro meta-search'.



Users can store their favorite postings in the search results in a hierarchical category created by users, which is called a ***'Dogam'*** in this hierarchical category where they can store postings. Once created and saved posts are stored in the form of a diagram, they can be checked at any time, and whenever you want to see a new post, you can check them immediately by clicking on the category of the picture stored in the system without the cumbersome procedure of the existing Instagram.



"MoaYo" means collecting posts about categories that users are interested in and "My".

'MoaYo' aims to provide the following services.

<br />

<br />

**1. Multiple tag-based meta** **Search**

    A. Extracting each tag and its significance

    B. Search the intersection of data obtained from 1.A of posts on Instagram
    
<br />

**2. User-created hierarchical illustrations**

    A. Completed picture when users type the desired tag in a hierarchical input window.

    B. Suggest associated tags for keywords when entering tags

    C. Click on the illustration to search for the tags without having to re-enter them in the search box.

<br />

**3. Share your illustrated book with others**

    A. Share the illustrations created by each user with others



<br/>



# 3. 소개 영상

- 제안서 발표 소개 영상

[![intro](https://user-images.githubusercontent.com/29545214/80134505-d10e6380-85d9-11ea-908e-0eeb7d07616b.png)](https://youtu.be/prg8dxmJ_Wk)



- 중간 평가 발표 영상

[![medium](https://user-images.githubusercontent.com/29545214/80134624-f69b6d00-85d9-11ea-98c0-842ecc99f720.png)](https://youtu.be/ZBMoho0NFUQ)



<br/>

# 4. 팀 소개

### 김혁만 교수님

<img src="https://i.ya-webdesign.com/images/light-bulb-icon-png-2.png" width="300" height="300">

(교수님의 요청으로 아이콘으로 대체합니다.)

:heavy_check_mark: 팀 지도, 아이디어 컨펌 및 조언, 구현 방향 조언

<br/>

### 맹산하

<img src="https://user-images.githubusercontent.com/29545214/77186349-14c30880-6b16-11ea-8e8d-629b71268499.jpeg" width="300" height="300">
<br/>

:heavy_check_mark: 팀장, 인스타그램 크롤링 api 서버 구축 및 메타 검색 로직 개발

:heavy_check_mark: [GitHub](https://github.com/joshua-dev)

:heavy_check_mark: msh01175@gmail.com

<br/>

### 강길웅

<img src="https://user-images.githubusercontent.com/29545214/77186529-5eabee80-6b16-11ea-8482-6ab68f04daa5.jpeg" width="300" height="300">
<br/>

:heavy_check_mark: 공유 api 서버 구축 및 앱 데이터 로직 개발

:heavy_check_mark: [GitHub](https://github.com/wooooong9)

:heavy_check_mark: smostr@kookmin.ac.kr

<br/>

### 김사라

<img src="https://user-images.githubusercontent.com/29545214/77186575-71bebe80-6b16-11ea-8496-cb7997b8718a.jpeg" width="300" height="300">
<br/>

:heavy_check_mark: UI/UX 디자인 기획 및 설계, 프론트엔드 이벤트 핸들링

:heavy_check_mark: [GitHub](https://github.com/20162819)

:heavy_check_mark: goddnsdl0716@kookmin.ac.kr

<br/>

### 이정현

<img src="https://user-images.githubusercontent.com/29545214/77186609-80a57100-6b16-11ea-9f42-a52effe15266.jpeg" width="300" height="300">
<br/>

:heavy_check_mark: UI/UX 디자인 적용 및 구현, 프론트엔드 이벤트 핸들링

:heavy_check_mark: [GitHub](https://github.com/labiss96)

:heavy_check_mark: labiss96@kookmin.ac.kr

<br/>

### 정준권

<img src="https://user-images.githubusercontent.com/29545214/77186635-8b600600-6b16-11ea-9a7e-2f529acca19a.jpeg" width="300" height="300">
<br/>

:heavy_check_mark: 안드로이드 백엔드 로직 설계 및 구현

:heavy_check_mark: [GitHub](https://github.com/script-brew)

:heavy_check_mark: junkwon96@kookmin.ac.kr
