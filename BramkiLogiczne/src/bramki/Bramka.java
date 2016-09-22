package bramki;

import logika.Wyliczalne;

public class Bramka implements Wyliczalne {    
    private Wyliczalne wejscie1;
    private Wyliczalne wejscie2;

    public Wyliczalne getWejscie1() {
        return wejscie1;
    }

    public void setWejscie1(Wyliczalne wejscie1) {
        this.wejscie1 = wejscie1;
    }

    public Wyliczalne getWejscie2() {
        return wejscie2;
    }

    public void setWejscie2(Wyliczalne wejscie2) {
        this.wejscie2 = wejscie2;
    }

	@Override
	public boolean Wylicz() {
	
		return false;
	}
}
