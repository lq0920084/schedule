CREATE TABLE schedule (
id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT "일정번호",
name VARCHAR(30) NOT NULL COMMENT "작성자명",
contents TEXT COMMENT "할일",
password VARCHAR(100) NOT NULL COMMENT "패스워드",
create_timestamp TIMESTAMP NOT NULL COMMENT "작성일과 작성시간",
modify_timestamp TIMESTAMP NOT NULL COMMENT "수정일과 수정시간"
)