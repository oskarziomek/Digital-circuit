package logika;

import java.util.HashMap;

import bramki.BramkaAnd;
import bramki.BramkaNand;
import bramki.BramkaNor;
import bramki.BramkaNot;
import bramki.BramkaOr;
import bramki.BramkaXor;

public class ParserWyrazenia {
	
	private static final char OR = '+';
    private static final char AND = '*';
    private static final char NOT = '-';
    private static final char XOR = '^';
    private static final char NOR = '%';
    private static final char NAND = '#';

    private HashMap<Character, Zmienna> zmienne;
    public String aktWyrazenie;
    
    public Wyliczalne parsuj(String wyrazenie) {
        zmienne = new HashMap<>();
        aktWyrazenie = wyrazenie;
        return tworzWezel();
    }

    public static boolean znakJestOperacja(char znak) {
        return (znak == OR || znak == AND || znak == NOT || znak == XOR || znak == NOR || znak == NAND);
    }
     
    public Wyliczalne tworzWezel() {
        char znakOperacji = aktWyrazenie.charAt(0);
        aktWyrazenie = aktWyrazenie.substring(1);  
        switch (znakOperacji) {
            case OR:
                BramkaOr bor = new BramkaOr();
                bor.setWejscie1(tworzWezel());
                bor.setWejscie2(tworzWezel());
                return bor;
            case AND:
                BramkaAnd band = new BramkaAnd();
                band.setWejscie1(tworzWezel());
                band.setWejscie2(tworzWezel());
                return band;
            case NOT:
                BramkaNot bnot = new BramkaNot();
                bnot.setWejscie(tworzWezel());
                return bnot;
            case XOR:
                BramkaXor bxor = new BramkaXor();
                bxor.setWejscie1(tworzWezel());
                bxor.setWejscie2(tworzWezel());
                return bxor;
            case NOR:
                BramkaNor bnor = new BramkaNor();
                bnor.setWejscie1(tworzWezel());
                bnor.setWejscie2(tworzWezel());
                return bnor;
            case NAND:
                BramkaNand bnand = new BramkaNand();
                bnand.setWejscie1(tworzWezel());
                bnand.setWejscie2(tworzWezel());
                return bnand;
            default:
                return tworzZmienna(znakOperacji);
                
        }
    }

    public Wyliczalne tworzZmienna(char znakZmiennej) {
       if(!zmienne.containsKey(znakZmiennej)) {
          Zmienna z = new Zmienna(znakZmiennej);
          zwrocZmienne().put(znakZmiennej, z);
       }       
       return zwrocZmienne().get(znakZmiennej);
    }

    public HashMap<Character, Zmienna> zwrocZmienne() {
        return zmienne;
    }

}
