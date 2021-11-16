CREATE DATABASE IF NOT EXISTS test1 DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `studentname` varchar(45) NOT NULL,
  `project` varchar(36) NOT NULL,
  `totalCommits` int NOT NULL,
  `totalAddLines` int NOT NULL,
  `totalDeleteLines` int NOT NULL,
  totalChangeLines int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `project` (
  `pid` int NOT NULL,
  `pname` varchar(45) NOT NULL,
  `student1` varchar(36) NOT NULL,
  `student2` varchar(36) NOT NULL,
  `student3` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 CREATE TABLE `commitevent` (
  `pid` int NOT NULL,
  `commitDate` datetime NOT NULL,
  `project` varchar(36) NOT NULL,
  `totalAddLines` int NOT NULL,
  `totalDeleteLines` int NOT NULL,
  totalChangeLines int NOT NULL,
  primary key (pid,commitDate)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table `studentnickname`(
    `pid` int NOT NULL,
    `pname` varchar(45) NOT NULL,
    `nickname` varchar(45) not null ,
    primary key (pid)
);
INSERT INTO `studentnickname`
    (`pid`, `pname`, `nickname`)VALUES
    (1,'何淇', 'mucerhq'),
    (2,'武伊雪', 'LILY123-lang'),
    (3,'赵琰晴', 'zhao-yanqing'),
    (4,'马加辰', 'MichaelJackchen'),
    (5,'王君', '2994856495'),
    (6,'杨皓天', 'MuBai-Argo'),
    (7,'陈浩楠', 'chn0213'),
    (8,'赵浚博', 'BestJob2000'),
    (9,'赵松青', 'LibertyChaser'),
    (10,'毛泓涛', 'tsagaanbar'),
    (11,'南佳霖', 'Nan-J'),
    (12,'李金欣', 'TxjbWwdh'),
    (13,'庞基玮', 'pangjiwei');
-- 每位学生提交总数表（由该表产生项目小组总提交数以及每个同学的总提交数，以及显示一个同学基本信息）
INSERT INTO `student` (`id`, `studentname`, `project`, `totalCommits`, `totalAddLines`, `totalDeleteLines`,
                       totalChangeLines)VALUES
(1,'何淇', 'Student-Grade-Accessment-System',30,200,200,200),
(2,'武伊雪', 'Student-Grade-Accessment-System',30,200,200,200),
(3,'赵琰晴', 'Student-Grade-Accessment-System',30,200,200,200),
(4,'马加辰', 'irrasa',20,200,200,200),
(5,'王君', 'irrasa',20,200,200,200),
(6,'杨皓天', 'data-extraction-and-integration-tool',30,200,200,200),
(7,'陈浩楠', 'data-extraction-and-integration-tool',20,200,200,200),
(8,'赵浚博', 'data-extraction-and-integration-tool',20,200,200,200),
(9,'赵松青', 'project-grouping-system',20,200,200,200),
(10,'毛泓涛', 'project-grouping-system',80,200,200,200),
(11,'南佳霖', 'project-grouping-system',20,200,200,200),
(12,'李金欣', 'MUC2019CS-IRRASb',20,200,200,200),
(13,'庞基玮', 'MUC2019CS-IRRASb',20,200,200,200);

-- 每个项目及其成员
INSERT INTO `project` (`pid`, `pname`,`student1`,`student2`,`student3`) VALUES
(1,'Student-Grade-Accessment-System', '何淇', '赵琰晴','武伊雪'),
(2,'data-extraction-and-integration-tool', '杨皓天', '陈浩楠','赵浚博'),
(3,'project-grouping-system', '赵松青', '毛泓涛','南佳霖'),
(4,'irrasa', '马加辰', '王君','田湘云'),
(5,'irrasb', '李金欣', '庞基玮','');

-- 每个commit信息（一个同学会出现多次，一个项目也会出现多次，同时有当天的时间、提交数、修改行数等等信息）
INSERT INTO `commitevent` (`pid`, `commitDate`,`project`,`totalAddLines`,`totalDeleteLines`,totalChangeLines) VALUES
('赵琰晴','2021-10-21','Student-Grade-Accessment-System', 20, 10,100),
('赵琰晴','2021-10-21','Student-Grade-Accessment-System', 20, 10,100),
('何淇','2021-10-21','Student-Grade-Accessment-System', 20, 10,100),
('武伊雪','2021-10-21','Student-Grade-Accessment-System', 20, 10,100),
('赵琰晴','2021-10-22','Student-Grade-Accessment-System', 30, 10,100),
('何淇','2021-10-22','Student-Grade-Accessment-System', 20, 10,100),
('武伊雪','2021-10-22','Student-Grade-Accessment-System', 20, 10,100),
('杨皓天','2021-10-21','data-extraction-and-integration-tool', 30, 10,100),
('陈浩楠','2021-10-21','data-extraction-and-integration-tool', 20, 10,100),
('赵浚博','2021-10-21','data-extraction-and-integration-tool', 20, 10,100),
('杨皓天','2021-10-22','data-extraction-and-integration-tool', 30, 10,100),
('陈浩楠','2021-10-22','data-extraction-and-integration-tool', 20, 10,100),
('赵浚博','2021-10-22','data-extraction-and-integration-tool', 20, 10,100),
('赵松青','2021-10-22','project-grouping-system', 30, 10,100),
('毛泓涛','2021-10-22','project-grouping-system', 20, 10,100),
('南佳霖','2021-10-22','project-grouping-system', 20, 10,100),
('赵松青','2021-10-23','project-grouping-system', 30, 10,100),
('毛泓涛','2021-10-23','project-grouping-system', 20, 10,100),
('南佳霖','2021-10-23','project-grouping-system', 20, 10,100),
('马加辰','2021-10-22','irrasa', 30, 10,100),
('王君','2021-10-22','irrasa', 20, 10,100),
('田湘云','2021-10-22','irrasa', 20, 10,100),
('马加辰','2021-10-23','irrasa', 30, 10,100),
('王君','2021-10-23','irrasa', 20, 10,100),
('田湘云','2021-10-23','irrasa', 20, 10,100),
('李金欣','2021-10-22','irrasb', 20, 10,100),
('庞基玮','2021-10-22','irrasb', 20, 10,100),
('李金欣','2021-10-23','irrasb', 20, 10,100),
('庞基玮','2021-10-23','irrasb', 20, 10,100);
