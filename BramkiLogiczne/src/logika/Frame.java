package logika;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
    
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
			}
		});
		panel.add(btnWczytajPlikI);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
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
