create table project
(
    pid      int         not null,
    pname    varchar(45) not null,
    student1 varchar(36) not null,
    student2 varchar(36) not null,
    student3 varchar(36) not null
);

INSERT INTO test1.project (pid, pname, student1, student2, student3) VALUES (1, 'Student-Grade-Accessment-System', '何淇', '赵琰晴', '武伊雪');
INSERT INTO test1.project (pid, pname, student1, student2, student3) VALUES (2, 'DataGripTools', '杨皓天', '陈浩楠', '赵浚博');
INSERT INTO test1.project (pid, pname, student1, student2, student3) VALUES (3, 'Piggy-ProjG', '赵松青', '毛泓涛', '南佳霖');
INSERT INTO test1.project (pid, pname, student1, student2, student3) VALUES (4, 'MUC2019CS-IRRASa', '马加辰', '王君', '田湘云');
INSERT INTO test1.project (pid, pname, student1, student2, student3) VALUES (5, 'MUC2019CS-IRRASb', '李金欣', '庞基玮', '');