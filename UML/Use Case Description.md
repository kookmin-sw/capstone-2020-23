<table>
    <tr>
    	<th>Use Case</th>
        <td colspan = 2>도감 생성</td>
    </tr>
    <tr>
        <th>Triggering Event</th>
        <td colspan = 2>사용자가 도감 생성 버튼을 누른다.</td>
    </tr>
    <tr>
    	<th>Brief Description</th>
    	<td colspan = 2>사용자가 카테고리 생성 버튼을 눌러서 새로운 계층을 가진 카테고리를 생성한다.</td>
    </tr>
    <tr>
    	<th>Actors</th>
    	<td colspan = 2>Primary actor : User<br/>Secondary actor : Database</td>
    </tr>
    <tr>
    	<th>Related Use Case</th>
    	<td colspan = 2>None</td>
    </tr>
    <tr>
    	<th>Preconditions</th>
    	<td colspan = 2>None</td>
    </tr>
    <tr>
    	<th>Postconditions</th>
    	<td colspan = 2>카테고리가 생성된다. 사용자가 만든 카테고리가 데이터베이스에 저장된다.<br/>카테고리가 생성되지 않는다. 에러 메시지를 출력하고, 생성을 취소한다.</td>
    </tr>
    <tr>
        <th align="center" rowspan = 2>Flow of Activities</th>
       	<th>Actor</th>
        <th>System</th>
    </tr>
    <tr>
    	<td>
            1.1 사용자가 카테고리 생성 버튼을 누른다. <br/>
            2.1 사용자가 카테고리 이름을 입력한다. <br/>
            3.1 사용자가 카테고리 생성 버튼을 누른다.<br/>
        	4.1 사용자가 2차 카테고리들을 입력한다. <br/>
        	5.1 사용자가 2차 카테고리를 클릭한다.<br/>
        	6.1 사용자가 3차 카테고리를 입력한다.<br/>
        	7.1 사용자가 Create 버튼을 누른다.
        </td>
    	<td>
            1.2시스템은 카테고리 생성 화면을 띄어준다.<br/>
        	2.2 시스템은 입력된 이름을 가진 카테고리 객체를 생성한다.<br/>
            3.2 시스템은 2차 카테고리 입력 화면을 띄어준다.<br/>
        	4.2 시스템은 생성된 카테고리 객체에 입력된 값들을 추가한다.<br/>
        	5.2 시스템은 3차 카테고리 입력 화면을 띄어준다.<br/>
        	6.2 시스템은 생성된 카테고리 객체에 입력된 값들을 추가한다.<br/>
        	7.2 시스템은 생성된 카테고리를 데이터베이스에 저장한다.<br/>
        </td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2>
            4a.1 사용자가 카테고리를 삭제하는 경우<br/>
        	4a.1.1 시스템은 해당 카테고리를 삭제한다.<br/>
        	7a.2 시스템과 데이터베이스의 연결이 끊길 경우<br/>
        	7a.2.1 시스템은 에러 메시지를 출력하고, 해당 카테고리를 임시 저장한다.</td>
    </tr>
</table>

<table>
    <tr>
    	<th>Use Case</th>
        <td colspan = 2>도감 수정</td>
    </tr>
    <tr>
        <th>Triggering Event</th>
        <td colspan = 2>사용자가 도감 수정 버튼을 누른다.</td>
    </tr>
    <tr>
    	<th>Brief Description</th>
    	<td colspan = 2>사용자가 도감 수정 버튼을 눌러서 해당 도감의 카테고리를 수정한다.</td>
    </tr>
    <tr>
    	<th>Actors</th>
    	<td colspan = 2>Primary actor : User<br/>Secondary actor : Database</td>
    </tr>
    <tr>
    	<th>Related Use Case</th>
    	<td colspan = 2>도감 생성, 도감 보기</td>
    </tr>
    <tr>
    	<th>Preconditions</th>
    	<td colspan = 2>1. 생성된 도감이 존재해야 한다.<br/>
        				2. 사용자가 해당 도감을 가지고 있어야 한다.</td>
    </tr>
    <tr>
    	<th>Postconditions</th>
    	<td colspan = 2>1. 도감의 내용이 수정된다. 수정된 도감이 데이터베이스에 갱신된다.</td>
    </tr>
    <tr>
        <th rowspan = 2>Flow of Activities</th>
       	<th>Actor</th>
        <th>System</th>
    </tr>
    <tr>
    	<td>
        	1.1 사용자가 나의 도감 탭을 누른다.<br/>
        	2.1 사용자가 도감을 선택한다.<br/>
        	3.1 사용자가 수정하고 싶은 카테고리를 수정한다.<br/>
        	4.1 사용자가 Update 버튼을 누른다.</td>
    	<td>
        	1.2 시스템은 나의 도감 화면을 띄어준다.<br/>
        	2.2 시스템은 해당 객체를 데이터베이스에서 찾는다.<br/>
            2.3 시스템은 해당 도감에 관련된 화면을 띄어준다.<br/>
        	4.2 시스템은 해당 객체의 내용을 수정한다.<br/>
            4.3 시스템은 수정된 해당 객체를 데이테베이스에 갱신한다.<br/>
        	</td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2>4.3a 시스템과 데이터베이스의 연결이 끊긴 경우<br/>
        				4.3a.1 시스템은 에러 메시지를 출력하고, 해당 객체를 임시저장한다.</td>
    </tr>
