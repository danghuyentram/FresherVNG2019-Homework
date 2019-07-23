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

Link: https://www.baeldung.com/java-thread-safety

## 2.3 Threadpool, executors
### Threadpool
Xét về hiệu suất, tạo ra một Thread mới là một hoạt động tốn kém bởi vì nó đòi hỏi hệ điều hành cung cấp tài nguyên để có thể thực thi task (tác vụ). Trên thực tế, ThreadPool được sử dụng cho các ứng dụng quy mô lớn khởi chạy rất nhiều luồng ngắn ngủi để sử dụng hiệu quả các tài nguyên và tăng hiệu suất.

Trong Java, ThreadPool được dùng để giới hạn số lượng Thread được chạy bên trong ứng dụng của chúng ta trong cùng một thời điểm. Nếu chúng ta không có sự giới hạn này, mỗi khi có một Thread mới được tạo ra và được cấp phát bộ nhớ bằng từ khóa new thì sẽ có vấn đề về bộ nhớ và hiệu suất, có thể dẫn đến lỗi crash chương trình.

Ví dụ: Khi chúng ta viết chương trình tải các tập tin từ Internet, mỗi tập tin cần 1 Thread để thực hiện quá trình tải, giả sử cần tải 100 tệp hình ảnh thì chúng ta phải cần tới 100 Thread hoạt động cùng một thời điểm trong cùng một chương trình. Điều này sẽ dễ dẫn đến lỗi quá tải của chương trình, làm ảnh hưởng đến hiệu suất và có thể dẫn đến gây lỗi (crash) chương trình.

Vì vậy, thay vì tạo các luồng mới khi các task (nhiệm vụ) mới đến, một ThreadPool sẽ giữ một số luồng nhàn rỗi (no task) đã sẵn sàng để thực hiện tác vụ nếu cần. Sau khi một thread hoàn thành việc thực thi một tác vụ, nó sẽ không chết. Thay vào đó nó vẫn không hoạt động trong ThreadPool và chờ đợi được lựa chọn để thực hiện nhiệm vụ mới.

Chúng ta có thể giới hạn một số lượng nhất định các Thread đồng thời trong ThreadPool, rất hữu ích để ngăn chặn quá tải. Nếu tất cả các Thread đang bận rộn thực hiện nhiệm vụ, nhiệm vụ mới được đặt trong một hàng đợi (BlockingQueue), chờ đợi một Thread trở nên có sẵn.

