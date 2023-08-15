
-- education ���� ����(�켱 system�������� ������ �� ���ο� ���� ����)
alter session set "_ORACLE_SCRIPT"=true;
create user education identified by 1234;
grant connect, resource, unlimited tablespace TO education;

--------------------------------------------------------------------------------
-- baking DB ����
--------------------------------------------------------------------------------

/*
[ ���̺� ���� ]
- ���¹�ȣ, �̸�, �ܾ� �� �����Ҽ� �ִ� ���̺��� �����Ѵ�.
- ���̺�� : banking_tb
- primary key�� ���� �������ǵ� �ɾ��ش�.
*/

// banking_tb ���̺� ����
create table banking_tb (
    idx number not null,
    accountID varchar2(40) not null,
    name varchar2(30) not null,
    balance number not null,
    constraint banking_tb_pk primary key (accountID)
);
// banking_tb ���̺� ���� Ȯ��
desc banking_tb;



/*
����������
- �������� : seq_banking
*/
create sequence seq_banking
    increment by 1 
    start with 1 
    minvalue 1 
    NoCycle 
    NoCache;
// �����ͻ������� ������ ������ Ȯ���ϱ�
select * from user_sequences;
// currval = �������� ���� �� Ȯ�� -> ������ ���� �� ���ʽ���ÿ��� �����߻���
select seq_banking.currval from dual;
/* ���̵����� �Է��ϰ� currval�� ���� ���� ���� */

// Ȯ���� ���̵����� �Է��غ���
insert into banking_tb values (seq_banking.nextval, '1111', '�����', 1000);

// ����Ŭ DB���� �Է��� ���̵����ʹ� commit�� �ؾ����� JAVA���� Ȯ�� ����
commit;




















