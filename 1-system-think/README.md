# System thinking

## Mục lục

 [1. Định lý CAP, khái niệm eventual consistency](#1-định-lý-cap-khái-niệm-eventual-consistency)    
- [1.1. Định lí CAP](#11-định-lí-cap)       
- [1.2. Consistency patterns](#12-consistency-patterns)        
  
[2. Khái niệm throughput, latency, IOPS](#2-khái-niệm-throughput-latency-iops)

[3. Các phương pháp để scale database (MySQL): sharding, replication, ...](#3-các-phương-pháp-để-scale-database-mysql-sharding-replication-)    
- [3.1. Relational database management system (RDBMS):](#31-relational-database-management-system-rdbms)    
- [3.2. ACID: tập các tính chất của các giao tác(transaction) trong RDBMS](#32-acid-tập-các-tính-chất-của-các-giao-táctransaction-trong-rdbms)    
- [3.3. Replication](#33-replication)       
- [3.4. Federation (phân vùng)](#34-federation-phân-vùng)   
- [3.5 Sharding](#35-sharding)    
- [3.6. Denormalization (Không chuẩn hóa)](#36-denormalization-không-chuẩn-hóa)    
- [3.7 SQL tunning](#37-sql-tunning)       
- [3.8. NoSQL](#38-nosql)       

[4. Task Queue khác gì Message Queue?](#4-task-queue-khác-gì-message-queue)
- [4.1. Message queues](#41-message-queues)    
- [4.2. Task queues](#42-task-queues)
 
[5. Load balancer](#5-load-balancer)   
- [5.1. Load balancer (cân bằng tải)](#51-load-balancer-cân-bằng-tải)   
- [5.2. Layer 4 load balancing](#52-layer-4-load-balancing)    
- [5.3. Layer 7 load balancing:](#53-layer-7-load-balancing)   
- [5.4. Nginx](#54-nginx)        

[6. Caching](#6-caching)   
- [6.1. Vai trò của cache](#61-vai-trò-của-cache)   
- [6.2. Thuật toán LRU (Least recently used):](#62-thuật-toán-lru-least-recently-used)    
- [6.3. Thuật toán LFU (Least Frequently used)](#63-thuật-toán-lfu-least-frequently-used)
  
[7. Redis](#7-redis)    
- [7.1. Khái niệm Redis](#71-khái-niệm-redis)    
- [7.2. Ưu, khuyết điểm của Redis so với DSMS](#72-ưu-khuyết-điểm-của-redis-so-với-dsms)    
- [7.3. Redis single instance architecture](#73-redis-single-instance-architecture)        
- [7.4. Redis pesistance](#74-redis-pesistance)       
- [7.6. Cách đặt tên key phù hợp](#76-cách-đặt-tên-key-phù-hợp)


# 1. Định lý CAP, khái niệm eventual consistency
## 1.1. Định lí CAP
![](https://i.stack.imgur.com/d3s55.png)

3 tính chất quan trọng của hệ phân tán: chỉ có thể đảm bảo được 2 trong 3 tính chất
- Consistency: dữ liệu ở tất cả các node phải đồng nhất vơi nhau
- Availability: tính khả dụng , hệ thống có thể vẫn hoạt động được khi một số node bị hư
- Partition Tolerance: hệ thống vẫn tiếp tục xử lí công việc kể cả khi kết nối mạng bị đứt
  
Hệ thống mạng không ổn định nên ta phải đảm bảo partition tolerance. Do đó cần phải đảm bảo P và thêm C hoặc A
### CP - consistency và partition tolerance
Dữ liệu sẽ được thống nhất giữa tất cả các node và đảm bảo các node sẽ tiếp tục xử lí công việc kể cả khi kết nối mạng bị đ. CP sẽ là lựa chọn tốt nhất nếu nghiệp vụ yêu cầu việc đọc ghi đồng nhất
### AP - availability và partition tolerance
Dữ liệu trả về là phiên bản khả dụng mới nhất trên node, có thể không phải là cái cuối cùng. Nội dụng được write cần thời gian để lan truyền tới các node khác khi partition hoạt động lại. AP sẽ là lựa chọn tốt nhất nếu nghiệp vụ cần tuân theo eventual consistency - tính nhất quán cuối cùng, hay hệ thống cần tiếp tục hoạt động ngay cả khi bị lỗi

## 1.2. Consistency patterns
### Weak consistency 
Sau khi ghi, có thể đọc được hoặc không. Kết nối ổn định nhất sẽ được ghi nhận. Giống như việc gọi điện thoại, tín hiệu có thể bị mất và khi kết nối lại thì ta sẽ không nghe lại được những gì đã mất trước đó. Weak consistency hoạt động tốt cho ứng dụng realtime như video chat hay realtime multiplayer game
### Eventual consistency
Sau khi cập nhật, có thể không đảm bảo đọc được giá trị mới được cập nhật. Tuy nhiên sau một khoảng thời gian thì cuối cùng cũng có thể đọc được giá trị mới nhất được cập nhật.
### Strong consistency
Sau khi mỗi lần ghi, luôn đọc được dữ liệu mới nhất, dữ liệu luôn được đồng bộ


# 2. Khái niệm throughput, latency, IOPS
Throughput, latency, IOPS là ba tham số quan trọng trong các hệ thống storage.
Để dễ hiểu hãy liên tưởng đến ví dụ sau: ship hàng từ điểm A đến B
- Số lượng chuyến đi thực hiện trong 1 khoảng thời gian là IOPS
- Số hàng được chuyển trong một khoảng thời gian là Throughput
- Độ trễ trung bình trong tất cả các chuyến đi trong một khoảng thời gian đã thực hiện là Latency

Ba tham số này, nhất là IOPS và lataency phản ánh chất lượng phục vụ nhưng không phải lúc nào cũng đi cùng nhau. 
Có thể một ngày có nhiều chuyến hàng nhưng có những chuyến chuyển rất nhanh, những chuyến thì rất chậm, IOPS cao nhưng latency trung bình lại thấp

Có thể một ngày ít chuyến hàng những mỗi chuyến lại full tải thì throughtput cao nhưng IOPS thấp
Nhưng cũng không phải vì thế mà các tham số này không ảnh hưởng nhau. Khi IOPS quá cao, chạm đến giới hạn vật lí hệ thống sẽ gây ra high latency, nếu không xử lí ngay sẽ làm giảm throughtput vì data không thực sự được chuyển đến đích mà bị nghẽn lại

Link tham khảo: 
https://kipalog.com/posts/Cau-chuyen-khai-niem--Throughput--latency-va-IOPS

# 3. Các phương pháp để scale database (MySQL): sharding, replication, ...
## 3.1. Relational database management system (RDBMS): 
Relational database management system (RDBMS): Hệ quản trị cơ sở dữ liệu quan hệ

## 3.2. ACID: tập các tính chất của các giao tác(transaction) trong RDBMS
- **Atomicity (tính nguyên tố)**: tất cả transaction đều phải được thực hiện hết hoặc không thực hiện transaction nào cả
- **Consistency (tính nhất quán)**: một transaction kết thúc (thành công hay thất bại), CSDL phải ở trạng thái nhất quán (đảm bảo mọi ràng buộc toàn vẹn)
- **Isolation (tính cô lập)**: một transaction không quan tâm đến các transaction khác xử lí đông thời với nó
- **Durability (tính bền vững)**: mọi thay đổi mà transaction thực hiện trên CSDL phải được ghi nhận bền vững vào thiết bị lưu trữ 

Có rất nhiều kỹ thuật để scale cơ sở dữ liệu quan hệ, dưới đây là một số kỹ thuật:
## 3.3. Replication
###  Master-slave replication
![](https://camo.githubusercontent.com/6a097809b9690236258747d969b1d3e0d93bb8ca/687474703a2f2f692e696d6775722e636f6d2f4339696f47746e2e706e67)

Master server có quyền đọc và ghi, slave server chỉ có quyền đọc bản ghi của master server. Slave server có thể thêm các slave server khác vào để cùng đọc. Khi master không hoạt động được, hệ thống có thể tiếp tục hoạt động bằng cách được bằng cách chuyển sang chế độ chỉ đọc và sẽ duy trì tới khi có một slave được thăng cấp thành master hoặc một master mới được thêm vào

### Master-master replication
![](https://camo.githubusercontent.com/5862604b102ee97d85f86f89edda44bde85a5b7f/687474703a2f2f692e696d6775722e636f6d2f6b7241484c47672e706e67)

Mọi master server đều có quyền đọc và ghi như nhau trên mọi bản ghi. Khi một master không hoạt động, hệ thống vẫn có thể tiếp tục ở chế độ đọc ghi như trước

**Khuyết điểm**
- Cần phải load balancer (cân bằng tải) hoặc phải thay đổi logic của ứng dụng khi phải quyết định node nào được ghi 
- Hầu hết các hệ thống master-master đều ít tuân thủ sự nhất quán (vi phạm ACID) hoặc có độ trễ lớn khi ghi do đồng bộ hóa
- Xung đột xảy ra khi có càng nhiều node thực hiện ghi và độ trễ cũng càng tăng

### Khuyết điểm của replication
- Nguy cơ bị mất dữ liệu nếu như một master bị chết trước khi bản ghi mới nhất được cập nhật cho tất cả các node
- Càng nhiều slave đọc thì cần phải tạo thêm nhiều bản sao, dẫn đến độ trễ lớn
- Một số hệ thống, master có thể ghi song song với đa tiểu trình nhưng chỉ có thể đọc tuần tự với đơn tiểu trình
- Mô hình replication càn nhiều phần cứng và việc bổ sung rất phức tạp

## 3.4. Federation (phân vùng)
![](https://camo.githubusercontent.com/6eb6570a8b6b4e1d52e3d7cc07e7959ea5dac75f/687474703a2f2f692e696d6775722e636f6d2f553371563333652e706e67)

**Federation** chia nhỏ CSDL dựa trên chức năng. Ví dụ như: thay vì sử dụng CSDL đơn hoặc nguyên khối, có thể chia CSDL ra thành ba phần: forums, user và product, kết quả giảm lượng đọc và ghi trên từng CSDL và sẽ giảm độ trễ khi lan truyền. CSDL nhỏ giúp dữ liệu nằm vừa trong bộ nhớ, tăng cache hit. Không cần những master ghi tuần tự mà có thể ghi song song, giúp tăng throughtput
**Khuyết điểm**
- Federation sẽ không hiệu quả khi mô hình yêu cầu một lượng lớn chức năng và bảng
- Cần nâng cấp logic ứng dụng khi quyết định xem nên ghi và đọc ở CSDL nào
- Kết dữ liệu từ hai CSDL phức tạp hơn với kết nối giữa các server
- Federation cần thêm nhiều phần cứng và việc bổ sung phức tạp
  
## 3.5 Sharding
![](https://camo.githubusercontent.com/1df78be67b749171569a0e11a51aa76b3b678d4f/687474703a2f2f692e696d6775722e636f6d2f775538783549642e706e67)

**Sharding** phân bố dữ liệu thành nhiều CSDL khác nhau và mỗi CSDL chỉ có thể quản lí tập hợp dữ liệu con của tập dữ liệu lớn nhằm giải quyết vấn đề mở rộng theo bề ngang (horizontal scaling)

**Ưu điểm**
Giống như Federation, giảm lượng đọc và ghi, giảm sự lan truyền, tỉ lệ cache hit cao hơn, cho phép ghi song song để tăng throughput. Index size được giảm giúp tăng tốc truy xuất dữ liệu. Nếu một shard chết, những shard khác vẫn tiếp tục hoạt động, mặc dù sẽ cần thêm một số bản sao để tránh mất dữ liệu

**Khuyết điểm**
- Cần phải nâng cấp logic ứng dụng để làm việc được với shards, nó sẽ dẫn đến các câu SQL query sẽ rất khức tạp
- Dữ liệu phân bố có thể mất cân đối trong shard. 
- Kết dữ liệu từ nhiều shard khác nhau thì phức tạp
- Sharding cần thêm nhiều phần cứng và bổ sung phức

## 3.6. Denormalization (Không chuẩn hóa)
Denormalization hướng tới việc tăng tốc đọc bằng cách tạo ra nhiều bản sao dữ liệu ghi ở nhiều bảng khác nhau để tránh tốn nhiều chi phí cho việc join giữa các bảng. 
- Nếu dữ liệu được phân bố bằng kỹ thuật federation và sharding thì việc join dữ liệu trong tương lai sẽ tốn nhiều chi phí và rất phức tạp. Denormalization có thể phá vỡ điều đó
- Hầu hết hệ thống, số lần đọc sẽ nhiều hơn ghi rất nhiều, có thể là 100:1 hoặc 1000:1. Việc đọc giữa nhưng CSDL khác nhau và join chúng tốn nhiều chi phí về thời gian và tài nguyên.

**Khuyết điểm**
- Dữ liệu bị trùng lắp
- Những ràng buộc có thể giúp đồng bộ các bản sao dữ liệu, điều này tăng sự phức tạp cho thiết kế CSDL
- CSDL của denormalization khi lượng ghi lớn có thể hoạt động kém hơn CSDL của normalization với cùng lượng thông tin

## 3.7 SQL tunning
### Tighten up the schema (thắt chặt lược đồ)
- MySQL lưu dữ liệu vào đĩa thành những block liền kể để truy cập nhanh
- Dùng CHAR thay vì VARCHAR: CHAR cho phép truy cập nhanh, ngẫu nhiên, VARCHAR cần phải tìm kí tự kết thúc của từng chuỗi
- Dùng TEXT cho những đoạn block văn bản lớn như blog, cho phép search
- Dùng INT cho số lớn tới 2^32 hoặc 4 tỉ
- DECIMAL cho tiền tệ để tránh float point bị lỗi
- Tránh lưu BLOBS lớn, thay vào đó lưu địa chỉ của nơi lưu object
- VARCHAR(255) là số lượng kí tự lớn nhất
có thể được tính như 1 số 8bit
- Gán ràng buộc NOT NULL khi dùng để cải thiện hiệu suất sử dụng

### Sử dụng index một cách đúng đắn
- Các cột được querying(select, group by, order by, join) có thể nhanh hơn nếu dùng index
- Index có thể dùng để biểu diễn như một cây nhị phân cân bằng, giúp giữ cho dữ liệu được sắp xếp, cho phép tìm kiếm truy xuất tuần tự, thêm, xóa với chi phí thời gian là log
- Lưu trữ thêm index tốn thêm nhiều tài nguyên
- Ghi có thể chậm hơn vì chỉ số cũng cần được cập nhật
- Khi cần load một lượng dữ liệu lớn, nó có thể nhanh hơn nếu bỏ đi index, load dữ liệu, gán lại index

### Tránh tốn nhiều chi phí cho join
Dùng denormalization

### Bảng phân vùng
Tách bảng thành nhiều phần giúp lưu trữ dẽ dàng hơn

### Điều chỉnh query cache
- Trong một số trường hợp, query cache có thể dẫn đến một số vần đề về hiều suất


## 3.8. NoSQL
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


# 4. Task Queue khác gì Message Queue?

## 4.1. Message queues
![](https://www.tutorialspoint.com/inter_process_communication/images/multiple_message_queue.jpg)

Message queus cung cấp một giao thức giao tiếp bất đồng bộ, nghĩa là bên nhận và bên gửi ở message không cần phải cùng tương tác với message cùng một lúc. Message sẽ được lưu trong queue cho đến khi có một bên nhận lấy nó đi. 
Có thể dùng message queue theo quy trình sau:
- Một ứng dụng publishes một công việc cho queue, sau đó thông báo trạng thái công việc cho người dùng
- Một bên nhận công việc từ hàng đợi, xử lí nó, sau đó gửi tín hiệu là công việc hoàn thành

User không bị block và công việc sẽ được thực hiện ở background. 

**Redis:** khá tốt nhưng message có thể bị mất

**RabbitMQ**: phổ biến những yêu cầu bạn phải làm quen với AMQP protocol và tự quản lí các node của bạn

## 4.2. Task queues
![](https://cloud.google.com/appengine/docs/images/push-queue.svg)

Tasks queue nhận task và dữ liệu liên quan, sau đó trả về kết quả. Nó hổ trợ lập lịch và có thể được dùng để chạy những công việc tính toán chuyên sâu ở background


**Celery** hỗ trợ lập lịch và chủ yếu hỗ trợ python

### Khác nhau giữa Message queue và Task queue
Điểm khác nhau cơ bản đó là:
- Message queue nhận message và chuyển message đó đến nơi nó được xử lí. Nó có một số worker chờ nhận được message mới trong queue. Khi có một message mới đến, nó sẽ bắt đầu chạy vào xử lí task đó
- Task queue nhận task và dữ liệu liên quan, xử lí chúng và trả về kết quả. 
  
# 5. Load balancer 

## 5.1. Load balancer (cân bằng tải) 
![](https://camo.githubusercontent.com/21caea3d7f67f451630012f657ae59a56709365c/687474703a2f2f692e696d6775722e636f6d2f6838316e39694b2e706e67)

Cân bằng tải phân bố các request mà client gửi tới đến các tài nguyên máy tính như là server hay CSDL. Trong mỗi trường hợp, cân bằng tải trả về response từ tài nguyên máy tính đến những client thích hợp. Cân bằng tải hiệu quả khi:
- Chặn các requests đến các unhealthy server
- Ngăn việc quá tải tài nguyên
- Giúp loại bỏ những điểm bị lỗi

Load balancers thường được chia thành 2 loại chính: Layer 4 và Layer 7

![](https://viblo.asia/uploads/277e07bd-dfdd-4782-8e35-a00e06cac706.png)
- Layer 4 load balancer xử lí dữ liệu tìm thấy trong các giao thức tầng network và transport (IP,TCP,FTP,UDP)
  
- Layer 7 load balancer phân phối yêu cầu dựa trên dữ liệu tìm thấy trong tầng application, lớp giao thức như HTTP
  
## 5.2. Layer 4 load balancing
Layer 4 load balancing: hoạt động ở tầng transport của tin nhắn và không liên quan đến nội dung tin nhắn. Layer 4 bancer chỉ chuyển tiếp gói dữ liệu máng đến và đi từ máy chủ upstream mà không kiểm tra nội dung của các gói dữ liệu. Có thể đưa ra quyết định định tuyến giới hạn bằng kiểm tra vài gói đầu tiên trong dòng TCP
## 5.3. Layer 7 load balancing: 
Layer 7 load balancing hoạt động ở tâng application, xử lí trực tiếp với nội dung thực tế của mỗi thư, HTTP là giao thức chủ yếu của layer 7 cho việc điều phối lưu lượng truy cập trang web trên internet. Layer 7 load banlancer điều phối lưu lượng theo một cách tính vi hơn layer 4, Một layer 7 load balancer chấm dứt mạng lưới giao vận và đọc message bên trong. Nó có thể quyết định cân bằng tải dựa trên nội dung của thư (URL hoặc cookie). Sau đó tạo mới một kết nối TCP mới đến máy chủ upstream đã chọn (hoặc tái sử dụng nếu sẵn có bằng phương pháp HTTP keepalives) và tạo ra yêu cầu đến máy chủ


## 5.4. Nginx
Nginx (được đọc là “Engine-X”)  là sản phẩm mã nguồn mở cho web server . Là một reverse proxy cho các giao thức HTTP, SMTP, POP3 and IMAP . Nhằn nâng cao hiệu xuất xử lý khi sử dung lượng RAM thấp .  Được cấp phép bởi BSD chạy trên nên tảng Unix , Linux  và các biến thểBSD, Mac OS X, Solaris, AIX, HP-UX và Microsoft Windows.

Nginx có thể triển khai nội dung của các trang web động bằng cách sử lý FastCGI, SCGI  cho các scripts . Và có thể sử dụng như là một server cân bằng tải . Sau đó vấn đề C10K xuất hiện  nói cách khác để cho phép mỗi máy chủ web phải có khả năng xử lý 10.000 khách hàng cùng một lúc.  Cần phải phát triển một mạng lưới  I / O tốt hơn và công nghệ quản lý chủ đề đã được xuất hiện. Sự xuất hiện của NGinx không phải là kết quả của một nỗ lực để giải quyết vấn đề C10K (như là một vấn đề phổ biến) nhưng “vấn đề C10K” đã thành công trong việc đưa ra các  nỗ lực để nâng cao hiệu suất phát triển mạng máy chủ


### Kiến trúc Nginx
Nginx thực hiện dự đoán và điều chỉnh tài nguyên phần cứng có sẵn:
- Master process: thực hiện những công việc riêng như: đọc cấu hình và liên kết tới các port, sau đó tạo ra các process con (dưới đây là các loại process con)
- Cache loader process: chạy khi khởi động để load cache trên đĩa lên bộ nhớ rồi thoát ra. Nhu cầu tài nguyên của nó thấp
- Cache manager process: chạy định kì và chia các mục từ cache trên đĩa để giữ chúng vừa với kích thước được cấu hình
- Worker process: xử lí các kết nối mạng, đọc và ghi nội dung vào đĩa, liên lạc với các upstream server. Mỗi worker process là một single-thread và chạy độc lập với nhau, chúng liên lạc với nhau thông qua bộ nhớ dùng chung cho dữ liệu cache dùng chung, dữ liệu lưu phiên và các tài nguyên dùng chung khác.
  
- Khi được khởi chạy service, nginx khởi tạo một tiến trình chủ và cũng là tiến trình duy nhất tồn tại trong bộ nhớ Master Process. Tiến trình này không chịu trách nhiệm tự xử lý bất kỳ request nào từ phía client mà thay vào đó nó sinh ra các tiến trình con gọi là Worker Process để xử lý các request này.
  
![](https://www.aosabook.org/images/nginx/architecture.png)

- Để định nghĩa cho các Worker Process này, chúng ta cần sử dụng tệp tin cấu hình để xác định số tiến trình, số lượng kết nối , tài khoản và nhóm tài khoản mà mỗi Worker Process chạy

### Nginx dùng single thread
**Context switch** điều phối các process sẽ bao gồm: việc ngừng process hiện tại lại, lưu lại trạng thái của process này, lựa chọn process tiếp theo sẽ được chạy, load trạng thái của process tiếp theo đó lên, rồi chạy tiếp process tiếp theo

Giả sử ta có 100 thread trên 4 core, hệ điều hành phải switch giữa các thread đang chạy cả nghìn lần trong 1 giây, nó làm tăng thời gian lên đáng kể. Nhưng Nginx không cần context switch, mỗi single thread có thể phục vụ tất cả các request. Điều đó khiến Nginx nhanh và phục vụ được nhiều request trong 1 giây nhanh hơn các muilti thread khác.

Nginx không sinh ra mỗi process hoặc thread cho mỗi connection, bộ nhớ được tối ưu cho đa số trường hợp. Nginx duy trì CPU cycles tốt vì nó không tạo và hủy các thread liên tục. Nginx sẽ kiểm tra trạng thái của network và bộ nhớ, khởi tạo connection mới, thêm vào run loop, xử lí bất đồng bộ cho tới khi hoàn thành, sau đó nó sẽ bị đẩy ra khỏi run loop.

Vậy nginx dùng single thread để:
- Giảm context switch giữa các thread, tăng tốc độ
- Duy trì CPU cyles vì không tạo và hủy các thread liên tục, các thread mới sẽ được thêm vào run loop và được đẩy ra ngoài khi hoàn thành xong.
  
Link tham khảo:
- http://labs.zonmob.com/web-server/nginx/gioi-thieu-nginx-va-cac-thong-cau-hinh-co-ban.html
- https://techmaster.vn/posts/34635/gioi-thieu-ve-nginx-cho-lap-trinh-vien
- https://viblo.asia/p/tim-hieu-va-huong-dan-setup-web-server-nginx-OREGwBwlvlN
- https://www.aosabook.org/en/nginx.html


# 6. Caching

- Vai trò của cache, các thuật toán apply cho cache (LRU, LFU)
![](https://camo.githubusercontent.com/7acedde6aa7853baf2eb4a53f88e2595ebe43756/687474703a2f2f692e696d6775722e636f6d2f51367a32344c612e706e67)

## 6.1. Vai trò của cache
Bộ nhớ cache: bộ nhớ truy cập nhanh, nằm giữa bộ vi xử lí và bộ nhớ chính với dung lượng rất nhỏ (từ vài KB đến vài MB)
Cache giúp tiết kiệm thời gian truy cập bộ nhớ của CPU bằng cách dự đoán trước lệnh kế tiếp mà CPU sẽ cần và nạp nó vào cache trước khi CPU thực sự cần đến nó. Nếu lệnh cần thiết đã có sẵn trong cache thì CPU sẽ truy xuất dữ liệu từ cache, nếu không CPU mới truy xuất trên bộ nhớ chính. 
Caching tăng tốc độ load page và có thể giảm tải cho server và CSDL. 

## 6.2. Thuật toán LRU (Least recently used): 
- Lựa chọn phần tử lâu nhất chưa được dùng tới để thay thế
- Đề cài đặt cần hai cấu trúc dữ liệu:
  - Một cấu trúc đẻ lưu cặp key - value (dễ thấy là hashmap)
  - Một cấu trúc lưu và thay đổi vị trí các key theo chiều tăng dần của thơi gian
Thuật toán của ta sẽ phụ thuộc nhiều vào cấu trúc để lưu các key

Có mấy quan sát cho chúng ta khi xem xét cấu trúc dữ liệu lưu của các key đó là
- Cấu trúc này cần có thao tác remove phần tử đâu tiên (phần tử có thời gian truy cập nhỏ nhất) (1)
- Cầu trúc này cần có thao tác chuyển vị trí 1 phần tử bất kỳ về cuối (2)
- Cấu trúc này cần có thao tác để search 1 phần tử theo key

Để search thì tốc độ tốt nhất là O(logN), nên nếu muốn xây dựng thuật toán tốt hơn O(logN) ta cần loại bỏ thao tác cuối. Chúng ta sẽ dùng HashMap để lưu trữ luôn vị trí của key
Thay vì sử dụng PriorityQueue ta sẽ dùng Double Linked List và lưu thêm head, tail để thực hiện 2 thao tác (1) và (2) bằng O(1)

Link tham khảo: 
https://techtalk.vn/thuat-toan-lru-cache.html



## 6.3. Thuật toán LFU (Least Frequently used)
![](https://camo.githubusercontent.com/2f07f79f85e176ad0743d046d75c0f92040d0344/68747470733a2f2f692e696d6775722e636f6d2f626a6354456e722e6a7067)

- Lựa chọn phần tử ít được sử dụng thường xuyên nhất để thay thế
- Để cài đặt ta cần dùng ba cấu trúc dữ liệu:
  - Một là hashtable để lưu cặp key - value, giúp truy xuất vào cache với O(1)
  - Hai là Double Linked list để lưu tần suất truy cập
  - Ba là một cấu trúc dữ liệu có thể liên kết được với các list tần số. Có thể là mảng hoặc linked list nào đó để khi truy cập vào cache, nó có thể dẽ dàng truy cập đến list tần số tiếp theo với thời gian O(1). 

Link tham khảo: 
http://www.javarticles.com/2012/06/lfu-cache.html


# 7. Redis
## 7.1. Khái niệm Redis
Redis là hệ thống lưu trữ key-value phổ biến nhất

**Lưu trữ key-value** là hệ thống lưu trữ dữ liệu dưới dạng một cặp key-value. Redis lưu trữ data trong RAM dưới dạng key-value

Trong Redis, key phải là string, những value có thể là string, list, set, sorted set hoặc hash

## 7.2. Ưu, khuyết điểm của Redis so với DSMS
**Ưu điểm**: hệ quản trị cơ sở dữ liệu lưu mọi thứ ở bộ nhớ phụ (second storage), làm cho việc đọc ghi chậm hơn. Redis lưu mọi thứ ở bộ nhớ chính (primary memory) làm cho việc đọc ghi dữ liệu nhanh hơn

**Khuyết điểm:** primary memory có kích thước nhỏ hơn secondary storage rất nhiều, nên Redis có thể không lưu trữ được những file hay dữ liệu lớn. Nó chỉ có thể lưu một lượng nhỏ thông tin mà cần truy cập một cách rất nhanh. Nếu ta có ghi nhiều dữ liệu hơn những gì bộ nhớ có thể chứa sẽ xảy ra lỗi

## 7.3. Redis single instance architecture
Kiến trúc Redis gồm 2 process chính là: Redis client và Redis server
![](http://qnimate.com/wp-content/uploads/2014/05/redis-client-server.jpg)

Redis client và server có thể ở cùng một máy tính hoặc ở hai máy tính khác nhau
- Redis server chịu trách nhiệm lưu trữ dữ liệu trong bộ nhớ. 
- Redis client có thể là Redis console client hoặc bất kì ngôn ngữ lập trình Redis API
API

Redis lưu trữ mọi thứ ở primary memory nên sẽ bị mất toàn bộ dữ liệu chỉ với một lần restart Redis server. 

### Cluster
![](https://cdn-images-1.medium.com/max/800/1*YlZIesKl-3rvAr6KLEoZiQ.png)

Redis cluster là một mô hình data sharding đơn giản. 
Một Redis cluster là:
- Horizontally scalable: ta có thể tiếp tục thêm các node vào để tăng cường nguồn lực
-  data sharding: tự động phân vùng và phân chia dữ liệu giữa các node
- Fault tolerant: khi một node bị hư, ta vẫn có thể tiếp tục công việc mà không bị mất dữ liệu
- Decentralized cluster management: không có node nào giành làm công việc của các node khác, mọi node đều được phân chia trong cấu hình cluster

**Cấu trúc Redis cluster**
Cluster hoạt động cần:
- Tối thiểu 3 Redis master nodes
- Tối thiểu 3 Redis slaves, 1 slave cho mỗi master


**Cơ chế Pub/sub**
![](https://codingpearls.com/wp-content/uploads/2018/04/redis-pub-sub.png)

- Pub: Publish, nghĩa là một producer đẩy dữ liệu và một channel hay còn gọi là topic
- Sub: Subcribe, nghĩa là một consumer đăng kí nhận dữ liệu từ một channel hay topic

## 7.4. Redis pesistance
Có 3 cách để ghi dữ liệu lên đĩa cứng (Redis persistance)

### RDB (Redis DataBase file)
RDB tạo và sao lưu snapshot của DB và ổ cứng sau mỗi khoảng thời gian nhất định
- **Ưu điểm** 
  - Cho phép người dùng lưu các phiên bản khác nhau của DB, thuận tiện khi có sự cố xảy ra
  - Lưu trữ data vào 1 file cố định, người dùng có thể dễ dàng chuyển data đến các data centers hay máy chủ khác nhau
  - Giúp tối ưu hóa hiệu năng của Redis. Tiến trình Redis chính sẽ chỉ làm các công việc trên RAM, bao gồm các thao tác cơ bản như thêm/đọc/xóa từ client, trong khi đó 1 tiến trình còn sẽ đảm nhiệm các thao tác disk I/O, giúp tối đa hiệu năng của Redis
- **Khuyết điểm**
  - Dữ liệu có thể bị mất sau lần cuối RDB snapshot
  - RDB cần dùng fork() để tạo tiến trình con phục vụ thao tác disk I/O. Trong trường hợp dữ liệu quá lớn, quá trình fork() có thể tốn thời gian và server không thể đáp ứng request từ client trong vài milisecond hoặc 1 second.

### AOF (Append Only File)
AOF lưu lại các thao tác write mà server nhận được, các thao tác này sẽ được chạy lại khi restart server hoặc tái thiết lập dataset ban đầu

- **Uư điểm**:
  - Dùng AOF giúp đảm bảo dataset được bên vững hơn RDB
  - Có thể config để Redis ghi log theo từng câu query hoặc mỗi giây 1 lần
  - Redis ghi log AOF theo kiểu thêm vào cuối file sẵn có, do đó tiến trình seek trên file có sẵn là không cần thiết
  - Khi chỉ 1 nữa câu lệnh được ghi trong file log, Redis vẫn có cơ chế quản lí và sửa lỗi đó
  - Redis cung cấp tiến trình chạy nền, cho phép ghi lại file AOF khi dung lượng file quá lơn.
  - Trong khi server vẫn thực hiện thao tác trên file cũ, 1 file hoàn toàn mới được tạo với số lượng tối thiểu các operation phục vụ cho việc tạo dataset hiện tại. Khi file cũ được ghi xong, Redis sẽ chuyển sang ghi log trên file mới
- **Nhược điểm**
  - File AOF thường lớn hơn file RDB với cùng 1 dataset
  - AOF có thể chậm hơn RDB tùy cách thiết lập thời gian sao lưu vào ổ cứng
ứng

Link tham khảo: https://viblo.asia/p/redis-la-gi-157G5okARAje

## 7.5. Data structure
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

## 7.6. Cách đặt tên key phù hợp
Redis key là một binary safe, nghĩa là bạn có thể dùng bất kì chuỗi nhị phân nào như một key, từ chuỗi string như "foo" đến nội dung của một file JPEG. Chuỗi rỗng cũng là một key có giá trị

**Một số quy tắc về đặt tên key:**
- Không nên đặt key quá dài vì sẽ tốn nhiều bộ nhớ đễ lưu và khi tìm kiếm dựa trên key sẽ tốn chi phí lớn. Trường hợp thật sự cần đặt key quá dài thì hãy dùng hash để băm nó ra
- Không nên đặt key quá ngắn. VD thay vì đặt "u1000flw" hãy đặt là "user:1000:followers" để sau này đọc lại vẫn có thể hiểu được.
- Đặt tên key có liên quan đến lược đồ. VD như "object-type:id" cụ thể là "user:1000". "." hay "-" thường dùng để tách nhiều trường với nhau
- Kích thước lớn nhất của key là 512MB

Link tham khảo: 
http://qnimate.com/overview-of-redis-architecture/