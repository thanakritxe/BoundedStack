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
        // //ปกติ
        // check("valid title lowercase", MovieLibrary.validate("harry potter"));
        // check("valid title exactly 20 chars", MovieLibrary.validate("abcdefghijklmnopqrst")); 
         // ตัวอักษรตัวใหญ๋||ตัวอักษร
        check("invalid: uppercase letter", !MovieLibrary.validate("Harry Potter"));
         //ตัวเลข
        check("invalid: numbers", !MovieLibrary.validate("minions 2"));
        check("password no thai number ",!MovieLibrary.validate("๑๒๓๔ถช๕๖๗๘๙ ") ); 
         // ตัวอักษรพิเศษ
        check("invalid: special chars", !MovieLibrary.validate("spider-man"));

         // ตัวอักษร<0||ส่งค่าว่าง 
        check("invalid: empty string", !MovieLibrary.validate(""));
        check("invalid: null", !MovieLibrary.validate(null));
         // ตัวอักษรตัวเว้นหลัง||ตัวอักษรเว้นหน้า
        check("invalid with leading space",!MovieLibrary .validate(" Abc12345"));
        check("invalid with trailing space",!MovieLibrary.validate("Abc12345 ")); 

         // ความยาวตัวอักษรอยู้ระหว่าง 1ถึง=20|| ห้ามใส่ค่าว่างหรือตัวอักษร=0||BOUNDARY / LENGTH CHECKS 

        check("name len =1",MovieLibrary.validate("A1"));
        check("name len =20",MovieLibrary.validate("AaBcse1dgfjde46djf23"));
        check("name len >20",!MovieLibrary.validate("AaBcse1dgfjde45646djf23") );

        //ไมมีภาษาไทย
        check("password  no thai langural ",!MovieLibrary.validate("วิดคอมเด้อจ้า ") ); 
        
      // ห้ามมีช่องว่างระหว่างตัวอักษร||NO Contains Space inside
        check("password null ",!MovieLibrary.validate("abc 123 ") ); 

      
      //ห้ามตัวอักษรผสมกับตัวเลข
        check("Password contains thai number", !MovieLibrary.validate("Abc123๔"));
      //ห้ามตัวอักษรไทยผสม
        check("Password contains thai character", !MovieLibrary.validate("Abc123ไทย") );


     

   
    }

    private static void testAdd() {
        System.out.println("\n-- 2. Add Tests --");

        MovieLibrary lib = new MovieLibrary();
        check("add valid title -> true", lib.add("minions"));
        check("add valid title -> size 1", lib.size() == 1);
        check("add duplicate title -> false", !lib.add("minions"));
    }

}