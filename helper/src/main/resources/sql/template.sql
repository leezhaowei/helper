SELECT cust_name FROM customer a WHERE NOT EXISTS (SELECT * FROM sales b WHERE a.cust_id=b.cust_id)


SELECT emp_name, order_date FROM employee a, sales b WHERE emp_no = sale_id AND a.emp_no IN (SELECT sale_id FROM sales GROUP BY sale_id HAVING COUNT(*) >= 3) ORDER BY emp_name

SELECT order_no, cust_id, sale_id, tot_amt FROM sales WHERE tot_amt= (SELECT MAX(tot_amt) FROM sales)


SELECT cust_id, sum(tot_amt) totprice FROM sales WHERE convert(CHAR(4), order_date, 120)='1996' GROUP BY cust_id

SELECT a.cust_id, cust_name, CONVERT(CHAR(10), order_date, 120), tot_amt FROM customer a LEFT OUTER JOIN sales b ON a.cust_id = b.cust_id ORDER BY a.cust_id, tot_amt DESC



CALL SYSPROC.ADMIN_CMD('REORG TABLE SCHEMA.TABLENAME');



