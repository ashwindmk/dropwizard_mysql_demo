# Dropwizard MySQL Demo

### MySQL Database

1. Install and start MySQL server on your system.

2. Create database

```shell
CREATE DATABASE employeedb;
```

3. Create table

```shell
use employeedb;
```

```shell
CREATE TABLE IF NOT EXISTS employee (
    id int (11) unsigned auto_increment primary key not null,
    name varchar (50) not null,
    department varchar (50) not null,
    salary int (11) unsigned not null
);
```

### Set environment variables

```
MYSQL_USER: xxxxx
MYSQL_PASSWORD: xxxxxx
```


### Create JAR

```shell
mvn clean package
```


### Start server

```shell
java -jar target/dropwizard-mysql-demo-0.0.1.jar server config.yml
```


### Usage

**1. Add new employee**
```
POST: http://localhost:8080/employee
{
   "name": "anurag",
   "department": "backend",
   "salary": 200000
}
```

**2. Get all employees**
```
GET: http://localhost:8080/employee
```

**3. Get employee by ID**
```
GET: http://localhost:8080/employee/1
```

**4. Delete employee by ID**
```
DELETE: http://localhost:8080/employee/1
```

**5. Update employee by ID**
```
PUT: http://localhost:8080/employee/1
{
    "salary": 15000
}
```