</table>

<table>
    <tr>
    	<th>Use Case</th>
        <td colspan = 2>도감 삭제</td>
    </tr>
    <tr>
        <th>Triggering Event</th>
        <td colspan = 2>사용자가 도감 삭제 버튼을 누른다.</td>
    </tr>
    <tr>
    	<th>Brief Description</th>
    	<td colspan = 2>사용자가 도감 삭제 버튼을 눌러서 해당 도감을 삭제한다.</td>
    </tr>
    <tr>
    	<th>Actors</th>
    	<td colspan = 2>Primary actor : User<br/>Secondary actor : Database</td>
    </tr>
    <tr>
    	<th>Related Use Case</th>
    	<td colspan = 2>도감 생성, 도감 보기</td>
    </tr>
    <tr>
    	<th>Preconditions</th>
    	<td colspan = 2>해당 도감이 존재해야 한다.<br/>
        				사용자가 해당 도감을 가지고 있어야 한다.</td>
    </tr>
    <tr>
    	<th>Postconditions</th>
    	<td colspan = 2>해당 도감이 삭제된다. 삭제된 도감은 데이터베이스에서 삭제된다.</td>
    </tr>
    <tr>
        <th rowspan = 2>Flow of Activities</th>
       	<th>Actor</th>
        <th>System</th>
    </tr>
    <tr>
    	<td>
            1.1 사용자가 나의 도감 탭을 누른다.<br/>
        	2.1 사용자가 도감을 선택한다.<br/>
        	3.1 사용자가 Delete 버튼을 누른다.<br/>
        </td>
    	<td>
        	1.2 시스템은 나의 도감 화면을 띄어준다.<br/>
        	2.2 시스템은 해당 객체를 데이터베이스에서 찾는다.<br/>
        	2.3 시스템은 해당 도감에 관련된 화면을 띄어준다.<br/>
        	3.2 시스템은 해당 객체를 데이터베이스에서 삭제한다.<br/>
    	</td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2>
        	3.3a 시스템과 데이터베이스의 연결이 끊긴 경우<br/>
        	3.3a.1 시스템은 에러 메시지를 출력하고, 해당 객체를 임시 저장한다.</td>
    </tr>
</table>
<table>
    <tr>
    	<th>Use Case</th>
        <td colspan = 2>도감 보기</td>
    </tr>
    <tr>
        <th>Triggering Event</th>
        <td colspan = 2>사용자가 나의 도감 탭에서 도감을 누른다.</td>
    </tr>
    <tr>
    	<th>Brief Description</th>
    	<td colspan = 2>사용자가 나의 도감 탭에서 도감을 눌러 도감에 대한 정보를 확인한다.</td>
    </tr>
    <tr>
    	<th>Actors</th>
    	<td colspan = 2>Primary actor: User <br/>Secondary actor: Database, Crawler server</td>
    </tr>
    <tr>
    	<th>Related Use Case</th>
    	<td colspan = 2>도감 생성, 도감 데이터 가져오기</td>
    </tr>
    <tr>
    	<th>Preconditions</th>
    	<td colspan = 2>해당 도감이 존재해야 한다.<br/>
        				사용자가 해당 도감을 가지고 있어야 한다.</td>
    </tr>
    <tr>
    	<th>Postconditions</th>
    	<td colspan = 2>사용자가 도감에 관련된 게시물을 확인한다.<br/></td>
    </tr>
    <tr>
        <th rowspan = 2>Flow of Activities</th>
       	<th>Actor</th>
        <th>System</th>
    </tr>
    <tr>
    	<td>
        	1.1 사용자가 메인 화면에서 도감을 클릭한다.<br/>
        	2.1 사용자가 탭을 눌러서 다른 카테고리를 선택한다.
        </td>
    	<td>
        	1.2 시스템은 해당 도감에 관련된 게시물을 띄어준다.<br/>
        	2.2 시스템은 해당 카테고리에 관련된 게시물을 띄어준다.</td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2></td>
    </tr>
