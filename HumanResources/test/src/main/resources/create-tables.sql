DROP TABLE IF EXISTS dept;
DROP TABLE IF EXISTS emp;

--department

CREATE TABLE dept(
  dept_id INT NOT NULL AUTO_INCREMENT,
  dept_name VARCHAR(45) NOT NULL,
  dept_description VARCHAR(45) NULL,
  PRIMARY KEY (dept_id)
);

--employee

CREATE TABLE emp(
  emp_id INT NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR (45) NOT NULL,
  salary INT NOT NULL,
  dept_id INT NOT NULL,
  PRIMARY KEY (emp_id),
  FOREIGN KEY (dept_id) REFERENCES dept(dept_id)
);
