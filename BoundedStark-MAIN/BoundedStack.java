import java.util.*;
public class MovieLibraryTest {

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
            System.out.println("** คำเตือน: Assertion ปิดอยู่! แนะนำให้รันด้วย java -ea MovieLibraryTest **\n");
        }

        System.out.println("=== Starting MovieLibrary Tests ===\n");

        testValidate();
    
        System.out.println("\n=== Test Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? ">>> ALL TESTS PASSED <<<" : ">>> SOME TESTS FAILED <<<");

        if (failed > 0) {
            System.exit(1);
        }
    }
    private static void testValidate() {
        System.out.println("-- 1. Validate Title Tests --");

        check("valid title lowercase", MovieLibrary.validate("harry potter"));
        check("valid title exactly 20 chars", MovieLibrary.validate("abcdefghijklmnopqrst")); 
        check("invalid: uppercase letter", !MovieLibrary.validate("Harry Potter"));
        check("invalid: numbers", !MovieLibrary.validate("minions 2"));
        check("invalid: special chars", !MovieLibrary.validate("spider-man"));
        check("invalid: > 20 chars", !MovieLibrary.validate("abcdefghijklmnopqrstu")); 
        check("invalid: empty string", !MovieLibrary.validate(""));
        check("invalid: null", !MovieLibrary.validate(null));
        check("Password with leading space",MovieLibrary .validate(" Abc12345"));
        check("Password with trailing space", MovieLibrary.validate("Abc12345 ")); 
    }

}