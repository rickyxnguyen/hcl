-- top 3 sales amount
select concat_ws(' ',e.firstName, e.lastName) as 'Employee Name',
concat('$', format(sum(amount),2)) as "Total Sales Amt." from employees n 
inner join customers c on e.employeeNumber = c.salesRepEmployeeNumber
inner join payments using (customerNumber)
group by e.employeeNumber
order by sum(amount)
desc limit 3;

-- employees working remote
select concat_ws(' ', firstName, lastName) as "Employee Name",
officeCode, city from employees
left join offices using (officeCode)
where officeCode = 0 or officeCode=null;

-- top two sales people
select concat_ws(' ',employees.firstName, employees.lastName) as 'Employee Name',
concat('$', format(sum(orderdetails.quantityOrdered*orderdetails*priceEach),2)) as "Total Sales Booked" from employees
inner join customers  on (employeeNumber = salesRepEmployeeNumber)
inner join orders on (orders.customerNumber = customers.customerNumber)
inner join orderDetails on (orders.orderNumbwe = orderdetails.orderNumber)
group by employeeNumber
order by sum(orderdetails.quantityOrdered*orderdetails*priceEach)
desc limit 2;

-- most employees by office
select city, count(*) as "Employee Amt."
from employees e
join offices using (officeCode)
group by city
order by count(*) desc
limit 3;

-- shows office where employees are remote
select concat_ws(' ', firstName, lastName) as "Employee Name",
city as "City" from employees
left outer join offices using (officeCode)
group by officeCode, employeeNumber having officeCode = 2
union
select concat_ws(' ', firstName, lastName), city from employees
right outer join offices using (officeCode)
group by officeCode, employeeNumber having count(employeeNumber) = 0;

-- top 3 product lines
select productline, concat('$', format(sum(quantityOrdered*priceEach),2)) as "Sum"
from products
inner join orderdetails using (productCode)
group by productLine
order by sum(quantityOrdered*priceEach) desc
limit 3;

-- top 3 customers by purchase amount
select customers.customerNumber, concat(contactFirstName, ' ',contactLastName) as 'Customer Name',
concat("$", format(sum(amount), 2)) as "Total Spent" from customers
inner join payments
on customers.customerNumber = payments.customerNumber
group by customerNumber order by sum(amount) desc limit 3;

-- customers with no purchases in our DB by location
select concat_ws(', ',country, city) as "Location",
concat(contactFirstName, ' ',contactLastName) as 'Customer',
count(checkNumber) as '# of payments' from customers
left join payments using (customerNumber)
group by country, customerNumber having count(checkNumber) = 0
order by country, city asc;

-- number of product orders and their status
select productName as "Product",
sum(quantityOrdered) as "Total",
status as "Status" from products
inner join orderDetails using (productCode)
inner join orders using (orderNumber)
where orderDetails.productCode = products.productCode
group by orderDetails.productCode, status
order by productName asc, status desc;

-- top 2 products by amount
select productName as "Product",
concat("$",format(sum(quantityOrdered*priceEach),2)) as "Total Sales"
from products
inner join orderDetails using (productCode)
group by productCode
order by sum(quantityOrdered*priceEach) desc
limit 2;