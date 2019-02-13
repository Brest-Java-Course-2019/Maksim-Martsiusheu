-- Schema HR
DROP TABLE IF EXISTS emp;
DROP TABLE IF EXISTS dept;

-- department
CREATE TABLE dept (
  dept_id   SERIAL NOT NULL,
  dept_name VARCHAR(45) NULL,
  PRIMARY KEY (dept_id)
);

-- employee
CREATE TABLE emp (
  emp_id    SERIAL NOT NULL,
  firstname VARCHAR(45) NULL,
  lastname  VARCHAR(45) NULL,
  dept_id   INTEGER NOT NULL,
  salary    DECIMAL(10,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (emp_id),
  CONSTRAINT emp_to_dept_fk
    FOREIGN KEY (dept_id)
    REFERENCES dept (dept_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
