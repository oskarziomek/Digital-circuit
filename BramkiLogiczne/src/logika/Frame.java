package logika;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import java.awt.Rectangle;

public class Frame extends JFrame {

	private JPanel contentPane;
	private String infix = "";
		
    
	public static void main(String[] args) {
		
		// Ustawienie systemowego wygl¹du okna aplikacji
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	    }
	    catch (ClassNotFoundException e) {
	    }
	    catch (InstantiationException e) {
	    }
	    catch (IllegalAccessException e) {
	    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// main
		
	}
	
	public static String czytajWyrazenieZPliku(String sciezka) {
		FileReader fr = null;
		String linia = "";

		// Otwieranie pliku
		try {
			fr = new FileReader(sciezka);
		} catch (FileNotFoundException e) {
			System.out.println("B³¹d przy otwieraniu pliku.");
		}

		BufferedReader bf = new BufferedReader(fr);
		
		try {
			linia = bf.readLine();
	    } catch (IOException e) {
	        System.out.println("B³¹d podczas czytania z pliku.");
	    }

		// Zamykanie pliku
		try {
			fr.close();
	    } catch (IOException e) {
	        System.out.println("B³¹d przy zamykaniu pliku.");
	    }
		
		return linia;	    
	}

	public Frame() {
		setTitle("Bramki Logiczne - wybierz plik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(new Rectangle(10, 10, 10, 10));
		scrollPane.setViewportView(textPane);
		textPane.setFont(new Font("Courier New", Font.BOLD, 15));
		textPane.setForeground(SystemColor.window);
		textPane.setCaretColor(Color.DARK_GRAY);
		textPane.setBackground(Color.DARK_GRAY);
		
		JButton btnWczytajPlikI = new JButton("Wczytaj plik i poka¿ tablicê prawdy");
		btnWczytajPlikI.setFocusable(false);
		btnWczytajPlikI.setToolTipText("Wczytuje dzia\u0142anie boolowskie z zewn\u0119trznego pliku, a nast\u0119pnie oblicza jego warto\u015B\u0107 dla wszystkich kombinacji zmiennych");
		btnWczytajPlikI.setBackground(new Color(0, 0, 0));
		btnWczytajPlikI.setForeground(new Color(0, 0, 0));
		btnWczytajPlikI.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnWczytajPlikI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infix = czytajWyrazenieZPliku(czytajPlik());
				//System.out.println("Dzialanie z infixem: " + infix);
		        //System.out.println("Dzialanie z prefixem: " + infixNaPrefix(infix));
		        
		        ParserWyrazenia pw = new ParserWyrazenia();		        
		        Wyliczalne a = pw.parsuj(infixNaPrefix(infix));
		        
		        String zmienne = sprawdzZmienne(new ArrayList<>(pw.zwrocZmienne().values()));
		        
		        String wynik = wypiszPrawdy(a, new ArrayList<>(pw.zwrocZmienne().values()));		        
		        String temp = wynik.replaceAll("false", "0");
		        String result = temp.replaceAll("true", "1");
		        
		        textPane.setEditable(false); 
		        textPane.setText("Wczytano wyra¿enie: " + infix + "\n\n" + zmienne + "    Wynik:" + "\n" + result);	        
			}
		});		
		panel.add(btnWczytajPlikI);		
	}
	
	public String czytajPlik() {
		String wynik = "";
		File selectedFile = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
		}
		wynik = selectedFile.getAbsolutePath();	
		return wynik;	
	}
	
	public static ArrayList<boolean[]> kombinatron(int dlugosc) {
        ArrayList<boolean[]> kombinacje = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, dlugosc); i++) {
            String bin = Integer.toBinaryString(i);
            while (bin.length() < dlugosc) {
                bin = "0" + bin;
            }
            char[] chars = bin.toCharArray();
            boolean[] boolArray = new boolean[dlugosc];
            for (int j = 0; j < chars.length; j++) {
                boolArray[j] = chars[j] != '0';
            }
            kombinacje.add(boolArray);
        }
        return kombinacje;
    }
	
	public static String sprawdzZmienne(ArrayList<Zmienna> zmienne) {
    	String tmp = "[";
        for (int i = 0; i < zmienne.size(); i++) {
        	 tmp += zmienne.get(i).getSymbol();
        	 tmp += ", ";
        }
        String wynik = tmp.substring(0, tmp.length()-2);
        wynik += "]";
        return wynik;
	}

    public static String wypiszPrawdy(Wyliczalne korzen, ArrayList<Zmienna> zmienne) {
    	String wynik = "";
        ArrayList<boolean[]> kombinacje = kombinatron(zmienne.size());
        
        for (boolean[] kombinacja : kombinacje) {
            for (int i = 0; i < kombinacja.length; i++) {
                zmienne.get(i).setWartoscLogiczna(kombinacja[i]);
            }
            wynik += Arrays.toString(kombinacja) + " ->   " + korzen.Wylicz() + "\n";
        }
        return wynik;
    }

    public static String infixNaPrefix(String infix) {
        Stack stack = new Stack();
        String prefix = "";
        for (int i = infix.length() - 1; i >= 0; i--) {
            char c = infix.charAt(i);
            if (Character.isLetter(c)) {
                prefix = ((Character) c).toString() + prefix;
            } else if (c == '(') {
                prefix = ((Character) stack.pop()).toString() + prefix;
            } else if (c == ')') {
                continue;
            } else if (c == '-') {
                prefix = c + prefix;
            } else {
                stack.push(c);
            }
        }
        return prefix;
    }

}
