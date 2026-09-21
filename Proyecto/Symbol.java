
/**
 * Write a description of class Symbol here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Symbol
{
    
    
    private String color;
    private int value;

    /**
     * Constructor for objects of class Symbol
     */
    public Symbol(String color, int value)
    {
        this.color = color;
        this.value = value;
    }
    public String getcolor(){
        return color;
    }
    public int getvalue(){
        return value;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setValue(int value){
        this.value = value;
    }
}