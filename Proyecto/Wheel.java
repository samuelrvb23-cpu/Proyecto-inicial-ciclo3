/**
 * Write a description of class slotMachine here. 
 * @author Samuel Ricardo Rojas Barragán.
 * @author Laura Sofia Casas Venegas.
 * @version 1.0 (2026)
 */
import java.util.ArrayList;


public class Wheel {
    private ArrayList<Symbol> symbols;
    private int xposition;
    private int yposition;
    private boolean isvisible;
    private boolean isInvisible;
    private int currentindex;
    private Circle symbolshape;
    private Rectangle background;
    private boolean isLocked = false;
    //objetos para la clase wheel 
    public Wheel(ArrayList<Symbol> initialSymbols, int x, int y) {
    this.symbols = new ArrayList<>();
    int i = 1;
    for (Symbol s : initialSymbols) {
        this.symbols.add(new Symbol(s.getcolor(), i)); 
        i++;
    }
     this.xposition = x;
     this.yposition = y;
     this.isvisible = false;
     if (!this.symbols.isEmpty()){
         this.currentindex = (int)(Math.random()*this.symbols.size());
        }
        else{
            this.currentindex = 0;
        }
     
     initvisuals();
     
    }
        // formas geometricas de la rueda
    private void initvisuals(){
        background = new Rectangle();
        background.changeSize(110,60);
        background.changeColor("white");
        background.moveHorizontal(xposition - 60);
        background.moveVertical(yposition - 50);
        
        symbolshape = new Circle();
        symbolshape.changeSize(40);
        symbolshape.moveHorizontal(xposition );
        updatevisualSymbol();
        symbolshape.moveVertical(yposition - 15);
    }
        // coordenadas en el lienzo 
    public void movewheel(int newX, int newY) {
        makeInvisible();
        this.xposition = newX;
        this.yposition = newY;
        initvisuals();
        if (isvisible){
            makeVisible();
        }
        
    }
        // hacer visible rueda y simbolo
    public void makeVisible(){
        isvisible = true;
        background.makeVisible();
        symbolshape.makeVisible();
        
    }
        // ocultar la rueda y simbolo
    public void makeInvisible(){
        isvisible = false;
        background.makeInvisible();
        symbolshape.makeInvisible();
        
    }
    // ciclo 3 agregar y eliminar simbolo al final de la rueda 
    
    public void addSymbol(String color){
        symbols.add(new Symbol(color,symbols.size()+1));
        updatevisualSymbol();
        
    }
    public boolean delSymbol(String color){
        if (symbols.size() <= 1|| !containsSymbol(color)) {
            return false;
        }
        for (int i =0; i<symbols.size();i++) {
            if (symbols.get(i).getcolor().equalsIgnoreCase(color)) {
                symbols.remove(i);
                break;
            }
        }
        if (currentindex >= symbols.size()){
            currentindex = 0;
        }
        updatevisualSymbol();
        return true;
        
    }
    public boolean containsSymbol(String color) {
        for (Symbol s: symbols) {
            if (s.getcolor().equalsIgnoreCase(color)) 
            return true;
        }
        return false;
    }
    public void updatevisualSymbol(){
        if (!symbols.isEmpty() && symbolshape !=null){
            symbolshape.changeColor(symbols.get(currentindex).getcolor());
        }
        
    }
    public int getSymbolCount() {
        return symbols.size();
    }
    // ciclo 4 posicion la rueda en un simbolo determinado
    public boolean placesymbol (String color) {
       for (int i =0; i<symbols.size(); i++) {
           if (symbols.get(i).getcolor().equalsIgnoreCase(color)){
               currentindex = i;
               updatevisualSymbol();
               return true;
           }
       }
       return false;
    } // gira la rueda hacia la siguiente posicion en orden circular.
    public void spin(){
        if(!symbols.isEmpty() && !isLocked){
            currentindex = (currentindex +1) % symbols.size();
            updatevisualSymbol();
            
        }
    
    }
    // ciclo 5 simbolo actualmente se este mostrando 
    public Symbol getVisibleSymbol(){
        if (symbols.isEmpty()){
            return null;
        }
        return symbols.get(currentindex);
    }
    public String[] getSymbolsArray(){
        String[] arr= new String[symbols.size()];
        for (int i = 0; i <symbols.size(); i++) {
            arr[i] = symbols.get(i).getcolor();
        }
        return arr;
    }
    /**
     *  ciclo 2 añadir bloqueos a las ruedas y cambiar de lugar
     */
    public void lock(){
        this.isLocked = true;
    }
    public void unlock(){
        this.isLocked = false;
    }
    public boolean islocked(){
        return this.isLocked;
    }
}    