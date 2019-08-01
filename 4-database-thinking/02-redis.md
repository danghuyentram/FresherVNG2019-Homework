
#  Redis

<!-- TOC -->
  - [1 Install](#1-install)   
  - [2 Data Type](#2-data-type)       
  - [3 Pub sub](#3-pub-sub)   
  - [4 Lock](#4-lock)       
  - [5 Redlock](#5-redlock)
<!-- /TOC -->

## 1 Install
### Version
Redis đánh số version dựa trên:
- Bản stable release: đánh số chẵn như là 1.2, 2.0, 2.2, 2.4, 2.6, 2.8.
- Bản unstable release: đánh số lẻ như: 2.9.x

### Install
- Download, extract and compile Redis with:

```
$ wget http://download.redis.io/releases/redis-5.0.5.tar.gz
$ tar xzf redis-5.0.5.tar.gz
$ cd redis-5.0.5
$ make
```

- The binaries that are now compiled are available in the src directory. Run Redis with:

```
$ src/redis-server
```

- You can interact with Redis using the built-in client:

```
$ src/redis-cli
redis> set foo bar
OK
redis> get foo
"bar"
```

## 2 Data Type
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
## 3 Pub sub
Cơ chế Pub/sub 

![](https://images.viblo.asia/c5051c43-10af-41ad-825e-b149a73f36c6.png)

- Pub - Publish: producer sẽ đẩy data vào một chanel.
- Sub - Subscribe: consumer đăng ký nhận dữ liệu từ một chanel. 


## 4 Lock
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

## 4 Distributed locks 
Thông thường, khi ta muốn lock data, ta sẽ phải yêu cầu được cấp khóa để truy cập độc quyền data đó, sau đó xử lí, và cuối cùng là trả lại khóa cho những người khác. Trình tự này nằm trong mô hình shared-memory data được truy cập bởi các thread

Đối với Redis, ta sử dụng WATCH thay cho lock, đó gọi là optimistic locking, bởi vì thay vì ngăn những người khác thay đổi data, ta sẽ thông báo nếu như có ai đó thay đổi dữ liệu trước khi ta tự thay đổi chúng.

Với distributed locking, ta có cùng cách sắp xếp các yêu cầu cấp khóa, xử lí, cơ chế trả khóa. Nhưng thay vì có được khóa chỉ được biết tới bởi các thread trong cùng một process thi ta có thể dùng khóa được cấp và trả lại ở các redis client khác nhau trên các máy khác nhau. 

Một lí do mà ta có thể tiết kiệm được rất nhiều thời gian để xây dụng lock với redis thay vì dùng lock cấp hệ điều hành, đó là vì vấn đề về phạm vị. Client muốn truy cập data lưu trên redis, thì client phải có được quyền truy cập khóa được xác định trong một phạm vi mà tất cả client đều có thể thấy được là redis. 

Link: https://redislabs.com/ebook/part-2-core-concepts/chapter-6-application-components-in-redis/6-2-distributed-locking/

## 5 Redlock

Để yêu cầu nhận được khóa, client thực hiện các bước sau:
- Lấy thời gian hiện tại theo millisecond
- Cố lấy khóa với tất cả N instance tuần tự, dùng cùng cặp key name và random value với tất cả instance.
- Client tính thời gian dùng để yêu cầu nhận khóa. Nếu client đã lấy được khóa từ đa số (ít nhất là 3 ) instance và tổng thời gian yêu cầu nhận khóa nhỏ hơn thời gian có hiệu lực của khóa, thì khóa được xem là đã lấy được
- Nếu đã lấy được khóa, thời gian hiệu lực tính bằng thời gian hiệu lực ban đầu trừ cho thời gian trôi qua
- Nếu client không lấy được khóa, nó sẽ cố gắng mở khóa tất cả instances


link: https://redis.io/topics/distlock
