INSERT INTO category (category_id, category_name, parent_id) VALUES (1, 'Bricks & Blocks', 0);
INSERT INTO category (category_id, category_name, parent_id) VALUES (2, 'Fittings', 0);
INSERT INTO category (category_id, category_name, parent_id) VALUES (3, 'Fixings', 0);
INSERT INTO category (category_id, category_name, parent_id) VALUES (4, 'Cement & Aggregates', 0);
INSERT INTO category (category_id, category_name, parent_id) VALUES (5, 'Bricks', 1);
INSERT INTO category (category_id, category_name, parent_id) VALUES (6, 'Blocks', 1);
INSERT INTO category (category_id, category_name, parent_id) VALUES (7, 'Screws', 2);

INSERT INTO product (prod_name, prod_amount, date_added, category_id) VALUES ('Red bricks', 2500, '2012-06-18', 5);
INSERT INTO product (prod_name, prod_amount, date_added, category_id) VALUES ('Aerated Concrete Block', 750, '2017-12-01', 6);
INSERT INTO product (prod_name, prod_amount, date_added, category_id) VALUES ('Dense Concrete Blocks', 4000, '2018-01-11', 6);
INSERT INTO product (prod_name, prod_amount, date_added, category_id) VALUES ('Dense Hollow Concrete Blocks', 2750, '2018-01-11', 6);
INSERT INTO product (prod_name, prod_amount, date_added, category_id) VALUES ('Wood Screws 3.0 x 20mm', 200, '2018-09-02', 7);
INSERT INTO product (prod_name, prod_amount, date_added, category_id) VALUES ('Wood Screws 4.0 x 20mm ', 170, '2018-09-02', 7);