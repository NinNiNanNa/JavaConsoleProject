
-- education 계정 생성(우선 system계정으로 연결한 후 새로운 계정 생성)
alter session set "_ORACLE_SCRIPT"=true;
create user education identified by 1234;
grant connect, resource, unlimited tablespace TO education;

--------------------------------------------------------------------------------
-- baking DB 세팅
--------------------------------------------------------------------------------

/*
[ 테이블 생성 ]
- 계좌번호, 이름, 잔액 을 저장할수 있는 테이블을 생성한다.
- 테이블명 : banking_tb
- primary key와 같은 제약조건도 걸어준다.
*/

// banking_tb 테이블 생성
create table banking_tb (
    idx number not null,
    accountID varchar2(40) not null,
    name varchar2(30) not null,
    balance number not null,
    constraint banking_tb_pk primary key (accountID)
);
// banking_tb 테이블 생성 확인
desc banking_tb;



/*
시퀀스생성
- 시퀀스명 : seq_banking
*/
create sequence seq_banking
    increment by 1 
    start with 1 
    minvalue 1 
    NoCycle 
    NoCache;
// 데이터사전에서 생성된 시퀀스 확인하기
select * from user_sequences;
// currval = 시퀀스의 현재 값 확인 -> 시퀀스 생성 후 최초실행시에는 오류발생함
select seq_banking.currval from dual;
/* 더미데이터 입력하고 currval를 쓰면 값이 나옴 */

// 확인차 더미데이터 입력해보기
insert into banking_tb values (seq_banking.nextval, '1111', '김소진', 1000);

// 오라클 DB에서 입력한 더미데이터는 commit을 해야지만 JAVA에서 확인 가능
commit;




















