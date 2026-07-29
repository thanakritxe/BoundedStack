import java.util.*;


/**
 * ADT MovieLibrary สำหรับเก็บและจัดการชื่อภาพยนตร์แบบจำกัดจำนวน
 */
public class MovieLibrary {

    public static final int MAX_MOVIES = 50;


//  * คลาส BoundeStack สำหรับจัดการและเก็บชื่อภาพยนตร์
//  * โดยรับเฉพาะตัวอักษรภาษาอังกฤษพิมพ์เล็ก (a-z) เท่านั้นเเละไม่ให้ใช้ตัวอักษรพิเศษ และมีความยาวไม่เกิน 20 ตัวอักษร
//  * movie!=null
//  * movie.lenht()<=20||movie.lenght()>0
//  * movie=ตัวอักษรในภาษาอังกฤษต้องเป็นตัวเล็กเท่านั้น
    
private final List<String> movies;

    public static boolean validate(String movie) {
        if (movie == null) return false;
        String trimmed = movie.trim();
        if (trimmed.isEmpty() || trimmed.length() > 20) return false;

        for (char c : movie.toCharArray()) {
            if (!Character.isLowerCase(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    private void checkRep() {
        assert movies != null : "movies must not be null";
        assert movies.size() <= MAX_MOVIES : "exceeded max capacity";

        Set<String> seen = new HashSet<>();
        for (String m : movies) {
            assert m != null : "element must not be null";
            assert validate(m) : "invalid movie title format: " + m;
            assert seen.add(m) : "duplicate element found: " + m;
        }
    }

    public MovieLibrary() {
        this.movies = new ArrayList<>();
        checkRep();
    }

    public MovieLibrary(List<String> initial) {
        if (initial == null) {
            throw new IllegalArgumentException("initial list cannot be null");
        }
        this.movies = new ArrayList<>();
        for (String m : initial) {
            if (!validate(m)) {
                throw new IllegalArgumentException("invalid title: " + m);
            }
            if (this.movies.contains(m)) {
                throw new IllegalArgumentException("duplicate title: " + m);
            }
            this.movies.add(m);
        }
        if (this.movies.size() > MAX_MOVIES) {
            throw new IllegalArgumentException("exceeds capacity");
        }
        checkRep();
    }

    public boolean add(String movie) {
       if (!validate(movie)) {
            throw new IllegalArgumentException("invalid title: " + movie);
        }
        if (movies.contains(movie) || movies.size() >= MAX_MOVIES) {
            return false;
        }
        movies.add(movie);
        checkRep();
        return true;
    }

    public int size() {
       return movies.size();
    }
}