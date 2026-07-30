import java.util.*;


 //ADT BoundedStack สำหรับเก็บข้อมูลแบบ LIFO 
public class BoundedStack<E> {

    private final List<E> elements;
    private final int capacity;

    private void checkRep() {
        assert elements != null : "elements list must not be null";
        assert capacity > 0 : "capacity must be positive";
        assert elements.size() <= capacity : "exceeded max capacity";

        for (E item : elements) {
            assert item != null : "element in stack cannot be null";
        }
    }

    
    //Creator: สร้าง Stack เปล่าโดยกำหนด capacity
    
    public BoundedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.elements = new ArrayList<>();
        checkRep();
    }

    
    //Mutator: ดันข้อมูลเข้าด้านบนสุดของ Stack 
    
    public void push(E item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot push null item");
        }
        if (isFull()) {
            throw new IllegalStateException("stack is full (overflow)");
        }
        elements.add(item);
        checkRep();
    }

    
    //Mutator: ดึงข้อมูลตัวบนสุดออก แล้วส่งค่าคืน
    
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("stack is empty (underflow)");
        }
        E item = elements.remove(elements.size() - 1);
        checkRep();
        return item;
    }

    
    //Observer: ดูค่าตัวบนสุดโดยไม่เอาออกจาก Stack
    
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
        return elements.get(elements.size() - 1);
    }

    
    //Observer: เช็กจำนวนสมาชิกปัจจุบัน
     
    public int size() {
        return elements.size();
    }

    
    //Observer: เช็กว่าสแต็กว่างหรือไม่
     
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    
    //Observer: เช็กว่าสแต็กเต็มแล้วหรือยัง
     
    public boolean isFull() {
        return elements.size() >= capacity;
    }

   
}