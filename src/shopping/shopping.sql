--------------------------------------------------------------------------------
-- shopping DB 세팅
--------------------------------------------------------------------------------

/*
[ 테이블 생성 ]
◈ 상품코드관리 : sh_product_code
상품코드   : p_code / 숫자형 / PK
카테고리명 : category_name / 문자형

◈ 상품관리 : sh_goods
상품일련번호 : g_idx / 숫자형 / PK
상품명       : goods_name / 문자형
상품가격     : goods_price / 숫자형
등록일       : regidate / 날짜형
상품코드     : p_code / 숫자형 / sh_product_code 테이블의 p_code 를 참조하는 FK

◈ 상품로그관리 : sh_goods_log
로그일련번호 : log_idx / 숫자형 / PK
상품명       : goods_name / 문자형
상품일련번호 : goods_idx / 숫자형
로그액션     : p_action  / 문자형 / 입력시 : ‘Insert’ , 삭제시 : ‘Delete’ 입력(check제약조건 적용)
*/

// 상품코드관리 테이블 생성
create table sh_product_code(
    p_code number not null,
    category_name varchar2(30) not null,
    constraint sh_product_code_pk primary key (p_code)
);
// 상품코드관리 테이블 생성 확인
desc sh_product_code;


// 상품관리 테이블 생성
create table sh_goods(
    g_idx number not null,
    goods_name varchar2(30) not null,
    goods_price number not null,
    regidate date,
    p_code number not null,
    constraint sh_goods_pk primary key (g_idx),
    constraint sh_goods_fk foreign key (p_code) references sh_product_code (p_code)
);
// 상품관리 테이블 생성 확인
desc sh_goods;


// 상품로그관리 테이블 생성
create table sh_goods_log(
    log_idx number not null,
    goods_name varchar2(30) not null,
    goods_idx number not null,
    p_action varchar2(30) not null,
    constraint sh_goods_log_pk primary key (log_idx)
);
// 상품로그관리 테이블 생성 확인
desc sh_goods_log;


/*
[ 시퀀스 생성 ]
앞에서 생성한 3개의 테이블에서 사용할 시퀀스를 생성하시오.
테이블 당 하나씩의 시퀀스를 생성하는 것을 권장하나,
여기서는 하나만 생성하여 사용한다.
- 시퀀스명 : seq_total_idx
- 증가치, 시작, 최소값 : 1로 지정
- 최대값, 사이클(cycle) 캐시(cache) : 사용하지 않음
*/

// 시퀀스 생성
create sequence seq_total_idx
    increment by 1 
    start with 1 
    minvalue 1 
    NoCycle 
    NoCache;
// 데이터사전에서 생성된 시퀀스 확인하기
select * from user_sequences;
// currval = 시퀀스의 현재 값 확인 -> 시퀀스 생성 후 최초실행시에는 오류발생함
select seq_total_idx.currval from dual;
/* 더미데이터 입력하고 currval를 쓰면 값이 나옴 */


/*
[ 더미데이터 입력 ]

sh_product_code 테이블
- 앞에서 생성한 시퀀스를 이용해서 3~5개 정도의 상품코드 레코드를 입력
    ex) 가전, 도서, 의류 등

sh_goods 테이블
- 앞에서 생성한 시퀀스를 이용해서 5~10개 정도의 상품 레코드를 입력
    ex) 냉장고, 세탁기 / 사피엔스, 총균쇠 / 롱패딩, 레깅스, 청바지 등
- 가격과 등록일은 본인이 적당히 정하면 된다.
- 단, 상품은 상품코드와 일치해야 한다.
    ex) 가전 - 냉장고 / 도서 - 총균쇠
    
sh_goods_log 테이블
- 별도로 입력하지 않는다.
*/

// sh_product_code 테이블에 더미데이터 5개 입력
insert into sh_product_code values (seq_total_idx.nextval, '스마트폰');
insert into sh_product_code values (seq_total_idx.nextval, '태블릿');
insert into sh_product_code values (seq_total_idx.nextval, '워치');
insert into sh_product_code values (seq_total_idx.nextval, '갤럭시북');
insert into sh_product_code values (seq_total_idx.nextval, '버즈');

// sh_goods 테이블에 더미데이터 10개 입력
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 Z 플립5', 1357100, '2023-08-11', 1);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 Z 폴드5', 2061300, '2023-08-10', 1);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 탭 S9 Ultra', 958800, '2022-07-10', 2);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 탭 S7 FE', 650000, '2022-07-15', 2);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 워치6', 329000, '2021-06-15', 3);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 워치5 프로', 289000, '2022-06-15', 3);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 북3 Ultra', 3520000, '2021-05-27', 4);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 북2 Pro', 1720000, '2021-05-27', 4);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 버즈2 프로', 189000, '2020-03-30', 5);
insert into sh_goods values (seq_total_idx.nextval, '갤럭시 버즈 라이브', 99000, '2020-03-07', 5);

// 오라클 DB에서 입력한 더미데이터는 commit을 해야지만 JAVA에서 확인 가능
commit;


/*
[ 상품수정 - 프로시저작성 ]
프로시저명 : ShopUpdateGoods
In파라미터 : 상품명, 가격, 제품코드, 수정할 상품의 일련번호
Out파라미터 : 레코드 수정 결과(1 혹은 0)
*/
create or replace procedure ShopUpdateGoods
(
    pro_g_idx in number,
    pro_goods_name in varchar2,
    pro_goods_price in number,
    pro_p_code in number,
    returnVal out number
)
is
begin
    update sh_goods 
    set goods_name=pro_goods_name, goods_price= pro_goods_price, p_code=pro_p_code
    where g_idx = pro_g_idx;
    
    if sql%Found then
        returnVal := 1;
        commit;
    else
        returnVal := 0;
    end if;
end;


/*
[ 상품삭제 - 프로시저작성 ]
프로시저명 : ShopDeleteGoods
In파라미터 : 삭제할 상품의 일련번호
Out파라미터 : 레코드 삭제 결과(1 혹은 0)
*/
create or replace procedure ShopDeleteGoods
(
    pro_g_idx in number,
    returnVal out number
)
is
begin
    delete from sh_goods where g_idx = pro_g_idx;
    
    if sql%Found then
        returnVal := 1;
        commit;
    else
        returnVal := 0;
    end if;
end;















