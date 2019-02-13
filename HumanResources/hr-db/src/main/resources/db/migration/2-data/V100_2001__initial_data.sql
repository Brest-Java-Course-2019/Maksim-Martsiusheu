INSERT INTO dept (dept_id, dept_name) VALUES (1, 'DEV');
INSERT INTO dept (dept_id, dept_name) VALUES (2, 'ACCOUNTING');
INSERT INTO dept (dept_id, dept_name) VALUES (3, 'MARKETING');
INSERT INTO dept (dept_id, dept_name) VALUES (4, 'HR');

INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (1, 'Ivan', 'Ivanov', 1, 500);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (2, 'Natashia', 'Konecny', 1, 350);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (3, 'Kourtney', 'Ostrem', 1, 470);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (5, 'Coletta', 'Stoltzfus', 2, 600);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (7, 'Alex', 'Sidorov', 2, 650);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (8, 'Sidorenko', 'Aliaksei', 3, 450);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (9, 'Olha', 'Ivanova', 3, 350);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (10, 'Ivan', 'Petrov', 1, 450);
INSERT INTO emp (emp_id, firstname, lastname, dept_id, salary) VALUES (11, 'Alex', 'Petrov', 2, 550);

SELECT setval('emp_emp_id_seq', (SELECT MAX(emp_id) FROM emp));

INSERT INTO emp (firstname, lastname, dept_id, salary) VALUES ('Alex', 'Kyznecov', 1, 455);
