
/*
    Ova klasa predstavlja stapove koji se sastoje od tri odvojena stapa 
    sa diskovima na vrhu. Ona uklju;uje metode za premestanje diskova 
    sa jednog stapa na drugi po odredjenim obrascima.
*/

import java.util.Stack;
import java.util.LinkedList;

public class Rods {
    //Konstante
    private static final int NUMBER_OF_RODS = 3;
    
    //Promenljive instance
    private Rod[] rodArray;
    private LinkedList<Integer> movesToSolve;
    
     /* Konstruktor klase rods. Uzima broj diska i pocetni stap, stap gde ce 
      diskovi biti na pocetku kao parametri.*/
      //numberOfDisks - broj diskova na platformi.
      //initialRod - Stap u kojem ce se diskovi nalaziti na pocetku.
     
    public Rods(int numberOfDisks, int initialRod) {
        Rod initial = new Rod(numberOfDisks);
        rodArray = new Rod[NUMBER_OF_RODS];
        for (int i = 0; i < NUMBER_OF_RODS; i++) {
            if (i == initialRod) {
                rodArray[i] = initial;
            } else { 
                rodArray[i] = new Rod();
            }
        }
        rodArray[initialRod] = initial;
        movesToSolve = new LinkedList<Integer>(); 
    }
    
    /*Metod je slican konstruktoru, inicializuje platformu,
     * ali ona ne menja druge strukture podataka.*/
     //numberOfDisks - broj diskova na platformi.
     //initialRod - stap u kojem ce se diskovi nalaziti na pocetku.
     
    public void initializeRods(int numberOfDisks, int initialRod) {
        Rod initial = new Rod(numberOfDisks);
        rodArray = new Rod[NUMBER_OF_RODS];
        for (int i = 0; i < NUMBER_OF_RODS; i++) {
            if (i == initialRod) {
                rodArray[i] = initial;
            } else { 
                rodArray[i] = new Rod();
            }
        }
        rodArray[initialRod] = initial;
    }
    
    
     //Vraca niz od tri odvojena stapa.

    public Rod[] getRodArray() {
        return rodArray;
    }
    
    //Pomera disk sa vrha jednog stapa na vrh drugog stapa
    //from - Stap kojim se uzima disk.
    //destination - Stap koji je dat disku.

    public void moveDisk(int from, int destination) {
        Stack<Disk> fromStack = rodArray[from].getDisksOnTop();
        if (!fromStack.isEmpty()) {
            rodArray[destination].getDisksOnTop().push(fromStack.pop());
        } else {
            System.out.println("Rod is empty, no disks to move...");
        }          
    }
    
    /*Pomera diskove po odredjenom rekurizivnom obrascu, 
      tako da problem sa kulamas se resava pravilno.*/
     //disksOnTop - Broj diskova na pocetnoj platformi
     //from - Stap na kojima su diskovi na pocetak.
     //pare - Stap koji se ne susrece sa odredistem.
     //destination - Stap, gde ce se diskovi nalaziti na kraju.
     
    public void solveTowersOfHanoi(int disksOnTop, int from, int spare, int destination) {
        if (disksOnTop >= 1) {
            solveTowersOfHanoi(disksOnTop - 1, from, destination, spare);
            moveDisk(from, destination);
            movesToSolve.addLast(from);
            movesToSolve.addLast(destination);
            solveTowersOfHanoi(disksOnTop - 1, spare, from, destination);
        } 
    }
    
    /*
      Vraca strukturu podataka koja sadrzi odredjene poteze za resavanje 
      Hanoi-skih kula. */
     
    public LinkedList<Integer> getMovesToSolve() {
        return movesToSolve;
    }
}