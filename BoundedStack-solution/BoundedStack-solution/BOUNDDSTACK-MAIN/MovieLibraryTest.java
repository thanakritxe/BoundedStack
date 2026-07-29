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
     public static void main(String[] a) {
        boolean ea = false;
        assert ea = true;
        if (!ea) System.out.println("** คำเตือน: assertion ปิดอยู่ รันด้วย  java -ea TestRunner **");

        System.out.println("== Movie Name Validation ==");

        testAdd();

          System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
}
 private static void testAdd() {
        System.out.println("\n-- Add --");

        MovieLibrary m = new MovieLibrary();
        check("add(A) -> returns true", m.add("A"));
        check("add(A) -> size 1", m.size() == 1);
        check("add(A) -> found by contains", m.contains("A"));

        m.add("B");
        m.add("C");
        check("add preserves insertion order",
                m.movies().equals(Arrays.asList("A", "B", "C")));

        // เพลงซ้ำไม่ใช่ error — คืน false เฉย ๆ
        check("add duplicate -> returns false", !m.add("A"));
        check("failed add leaves size unchanged", m.size() == 3);

        // input ที่ผิดเงื่อนไขต้องโยน exception
        boolean threwEmpty = false;
        try {
            m.add("");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("add(empty string) -> throws IllegalArgumentException", threwEmpty);

        boolean threwNull = false;
        try {
            m.add(null);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("add(null) -> throws IllegalArgumentException", threwNull);

        check("failed adds leave MovieLibrary unchanged", m.size() == 3);

        // boundary: เติมจนเต็มพอดีแล้วเติมเพิ่ม
        MovieLibrary full = new MovieLibrary();
        for (int i = 0; i < MovieLibrary.MAX_MOVIES; i++) {
            full.add("song" + i);
        }
        check("can fill up to MAX_SONGS", full.size() == MovieLibrary.MAX_MOVIES);
        check("add when full -> returns false", !full.add("one more"));
        check("full MovieLibrary stays at MAX_SONGS",
                full.size() == MovieLibrary.MAX_MOVIES);
    }
}
