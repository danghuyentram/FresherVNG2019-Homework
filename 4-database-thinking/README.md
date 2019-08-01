# Database thinking


<!-- TOC -->
 - [1. Các khái niệm về database](#1-các-khái-niệm-về-database)
 -  [2. SQL](#2-sql)   
    - [2.1 Chức năng SQL](#21-chức-năng-sql)    
     - [2.2 Lệnh trong SQL](#22-lệnh-trong-sql)        
   - [2.3 SQL Query Processing](#23-sql-query-processing)   
  - [3. NoSQL](#3-nosql)       
<!-- /TOC -->




# 1. Các khái niệm về database
![](https://kipalog.kaopiz.com/uploads/905c/bbf5/db_en.png)

Ở hình trên thì cho chúng ta thấy mô hình thường thấy của một ứng dụng trong thực tế hiện nay. Người dùng thông qua một ứng dụng sử dụng một hệ quản trị cơ sở dữ liệu(DBMS) để thực hiện các thao tác trực tiếp với database. 

- Database (cơ sở dữ liệu): là tập dữ liệu có tổ chức, thường được lưu trữ và truy cập điện tử từ hệ thống máy tính. 
- Database Management System (hệ quản trị cơ sở dữ liệu): là phần mềm tương tác với người dùng cuối, ứng dụng và chính cơ sở dữ liệu để thu thập và phân tích dữ liệu. Phần mềm DBMS bao gồm các tiện ích cốt lõi được cung cấp để quản trị cơ sở dữ liệu. Tổng cộng của cơ sở dữ liệu, DBMS và các ứng dụng liên quan có thể được gọi là "hệ thống cơ sở dữ liệu". Thông thường thuật ngữ "cơ sở dữ liệu" cũng được sử dụng để nói đến bất kỳ DBMS, hệ thống cơ sở dữ liệu hoặc ứng dụng nào được liên kết với cơ sở dữ liệu.
- Relational database management system (RDBMS): Hệ quản trị cơ sở dữ liệu quan hệ
- ACID: tập các tính chất của các giao tác(transaction) trong RDBMS
  - Atomicity (tính nguyên tố): tất cả transaction đều phải được thực hiện hết hoặc không thực hiện transaction nào cả
  - Consistency (tính nhất quán): một transaction kết thúc (thành công hay thất bại), CSDL phải ở trạng thái nhất quán (đảm bảo mọi ràng buộc toàn vẹn)
  - Isolation (tính cô lập): một transaction không quan tâm đến các transaction khác xử lí đông thời với nó
  - Durability (tính bền vững): mọi thay đổi mà transaction thực hiện trên CSDL phải được ghi nhận bền vững vào thiết bị lưu trữ
- Database user: Là những người trực tiếp thao tác với DB. DB users thì có thể chia làm 3 loại:
  - DB adminstactors: Là người quản lý và vận hành DB của bạn. Sử dụng các software và hardware để có thể điều khiển và kiểm soát tính khả dụng của hệ thống.
  - DB Designer: Trao đổi trực tiếp vs end-user để hiểu những thông tin họ cần. Từ đó thì có thể định nghĩa được các nội dung, cấu trúc, quan hệ và các chức năng của DB.
  - End-User: Người trực tiếp thao tác vs thông tin của DB( như là truy vấn, thay đổi, cập nhật dữ liệu...)
- Characteristics of Database Approach
![](https://kipalog.kaopiz.com/uploads/062a/c9e6/Screen%20Shot%202018-04-30%20at%2010.03.01%20AM.png)
  - Self-describing( tự mô tả): Catalog của DBMS sẽ lưu trữ các đặc tả về database. Cái đặc tả này thì gọi là meta-dât. Nó cho phép phần mềm DBMS làm việc được với nhiều DB.
  - Tách biệt giữa chương trình và dữ liệu: Được gọi là program-data independence. Cho phép thay đổi cấu trúc lưu trữ dữ liệu và các hoạt động mà không cần phải thay đổi các chương trình truy cập DBMS.
  - Data Abstraction: Một data model được dùng để ẩn chi tiết lưu trữ và trình bày cho người dùng với khung nhìn khái niệm của DB.
  - Hỗ trợ nhiều view của data: Mỗi user có thể nhìn database dưới cái view khác nhau, cái mà chỉ mô tả những dữ liệu mà người dùng đó quan tâm.
  - Chia sẻ dữ liệu và xử lý transtaction của nhiều user: Cho phép nhiều người có thể nhận và update database đồng thời.
  - Performance: Khôi phục và lưu trữ dữ liệu nhanh, xử lý được với dữ liệu dung lượng lớn.
- Data Model: Dùng để lưu trữ những khái niệm mô tả về cấu trúc của DB như là data types, relationships, constaints, sematics...
- Schema: Cấu trúc dữ liệu đáp ứng đầy đủ các chức năng mà người dùng muốn có.
- Instance: Dữ liệu của chính nó.

Link: https://kipalog.kaopiz.com/posts/DB_P1--Cac-khai-niem-co-ban-ve-Database



# 2. SQL
- SQL là viết tắt của Structured Query Language, là ngôn ngữ truy vấn mang tính cấu trúc.
- Nó được thiết kế để quản lý dữ liệu trong một hệ thống quản lý cơ sở dữ liệu quan hệ (RDBMS).
- SQL là ngôn ngữ cơ sở dữ liệu, được sử dụng để tạo, xóa trong cơ sở dữ liệu, lấy các hàng và sửa đổi các hàng, … 

Tất cả DBMS như MySQL, Oracle, MS Access, Sybase, Informix, Postgres và SQL Server sử dụng SQL như là ngôn ngữ cơ sở dữ liệu chuẩn.

- **SQL là ngôn ngữ hỏi có tính tương tác:** Người sử dụng có thể dễ dàng thông qua các trình tiện ích để gởi các yêu cầu dưới dạng các câu lệnh SQL đến cơ sở dữ liệu và nhận kết quả trả về từ cơ sở dữ liệu

- **SQL là ngôn ngữ lập trình cơ sở dữ liệu:** Các lập trình viên có thể nhúng các câu lệnh SQL vào trong các ngôn ngữ lập trình để xây dựng nên các chương trình ứng dụng giao tiếp với cơ sở dữ liệu

- **SQL là ngôn ngữ quản trị cơ sở dữ liệu :** Thông qua SQL, người quản trị cơ sở dữ liệu có thể quản lý được cơ sở dữ liệu, định nghĩa các cấu trúc lưu trữ dữ liệu, điều khiển truy cập cơ sở dữ liệu,…

- **SQL là ngôn ngữ cho các hệ thống khách/chủ (client/server):** Trong các hệ thống cơ sở dữ liệu khách/chủ, SQL được sử dụng như là công cụ để giao tiếp giữa các trình ứng dụng phía máy khách với máy chủ cơ sở dữ liệu.

- **SQL là ngôn ngữ truy cập dữ liệu trên Internet :** Cho đến nay, hầu hết các máy chủ Web cũng như các máy chủ trên Internet sử dụng SQL với vai trò là ngôn ngữ để tương tác với dữ liệu trong các cơ sở dữ liệu.

- **SQL là ngôn ngữ cơ sở dữ liệu phân tán :** Đối với các hệ quản trị cơ sở dữ liệu phân tán, mỗi một hệ thống sử dụng SQL để giao tiếp với các hệ thống khác trên mạng, gởi và nhận các yêu cầu truy xuất dữ liệu với nhau.

- **SQL là ngôn ngữ sử dụng cho các cổng giao tiếp cơ sở dữ liệu :** Trong một hệ thống mạng máy tính với nhiều hệ quản trị cơ sở dữ liệu khác nhau, SQL thường được sử dụng như là một chuẩn ngôn ngữ để giao tiếp giữa các hệ quản trị cơ sở dữ liệu SQL chuẩn bao gồm lệnh thường được sử dụng nhấ t khoảng 40 câu lệnh.


**SQL dùng để**
- Tạo cơ sở dữ liệu, bảng và view mới.
- Để chèn các bản ghi vào trong một cơ sở dữ liệu.
- Để xóa các bản ghi từ một cơ sở dữ liệu.
- Để lấy dữ liệu từ một cơ sở dữ liệu.

## 2.1 Chức năng SQL
- Với SQL, chúng ta có thể truy vấn Database theo nhiều cách khác nhau, bởi sử dụng các lệnh.
- Với SQL, người dùng có thể truy cập dữ liệu từ RDBMS.
SQL cho phép người dùng miêu tả dữ liệu.
- SQL cho phép người dùng định nghĩa dữ liệu trong một Database và thao tác nó khi cần thiết.
- Cho phép người dùng tạo, xóa Database và bảng.
- Cho phép người dùng tạo view, Procedure, hàm trong một Database.
- Cho phép người dùng thiết lập quyền truy cập vào bảng, thủ tục và view.

## 2.2 Lệnh trong SQL
Các lệnh SQL chuẩn để tương tác với Relational Database là CREATE, SELECT, INSERT, UPDATE, DELETE và DROP. Các lệnh này có thể được phân loại thành các nhóm dựa trên bản chất của chúng.

### DDL (Data Definition Language) – Ngôn ngữ định nghĩa dữ liệu
- Lệnh CREATE: Tạo một bảng, một View của bảng, hoặc đối tượng khác trong Database.

- Lệnh ALTER: Sửa đổi một đối tượng Database đang tồn tại, ví dụ như một bảng.

- Lệnh: Xóa toàn bộ một bảng, một View của bảng hoặc đối tượng khác trong một Database

### DML (Data Manipulation Language) – Ngôn ngữ thao tác dữ liệu
- Lệnh SELECT: Lấy các bản ghi cụ thể từ một hoặc nhiều bảng.

- Lệnh INSERT: Tạo một bản ghi.

- Lệnh UPDATE: Sửa đổi các bản ghi.

- Lệnh DELETE: Xóa các bản ghi.

### DCL (Data Control Language) – Ngôn ngữ điều khiển dữ liệu
- Lệnh GRANT: Trao một quyền tới người dùng.

- Lệnh REVOKE: Thu hồi quyền đã trao cho người dùng.

Link: https://it.die.vn/s/sql-structured-query-language/


### Syntax trong SQL
- select

```
SELECT cot1, cot2....cotN
FROM   ten_bang;
```

- distinct

```
SELECT DISTINCT cot1, cot2....cotN
FROM   ten_bang;
```

- where

```
SELECT cot1, cot2....cotN
FROM   ten_bang
WHERE  DIEU_KIEN;
```

- and / or

```
SELECT cot1, cot2....cotN
FROM   ten_bang
WHERE  DIEU_KIEN_1 {AND|OR} DIEU_KIEN_2;
```

- in

```
SELECT cot1, cot2....cotN
FROM   ten_bang
WHERE  ten_cot IN (gtri-1, gtri-2,...gtri-N);
```

- between
  
```
SELECT cot1, cot2....cotN
FROM   ten_bang
WHERE  ten_cot BETWEEN gtri-1 AND gtri-2;
```

- like

```
SELECT cot1, cot2....cotN
FROM   ten_bang
WHERE  ten_cot LIKE { PATTERN };
```

- order by

```
SELECT cot1, cot2....cotN
FROM   ten_bang
WHERE  DIEU_KIEN
ORDER BY ten_cot {ASC|DESC};
```

- group by

```
SELECT SUM(ten_cot)
FROM   ten_bang
WHERE  DIEU_KIEN
GROUP BY ten_cot;
```

- count
  
```
SELECT COUNT(ten_cot)
FROM   ten_bang
WHERE  DIEU_KIEN;
```

- having

```
SELECT SUM(ten_cot)
FROM   ten_bang
WHERE  DIEU_KIEN
GROUP BY ten_cot
HAVING (dieu kien la ham so hoc);
```

- create table

```
CREATE TABLE ten_bang(
cot1 kieu_du_lieu,
cot2 kieu_du_lieu,
cot3 kieu_du_lieu,
.....
cotN kieu_du_lieu,
PRIMARY KEY( mot hoac nhieu cot )
);
```

- drop table

```
DROP TABLE ten_bang;
```

- create index
  
```
CREATE UNIQUE INDEX ten_chi_muc
ON ten_bang ( cot1, cot2,...cotN);
```

- drop index
  
```
ALTER TABLE ten_bang
DROP INDEX ten_chi_muc;
```

- desc

```
DESC ten_bang;
```

- truncate table
  
```
TRUNCATE TABLE ten_bang;
```

- alter table

```
ALTER TABLE ten_bang {ADD|DROP|MODIFY} ten_cot {kieu_du_lieu};
```

- alter table rename

```
ALTER TABLE ten_bang RENAME TO ten_bang_moi;
```

- insert into 

```
INSERT INTO ten_bang( cot1, cot2....cotN)
VALUES ( giatri1, giatri2....giatriN);
```

- update

```
UPDATE ten_bang
SET cot1 = giatri1, cot2 = giatri2....cotN=giatriN
[ WHERE  DIEU_KIEN ];
```

- delete

```
DELETE FROM ten_bang
WHERE  {DIEU_KIEN};
```

- create database

```
CREATE DATABASE ten_co_so_du_lieu;
```

- drop database

```
DROP DATABASE ten_co_so_du_lieu;
```

- use

```
USE ten_co_so_du_lieu;
```

- rollback

```
ROLLBACK;
```

link: https://vietjack.com/sql/cu_phap_sql_co_ban.jsp

## 2.3 SQL Query Processing
Query processing bao gồm thông dịch câu truy vấn (query) ở mức high level xuống low level để có thể dùng ở mức vật lí của hệ thống file, tối ưu hóa câu query và thực thi thật sự câu query đó

Block diagram query processing:
![](https://cdncontribute.geeksforgeeks.org/wp-content/uploads/555-4.png)

Chi tiết diagram
![](https://cdncontribute.geeksforgeeks.org/wp-content/uploads/pp-5.png)

Gồm các bước sau:
1. Parser: kiểm tra câu truy vấn, sau đó chuyển câu truy vấn thành biểu thức đại số, gồm các bước sau:
   - Syntax check: kiểm tra cú pháp
    ```
    SELECT * FORM employee
    ```
    Câu query trên sẽ bị báo lỗi ngay bước này do chữ FROM bị viết sai

  - Sematic check: kiểm tra ngữ nghĩa. Kiểm tra câu truy vấn có nghĩa không. Ví dụ: câu query bao gồm một bảng không có trong database
  - Shared pool check: Mọi câu query đều có một hash code trong quá trình nó thực thi. Bước này kiểm trả xem có tồn tại hash code trong shared pool không. Nếu có thì database sẽ không thực hiện các bước bổ sung để tối ưu hóa và thực thi.
2. Optimizer: ở bước này, database phải thực hiện hard parse cho 1 câu lệnh DML duy nhất và thực hiện tối ưu hóa trong lúc parse. Database sẽ không tối ưu hóa ĐL trừ khi nó bao gồm một câu lệnh DML chẳng hạn như một subquery và cần phải tối ưu hóa nó.
Bước này lựa chọn cách thực hiện câu query sao cho câu query được keierm tra và thực thi hiệu quả nhất

3. Execution Engine: chạy câu query và trả ra kết quả

Link: https://www.geeksforgeeks.org/sql-query-processing/

## 3. NoSQL
NoSQL là tập hợp dữ liệu được biểu diễn dưới dạng key-value, document, wide column, graph database. Dữ liệu là dạng không chuẩn hóa. Hầu hết NoSQL lưu trữ thường không đảm báo tính ACID của transaction và favor eventual consitency

### BASE
BASE thường được dùng để đặc tả các tính chất của CSDL NoSQL. So sánh với CAP, BASE chọn tính khả dụng (availability) hơn là tính nhất quán (consistency)
- Basically available: hệ thống phải tuân thủ tính khả dụng
- Soft state: trạng thái của hệ thống có thể thay đổi theo thời gian, ngay cả khi không có input
- Eventual consistency: hệ thống sẽ trở nên nhất quán trong một khoảng thời gian, ngay cả khi hệ thông không nhận được input trong khoảng thời gian đó

### Key-value store

Key-value store cho phép O(1) đọc và ghi
thường được hỗ trợ bởi bộ nhớ hoặc SSD. Dữ liệu được lưu trữ với key như thứ tự từ điển, cho phép truy xuất hiệu quả trong phạm vi của key. Key-value store cho phép lưu một metadata như một value

Key-value stores cung cấp hiệu suất cao và thường được dùng cho các mô hình dữ liệu đơn giản hoặc dữ liệu thay đổi thương xuyên, chẳng hạn như trong vùng nhớ của cache. Vì nó chỉ cung cấp những operation giới hạn, nên những việc phức tạp hơn sẽ chuyển sang cho tầng ứng dụng nếu cần

### Document store

Document store tập trung xung quanh document(XML,JSON,binary,...), document sẽ lưu toàn bộ thông tin về object. 

Document store cung cấp API hoặc một ngôn ngữ truy vấn để truy vấn dựa vào cấu trúc bên trong của document đó. 

Document có thể được tổ chức thành collection, tag, metadata, directories. Mặc dù document có được tổ chức hay được gom nhóm lại chung với nhau thì document vẫn có những field hoàn toàn khác với những document còn lại.

Document store có tính linh hoạt cao và thường được dùng để làm việc với dữ liệu thỉnh thoảng thay đổi

### Wide column store
![](https://camo.githubusercontent.com/823668b07b4bff50574e934273c9244e4e5017d6/687474703a2f2f692e696d6775722e636f6d2f6e3136694f476b2e706e67)

Đơn vị lưu trữ cơ bản của wide column là column(name/value pair). 1 cột có thể được gom nhóm thành một họ các cột. Có thể truy xuất vào từng cột mà không cần row key, cột có cùng row key thì tạo thành một row. Mỗi value bao gồm timestamp cho versioning và cho giải quyết xung đột

Wide column store đem lại tính khả dụng cao và khả năng mở rộng cao. Nó thường được dùng cho những tập dữ liệu rất lớn

### Graph database
![](https://camo.githubusercontent.com/bf6508b65e98a7210d9861515833afa0d9434436/687474703a2f2f692e696d6775722e636f6d2f664e636c3635672e706e67)

Trong graph database, mỗi node là một record và mỗi cung nối là một quan hệ giữa hai node. Graph database được tối ưu hóa để biểu diễn những quan hệ phức tạp giữa nhiều khóa ngoại hay quan hệ nhiều nhiều

Graph database đem lại hiệu xuất cao với các mô hình dư liệu với các quan hệ phức tạp như mạng xã hội. Chúng relatively new và không được dùng rộng raaix, có thể khó để tìm một công cụ phát triển và tài nguyên. Nhiều graphs chỉ có thể truy cập bằng REST APIs



[MySQL](01-mysql.md)

[Redis](02-redis.md)