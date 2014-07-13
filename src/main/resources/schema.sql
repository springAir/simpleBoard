CREATE TABLE IF NOT EXISTS Post (
  id         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  board_id   INT          NOT NULL,
  writer     VARCHAR(255) NOT NULL,
  title      VARCHAR(255),
  content CLOB(1000),
  write_date DATETIME     NOT NULL
);

CREATE TABLE IF NOT EXISTS Board (
  id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  key_name VARCHAR(255) NOT NULL, -- url에서 사용될 이름을 표현
  name     VARCHAR(255) NOT NULL -- 게시판의 실제 이름을 표현
);

INSERT INTO Board (key_name, name) VALUES ('test', '테스트');


INSERT INTO Post (writer, title, content, write_date, board_id)
  SELECT
    '박세종'     AS writer,
    'test1'   AS title,
    'content1' AS content,
    now()     AS write_date,
    id        AS board_id
  FROM board
  WHERE key_name = 'test';


INSERT INTO Post (writer, title, content, write_date, board_id)
  SELECT
    '박세종'     AS writer,
    'test2'   AS title,
    'content2' AS content,
    now()     AS write_date,
    id        AS board_id
  FROM board
  WHERE key_name = 'test';


INSERT INTO Post (writer, title, content, write_date, board_id)
  SELECT
    '박세종'     AS writer,
    'test3'   AS title,
    'content3' AS content,
    now()     AS write_date,
    id        AS board_id
  FROM board
  WHERE key_name = 'test';


INSERT INTO Post (writer, title, content, write_date, board_id)
  SELECT
    '박세종'     AS writer,
    'test4'   AS title,
    'content4' AS content,
    now()     AS write_date,
    id        AS board_id
  FROM board
  WHERE key_name = 'test';


INSERT INTO Post (writer, title, content, write_date, board_id)
  SELECT
    '박세종'     AS writer,
    'test5'   AS title,
    'content5' AS content,
    now()     AS write_date,
    id        AS board_id
  FROM board
  WHERE key_name = 'test';