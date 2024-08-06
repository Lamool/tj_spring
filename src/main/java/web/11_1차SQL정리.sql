/*
	- SQL
    1. DDL : 정의어
		create 		: 데이터베이스/테이블 생성
			- create database [데이터베이스명]
            - create table [테이블명]
        drop 		: 데이터베이스/테이블 삭제
			- drop database [데이터베이스명]
            - drop table [테이블명]
			* drop database/table if exists [데이터베이스명/테이블명]
		use 		: 데이터베이스 활성화
			- use [데이터베이스명]
		truncate 	: 테이블의 모든 레코드 삭제
			- truncate table [테이블명]
		show 		: 모든 데이터베이스 목록을 조회하기
			- show databases
            
    2. DML : 조작어
		select : 조회
			- select [필드명] from [테이블명] where [조건절]
        insert : 삽입
			- insert into [테이블명] (필드명1, 필드명2) values (값1, 값2)
        update : 수정
			- update [테이블명] set 필드명1 = 수정값1, 필드명2 = 수정값2 where [조건절]
        delete : 삭제
			- delete from [테이블명] where [조건절]
            
    3. DCL : 제어어
		grant : 권한부여
        revoke : 권한취소
        - TCL
        commit : 작업한 데이터를 데이터베이스에 반영 완료
        rollback : 작업 반영 취소
        savepoint : 작업 위치 지정 (이후 해당 위치 이동 가능)
        
	4. 연산자
		1. 산술연산자 : +더하기 -빼기 *곱하기 /나누기 div몫 mod나머지
		2. 비교연산자 : >초과 <미만 >=이상 <=이하 =같다 !=같지않다
        3. 논리연산자 : and이면서 or이거나 not부정
        4. 기타연산자 :
			1. between시작값 and 끝값 : 시작값부터 끝값까지의 포함된 값
            2. in(값1, 값2)		   : 값이 하나라도 포함된 값 검사
            3. 필드명 like 패턴		   : 필드의 패턴 값 검사
				1. % : 모든 문자 대응
                2. _ : _개수 만큼 문자수 대응
			4. [필드명] is null, [필드명] is not null : 필드의 null 여부 검사
            5. [필드명] as 별칭 	   : 조회시 사용되는 별칭/별명
            6. distinct [필드명]	   : 조회시 필드의 중복값을 제거
	
    5. 다양한 문법절
		[1] where 절			: [그룹 전] 일반 조건
			where [필드명] 연산자
        [2] group by 절		: 그룹 조건
			group by [필드명]
        [3] having 절		: [그룹 후] 그룹내 조건
			having [필드명] 연산자
        [4] order by 절		: 정렬 조건
			order by [필드명] [ASC/DESC]
            order by [필드명] [ASC/DESC], [필드명2] [ASC/DESC]
        [5] limit 절
			limit 레코드수
            limit 시작레코드번호[0~], 레코드수
	
    6. 내부 조인 절
		[1] [테이블명A], [테이블명B] where 테이블명A.pk필드 = 테이블명B.fk필드;
        [2] [테이블명A] natural join [테이블명B];
        [3] [테이블명A] join [테이블명B] on 테이블명A.pk필드 = 테이블명B.fk필드;
        [4] [테이블명A] inner join [테이블명B] on 테이블명A.pk필드 = 테이블명B.fk필드;
        [5] [테이블명A] join [테이블명B] using(필드명);
    
    
		= 순서 =
        select *
        from [테이블명] inner join [테이블명]
        on [조인조건]
        where [조건절]
        group by [그룹절]
        having [그룹조건]
        order by [정렬조건]
        limit[개수제한조건]
    
    7. 제약조건
		[1] not null : 필드의 null 포함 불가능
        [2] default : 레코드 삽입시 필드의 기본값 설정
        [3] unique : 필드의 중복 포함 불가능
        [4] (mysql) auto_increment : 레코드 삽입시 필드의 자동번호 부여
        [5] primary key : 식별키 (해당 테이블의 고유한 값을 가진 값의 필드)
        [6] foreign key : 참조키(다른 테이블의 식별키 필드를 참조하는 필드)
        [7] 제약조건 옵션
            on [delete/update] restrict : pk 필드값이 삭제/수정할때 pk 필드값을 참조하는 FK필드값이 있으면 삭제/수정 취소
            on [delete/update] cascade  : pk 필드값이 삭제/수정할때 pk 필드값을 참조하는 FK필드값이 있으면 삭제/수정 같이 처리
            on [delete/update] set null : pk 필드값이 삭제/수정할때 pk 필드값을 참조하는 FK필드값이 있으면 삭제/수정 처리 후 FK 필드값을 NULL 설정
    
    - 필드 타입
		[정수]
			TINYINT : 1바이트
            SMALLINT : 2바이트
            MEDIUMINT : 3바이트
            INT : 4바이트
            BIGINT : 8바이트
				- signed : [부호있음] 음수 사용 (기본값)
					-128 ~ 127
                - unsigned : [부호없음] 음수 사용 안 함
					0 ~ 255
		[실수]
			FLOAT : 4바이트
            DOUBLE : 8바이트
		[날짜]
			DATE		: 날짜만 표현
            DATETIME	: 날짜 외 시간 표현
		[문자]
			CHAR : 고정 길이
            VARCHAR : 가변길이
            TEXT : 고정길이 6만개
            LONGTEXT : 고정길이 42억개 [4GB]
        [논리]
			BOOL : true/false, tinyint
            

*/