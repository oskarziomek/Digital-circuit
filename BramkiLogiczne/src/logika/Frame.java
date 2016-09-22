package logika;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import javax.swing.JLabel;
import java.awt.SystemColor;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String infix = "";
	JLabel lblNewLabel = new JLabel(" ");
	
    
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
		setTitle("Bramki Logiczne");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnWczytajPlikI = new JButton("Wczytaj plik i poka¿ tablicê prawdy");
		btnWczytajPlikI.setToolTipText("Wczytuje dzia\u0142anie boolowskie z zewn\u0119trznego pliku, a nast\u0119pnie oblicza jego warto\u015B\u0107 dla wszystkich kombinacji zmiennych");
		btnWczytajPlikI.setBackground(new Color(0, 0, 0));
		btnWczytajPlikI.setForeground(new Color(0, 0, 0));
		btnWczytajPlikI.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnWczytajPlikI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infix = czytajWyrazenieZPliku(czytajPlik());
				System.out.println("Dzialanie z infixem: " + infix);
		        System.out.println("Dzialanie z prefixem: " + infixNaPrefix(infix));
		        
		        ParserWyrazenia pw = new ParserWyrazenia();
		        
		        Wyliczalne a = pw.parsuj(infixNaPrefix(infix));
		        
		        wypiszPrawdy(a, new ArrayList<>(pw.zwrocZmienne().values()));
		        
		        lblNewLabel.setText(infix);
		        
			}
		});
		panel.add(btnWczytajPlikI);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.desktop);
		contentPane.add(panel_1, BorderLayout.CENTER);
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public String czytajPlik() {
		String wynik = "";
		File selectedFile = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
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

    private static void wypiszPrawdy(Wyliczalne korzen, ArrayList<Zmienna> zmienne) {
        ArrayList<boolean[]> kombinacje = kombinatron(zmienne.size());
        for (boolean[] kombinacja : kombinacje) {
            for (int i = 0; i < kombinacja.length; i++) {
                zmienne.get(i).setWartoscLogiczna(kombinacja[i]);
            }
            System.out.println(Arrays.toString(kombinacja) + " " + korzen.Wylicz());
        }
    }

    private static String infixNaPrefix(String infix) {
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
