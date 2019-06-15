# LowSqlDB使用手册

### java版本

在`java 9.0.4`的环境下测试可以正常运行 

### 数据库运行

`java -jar LowSqlDBServer.jar`

`java -jar LowSqlDBClient.jar`

分别启动数据库服务器和客户端

### 客户端模式选择

客户端正常启动后，可以输出三个命令指定相应模式:

1. `import XXX.XXX`，其中`XXX.XXX`是当前目录下某个`sql`文件，服务器会运行该文件中的`sql`命令。
2. `sql`，进入该模式后，可以输入`sql`语句，服务器会执行输入的`sql`语句。注意该模式下，`sql`语句以换行为结尾（而不是以分号结尾）。在`sql`模式下输入`exit `可以退出`sql`模式。
3. `exit`，客户端与服务器断开连接。

执行后的结果会在客户端控制台输出，并且存储在`result.txt`中（以防执行结果过长导致控制台无法完全显示）

### 支持的指令

1. 支持数据类型：Int,String,Varchar,Long,Float,Double,Char
2. 创建数据库：CREATE DATABASE *dbName*
3. 删除数据库：DROP DATABASE *dbName*
4. 切换数据库：USE DATABASE *dbName*
5. 查询所有数据库：SHOW DATABASES
6. 查询一个数据库中所有表名 SHOW DATABASE *dbName*
7. 查询一个表的Schema SHOW TABLE tableName
8. 其它在大作业说明文档中出现的指令

### 测试文件

根据LowSqlDB支持的指令，我们编写了`fulltest.sql`进行测试，测试创建的表名为`avengers`，如果先前的测试在相同的数据库实例中也创建了名为`avengers`的表，请先删除该表（或者直接清空data,index,system下的文件）。测试流程大致如下：

1. 创建新的数据库实例。
2. 创建新表，删除该表，再次创建该表。
3. 插入10000条数据。
4. 删除5000条数据。
5. 更新2000条数据。
6. 创建另一个表，插入9条数据。
7. 显示表的Schema。
8. 两表join的select，以及其它的一些select。
9. 显示当前数据库的所有表。

### 特别说明

1. `data`文件夹下存储插入的数据，`index`文件夹下存储索引数据，`system`文件夹下存储数据库元数据。对数据库的**误操作或非法操作**可能导致元数据、索引、数据逻辑不一致的情况，可能导致`Server`出错，此时只需删除这三个文件夹下的所有文件，重新启动`Server`即可。
2. 每次操作后，`Server`会返回两个时间，一个是`Total running time`，该时间表示全部操作的总耗时，包括读取import文件，socket传输时间，服务器执行相应指令的时间等；另一个是`Antlr parser time`，该时间表示数据库服务器使用`Antlr`解析语句的耗时。`Total running time`是总耗时，包含`Antlr parser time`。
3. 数据库服务器在没有数据库实例时，会创建一个默认数据库实例，名为`test`。