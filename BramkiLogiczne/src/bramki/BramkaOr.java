package bramki;

public class BramkaOr extends Bramka {
	
	@Override
    public boolean Wylicz() {
        return getWejscie1().Wylicz() || getWejscie2().Wylicz();
    }

}
