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
    	<td colspan = 2>Primary actor: User<br/>Secondary actor: Database</td>
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
        	7.2 시스템은 생성된 카테고리를 데이터베이스에 보낸다.<br/>
        	7.3 데이터베이스는 카테고리를 테이블에 저장한다.
        </td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2>
            4a.1 사용자가 카테고리를 삭제하는 경우<br/>
        	4a.1.1 시스템은 해당 카테고리를 삭제한다.</td>
    </tr>
</table>

<table>
    <tr>
    	<th>Use Case</th>
        <td colspan = 2>도감 수정</td>
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
    	<td></td>
    	<td></td>
    </tr>
    <tr>
    	<th>Exception Conditions</th>
    	<td colspan = 2></td>
    </tr>
</table>

<table>
    <tr>
    	<td>Use Case</td>
        <td colspan = 2>도감 삭제</td>
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
        <td colspan = 2>도감 보기</td>
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
        <td colspan = 2>도감 데이터 가져오기</td>
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
        <td colspan = 2>내 도감 공유</td>
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