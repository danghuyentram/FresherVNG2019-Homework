# Advance knowledge of Web development

Mục lục

- [Advance knowledge of Web development](#advance-knowledge-of-web-development)
- [1. HTTP và Restful API](#1-http-v%c3%a0-restful-api)
  - [1.1 AJAX](#11-ajax)
    - [Cách hoạt động của AJAX](#c%c3%a1ch-ho%e1%ba%a1t-%c4%91%e1%bb%99ng-c%e1%bb%a7a-ajax)
      - [Synchronous (xử lý đồng bộ):](#synchronous-x%e1%bb%ad-l%c3%bd-%c4%91%e1%bb%93ng-b%e1%bb%99)
      - [Asynchronous (xử lý bất đồng bộ):](#asynchronous-x%e1%bb%ad-l%c3%bd-b%e1%ba%a5t-%c4%91%e1%bb%93ng-b%e1%bb%99)
  - [1.2 HTTP Method: GET, POST, PUT, DELETE, ...](#12-http-method-get-post-put-delete)
    - [IDEMPOTENT](#idempotent)
    - [SAFE](#safe)
    - [GET](#get)
    - [POST](#post)
    - [PUT](#put)
    - [DELETE](#delete)
  - [1.3 CORS](#13-cors)
    - [Same-origin policy](#same-origin-policy)
    - [CORS](#cors)
    - [XMLHttpRequest](#xmlhttprequest)
    - [Fetch API](#fetch-api)
    - [Cấu hình máy chủ hỗ trợ CORS](#c%e1%ba%a5u-h%c3%acnh-m%c3%a1y-ch%e1%bb%a7-h%e1%bb%97-tr%e1%bb%a3-cors)
      - [Đối với truy vấn đơn giản](#%c4%90%e1%bb%91i-v%e1%bb%9bi-truy-v%e1%ba%a5n-%c4%91%c6%a1n-gi%e1%ba%a3n)
      - [Đối với truy vấn cần preflight](#%c4%90%e1%bb%91i-v%e1%bb%9bi-truy-v%e1%ba%a5n-c%e1%ba%a7n-preflight)
  - [1.4 Session](#14-session)
    - [Khái niệm session](#kh%c3%a1i-ni%e1%bb%87m-session)
    - [Cấu trúc 1 session](#c%e1%ba%a5u-tr%c3%bac-1-session)
    - [Ứng dụng](#%e1%bb%a8ng-d%e1%bb%a5ng)
  - [1.5 Cookie](#15-cookie)
    - [Cách hoạt động](#c%c3%a1ch-ho%e1%ba%a1t-%c4%91%e1%bb%99ng)
    - [Ứng dụng](#%e1%bb%a8ng-d%e1%bb%a5ng-1)
    - [So sánh cookie và session](#so-s%c3%a1nh-cookie-v%c3%a0-session)
  - [1.6 JWT](#16-jwt)
    - [Các thành phần của JWT](#c%c3%a1c-th%c3%a0nh-ph%e1%ba%a7n-c%e1%bb%a7a-jwt)
      - [Header](#header)
      - [Payload](#payload)
      - [Signature](#signature)
    - [Khi nào thì dùng JWT?](#khi-n%c3%a0o-th%c3%ac-d%c3%b9ng-jwt)
    - [Cách hoạt động của JWT](#c%c3%a1ch-ho%e1%ba%a1t-%c4%91%e1%bb%99ng-c%e1%bb%a7a-jwt)
  - [1.6 Restful API](#16-restful-api)
    - [Hoạt động của RESTful](#ho%e1%ba%a1t-%c4%91%e1%bb%99ng-c%e1%bb%a7a-restful)
    - [Nguyên tắt thiết kế của REST API](#nguy%c3%aan-t%e1%ba%aft-thi%e1%ba%bft-k%e1%ba%bf-c%e1%bb%a7a-rest-api)
    - [Authentication và dữ liệu trả về](#authentication-v%c3%a0-d%e1%bb%af-li%e1%bb%87u-tr%e1%ba%a3-v%e1%bb%81)
    - [Status code](#status-code)
  - [1.7 Server Render và Single Page App](#17-server-render-v%c3%a0-single-page-app)
    - [Cơ chế server-side rendering](#c%c6%a1-ch%e1%ba%bf-server-side-rendering)
      - [Ưu điểm](#%c6%afu-%c4%91i%e1%bb%83m)
      - [Khuyết điểm](#khuy%e1%ba%bft-%c4%91i%e1%bb%83m)
    - [Cơ chế client-side rendering](#c%c6%a1-ch%e1%ba%bf-client-side-rendering)
      - [Ưu điểm](#%c6%afu-%c4%91i%e1%bb%83m-1)
      - [Khuyết điểm](#khuy%e1%ba%bft-%c4%91i%e1%bb%83m-1)

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
DELETE dùng để xóa 1 resource xác định bằng URI.
DELETE operation là idempotence, tuy nhiên gọi DELETE với 1 resource 2 lần có thể return 404 NOT FOUND bởi vì nó đã bị xóa nên không còn tồn tại nữa. Một số ý kiến khác cho rằng, việc gọi DELETE không cò là idempotence nữa, tuy nhiên trạng thái end-state của resource là giống nhau và việc return 404 được chấp nhận và cho biết chính xác status của lần gọi

Ví dụ: Delete khỏi server user có id = 2

![](https://s3.ap-southeast-1.amazonaws.com/hocdai/static/png/1/1529294968/delete-method.png)


Link: https://hocdai.com/http-can-ban/http-message/http-method-la-gi
https://www.restapitutorial.com/lessons/httpmethods.html

## 1.3 CORS
CORS là viết tắt của từ Cross-origin resource sharing.
CORS là một cơ chế cho phép nhiều tài nguyên khác nhau (fonts, Javascript, v.v…) của một trang web có thể được truy vấn từ domain khác với domain của trang đó.

### Same-origin policy
CORS được sinh ra là vì same-origin policy, là một chính sách liên quan đến bảo mật được cài đặt vào toàn bộ các trình duyệt hiện nay. Chính sách này ngăn chặn việc truy cập tài nguyên của các domain khác một cách vô tội vạ.

Ta có ví dụ một kịch bản như sau:

- Bạn truy cập một trang web có mã độc. Trang web đó sử dụng Javascript để truy cập tin nhắn Facebook của bạn ở địa chỉ https://facebook.com/messages.
- Nếu bạn đã đăng nhập Facebook từ trước rồi. Nếu không có same-origin policy, trang web độc hại kia có thể thoải mái lấy dữ liệu của bạn và bất cứ điều gì chúng muốn.

Same-origin policy chính là để ngăn chặn những kịch bản như trên để bảo vệ người dùng, giúp an toàn hơn khi lướt web.

### CORS 
Thế nhưng trong thế giới web, lập trình viên thường xuyên phải thực hiện truy vấn đến các domain khác, đặc biệt là khi làm việc với các API.

Đó là lúc chúng ta cần đến CORS. CORS sử dụng các HTTP header để “thông báo” cho trình duyệt rằng, một ứng dụng web chạy ở origin này (thường là domain này) có thể truy cập được các tài nguyên ở origin khác (domain khác).

Một ứng dụng web sẽ thực hiện truy vấn HTTP cross-origin nếu nó yêu cầu đến các tài nguyên ở origin khác với origin nó đang chạy (khác giao thức, domain, port). Sự khác biệt về giao thức ở đây là khác biệt kiểu như HTTP với FTP chứ không phải HTTP và HTTPS (dù nhiều trình duyệt không cho phép trộn lẫn các tài nguyên truy cập bằng HTTP và HTTPS nhưng đó là vấn đề khác, không liên quan đến CORS).

Các trường hợp cần đến CORS rất phổ biến trong thực tế. Một ví dụ rất điển hình như sau: một ứng dụng web chạy ở domain foo.com và nó cần truy vấn đến bar.com để lấy một vài dữ liệu (thường được thực hiện bởi JavaScript bằng cách sử dụng XMLHttpRequest).

Các trình duyệt đều cài đặt same-origin policy và tuân thủ nó rất chặt chẽ. Cài đặt XMLHttpRequest và kể cả Fetch API cũng đều tuân thủ chính sách này. Do đó những truy vấn như ở trên sẽ không thu được kết quả gì, trừ khi máy chủ trả về response có các header CORS phù hợp.

Như vậy, bằng việc sử dụng CORS, chúng ta có thể thúc đẩy việc giao tiếp trong ứng dụng web dễ dàng hơn rất nhiều.


### XMLHttpRequest
Dùng XMLHttpRequest(XHR) object để giao tiếp với server, bạn có thể lấy data từ URL mà không cần refresh lại full page, nó được dùng nhiều trong AJAX.
Không như cái tên của nó, XMLHttpRequest có thể lấy bất kì loại data nào, không chỉ riêng XML

link: https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest
https://completejavascript.com/xmlhttprequest-tao-http-request-den-server-trong-javascript/

### Fetch API
Fetch API cung cấp inteface để lấy resources. Nó khá giống XMLHttpRequest nhưng API mới cung cấp các feture mạnh và linh hoạt hơn



### Cấu hình máy chủ hỗ trợ CORS
Có hai loại truy vấn CORS: loại truy vấn “đơn giản” và “không đơn giản”.

Một truy vấn đơn giản hoàn toàn không cần đến CORS preflight. Một truy vấn sẽ được gọi là đơn giản nếu nó thoả mãn những điều kiện sau:

- Phương thức của truy vấn là một trong các loại GET, HEAD, POST.
- Giá trị của Content-Type phải là một trong số các loại application/x-www-form-urlencoded, multipart/form-data, text/plain.
- Không có event handler nào với event XMLHttpRequest.upload.
- Không sử dụng đối tượng ReadableStream trong truy vấn.
- Các HTTP header sau phải khớp:
    - Accept
    - Accept-Language
    - Content-Language
    - Last-Event-ID

Những truy vấn này được gọi là “đơn giản” bởi chúng có thể được coi là truy vấn thông thường từ trình duyệt mà không cần đến CORS, giống như submit một form HTML thông thường chẳng hạn.

Những truy vấn không phải “đơn giản” sẽ là truy vấn không đơn giản, và chúng cần CORS preflight. CORS preflight có nghĩa là trước khi truy vấn được gửi, nó cần phải gửi một truy vấn trước bằng phương thức OPTIONS. Mục đích của truy vấn “preflight” này là nhằm kiểm tra xem truy vấn thực sự có an toàn để gửi và nhận hay không.

#### Đối với truy vấn đơn giản
Một truy vấn CORS đơn giản như đã nói ở trên, có thể có gói tin HTTP dạng như sau:

```
GET /cors HTTP/1.1
Origin: https://api.topdevvn.com
Host: api.alice.com
Accept-Language: en-US
Connection: keep-alive
User-Agent: Mozilla/...
```

Với các phương thức khác, gói tin HTTP cũng tương tự như vậy. Lưu ý rằng, một truy vấn CORS hợp lệ luôn luôn có Origin ở trong header. Giá trị của header này hoàn toàn được thiết lập tự động bởi trình duyệt, và không ai có thể thay đổi nó được. Giá trị của header này sẽ bao gồm scheme (http), domain (api.bob.com) và cổng (trong trường hợp dùng cổng mặc định thì không cần, ví dụ http dùng cổng 80). Giá trị của header chính là biểu thị nguồn gốc của truy vấn.

Một điểm lưu ý nữa là sự xuất hiện của header Origin không đồng nghĩa với việc truy vấn đó là cross origin. Dù tất cả các truy vấn cross origin đều có header này, nhưng một số truy vấn same origin cũng có header này. Điều đó phụ thuộc vào từng trình duyệt cụ thể.

Dưới đây là response của máy chủ phản hồi cho một truy vấn CORS hợp lệ:

```
Access-Control-Allow-Origin: https://api.topdevvn.com
Access-Control-Allow-Credentials: true
Access-Control-Expose-Headers: FooBar
```

Tất cả các header liên quan đến CORS đều có phần đầu tiên là Acess-Control-. Ý nghĩa của từng header như sau:

- Access-Control-Allow-Origin (bắt buộc): đây là header phải có trong mọi response cho một truy vấn CORS hợp lệ. Nếu không có header này, truy vấn sẽ bị lỗi, giá trị của nó có thể là giá trị của header Origin được gửi lên hoặc * biểu thị cho mọi origin.
- Access-Control-Allow-Credentials (tuỳ chọn): Mặc định, cookie sẽ không được sử dụng trong các truy vấn CORS. Header này sẽ biểu thị giá trị logic rằng có thể sử dụng cookie hay không. Giá trị duy nhất của header này là true. Nếu không muốn sử dụng cookie thì thông thường người ta sẽ bỏ header này trong response chứ không phải đặt giá trị nó là false. Lưu ý rằng, header này chỉ hoạt động nếu phía client cũng đặt giá trị withCredentials = true như đã nói ở phần trước.
- Access-Control-Expose-Headers (tuỳ chọn): Một đối tượng XMLHttpRequest có một phương thức getResponseHeader, phương thức này sẽ trả về giá trị của một header cụ thể trong response. Với các truy vấn CORS, phương thức này chỉ có thể truy cập được một số header đơn giản mà thôi. Nếu muốn phương thức này có thể truy cập nhiều header hơn, chúng ta cần đến giá trị của header này. Giá trị của header này là một danh sách các header có thể truy cập được, ngăn cách bằng dấu phẩy.

#### Đối với truy vấn cần preflight
Ở phía frontend, các truy vấn đơn giản hay phức tạp đều trông sẽ giống nhau. Truy vấn preflight hoàn toàn được thực hiện ngầm và trong suốt với người dùng. Truy vấn preflight sẽ được gửi đi trước nhằm xác định xem truy vấn thực sự có thể thực hiện được hay không.

Sau khi có được phản hồi tích cực, trình duyệt sẽ gửi truy vấn thực sự. Kết quả của truy vấn preflight có thể được cache nên nó không cần phải thực hiện cho mọi truy vấn.

Dưới đây là một gói tin HTTP cho truy vấn preflight:

```
OPTIONS /cors HTTP/1.1
Origin: https://api.topdevvn.com
Access-Control-Request-Method: PUT
Access-Control-Request-Headers: X-Custom-Header
Host: api.alice.com
Accept-Language: en-US
Connection: keep-alive
User-Agent: Mozilla/...
```

Tương tự như truy vấn đơn giản, truy vấn này cũng tự động được thêm header Origin. Truy vấn preflight sẽ được thực hiện bằng phương thức OPTIONS với một số header đặc thù:
- Access-Control-Request-Method: Đây là phương thức HTTP dùng trong truy vấn thực sự. Giá trị của header luôn luôn phải có, ngay cả khi các phương thức đó cũng là phương thức của một truy vấn đơn giản.
- Access-Control-Request-Headers: Đây là danh sách (ngăn cách bằng dấu phẩy) các header được thêm vào truy vấn.

Truy vấn preflight là một cách để hỏi máy chủ rằng, liệu truy vấn thực sự có thể thực hiện được hay không. Mà máy chủ dựa vào hai header này để quyết định xem có chấp nhận truy vấn hay không. Nếu chấp nhận, máy chủ sẽ phản hồi như sau:

```
Access-Control-Allow-Origin: https://api.topdevvn.com
Access-Control-Allow-Methods: GET, POST, PUT
Access-Control-Allow-Headers: X-Custom-Header
Content-Type: text/html; charset=utf-8
```

Trong đó, response có thể có những header như sau:
- Access-Control-Allow-Origin (bắt buộc): Tương tự như trường hợp truy vấn CORS đơn giản.
- Access-Control-Allow-Methods (bắt buộc): Là một danh sách (ngăn cách bằng dấu phẩy) các phương thức HTTP được chấp nhận. Dù truy vấn preflight có hỏi về một phương thức cụ thể của truy vấn tiếp theo, giá trị của header này trong responses có thể bao gồm tất cả các phương thức được chấp nhận.
- Access-Control-Allow-Headers (bắt buộc nếu truy vấn có header Access-Control-Request-Headers): Là danh sách các header (ngăn cách bằng dấu phẩy) được hỗ trợ. Tương tự như header trước, giá trị của header này cũng có thể bao gồm tất cả các header được chấp nhận.
- Access-Control-Allow-Credentials (tuỳ chọn): Tương tự như trường hợp truy vấn CORS đơn giản.
- Access-Control-Max-Age (tuỳ chọn): Truy vấn preflight không nhất thiết phải được thực hiện cho mọi truy vấn, mà kết quả của nó có thể cache được. Giá trị của header này chính là số giây mà giá trị của truy vấn preflight có thể được cache.

Một khi truy vấn preflight có được phản hồi và được chấp nhận, trình duyệt sẽ thực hiện truy vấn thực sự. Truy vấn lúc này tương tự như truy vấn CORS đơn giản và quá trình xử lý cũng như phản hồi hoàn toàn tương tự như vậy.

Nếu muốn từ chối truy vấn CORS, máy chủ có thể phần hồi một gói tin HTTP bình thường (mã 200) nhưng không có chứa HTTP header nào liên quan đến CORS. Trong trường hợp truy vấn preflight nhận được phản hồi như vậy, trình duyệt sẽ hiểu là truy vấn không được chấp nhận và nó sẽ không gửi thêm truy vấn nào nữa.

Về phía client, nếu trong trường hợp không thực hiện được truy vấn, event onerror sẽ được gọi. Tuy nhiên, như đã nó ở trên, trình duyệt cũng không thể truy cập được nhiều thông tin về lỗi đó, chỉ đơn giản là biết có lỗi mà thôi.

link: https://topdev.vn/blog/cors-la-gi/


## 1.4 Session
### Khái niệm session
Một session hay còn gọi là một phiên làm việc. Trong khoa học máy tính, Nó đơn giản là cách giao tiếp giữa client (ở đây là trình duyệt web hoặc ứng dụng trên thiết bị của bạn) với server. Một session bắt đầu khi client gửi request đến sever, nó tồn tại xuyên suốt từ trang này đến trang khác trong ứng dụng và chỉ kết thúc khi hết thời gian timeout hoặc khi bạn đóng ứng dụng. Giá trị của session sẽ được lưu trong một tệp tin trên máy chủ

### Cấu trúc 1 session
Session là 1 cấu trúc dữ liệu key-value. Có thể xem nó như một hash-table trong đó mỗi người dùng nhận được một hash-key để lưu dữ liệu vào. Hash-key này sẽ là "session_id". Cấu trúc dữ liệu session sẽ như sau:

![](https://images.viblo.asia/eea615f1-4026-4eb3-ba61-feb122e0b3de.png)

Mỗi người dùng chỉ có thể truy cập vào session của họ. Session có thể được lưu trên cả server hay client. Nếu ở client, nó sẽ được lưu trữ bởi browser (hầu hết trong cookie), nếu nó được lưu trữ trên server, session_id sẽ được tạo và quản lí bởi server.

 Nguyên tắc chung đó là khi client cung cấp cho máy chủ session_id của bạn, và ngược lại máy chủ cấp cho bạn quyền truy cập vào dữ liệu phiên của bạn nếu nó tìm thấy session_id được lưu trữ trong kho dữ liệu session của nó. Cấu trúc session giống như một locker dữ liệu cho người dùng, và key cho locker là session_id, server là người cho bạn thấy cái nào là locker của bạn.

 ![](https://images.viblo.asia/ace9347e-f6a8-4908-9a7f-1d20087b6e71.png)

 Ví dụ: giả sự bạn đã truy cập vào gmail sau khi login và bây giờ bạn uốn điều hướng đến drafts của mình

Trình tự hoạt động sẽ như sau:
1. Bạn gửi http request tới server yêu cầu trang drafts. bạn gửi kèm session_id của mình để thông báo server rằng bạn đã login từ trước và muốn đến trang draft ngay bây giờ. Session_id thường được gửi tỏng cookie, nhưng nó cũng có thể được gửi trong cấc tham số GET hay POST hay bất kì kĩ thuật nào khác
2. Server nhận request của bạn. Nó kiểm tra session_id của bạn bằng cách tìm trong server datastore và nó tìm thấy session_id của bạn là 5. 
3. Server sau đó thực thi mã tương ứng với yêu cầu của bạn: cung cấp trang draft
4. Mã sẽ lấy user_id của bạn từ session được cung cấp bởi máy chủ trước đó, sau đó sử dụng user_id này yêu cầu app database cung cấp cho nó draft của người dùng có user_id này
5. Khi mã đã nhận được draft của bạn từ CSDL, nó tạo 1 trang HTML và bỏ các draft của bạn vào đó rồi đưa cho server
6. Server sẽ gửi lại bạn trang http response chứa các bản draft cùng với session_id của bạn

### Ứng dụng
- Một trong các ứng dụng điển hình là việc quản lý Đăng nhập, Đăng xuất của thành viên mà hầu hết các trang Web nào cũng phải có.
- Với những tác vụ cần xác nhận là thành viên mới sử dụng được, chúng ta cần yêu cầu thành viên đăng nhập vào hệ thống. Nhưng nếu chỉ dùng biến thông thường thì mỗi lần cần thực hiện lại phải đăng nhập vào. Trong khi ấy, nều dùng session thì sau khi đăng nhập, 1 biến session được tạo ra (ví dụ là user_id), thì biến này sẽ tồn tại từ trang này sang trang khác, như thế khi cần thực hiện tác vụ khác cũng cần đăng nhập, ta chỉ cần kiểm tra xem có tồn tại biến user_id này hay chưa là đủ. Nếu tồn tại rồi thì thôi, chưa tồn tại thì đăng nhập.

Link: https://machinesaredigging.com/2013/10/29/how-does-a-web-session-work/

## 1.5 Cookie

![](https://viblo.asia/uploads/5d7bb470-8660-4510-9bf4-1b372e8031a5.png)

 Cookie là một tệp thông tin chứa các dữ liệu của người dùng như tên đăng nhập, mật khẩu, các lựa chọn của người dùng khi truy cập website

Cookie lưu những dữ liệu duyệt web trên máy tính của chúng ta và giúp truy cập những trang web mà chúng ta thường dùng một cách nhanh chóng. Cookie chỉ cho phép website chứa cookie mới có thể đọc được những thông tin này. Thông thường những website lớn như website thương mại, diễn đàn thường có chức năng truy cập cookie để thuận tiện cho việc quảng cáo sản phẩm và lưu trữ các thông tin khách hàng.


Dữ liệu cookie là dữ liệu nhạy cảm vì nó chứa các thông tin của người dùng và có thể làm rò rỉ thông tin cá nhân của người dùng nếu bị lộ ra ngoài. Một số trình duyệt hiện đại có chức năng ngăn chặn cookie gửi ngược lại cho bên thứ 3 hoặc yêu cầu người dùng phải cấp quyền đọc cookie.

Cookie gồm có hai loại chính là:

- Cookie của bên thứ nhất được tạo ra bởi trang web mà người dùng truy cập.

- Cookie của bên thứ ba được tạo ra bởi các trang web khác. Các cookie bên thứ ba thường là Cookie quảng cáo và thu thập dữ liệu người dùng để nâng cao trải nghiệm lướt web.


Trong Cookie có một số thông số sau:
- Địa chỉ URL mà trình duyệt sẽ gửi cookie tới
- Thời gian hết hạn của cookie
- Các cặp biến: giá trị được lưu trữ liên tục

### Cách hoạt động
Khác với dữ liệu gửi từ form (POST hay GET) thì cookies sẽ được trình duyệt tự động gửi đi theo mỗi lần truy cập lên máy chủ.

Trong quá trình làm việc, cookie có thể bị thay đổi giá trị. Cookie sẽ bị vô hiệu hoá nếu cửa sổ trình duyệt điều khiển cookie đóng lại và cookie hết thời gian có hiệu lực. Theo mặc định, thời gian “sống” của cookies là tồn tại cho đến khi cửa sổ trình duyệt sử dụng cookies bị đóng. Tuy nhiên người ta có thể thiết lập tham số thời gian để cookie có thể sống lâu hơn (6 tháng chẳng hạn). Ví dụ như chế độ Remember ID & Password của 1 số trang web.

### Ứng dụng
Người ta thường dùng cookies để lưu trữ các thông tin có liên quan đến nhiều phiên làm việc khác nhau (qua nhiều lần đóng và mở session). Vì giao thức HTTP là giao thức không lưu trạng thái (Mỗi khi xử lý xong một yêu cầu từ máy khách là nó sẽ ngắt kết nối và có thể kết thúc phiên), nên cookie sinh ra để làm nhiệm vụ lưu trữ một số biến trạng thái để khắc phục nhược điểm này.


Link: https://techtalk.vn/session-va-cookies.html


### So sánh cookie và session

| Cookie                                                      | Session                                                                                       |
|-------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| Cookie được lưu ở browser của người dùng                    | Session khong được lưu ở browser người dùng                                                   |
| Dữ liệu cookie được lưu ở ở phía client                     | Dữ liệu session được lưu ở server                                                             |
| Dữ liệu cookie dễ dàng sửa đổi vì nó được lưu ở phía client | Dữ liệu session không dễ sửa đổi vì nó được lưu ở server                                      |
| Dữ liệu cookie có sẵn trong browser cho tới khi nó hết hạn  | Dữ liệu session có sẵn cho browser chạy. Sau khi đóng browser sẽ không mất thông tin session  |
| Các định danh cụ thể liên kết tới server                    | Các định danh cụ thể liên kết tới người dùng                                                  |

## 1.6 JWT
JSON Web Mã (JWT) là một chuẩn mở (RFC 7519) định nghĩa một cách nhỏ gọn và khép kín để truyền một cách an toàn thông tin giữa các bên dưới dạng đối tượng JSON. Thông tin này có thể được xác minh và đáng tin cậy vì nó có chứa chữ ký số. JWTs có thể được ký bằng một thuật toán bí mật (với thuật toán HMAC) hoặc một public / private key sử dụng mã hoá RSA.

Ví dụ:

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjEzODY4OTkxMzEsImlzcyI6ImppcmE6MTU0ODk1OTUiLCJxc2giOiI4MDYzZmY0Y2ExZTQxZGY3YmM5MGM4YWI2ZDBmNjIwN2Q0OTFjZjZkYWQ3YzY2ZWE3OTdiNDYxNGI3MTkyMmU5IiwiaWF0IjoxMzg2ODk4OTUxfQ.uKqU9dTB6gKwG6jQCuXYAiMNdfNRw98Hw_IWuA5MaMo
```

Thoạt trông phức tạp là thế nhưng nếu hiểu, cấu trúc của một JWT chỉ đơn giản như sau:

```
<base64-encoded header>.<base64-encoded payload>.<base64-encoded signature>
```

Nói một cách khác, JWT là sự kết hợp (bởi dấu .) một Object Header dưới định dạng JSON được encode base64, một payload object dưới định dạng JSOn được encode base64 và một Signature cho URI cũng được mã hóa base64 nốt.

### Các thành phần của JWT
#### Header
Header bao gồm hai phần chính: loại token (mặc định là JWT - Thông tin này cho biết đây là một Token JWT) và thuật toán đã dùng để mã hóa (HMAC SHA256 - HS256 hoặc RSA).

```
{
  "alg": "HS256",
  "typ": "JWT"
}
```

#### Payload
Payload chứa các claims. Claims là một các biểu thức về một thực thể (chẳng hạn user) và một số metadata phụ trợ. Có 3 loại claims thường gặp trong Payload: reserved, public và private claims.

- Reserved claims: Đây là một số metadata được định nghĩa trước, trong đó một số metadata là bắt buộc, số còn lại nên tuân theo để JWT hợp lệ và đầy đủ thông tin: iss (issuer), iat (issued-at time) exp (expiration time), sub (subject), aud (audience), jti (Unique Identifier cho JWT, Can be used to prevent the JWT from being replayed. This is helpful for a one time use token.) ... Ví dụ:

```
{
    "iss": "jira:1314039",
    "iat": 1300819370,
    "exp": 1300819380,
    "qsh": "8063ff4ca1e41df7bc90c8ab6d0f6207d491cf6dad7c66ea797b4614b71922e9",
    "sub": "batman",
    "context": {
        "user": {
            "userKey": "batman",
            "username": "bwayne",
            "displayName": "Bruce Wayne"
        }
    }
}
```

- Public Claims - Claims được cộng đồng công nhận và sử dụng rộng rãi.
- Private Claims - Claims tự định nghĩa (không được trùng với Reserved Claims và Public Claims), được tạo ra để chia sẻ thông tin giữa 2 parties đã thỏa thuận và thống nhất trước đó.

#### Signature
Chữ ký Signature trong JWT là một chuỗi được mã hóa bởi header, payload cùng với một chuỗi bí mật theo nguyên tắc sau:

```
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
```

Do bản thân Signature đã bao gồm cả header và payload nên Signature có thể dùng để kiểm tra tính toàn vẹn của dữ liệu khi truyền tải.

### Khi nào thì dùng JWT?
Một trong những tình huống ứng dụng JWT thường gặp, đó là:

- Authentication: Tình huống thường gặp nhất, khi user logged in, mỗi request tiếp đó đều kèm theo chuỗi token JWT, cho phép người dùng có thể truy cập đường dẫn, dịch vụ và tài nguyên được phép ứng với token đó. Single Sign On cũng là một chức năng có sử dụng JWT một cách rộng rãi, bởi vì chuỗi JWT có kích thước đủ nhỏ để đính kèm trong request và sử dụng ở nhiều hệ thống thuộc các domain khác nhau.
- Information Exchange: JSON Web Token cũng là một cách hữu hiệu và bảo mật để trao đổi thông tin giữa nhiều ứng dụng, bởi vì JWT phải được ký (bằng cặp public / private key), bạn có thể chắc rằng người gửi chính là người mà họ nói rằng họ là (nói tóm tắt hơn là không hoặc khó để mạo danh bằng JWT), ngoài ra, chữ ký cũng được tính toán dựa trên nội dung của header và nội dung payload, nhờ đó, bạn có thể xác thực được nội dung là nguyên bản, chưa được chỉnh sửa hoặc can thiệp. Tuy nhiên, một lưu ý hết sức quan trọng là do cấu trúc của JWT đơn giản nên JWT có thể dễ dàng bị decode, do vậy, bạn không nên dùng JWT để transfer các thông tin nhạy cảm.

### Cách hoạt động của JWT
![](https://viblo.asia/uploads/bd5688e3-49bc-42cd-956c-79c96d1f5095.png)

Ví dụ: ứng dụng JWT trong bài toán Authenticate. 
- Khi user login thành công (browser sẽ POST username và password lên server) server sẽ trả về 1 chuổi JWT về browser, token JWT sẽ được lưu lại ở browser của người dùng (thường là LocalStorage hoặc Cookie) thay vì cách tạo ra 1 session trên server và trả về cookie
- Bất cứ khi nào user muốn truy cập vào rout mà phải login mới vào được, browser sẽ gửi token JWT này trong header Authorization, Bearer schma của request gửi đi

```
Authorization: Bearer <token>
```

Đây là cách mà stateless authentication làm việc, trạng thái của user không được lưu tỏng bộ nhớ của server mà được đóng gói hẳn vào trong JWT. Server sẽ kiểm tra token JWT này có hợp lệ không(Bởi vì JWT có tính chất self-contained mọi thông tin cần thiết để kiểm tra JWT đều đã được chứa trong token JWT)

Do tính chất stateless nên ta không cần lo lắng về domains nào được sử dụng cho API của bạn và rắc rối với CORS vì nó không sử dụng cookie

link: https://viblo.asia/p/tim-hieu-ve-json-web-token-jwt-7rVRqp73v4bP

## 1.6 Restful API
RESTful API là một tiêu chuẩn dùng trong việc thiết kế API cho các ứng dụng web (thiết kế Web services) để tiện cho việc quản lý các resource. Nó chú trọng vào tài nguyên hệ thống (tệp văn bản, ảnh, âm thanh, video, hoặc dữ liệu động…), bao gồm các trạng thái tài nguyên được định dạng và được truyền tải qua HTTP.

![](https://topdev.vn/blog/wp-content/uploads/2019/04/restful-api.jpg)

API (Application Programming Interface) là một tập các quy tắc và cơ chế mà theo đó, một ứng dụng hay một thành phần sẽ tương tác với một ứng dụng hay thành phần khác. API có thể trả về dữ liệu mà bạn cần cho ứng dụng của mình ở những kiểu dữ liệu phổ biến như JSON hay XML.

REST (REpresentational State Transfer) là một dạng chuyển đổi cấu trúc dữ liệu, một kiểu kiến trúc để viết API. Nó sử dụng phương thức HTTP đơn giản để tạo cho giao tiếp giữa các máy. Vì vậy, thay vì sử dụng một URL cho việc xử lý một số thông tin người dùng, REST gửi một yêu cầu HTTP như GET, POST, DELETE, vv đến một URL để xử lý dữ liệu.

RESTful API là một tiêu chuẩn dùng trong việc thiết kế các API cho các ứng dụng web để quản lý các resource. RESTful là một trong những kiểu thiết kế API được sử dụng phổ biến ngày nay để cho các ứng dụng (web, mobile…) khác nhau giao tiếp với nhau.

Chức năng quan trọng nhất của REST là quy định cách sử dụng các HTTP method (như GET, POST, PUT, DELETE…) và cách định dạng các URL cho ứng dụng web để quản các resource. RESTful không quy định logic code ứng dụng và không giới hạn bởi ngôn ngữ lập trình ứng dụng, bất kỳ ngôn ngữ hoặc framework nào cũng có thể sử dụng để thiết kế một RESTful API.

### Hoạt động của RESTful
![](https://topdev.vn/blog/wp-content/uploads/2019/04/restful-rest-diagram-api.jpg)

REST hoạt động chủ yếu dựa vào giao thức HTTP. Các hoạt động cơ bản nêu trên sẽ sử dụng những phương thức HTTP riêng.

- GET (SELECT): Trả về một Resource hoặc một danh sách Resource.
- POST (CREATE): Tạo mới một Resource.
- PUT (UPDATE): Cập nhật thông tin cho Resource.
- DELETE (DELETE): Xoá một Resource.

Những phương thức hay hoạt động này thường được gọi là CRUD tương ứng với Create, Read, Update, Delete – Tạo, Đọc, Sửa, Xóa.

Hiện tại đa số lập trình viên viết RESTful API giờ đây đều chọn JSON là format chính thức nhưng cũng có nhiều người chọn XML làm format, nói chung dùng thế nào cũng được miễn tiện và nhanh.

REST là viết tắt của cụm từ Representational State Transfer và các ứng dụng sử dụng kiểu thiết kế REST thì được gọi là RESTful (-ful là tiếp vị ngữ giống như beauty và beautiful). Tất nhiên bạn cũng có thể sử dụng thuật ngữ REST thay cho RESTful và ngược lại.


### Nguyên tắt thiết kế của REST API
1. Dùng HTTP method rõ ràng như sau.

Chúng ta có 4 HTTP method cơ bản bao gồm POST, GET, PUT, DELETE. Với mỗi method sẽ ứng với một chức năng tương ứng của API là tạo, đọc, sửa và xoá. Như sau nè:

![](https://cdn-images-1.medium.com/max/800/1*YRFNzFCvu0gdRHWoTOctPw.png)

2. Sử dụng danh từ số nhiều và không sử dụng động từ.

Ví dụ như /dogs, /cats,... chứ không phải là /getAllDog,...

3. Chỉ sử dụng danh từ số nhiều.
Không vừa dùng số nhiều vừa dùng số ít.

4. Versioning
Versioning là một điều bắt buộc với tất cả resource, việc đánh version cho resource tuân thủ 2 nguyên tắc sau:

- Bắt đầu bằng “v” và kết thúc bằng một số nguyên dương , tránh dùng số thập phân (dùng v1 thay vì v1.5)
- Versioning sẽ được đặt ở vị trí đầu tiên của resource


### Authentication và dữ liệu trả về

RESTful API không sử dụng session và cookie, nó sử dụng một access_token với mỗi request. Dữ liệu trả về thường có cấu trúc như sau:

```
{
    "data" : {
        "id": "1",
        "name": "TopDev"
    }
}
```

### Status code
Khi chúng ta request một API nào đó thường thì sẽ có vài status code để nhận biết sau:

- 200 OK – Trả về thành công cho những phương thức GET, PUT, PATCH hoặc DELETE.
- 201 Created – Trả về khi một Resouce vừa được tạo thành công.
- 204 No Content – Trả về khi Resource xoá thành công.
- 304 Not Modified – Client có thể sử dụng dữ liệu cache.
- 400 Bad Request – Request không hợp lệ
- 401 Unauthorized – Request cần có auth.
- 403 Forbidden – bị từ chối không cho phép.
- 404 Not Found – Không tìm thấy resource từ URI
- 405 Method Not Allowed – Phương thức không cho phép với user hiện tại.
- 410 Gone – Resource không còn tồn tại, Version cũ đã không còn hỗ trợ.
- 415 Unsupported Media Type – Không hỗ trợ kiểu Resource này.
- 422 Unprocessable Entity – Dữ liệu không được xác thực
- 429 Too Many Requests – Request bị từ chối do bị giới hạn

Link: 
https://topdev.vn/blog/restful-api-la-gi/

https://viblo.asia/p/cau-chuyen-cua-restful-api-Qpmle24N5rd

## 1.7 Server Render và Single Page App
### Cơ chế server-side rendering
Cơ chế server-side rendering đã được dùng từ rất lâu. Gọi là server-side rendering vì phần lớn logic sẽ được xử lí ở server:
![](https://toidicodedao.files.wordpress.com/2018/07/diagram-serversiderendered.png?w=346&h=248)

- Khi người dùng vào một trang web, trình duyệt sẽ gửi GET request tới web server
- Web server sẽ nhận request và đọc dữ liệu từ db
- Web server sẽ render HTML, trả về cho browser để hiện thị cho người dùng

Một số tính chất của cơ chế server side rendering:

- Logic từ đơn giản (validation, đọc dữ liệu) cho đến phức tạp (phân quyền, thanh toán) đều nằm ở phía server
- Logic để routing – chuyển trang nằm ở server
- Logic để render – hiển thị trang web cũng nằm ở server nốt

#### Ưu điểm
Vì ra đời lâu nên server-side-render đã được sử dụng và cải tiến rất nhiều (caching, CDN), những ưu điểm của cơ chế này:
- Initial load nhanh, dễ optimize, vì toàn bộ dữ liệu đã được sử lí ở server. Client chỉ việc hiện thị
- Các web framework từ xưa tới nay đều hỗ trợ cơ chế này
- Dễ hiểu dễ code. Developer chỉ cần code 1 project web là được, không cần phải tách ra front-end và back-end
- SEO tốt vì khi bot của google, bing vào web sẽ thấy toàn bộ dữ liệu dưới dạng HTML
![](https://toidicodedao.files.wordpress.com/2018/07/seo-1-500x354.jpg?w=306&h=217)
- Chạy được trên phần lớn mọi browser, kể cả disable JavaScipt vẫn chạy tốt

#### Khuyết điểm
- Mỗi lần người dùng chuyển trang là site phải load lại nhiều lần, gây khó chịu
- Nặng server vì server phải xử lý nhiều logic và dữ liệu. Có thể sử dụng caching để giảm tải.
- Tốn băng thông vì server phải gửi nhiều dữ liệu thừa và trùng  (HTML, header, footer).  Có thể sử dụng CDN để giảm tải.
- Tương tác không tốt như Client Side rendering vì trang phải refresh, load lại nhiều lần.

Cơ chế server side rendering vẫn còn được sử dụng ở rất rất nhiều site.
Các trang web sử dụng cơ chế này:

- Toàn bộ những trang web được build từ CMS như Joomla, WordPress.
- Các trang web bán hàng, web tin tức: Thegioididong, Vnexpress, Zing News
- Các web đọc truyện và forum: Webtretho, vozforum

### Cơ chế client-side rendering
Thế rồi, ở những năm 2010, với sự phát triển của JavaScript và AJAX, cơ chế client-side rendering bắt đầu được sử dụng.

Developer bắt đầu build ứng dụng dưới dạng SPA – Single Page Application. Ứng dụng nằm trong 1 page duy nhất nên được gọi là Single Page Application.

Client Side Rendering tức là việc render HTML, CSS sẽ được thực hiện ở client (Tức JavaScript ở trình duyệt)

![](https://toidicodedao.files.wordpress.com/2018/07/sharepoint-2013-clientside-rendering-csr-jslink-templates-10-638.jpg?w=403&h=227)

So với Server Side Rendering (SSR) đã nhắc trong phần 1, Client Side Rendering (CSR) có những đặc điểm sau:

- Những logic đơn giản (validation, đọc dữ liệu, sorting, filtering) nằm ở client side
- Logic để routing (chuyển trang), render (hiển thị) dữ liệu thì 96.69% là nằm ở client side
- Logic phức tạp (thanh toán, phân quyền) hoặc cần xử lý nhiều (data processing, report) vẫn nằm ở server side.

#### Ưu điểm
Ra mắt sau nên Client Side Rendering giải quyết được một số vấn đề của server side rendering:

- Page chỉ cần load một lần duy nhất. Khi user chuyển trang hoặc thêm dữ liệu, JavaScript sẽ lấy và gửi dữ liệu từ server qua AJAX. User có thể thấy dữ liệu mới mà không cần chuyển trang.
- Chuyển logic sang client nên giảm tải được một phần cho server.
- Giảm được băng thông do chỉ cần lấy JSON và dữ liệu cần thiết, thay vì phải lấy toàn bộ trang
- Với các ứng dụng cần tương tác nhiều, SPA hoạt động mượt mà hơn vì code chạy trên browser, không cần load đi loại lại nhiều

#### Khuyết điểm
- Initial load sẽ chậm hơn nếu không biết optimize. Lý do là broser phải tải toàn bộ JavaScript về (khá nặng), parse và chạy JS, gọi API để lấy dữ liệu từ server (chậm), sau đó render dữ liệu
- Đòi hỏi project phải chia làm 2 phần riêng là back-end (REST api) và front-end nên khó code hơn
- Không chạy được nếu JavaScript bị disable, hoặc ở các trình duyệt cũ không nhận JavaScript ES6 (Có thể dùng transpiler và polyfill nhưng sẽ làm tăng kích cỡ file js)
- SEO không tốt bằng Server Side Rendering (Do bot crawl không đọc được dữ liệu). Để giải quyết, ta phải kết hợp thêm SSR (Bot mới của Google đọc được client-side rendering rồi).
- Nếu client sử dụng mobile, device yếu thì khi load sẽ bị chậm

![](https://toidicodedao.files.wordpress.com/2018/08/frontend-backend-810x389.png?w=397&h=191)

Vì Client-side rendering rất phù hợp cho những ứng dụng cần tương tác nhiều, hầu hết web của các công ty công nghệ, công ty startup đều đùng cơ chế này:
- Facebook (React)
- Instagram (React)
- Netflix (React)
- Dropbox (React)
- AirBnb (React)
- Trello (Angular)
- Paypal (Angular + React)
- Xiaomi (VueJS)
- Alibabe (VueJS)
- Gitlab (VueJS)

link: https://toidicodedao.com/2018/09/11/su-khac-biet-giua-server-side-rendering-va-client-side-rendering/


