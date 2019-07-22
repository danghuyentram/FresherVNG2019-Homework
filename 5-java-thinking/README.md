# Java thinking

# 1. Unit Test/Logging/Performance
## 1.1 Unit test

### What?

Một Unit là một thành phần PM nhỏ nhất mà ta có thể kiểm tra được như các hàm (Function), thủ tục (Procedure), lớp (Class), hoặc các phương thức (Method).

Vì Unit được chọn để kiểm tra thường có kích thước nhỏ và chức năng hoạt động đơn giản, chúng ta không khó khăn gì trong việc tổ chức, kiểm tra, ghi nhận và phân tích kết quả kiểm tra nên việc phát hiện lỗi sẽ dễ dàng xác định nguyên nhân và khắc phục cũng tương đối dễ dàng vì chỉ khoanh vùng trong một Unit đang kiểm tra.

Mỗi UT sẽ gửi đi một thông điệp và kiểm tra câu trả lời nhận được đúng hay không, bao gồm:
- Các kết quả trả về mong muốn
- Các lỗi ngoại lệ mong muốn

Các đoạn mã UT hoạt động liên tục hoặc định kỳ để thăm dò và phát hiện các lỗi kỹ thuật trong suốt quá trình phát triển, do đó UT còn được gọi là kỹ thuật kiểm nghiệm tự động. UT có các đặc điểm sau:

- Đóng vai trò như những người sử dụng đầu tiên của hệ thống.
- Chỉ có giá trị khi chúng có thể phát hiện các vấn đề tiềm ẩn hoặc lỗi kỹ thuật.

**Một số khái niệm đi kèm**
- **Assertion:** Là một phát biểu mô tả các công việc kiểm tra cần tiến hành, thí dụ: AreEqual(), IsTrue(), IsNotNull()… Mỗi một UT gồm nhiều assertion kiểm tra dữ liệu đầu ra, tính chính xác của các lỗi ngoại lệ ra và các vấn đề phức tạp khác như: – Sự tồn tại của một đối tượng – Điều kiện biên: Các giá trị có vượt ra ngoài giới hạn hay không – Thứ tự thực hiện của các luồng dữ liệu …
- **Test Point:** Là một đơn vị kiểm tra nhỏ nhất, chỉ chứa đơn giản một assertion nhằm khẳng định tính đúng đắn của một chi tiết mã nào đó. Mọi thành viên dự án đều có thể viết một test point. Test Case: Là một tập hợp các test point nhằm kiểm tra một đặc điểm chức năng cụ thể, thí dụ toàn bộ giai đoạn người dùng nhập dữ liệu cho đến khi thông tin được nhập vào cơ sở dữ liệu. Trong nhiều trường hợp kiểm tra đặc biệt và khẩn cấp có thể không cần đến test case.
- **Test Suite:** Là một tập hợp các test case định nghĩa cho từng module hoặc hệ thống con.
- **Regression Testing (hoặc Automated Testing):** Là phương pháp kiểm nghiệm tự động sử dụng một phần mềm đặc biệt. Cùng một loại dữ liệu kiểm tra giống nhau nhưng được tiến hành nhiều lần lặp lại tự động nhằm ngăn chặn các lỗi cũ phát sinh trở lại. Kết hợp Regression Testing với Unit Testing sẽ đảm bảo các đoạn mã mới vẫn đáp ứng yêu cầu thay đổi và các đoạn mã cũ sẽ không bị ảnh hưởng bởi các hoạt động bảo trì.
- **Production Code:** Phần mã chính của ứng dụng được chuyển giao cho khách hàng.
- **Unit Testing Code:** Phần mã phụ để kiểm tra mã ứng dụng chính, không được chuyển giao cho khách hàng.

