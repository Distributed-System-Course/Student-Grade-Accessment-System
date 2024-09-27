create table student
(
    id               int         not null,
    studentname      varchar(45) not null,
    project          varchar(36) not null,
    totalCommits     int         not null,
    totalAddLines    int         not null,
    totalDeleteLines int         not null,
    totalChangeLines int         not null
);

INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (1, '何淇', 'Student-Grade-Accessment-System', 32, 293578, 187151, 480729);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (2, '武伊雪', 'Student-Grade-Accessment-System', 9, 377288, 0, 377288);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (3, '赵琰晴', 'Student-Grade-Accessment-System', 11, 398003, 2, 398005);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (4, '马加辰', 'MUC2019CS-IRRASa', 8, 1599, 68, 1667);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (5, '王君', 'MUC2019CS-IRRASa', 0, 0, 0, 0);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (6, '杨皓天', 'DataGripTools', 25, 99473, 58930, 158403);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (7, '陈浩楠', 'DataGripTools', 6, 77753, 77785, 155538);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (8, '赵浚博', 'DataGripTools', 10, 372, 65, 437);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (9, '赵松青', 'Piggy-ProjG', 8, 530, 54, 584);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (10, '毛泓涛', 'Piggy-ProjG', 31, 3408, 543, 3951);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (11, '南佳霖', 'Piggy-ProjG', 5, 805, 4, 809);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (12, '李金欣', 'MUC2019CS-IRRASb', 4, 1214, 520, 1734);
INSERT INTO test1.student (id, studentname, project, totalCommits, totalAddLines, totalDeleteLines, totalChangeLines) VALUES (13, '庞基玮', 'MUC2019CS-IRRASb', 0, 0, 0, 0);