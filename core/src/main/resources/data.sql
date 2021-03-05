DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE `users` (
  `user_id` INT AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)

);
 
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
);
 
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
   FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
   FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

  INSERT INTO `roles` (`name`) VALUES ('Customers');
  INSERT INTO `roles` (`name`) VALUES ('Admin');


  INSERT INTO `users` (`user_name`,`first_name`, `last_name`,`password`, `enabled`) VALUES ('user', 'user','user','$2y$12$FfLlQqXfeKxMHC/178KpcePqqgRyyq8s3BwdfCEkhUj9Y0uJeTv6O', '1');
  INSERT INTO `users` (`user_name`,`first_name`, `last_name`, `password`, `enabled`) VALUES ('admin','admin', 'admin','$2y$12$YWbtJzO/upoBDbhLG3OOo.U7mKSRL3F6Zc6jRzKvsMAmPxX4Q3Uhu', '1');

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 2);
