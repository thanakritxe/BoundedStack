import java.util.*;


 //ADT BoundedStack สำหรับเก็บข้อมูลแบบ LIFO 
 //โดยมีการกำหนดขนาดความจุสูงสุดชัดเจน
 
public class BoundedStack<E> {

    // AF(elements, capacity) = สแต็กที่มี capacity เท่ากับตัวแปร capacity
    //   และมีข้อมูลเรียงจากล่างขึ้นบนเป็น elements[0], elements[1], ..., elements[size()-1]
    //
    // RI:
    //   - elements ต้องไม่เป็น null
    //   - capacity ต้องมากกว่า 0
    //   - elements.size() ต้องไม่เกิน capacity
    //   - สมาชิกใน elements ต้องไม่มีค่า null

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
   

    /**
     *  Creator: สร้าง Stack เปล่าโดยกำหนด capacity
     * @param capacity ขนาดความจุสูงสุดที่สแต็กนี้สามารถรองรับ
     * @requires capacity > 0
     * @effects สร้างสแต็กใหม่ที่ว่างเปล่า (size = 0) และมีความจุสูงสุดเท่ากับ capacity
     * @throws IllegalArgumentException ถ้า capacity น้อยกว่าหรือเท่ากับ0
     */
    
    public BoundedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.elements = new ArrayList<>();
        checkRep();
    }
     

    /**
     * Mutator: ดันข้อมูลเข้าด้านบนสุดของ Stack 
     * @param item ข้อมูลที่ต้องการเพิ่มเข้าสแต็ก
     * @requires item != null และ !isFull()
     * @effects เพิ่ม item เข้าไปอยู่ที่ตำแหน่งบนสุดของสแต็ก (top) ทำให้ size เพิ่มขึ้น 1
     * @throws IllegalArgumentException ถ้า item มีค่าเป็น null
     * @throws IllegalStateException ถ้าสแต็กเต็มแล้ว (isFull() == true)
     */
    
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
    
    /**
     * Mutator: ดึงข้อมูลตัวบนสุดออก แล้วส่งค่าคืน
     * @return ข้อมูลตัวบนสุดที่เพิ่งถูกนำออกจากสแต็ก
     * @requires !isEmpty()
     * @effects ลบข้อมูลตัวบนสุดออกจากสแต็ก และทำให้ size ลดลง 1
     * @throws IllegalStateException ถ้าสแต็กนี้ว่างเปล่า 
     */
    
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("stack is empty (underflow)");
        }
        E item = elements.remove(elements.size() - 1);
        checkRep();
        return item;
    }
    
    /**
    
     * observer: ดูค่าตัวบนสุดโดยไม่เอาออกจาก Stack
     * @return ข้อมูลตัวบนสุดของสแต็กปัจจุบัน
     * @requires !isEmpty()
     * @effects ไม่เปลี่ยนแปลงสถานะใดๆ ในสแต็ก
     * @throws IllegalStateException ถ้าสแต็กว่างเปล่า (isEmpty() == true)
     */
    
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
        return elements.get(elements.size() - 1);
    }
    
    /**
    
        * Observer: เช็กจำนวนสมาชิกปัจจุบัน
        * @return จำนวนสมาชิกปัจจุบันในสแต็ก (0 <= size <= capacity)
        * @requires none
        * @effects ไม่เปลี่ยนแปลงสถานะใดๆ ในสแต็ก
        */
    public int size() {
        return elements.size();
    }
    /**
     * [Observer] ตรวจสอบว่าสแต็กว่างเปล่าหรือไม่
     * 
     * @return true ถ้าสแต็กไม่มีสมาชิกเลย (size == 0), false ในกรณีอื่นๆ
     *///
     
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
     /**
     *Observer: เช็กว่าสแต็กเต็มแล้วหรือยัง
     * 
     * @return true ถ้าจำนวนสมาชิกเท่ากับ capacity (size == capacity), false ในกรณีอื่นๆ
     */
    public boolean isFull() {
        return elements.size() >= capacity;
    }
    //Producer: สร้างคัดลอก BoundedStack ตัวใหม่ขึ้นมา โดยไม่กระทบตัวเดิม
    /**
     *Producer: สร้างคัดลอก BoundedStack ตัวใหม่ขึ้นมา โดยไม่กระทบตัวเดิม
     * 
     * @return BoundedStack อ็อบเจกต์ใหม่ที่มีความจุเท่าเดิมและบรรจุข้อมูลเหมือนเดิม
     * 
     */
     
    public BoundedStack<E> copy() {
        BoundedStack<E> newStack = new BoundedStack<>(this.capacity);
        for (E item : this.elements) {
            newStack.push(item);
        }
        return newStack;
    }
}