CREATE DATABASE IF NOT EXISTS test1 DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `studentname` varchar(45) NOT NULL,
  `project` varchar(36) NOT NULL,
  `totalCommits` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `project` (
  `pid` int NOT NULL,
  `pname` varchar(45) NOT NULL,
  `student1` varchar(36) NOT NULL,
  `student2` varchar(36) NOT NULL,
  `student3` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `commitEvent`(
    `pid` int NOT NULL,
    `pname` varchar(45) NOT NULL,
    `commitDate` DATE ,
    `totalCommit` int NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 每位学生提交总数表（由该表产生项目小组总提交数以及每个同学的总提交数，以及显示一个同学基本信息）
INSERT INTO `student` (`id`, `studentname`,`project`,`totalCommits`) VALUES
(1,'何淇', 'Student-Grade-Accessment-System',30),
(2,'武伊雪', 'Student-Grade-Accessment-System',30),
(3,'赵琰晴', 'Student-Grade-Accessment-System',30),
(4,'马加辰', 'irrasa',20),
(5,'王君', 'irrasa',20),
(6,'杨皓天', 'data-extraction-and-integration-tool',30),
(7,'陈浩楠', 'data-extraction-and-integration-tool',20),
(8,'赵浚博', 'data-extraction-and-integration-tool',20),
(9,'赵松青', 'project-grouping-system',20),
(10,'毛泓涛', 'project-grouping-system',80),
(11,'南佳霖', 'project-grouping-system',20),
(12,'李金欣', 'irrasb',20),
(13,'庞基玮', 'irrasb',20);

-- 每个项目及其成员
INSERT INTO `project` (`pid`, `pname`,`student1`,`student2`,`student3`) VALUES
(1,'Student-Grade-Accessment-System', '何淇', '赵琰晴','武伊雪'),
(2,'data-extraction-and-integration-tool', '杨皓天', '陈浩楠','赵浚博'),
(3,'project-grouping-system', '赵松青', '毛泓涛','南佳霖'),
(4,'irrasa', '马加辰', '王君','田湘云'),
(5,'irrasb', '李金欣', '庞基玮','');

-- 每个commit信息（一个同学会出现多次，一个项目也会出现多次，同时有当天的时间、提交数、修改行数等等信息）