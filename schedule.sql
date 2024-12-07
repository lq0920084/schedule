CREATE TABLE schedule (
id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT "일정번호",
name VARCHAR(30) NOT NULL COMMENT "작성자명",
contents TEXT COMMENT "할일",
password VARCHAR(100) NOT NULL COMMENT "패스워드",
create_timestamp TIMESTAMP NOT NULL COMMENT "작성일과 작성시간",
modify_timestamp TIMESTAMP NOT NULL COMMENT "수정일과 수정시간"
)


CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT  "일정번호",
                        userid VARCHAR(36) NOT NULL UNIQUE COMMENT "작성고유번호",
                        contents TEXT COMMENT "할일",
                        password VARCHAR(64) NOT NULL COMMENT "패스워드"
);
CREATE TABLE user(
                     userid VARCHAR(36) NOT NULL PRIMARY KEY COMMENT "작성고유번호",
                     name VARCHAR(40) NOT NULL COMMENT "작성자명",
                     email VARCHAR(50) NOT NULL COMMENT "이메일",
                     create_timestamp TIMESTAMP NOT NULL COMMENT "작성일과 수정시간",
                     modify_timestamp TIMESTAMP NOT NULL COMMENT "수정일과 수정시간", FOREIGN KEY (userid) REFERENCES schedule(userid) ON UPDATE CASCADE ON DELETE CASCADE

);