package bramki;

public class BramkaXor extends Bramka {
	
	@Override
    public boolean Wylicz() {
        boolean wynik1 = getWejscie1().Wylicz();
        boolean wynik2 = getWejscie2().Wylicz();
        return (wynik1 && !wynik2) || (!wynik1 && wynik2);
    }

}
