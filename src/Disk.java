

 // Klasa specifikacije diskova
 

public class Disk { 
    
    protected static final int THICKNESS = 25;  //18
    
    
    private int size; 
    
    // Predstavljanje diskova odredjenih velicina.
    public Disk(int size) {
        this.size = size;
    }
    
    
     // Vraca velicinu odredjenog diska.
     
    public int getSize() {
        return size;
    }
}