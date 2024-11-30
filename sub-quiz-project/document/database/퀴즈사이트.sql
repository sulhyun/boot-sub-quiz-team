DROP DATABASE IF EXISTS quizgame;

CREATE DATABASE IF NOT EXISTS quizgame;

USE quizgame;

CREATE TABLE `community` (
	`co_num`	int primary key auto_increment	NOT NULL,
	`co_name`	varchar(255)	NULL
);

CREATE TABLE `post` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`po_title`	varchar(255)	NULL,
	`po_content`	longtext	NULL,
	`po_date`	datetime	NULL,
	`po_view`	int	NULL,
	`po_secret`	char(1)	NULL,
	`mb_id`	varchar(255)	NULL,
	`co_num`	int	NOT NULL
);

CREATE TABLE `member` (
	`mb_num`	int primary key auto_increment	NOT NULL,
	`mb_id`	varchar(255)	NULL,
	`mb_pw`	varchar(255)	NULL,
	`mb_name`	varchar(50)	NULL,
	`mb_nick`	varchar(50)	NULL,
	`mb_hp`	varchar(50)	NULL,
	`mb_email`	varchar(50)	NULL,
	`mb_zip`	int(11)	NULL,
	`mb_addr`	varchar(255)	NULL,
	`mb_addr2`	varchar(255)	NULL,
	`mb_birth`	Date	NULL,
	`mb_level`	int(11)	NULL,
	`mb_datetime`	datetime	NULL,
	`mb_edit_date`	datetime	NULL,
	`mb_out_date`	datetime	NULL,
	`mb_cookie`	varchar(255)	NULL,
	`mb_cookie_limit`	varchar(255)	NULL,
	`mb_point`	int(11)	NULL
);

CREATE TABLE `comment` (
	`cm_num`	int primary key auto_increment	NOT NULL,
	`cm_content`	varchar(255)	NULL,
	`cm_ori_num`	int	NULL,
	`cm_date`	datetime	NULL,
	`mb_id`	varchar(255)	NULL,
	`po_num`	int	NOT NULL
);

CREATE TABLE `recommend` (
	`re_num`	int primary key auto_increment	NOT NULL,
	`re_state`	char(1)	NULL,
	`mb_id`	varchar(255)	NULL,
	`po_num`	int	NOT NULL
);

CREATE TABLE `file` (
	`fi_num`	int primary key auto_increment	NOT NULL,
	`fi_ori_name`	varchar(255)	NULL,
	`fi_path`	text	NULL,
	`fi_no`	int	NULL,
	`fi_reg_num`	int	NULL,
	`fi_type`	varchar(255)	NULL
);

CREATE TABLE `friend` (
	`fr_num`	int primary key auto_increment	NOT NULL,
	`mb_id`	varchar(255)	NULL,
	`fr_id`	varchar(255)	NULL,
	`fr_status`	char(1)	NULL
);

CREATE TABLE `quiz_type` (
	`qt_num`	int primary key auto_increment	NOT NULL,
	`qt_name`	varchar(255)	NULL
);

CREATE TABLE `quiz_choice` (
	`qu_num`	int primary key auto_increment	NOT NULL,
	`qu_content`	longtext	NULL,
	`qu_answer1`	int(11)	NULL,
	`qu_answer2`	int(11)	NULL,
	`qu_answer3`	int(11)	NULL,
	`qu_answer4`	int(11)	NULL,
	`qu_correct_answer`	int(11)	NULL,
	`qt_num`	int	NOT NULL
);

CREATE TABLE `quiz_attempt` (
	`qa_num`	int primary key auto_increment	NOT NULL,
	`qa_count`	int	NULL,
	`qa_correct_count`	int	NULL,
	`qu_num`	int	NOT NULL
);

CREATE TABLE `point` (
	`pi_num`	int primary key auto_increment	NOT NULL,
	`pi_no`	int	NULL,
	`pi_content`	varchar(255)	NULL,
	`pi_datetime`	datetime	NULL,
	`mb_id`	varchar(255)	NULL
);

