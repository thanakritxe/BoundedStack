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
 
public class MovieLibrary {

    public static final int MAX_MOVIES = 50;
    
    private final List<String> movies;
      private final int capacity;


      private void checkRep() {
        assert movies != null : "movies ต้องไม่เป็น null";
        assert movies.size() <= MAX_MOVIES;
        Set<String> seen = new HashSet<>();
        for(String m :movies){
            assert m != null;
            assert !(m=="");
            assert seen.add(m) : "duplicae: " + m;}
        }
        

   public MovieLibrary() {
        this.movies = new ArrayList<>();
        this.capacity = 0;
        
        checkRep();
    }



   


    public static boolean validate(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }






    public boolean add(String movie) {
        if(movie==null || movie=="")throw new IllegalArgumentException();
        if(movies.contains(movie)||movies.size()==MAX_MOVIES) return false;
        movies.add(movie);
        checkRep();
        return true; 
    }






    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }






    public boolean contains(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }


    public Object movies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movies'");
    }

}