![](https://gpcoder.com/wp-content/uploads/2018/02/threadpool-executor.png)

Java Concurrency API hỗ trợ một vài loại ThreadPool sau:

- Cached thread pool: giữ một số luồng còn sống (alive) và tạo ra các luồng mới nếu cần.
- Fixed thread pool: giới hạn số lượng tối đa của các Thread được tạo ra để thực thi các task (nhiệm vụ). Các task khác đang chờ trong hàng đợi (BlockingQueue).
- Single-threaded pool: chỉ giữ một Thread thực thi một nhiệm vụ một lúc.
- Fork/Join pool: một Thread đặc biệt sử dụng Fork/ Join Framework để tận dụng lợi thế của nhiều bộ vi xử lý để thực hiện công việc lớn nhanh hơn bằng cách chia nhỏ công việc thành các phần nhỏ hơn để xử lý đệ quy.

Trong thực tế, ThreadPool được sử dụng rộng rãi trong các máy chủ web, nơi một ThreadPool được sử dụng để phục vụ các yêu cầu của khách hàng. Thread pool cũng được sử dụng trong các ứng dụng cơ sở dữ liệu nơi mà một ThreadPool được sử dụng để duy trì các kết nối mở với cơ sở dữ liệu.

Việc cài đặt ThreadPool là một công việc phức tạp, nhưng chúng ta không cần phải lo lắng điều này bởi vì Java Concurrency API đã xây dựng sẵn (build-in) các lớp hỗ trợ ThreadPool trong gói java.util.concurrent. Chúng ta sẽ tiếp tục tìm hiểu ở các phần tiếp theo của bài viết này.

Link: https://gpcoder.com/3548-huong-dan-tao-va-su-dung-threadpool-trong-java/

### Executors và Executor
#### Executors
Executors có khả năng chạy các task bất đồng bộ và quản lí các thread trong pool, chúng ta không cần phải tạo thủ công các thread mới. Tất cả thread trong pool sẽ được tái sử dụng lại các task khác khi nó đã xong task của mình, chúng ta có thể chạy nhiều task đồng thời trong suốt vòng đời của ứng dụng chỉ với single executor service

Ví dụ sử dụng executors:

```
ExecutorService executor = Executors.newSingleThreadExecutor();
executor.submit(() -> {
    String threadName = Thread.currentThread().getName();
    System.out.println("Hello " + threadName);
});

// => Hello pool-1-thread-1
```

Class Executors cung cấp nhiều factory method tiện ích cho việc tạo các loại executor service khác nhau. 

Link: https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

#### Executor

Một Executor là một đối tượng chịu trách nhiệm quản lý các luồng và thực hiện các tác vụ Runnable được yêu cầu xử lý. Nó tách riêng các chi tiết của việc tạo Thread, lập kế hoạch (scheduling), … để chúng ta có thể tập trung phát triển logic của tác vụ mà không quan tâm đến các chi tiết quản lý Thread.

![](https://gpcoder.com/wp-content/uploads/2018/02/threadpool-executor-service.png)

Java Concurrency API định nghĩa 3 interfaces cơ bản sau cho các Executor:

- Executor: là interface cha của tất cả Executor. Nó xác định chỉ một phương thực excute(Runnable).
- ExecutorService: là một Executor cho phép theo dõi tiến trình của các tác vụ trả về giá trị (Callable) thông qua đối tượng Future, và quản lý việc kết thúc các luồng. Các phương thức chính của nó bao gồm submit() và shutdown().
- ScheduledExecutorService: là một ExecutorService có thể lên lịch cho các tác vụ để thực thi sau một khoảng thời gian nhất định, hoặc để thực hiện định kỳ. Các phương thức chính của nó là schedule(), scheduleAtFixedRate() and scheduleWithFixedDelay().

Chúng có thể tạo một Executor bằng cách sử dụng một trong các phương thức được cung cấp bởi lớp tiện ích Executors như sau:

- newSingleThreadExecutor(): trong ThreadPool chỉ có 1 Thread và các task (nhiệm vụ) sẽ được xử lý một cách tuần tự.
- newCachedThreadPool(): trong ThreadPool sẽ có nhiều Thread và các nhiệm vụ sẽ được xử lý một cách song song. Các Thread cũ sau khi xử lý xong sẽ được sử dụng lại cho nhiệm vụ mới. Mặc định nếu một Thread không được sử dụng trong vòng 60 giây thì Thread đó sẽ bị tắt.
- newFixedThreadPool(int n): trong ThreadPool sẽ được cố định các Thread. Nếu một nhiệm vụ mới được đưa vào mà các Thread đều đang “bận rộn” thì nhiệm vụ đó sẽ được gửi vào Blocking Queue và sau đó nếu có một Thread đã thực thi xong nhiệm vụ của nó thì nhiệm vụ đang ở trong Queue đó sẽ được push ra khỏi Queue và được Thread đó xử lý tiếp.
- newScheduledThreadPool(int corePoolSize): tương tự như - newCachedThreadPool() nhưng sẽ có thời gian delay giữa các Thread.
newSingleThreadScheduledExecutor(): tương tự như newSingleThreadExecutor() nhưng sẽ có khoảng thời gian delay giữa các Thread.


# 3. Networking
## 3.1 Connection pooling
### Connection pool
một connection là một bộ đệm duy trì các kết nối tới cơ sở dữ liệu. Các kết nối tới cơ sở dữ liệu sau khi sử dụng sẽ không đóng lại ngay mà sẽ được dùng lại khi được yêu cầu trong tương lai.

Cơ chế hoạt động của nó như sau: khi một connection (một kết nối) được tạo, nó sẽ được đưa vào pool và sử dụng lại cho các yêu cầu kết nối tiếp theo và chỉ bị đóng khi hết thời gian timeout.

Ví dụ, max pool size = 10 (số lượng tối đa connection trong pool là 10).

Bây giờ user kết nối tới database (truy vấn database), hệ thống sẽ kiểm tra trong connection pool có kết nối nào đang rảnh không?

- Trường hợp chưa có kết nối nào trong connection pool hoặc tất cả các kết nối đều bận (đang được sử dụng bởi user khác) và số lượng connection trong connection < 10 thì sẽ tạo một connection mới tới database để kết nối tới database đồng thời kết nối đó sẽ được đưa vào connection pool.
- Trường hợp tất cả các kết nối đang bận và số lượng connection trong connection pool = 10 thì người dùng phải đợi cho các user dùng xong để được dùng.

Sau khi một kết nối được tạo và sử dụng xong nó sẽ không đóng lại mà sẽ duy trì trong connection pool để dùng lại cho lần sau và chỉ thực sự bị đóng khi hết thời gian timeout (lâu quá không dùng đến nữa)


![](https://stackjava.com/wp-content/uploads/2018/04/connection-pool-logic.png)

### Dùng connection pooling vì
Các bước trong 1 vòng đời kết nối cơ sở dữ liệu:
1. Mở kết nối tới database bằng database driver
2. Mở 1 TCP socket để read/write data
3. Read /  write data
4. Đóng kết nối
5. Đóng socket

Ta thấy được việc kết nối database là rất tốn kém, nên cần giảm thiểu hết mức có thể

Vậy nên đó là lí do connection pooling ra đời. Nó đơn giản chỉ là implement 1 container chứa các database connection, cho phép dùng lại số connection có sẵn, ta có thể tiết kiệm 1 lượng lớn chi phí cho performing của việc kết nối database.

link: https://www.baeldung.com/java-connection-pooling

### Các thông tin cấu hình Connection Pool
Connection Pool thường được cấu hình ở file config của ứng dụng server. Tùy thuộc vào ứng dụng mà bạn có thể có nhiều hơn một connection pool, mỗi connection pool được sử dụng bởi các thành phần khác nhau của ứng dụng. Dưới đây là một số tham số khi cấu hình connection pool:

- Connection pool name: Sử dụng để xác định và đăng ký với datasource.
- Initial number of connections: Số connection được tạo vào đưa vào connection pool khi ứng dụng được start.
- Maximum and minimum pool size: Số connection tối đa và tối thiểu trong connection pool
- JDBC URL: chỉ rõ vị trí database, database name, port, hostname
- JDBC driver class name.

link: https://stackjava.com/faq/connection-pool-la-gi-khai-niem-connection-pool-trong-database.html

## 3.2 Caching, caching với guava, redis
### Caching
Caching là quá trình lưu data trong cache

### Caching với Guava
#### Guava cache
Guava hỗ trợ in – memory cache, lưu trữ dữ liệu dưới dạng cặp dữ liệu key – value . Guava chủ yếu hỗ trợ 2 method cache chính đó là LoadingCache và Cache.

- LoadingCache: tự động load dữ liệu vào cache nếu trong cache chưa có dữ liệu
- Cache: chúng ta phải thực hiện các thao tác kiểm tra sự tồn tại của key trước khi put value vào cache, hay get cache

Guava hỗ trợ thread – seft trong thao tác dữ liệu với cache. Tuy nhiên, Guava không hỗ trợ distributed cache.

#### Guava - Caching Utilities
- Khai báo interface

```
@Beta
@GwtCompatible
public interface LoadingCache<K,V>
   extends Cache<K,V>, Function<K,V>
``` 

- Interface method
  - ConcurrentMap<K,V> asMap(): trả về tập các entry được lưu trong cache như một thread-safe map
  - V get(K key): trả về giá trị ứng với key tương ứng trong cache, first loading value đó nếu cần
  - ImmutableMap<K,V> getAll(Iterable<? extends K> keys): trả về 1 map các key - value, tạo hoặc lấy các value đó nếu cần
  -  V getUnchecked(K key): giống với get nhưng ko throw checked execption
  -  void refresh(K key): load 1 value mới cho key, có thể bất đồng bộ

- Ví dụ: 

GuavaTester.java
```
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaTester {
   public static void main(String args[]) {
   
      //create a cache for employees based on their employee id
      LoadingCache<String, Employee> employeeCache = 
         CacheBuilder.newBuilder()
         .maximumSize(100)                             // maximum 100 records can be cached
         .expireAfterAccess(30, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
         .build(new CacheLoader<String, Employee>() {  // build the cacheloader
            
            @Override
            public Employee load(String empId) throws Exception {
               //make the expensive call
               return getFromDatabase(empId);
            } 
         });

      try {			
         //on first invocation, cache will be populated with corresponding
         //employee record
         System.out.println("Invocation #1");
         System.out.println(employeeCache.get("100"));
         System.out.println(employeeCache.get("103"));
         System.out.println(employeeCache.get("110"));
         
         //second invocation, data will be returned from cache
         System.out.println("Invocation #2");
         System.out.println(employeeCache.get("100"));
         System.out.println(employeeCache.get("103"));
         System.out.println(employeeCache.get("110"));

      } catch (ExecutionException e) {
         e.printStackTrace();
      }
   }

   private static Employee getFromDatabase(String empId) {
   
      Employee e1 = new Employee("Mahesh", "Finance", "100");
      Employee e2 = new Employee("Rohan", "IT", "103");
      Employee e3 = new Employee("Sohan", "Admin", "110");

      Map<String, Employee> database = new HashMap<String, Employee>();
      
      database.put("100", e1);
      database.put("103", e2);
      database.put("110", e3);
      
      System.out.println("Database hit for" + empId);
      
      return database.get(empId);		
   }
}

class Employee {
   String name;
   String dept;
   String emplD;

   public Employee(String name, String dept, String empID) {
      this.name = name;
      this.dept = dept;
      this.emplD = empID;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getDept() {
      return dept;
   }
   
   public void setDept(String dept) {
      this.dept = dept;
   }
   
   public String getEmplD() {
      return emplD;
   }
   
   public void setEmplD(String emplD) {
      this.emplD = emplD;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(Employee.class)
      .add("Name", name)
      .add("Department", dept)
      .add("Emp Id", emplD).toString();
   }	
}
```

Output:
```
Invocation #1
Database hit for100
Employee{Name=Mahesh, Department=Finance, Emp Id=100}
Database hit for103
Employee{Name=Rohan, Department=IT, Emp Id=103}
Database hit for110
Employee{Name=Sohan, Department=Admin, Emp Id=110}
Invocation #2
Employee{Name=Mahesh, Department=Finance, Emp Id=100}
Employee{Name=Rohan, Department=IT, Emp Id=103}
Employee{Name=Sohan, Department=Admin, Emp Id=110}
```

link: https://www.tutorialspoint.com/guava/guava_quick_guide.htm

### Caching với redis

## 3.3 Khái niệm cơ bản về protocol trong networking.
### HTTP
HTTP ( HyperText Transfer Protocol): Giao thức truyền tải siêu văn bản là một trong năm giao thức chuẩn của mạng Internet, được dùng để liên hệ thông tin giữa Máy cung cấp dịch vụ (Web server) và Máy sử dụng dịch vụ (Web client) trong mô hình Client/Server dùng cho World Wide Web-WWW, HTTP là một giao thức thuộc tầng ứng dụng, nằm trên cặp giao thức tầng giao vận & tầng mạng là TCP/IP.

### Websocket
Websocket: là một giao thức giúp truyền dữ liệu hai chiều giữa server-client qua một kết nối TCP duy nhất. Hơn nữa, webSocket là một giao thức được thiết kế để truyền dữ liệu bằng cách sử dụng cổng 80 và cổng 443 và nó là một phần của HTML5. Vì vậy, webSockets có thể hoạt động trên các cổng web tiêu chuẩn, nên không có rắc rối về việc mở cổng cho các ứng dụng, lo lắng về việc bị chặn bởi các tường lửa hay proxy server

Không giống với giao thức HTTP là cần client chủ động gửi yêu cầu cho server, client sẽ chời đợi để nhận được dữ liệu từ máy chủ. Hay nói cách khác với giao thức Websocket thì server có thể chủ động gửi thông tin đến client mà không cần phải có yêu cầu từ client.

Tất cả dữ liệu giao tiếp giữa client-server sẽ được gửi trực tiếp qua một kết nối cố định làm cho thông tin được gửi đi nhanh chóng và liên tục khi cần thiết. WebSocket làm giảm độ trễ bởi vì một khi kết nối WebSocket được thành lập, server không cần phải chờ đợi cho một yêu cầu từ client.

Tương tự như vậy, client có thể gửi tin nhắn đến server bất cứ lúc nào. Yêu cầu duy nhất này giúp làm giảm đáng kể độ trễ, mà sẽ gửi một yêu cầu trong khoảng thời gian, cho dù thông điệp có sẵn.

Để có thể sử dụng được Websocket thì không phải chỉ cần trình duyệt hỗ trợ mà còn phải có server Websocket, server Websocket có thể được tạo ra bằng bất kỳ ngôn ngữ server-side nào, nhưng Node.js được sử dụng rộng rãi hơn cả vì nó viết bằng Javascript nên mang nhiều ưu điểm so với các ngôn ngữ server-side truyền thống khác.

#### Hoạt động:
![](https://images.viblo.asia/34e0ae36-e850-49f4-8d61-8aa1ab312d3e.jpg)

Giao thức có hai phần: Bắt tay và truyền dữ liệu Ban đầu client sẽ gửi yêu cầu khởi tạo kết nối websocket đến server, server kiểm tra và gửi trả kết quả chấp nhận kết nối, sau đó kết nối được tạo và quá trình gửi dữ liệu có thể được thực hiện, dữ liệu chính là các Ws frame

Link: https://viblo.asia/p/co-ban-ve-giao-thuc-websocket-va-thu-vien-socketio-63vKjmmM52R


### gRPC
- Remote Procedure Call  (RPC) – Thủ tục gọi hàm từ xa là một kỹ thuật tiến bộ cho quá trình kết nối từ Client đến Server để sử dụng các ứng dụng và dịch vụ. RPC cho phép client có thể kết nối tới 1 dịch vụ sử dụng dynamic port nằm ở một máy tính khác. Trong hệ thống mạng máy tính hiện nay có rất nhiều dịch vụ và ứng dụng sử dụng cơ chế kết nối RPC, ví dụ quá trình đồng bộ của các Domain Controller trong hệ thống  Active Directory, hoặc khi MS Outlook kết nối tới MS Exchange Server… 

RPC có thể được xem là một giao thức request & respone thông thường. Tuy nhiên, nó được dùng cho việc giao tiếp giữa các server với nhau (server-server) nhiều hơn là client-server. Việc này có ý nghĩa rất quan trọng, vì trong các hệ thống phân tán – distributed system – application code ở nhiều server hơn là một server. Ví dụ thường thấy nhất chính là kiến trúc Microservice.

Điều này nghĩa là, một request từ phía client có thể cần nhiều service, chạy trên nhiều server, tổng hợp thông tin rồi sau đó mới response cho client.

Sự liên lạc giữa các service trên các server lúc này sẽ là vấn đề – mà trước đó tất cả service chạy trên cùng một server thì khoẻ re, vì local call nên chẳng ngần ngại gì cả. Chính xác là khi đó, một server muốn “nói chuyện” với server khác sẽ cần phải encode data (JSON, XML, …), phía nhận cũng phải làm công việc ngược lại, là decode data mới hiểu thằng kia nói gì với mình, rồi lại phải encode tiếp. Việc này tiêu tốn khá nhiều tài nguyên xử lý (CPU) mà lẽ ra chỉ cần làm ở bước đầu và cuối (đầu nhận và trả về cuối cùng).

Tối ưu cho việc “giao tiếp” giữa các server là lý do gRPC ra đời.

Để giải bài toán trên, gRPC sử dụng binary để truyền đi, thay vì phải encode chúng thành các ngôn ngữ trung gian như JSON, XML, …

- gRPC: là một modern open source high performance RPC framework, có thể chạy với bất kì môi trường nào. 

![](https://gopherhome.files.wordpress.com/2018/12/gopher-grpc-at-a-glance.png)

Việc này rõ ràng làm tăng tốc độ giao tiếp giữa các server lên rất nhiều, đồng thời giảm overhead cho CPUs. Google cũng “tiện tay” làm luôn cả protobuf (protocol buffers), đây là ngôn ngữ mà gRPC dùng như một default serialization format. Implement phần này thật sự phải là tay to lắm nên Google xử dụng protobuf như một script trung gian để generate phần “hard core” cho các lập trình viên ở các ngôn ngữ phổ biến như: C++, C#, Go, Java, Python, NodeJS….

Thứ giúp gRPC giao tiếp binary ngon vậy chính là HTTP/2, đây vốn là giao thức có rất nhiều cải tiến so với HTTP/1.1. Bản thân HTTP/2 cũng được coi như là sự thay thế cho SPDY – giao thức mà cũng chính Google phát triển – open source vào năm 2012 và ngừng hỗ trợ vào 2015 (HTTP/2 có implement và thay thế rồi).

link: 
https://gopher.vn/2018/08/11/grpc-la-gi-co-nen-dung-khong/

https://grpc.io/docs/guides/

## 3.4 SSL / TLS
SSL là chữ viết tắt của Secure Sockets Layer (Lớp socket bảo mật). Một loại bảo mật giúp mã hóa liên lạc giữa website và trình duyệt. Công nghệ này đang lỗi thời và được thay thế hoàn toàn bởi TLS.

TLS là chữ viết tắt của Transport Layer Security, nó cũng giúp bảo mật thông tin truyền giống như SSL. Nhưng vì SSL không còn được phát triển nữa, nên TLS mới là thuật ngữ đúng nên dùng.

### Kiểm tra SSL/TLS
Khi chúng ta trao đổi thông tin quan trọng thì việc kiểm tra xem site đó có đối ứng SSL/TLS hay không là việc cần thiết. Ở trang web được cài SSL/TLS, URL [http://] hiển thị ở thanh address của trình duyệt được gắn thêm [s] thể hiện secure thành [https://] .

### SSL/TLS server certificate

Chứng chỉ SSL/TLS hoạt động bằng cách tích hợp key mã hóa vào thông tin định danh công ty. Nó sẽ giúp công ty mã hóa mọi thông tin được truyền mà không bị ảnh hưởng hoặc chỉnh sửa bởi các bên thứ 3.

![](https://www.hostinger.vn/huong-dan/wp-content/uploads/sites/10/2018/12/thiet-lap-ket-noi-an-toan-voi-server.png)

SSL/TLS hoạt động bằng cách sử dụng public và private key, đồng thời các khóa duy nhất của mỗi phiên giao dịch. Mỗi khi khách truy cập điền vào thanh địa chỉ SSL thông tin web browser hoặc chuyển hướng tới trang web được bảo mật, trình duyệt và web server đã thiết lập kết nối.

Trong phiên kết nối ban đầu, public và private key được dùng để tạo session key, vốn được dùng để mã hóa và giải mã dữ liệu được truyền đưa. Session key sẽ được sử dụng trong một khoảng thời gian nhất định và chỉ có thể dùng cho phiên giao dịch này.

Nếu có khóa màu xanh ngay đầu địa chỉ web thì tức là website đã thiết lập đúng SSL/TLS. Bạn có thể nhấn vào nút màu xanh đó để xem ai là người giữ chứng chỉ này.

### Khi nào và vì sao SSL/TLS là một điều BẮT BUỘC?
Có 3 lý do chính mà một website hiện đại buộc phải có SSL/TLS:
- Khi bạn cần chứng thực: Bất kỳ server nào cũng có thể giả dạng là server của bạn, đánh cắp thông tin được truyền đưa. SSL/TLS cho phép bạn xác thực danh tính của server để người dùng biết chắc họ đang giao tiếp với đúng người mà họ muốn giao tiếp.
- Để tăng độ tin cậy: Nếu bạn đang chạy một site ecommerce mà bạn cần người dùng đưa các thông tin quan trọng đối với họ, thì ít nhất họ cần biết thông tin họ gửi phải được bảo mật trước thì họ mới tin bạn. Sử dụng SSL/TLS là cách dễ nhất để cho khách truy cập tin tưởng, hơn bất kỳ lời cam kết nào được đưa ra từ phía bạn.
- Khi bạn cần tuân thủ chuẩn của ngành của bạn: Trong một số ngành nhất định, như ngành tài chính, bạn sẽ bắt buộc áp dụng một số chuẩn bảo mật. Bạn cũng có thể tham khảo chỉ dẫn về Payment Card Industry (PCI) mà bạn cần tuân thủ nếu bạn muốn nhận thanh toán qua thẻ tín dụng trên website của bạn. Một trong số các yêu cầu thiết yếu là việc sử dụng chứng chỉ SSL/TLS.

Link: 
https://www.hostinger.vn/huong-dan/https-tls-ssl-la-gi/

https://viblo.asia/p/ssltls-la-gi-Do754wnBlM6

## 3.5 RESTful API 
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

## 4 Benchmark
### 4.1 Benchmark
Benchmark là đánh giá, cũng tương tự gần như review (đánh giá), nhưng nó không phải là đánh giá toàn bộ mà là chỉ đánh giá một phần của thiết bị phần cứng hoặc phần mềm cần đánh giá, một phần của review. Bản thân review có thể có hoặc không có phần benchmark.

Điểm benchmark là giá trị cho biết tốc độ của một thiết bị có nhanh hay không, có thực sự nổi bật hay không.

Các dạng Benchmark
Chúng ta có thể có 2 dạng Benchmark theo tiêu chí mà người dùng muốn sử dụng để đo tốc độ.
- Tiêu chí về phần cứng ta có đo điểm Benchmark của CPU, GPU, RAM, HDD.
- Tiêu chí về phần mềm ta có đo điểm Benchmark về game, về thiết kế, về công nghệ, vv...

### Các tool hỗ trợ benchmark hệ thống dành cho Java
#### JMeter
JMeter là công cụ mã nguồn mở rất phổ biến trong việc kiểm thử khả năng chịu tải cho website. Apache Jmeter là một ứng dụng của Java được thiết kế đặc biệt cho khả năng đo hiệu suất.

![](https://cuongquach.com/resources/images/2017/11/apache-jmeter.jpg)

Ưu điểm:
- Có khả năng kiểm thử với một loạt các công nghệ như Java Objects, Web HTTP/HTTPS, SOAP và Rest Services, FTP, Database với JDBC.
- Một IDE (Integrated Development Environment) tốt để bạn có thể sử dụng để ghi lại, xây dựng và gỡ lỗi các bài kiểm tra hiệu năng của bạn.
- Từ phiên bản JMeter 3.1 thì Groovy là ngôn ngữ lập trình mặc định.
- Một trong những công cụ kiểm tra hiệu suất phổ biến nhất.
- Nguồn mở, miễn phí
- Giao diện đơn giản, trực quan dễ sử dụng
- JMeter lưu các kịch bản kiểm thử của nó dưới dạng các file XML, do đó ta có thể tự tạo các kịch bản kiểm thử của mình bằng một trình soạn thảo bất kỳ và load nó lên
- Đa luồng, giúp xử lý tạo nhiều request cùng một khoảng thời gian, xử lý các dữ liệu thu được một cách hiệu quả

Nhược điểm:
- Gặp khó khăn khi mở rộng quy mô xử lý những bài test phân phối lớn. Đặc biệt nếu bạn thiết lập một cụm máy thì bạn phải cấu hình để chúng có thể trao đổi với nhau.
- Gặp một loạt các vấn đề dàn xếp khi thực hiện các bài kiểm tra lớn.

**Các thành phần cơ bản của jmeter**
JMeter cung cấp tất cả những thành phần cơ bản để phục vụ cho việc thiết kế kế hoạch, thực thi và giám sát kết quả trong suốt quá trình test. Những thành phần cơ bản của JMeter là:

- Thread Group: Một Thread Group đại diện cho một nhóm người dùng, và nó chứa tất cả những yếu tố khác.Mỗi Thread Group sẽ mô phỏng những người dùng để thực hiện một trường hợp thử nghiệm cụ thể. Thread Group cho phép tester thực hiện những tùy chỉnh về:
  - Số lượng Thread: Mỗi Thread đại diện cho một người dùng ảo, JMeter cho phép thay đổi số lượng người dùng không hạn chế để thực hiện các thử nghiệm.
  - Ram-Up Period: Thời gian để bắt đầu tất cả những Thread.
  - Loop Count: Số lần lặp lại những yêu cầu của người dùng. Ngoài ra còn có những tùy chọn khác như việc chạy các Thread vào lịch biểu định sẵn, xác định hành động sẽ thực hiện khi xảy ra lỗi…


- Controller ( Sampler và Logic Cotroller ): JMeter cung cấp hai dạng Controller: Sampler và Logic Controller, trong đó: - Sampler ( lấy mẫu ): Cho phép JMeter gửi những yêu cầu cụ thể đến một máy chủ. - Logic Controller: Tùy biến việc khi nào thì gửi yêu cầu. Các thành phần Controller được tạo ra để định nghĩa kịch bản thực tế của người dùng bằng việc ghi lại những yêu cầu cụ thể của người dùng tới một server xác định.Cụ thể đối với kiểm thử website thì đó là những HTTP Request được gửi đến server của người dùng.

- Listener: Công cụ Listener mà JMeter cung cấp cho phép xem những kết quả thu được từ việc chạy thử nghiệm dưới các dạng khác nhau như: đồ thị, bảng biểu, cây.. Các listeners sẽ cung cấp một cách trực quan nhất những dữ liệu thu thập được từ việc thực thi các Test case. Tester cũng sẽ có thể tùy chỉnh những thông tin mà Listener trả về một cách dễ dàng bởi các tính năng trong giao diện cụ thể của từng Listener. Có rất nhiều dạng Listener được JMeter cung cấp, có thể kể đến một số Listener thường được sử dụng để cung cấp như:
  - Graph Full Results: Cung cấp tất cả những kết quả trả về dưới dạng đồ thị : Lỗi, thời gian phản hồi, lưu lượng …
  - View Results in Table: Hiển thị những thông số về thời gian phản hồi của từng yêu cầu, những yêu cầu thực hiện thành công và thất bại… dưới dạng bảng.trong suốt quá trình thực thi thử nghiệm.
  - Summary Report : Cung cấp những thống kê tổng thể.
  - Timer: Timer là một phần rất quan trọng khi xây dựng một Test Plan, nó cho phép cài đặt khoảng thời gian giữa 2 yêu cầu kế tiếp nhau mà người dùng ảo gửi đến máy chủ. Điều này sẽ tạo ra một mô phỏng thực tế nhất so với hoạt động thực tế của người dùng trên website. JMeter cung cấp nhiều Timer với các dạng khác nhau để thiết lập thời gian nghỉ giữa việc thực hiện 2 yêu cầu , như :
    - Constant Timer: xác lập thời gian là một hằng số.
    - Uniform Random Timer: xác lập thời gian nghỉ ở một khoảng xác định.
  - Assertion: là công cụ có tác dụng xác nhận những dữ liệu mà Website trả về có đúng với yêu cầu đặt ra hay không.



#### Locust
![](https://cuongquach.com/resources/images/2017/11/locust-logo.jpg)

Đây là công cụ kiểm tra tải đơn giản, dễ sử dụng và dễ phân phối và tất nhiên nó được sử dụng để kiểm thử tải trang web rồi. Locust cũng có thể giúp bạn tìm ra bao nhiêu người dùng truy cập đồng thời trên web mà hệ thống có thể xử lý được. Bạn cũng có thể xác định hành vi mà bạn muốn cho từng trường hợp kiểm thử. Ngoài ra Locust còn cung cấp giao diện web để bạn theo dõi quá trình benchmark theo thời gian thực.

Ưu điểm:
- Khả năng tạo kịch bản cho bài test bằng python.
- Dễ dàng quy mô số người truy cập mà bạn cần.
- Giao diện trên nền web rất chất, đẹp.
- Có khả năng mở rộng.
- Hiệu quả trong việc test các API.
- Trên Github hiện tại nó đang được 6293 sao đấy nhé.

Nhược điểm:
- Hiện tại chưa thấy nhiều báo cáo về nhược điểm của Locust cả, tuy nhiên bản thân mình thấy thì Locust chỉ sử dụng được khi OS cài python 2.7, 3.3, 3.4, 3.5, và 3.6.
- Mình lab thử trên CentOS 6 thì phải cài thêm python 2.7 vì trên CentOS 6 chỉ mặc định cài python 2.6 mà thôi, hơi bất tiện xíu nhưng không sao.

Link: https://cuongquach.com/top-10-cong-cu-ma-nguon-mo-kiem-tra-tai-website-phan-1.html

https://viblo.asia/p/mojito-huong-dan-su-dung-jmeter-de-test-performance-cho-he-thong-website-bJzKmLGO59N

## 5. JVM
## 5.1 JVM
JVM (Java Virtual Machine) là 1 máy ảo java - trình thông dịch của Java. Nó cung cấp môi trườngink để code java có thể được thực tinkhi, chương trình Java khi biên dinkịch sẽ tạo ra các file *.class chứaink byte code , Các file *.class này inksẽ được JVM thực hiện chuyển byte coinkde thành mã máy tương ứng với tinkừng hệ điều hành và phần cứng khác inknhau thực thi. Các bạn có thể thainkm khảo cơ chế thực hiện 1 chương trình Java trong sơ đồ dưới đây :

![](https://viblo.asia/uploads/4081ff8d-dbbc-46fe-8d02-42b5a62cc2c0.png)

JVM là:
- A specification: Nơi làm việc của JVM được quy định.JVM cung cấp các thuật toán đọc lập được cung cấp bởi Sun và nhiều công ty phát triển phần mềm khác.
- An implementation: 1 implemention được biết đến chính là JRE. JRE là một ứng dụng nền giúp thực thi các file mã máy đã được biên dịch từ file nguồn *.java. Các thành phần của JRE chỉ bao gồm các gói Java và thư viện thực thi ứng dụng (runtime libraries) nên JRE không có khả năng biên dịch file Java thành mã máy chỉ có khả năng thực thi các file byte code sau khi đã được JDK biên dịch.
- Runtime Instance : Bất cứ khi nào bạn viết lệnh java trên dấu nhắc lệnh để chạy các lớp java, và instance của JVM được tạo ra.

Hoạt động:
JVM thực hiện các công việc sau:
- Loads code - tải mã lệnh
- Verifies code - Kiểm tra mã lệnh
- Executes code - thực thi mã lệnh
- Provides runtime environment - công cấp môi trường biên dịch mã

### Kiến trúc bên trong JVM
Nó chứa classloader, khu vực bộ nhớ, bộ máy thực thi,...

![](https://viblo.asia/uploads/03cc91ac-abf2-40db-896e-035692c253ac.png)

1. Classloader:
Classloader là một hệ thống phụ của JVM được sử dụng để tải các file class.

2. Class (Method) Area:
Cửa hàng lớp (Method): Vùng chứa các class và cung cấp các class nền tảng cho phép mở rộng hoặc ghi đè lên nó

3. Heap
Đây là khu vực dữ liệu thời gian chạy trong đó các đối tượng được phân bổ.

4. Stack
Java stack store frames. Nó lưu trữ các biến địa phương và kết quả từng phần, và đóng một phần trong phương pháp gọi và trở về.

Mỗi thread cung cấp 1 JVM stack riêng, được tạo cùng thời gian với thread.

Một new frame được tạo ra mỗi lần method được gọi. Một frame bị hủy khi method được gọi của nó hoàn thành.

5. Program Counter Register:

PC (đếm chương trình) đăng ký. Nó chứa địa chỉ của các máy ảo Java hướng dẫn hiện đang được thực hiện.

6. Native Method Stack
Nó chứa tất cả các phương pháp có nguồn gốc được sử dụng trong các ứng dụng.


7. Execution Engine:
Nó chứa:
- Một bộ xử lý ảo

- Phiên dịch: Đọc dòng bytecode sau đó thực hiện các hướng dẫn.

- Just-In-Time (JIT) biên dịch: Nó được sử dụng để cải thiện performance.JIT biên dịch các phần của mã byte có chức năng tương tự như cùng một lúc, và do đó làm giảm số lượng thời gian cần thiết cho compilation.Thuật ngữ: trình biên dịch: đề cập đến như một dịch giả từ những hướng dẫn của một máy ảo Java (JVM) cho các tập lệnh của CPU cụ thể.

Link: https://viblo.asia/p/kien-truc-jvm-java-virtual-machine-ogBG29o6MxnL

## 5.2 JRE
Runtime enviroment là một phần mềm được thiết kế để chạy các phần mềm khác. Là runtime enviroment cho Java, JRE chứa các thư viện lớp Java, trình tải lớp Java và Máy ảo Java. Trong hệ thống này:

- Trình tải lớp chịu trách nhiệm tải chính xác các lớp và kết nối chúng với các thư viện lớp Java cốt lõi.
- JVM chịu trách nhiệm đảm bảo các ứng dụng Java có tài nguyên mà chúng cần để chạy và hoạt động tốt trong thiết bị hoặc môi trường đám mây của bạn.
- JRE chủ yếu là một thùng chứa cho các thành phần khác và chịu trách nhiệm điều phối các hoạt động của chúng.

### Runtime Enviroment
Một chương trình phần mềm cần phải thực thi và để thực hiện nó cần một môi trường để chạy. Runtime Enviroment tải các tệp lớp và đảm bảo có quyền truy cập vào bộ nhớ và các tài nguyên hệ thống khác để chạy chúng. Trước đây, hầu hết các phần mềm đều sử dụng hệ điều hành (HĐH) làm Runtime Enviroment. Chương trình chạy bên trong bất kỳ máy tính nào được bật, nhưng dựa vào cài đặt hệ điều hành để truy cập tài nguyên. Tài nguyên trong trường hợp này sẽ là những thứ như bộ nhớ và tệp chương trình và dependencies. Java Runtime Enviroment đã thay đổi tất cả, ít nhất là đối với các chương trình Java.

JRE chứa các thư viện và phần mềm mà các chương trình Java của bạn cần chạy. Ví dụ, trình tải lớp Java là một phần của Java Runtime Environment. Phần mềm quan trọng này tải mã Java được biên dịch vào bộ nhớ và kết nối mã với các thư viện lớp Java thích hợp.

### Cách JRE hoạt động với JVM
Java Virtual Machine là một hệ thống phần mềm đang chạy chịu trách nhiệm thực thi các chương trình Java trực tiếp. JRE là hệ thống trên đĩa lấy mã Java của bạn, kết hợp nó với các thư viện cần thiết và khởi động JVM để thực thi nó.

![](https://images.viblo.asia/b4e3484f-fdde-4063-a947-53527ed7060a.png)

### Bộ nhớ Java và JRE
Bộ nhớ Java bao gồm ba thành phần: heap, stack và metaspace (trước đây được gọi là permgen).

- Metaspace là nơi Java giữ thông tin không thay đổi của chương trình như các định nghĩa lớp.
- Không gian heap (Heap space) là nơi Java giữ nội dung biến(variable content).
- Không gian ngăn xếp (Stack space) là nơi Java lưu trữ thực thi hàm(function execution) và tham chiếu biến(variable references).


## 5.3 JDK 
JDK (Java Development Kit): JRE có thể được sử dụng như một thành phần độc lập để chạy các chương trình Java, nhưng nó cũng là một phần của JDK. JDK yêu cầu JRE vì chạy các chương trình Java là một phần của việc phát triển chúng.

![](https://images.viblo.asia/0fa40581-96db-4353-8b0a-269848407ee5.png)

Các định nghĩa:

- Định nghĩa kỹ thuật: JDK là một triển khai của đặc tả nền tảng Java, bao gồm các trình biên dịch và thư viện lớp.
- Định nghĩa hàng ngày: JDK là gói phần mềm bạn tải xuống để tạo các ứng dụng dựa trên Java.

Link: https://viblo.asia/p/jvm-jdk-jre-co-gi-khac-biet-giua-chung-oOVlYBXV58W

## 6.  Monitoring

## 7.  Useful library
