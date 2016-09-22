package logika;

public class Zmienna implements Wyliczalne {
	
    private boolean wartosc;
    private char symbol;
    
    public Zmienna(char symbol) {
        this.symbol = symbol;
    }
    
    public void setWartoscLogiczna(boolean nowaWartosc) {
        wartosc = nowaWartosc;
    }

    @Override
    public boolean Wylicz() {
        return wartosc;
    }
    
    public char getSymbol() {
        return symbol;
    }
}