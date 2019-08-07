# Advance knowledge of Web development

# 1. HTTP và Restful API
## 1.1 AJAX
AJAX: Asynchronous JavaScript and XML

Ajax là một bộ công cụ cho phép load dữ liệu từ server mà không yêu cầu tải lại trang.Nó sử dụng chức năng sẵn có XMLHttpRequest(XHR) của trình duyệt để thực hiện một yêu cầu đến server và xử lý dữ liệu server trả về.

Ajax là sự kết hợp của một nhóm các công nghệ có sẵn trong Javascript sau đây:
- HTML & CSS: Hiển thị thông tin
- DOM (Document Object Model): Tương tác với thông tin được hiển thị thông qua Javascript
- XMLHttpRequest: Trao đổi dữ liệu với Sever một cách không đồng bộ
- XML: Là định dạng cho dữ liệu truyền Ajax đóng vai trò làm trung gian giữa Client và Server tạo nên sự mượt mà cho ứng dụng Web của chúng ta.

### Cách hoạt động của AJAX
![](https://viblo.asia/uploads/5edffe26-d867-452d-a42f-592315a3fcaa.png)

Chúng ta sẽ đề cập đến 2 khái niệm Synchronous và Asynchronous:
#### Synchronous (xử lý đồng bộ):
- Chương trình sẽ chạy theo từng bước và chỉ khi nào bước 1 thực hiện xong thì mới nhảy sang bước 2.
- Trong một chuỗi các hàm của một quy trình có n tác vụ, trình tự thực hiện các hàm sẽ không bao giờ thay đổi. Hàm A đã được thiết lập để được gọi và chạy trước hàm B thì dù có phải đợi dài cổ hàm B cũng phải chờ hàm A kết thúc mới được phép bắt đầu.
- Đây là nguyên tắc cơ bản trong lập trình mà bạn đã được học đó là khi biên dịch các đoạn mã thì trình biên dịch sẽ biên dịch theo thứ tự từ trên xuống dưới, từ trái qua phải và chỉ khi nào biên dịch xong dòng thứ nhất mới nhảy sang dòng thứ hai, điều này sẽ sinh ra một trạng thái ta hay gọi là trạng thái chờ. Ví dụ trong quy trình sản xuất dây chuyền công nghiệp được coi là một hệ thống xử lý đồng bộ.

#### Asynchronous (xử lý bất đồng bộ):
- Ngược lại với Synchronous thì Asynchronous là xử lý bất động bộ, nghĩa là chương trình có thể nhảy đi bỏ qua một bước nào đó
- Trong một chuỗi các hàm của một quy trình có n tác vụ, nếu nó được bảo là bất đồng bộ thì có nghĩa là cho dù hàm B được gọi sau hàm A nhưng không ai đảm bảo được rằng hàm A sẽ phải kết thúc trước hàm B và hàm B bắt buộc phải chỉ được gọi chạy khi hàm A kết thúc.
- Vì vậy Asynchronous được ví như một chương trình hoạt động không chặt chẽ và không có quy trình nên việc quản lý rất khó khăn. Tuy nhiên nó mang lại sự uyển chuyển và khả năng tùy biến cao

Cùng trở lại vấn đề về cách thức hoạt động nhé.! Dựa vào 2 khái niệm ở trên thì chúng ta có thể thấy các ứng dụng web truyền thống – xử lý đồng bộ sẽ khác với các ứng dụng web kết hợp AJAX – xử lý bất đồng bộ. Với ứng dụng web thường: CLIENT sẽ gửi lên SEVER một HTTP Request. Trải qua một loạt các tác vụ bên phía SEVER như: tính toán, lấy dữ liệu, kiểm tra tính hợp lệ, sửa đổi bộ nhớ… sau đó mới gửi trả lại một trang HTML hoàn chỉnh tới CLIENT. Về mặt kỹ thuật, phương pháp này nghe có vẻ hợp lý nhưng cũng khá bất tiện và mất thời gian, bởi khi server đang thực hiện vai trò của nó thì người dùng sẽ làm gì ? Có vấn đề gì ở đây ? Mỗi lần như vậy Client sẽ gửi toàn bộ nội dung website lên Server, và Server cũng trả về tương ứng. Bạn hình dung, khi xem một bài báo, hay website chia sẻ hình ảnh, bạn chỉ quan tâm nội dung bài báo, hình ảnh đó mà thôi, không cần tải hết cả trang làm gì đó. Đó là hạn chế, bạn sẽ phải tốn thời gian chờ đợi thứ không mong muốn. Với ứng dụng web + AJAX: Ở đây cở chế xử lý AJAX sẽ đóng vai trò làm trung gian giữa CLIENT và SERVER. Thay vì tải lại (refresh) toàn bộ một trang, nó chỉ nạp những thông tin được thay đổi, còn giữ nguyên các phần khác. Ví dụ, trong một website ảnh, với ứng dụng truyền thống, toàn bộ trang chứa các ảnh sẽ phải mở lại từ đầu nếu có một thay đổi nào đó trên trang. Còn khi áp dụng AJAX, DHTML chỉ thay thế đoạn tiêu đề và phần vừa chỉnh sửa, do vậy tạo nên các giao dịch trơn tru, nhanh chóng. 3. Lời kết Quá trình hoạt động của Ajax diễn ra ở 2 giai đoạn: Client sẽ gửi một request theo dạng bất đồng bộ (Asynchronous) tới server. Được gọi là bất đồng bộ là bởi vì trang web trên trình duyệt vẫn duy trì hoạt động như bình thường cho tới khi Server nhận được request từ phía client và trả về kết quả cho client. Lúc này trình duyệt sẽ cập nhật nội dung trang web dựa trên kết quả trả về. Như vậy bạn có thể thấy được toàn bộ quá trình hoạt động của Ajax không làm gián đoạn sự hiển thị hay tương tác của trang web vào trước và trong thời gian trình duyệt gửi Ajax request tới server. Chính tính năng này sẽ giúp tăng trải nghiệm của người dùng và đồng thời tối ưu hóa tài nguyên trên server.

Link: https://viblo.asia/p/ajax-hoat-dong-nhu-the-nao-jvElaXzzZkw

## 1.2 HTTP Method: GET, POST, PUT, DELETE, ...
HTTP method là một thành phần của request gọi từ client tới server để yêu cầu server thực hiện một việc gì đó như là lấy dữ liệu từ server về, gửi dữ liệu lên server để xử lý, cập nhật hoặc xóa dữ liệu trên server…

### IDEMPOTENT
các method được coi là idempotent khi nó có thể thực hiên n + 1 lần mà vẫn trả lại 1 kết quả như ban đầu.
vì điều này nên các method safe thì đều idempotent. Nhưng unsafe chưa chắc đã idempotent.

### SAFE
một method được coi là safe khi nó không làm thay đổi trạng thái "sate" của server. Nói cách khác, an toàn là chỉ đọc mà không làm thay đổi bất kì điều gì. Các method được coi là safe chỉ có: GET
Unsafe: PUT, DELETE, POST và PATCH.

### GET
HTTP GET method dùng để chỉ đọc hay nhận dữ liệu từ server mà không làm thay đổi dữ liệu vậy nên nó là safe và idempotent. Gọi GET 1 hay 10 lần thì kết quả vẫn giống nhau

Ví dụ: Lấy thông tin từ api.hocdai.com, với user có id là 1.

![](https://s3.ap-southeast-1.amazonaws.com/hocdai/static/png/1/1529294906/get-method.png)

### POST
POST hầu hết được dùng để tạo resource mới. 
POST không phải là safe hay idempotent, vì thế nó được khuyên dùng cho các non-idempotent resource request. Tạo 2 POST request giống nhau thì rất có thể kết quả nằm trong 2 resource chứa thông tin giống nhau

Ví dụ: POST thông tin đến api.hocdai.com/user, để tạo user có name là test.

![](https://s3.ap-southeast-1.amazonaws.com/hocdai/static/png/1/1529294929/post-method.png)

### PUT
PUT hầu hết được dùng để update, PUT-ing được hiểu là resource URI với request body bao gồm bản newly-update của resource cũ. 
Tuy nhiên PUT cũng có thể được dùng để tạo resource mới trong trường hợp id của resource được tạo bởi client thay vì server. 
PUT không phải là safe operation, bởi vì nó làm thay đổi trạng thái của server, tuy nhiên nó lại là idempotent. Bởi vì nếu ta tạo hay update resource bằng PUT n lần thì resource vẫn tồn tại như vậy và vẫn ở state giống lần gọi đầu tiên. Nếu trong trường hợp gọi PUT với resource chứa increment counter thì lần gọi này sẽ không còn là idempotent nữa. 
PUT được khuyên dùng cho các request idempotent

Ví dụ: Cập nhật name = “new” cho user có id = 2 bằng api.hocdai.com/user

![](https://s3.ap-southeast-1.amazonaws.com/hocdai/static/png/1/1529294947/put-method.png)


### DELETE
DELETE dùng để xóa 1 resource xác định bằng URI


