package bramki;

public class BramkaAnd extends Bramka {
	
	@Override
    public boolean Wylicz() {
        return getWejscie1().Wylicz() && getWejscie2().Wylicz();
    }

}
