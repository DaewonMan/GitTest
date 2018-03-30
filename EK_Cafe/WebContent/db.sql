create table ek_cafe_member(
	m_id varchar2(10 char) primary key,
	m_pw varchar2(10 char) not null,
	m_name varchar2(10 char) not null,
	m_birth date not null,
	m_img varchar2(100 char) not null
);

select * from ek_cafe_member;

create sequence ek_cafe_board_seq;
create sequence ek_cafe_repl_seq;

create table ek_cafe_board(
	b_no number(5) primary key,			
	b_id varchar2(10 char) not null,	
	b_txt varchar2(200 char) not null,
	b_date date not null
);

insert into ek_cafe_member values(ek_cafe_member_seq.nextval, 'stwzzang', '11qqQQ', 'stwforever', sysdate);
insert into EK_CAFE_BOARD values(ek_cafe_board_seq.nextval, 'ala', '드디어?', sysdate);

create table ek_cafe_repl(
	r_no number(7) primary key,			-- 댓글 번호
	r_b_no number(3) not null,			-- 글 번호
	r_id varchar2 (15 char) not null,	-- 댓글 쓴사람
	r_txt varchar2 (15 char) not null,	-- 댓글 내용
	r_date date not null
);
drop table ek_cafe_repl cascade constraint purge;

select * from ek_cafe_member;
select * from ek_cafe_board;
select * from ek_cafe_repl;

-------------------------------------------------------------
create sequence ek_cafe_exam_seq;

create table ek_cafe_exam(
	e_no number(7) primary key,  -- 문제번호
	e_level varchar2(10 char) not null, -- 레벨
	e_problem varchar2(200 char) not null, -- 문제
	e_answer1 varchar2(10 char) not null, -- 답안1
	e_answer2 varchar2(10 char) not null, -- 답안2
	e_answer3 varchar2(10 char) not null, -- 답안3
	e_solution varchar2(10 char) not null -- 정답
);

--drop table EK_CAFE_EXAM cascade constraint;

insert into EK_CAFE_EXAM values(ek_cafe_exam_seq.nextval, 'bgn', 'She _______ a girl and I am a boy.', 'am', 'is', 'are', 'is' ); 
insert into EK_CAFE_EXAM values(ek_cafe_exam_seq.nextval, 'bgn', 'I eat _______ apple.', 'an', 'a', 'am', 'an' ); 
insert into EK_CAFE_EXAM values(ek_cafe_exam_seq.nextval, 'bgn', 'You and I _______ some food.', 'eats', 'have', 'drinks', 'have' ); 

select * from EK_CAFE_EXAM;

----------------------------------------------------------------
create table ek_cafe_examiner(
	emr_id varchar2(10 char) primary key
);

select * from ek_cafe_examiner;

insert into EK_CAFE_EXAMINER values('admin');

----------------------------------------------------------------
create table ek_cafe_download(
	d_no number(5) primary key,
	d_id varchar2(10 char) not null,
	d_title varchar2(20 char) not null,
	d_file varchar2(200 char) not null,
	d_date date not null
);

create sequence ek_cafe_download_seq;

select * from ek_cafe_download;
drop table ek_cafe_download cascade constraint;
drop sequence ek_cafe_download_seq;