#### Vòng đời của Unit test
UT có 3 trạng thái cơ bản:
- Fail (trạng thái lỗi)
- Ignore (tạm ngừng thực hiện)
- Pass (trạng thái làm việc)
- Toàn bộ UT được vận hành trong một hệ thống tách biệt. Có rất nhiều PM hỗ trợ thực thi UT với giao diện trực quan. Thông thường, trạng thái của UT được biểu hiện bằng các màu khác nhau: màu xanh (pass), màu vàng (ignore) và màu đỏ (fail)

![](https://images.viblo.asia/0df195a2-8ea0-4ebf-9465-0bd8affa1520.jpg)

UT chỉ thực sự đem lại hiệu quả khi:
- Được vận hành lặp lại nhiều lần
- Tự động hoàn toàn
- Độc lập với các UT khác.

### Why
#### Ứng dụng của Unit test
- Kiểm tra mọi đơn vị nhỏ nhất là các thuộc tính, sự kiện, thủ tục và hàm.
- Kiểm tra các trạng thái và ràng buộc của đối tượng ở các mức sâu hơn mà thông thường chúng ta không thể truy cập được.
- Kiểm tra các quy trình (process) và mở rộng hơn là các khung làm việc(workflow – tập hợp của nhiều quy trình)

#### Lợi ích khi áp dụng Unit test
Thời gian đầu, người ta thường do dự khi phải viết UT thay vì tập trung vào code cho các chức năng nghiệp vụ. Công việc viết Unit Test có thể mất nhiều thời gian hơn code rất nhiều nhưng lại có lợi ích sau:
- Tạo ra môi trường lý tưởng để kiểm tra bất kỳ đoạn code nào, có khả năng thăm dò và phát hiện lỗi chính xác, duy trì sự ổn định của toàn bộ PM và giúp tiết kiệm thời gian so với công việc gỡ rối truyền thống.
- Phát hiện các thuật toán thực thi không hiệu quả, các thủ tục chạy vượt quá giới hạn thời gian.
Phát hiện các vấn đề về thiết kế, xử lý hệ thống, thậm chí các mô hình thiết kế.
- Phát hiện các lỗi nghiêm trọng có thể xảy ra trong những tình huống rất hẹp.
- Tạo hàng rào an toàn cho các khối mã: Bất kỳ sự thay đổi nào cũng có thể tác động đến hàng rào này và thông báo những nguy hiểm tiềm tàng.

Trong môi trường làm việc Unit Test còn có tác dụng rất lớn đến năng suất làm việc:
- Giải phóng chuyên viên QA khỏi các công việc kiểm tra phức tạp.
- Tăng sự tự tin khi hoàn thành một công việc. Chúng ta thường có cảm giác không chắc chắn về các đoạn mã của mình như liệu các lỗi có quay lại không, hoạt động của module hiện hành có bị tác động không, hoặc liệu công việc hiệu chỉnh mã có gây hư hỏng đâu đó…
- Là công cụ đánh giá năng lực của bạn. Số lượng các tình huống kiểm tra (test case) chuyển trạng thái “pass” sẽ thể hiện tốc độ làm việc, năng suất của bạn.


Link: https://viblo.asia/p/unit-test-la-gi-maGK7m3Llj2

### How?
### JUnit in Java
JUnit là một framework đơn giản dùng cho việc tạo các unit testing tự động, và chạy các test có thể lặp đi lặp lại. Nó chỉ là một phần của họ kiến trúc xUnit cho việc tạo các unit testing. JUnit là một chuẩn trên thực tế cho unit testing trong Java. 

#### Những đặc điểm đáng lưu ý của JUnit
- Xác nhận (assert) việc kiểm tra kết quả được mong đợi

- Các Test Suite cho phép chúng ta dễ dàng tổ chức và chạy các test

- Hỗ trợ giao diện đồ họa và giao diện dòng lệnh: Các test case của JUnit là các lớp của Java, các lớp này bao gồm một hay nhiều các phương thức unit testing, và những test này lại được nhóm thành các Test Suite. Mỗi phương thức test trong JUnit phải được thực thi nhanh chóng. Tốc độ là điều tối quan trọng vì càng nhiều test được viết và tích hợp vào bên trong quá trình xây dựng phần mềm, cần phải tốn nhiều thời gian hơn cho việc chạy toàn bộ Test Suite. Các lập trình viên không muốn bị ngắt quãng trong một khoãng thời gian dài trong khi các test chạy, vì thế các test mà chạy càng lâu thì sẽ có nhiều khả năng là các lập trình viên sẽ bỏ qua bước cũng không kém phần quan trọng này. Các test trong JUnit có thể là các test được chấp nhận hay thất bại, các test này được thiết kế để khi chạy mà không cần có sự can thiệp của con người. Từ những thiết kế như thế, bạn có thể thêm các bộ test vào quá trình tích hợp và xây dựng phần mềm một cách liên tục và để cho các test chạy một cách tự động

#### Kiến trúc tổng quan
![](https://viblo.asia/uploads/470bae79-3558-41bc-9ddc-578da874b065.png)

- JUnit test framework cung cấp cho chúng ta các gói lớp có sẵn cho phép chúng ta viết các phương thức test một cách dễ dàng.
- TestRunner sẽ chạy các test và trả về kết quả là các Test Results.
- Các lớp của chương trình test chúng ta sẽ được kế thừa các lớp trừu tượng TestCase.
- Khi viết các Test Case chúng ta cần biết và hiểu lớp Assert class.
- Một số định nghĩa trong mô hình tổng quát:
  - Test case : test case định nghĩa môi trường mà nó có thể sử dụng để chạy nhiều test khác nhau
  - TestSuite : testsuite là chạy một tập các test case và nó cũng có thể bao gồm nhiều test suite khác, test suite chính là tổ hợp các test.

#### Cách viết 1 test case
Bạn muốn viết các unit test với JUnit. Việc đầu tiên bạn phải tạo một lớp con thừa kế từ lớp junit.framework.TestCase. Mỗi unit test được đại diện bởi một phương thức testXXX() bên trong lớp con của lớp TestCase. Ta có một lớp Person như sau:

```
    public class Person {  
        private String firstName;  
        private String lastName;  
        public Person(String firstName, String lastName) {  
            if (firstName == null && lastName == null) {  
                throw new IllegalArgumentException("Both names cannot be null");  
            }  
            this.firstName = firstName;  
            this.lastName = lastName;  
        }  
        public String getFullName() {  
            String first = (this.firstName != null) ? this.firstName : "?";
            String last = (this.lastName != null) ? this.lastName : "?";  
            return first + last;  
        }  
        public String getFirstName() {  
            return this.firstName;  
        }  
        public String getLastName() {  
            return this.lastName;  
        }  
    }  
```

Sau đó ta sẽ viết một test case đơn giản để test một số phương thức của lớp trên

```
    import junit.framework.TestCase;  
    public class TestPerson extends TestCase {  
        public TestPerson(String name) {  
            super(name);  
        }  
        /** 
            * Xac nhan rang name duoc the hien dung dinh dang 
        */  
        public void testGetFullName() {  
            Person p = new Person("Aidan", "Burke");  
            assertEquals("Aidan Burke", p.getFullName());  
        }  
        /** 
        * Xac nhan rang nulls da duoc xu ly chinh xac 
        */  
        public void testNullsInName() {  
            Person p = new Person(null, "Burke");  
            assertEquals("? Burke", p.getFullName());  
            p = new Person("Tanner", null);  
            assertEquals("Tanner ?", p.getFullName());  
        }  
    }
```

Lưu ý: mỗi unit test là một phương thức public và không có tham số, được bắt đầu bằng tiếp đầu ngữ test. Nếu bạn không tuân theo quy tắc đặt tên này thì JUnit sẽ không xác định được các phương thức test một các tự động.

Để biên dịch TestPerson, chúng ta phải khai báo gói thư viện junit trong biến đường môi trường classpath:

```
set classpath=%classpath%;.;junit.jar 
javac TestPerson
```

Để chạy một JUnit TestCase, ta có 2 cách

- Chạy với môi trường text, các bạn gõ lệnh:

```
        java junit.textui.TestRunner TestPerson
```

- Chạy với môi trường đồ họa
  
```
        java junit.swingui.TestRunner TestPerson
```

- Chúng ta có thể chạy trực tiếp các TestCase mà không muốn kích hoạt một trong các test runner của JUnit. Chúng ta sẽ thêm phương thức main() vào test case. Ví dụ:

```
 public class TestGame extends TestCase {  
        …  
        public static void main(String [args) {  
            junit.textui.TestRunner.run(new TestSuite(TestGame.class))  
        }  
    }
```

#### Các phương thức Assert()
Các phương thức assertXXX() được dùng để kiểm tra các điều kiện khác nhau.junit.framework.TestCase, lớp cha cho tất cả các test case, thừa kế từ lớp junit.framework.Assert. Lớp này định nghĩa khá nhiều các phương thức assertXXX(). Các phương thức test hoạt động bằng cách gọi những phương thức này.

Sau đây là mô tả các phương thức assertXXX() khác nhau có trong lớp junit.framework.Assert.

- assertEquals(): So sánh 2 giá trị để kiểm tra bằng nhau. Test sẽ được chấp nhận nếu các giá trị bằng nhau.
- assertFalse(): Đánh giá biểu thức luận lý. Test sẽ được chấp nhận nếu biểu thức sai.
- assertNotNull(): So sánh tham chiếu của một đối tượng với null. Test sẽ được chấp nhận nếu tham chiếu đối tượng khác null.
- assertNotSame(): So sánh địa chỉ vùng nhớ của 2 tham chiếu đối tượng bằng cách sử dụng toán tử ==. Test sẽ được chấp nhận nếu cả 2 đều tham chiếu đến các đối tượng khác nhau
- assertNull(): So sánh tham chiếu của một đối tượng với giá trị null. Test sẽ được chấp nhận nếu tham chiếu là null.
- assertSame(): So sánh địa chỉ vùng nhớ của 2 tham chiếu đối tượng bằng cách sử dụng toán tử ==. Test sẽ được chấp nhận nếu cả 2 đều tham - chiếu đến cùng một đối tượng.
- assertTrue(): Đánh giá một biểu thức luận lý. Test sẽ được chấp nhận nếu biểu thức đúng fail(): Phương thức này làm cho test hiện hành thất bại, phương thức này thường được sử dụng khi xử lý các biệt lệ.

#### Set up và tear down
Hai phương thức setUp() và tearDown() là một phần của lớp junit.framework.TestCase Bằng cách sử dụng các phương thức setUp và tearDown. Khi sử dụng 2 phương thức setUp() và tearDown() sẽ giúp chúng ta tránh được việc trùng mã khi nhiều test cùng chia sẻ nhau ở phần khởi tạo và dọn dẹp các biến.

JUnit tuân thủ theo một dãy có thứ tự các sự kiện khi chạy các test. Đầu tiên, nó tạo ra một thể hiện mới của test case ứng với mỗi phương thức test. Từ đó, nếu bạn có 5 phương thức test thì JUnit sẽ tạo ra 5 thể hiện của test case. Vì lý do đó, các biến thể hiện không thể được sử dụng để chia sẻ trạng thái giữa các phương thức test. Sau khi tạo xong tất cả các đối tượng test case, JUnit tuân theo các bước sau cho mỗi phương thức test:
- Gọi phương thức setUp() của test case
- Gọi phương thức test
- Gọi phương thức tearDown() của test case

Sau đây chúng ta sẽ xem xét ví dụ:

```
public class Ship {  
    private String id;  
    public Ship(String id) {  
        this.id = id;  
    }  
    public String getId() {  
        return this.id;  
    }  
}  

public class TestGame extends TestCase {  
    private Game game;  
    private Ship fighter;  
    public void setUp() throws BadGameException {  
        this.game = new Game();  
        this.fighter = this.game.createFighter("001");  
    }
    public void tearDown() {  
        this.game.shutdown();  
    }  
    public void testCreateFighter() {  
        System.out.println("Begin testCreateFigher()");  
        assertEquals("Fighter did not have the correct identifier""001", this.fighter.getId());  
        System.out.println("End testCreateFighter()");  
    }  
    public void testSameFighters() {  
        System.out.println("Begin testSameFighters()");  
        Ship fighter2 = this.game.createFighter("001");  
        assertSame("createFighter with same id should return same object",this.fighter, fighter2);  
        System.out.println("End testSameFighters()");  
    }  
    public void testGameInitialState() {  
        System.out.println("Begin testGameInitialState()");  
        assertTrue("A new game should not be started yet",!this.game.isPlaying());  
        System.out.println("End testGameInitialState()");  
    }  
} 
```

Thông thường bạn có thể bỏ qua phương thức tearDown() vì mỗi unit test riêng không phải là những tiến trình chạy tốn nhiều thời gian, và các đối tượng được thu dọn khi JVM thoát. tearDown() có thể được sử dụng khi test của bạn thực hiện những thao tác như mở kết nối đến cơ sở dữ liệu hay sử dụng các loại tài nguyên khác của hệ thống và bạn cần phải dọn dẹp ngay lập tức. Nếu bạn chạy một bộ bao gồm một số lượng lớn các unit test, thì khi bạn trỏ tham chiếu của các đối tượng đến null bên trong thân phương thức tearDown() sẽ giúp cho bộ dọn rác lấy lại bộ nhớ khi các test khác chạy.

Đôi khi bạn muốn chạy vài đoạn mã khởi tạo chỉ một lần, sau đó chạy các phương thức test, và bạn chỉ muốn chạy các đoạn mã dọn dẹp chỉ sau khi tất cả test kết thúc. Ở phần trên, JUnit gọi phương thứcsetUp() trước mỗi test và gọi tearDown() sau khi mỗi test kết thúc, vì thế để làm được điều như trên, chúng ta sẽ sử dụng lớp junit.extension.TestSetup để đạt được yêu cầu trên.

Ví dụ sau sẽ minh họa việc sử dụng lớp trên:

```
 import junit.extensions.TestSetup;  
    import junit.framework.*;  
    public class TestPerson extends TestCase {   
        public TestPerson(String name) {  
            super(name);  
        }  
        public void testGetFullName() {  
            Person p = new Person("Aidan", "Burke");  
            assertEquals("Aidan Burke", p.getFullName());  
        }  
        public void testNullsInName() {  
            Person p = new Person(null, "Burke");  
            assertEquals("? Burke", p.getFullName());  
            p = new Person("Tanner", null);  
            assertEquals("Tanner ?", p.getFullName());  
        }  
        public static Test suite() {  
            TestSetup setup = new TestSetup(new TestSuite(TestPerson.class)) {  
            protected void setUp() throws Exception {  
            //Thực hiện các đoạn mã khởi tạo một lần ở đây  
        }  
        protected void tearDown() throws Exception {  
            //Thực hiện các đoạn mã dọn dẹp ở đây  
        }  
        return setup;  
    }
```

TestSetup là một lớp thừa kế từ lớp junit.extension.TestDecorator, Lớp TestDecorator là lớp cơ sở cho việc định nghĩa các test biến thể. Lý do chính để mở rộng TestDecorator là để có được khả năng thực thi đoạn mã trước và sau khi một test chạy. Các phương thức setUp() và tearDown() của lớp TestSetupđược gọi trước và sau khi bất kỳ Test nào được truyền vào constructor,

Trong ví dụ trên chúng ta đã truyền một tham số có kiểu TestSuite vào constructor của lớp TestSetup

```
    TestSetup setup = new TestSetup(new TestSuite(TestPerson.class)) {
    }
```
Điều này có nghĩa là 2 phương thức setUp() được gọi chỉ một lần trước toàn bộ bộ test và tearDown() được gọi chỉ một lần sau khi các test trong bộ test kết thúc.

Chú ý: các phương thức setUp() và tearDown() bên trong lớp TestPerson vẫn được thực thi trước và sau mỗi phương thức test bên trong lớp TestPerson.

Link: https://viblo.asia/p/testing-with-junit-in-java-dWrvwWODvw38

## 1.2 Logging
### Log
Log là một quá trình ghi lại những thông tin được thông báo, lưu lại trong quá trình hoạt động của một ứng dụng ở một nơi tập trung. Mục đích chính là để có thể xem lại các thông tin hoạt động của ứng dụng trong quá khứ như debug khi có lỗi xảy ra, check health, xem info, error, warning,…

Có nhiều cách để ghi log: có thể lưu vào file, console (sử dụng lệnh sysout), database hoặc đâu đó để có thể xem lại được.

Trong các ứng dụng thực tế, lưu ra console ít được lựa chọn bởi có một số hạn chế sau:

- Chỉ hiển thị kết quả ra console. Vì vậy, mỗi khi console được đóng thì tất cả thông tin log được show trên console cũng mất.
- Nội dụng log được hiển thị trên console rất khó đọc.

Việc đầu tiên trước khi output dòng log ra hãy tưởng tượng sau đó có thể sử dụng được không, hay chỉ là thông tin vô nghĩa.

Ví dụ: khi bạn xử lý một HTTP request từ phía client, request này khi được xử lý thì gây ra lỗi 500 – “Internal server error”. Khi đó thông tin log ở đây ít nhất phải có:

- Thời gian request.
- Người request.
- HTTP request info: header, request, body,…
- HTTP response info.
- Error stack trace về error đó như lỗi ở đoạn nào, dòng nào, lỗi gì, input như thế nào,…

Khi xem lại đoạn log, chúng ta biết được cách tái hiện lại lỗi hay phán đoán lỗi xảy ra như thế nào để khác phục nhanh hơn và chính xác hơn.


### Phân loại log
Đối tượng level: Định nghĩa độ ưu tiên của các thông tin cần log: gôm 7 cấp độ

| Level | Mô tả                                                                                                                 |
|-------|-----------------------------------------------------------------------------------------------------------------------|
| All   | cấp độ thấp nhất,logger và appender được định nghĩa với cấp độ này, mọi thông tin cần log sẽ được log                 |
| Debug | ở cấp độ này, các thông tin debug sẽ được log                                                                         |
| Infor | ở cấp độ này, các thông tin về luồng làm việc của chương trình sẽ được log.                                           |
| Warn  | cấp độ cho cho phép chúng ta log các thông tin cảnh báo của chương trình.                                             |
| Error | các lỗi khi chạy chương trình sẽ được log nếu chúng ta sử dụng cấp độ này.                                            |
| Fatal | cấp độ này sẽ log các lỗi nghiêm trọng xảy ra trong chương trình, có thể làm cho chương trình không sử dụng được nữa. |
| Off   | đây là cấp độ cao nhất, được sử dụng khi chúng ta không muốn log bất kỳ thông tin nào nữa.                            |

Độ ưu tiên của các cấp độ từ thấp đến cao như sau: ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF.

Một yêu cầu log ở level p đối với log đang ở level q sẽ được kích hoạt nếu p >= q

Ví dụ: in tất cả message trừ debug và infor

```
import org.apache.log4j.*;

public class LogClass {
   private static org.apache.log4j.Logger log = Logger.getLogger(LogClass.class);
   
   public static void main(String[] args) {
      log.setLevel(Level.WARN);

      log.trace("Trace Message!");
      log.debug("Debug Message!");
      log.info("Info Message!");
      log.warn("Warn Message!");
      log.error("Error Message!");
      log.fatal("Fatal Message!");
   }
}
```

Output

```
Warn Message!
Error Message!
Fatal Message!
```

Link: https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm

### Apache Log4j 
Apache Log4j hay ngắn gọn là Log4j là một thư viện được cung cấp bởi Apache hỗ trợ ghi log được viết bằng ngôn ngữ Java.

Cách thành phần chính của Log4j:

- Logger: chịu trách nhiệm thu thập thông tin log.
- Appender: chịu trách nhiệm ghi log tới các vị trí đã được cấu hình (file, console). Các loại Appender: SyslogAppendersends, SMTPAppender, JDBCAppender, FileAppender, SocketHubAppender, SocketAppender, TelnetAppender, ConsoleAppender, JMSAppender, …
- Layout: chịu trách nhiệm định dạng (format) kết quả log. Các loại Layout: PatternLayout, SimpleLayout, XMLLayout, HTMLLayout.

![](https://gpcoder.com/wp-content/uploads/2019/04/Logger-flow.png)

Các tính năng của Log4j:

- Thread safe.
- Tối ưu cho tốc độ.
- Hỗ trợ nhiều output (file + console).
- Hỗ trợ nhiều level log: ALL, TRACE, INFO, WARNING, ERROR, FATAL.

Để sử dụng Log4j, chúng ta cần thực hiện theo các bước:

- Khai báo thư viện cần thiết cho Log4j.
- Cấu hình Log4j.
- Đặt câu lệnh log trong ứng dụng.

Link: https://gpcoder.com/5500-gioi-thieu-java-logging/

## 1.3 Throughput và latency, P99

- Throughput: (thông lượng) Thông lượng (throughput) là lượng thông tin hữu ích được truyền đi trên mạng trong một đơn vị thời gian 

- Latency: độ trễ của mạng là sự chậm trễ thường phát sinh trong xữ lý dữ liệu của mạng máy tính.

- p99: 99th percentile latency: trong 100 latency quan sát được thì có 99 cái có giá trị nhỏ hơn p99 và 1 cái lớn hơn hoặc bằng p99. 

# 2. Threading
## 2.1  Thread, multithreading & concurrency
### Thread (tiểu trình): 
là một tác vụ cơ sở độc lập nhìn từ CPU, nó bao gồm định danh tiểu trình, một con trỏ lệnh, một tập thanh ghi, stack. Các thread trong cùng 1 process có thể chia sẽ vùng mã nguồn, vùng dữ liệu và những tài nguyên dùng chung khác, ví dụ như tập tin đang mở. Một process có thể có nhiều thread

Các trạng thái của thread:
- New thread: khi một thread mới được tạo, nó là new state. Khi một thread ở trạng thái này, thì thread vẫn chưa hoạt động.

- Runnable: một thread đã sẵn sàng để chạy, nó sẽ chuyển sang trạng thái runnable. Trong trạng thái này, một thread có thể thực sự đang chạy hoặc có thể sẵn sàng chạy bất cứ lúc nào.

- Blocked/Waiting: khi một thread tạm thời không hoạt động, sau đó nó có thể nằm trong một những trạng thái sau:
  - Blocked
  - Waiting

### Multi-threading (đa luồng): 
nhiều thread chạy song song nhau và thực hiện nhiệm vụ khác nhau cùng một lúc.

Đối với hệ thống có multi-core, đa luồng tức là các thread sẽ được thực hiện cùng 1 lúc trên các core khác nhau

Đối với hệ thống single-core, vì tại một thời điểm chỉ có 1 lệnh được thực hiện trên core nên đa luồng nghĩa là chia thời gian giữa các thread, làm ta có cảm giác chúng gần nhau chạy đông thời với nhau.

### Concurrency 
![](https://i.stack.imgur.com/OdYWr.gif)

Concurrency là hai hay nhiều process khởi động, chạy và hoàn tất trong khoảng thời gian chồng chéo nhau. Vì trong 1 khoảng thời gian, 1 core máy tính chỉ có thể thực hiện được 1 lệnh duy nhất, nên nó sẽ thực hiện ngắt và chuyển đổi giữa các lệnh của các thread khác nhau. Điều này làm cho ta cảm thấy chúng đang chạy song song với nhau nhưng không phải

## 2.2 Thread-safety trong Java
Trong môi trường multithread, ta cần phải implement write theo 1 cách thread-safety. Nghĩa là nhiều thread khác nhau có thể cùng truy cập 1 tài nguyên mà không gây ra lỗi

### Stateless implementation
Để hiểu về cách tiếp cận này, hãy tìm hiểu ví dụ sau:

```
public class MathUtils {
     
    public static BigInteger factorial(int number) {
        BigInteger f = new BigInteger("1");
        for (int i = 2; i <= number; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }
}
```

factorial() method là một stateless deterministic function. Nghĩa là với mỗi input chỉ còn một output tương ứng duy nhất

Method này không dựa vào các state bên ngoài hay các state đang duy trì. Vì thế, nó cũng được coi như là 1 thread-safe và an toàn khi được gọi bởi multiple thread trong cùng một lúc

Tất cả thread đều an toàn khi gọi factorial() method và có được kết quả mong đợi mà không cần quan tâm kết quả sẽ bị thay đổi bởi các thread khác.

Vì vậy, stateless implementation là cách đơn giản nhất để đạt được thread-safety

### Immutable Implementations
Nếu ta cần phải chia sẽ state giữa các thread, ta có thể tạo thread-safe class bằng cách khiến cho nó bất biến

Đơn giản thì một instance của class là bất biến ( immutable) khi state bên trong nó không thể thay đổi sau khi nó được khởi tạo

Cách đơn giản nhất để tạo class bất biến trong java là khai báo tất cả các trường là private và final và không cung cấp setter ra bên ngoài:

Ví dụ:

```
public class MessageService {
     
    private final String message;
 
    public MessageService(String message) {
        this.message = message;
    }
     
    // standard getter
     
}
```

MessageService là một class bất biến vì state của nó không thể bị thay đổi sau khi khởi tạo, multiple thread chỉ có thể read-only nó. Vậy nên nó là thread-safe

Vậy nên, bất biến cũng là 1 cách khác để đạt được thread-safety


### Synchronized Collections
Ta có thể tạo thread-safe collection bằng cách dùng tập các synchronization wrapper có trong collection framework

Ví dụ:

```
Collection<Integer> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
Thread thread1 = new Thread(() -> syncCollection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6)));
Thread thread2 = new Thread(() -> syncCollection.addAll(Arrays.asList(7, 8, 9, 10, 11, 12)));
thread1.start();
thread2.start();
```

Synchronized collection dùng khóa nội tại trong mỗi method. Nghĩa là method chỉ có thể được truy cập bởi duy nhất 1 thread tại 1 thời điểm, trong lúc đó các thread khác sẽ bị block cho tới khi method đó được unlock bởi thread đầu tiên

###  Concurrent Collections

Để tạo thread-safe collection ta cũng có thể dùng concurrent collection
Java cung cấp java.util.concurrent package, bao gồm một số concurrent collection ví dụ như ConcurrentHashMap:

```
Map<String,String> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("1", "one");
concurrentMap.put("2", "two");
concurrentMap.put("3", "three");
```

Không giống như Synchronized Collections, concurrent collection đạt được thread-safety bằng cách chia nhỏ data của nó thành các segments. Ví dụ trong ConcurrentHashMap, nhiều thread có thể yêu cầu khóa trên các map segment khác nhau, vậy nên multiple thread có thể truy cập map trong cùng 1 thời điểm

Concurrent collecton có lợi thế hơn synchrnized collection vì các thread có thể truy cập đồng thời

Khuyết điểm của cả concurrent collection và synchronized collection là nó chỉ đảm bảo thread-safe cho collection chứ không đảm bảo cho nội dụng bên trong


