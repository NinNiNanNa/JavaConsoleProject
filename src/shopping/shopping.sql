--------------------------------------------------------------------------------
-- shopping DB ����
--------------------------------------------------------------------------------

/*
[ ���̺� ���� ]
�� ��ǰ�ڵ���� : sh_product_code
��ǰ�ڵ�   : p_code / ������ / PK
ī�װ��� : category_name / ������

�� ��ǰ���� : sh_goods
��ǰ�Ϸù�ȣ : g_idx / ������ / PK
��ǰ��       : goods_name / ������
��ǰ����     : goods_price / ������
�����       : regidate / ��¥��
��ǰ�ڵ�     : p_code / ������ / sh_product_code ���̺��� p_code �� �����ϴ� FK

�� ��ǰ�αװ��� : sh_goods_log
�α��Ϸù�ȣ : log_idx / ������ / PK
��ǰ��       : goods_name / ������
��ǰ�Ϸù�ȣ : goods_idx / ������
�α׾׼�     : p_action  / ������ / �Է½� : ��Insert�� , ������ : ��Delete�� �Է�(check�������� ����)
*/

// ��ǰ�ڵ���� ���̺� ����
create table sh_product_code(
    p_code number not null,
    category_name varchar2(30) not null,
    constraint sh_product_code_pk primary key (p_code)
);
// ��ǰ�ڵ���� ���̺� ���� Ȯ��
desc sh_product_code;


// ��ǰ���� ���̺� ����
create table sh_goods(
    g_idx number not null,
    goods_name varchar2(30) not null,
    goods_price number not null,
    regidate date,
    p_code number not null,
    constraint sh_goods_pk primary key (g_idx),
    constraint sh_goods_fk foreign key (p_code) references sh_product_code (p_code)
);
// ��ǰ���� ���̺� ���� Ȯ��
desc sh_goods;


// ��ǰ�αװ��� ���̺� ����
create table sh_goods_log(
    log_idx number not null,
    goods_name varchar2(30) not null,
    goods_idx number not null,
    p_action varchar2(30) not null,
    constraint sh_goods_log_pk primary key (log_idx)
);
// ��ǰ�αװ��� ���̺� ���� Ȯ��
desc sh_goods_log;


/*
[ ������ ���� ]
�տ��� ������ 3���� ���̺��� ����� �������� �����Ͻÿ�.
���̺� �� �ϳ����� �������� �����ϴ� ���� �����ϳ�,
���⼭�� �ϳ��� �����Ͽ� ����Ѵ�.
- �������� : seq_total_idx
- ����ġ, ����, �ּҰ� : 1�� ����
- �ִ밪, ����Ŭ(cycle) ĳ��(cache) : ������� ����
*/

// ������ ����
create sequence seq_total_idx
    increment by 1 
    start with 1 
    minvalue 1 
    NoCycle 
    NoCache;
// �����ͻ������� ������ ������ Ȯ���ϱ�
select * from user_sequences;
// currval = �������� ���� �� Ȯ�� -> ������ ���� �� ���ʽ���ÿ��� �����߻���
select seq_total_idx.currval from dual;
/* ���̵����� �Է��ϰ� currval�� ���� ���� ���� */


/*
[ ���̵����� �Է� ]

sh_product_code ���̺�
- �տ��� ������ �������� �̿��ؼ� 3~5�� ������ ��ǰ�ڵ� ���ڵ带 �Է�
    ex) ����, ����, �Ƿ� ��

sh_goods ���̺�
- �տ��� ������ �������� �̿��ؼ� 5~10�� ������ ��ǰ ���ڵ带 �Է�
    ex) �����, ��Ź�� / ���ǿ���, �ѱռ� / ���е�, ���뽺, û���� ��
- ���ݰ� ������� ������ ������ ���ϸ� �ȴ�.
- ��, ��ǰ�� ��ǰ�ڵ�� ��ġ�ؾ� �Ѵ�.
    ex) ���� - ����� / ���� - �ѱռ�
    
sh_goods_log ���̺�
- ������ �Է����� �ʴ´�.
*/

// sh_product_code ���̺� ���̵����� 5�� �Է�
insert into sh_product_code values (seq_total_idx.nextval, '����Ʈ��');
insert into sh_product_code values (seq_total_idx.nextval, '�º�');
insert into sh_product_code values (seq_total_idx.nextval, '��ġ');
insert into sh_product_code values (seq_total_idx.nextval, '�����ú�');
insert into sh_product_code values (seq_total_idx.nextval, '����');

// sh_goods ���̺� ���̵����� 10�� �Է�
insert into sh_goods values (seq_total_idx.nextval, '������ Z �ø�5', 1357100, '2023-08-11', 1);
insert into sh_goods values (seq_total_idx.nextval, '������ Z ����5', 2061300, '2023-08-10', 1);
insert into sh_goods values (seq_total_idx.nextval, '������ �� S9 Ultra', 958800, '2022-07-10', 2);
insert into sh_goods values (seq_total_idx.nextval, '������ �� S7 FE', 650000, '2022-07-15', 2);
insert into sh_goods values (seq_total_idx.nextval, '������ ��ġ6', 329000, '2021-06-15', 3);
insert into sh_goods values (seq_total_idx.nextval, '������ ��ġ5 ����', 289000, '2022-06-15', 3);
insert into sh_goods values (seq_total_idx.nextval, '������ ��3 Ultra', 3520000, '2021-05-27', 4);
insert into sh_goods values (seq_total_idx.nextval, '������ ��2 Pro', 1720000, '2021-05-27', 4);
insert into sh_goods values (seq_total_idx.nextval, '������ ����2 ����', 189000, '2020-03-30', 5);
insert into sh_goods values (seq_total_idx.nextval, '������ ���� ���̺�', 99000, '2020-03-07', 5);

// ����Ŭ DB���� �Է��� ���̵����ʹ� commit�� �ؾ����� JAVA���� Ȯ�� ����
commit;


/*
[ ��ǰ���� - ���ν����ۼ� ]
���ν����� : ShopUpdateGoods
In�Ķ���� : ��ǰ��, ����, ��ǰ�ڵ�, ������ ��ǰ�� �Ϸù�ȣ
Out�Ķ���� : ���ڵ� ���� ���(1 Ȥ�� 0)
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
[ ��ǰ���� - ���ν����ۼ� ]
���ν����� : ShopDeleteGoods
In�Ķ���� : ������ ��ǰ�� �Ϸù�ȣ
Out�Ķ���� : ���ڵ� ���� ���(1 Ȥ�� 0)
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















