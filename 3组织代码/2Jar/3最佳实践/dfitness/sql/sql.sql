CREATE TABLE `aus_demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `created` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4;

INSERT INTO `aus_demo`(username, password, phone,created)
VALUES ('zhenyun.su', '111222', '13795467304', now());

create unique index aus_demo_un1 on aus_demo(username);