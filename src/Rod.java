
//Klasa predstavlja Rod sa diskovima na vrhu. Ona pokrece


import java.util.Stack;

public class Rod {
    //Konstanta
    private static final int  DISK_CAPACITY = 6;
    
    //Promenljiva varijabla
    private Stack<Disk> disksOnTop;
    
    /* Konstruktor u osnovi konstrukise stack koji modeluje 
       diskove koji se nalaze na vrhu. */
     
    public Rod() {
        disksOnTop = new Stack<Disk>();
    }
    
    /* Konstruktor u osnovi konstruise stack koji modeluje 
       diskove koji se nalaze na vrhu. Vraca broj diskova 
       koji se nalazi na vrhu. */
    //numberOfDisks - broj diskova koji ce stap sadrzati.
    
    public Rod(int numberOfDisks) {
        disksOnTop = new Stack<Disk>();
        for (int i = 0; i < numberOfDisks; i++) {
            disksOnTop.push(new Disk(DISK_CAPACITY - i));
        }
    }
    
   
    //Vraca stack koji se sastoji od diskova na vrhu stapa.

    public Stack<Disk> getDisksOnTop() {
        return disksOnTop;
    }
}