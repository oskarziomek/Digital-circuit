package bramki;

import logika.Wyliczalne;

public class BramkaNot implements Wyliczalne {
    private Wyliczalne wejscie;
    
    @Override
    public boolean Wylicz() {
        return !wejscie.Wylicz();
    }

    public Wyliczalne getWejscie() {
        return wejscie;
    }

    public void setWejscie(Wyliczalne wejscie) {
        this.wejscie = wejscie;
    }    
}
