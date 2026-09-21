
/**
 * Write a description of class slotMachine here. 
 * @author Samuel Ricardo Rojas Barragán.
 * @author Laura Sofia Casas Venegas.
 * @version 1.0 (2026)
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Collections;

public class SlotMachine {
    
    private ArrayList<Wheel> wheels;
    private ArrayList<Symbol> symboList;
    private boolean isvisible;
    private boolean lastOk;
    private Rectangle machineFrame;
    private Rectangle leverHorizontal;
    private Rectangle leververtical;
    private Circle lever;
    
    /**
     * Constructor for objects of class slotMachine
     */
    public SlotMachine(){
        this.wheels = new ArrayList<>();
        this.symboList = new ArrayList<>();
        this.isvisible = false;
        this.lastOk = true;
        
        symboList.add(new Symbol("red",1));
        symboList.add(new Symbol("yellow",2));
        symboList.add(new Symbol("blue",3));
        
        machineFrame = new Rectangle();
        machineFrame.changeSize(150,260);
        machineFrame.changeColor("black");
        machineFrame.moveHorizontal(-20);
        machineFrame.moveVertical(-10);
        
        wheels.add(new Wheel(symboList, 60, 60));
        wheels.add(new Wheel(symboList, 140, 60));
        wheels.add(new Wheel(symboList, 220, 60));
                
        initlever();
        
    }
    public SlotMachine(int n){
        
        if (n<1) {
            System.out.println("numero ingresado no es valido, ingrese un numero entero positivo");
            lastOk = false;
            return;
        }
        else{
            this.wheels = new ArrayList<>();
            this.symboList = new ArrayList<>();
            this.isvisible = false;
            this.lastOk = true;
            symboList.add(new Symbol("red",1));
            symboList.add(new Symbol("yellow",2));
            symboList.add(new Symbol("blue",3));
            machineFrame = new Rectangle();
            machineFrame.changeSize(150,(n*80)+20);
            machineFrame.changeColor("black");
            machineFrame.moveHorizontal(-20);
            machineFrame.moveVertical(-10);            
            for(int i=0; i < n; i++) {
                ArrayList<Symbol> wheelSymbols = new ArrayList<>(symboList);     
                Collections.shuffle(wheelSymbols);
                wheels.add(new Wheel(wheelSymbols,60 +(i*80),+60));
            }
            initlever();
        }
    }
    private void initlever() {
        int x = (wheels.size()*80);
        leverHorizontal = new Rectangle();
        leverHorizontal.changeSize(6,25);
        leverHorizontal.changeColor("black");
        leverHorizontal.moveHorizontal(x);
        leverHorizontal.moveVertical(50);
        
        leververtical = new Rectangle();
        leververtical.changeSize(45,6);
        leververtical.changeColor("black");
        leververtical.moveHorizontal(x + 19);
        leververtical.moveVertical(10);
        
        lever = new Circle ();
        lever.changeSize(24);
        lever.changeColor("gray");
        lever.moveHorizontal(x+70);
        lever.moveVertical(-2);
        
    }
        
    public void makeVisible() {
        isvisible = true;
        machineFrame.makeVisible();
        leverHorizontal.makeVisible();
        leververtical.makeVisible();
        lever.makeVisible();
        for (Wheel w :wheels) {
            w.makeVisible();
        }
        lastOk = true;
    }
    public void makeInvisible() {
        isvisible = false;
        leverHorizontal.makeInvisible();
        machineFrame.makeInvisible();
        leverHorizontal.makeInvisible();
        leververtical.makeInvisible();
        lever.makeInvisible();
        
        machineFrame.makeInvisible();
            for (Wheel w:wheels) {
                w.makeInvisible();
            }
            lastOk = true;
        }
    public boolean ok() {
        return lastOk;
    }
    // ciclo 2 añador y eliminar wheel
    public void addWheel(int pos){
        if (pos <1 || pos>wheels.size() +1){ 
            lastOk = false;
            return;
        }
        wheels.add(pos - 1, new Wheel(symboList, 60, 60));
        redraw();
        lastOk =true;  
    }
    public void delWheel(int pos){
        if (wheels.size() <=1){
            lastOk = false;
            if (isvisible) {
                JOptionPane.showMessageDialog(null,"no se puede borrar todas las ruedas");
            }
            return;
        }
        if (pos <1 || pos>wheels.size()) {
            lastOk = false;
            return;
        }
        Wheel w = wheels.remove(pos-1);
        w.makeInvisible();
        redraw();
        lastOk = true;
    }
    private void redraw() {
        machineFrame.changeSize(150,(wheels.size() *80)+20);
        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).movewheel(60 +(i *80),60);
        }
        leverHorizontal.makeInvisible();
        leververtical.makeInvisible();
        lever.makeInvisible();
        initlever();
        if (isvisible) {
            makeVisible();
        }
        
    }
    // ciclo 3 mismo paso ahora con symbolo
     public void addSymbol(int wheel,String symbol){
        if (wheel <1||wheel>wheels.size()){
            lastOk = false;
            return;
        }
        
        wheels.get(wheel -1).addSymbol(symbol);
        lastOk =true;  
    }
    public void delSymbol(String symbol){
            for (Wheel w: wheels) {
                if(w.containsSymbol(symbol) && w.getSymbolCount() <=1) {
                lastOk = false;
                if (isvisible) {
                    JOptionPane.showMessageDialog(null,"no se puede eliminar el simbolo");
                }
                return;
            }
    
        } 
        boolean anyDeleted = false;
        for (Wheel w: wheels) {
            if (w.delSymbol(symbol)){
                anyDeleted = true;
            }
        }
        if (!anyDeleted) {
            lastOk = false;
            if (isvisible) {
                JOptionPane.showMessageDialog(null,"el simbolo no existe en ninguna rueda");
            }
            return;
        }
        lastOk = true;
        } 
        // ciclo 4
    public void placeSymbol(int wheel, String symbol) {
        if (wheel <1||wheel>wheels.size()){
            lastOk = false;
            return;
        }
        boolean success = wheels.get(wheel -1).placesymbol(symbol);
        if (!success) {
            lastOk = false;
                if (isvisible) {
                    JOptionPane.showMessageDialog(null,"el simbolo no existe en la rueda indicada");
                }
                return;
            }
            lastOk = true;
        }
    public void spin(){
        for (Wheel w : wheels) {
            if (!w.islocked()) {
                // Cada rueda gira un número de pasos independiente para que no queden sincronizadas
                int steps = (int)(Math.random() * 12) + 3; // Entre 3 y 14 pasos aleatorios
                for (int s = 0; s < steps; s++) {
                    w.spin();
                }
            }
        }
        lastOk = true;
    }
    // ciclo 5
    public String[] configuration() {
        String[] config = new String[wheels.size()];
        for (int i = 0; i< wheels.size(); i++) {
            config[i] = wheels.get(i).getVisibleSymbol().getcolor();
        }
        lastOk = true;
        return config;
    }
    public String[] symbols() {
        if (wheels.isEmpty()) {
            lastOk = false;
            return new String[0];
        }
        lastOk = true;
        return wheels.get(0).getSymbolsArray();
    }
    public int distinctSymbols() {
        ArrayList<String> distinct = new ArrayList<>();
        for (Wheel w : wheels){
            String sym = w.getVisibleSymbol().getcolor();
            if (!distinct.contains(sym)) {
                distinct.add(sym);
            }
        }
        lastOk = true;
        return distinct.size();
    }
    // ciclo 6 
    public boolean isJackpot() {
        boolean jackpot = false;
        if (distinctSymbols() == 1) {
            jackpot = true;
            machineFrame.changeColor("yellow");
        }
        else {
            machineFrame.changeColor("black");
        }
        lastOk = true;
        return jackpot;
        
    }
    public void exit() {
        makeInvisible();
        System.exit(0);
    }
    public void swap(int wheel1, int wheel2){
        if (wheel1 < 1 || wheel1 > wheels.size() || wheel2 <1 || wheel2> wheels.size() || wheel1 == wheel2){
            lastOk = false;
            return;
        }
        Wheel temp = wheels.get(wheel1-1);
        wheels.set(wheel1-1,wheels.get(wheel2-1));
        wheels.set(wheel2 -1,temp);
        
        redraw();
        lastOk = true;
    }
    public void lock(int wheel){
        if (wheel < 1 || wheel > wheels.size()){
            lastOk = false;
            return;
        }
        wheels.get(wheel -1).lock();
        lastOk = true;
    }
    public void unlock(int wheel){
        if (wheel < 1 || wheel > wheels.size()){
            lastOk = false;
            return;
        }
        wheels.get(wheel - 1).unlock();
        lastOk = true;
        }  
    public void spin (int wheel, int steps){
        if (wheel < 1 || wheel > wheels.size() || steps <0){
            lastOk = false;
            return;
        }
        for (int s=0 ; s<steps; s++){
            wheels.get(wheel - 1).spin(); // <-- Llama directamente al método spin de esa rueda específica
        }
        lastOk = true;
    }
    public void spin(String[] setSymbols) {
        if (setSymbols == null || setSymbols.length != wheels.size()){
            lastOk = false;
            return;
        }
        for (int i = 0; i <wheels.size();i++){
            if (!wheels.get(i).containsSymbol(setSymbols[i])) {
                lastOk = false;
                return;
            }
        }
        for (int i= 0; i <wheels.size(); i++){
            wheels.get(i).placesymbol(setSymbols[i]);
        }
        lastOk = true;
    }
}     