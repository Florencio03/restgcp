CREATE DATABASE  IF NOT EXISTS `session_directory`;
USE `session_directory`;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `id`  char(36) NOT NULL,
  `created_at` DATETIME NOT NULL, 
  `mood_summary` varchar(100) DEFAULT NULL, 
  `dominant_emotion` varchar(30) DEFAULT NULL,
  `marking_status` varchar(30) DEFAULT NULL, 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data example for table `session`
--

INSERT INTO `session` VALUES 
	('91f9d1f0-3c70-4ed3-88d7-4cc9fc108e29', now(), 'Mejor que ayer, aunque con dudas.','anxiety', 'spotted');