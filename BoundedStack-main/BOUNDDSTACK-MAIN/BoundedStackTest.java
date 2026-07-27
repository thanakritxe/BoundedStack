import javax.lang.model.element.Name;

public class BoundedStsackTest {

    static int pass = 0, fail = 0;

    static void check(String name, boolean ok) {
        if (ok) { pass++; System.out.println("  [PASS] " + name); }
        else    { fail++; System.out.println("  [FAIL] " + name); }
    }

    public static void main(String[] a) {
        boolean ea = false;
        assert ea = true;
        if (!ea) System.out.println("** คำเตือน: assertion ปิดอยู่ รันด้วย  java -ea TestRunner **");

        System.out.println("== Password Validation ==");

        // ตัวอย่าง assertion ปกติ (ตัวแทนกลุ่ม valid)
        //check("'Abcdef12' valid", PasswordValidator.validate("Abcdef12"));

        // ตัวอย่างแพตเทิร์นทดสอบ "ต้อง throw" ด้วย try/catch
        boolean threw = false;
        try { NameValidator.validate(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("null -> throws IllegalArgumentException", threw);

        // ความยาวชื่อน้อยกว่าหรือเท่ากับ20|ความยาวตัวอักษรชื่อไม่ต่ำกว่า0ตัวอักษร||ิความยาวตัวอักษรอยู่ระหว่างๅ1-20ตัวอักษร  
        check("name len >0",PasswordValidator.validate("Abcdef12sd") ==true );
        check("name len 20>",PasswordValidator.validate("AaBcse1dgfjde46djf23")==true );
        check("name len 0<||>=20",PasswordValidator.validate("AaBcse12102030400000")==true );
       

        // TODO: R3 - ไม่มีตัวพิมพ์ใหญ่ -> false

          check(" name no upper character",PasswordValidator.validate("aaaaabbb123")==false );

        // TODO: R4 - ไม่มีตัวพิมพ์เล็ก -> false
          check("password no lowwer",PasswordValidator.validate("AAAAABBB123")==false );

        // TODO: R5 - ไม่มีตัวเลข -> false
          check("password no number",PasswordValidator.validate("srgthygyy")==false );

        // TODO: R6 - มีช่องว่าง -> false
          check("password no speace",PasswordValidator.validate(" ")==false );

        // TODO: boundary อื่นๆ ที่คุณคิดว่าจำเป็น
          check("password no special character",PasswordValidator.validate("*&%@#!")==false );

          check("password null ",PasswordValidator.validate(" ")==false ); 

          check("password no thai number ",PasswordValidator.validate("๑๒๓๔ถช๕๖๗๘๙ ")==false ); 

          check("password  no thai langural ",PasswordValidator.validate("วิดคอมเด้อจ้า ")==false ); 



        System.out.println("==================================");
        System.out.printf("PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================");
        System.exit(fail == 0 ? 0 : 1);

        
    }
}