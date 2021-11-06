CREATE DATABASE IF NOT EXISTS test DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE TABLE `data` (
  `name` char(10) NOT NULL,
  `date` datetime NOT NULL,
  `project` char(100) NOT NULL,
  `addLines` int NOT NULL,
  `deleteLines` int NOT NULL,
  `totalchangelines` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
INSERT INTO `data` (`name`, `date`,`project`,`addLines`,`deleteLines`,`totalchangelines`) VALUES
('何淇', '2021-10-22', 'Student-Grade-Accessment-System',100,10,300),
('武伊雪', '2021-10-21', 'Student-Grade-Accessment-System',100,10,300),
('赵琰晴', '2021-10-20', 'Student-Grade-Accessment-System',100,10,300),
('马加辰', '2021-10-19', 'irrasa',100,10,300),
('王君', '2021-10-18', 'irrasa',100,10,300),
('杨皓天', '2021-10-17', 'data-extraction-and-integration-tool',100,10,300),
('陈浩楠', '2021-10-16', 'data-extraction-and-integration-tool',100,10,300),
('赵浚博', '2021-10-15', 'data-extraction-and-integration-tool',100,10,300),
('赵松青', '2021-10-14', 'project-grouping-system',100,10,300),
('毛泓涛', '2021-10-13', 'project-grouping-system',100,10,300),
('南佳霖', '2021-10-12', 'project-grouping-system',100,10,300),
('李金欣', '2021-10-23', 'irrasb',100,10,300),
('庞基玮', '2021-10-24', 'irrasb',100,10,300);