# Database thinking

# 1. Các khái niệm về database
![](https://kipalog.kaopiz.com/uploads/905c/bbf5/db_en.png)

Ở hình trên thì cho chúng ta thấy mô hình thường thấy của một ứng dụng trong thực tế hiện nay. Người dùng thông qua một ứng dụng sử dụng một hệ quản trị cơ sở dữ liệu(DBMS) để thực hiện các thao tác trực tiếp với database. 
- Database (cơ sở dữ liệu): là tập dữ liệu có tổ chức, thường được lưu trữ và truy cập điện tử từ hệ thống máy tính. K
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


# 3. My SQL
## 3.1 Storage Engine
### InnoDB
Đây là Storage Engine mặc định trong MySQL 5.7. InnoDB là một Storage Engine transaction-safe (tuân thủ ACID) cho MySQL có các commit, rollback và khả năng khôi phục lỗi để bảo vệ dữ liệu người dùng. Row-level locking của InnoDB và kiểu nonlocking read của Oracle-style làm tăng sự đồng thời và hiệu suất của nhiều người dùng. InnoDB lưu trữ dữ liệu người dùng trong các clustered indexes để giảm I/O cho các truy vấn thông thường dựa trên các primary key. Để duy trì tính toàn vẹn của dữ liệu, InnoDB cũng hỗ trợ các ràng buộc toàn vẹn Foreign Key.

### MyISAM
Table-level locking giới hạn hiệu suất read/write dữ liệu, vì vậy nó thường được sử dụng cho các công việc read-only hoặc read-mostly trong các cấu hình Web và lưu trữ dữ liệu.

### So sánh InnoDB và MyISAM
- InnoDB phục hồi từ một vụ crash hoặc tắt máy bất ngờ bằng cách phát lại các bản ghi log của nó. MyISAM phải quét và sửa chữa đầy đủ hoặc xây dựng lại các chỉ mục hoặc bảng có thể đã được cập nhật nhưng không đầy đủ sang ổ cứng. Kể từ khi phương pháp InnoDB là khoảng thời gian cố định trong khi thời gian MyISAM phát triển với kích thước của các tập tin dữ liệu, InnoDB cung cấp sẵn sàng hơn khi kích thước cơ sở dữ liệu phát triển.
- InnoDB, với innodb_flush_log_at_trx_commit đặt thành 1, ghi nhật ký transactioni sau mỗi transaction, cải thiện đáng kể độ tin cậy. MyISAM phải được chạy trên đầu trang của một hệ thống tập tin journal đầy đủ, chẳng hạn như ext4 gắn kết với data=journal, để cung cấp khả năng phục hồi tương tự chống lại các tập tin dữ liệu hỏng. (Journal có thể được đặt trên một thiết bị SSD để cải thiện hiệu năng MyISAM, tương tự, nhật ký InnoDB có thể được đặt trên một hệ thống tập tin không ghi nhật ký như ext2 chạy trên một SSD để tăng hiệu suất tương tự. )
- InnoDB có thể chạy ở chế độ mà nó có độ tin cậy thấp hơn nhưng trong một số trường hợp hiệu năng cao hơn. Thiết lập innodb_flush_log_at_trx_commit đến 0 chuyển sang chế độ mà transaction không commit với disk trước khi kiểm soát được trả lại cho người gọi. Thay vào đó, disk flushes xảy ra trên một bộ đếm thời gian.
- InnoDB tự động nhóm lại nhiều chèn đồng thời và flushes chúng vào đĩa cùng một lúc MyISAM dựa vào bộ nhớ cache khối hệ thống tập tin cho bộ nhớ đệm đọc cho các hàng dữ liệu và các chỉ mục, trong khi InnoDB thực hiện điều này trong chính công cụ, kết hợp các cache hàng với các cache chỉ mục.
- InnoDB sẽ lưu trữ các hàng trong trật tự primary nếu có, khác thứ tự duy nhất thứ tự then chốt. InnoDB sẽ sử dụng một phím số nguyên duy nhất được tạo ra bên trong và sẽ lưu trữ các hồ sơ theo thứ tự chèn, vì MyISAM nào. Ngoài ra, một trường khoá chính có thể tự động được sử dụng để đạt được hiệu quả tương tự.
- InnoDB cung cấp lưu trữ trang nén LZW có thể cập nhật cho cả dữ liệu và chỉ mục. MyISAM bảng nén không thể được cập nhật.
- Khi hoạt động ở các chế độ tuân thủ ACID đầy đủ, InnoDB phải thực hiện một tuôn ra đĩa ít nhất một lần cho mỗi giao dịch, mặc dù nó sẽ kết hợp flushes cho chèn từ nhiều kết nối. Đối với các ổ cứng hoặc mảng điển hình, điều này sẽ áp đặt giới hạn khoảng 200 giao dịch cập nhật mỗi giây. Đối với các ứng dụng yêu cầu tỷ lệ giao dịch cao hơn, bộ điều khiển đĩa với bộ nhớ đệm ghi và sao lưu pin sẽ được yêu cầu để duy trì tính toàn vẹn của giao dịch. InnoDB cũng cung cấp một số chế độ làm giảm hiệu ứng này, tự nhiên dẫn đến mất bảo đảm toàn vẹn giao dịch, mặc dù vẫn giữ được độ tin cậy cao hơn MyISAM. MyISAM không có chi phí này, nhưng chỉ vì nó không hỗ trợ giao dịch.
- MyISAM sử dụng khóa mức bảng để cập nhật và xóa các hàng hiện có, với một tùy chọn để nối các hàng mới thay vì lấy khóa và chèn chúng vào không gian trống. InnoDB sử dụng khóa cấp hàng. Đối với các ứng dụng cơ sở dữ liệu lớn, nơi nhiều hàng thường được cập nhật, cấp hàng khóa là rất quan trọng bởi vì một khóa bảng duy nhất giảm đáng kể đồng thời trong cơ sở dữ liệu.
- Cả InnoDB và MyISAM đều hỗ trợ tìm kiếm toàn văn, với InnoDB đạt được sự hỗ trợ chỉ mục toàn văn trong MySQL 5.6.4, nhưng kết quả có thể khác biệt đáng kể.

Link : https://viblo.asia/p/gioi-thieu-cac-storage-engine-trong-mysql-Eb85oEb8Z2G

## 3.2 Data types
Trong MySQL, kiểu dữ liệu được chia làm ba loại chính: numeric, string, date time

### Numeric
![](capture_screen/cap1.png)

### String
![](capture_screen/cap2.png)

### Date time
![](capture_screen/cap3.png)

link: http://webcoban.vn/mysql/cac-kieu-du-lieu-trong-mysql.html

### utf8mb4
Theo định nghĩa của mysql

```
The utfmb4 character set has these characteristics:

Supports BMP and supplementary characters.

Requires a maximum of four bytes per multibyte character.
```

utf8mb4 bao gồm utf8mb3 

```
utf8 is an alias for the utf8mb3 character set.

The utf8mb3 character set has these characteristics:

・Supports BMP characters only (no support for supplementary characters)

・Requires a maximum of three bytes per multibyte character.

Applications that use UTF-8 data but require supplementary character support should use utf8mb4 rather than utf8mb3 (see Section 10.9.1, “The utf8mb4 Character Set (4-Byte UTF-8 Unicode Encoding)”).
```

- Đối với kí tự BMP, utf8mb4 và utf8mb3 lưu trữ giống hệt nhau: code value, encoding, length
- Đối với các kí tự bổ sung, utf8mb4 cần 4 byte đê lưu, trong khi utf8mb3 không thể lưu hết kí tự nếu nó có độ dài lớn hơn 3 byte và sẽ gây ra lỗi.

Vậy nên cần cover từ utf8mb3 sang utf8mb4 như sau:

```
SELECT CONCAT(utf8mb3_col, utf8mb4_col);
```
hoặc

```
SELECT * FROM utf8mb3_tbl, utf8mb4_tbl
WHERE utf8mb3_tbl.utf8mb3_col = utf8mb4_tbl.utf8mb4_col;
```
link: https://dev.mysql.com/doc/refman/5.5/en/charset-unicode-utf8mb4.html


## 3.3 Transaction
Có thể hiểu Transaction là một tiến trình xử lý có xác định điểm đầu và điểm cuối, được chia nhỏ thành các operation (phép thực thi) , tiến trình được thực thi một cách tuần tự và độc lập các operation đó theo nguyên tắc hoặc tất cả đều thành công hoặc một operation thất bại thì toàn bộ tiến trình thất bại. Nếu việc thực thi một operation nào đó bị fail đồng nghĩa với việc dữ liệu phải rollback về trạng thái ban đầu.

## ACID: tập các tính chất của các giao tác(transaction) trong RDBMS
  - Atomicity (tính nguyên tố): tất cả transaction đều phải được thực hiện hết hoặc không thực hiện transaction nào cả
  - Consistency (tính nhất quán): một transaction kết thúc (thành công hay thất bại), CSDL phải ở trạng thái nhất quán (đảm bảo mọi ràng buộc toàn vẹn)
  - Isolation (tính cô lập): một transaction không quan tâm đến các transaction khác xử lí đông thời với nó
  - Durability (tính bền vững): mọi thay đổi mà transaction thực hiện trên CSDL phải được ghi nhận bền vững vào thiết bị lưu trữ

## Rủi ro khi thực thi transaction
Có ba loại rủi ro chính khiến việc thực thi một transaction có thể bị fail.
- Việc thực thi operation bị lỗi: 
dẫn tới transaction bị lỗi
- Vấn đề về phần cứng và mạng: việc phần cứng hoặc mạng có vấn đề trong lúc đang thực thi transaction sẽ dẫn đến tiến trình xử lý thất bại.
- Các vấn đề với dữ liệu dùng chung: Đây là vấn đề khó nhất. Rõ ràng data là một tài nguyên dùng chung, do đó sẽ có những nguy cơ mà transaction gặp phải khi xử lý dữ liệu dùng chung này. Ta sẽ xem xét kỹ hơn dưới đây. Như chúng ta đã biết, phần mềm viết ra là để xử lý dữ liệu, 2 operations (phép) căn bản của phần mềm với dữ liệu là đọc và ghi (read và write) trong đó phép write lại được chia nhỏ thành 3 operations nhỏ hơn là insert (thêm mới), update (sửa), delete (xóa). Dữ liệu là một tài nguyên dùng chung, nếu như có nhiều tiến trình xử lý đồng thời thực hiện các phép trên dữ liệu sẽ xảy ra những rủi ro: write-write, write-read,… việc dữ liệu ghi cùng lúc dẫn tới hỏng dữ liệu hoặc dữ liệu đọc ra không đồng nhất với dữ liệu mới ghi vào,… sẽ đề cập kỹ hơn trong phần tiếp theo dưới đây.

link: https://viblo.asia/p/tim-hieu-ve-transaction-trong-mysql-RnB5pnxGZPG


## Isolation
### Dirty read
Đọc dữ liệu rác. 
vd:
![](capture_screen/cap4.png)
Sau khi T2 rollback thì giá trị mà T1 đọc sau khi T2 ghi xuống là không tồn tại

### Non-repeatable reads
vd:
![](capture_screen/cap5.png)

Giá trị của T1 trong 2 lần đọc là khác nhau do T2 đã thay đổi giá trị đó

### Phantom reads
vd: 
![](capture_screen/cap6.png)

### Lost update

### Các mức level isolation
![](capture_screen/cap7.png)

# 4. Redis
## 4.1 Install
## 4.2 Data Type
Redis lưu data dưới dạng key-value.

### String
![](https://techtalk.vn/techtalk_blog/public/picture/img/lephuocthach/1509072933.png)

Value có thể là string với mọi kiểu, ta có thể lưu một file hình jpeg bên trong value. Value không lớn hơn 512MB

  ```
> set mykey somevalue
OK
> get mykey
"somevalue"
  ```

- INCR, DECR, INCRBY: parse string thành integer để thực hiện các lệnh tăng, giảm,..
- APPEND
  
  ```
  redis> APPEND ts "0043"
  (integer) 4
  redis> APPEND ts "0035"
  (integer) 8
  redis> GETRANGE ts 0 3
  "0043"
  redis> GETRANGE ts 4 7
  "0035"
  redis> 
  ```
- Dùng String như vector truy xuất ngẫu nhiên bằng GETRANGE
  
  ```
  redis> SET mykey "This is a string"
  "OK"
  redis> GETRANGE mykey 0 3
  "This"
  redis> GETRANGE mykey -3 -1
  "ing"
  redis> GETRANGE mykey 0 -1
  "This is a string"
  redis> GETRANGE mykey 10 100
  "string"
  redis> 
  ```
  và SETRANGE

  ```
  redis> SET key1 "Hello World"
  "OK"
  redis> SETRANGE key1 6 "Redis"
  (integer) 11
  redis> GET key1
  "Hello Redis"
  redis> 
  ```
- Mã hóa dữ liệu bằng GETBIT và SETBIT
  
  ```
  redis> SETBIT mykey 7 1
  (integer) 0
  redis> GETBIT mykey 0
  (integer) 0
  redis> GETBIT mykey 7
  (integer) 1
  redis> GETBIT mykey 100
  (integer) 0
  redis> 
  ```


### List
![](https://techtalk.vn/techtalk_blog/public/picture/img/lephuocthach/1509072947.png)

Danh sách liên kết của các string. 
Max length của 1 list là 2^32-1 phần tử
Redis List dựa trên độ phức tạp thời gian là hằng số cho thao tác thêm, xóa phần tử ở đầu hoặc đuôi. Truy cập các phần tử ở gần đầu hoặc đuôi rất nhanh, nhưng truy cập các phần tử nằm giữa những list lớn rất chậm, có thể tốn thời gian O(N)

  ```
> rpush mylist A
(integer) 1
> rpush mylist B
(integer) 2
> lpush mylist first
(integer) 3
> lrange mylist 0 -1
1) "first"
2) "A"
3) "B"
  ```

- Mô hình timeline cho mạng xã hội, dùng LPUSH phần tử mới vào timeline của user, dùng LRANGE để lấy phần tử mới vừa thêm vào
  
  ```
  redis> LPUSH mylist "world"
  (integer) 1
  redis> LPUSH mylist "hello"
  (integer) 2
  redis> LRANGE mylist 0 -1
  1) "hello"
  2) "world"
  redis> 
  ```
- Dùng LPUSH cùng với LTRIM tạo 1 list với số lượng phần tử cho trước
  
  ```
  LPUSH mylist someelement
  LTRIM mylist 0 99
  ```
  tạo list mylist với phần tử là someelement và có thể lưu 100 phần tử

- List có thể dùng để truyền các message
essage

### Set
![](https://techtalk.vn/techtalk_blog/public/picture/img/lephuocthach/1509072961.png)

Set là tập các string không theo thứ tự. Có thểm thêm, xóa, tìm kiếm phần tử với thời gian hằng số O(1). Redis Set không có phép các phần tử bị trùng nhau. Số lượng phần tử lớn nhất của set là 2^32-1

  ```
  > sadd myset 1 2 3
  (integer) 3
  > smembers myset
  1. 3
  2. 1
  3. 2
  ```
- Theo dõi các những phần tử phân biệt bằng Redis Set. VD: muốn biết tất cả các IP phân biệt truy cập blog, dùng SADD mỗi khi có process truy cập vào, vì IP bị trùng sẽ không được thêm lại
  
  ```
  redis> SADD myset "Hello"
  (integer) 1
  redis> SADD myset "World"
  (integer) 1
  redis> SADD myset "World"
  (integer) 0
  redis> SMEMBERS myset
  1) "World"
  2) "Hello"
  redis> 
  ```

- SINTER: trả về phần tử chung của các tập
  
  ```
  redis> SADD key1 "a"
  (integer) 1
  redis> SADD key1 "b"
  (integer) 1
  redis> SADD key1 "c"
  (integer) 1
  redis> SADD key2 "c"
  (integer) 1
  redis> SADD key2 "d"
  (integer) 1
  redis> SADD key2 "e"
  (integer) 1
  redis> SINTER key1 key2
  1) "c"
  redis> 
  ```
- SRANDMEMBER: trả về random phần tử của set, có thể trả về các phần tử trùng nhau
  
  ```
  redis> SADD myset one two three
  (integer) 3
  redis> SRANDMEMBER myset
  "two"
  redis> SRANDMEMBER myset 2
  1) "two"
  2) "three"
  redis> SRANDMEMBER myset -5
  1) "three"
  2) "three"
  3) "two"
  4) "one"
  5) "three"
  redis>
  ```
- SPOP: giống SRANMEMBER nhưng khi trả về thì remove phần tử đó ra khỏi set
  
  ```
  redis> SADD myset "one"
  (integer) 1
  redis> SADD myset "two"
  (integer) 1
  redis> SADD myset "three"
  (integer) 1
  redis> SPOP myset
  "one"
  redis> SMEMBERS myset
  1) "three"
  2) "two"
  redis> SADD myset "four"
  (integer) 1
  redis> SADD myset "five"
  (integer) 1
  redis> SPOP myset 3
  1) "two"
  2) "three"
  3) "four"
  redis> SMEMBERS myset
  1) "five"
  redis> 
  ```
### Hash
![](https://techtalk.vn/techtalk_blog/public/picture/img/lephuocthach/1509072991.png)

Lưu trữ hash table các cặp key-value, trong đó key được sắp xếp ngẫu nhiên, không theo thứ tự. Redis hỗ trợ các thao tác thêm, đọc, xóa từng phần tử, cũng như đọc tất cả giá trị.
Mỗi hash có thể lưu 2^32-1 cặp giá trị

  ```
  > hmset user:1000 username antirez birthyear 1977 verified 1
  OK
  > hget user:1000 username
  "antirez"
  > hget user:1000 birthyear
  "1977"
  > hgetall user:1000
  1) "username"
  2) "antirez"
  3) "birthyear"
  4) "1977"
  5) "verified"
  6) "1"
  ```


### Sorted set
![](https://techtalk.vn/techtalk_blog/public/picture/img/lephuocthach/1509072973.png)

Redis sorted set tương tự như Redis set, là tập các string không lặp lại. Điểm khác biêt đó là sorted set được truy xuất, sắp xếp dựa vào score. Khi các phần tử phân biệt với nhau thì score có thể lặp lại.

  ```
  > zadd hackers 1940 "Alan Kay"
  (integer) 1
  > zadd hackers 1957 "Sophie Wilson"
  (integer) 1
  > zadd hackers 1953 "Richard Stallman"
  (integer) 1
  > zadd hackers 1949 "Anita Borg"
  (integer) 1
  > zadd hackers 1965 "Yukihiro Matsumoto"
  (integer) 1
  > zadd hackers 1914 "Hedy Lamarr"
  (integer) 1
  > zadd hackers 1916 "Claude Shannon"
  (integer) 1
  > zadd hackers 1969 "Linus Torvalds"
  (integer) 1
  > zadd hackers 1912 "Alan Turing"
  (integer) 1
  ```

Sorted set giúp thêm, xóa, sửa phần tử rất nhanh (thời gian log với số phần tử). Dùng sorted set khi bạn cần: các phần tử được sắp xếp, tìm kiếm phần tử nhanh, truy xuất phần tử ở giữa nhanh

### Hyperloglog
Hyperloglog là cấu trúc dữ liệu xác suất dùng để đếm các phần tử phân biệt. Việc đếm các phần tử phân biệt nhau cần một vùng nhớ tỉ lệ với số phần tử muốn đếm, vì cần phải nhớ những phần tử đã đếm trước đó tránh đếm chúng lại nhiều lần.

Tuy nhiên, có một bộ các thuật toán ưu tiên bộ nhớ hơn độ chính xác với một sai số chấp nhận được, Redis là thấp hơn 1%.
Điểm hay của thuật toán này là bạn không cần dùng bộ nhớ tỉ lệ với số phần tử cần đếm, thay vào đó là một bộ nhớ hằng, 12K cho trường hợp xấu nhất.

Redis Hyperloglog được mã hóa như Redis string, có thể gọi GET và SET đến server

Về khái niệm, một số chức năng sử dụng set và hyperloglog là giống nhau. VD như dùng SADD để quan sát các phần tử được thêm vào set, vì SADD sẽ không thêm những phần tử đã có trong set

Khi cần đếm tất cả những truy vấn khác nhau trong một ngày, chương trình cần gọi PFADD mỗi lần có truy vấn được thực hiện và có thể đếm bằng PFCOUNT

  ```
  redis> PFADD hll foo bar zap
  (integer) 1
  redis> PFADD hll zap zap zap
  (integer) 0
  redis> PFADD hll foo bar
  (integer) 0
  redis> PFCOUNT hll
  (integer) 3
  redis> PFADD some-other-hll 1 2 3
  (integer) 1
  redis> PFCOUNT hll some-other-hll
  (integer) 6
  redis>
  ```

**Dùng Hyperloglog khi** cần đếm các phần tử phân biệt mà ưu tiên bộ nhớ hơn độ chính xác với một sai số chấp nhận được
## 4.3 Pub sub
Cơ chế Pub/sub 

![](https://codingpearls.com/wp-content/uploads/2018/04/redis-pub-sub.png)

Pub: Publish, nghĩa là một producer đẩy dữ liệu và một channel hay còn gọi là topic
Sub: Subcribe, nghĩa là một consumer đăng kí nhận dữ liệu từ một channel hay topic

## 4.4 Lock
Lock hay mutex là cơ chế synchronization dùng để giới hạn truy cập vào tài nguyên khi có nhiều thread cùng thực thi. 

Locking là một cơ chế RDBMS ngăn chặn người dùng từ nhiều transactions khác nhau gây ra data conflicts. Locking một row giúp ngăn chặn các transactions khác thay đổi row đó cho đến khi transaction đang truy cập vào nó kết thúc. Trong đó có 2 chiến lược lock là: optimistic và pesimistic. Trong bài post này, tôi sẽ giải thích sự khác nhau giữa pessimistic và optimistic locking đối với context của ADF framework.

![](https://images.viblo.asia/fad587f3-af0f-4f26-bcc5-4f092470d3d0.png)

### Optimistic Locking
![](https://labs.septeni-technology.jp/wp-content/uploads/2017/05/OOL.png)

- Mục đích: ngăn ngừa conflict giữa các transactions nghiệp vụ đồng thời bằng việc phát hiện ra conflict và thực hiện rollback transaction.
Vấn đề tương tranh giữa các transaction thường xảy ra trong hệ thống có nhiều transactions đồng thời. Chúng ta không thể chỉ phụ thuộc vào việc quản lý database để đảm bảo các transaction nghiệp vụ sẽ ghi dữ liệu nhất quán được. Tính toàn vẹn của dữ liệu dễ ảnh hưởng bởi 2 session cùng hoạt động (update) trên các records, hoặc cũng có thể 1 session sửa dữ liệu và 1 session đọc dữ liệu không nhất quán cũng dễ xảy ra tương tự. Optimistic  Lock giải quyết được problem trên bằng việc xác thực các thay đổi về việc commited trên từng session để không conflict đến session khác.
- Cách thức hoạt động: 
  - Optimistic  Lock chứa 1 điều kiện validate. Tại 1 thời điểm, 1 session load dữ liệu của 1 record, session khác không được thay thế nó.
  - Cài đặt phổ biến nhất là sử dụng version number cho với mỗi record trong hệ thống. Khi 1 record được load thì number đại diện cho version chứa được bởi session cùng với tất cả các trạng thái của session. Optimistic  Lock sẽ quan tâm đến việc so sánh dữ liệu number version lưu trong session data và current session trong record data. Nếu 2 giá trị number version bằng nhau tức việc verify thành công thì tất cả các thay đổi, bao gồm cả việc tăng version sẽ được committed.
  - Đối với Database RDBMS, 1 câu lệnh SQL có thể thực hiện lock và update dữ liệu record. Transaction nghiệp vụ sẽ kiểm tra giá trị row_count trả về bởi SQL execution. Nếu row_count = 1 tức là cập nhật thành công, row_count = 0 tức là record đã bị changed hoặc deleted. Với row_count = 0, transaction nghiệp vụ bắt buộc phải thực hiện rollback lại system transaction để ngăn ngừa các thay đổi tác động vào record data.
  - Ngoài thông tin version number của mỗi record, thông tin lưu trữ còn có thêm như user thực hiện modified record cuối cùng hoặc timestamp thời gian thay đổi.
  - Có thể sử dụng câu điều kiện update vào tất cả các trường trong row: 
UPDATE Customer SET …, version = (session’s copy of version + 1) WHERE id=? and version= session’s copy of version

### Pessimistic Lock
![](https://labs.septeni-technology.jp/wp-content/uploads/2017/05/POL.png)

- Mục đích: Với cách tiếp cận Optimistic  Locking không giải quyết triệt để được với các trường hợp người dùng truy cập cùng một dữ liệu trong một transaction (1 transaction sẽ commit thành công và 1 transaction sẽ failed => rollback). Bởi vì sự phát hiện conflict xảy ra ở giai đoạn cuối transaction, do đó dữ liệu đã xử lý của transaction failed sẽ là lãng phí ?
Pessimistic  Lock đã ngăn ngừa việc conflict giữa chúng với nhau bằng cách khi thực hiện transaction sẽ lock dữ liệu trước khi sử dụng nó, trong thời gian transaction sử dụng dữ liệu đó sẽ đảm bảo chắc chắn việc không có xung đột nào xảy ra.

- Cách thức hoạt động: 
Để cài đặt được Pessimistic  Lock cần làm:
  - Xác định kiểu của lock mà bạn cần dùng
  - Xây dựng lock manager
  - Xác định đối tượng cho transaction để sử dụng locks.

Về lock type, chúng ta có 3 sự lựa chọn:
- Exclusive Write Lock: chỉ cho phép 1 transaction được thực thi việc ghi dữ liệu. Nó sẽ tránh được conflict bởi không cho phép 2 transactions nghiệp vụ nào được thay đổi cùng 1 dữ liệu đồng thời.
- Exclusive Read Lock: chỉ có phép 1 transaction được thực thi đọc dữ liệu. 2 transactions sẽ không được đọc dữ liệu đồng thời
- Exclusive Read and Write Lock: 1 record không thể bị thực hiện write-lock khi 1 transaction khác đang sở hữu read lock trên nó và ngược lại. Concurrent read locks được chấp nhận. Tồn tại 1 single read lock ngăn ngừa business transaction từ việc sửa dữ liệu, nó sẽ không ảnh hưởng gì trong việc cho phép các sessions khác cùng đọc.


Xây dựng lock manager: Công việc của Lock Manager là grant hoặc deny bất kỳ request nào bởi transaction nghiệp vụ cho việc thực thi hoặc release 1 lock. Để làm công việc đó, Lock Manager cần biết rõ những gì sẽ bị lock như là ý định của người lock. Session và business transaction gần tương đương và có thể thay thế. Lock Manager không nên chứa nhiều hơn 1 table quản lý các lock của owner. Đơn giản nhất là 1 in-memory hash table hoặc 1 database table. Các transaction chỉ nên tương tác với lock manager và không được tương tác với lock object.

Chúng ta thường lock các object hoặc record, nhưng thật ra thứ cần lock thực sự là ID hoặc Primary Key (thứ để xác định tìm ra object). Nó cho phép chúng ta chứa lock trước khi load chúng.

Đối với Database RDBMS, ví dụ như MySQL có hỗ trợ 2 cơ chế lock là:
- Shared Lock Statement: LOCK IN SHARED MODE. Ví dụ: Có 2 bảng PARENT và CHILD, khi transaction thực hiện insert dữ liệu vào bảng CHILD cần đảm bảo rằng dữ liệu parent_id phải tồn tại ở bảng PARENT tại thời điểm đó.

```
SELECT * FROM parent WHERE NAME = 'Jones' LOCK IN SHARE MODE;
```
Sau khi LOCK IN SHARE MODE, câu query sẽ trả về giá trị Jones và transaction có thể an toàn thực hiện insert dữ liệu vào bảng CHILD. Trong thời điểm đó các transaction khác thực hiện UPDATE, DELETE lên row chứa giá trị Jones sẽ phải chờ transaction ban đầu hoàn thành.

- Exclusive Lock Statement: FOR UPDATE. Cách giải quyết trên sẽ gặp phải vấn đề nếu 2 transaction cùng thực hiện đọc bảng PARENT với row Jones, và đều đọc được dữ liệu sau đó insert sẽ bị duplicate giá trị. Cách khắc phục triệt để là sử dụng SELECT FOR UPDATE:

```
SELECT * FROM parent WHERE NAME = 'Jones' FOR UPDATE;
```

Khi thực hiện FOR UPDATE, transaction khác sẽ không tìm thấy dữ liệu từ bảng parent với row có name là Jones.

link: https://labs.septeni-technology.jp/technote/optimistic-hay-pessimistic-locking/

## 4.4 Distributed locks 
Distributed locks rất hữu dụng trong môi trường mà các process khác nhau sử dụng tài nguyên chia sẽ theo cách loại trừ lẫn nhau.

## 4.5 Redlock
Để yêu cầu nhận được khóa, client thực hiện các bước sau:
- Lấy thời gian hiện tại theo millisecond
- Cố lấy khóa với tất cả N instance tuần tự, dùng cùng cặp key name và random value với tất cả instance.
- Client tính thời gian dùng để yêu cầu nhận khóa. Nếu client đã lấy được khóa từ đa số (ít nhất là 3 ) instance và tổng thời gian yêu cầu nhận khóa nhỏ hơn thời gian có hiệu lực của khóa, thì khóa được xem là đã lấy được
- Nếu đã lấy được khóa, thời gian hiệu lực tính bằng thời gian hiệu lực ban đầu trừ cho thời gian trôi qua
- Nếu client không lấy được khóa, nó sẽ cố gắng mở khóa tất cả instances

link: https://redis.io/topics/distlock