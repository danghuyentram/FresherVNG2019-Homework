# Database thinking

# 1. Các khái niệm về database
![](https://kipalog.kaopiz.com/uploads/905c/bbf5/db_en.png)

Ở hình trên thì cho chúng ta thấy mô hình thường thấy của một ứng dụng trong thực tế hiện nay. Người dùng thông qua một ứng dụng sử dụng một hệ quản trị cơ sở dữ liệu(DBMS) để thực hiện các thao tác trực tiếp với database. 
- Database (cơ sở dữ liệu): là tập dữ liệu có tổ chức, thường được lưu trữ và truy cập điện tử từ hệ thống máy tính. K
- Database Management System (hệ quản trị cơ sở dữ liệu): là phần mềm tương tác với người dùng cuối, ứng dụng và chính cơ sở dữ liệu để thu thập và phân tích dữ liệu. Phần mềm DBMS bao gồm các tiện ích cốt lõi được cung cấp để quản trị cơ sở dữ liệu. Tổng cộng của cơ sở dữ liệu, DBMS và các ứng dụng liên quan có thể được gọi là "hệ thống cơ sở dữ liệu". Thông thường thuật ngữ "cơ sở dữ liệu" cũng được sử dụng để nói đến bất kỳ DBMS, hệ thống cơ sở dữ liệu hoặc ứng dụng nào được liên kết với cơ sở dữ liệu.
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

# SQL

