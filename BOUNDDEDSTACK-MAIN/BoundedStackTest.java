
public class BoundedStackTest {

    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }

    public static void main(String[] args) {
        boolean ea = false;
        assert ea = true;
        if (!ea) {
            System.out.println("** เตือน: รันด้วย java -ea BoundedStackTest เพื่อเปิดระบบ Assert **\n");
        }

        System.out.println("=== BoundedStack Tests ===\n");

        testCreator();
        testPush();
        testPop();
        testObservers();
        testProducer();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? ">>> ALL TESTS PASSED <<<" : ">>> SOME TESTS FAILED <<<");

        if (failed > 0) {
            System.exit(1);
        }
    }
    /**
 * ทดสอบ Creator: BoundedStack(int capacity)
 *   - capacity ปกติ (> 1)         -> ตรวจสอบว่า size() เริ่มต้นเป็น 0
 *   - capacity ขั้นต่ำสุด (= 1)    -> boundary case ของ @requires capacity > 0
 *   - capacity ไม่ถูกต้อง (<= 0)   -> ตรวจว่าthrows IllegalArgumentException ตามสเปค
 */
    private static void testCreator() {
        System.out.println("-- 1. Creator --");
        BoundedStack<String> stack = new BoundedStack<>(5);
        check("size = 0", stack.size() == 0);
        BoundedStack<String> minStack = new BoundedStack<>(1);
        check("create stack size 1 ", minStack.size() == 0);
        boolean invalidCapacity = false;
        try {
            new BoundedStack<String>(0);
        } catch (IllegalArgumentException e) {
            invalidCapacity = true;
        }
        check("capacity <= 0 throw IllegalArgumentException", invalidCapacity);
    }

    /**
 * ทดสอบ Mutator: push(E item)
 *   - push ลงสแต็กที่ยังไม่เต็ม           -> size เพิ่มขึ้นถูกต้อง
 *   - push จนสแต็กเต็มพอดี (size == capacity) -> isFull() จะต้องเป็น true
 *   - push(null)                          -> ต้องโยนthrows IllegalArgumentException
 *   - push เมื่อ isFull() == true (overflow) -> ต้องโยนthrows IllegalStateException

*/
    private static void testPush() {
        System.out.println("\n-- 2. Push Tests --");
        BoundedStack<String> stack = new BoundedStack<>(2);
        stack.push("minions");
        check("push top size = 1", stack.size() == 1);
        stack.push("minions"); 
        check("push copy size = 2", stack.size() == 2);
        check("stack is Full = true", stack.isFull());
        boolean nullPushed = false;
        try {
            stack.push(null);
        } catch (IllegalArgumentException e) {
            nullPushed = true;
        }
        check("push(null) throw Exception", nullPushed);

        boolean overflow = false;
        try {
            stack.push("extra");
        } catch (IllegalStateException e) {
            overflow = true;
        }
        check("push overflow capacity --> Stack Overflow Exception", overflow);
    }

    /**
 * ทดสอบ Mutator: pop()
 
 *   - pop จากสแต็กที่มีมากกว่า 1 element -> ต้องได้ตัวบนสุด  และ size ทำการลดลง
 *   - pop จนเหลือ element สุดท้าย        -> ต้องได้ค่าตัวแรกที่ push เข้าไป
 *   - pop จนสแต็กว่าง (isEmpty() == true) -> ต้องโยน throws IllegalStateException

 */

    private static void testPop() {
        System.out.println("\n-- 3. Pop Tests --");
        BoundedStack<String> stack = new BoundedStack<>(3);
        stack.push("matrix");
        stack.push("alien");
        check("pop top", stack.pop().equals("alien"));
        check("pop size = 1", stack.size() == 1);
        check("pop last", stack.pop().equals("matrix"));
        check("pop null", stack.isEmpty());
        boolean underflow = false;
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            underflow = true;
        }
        check("pop from null --> Stack Underflow Exception", underflow);
    }


    /**
 * ทดสอบ Observers: size(), isEmpty(), isFull(), top()
 
 *   - สแต็กว่างเปล่าตั้งแต่สร้าง       -> isEmpty() == true
 *   - สแต็กมีสมาชิกแต่ไม่เต็ม          -> isFull() == false, top() คืนค่าตัวบนสุด
 *   - เรียก top() ซ้ำโดยไม่ pop        -> ต้องไม่เปลี่ยนแปลง size (เป็น observer จริง)
 *   - เรียก top() บนสแต็กว่าง          -> ต้องโยน IllegalStateException
 */
    private static void testObservers() {
        System.out.println("\n-- 4. Observer Tests --");
        BoundedStack<String> stack = new BoundedStack<>(3);
        check("new stack isEmpty", stack.isEmpty());
        stack.push("alien");
        stack.push("pokemon");
        check("size = 2", stack.size() == 2);
        check("see the top value ", stack.top().equals("pokemon"));
        check("top --> size = 2 ", stack.size() == 2);
        check("not isFull = false", !stack.isFull());
        BoundedStack<String> emptyStack = new BoundedStack<>(2);
        boolean emptyTop = false;
        try {
            emptyStack.top();
        } catch (IllegalStateException e) {
            emptyTop = true;
        }
        check("top stack null throw Exception", emptyTop);
    }
/**
 * ทดสอบ Producer: copy()
 *

 *   - copy() ให้ state เริ่มต้นเหมือนต้นฉบับ (size, top เท่ากัน)
 *   - แก้ไข copy (push เพิ่ม) แล้วต้นฉบับต้องไม่เปลี่ยน -> ยืนยัน independence
 *
 */
    private static void testProducer() {
        System.out.println("\n-- 5. Producer Tests --");
        BoundedStack<String> original = new BoundedStack<>(5);
        original.push("alien");
        original.push("pokemon");
        BoundedStack<String> copy = original.copy();
        check("copy = original", copy.size() == original.size());
        check("copy top = original", copy.top().equals(original.top()));
        copy.push("matrix");
        check("edit copy --> original not change size", original.size() == 2);
        check("copy = 3", copy.size() == 3);
    }
}
