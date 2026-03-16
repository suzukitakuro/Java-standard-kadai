CREATE TABLE IF NOT EXISTS students
(
  id INT AUTO_INCREMENT PRIMARY KEY ,
  name varchar(100) NOT NULL,
  kanaName varchar(100) NOT NULL,
  nickname varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  area varchar(100) NOT NULL,
  age INT,
  sex varchar(50),
  remark TEXT,
  isDeleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
  courseId INT AUTO_INCREMENT PRIMARY KEY,
  courseName varchar(100) NOT NULL,
  courseStart timestamp,
  courseEnd timestamp,
  id varchar(100)
);