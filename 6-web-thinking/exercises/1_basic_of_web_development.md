#  01_basic_of_web_development

Mục lục 
- [01_basic_of_web_development](#01basicofwebdevelopment)
- [1 Frontend Basic](#1-frontend-basic)
  - [1.1 HTTP và HTTP5](#11-http-v%c3%a0-http5)
  - [1.2 CSS3](#12-css3)
    - [CSS box model](#css-box-model)
      - [Content](#content)
      - [Padding](#padding)
      - [Border](#border)
      - [Margin](#margin)
    - [Flex box](#flex-box)
      - [Các Thuộc Tính Của Flexbox Container](#c%c3%a1c-thu%e1%bb%99c-t%c3%adnh-c%e1%bb%a7a-flexbox-container)
      - [Media queries](#media-queries)
  - [1.3 Responsive Web Design](#13-responsive-web-design)
    - [Các vị trí xây dựng Responsive thông dụng](#c%c3%a1c-v%e1%bb%8b-tr%c3%ad-x%c3%a2y-d%e1%bb%b1ng-responsive-th%c3%b4ng-d%e1%bb%a5ng)
- [2 JavaScript](#2-javascript)
  - [2.1 JavaScript Syntax](#21-javascript-syntax)
    - [Khai báo biến](#khai-b%c3%a1o-bi%e1%ba%bfn)
    - [Evaluating variables](#evaluating-variables)
    - [Cấu trúc dữ liệu và kiểu dữ liệu](#c%e1%ba%a5u-tr%c3%bac-d%e1%bb%af-li%e1%bb%87u-v%c3%a0-ki%e1%bb%83u-d%e1%bb%af-li%e1%bb%87u)
  - [2.2 Async trong JavaScript:](#22-async-trong-javascript)
    - [Callbacks](#callbacks)
    - [Callback hell](#callback-hell)
    - [Promises](#promises)
    - [Async/Await](#asyncawait)

# 1 Frontend Basic
## 1.1 HTTP và HTTP5

HTML hoặc Hyper-Text Markup Language có thể được hiểu là ngôn ngữ chính của WordWide Web. Hầu hết những trang web được host trên mạng đều được viết bởi vài phiên bản của HTML. Qua HTML, lập trình viên có thể đảm bảo chắc chắn làm thế nào để  multimedia, văn bản và hyperlink giữa chúng hiển thị trên trình duyệt web. Từ những elements mà kết nối đến văn bản của bạn (hypertext) tới việc giúp những tài liệu này tương tác được (ví dụ: forms); tất cả là thành phần của HTML.

![](https://www.hostinger.com/tutorials/wp-content/uploads/sites/2/2017/03/history-of-html.jpg?x87113)

HTML5 là phiên bản mới nhất của HTML. Hai phiên bản này khá khác nhau. HTML5 là phiên bản mới về ngôn ngữ, thuộc tính và công nghệ mới cho phép xây dựng các trang web, ứng dụng đa dạng và mạnh mẽ hơn. Hai bản này thường được gọi là HTML5.
HTML5 được thiết kế để có thể sử dụng trên tất cả cá nhà phát triển web mở, trang này liên kết với nhiều tài nguyên công nghệ HTML5, phân thành nhiều nhóm dựa vào từng chức năng.


- Semantics: cho phép mô tả chính xác nội dung.

- Connectivity: cho phép giao tiếp với máy chủ theo nhiều cách.

- Offline and storage: cho phép các trang web lưu trữ dữ liệu ở máy khách hàng và hoạt động ngoại tuyến hiệu quả hơn.

- Multimedia: tạo video và âm thanh trong web mở.

- 2D/3D graphics and effects: cho phép phạm vi trình bày đa dạng hơn.

- Performance and integration: tối ưu hóa tốc độ và phần cứng.

- Device access: cho phép sử dụng các thiết bị đầu vào và ra khác nhau.

- Styling: cho phép viết các chủ đề phức tạp.

**Khác biệt chính giữa HTML và HTML5:**
- SVG, canvas và những hình dạng vector đều được hỗ trợ bởi HTML5, khi HTML nếu muốn sử dụng hình vector chỉ có thể dùng nó trong một công nghệ khác như, Flash, VML và silver light.
- HTML5 sử dụng web SQL databases, application cache để lưu dữ liệu tạm, trong khi đó, HTML chỉ có cache của trình duyệt được dùng cho mục đích này.
- Một khác biệt nữa giữa HTML và HTML5 đáng nhắc đến là HTML không cho phép JavaScript chạy trong web browser (thay vì vậy nó chạy trong interface thread của browser), trong khi đó HTML5 hỗ trợ hoàn toàn cho JavaScript để chạy nền (nhờ vào JS web worker API của HTML5).
- HTML5 không dựa trên SGML, cho phép nó tăng luật parsing, có thể tương thích mạnh mẽ hơn
- Trong HTML5, inline MathML và SVG có thể được dùng trong văn bản nơi mà không được hỗ trợ trong HTML.
- Một số elements lỗi thời đã bị loại bỏ hoàn toàn là: isindex, noframes, acronym, applet, basefont, dir, font, frame, frameset, big, center, strike, tt.
- HTML5 hỗ trợ nhiều loại điều khiển form, ví dụ: ngày giờ, email, số lượng, khoảng, số điện thoại, URL, tìm kiếm, vâng vâng
- Có nhiều element được giới thiệu trong HTML. Một vài trong số chúng là quan trọng nhất: summary, time, aside, audio, command, data, datalist, details, embed, wbr, figcaption, figure, footer, header, article, hgroup, bdi, canvas, keygen, mark, meter, nav, output, progress, rp, rt, ruby, section, source, track, video.

![](https://www.hostinger.com/tutorials/wp-content/uploads/sites/2/2017/03/differences-between-html-and-html5.png?x87113)

![](https://cdn.educba.com/academy/wp-content/uploads/2018/06/HTML-VS-HTML5.jpg)


## 1.2 CSS3
CSS3 là phiên bản mới nhất của Thuộc tính CSS. Thuật ngữ CSS3 không chỉ là một tham chiếu đến các tính năng mới trong CSS, mà là cấp độ thứ 3 trong tiến trình của Thuộc tính CSS. CSS3 chứa tất cả mọi thứ có trong CSS2.1 (phiên bản trước). Nó cũng bổ sung các tính năng mới để giúp các nhà phát triển giải quyết một số vấn đề mà không cần đánh dấu phi ngữ nghĩa, tập lệnh phức tạp hoặc hình ảnh bổ sung.

**Sự khác nhau giữa CSS và CSS3**
Thay đổi lớn nhất hiện của CSS3 là việc giới thiệu các mô-đun. Ưu điểm của các mô-đun là ​​cho phép thuộc tính được hoàn thành và phê duyệt nhanh hơn vì các phân đoạn được hoàn thành và được phê duyệt theo từng khối.

Các tính năng được bao gồm trong CSS3 bao gồm hỗ trợ cho các bộ chọn bổ sung, đổ bóng, góc tròn, nhiều hình nền, hình động, độ trong suốt ..vv... Nó chứa “thuộc tính CSS” (đã được chia thành các phần nhỏ hơn). Ngoài ra còn có các mô-đun mới được thêm vào. Một số mô-đun quan trọng nhất trong CSS3 là:

- Bộ chọn
- Mô hình hộp
- Hình nền và đường viền
- Giá trị hình ảnh và nội dung thay thế
- Hiệu ứng văn bản
- Chuyển đổi 2D / 3D
- Ảnh động
- Bố cục nhiều cột
- Giao diện người dùng


### CSS box model
Ở trong HTML với mọi element chúng ta có thể gọi nó là một cái "hộp". Và trong CSS thì thuật ngữ Box Model dùng để nói về design và layout. Chúng ta có thể coi CSS Box Model giống như là một cái hộp bao quanh element của chúng ta và trong đó có rất nhiều lớp dày mỏng khác nhau, các lớp dày mỏng đó bao gồm: margins, border, padding và cuối cùng là phần nội dung của chúng ta (text và ảnh). Chúng ta có thể xem hình dưới đây để dễ hình dung hơn về Box Model:

![](https://s3-ap-southeast-1.amazonaws.com/kipalog.com/k68zn5q971_image.png)

- Content: như đã nói ở trên đây là phần mà text và hình ảnh của chúng ta xuất hiện
- Padding: là một khoảng trống kế tiếp bọc xung quanh content
- Border: phần khung bao bọc xung quanh padding và content
- Margin: cuối cùng, margin là phần ngoài cùng của Box Model, chỉ là một khoảng trống không màu

#### Content
Tiếp tục nhắc lại content là phần xuất hiện của text và hình ảnh, phần này không có gì đặc biệt để nói cho lắm, tổng kích cở của text bao nhiêu (có thể lớn nhỏ nếu chúng ta tùy chỉnh font-size) hay hình ảnh bao lớn thì đó cũng là kích cở của content. Tuy nhiên, có một điều cần chú ý là content và hai thuộc tính height và width, khi chúng ta đặt một height hay một width hoặc thậm chí cả hai cho một element thì height và width này chỉ tác động lên một vùng duy nhất đó là content chứ không hề đá động gì đến padding, border hay margin cả. Vậy, phần kích thước đầu tiên của Box Model đó chính là content.

#### Padding
Nói đơn giản về padding thì nó là thuộc tính dùng để tạo ra một vùng khoảng trống bao bọc xung quanh content và nó sẽ nằm bên trong border. Khi xét padding cho một element ta có thể xét theo 2 kiểu:

- Xét theo từng bên của element: top, right, bottom và left như sau:
    - padding-top
    - padding-right
    - padding-bottom
    - padding-left
- Hay là xét theo kiểu viết tắc:
    - padding: 5px 6px 8px 7px (top right bottom left)
    - padding: 5px 6px 7px (top right&left bottom)
    - padding: 5px 6px (top&bottom right&left)
    - padding: 5px (cho toàn bộ 4 mặt)
- Và chúng ta có các đơn vị để xét padding như:
    - "Đơn vị đo chiều dài" như: cm, px, em, etc
    - Phần trăm (%): cái % này sẽ phụ thuộc vào width của thằng element chứa nó
    - Inherit: kế thừa cái padding đã được xét ở element mẹ (hay bố cũng được)

#### Border
Border, lớp tiếp theo của Box Model, bỏ qua các phần trang trí màu mè của nó, chúng ta chỉ xét tới các phần như border-width hay xét border cho các mặt của element. Thứ nhất, nói sơ qua cấu trúc border thì nó sẽ gồm có:

- border-width: độ rộng của border tính bằng các đơn vị như: in px, pt, cm, em, etc
- border-style: style cho border như kiểu đường thằng, chấm bi, song nét,... (solid, dotted, double,.. )
- border-color: màu cho border và được xét bởi nhiều cách: tên màu, giá trị hexa, giá trị rgb hay là trong suốt (transparent)

#### Margin
Lớp cuối cùng của Box Model đó chính là lớp margin bên ngoài, margin là một thuộc tính dùng để tạo khoảng cách xung quanh element để cách element đó với các thứ khác và phần margin sẽ nằm bên ngoài border. Giống như padding thì margin cũng được xét theo hai kiểu, một là:

- margin-top
- margin-right
- margin-bottom
- margin-left

Và thứ hai là viết tắc tương tự như padding. Về phần đơn vị, margin cũng có các kiểu: "đơn vị đo độ dài", % hay inherit như padding. Tuy nhiên, margin còn có thêm một giá tị nửa, đó chính là margin:auto. Khi ta dùng cái auto này thì trình duyệt sẽ tự động tính toán và đặt lại khoảng cách cho element sao cho nó được căn ngay chính giữa bên trong container chứa nó.

link: https://kipalog.com/posts/Cau-truc-CSS-Box-Model

### Flex box
Bố cục Flex được thiết lập từ một khung lớn (parent container) đóng vai trò là khung linh hoạt (flex containter) và các thẻ con ngay trong nó (immediate children) đóng vai trò các mục nhỏ linh hoạt (flex items). 

![](https://viblo.asia/uploads/64c437e0-58ce-430d-b535-6ce0dd2754bb.png)

Từ hình ảnh trên bạn có thể thấy các thuộc tính và thuật ngữ được sử dụng để mô tả khung linh hoạt (flex containter) và các các mục nhỏ linh hoạt (flex items). Để hiểu sâu hơn, bạn có thể đọc tài liệu flexbox model bởi W3C Việc bố trí flexbox đã trải qua nhiều lần lặp lại và một số thay đổi cú pháp từ dự thảo ban đầu của nó trong năm 2009, do đó tránh nhầm lẫn vaà để rõ ràng chúng ta nên sử dụng thay đổi lần cuối cùng vào (tháng 9 năm 2014). Nếu bạn cần duy trì tính tương thích của trình duyệt cũ, bạn có thể đọc bài viết này về cách sử dụng nó một cách tốt nhất.

#### Các Thuộc Tính Của Flexbox Container
**flex-direction**

Thuộc tính này quy định cách trình bày các mục linh hoạt trong khung linh hoạt, bằng cách đặt hướng cho khung linh hoạt theo một trục chính. Chúng có thể được trình bày theo hai hướng, giống như hàng ngang hay hàng dọc.

Theo hàng ngang (row), các mục linh hoạt sẽ được sắp xếp theo một hàng từ trái qua phải.

```
.flex-container {
  -webkit-flex-direction: row; /* Safari */
  flex-direction:         row;
}
```

![](https://viblo.asia/uploads/bba4a3f6-d0ff-424d-abcf-406c2782d910.png)

Theo hàng đảo ngược (row-reverse), các mục linh hoạt sẽ được sắp xếp theo một hàng ngược lại.

```
.flex-container {
  -webkit-flex-direction: row-reverse; /* Safari */
  flex-direction:         row-reverse;
}
```

![](https://viblo.asia/uploads/496b05b5-c5c6-4780-a4d1-d94364f8a278.png)

Tương tự với hàng dọc (column), các mục linh hoạt sẽ được sắp xếp theo một cột từ trên xuống dưới.

```
.flex-container {
  -webkit-flex-direction: column; /* Safari */
  flex-direction:         column;
}
```

![](https://viblo.asia/uploads/133446b0-b255-42f2-94f8-ea4d1b87992b.png)

Và ngược lại…
```
-webkit-flex-direction: column-reverse; /* Safari */
  flex-direction:         column-reverse;

```

![](https://viblo.asia/uploads/8c490d49-30fa-4a28-97a7-24105370154e.png)

Giá trị mặc định: row Chú ý: hàng (row) và hàng đảo ngược (row-reverse) được phụ thuộc vào chế độ viết cho nên ở chế độ viết từ phải qua trái (rtl) thì chúng sẽ tự động được đảo ngược.

**flex-wrap**
Ý tưởng ban đầu của flexbox là đặt các mục linh hoạt theo một hàng duy nhất. Nhưng sẽ thế nào nếu chúng ta muốn có một bố cục với các mục xếp thành nhiều hàng? Thuộc tính flex-wrap được tạo ra để giúp chúng ta giải quyết điều này.

Các mục linh hoạt được hiển thị trên cùng một hàng, mặc định chúng sẽ tự động dãn hoặc thu hẹp để vừa với chiều rộng của khung lớn.

```
.flex-container {
  -webkit-flex-wrap: nowrap; /* Safari */
  flex-wrap:         nowrap;
}
```

![](https://viblo.asia/uploads/c74c4eb4-79ff-45de-832a-bc73c896d543.png)

**flex-flow**
Thuộc tính này là một dạng viết tắt (shorthand) cho hai thuộc tính flex-direction và flex-wrap.

```
.flex-container {
  -webkit-flex-flow: <flex-direction> || <flex-wrap>; /* Safari */
  flex-flow:         <flex-direction> || <flex-wrap>;
}
```

**justify-content**
Thuộc tính justify-content sắp xếp các mục linh hoạt theo trục chính của dòng hiện tại trong khung linh hoạt. Nó giúp bổ sung không gian còn thừa ngay cả khi các mục linh hoạt trên một dòng không thể co giãn hoặc đã đạt đến kích thước tối đa.

```
.flex-container {
  -webkit-justify-content: flex-start; /* Safari */
  justify-content:         flex-start;
}
```

![](https://viblo.asia/uploads/9e8a90bc-c7d2-43d9-a4cd-9ea27b55b192.png)

Link: https://viblo.asia/p/huong-dan-su-dung-css-flexbox-cho-nguoi-moi-tim-hieu-oOVlYyA4l8W


#### Media queries

Media Query là một trong những module mới được thêm vào trong CSS3. Nó là một sự cải thiện của Media Type đã có từ CSS2, bằng việc thêm vào những cú pháp query để ta có thể đáp ứng được cho nhiều device với nhiều kích cỡ màn hình khác nhau.

Module Media Query hiện đã được implement đầy đủ trong các trình duyện hiện đại như Webkit, Firefox, Opera hay IE (kể từ version 9).

**Media Type**
Trước khi tìm hiểu về cú pháp của Media Query, trước hết ta hãy nói qua về Media Type trước.

Media Type vốn đã có từ CSS2, nó giúp ta xác định định dạng của mỗi loại thiết bị. Tất cả các giá trị của Media Type bao gồm

- All: Dùng cho tất cả các loại Media Type
- Aural: Dùng cho speech and sound synthesizers
- Braille: Dùng cho các devices liên quan đến chữ nổi (braille)
- Embossed: Dùng cho các loại máy in các trang braille
- Handheld: Dùng cho các thiết bị nhỏ, thiết bị cầm tay
- Print: Dùng cho máy in
- Projection: Dùng cho các loại máy chiếu
- Screen: Dùng cho computer screen
- Tty: Dùng cho các thiết bị sử dụng fixed-pitch character grid
- Tv: Dùng cho các loại TV

Ta có thể sử dụng Media Type theo cú pháp sau đây:

@media media_type {rules}

Chẳng hạn như ta có đoạn code sau:

```
/*Áp dụng cho computer screens*/
@media screen {
  body {
    width: 960px;
  }
}
/*Áp dụng cho các thiết bị cầm tay nhỏ*/
@media handheld {
  body {
    width: 320px
  }
}
```

**Media Query Syntax**

Cú pháp của Media Query được mở rộng ra từ cú pháp của Media Type như sau:

@media media_type (feature:value) { rules }

Các thuộc tính feature và value sẽ giúp chúng ta xác định chính xác từng loại màn hình mà ta mong muốn.

Đôi khi có những Media Feature mà một mình nó đứng không cũng có ý nghĩa, mà không cần đến value, thì cú pháp của ta sẽ như sau:

@media media_type (feature) { rules }

Ngoài ra thì như đã nói mặc định của Media Type sẽ luôn là all, thế nên ta có thể lược bỏ phần media_type ở trên nếu muốn nó được apply cho tất cả các devices.

link: https://viblo.asia/p/tim-hieu-ve-media-query-3ZabG9oRzY6E

## 1.3 Responsive Web Design
Responsive Web Design là kỹ thuật thiết kế web đáp ứng với nhiều kích cỡ giao diện trên nhiều thiết bị khác nhau. Đáp ứng nhiều kích cỡ ở đây có nghĩa là trên mọi thiết bị đều phải chạy toàn màn hình (full screen), không bị vỡ giao diện và ẩn mất đi một số vị trí nào và không phải sử dụng chức năng zoom để xem.

Hiện nay công nghệ sản xuất ra rất nhiều thiết bị có thể lướt web được, từ smart phone cho tới laptop, máy tính bàn (desktop), mỗi thiết bị lại có một kích cỡ màn hình khác nhau nên trước đây nếu chúng ta sử dụng giao diện nhỏ thì phải zoom mới có thể thấy được thôn tin. Vấn đề này bạn đừng hiểu nhầm đó là responsive nhé, responsive là không zoom mà vẫn có thể sử dụng được website.

Như hình dưới đây là giao diện của website freetuts.net lúc ở màn hình to và màn hình nhỏ.

### Các vị trí xây dựng Responsive thông dụng
Hầu hết chúng ta phải tạo Responsive cho mọi vị trí trên website, tuy nhiên mình sẽ liệt kê một số vị trí thông dụng cho bạn dễ hình dung về cách hoạt động của Responsive là như thế nào.

Responsive menu:
Vị trí menu điều hướng các hoạt động của website, nó sẽ chứa các đường dẫn tới các ngỏ ngách để từ đó người dùng có thể tìm thấy thông tin mong muốn. Thông thường với vị trí này chúng ta phải tạo responsive cho nó, nghĩa là ở giao diện lớn thì menu chúng ta hiển thị dài và chiều ngang nhưng qua giao diện nhỏ thì chúng ta ẩn hết đi chỉ hiển thị một nút nhỏ và khi người dùng click vào nút đó thì hiển thị menu ra theo chiều dọc.

Bạn có thể xem demo bằng cách thu nhỏ trình duyệt của lại và xem menu của website freetuts.net sẽ thay đổi như thế nào nhé.

Responsive Column:
Mỗi giao diện thông thường chúng ta có các vị trí sidebar left, sidebar right và content, như vậy với ba vị trí này thì chúng ta tạm chia làm ba column. Nếu ở giao diện lớn thì chúng ta sẽ hiển thị nó ở dạng 3 column nhưng ở giao diện nhỏ thì chúng ta chỉ hiển thị nó ở dạng 1 column thôi.

Responsive font size:
Với font size thì chúng ta hay thay đổi kích thước cho nó, với giao diện lớn thì chúng ta hiển thị kích thước lớn nhưng qua giao diện nhỏ thì đôi lúc chúng ta sẽ cho kích thước nhỏ lại để nó hiển thị trên một hàng hoặc hiển thị nhỏ lại để dễ nhìn hơn.

Responsive image:
Với hình ảnh thì nếu bạn thiết lập chiều rộng và chiều cao cho nó thì khi qua giao diện nhỏ sẽ bị vỡ ngay vì kích thước của hình ảnh lớn hơn kích thước của thiết bị. Lúc này ta phải thay đổi lại kích thước làm sao hiển thị đúng với chiều rộng của thiết bị.

Thật ra đây là một số vị trí thường gặp thôi chứ trong thực tế thì tùy vào mỗi layout mà chúng ta có nhưng cách thiết kế khác nhau nhé.

# 2 JavaScript
## 2.1 JavaScript Syntax
### Khai báo biến
- var
Khai báo 1 biến có phạm vi truy cập xuyên suốt function chứa nó.

```
function foo() { 
  var x = 10; 
  if (true) { 
   var x = 20; // x ở đây cũng là x ở trên
   console.log(x); // in ra 20 
  } 
   console.log(x); // vẫn là 20 
}
```

- let
Khai báo 1 biến chỉ có thể truy cập được trong block bao quanh nó.

```
function foo() { 
  let x = 10; 
  if (true) { 
    let x = 20; // x này là x khác rồi đấy
    console.log(x); // in ra 20 
  } 
  console.log(x); // in ra 10 
}

```

- const
Khai báo 1 hằng số - là một giá trị không thay đổi được trong suốt quá trình chạy.
```
const A = 5; 
A = 10; // Lỗi Uncaught TypeError: Assignment to constant variable
```

### Evaluating variables
Một biến được khai báo với cú pháp var hoặc let mà không có giá trị khởi tạo, sẽ có giá trị là undefined.

Khi truy cập vào một biến chưa được khai báo, bạn sẽ nhận được kết quả là: ReferenceError.

```
var a;
console.log("The value of a is " + a); // logs "Giá trị của a là undefined"
console.log("The value of b is " + b); // throws ReferenceError exception
```

Bạn có thể sử dụng undefined  để xác định một biến có giá trị hay không? Dưới đây là một ví dụ, biến input không được gán giá trị, vậy câu điều kiện if là return true và không chạy vào else.

```
var input;
if(input === undefined){
  doThis();
} else {
  doThat();
}
```
### Cấu trúc dữ liệu và kiểu dữ liệu
Six data types that are primitives:
- Boolean. true and false.
- null. A special keyword denoting a null value. Because JavaScript is case-sensitive, null is not the same as Null, NULL, or any other variant.
- undefined. A top-level property whose value is undefined.
- Number. 42 or 3.14159.
-String. "Howdy"
- Symbol (new in ECMAScript 6). A data type whose instances are unique and immutable.
- and Object

## 2.2 Async trong JavaScript:
### Callbacks
Vậy vấn đề ở đây là gì? Tưởng tượng khi JavaScript gọi một process mất cực kỳ nhiều thời gian như Ajax hoặc xử lý database? Thao tác này có thể mất nhiều giây, hoặc thậm chí cả phút. Và trình duyệt sẽ bị khóa khi nó đang chờ response, tức là không thể xử lý thêm request của người dùng trong khoảng thời gian đó.

Giải pháp ở đây là xử lý bất đồng bộ. Trong JavaScript, khi một thao tác bất đồng bộ có kết quả (kết quả này có thể là dữ liệu trả về hoặc lỗi xảy ra khi thao tác), nó sẽ gọi một function mỗi khi kết quả sẵn sàng, function này được gọi là "callback". Trong khi đó JavaScript tiếp tục thực thi bình thường. Bạn có thể thấy các framework thường có APIs mà bạn có thể thêm callback function để thực thi sau khi gọi. Đăng ký một event listener trong browser với addEventListener, đọc nội dung file với fs.readFile hoặc đăng ký middleware trong express web server với server.use là ví dụ của những APIs mà sử dụng callback.

Đây là ví dụ lấy dữ liệu từ một URL sử dụng module request:

```
const request = require(‘request’);
request('https://www.somepage.com', function (error, response, body) {
  if(error){
    // Handle error.
  }
  else {
    // Successful, do something with the result.
  }
});
```

Ta có thể viết lại nó như sau:

```
const request = require(‘request’);
function handleResponse(error, response, body){
    if(error){
        // Handle error.
    }
    else {
        // Successful, do something with the result.
    }
}
request('https://www.somepage.com', handleResponse);
```

Như bạn thấy, request lấy function handleResponse làm tham số cuối cùng. Function này không được thực thi ngay. Nó được lưu lại và sẽ thực thi khi thao tác lấy dữ liệu từ url hoàn thành.


### Callback hell
```
fs.readdir(source, function (err, files) {
  if (err) {
    console.log('Error finding files: ' + err)
  } else {
    files.forEach(function (filename, fileIndex) {
      console.log(filename)
      gm(source + filename).size(function (err, values) {
        if (err) {
          console.log('Error identifying file size: ' + err)
        } else {
          console.log(filename + ' : ' + values)
          aspect = (values.width / values.height)
          widths.forEach(function (width, widthIndex) {
            height = Math.round(width / aspect)
            console.log('resizing ' + filename + 'to ' + height + 'x' + height)
            this.resize(width, height).write(dest + 'w' + width + '_' + filename, function(err) {
              if (err) console.log('Error writing file: ' + err)
            })
          }.bind(this))
        }
      })
    })
  }
})
```

Khi ta có callback lồng trong callback như thế này, đoạn code trở nên khó đọc và rất loạn, nó được gọi là callback hell. Điều này xảy ra khi nhiều người muốn viết JavaScript theo cách xử lý tuần tự từ trên xuống dưới, tuy nhiên đó lại là sai lầm dẫn đến đoạn code rất rối.

### Promises
Vậy làm sao để xử lý khi gặp callback hell? ES2015 (ES6) giới thiệu Promises. Callbacks vẫn được sử dụng, nhưng Promises cung cấp một cú pháp rõ ràng hơn để chain các câu lệnh bất đồng bộ.

Để tạo một Promise, ta dùng đoạn code sau:

```
const myPromise = new Promise(function(resolve, reject) {
  
  // code here
  
  if (codeIsFine) {
    resolve('fine')
  } else {
    reject('error')
  }
})
myPromise
  .then(function whenOk(response) {
    console.log(response)
    return response
  })
  .catch(function notOk(err) {
    console.error(err)
  })
```

Hãy phân tích đoạn code trên:

- Một promise được khởi tạo với một function có câu lệnh resolve hoặc reject
- Đặt đoạn code bất đồng bộ trong function Promise resolve khi mọi thứ xảy ra như mong muốn Nếu không thì reject
- Khi resolve được chạy thì đoạn code trong .then sẽ thực thi Khi reject được chạy thì .catch sẽ được trigger

### Async/Await
Promises có thể hơi khó nhằn, nên ES2017 giới thiệu async và await, nó giúp Promise trông đơn giản hơn, tránh được chain .then() dài dằng dặc.

Async/Await là bước kế tiếp trong công cuộc xử lý thao tác bất đông bộ trong JavaScript. Nó cho bạn 2 từ khóa mới là: async và await. Async sẽ thông báo rằng function sẽ xử lý bất đồng bộ, và await sẽ được dùng để báo chúng ta muốn đợi kết quả của thao tác bất đồng bộ trong một function có đánh dấu async.

Ví dụ cơ bản:

```
async function getSomeAsyncData(value){
    const result = await fetchTheData(someUrl, value);
    return result;
}
```

Lưu ý là chỉ dùng được await trong function có từ khóa async ở phía trước:

```
function justANormalFunction(value){
    // function này sẽ sinh lỗi cú pháp vì thiếu khai báo từ khóa `async`
    const result = await fetchTheData(someUrl, value);
    return result;
}
```

Ta chỉ await được những function trả về một Promise hoặc nó có khai báo async.

```
function fetchTheData(someValue){
    return new Promise(function(resolve, reject){
        getData(someValue, function(error, result){
            if(error){
                reject(error);
            }
            else{
                resolve(resutl);
            }
        })
    });
}
async function getSomeAsyncData(value){
    const result = await fetchTheData(value);
    return result;
}
```
Và giờ đây, ta có thể dùng await để viết code trông dễ đọc hơn. Thay vì

```
function printAll(){
  printString("A")
  .then(() => printString("B"))
  .then(() => printString("C"))
}
printAll()
```

ta có thể tận dụng await như sau:

```
async function printAll(){
  await printString("A")
  await printString("B")
  await printString("C")
}
printAll()

```

link: https://viblo.asia/p/javascript-tu-callbacks-den-promises-va-asyncawait-Do754Jq3ZM6





