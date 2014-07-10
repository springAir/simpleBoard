CREATE TABLE IF NOT EXISTS Post (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    board_Id INT NOT NULL,
    writer VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    content CLOB(1000),
    write_date DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS  Board (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    key_name VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

/*
INSERT INTO Board (writer, title, content, write_date) VALUES('박세종', 'test1', 'test content1', '2014-02-08 12:34:56');
INSERT INTO Board (writer, title, content, write_date) VALUES('박세종', 'test2', 'test content2', '2014-02-08 12:34:57');
INSERT INTO Board (writer, title, content, write_date) VALUES('박세종', 'test3', 'test content3', '2014-02-08 12:34:58');
INSERT INTO Board (writer, title, content, write_date) VALUES('박세종', 'test4', 'test content4', '2014-02-08 12:34:59');
INSERT INTO Board (writer, title, content, write_date) VALUES('박세종', 'test5', 'test content5', '2014-02-08 12:34:00');
*/