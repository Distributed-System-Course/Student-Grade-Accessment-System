CREATE DATABASE IF NOT EXISTS test DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE TABLE `data` (
  `name` char(10) NOT NULL,
  `date` datetime NOT NULL,
  `project` char(100) NOT NULL,
  `addLines` int NOT NULL,
  `deleteLines` int NOT NULL,
  `totalchangelines` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
INSERT INTO `goods` (`id`, `name`, `num`) VALUES
('何淇', '2021-10-22', 'Student-Grade-Accessment-System',100,10,300),
(, '羊毛衫', 20),
(3, '雪纺衫', 50),
(4, '裤子', 30),
(5, '高跟鞋', 23),
(6, '袜子', 60);