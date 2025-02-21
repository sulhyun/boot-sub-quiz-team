DROP TABLE IF EXISTS quizgame.inquiry;
CREATE TABLE quizgame.inquiry (
	iq_num INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	mb_id VARCHAR(255) NOT NULL,
	iq_title VARCHAR(255) NOT NULL,
	iq_content LONGTEXT NOT NULL,
	iq_datetime1 DATETIME NOT NULL,
	iq_comment LONGTEXT NOT NULL,
	iq_datetime2 DATETIME NOT NULL,
	iq_view INT DEFAULT 0
);