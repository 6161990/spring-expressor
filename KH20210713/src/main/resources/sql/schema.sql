DROP TABLE IF EXISTS test_db;

CREATE TABLE test_db
(
    id  INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    old  INT,
    PRIMARY KEY (id)
);