</table>
<table>
    <tr>
    	<th>Use Case</th>
        <td colspan = 2>도감 데이터 가져오기</td>
    </tr>
    <tr>
        <th>Triggering Event</th>
        <td colspan = 2>사용자가 메인 화면에서 도감을 누른다.</td>
    </tr>
    <tr>
    	<th>Brief Description</th>
    	<td colspan = 2>사용자가 메인 화면에서 도감을 누르면, 도감에 관련된 게시물을 인스타크램에서 가져온다.</td>
    </tr>
    <tr>
    	<th>Actors</th>
    	<td colspan = 2>Primary actor: User<br/>Secondary actor: Crawler server</td>
    </tr>
    <tr>
    	<th>Related Use Case</th>
    	<td colspan = 2>도감 보기</td>
    </tr>
    <tr>
    	<th>Preconditions</th>
    	<td colspan = 2>해당 도감이 생성되어 있어야 한다.<br/></td>
    </tr>
    <tr>
    	<th>Postconditions</th>
    	<td colspan = 2>시스템은 도감에 관련된 게시물을 가져온다.<br/>
        				사용자는 도감에 관련된 게시물을 볼 수 있다.</td>
    </tr>
    <tr>
        <th rowspan = 2>Flow of Activities</th>
       	<th>Actor</th>
        <th>System</th>
    </tr>
    <tr>
    	<td>
        	1.1 사용자는 메인 화면에서 도감을 누른다.<br/>
        </td>
    	<td>
        	1.2 시스템은 선택된 도감을 데이터베이스에 찾는다.<br/>
        	1.3 시스템은 찾은 도감 객체를 크롤러 서버에 Json형태로 요청한다.<br/>
        	1.4 시스템은 받은 데이터를 토대로 게시물 객체들을 생성한다.<br/>
        	1.5 시스템은 받은 게시물과 사용가 저장한 게시물을 화면에 띄어준다.</td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2>
        	1a.3 도감에 해당하는 게시물이 없을 때<br/>
        	1a.3.1 시스템은 해당 도감의 검색결과에 대한 에러 메시지를 출력한다.</td>
    </tr>
</table>


<table>
    <tr>
    	<th>Use Case</th>
        <td colspan = 2>내 도감 공유</td>
    </tr>
    <tr>
        <th>Triggering Event</th>
        <td colspan = 2></td>
    </tr>
    <tr>
    	<th>Brief Description</th>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<th>Actors</th>
    	<td colspan = 2>Primary actor: <br/>Secondary actor: </td>
    </tr>
    <tr>
    	<th>Related Use Case</th>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<th>Preconditions</th>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<th>Postconditions</th>
    	<td colspan = 2></td>
    </tr>
    <tr>
        <th rowspan = 2>Flow of Activities</th>
       	<th>Actor</th>
        <th>System</th>
    </tr>
    <tr>
    	<th></td>
    	<th></td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2></td>
    </tr>
</table>


<table>
    <tr>
    	<td>Use Case</td>
        <td colspan = 2>공유 도감 수정</td>
    </tr>
    <tr>
        <td>Triggering Event</td>
        <td colspan = 2></td>
    </tr>
    <tr>
    	<td>Brief Description</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<td>Actors</td>
    	<td colspan = 2>Primary actor: <br/>Secondary actor: </td>
    </tr>
    <tr>
    	<td>Related Use Case</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<td>Preconditions</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<td>Postconditions</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
        <td rowspan = 2>Flow of Activities</td>
       	<td>Actor</td>
        <td>System</td>
    </tr>
    <tr>
    	<td></td>
    	<td></td>
    </tr>
    <tr>
    	<td>Exception Conditions</td>
    	<td colspan = 2></td>
    </tr>
</table>

<table>
    <tr>
    	<td>Use Case</td>
        <td colspan = 2>내 도감으로 가져오기</td>
    </tr>
    <tr>
        <td>Triggering Event</td>
        <td colspan = 2></td>
    </tr>
    <tr>
    	<td>Brief Description</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<td>Actors</td>
    	<td colspan = 2>Primary actor: <br/>Secondary actor: </td>
    </tr>
    <tr>
    	<td>Related Use Case</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<td>Preconditions</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
    	<td>Postconditions</td>
    	<td colspan = 2></td>
    </tr>
    <tr>
        <td rowspan = 2>Flow of Activities</td>
       	<td>Actor</td>
        <td>System</td>
    </tr>
    <tr>
    	<td></td>
    	<td></td>
    </tr>
    <tr>
    	<td>Exception Conditions</td>
    	<td colspan = 2></td>
    </tr>
</table>