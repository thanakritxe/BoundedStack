
import java.util.*;

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

        System.out.println("\n=== Test Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? ">>> ALL TESTS PASSED <<<" : ">>> SOME TESTS FAILED <<<");

        if (failed > 0) {
            System.exit(1);
        }
    }

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
        check("capacity <= 0 ต้องโยน IllegalArgumentException", invalidCapacity);
    }

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

    private static void testObservers() {
        System.out.println("\n-- 5. Observer Tests --");

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
}
