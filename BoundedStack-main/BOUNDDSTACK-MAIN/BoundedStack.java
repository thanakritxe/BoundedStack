import java.util.*;
/**
 * คลาส BoundeStack สำหรับจัดการและเก็บชื่อนิสิต 
 * โดยรับเฉพาะตัวอักษรภาษาอังกฤษพิมพ์เล็ก (a-z) เท่านั้นเเละไม่ให้ใช้ตัวอักษรพิเศษ และมีความยาวไม่เกิน 20 ตัวอักษร
 * name!=null
 * name.lenht()<=20||name.lenght()>0
 * name=ตัวอักษรในภาษาอังกฤษต้องเป็นตัวเล็กเท่านั้น
 *  *
 * @param item ข้อมูลที่ต้องการเพิ่ม (ต้องไม่เป็น null)เเละ ค่าที่รับเข้ามาต้อง>0
 * @throws NullPointerException error เมื่่อถ้าค่าที่รับเข้ามา เป็น null เเละ error เมื่อถ้า ค่าที่รับเข้า<=0
 * @return รับเป็นชื่อนิสิตที่ใช้programนี้
 * 
 */
 
public class BoundedStack {

     private final List<String> elements;
      private final int capacity;



    public BoundedStack(int capacity){
        this.elements=new ArrayList<>();
        this.capacity=capacity;
    }

}
