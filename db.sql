FROM article;

# member 테이블 세팅
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME,
    updateDate DATETIME,
    delDate DATETIME,
	delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
	authStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    loginId CHAR(20) NOT NULL UNIQUE,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(20) NOT NULL,
    `nickname` CHAR(20) NOT NULL,
    `email` CHAR(100) NOT NULL,
    `phoneNo` CHAR(20) NOT NULL
);

# member 테이블에 테스트 데이터 삽입
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = SHA2('admin', 256),
`name` = '관리자',
`nickname` = '관리자',
`email` = '',
`phoneNo` = ''; 