CREATE TABLE `quiz_interest` (
	`qi_num`	int primary key auto_increment	NOT NULL,
	`mb_id`	varchar(255)	NULL,
	`qt_num`	int	NOT NULL
);

CREATE TABLE `rating` (
	`ra_num`	int primary key auto_increment	NOT NULL,
	`ra_no`	int(11)	NULL,
	`mb_id`	varchar(255)	NULL,
	`qt_num`	int	NOT NULL
);

CREATE TABLE `quiz_subjective` (
	`qs_num`	int primary key auto_increment	NOT NULL,
	`qs_content`	longtext	NULL,
	`qs_answer`	varchar(255)	NULL,
	`qs_correct_answer`	varchar(255)	NULL,
	`qt_num`	int	NOT NULL
);

CREATE TABLE `event` (
	`ev_num`	int primary key auto_increment	NOT NULL,
	`ev_title`	varchar(255)	NULL,
	`ev_content`	longtext	NULL,
	`ev_start_level`	int(11)	NULL,
	`ev_end_level`	int(11)	NULL,
	`ev_point`	int(11)	NULL,
	`ev_datetime`	datetime	NULL,
	`ev_start`	datetime	NULL,
	`ev_end`	datetime	NULL,
	`ev_status`	char(2)	NULL,
	`ev_cnt`	int(11)	NULL
);

CREATE TABLE `event_prize` (
	`ep_num`	int primary key auto_increment	NOT NULL,
	`ep_prize`	varchar(255)	NULL,
	`mb_id`	varchar(255)	NULL,
	`ep_rank`	int(11)	NULL,
	`ev_num`	int	NOT NULL
);

CREATE TABLE `event_list` (
	`el_num`	int primary key auto_increment	NOT NULL,
	`mb_id`	varchar(255)	NULL,
	`el_datetime`	datetime	NULL,
	`ev_num`	int	NOT NULL
);

CREATE TABLE `email_verification` (
	`evc_num`	int primary key auto_increment	NOT NULL,
	`mb_email`	varchar(255)	NULL,
	`evc_code`	char(6)	NULL
);

ALTER TABLE `post` ADD CONSTRAINT `FK_community_TO_post_1` FOREIGN KEY (
	`co_num`
)
REFERENCES `community` (
	`co_num`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_post_TO_comment_1` FOREIGN KEY (
	`po_num`
)
REFERENCES `post` (
	`po_num`
);

ALTER TABLE `recommend` ADD CONSTRAINT `FK_post_TO_recommend_1` FOREIGN KEY (
	`po_num`
)
REFERENCES `post` (
	`po_num`
);

ALTER TABLE `quiz_choice` ADD CONSTRAINT `FK_quiz_type_TO_quiz_choice_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

ALTER TABLE `quiz_attempt` ADD CONSTRAINT `FK_quiz_choice_TO_quiz_attempt_1` FOREIGN KEY (
	`qu_num`
)
REFERENCES `quiz_choice` (
	`qu_num`
);

ALTER TABLE `quiz_interest` ADD CONSTRAINT `FK_quiz_type_TO_quiz_interest_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

ALTER TABLE `rating` ADD CONSTRAINT `FK_quiz_type_TO_rating_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

ALTER TABLE `quiz_subjective` ADD CONSTRAINT `FK_quiz_type_TO_quiz_subjective_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

ALTER TABLE `event_prize` ADD CONSTRAINT `FK_event_TO_event_prize_1` FOREIGN KEY (
	`ev_num`
)
REFERENCES `event` (
	`ev_num`
);

ALTER TABLE `event_list` ADD CONSTRAINT `FK_event_TO_event_list_1` FOREIGN KEY (
	`ev_num`
)
REFERENCES `event` (
	`ev_num`
);

