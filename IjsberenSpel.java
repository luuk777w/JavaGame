package com.ijsberenSpel;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Color;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IjsberenSpel {

	//het frame
	private JFrame frame;
	//alle textfields
	private JTextField TF_wakken;
	private JTextField TF_ijsberen;
	private JTextField TF_pinguins;
	private JTextField TF_wakkenOplossing;
	private JTextField TF_ijsberenOplossing;
	private JTextField TF_pinguinsOplossing;
	private JTextField TF_dice;
	//alle buttons
	private JButton BTN_werpen;
	private JButton BTN_controleren;
	private JButton BTN_nieuwSpel;
	private JButton BTN_oplossing;
	private JTextField TF_gegooid;
	private JTextField TF_goedGeraden;
	private JTextField TF_foutGeraden;
	//alle labels
	private JLabel LB_wakken;
	private JLabel LB_ijsberen;
	private JLabel LB_pinguins;
	private JLabel LB_dice;
	private JLabel LB_tips;
	private JLabel LB_wakkenOplossing;
	private JLabel LB_ijsberenOplossing;
	private JLabel LB_pinguinsOplossing;
	private JLabel LB_gegooid;
	private JLabel LB_goedGeraden;
	private JLabel LB_foutGeraden;
	private JLabel LB_welkom; 
	//alle panels
	private Panel P_dice;
	private Panel P_velden;
	private Panel P_tips;
	private Panel P_oplossing;
	//overig
	private List tipList;
	
	/*
	 *Variables 
	 */

	ArrayList<Integer> dices = new ArrayList<Integer>();
	
	String[] tips = {
			"Ijsberen leven op de noordpool, Pinguins leven op de...",
			"Tip2",
			"Tip3"
	};
	
	int wakken = 0;
	int ijsberen = 0;
	int pinguins = 0;
	int beurten = 0;
	int goed = 0;
	int fout = 0;

	/**
	 * Het initialiseren van de Ijsberen Method
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IjsberenSpel window = new IjsberenSpel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * De main method.
	 */
	public IjsberenSpel() {
		layout();
		
		/*
		 * ===================================================
		 * Nieuw Spel Button
		 * ===================================================
		 */
		BTN_nieuwSpel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogResult = JOptionPane.showConfirmDialog (null, 
						"Weet je zeker dat je een nieuw spel wilt starten?","Warning",JOptionPane.OK_CANCEL_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					reset();
					P_dice.removeAll();
					P_dice.revalidate();
					P_dice.repaint(); 
					beurten = 0;
					goed = 0;
					fout = 0;
					
					TF_gegooid.setText(null);
					TF_goedGeraden.setText(null);
					TF_foutGeraden.setText(null);
					
					BTN_oplossing.setEnabled(false);
					BTN_controleren.setEnabled(false);
					BTN_werpen.setEnabled(true);
				}

			}
		});
		
		/*
		 * ===================================================
		 * Oplossing Button
		 * ===================================================
		 */
		BTN_oplossing.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				BTN_oplossing.setEnabled(false);
				
				TF_wakkenOplossing.setText(Integer.toString(wakken));
				TF_ijsberenOplossing.setText(Integer.toString(ijsberen));
				TF_pinguinsOplossing.setText(Integer.toString(pinguins));

			}
		});
		
		/*
		 * ===================================================
		 * Controlleer Button
		 * ===================================================
		 */
		BTN_controleren.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//Reset variables
				int Userwakken = 0;
				int Userijsberen = 0;
				int Userpinguins = 0;
				int score = 0;
				wakken = 0;
				ijsberen = 0;
				pinguins = 0;
				
				try{
					Userwakken = Integer.parseInt(TF_wakken.getText());
					Userijsberen = Integer.parseInt(TF_ijsberen.getText());
					Userpinguins = Integer.parseInt(TF_pinguins.getText());
					
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Ongeldige invoer! \nEr is niks ingevult of het is geen cijfer.", 
							"ERROR: Ongeldige invoer!", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				BTN_oplossing.setEnabled(true);
				BTN_werpen.setEnabled(true);
				BTN_controleren.setEnabled(false);
				
				for(int i = 0; i < dices.size(); i++){
					
					int dice2 = dices.get(i);
					
					switch(dice2){
					case 1: wakken = wakken + 1;
							pinguins = pinguins + 6;
							break;	
					case 2: pinguins = pinguins + 5;
							break;
					case 3: wakken = wakken +1;
							ijsberen = ijsberen + 2;
							pinguins = pinguins + 4;
							break;
					case 4: pinguins = pinguins + 3;
							break;
					case 5:	wakken = wakken +1;
							ijsberen = ijsberen + 4;
							pinguins = pinguins + 2;
							break;
					case 6: pinguins = pinguins + 1;
							break;
					}
					
				}
				
				if(Userwakken == wakken){
					TF_wakken.setBackground(Color.GREEN);
					score = score + 1;
				} else {
					TF_wakken.setBackground(Color.RED);
					TF_wakken.setForeground(Color.WHITE);
				}
				
				if(Userijsberen == ijsberen){
					TF_ijsberen.setBackground(Color.GREEN);
					score = score + 1;
				} else {
					TF_ijsberen.setBackground(Color.RED);
					TF_ijsberen.setForeground(Color.WHITE);
				}
				
				if(Userpinguins == pinguins){
					TF_pinguins.setBackground(Color.GREEN);
					score = score + 1;
				} else {
					TF_pinguins.setBackground(Color.RED);
					TF_pinguins.setForeground(Color.WHITE);
				}
				
				if(score == 3){
					goed = goed + 1;
				} else {
					fout = fout + 1;
				}
				
				beurten = beurten + 1;
				
				TF_gegooid.setText(Integer.toString(beurten));
				TF_goedGeraden.setText(Integer.toString(goed));
				TF_foutGeraden.setText(Integer.toString(fout));
				
				switch(fout){
					case 3: tipList.add(tips[0]);
						break;
					case 5: tipList.add(tips[1]);
						break;
					case 8: tipList.add(tips[2]);
						break;
						
				}
			}
		});
		
		/*
		 * ===================================================
		 * Werp Button
		 * ===================================================
		 */
		BTN_werpen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				BTN_controleren.setEnabled(true);
				BTN_oplossing.setEnabled(false);
				BTN_werpen.setEnabled(false);
				
				//resetting
				dices.removeAll(dices);
				P_dice.removeAll();
				reset();
				
				int dice = Validate(TF_dice.getText());
				
				if(dice != 0){
				
					int[] nummerX = {
						10, 100, 190,
						280, 370, 460,
						10, 100, 190,
						280, 370, 460
					};
					
					int[] nummerY = {
						10, 10, 10,
						10, 10, 10,
						100, 100, 100,
						100, 100, 100,	
					};
					
					for(int i = 1; i <= dice; i++){
						
						int random = ThreadLocalRandom.current().nextInt(1, 6 + 1);
						dices.add(random);
						ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("dice" + random + ".png")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
						
						JLabel diceLabel = new JLabel();
						diceLabel.setIcon(imageIcon);
						diceLabel.setBounds(nummerX[i-1], nummerY[i-1], 80, 80);
						P_dice.add(diceLabel);
						
					}
					
					P_dice.revalidate();
					P_dice.repaint(); 
					
				}
			}
		});

	}
	
	/**
	 * Valideert of het nummer een nummer is, en/of
	 * het tussen 3 en 12 zit
	 * @
	 * @return int
	 */
	public int Validate(String dice){
		
		int output = 0;
		
		try{
			int parsedDice = Integer.parseInt(dice);
			
			if(parsedDice >= 3 && parsedDice <= 12){
				output = parsedDice;
			} else{
				throw new NumberFormatException();
			}
			
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Ongeldige invoer! \nLetop dat het een getal tussen 3 en 12 moet zijn", 
					"InfoBox: " + "Ongeldige invoer!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return output;
	}
	
	/**
	 * Dit reset:
	 * TF_wakken
	 * TF_ijsberen
	 * TF_pinguins
	 * TF_wakkenOplossing
	 * TF_ijsberenOplossing
	 * TF_pinguinsOplossing
	 */
	private void reset(){
		
		TF_wakken.setBackground(Color.WHITE);
		TF_wakken.setForeground(Color.BLACK);
		TF_wakken.setText(null);
		TF_ijsberen.setBackground(Color.WHITE);
		TF_ijsberen.setForeground(Color.BLACK);
		TF_ijsberen.setText(null);
		TF_pinguins.setBackground(Color.WHITE);
		TF_pinguins.setForeground(Color.BLACK);
		TF_pinguins.setText(null);
		
		TF_wakkenOplossing.setText(null);
		TF_ijsberenOplossing.setText(null);
		TF_pinguinsOplossing.setText(null);
	}

	/**
	 * De layout. Dit is gewoon de layout.
	 */
	private void layout() {
		
		//Het maken van het frame
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 590, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * ================================================
		 * Alle panels.
		 * ================================================
		 */
		
		P_dice = new Panel();
		P_dice.setBackground(Color.WHITE);
		P_dice.setBounds(10, 45, 555, 190);
		frame.getContentPane().add(P_dice);
		P_dice.setLayout(null);
		
		P_velden = new Panel();
		P_velden.setBackground(Color.WHITE);
		P_velden.setBounds(10, 240, 555, 75);
		frame.getContentPane().add(P_velden);
		P_velden.setLayout(null);
		
		P_tips = new Panel();
		P_tips.setBackground(Color.WHITE);
		P_tips.setBounds(10, 320, 220, 195);
		frame.getContentPane().add(P_tips);
		P_tips.setLayout(null);
		
		P_oplossing = new Panel();
		P_oplossing.setBackground(Color.WHITE);
		P_oplossing.setBounds(245, 320, 320, 195);
		frame.getContentPane().add(P_oplossing);
		P_oplossing.setLayout(null);
		
		/*
		 * ================================================
		 * Labels en invul velden voor het P_velden paneel.
		 * ================================================
		 */
		
		//De werpen Button.
		BTN_werpen = new JButton("Werpen");
		BTN_werpen.setBounds(465, 10, 80, 30);
		P_velden.add(BTN_werpen);
		
		//De controlleer Button.
		BTN_controleren = new JButton("Controleren");
		BTN_controleren.setEnabled(false);
		BTN_controleren.setBounds(10, 45, 535, 23);
		P_velden.add(BTN_controleren);
		
		//Wakken label.
		LB_wakken = new JLabel("Wakken:");
		LB_wakken.setBounds(10, 10, 55, 30);
		P_velden.add(LB_wakken);
		
		//wakken TextField.
		TF_wakken = new JTextField();
		TF_wakken.setBounds(60, 15, 30, 20);
		P_velden.add(TF_wakken);
		TF_wakken.setColumns(10);
		
		//ijsberen label.
		LB_ijsberen = new JLabel("Ijsberen:");
		LB_ijsberen.setBounds(100, 10, 55, 30);
		P_velden.add(LB_ijsberen);
		
		//ijsberen TextField.
		TF_ijsberen = new JTextField();
		TF_ijsberen.setColumns(10);
		TF_ijsberen.setBounds(150, 15, 30, 20);
		P_velden.add(TF_ijsberen);
		
		//pinguins label.
		LB_pinguins = new JLabel("Pinguins:");
		LB_pinguins.setBounds(190, 10, 55, 30);
		P_velden.add(LB_pinguins);
		
		//pinguins TextField.
		TF_pinguins = new JTextField();
		TF_pinguins.setColumns(10);
		TF_pinguins.setBounds(245, 15, 30, 20);
		P_velden.add(TF_pinguins);
		
		//Aantal Dobbelstenen Label.
		LB_dice = new JLabel("Aantal dobbelstenen:");
		LB_dice.setBounds(300, 10, 120, 30);
		P_velden.add(LB_dice);
		
		//Aantal Dobbelstenen TextField.
		TF_dice = new JTextField();
		TF_dice.setText("3");
		TF_dice.setColumns(10);
		TF_dice.setBounds(425, 15, 30, 20);
		P_velden.add(TF_dice);
		
		/*
		 * ================================================
		 * Labels en invul velden voor het P_tips paneel.
		 * ================================================
		 */
		
		//Tips Label.
		LB_tips = new JLabel("Tips:");
		LB_tips.setBounds(95, 10, 40, 20);
		P_tips.add(LB_tips);
		
		tipList = new List();
		tipList.setBounds(11, 36, 199, 149);
		P_tips.add(tipList);
		
		/*
		 * ================================================
		 * Labels en invul velden voor het P_oplossing paneel.
		 * ================================================
		 */
		
		//Oplossing Button
		BTN_oplossing = new JButton("De Oplossing");
		BTN_oplossing.setEnabled(false);
		BTN_oplossing.setBounds(85, 10, 150, 30);
		P_oplossing.add(BTN_oplossing);
		
		//wakken Label.
		LB_wakkenOplossing = new JLabel("Wakken:");
		LB_wakkenOplossing.setBounds(12, 50, 60, 20);
		P_oplossing.add(LB_wakkenOplossing);
		
		//wakken TextField.
		TF_wakkenOplossing = new JTextField();
		TF_wakkenOplossing.setEditable(false);
		TF_wakkenOplossing.setColumns(10);
		TF_wakkenOplossing.setBounds(72, 50, 30, 20);
		P_oplossing.add(TF_wakkenOplossing);
		
		//ijsberen Label.
		LB_ijsberenOplossing = new JLabel("Ijsberen:");
		LB_ijsberenOplossing.setBounds(112, 50, 60, 20);
		P_oplossing.add(LB_ijsberenOplossing);
		
		//ijsberen TextField.
		TF_ijsberenOplossing = new JTextField();
		TF_ijsberenOplossing.setEditable(false);
		TF_ijsberenOplossing.setColumns(10);
		TF_ijsberenOplossing.setBounds(172, 50, 30, 20);
		P_oplossing.add(TF_ijsberenOplossing);
		
		//pinguins Label.
		LB_pinguinsOplossing = new JLabel("Pinguins:");
		LB_pinguinsOplossing.setBounds(212, 50, 60, 20);
		P_oplossing.add(LB_pinguinsOplossing);
		
		//pinguins TextField.
		TF_pinguinsOplossing = new JTextField();
		TF_pinguinsOplossing.setEditable(false);
		TF_pinguinsOplossing.setColumns(10);
		TF_pinguinsOplossing.setBounds(272, 50, 30, 20);
		P_oplossing.add(TF_pinguinsOplossing);
		
		//Aantal keer gegooid Label.
		LB_gegooid = new JLabel("Aantal keer gegooid:");
		LB_gegooid.setBounds(10, 90, 160, 20);
		P_oplossing.add(LB_gegooid);
		
		//Aantal keer gegooid Textfield.
		TF_gegooid = new JTextField();
		TF_gegooid.setText("0");
		TF_gegooid.setEditable(false);
		TF_gegooid.setColumns(10);
		TF_gegooid.setBounds(240, 90, 30, 20);
		P_oplossing.add(TF_gegooid);
		
		//Aantal keer goed geraden Label
		LB_goedGeraden = new JLabel("Aantal keer goed geraden:");
		LB_goedGeraden.setBounds(10, 120, 160, 20);
		P_oplossing.add(LB_goedGeraden);
		
		//Aantal keer goed geraden Textfield.
		TF_goedGeraden = new JTextField();
		TF_goedGeraden.setText("0");
		TF_goedGeraden.setEditable(false);
		TF_goedGeraden.setColumns(10);
		TF_goedGeraden.setBounds(240, 120, 30, 20);
		P_oplossing.add(TF_goedGeraden);
		
		//Aantal keer fout geraden Label.
		LB_foutGeraden = new JLabel("Aantal keer fout geraden");
		LB_foutGeraden.setBounds(10, 150, 160, 20);
		P_oplossing.add(LB_foutGeraden);
		
		//Aantal keer fout geraden Textfield
		TF_foutGeraden = new JTextField();
		TF_foutGeraden.setText("0");
		TF_foutGeraden.setEditable(false);
		TF_foutGeraden.setColumns(10);
		TF_foutGeraden.setBounds(240, 150, 30, 20);
		P_oplossing.add(TF_foutGeraden);
		
		/*
		 * ================================================
		 * Overige Labels en TextFields die niet
		 * in een panel zitten.
		 * ================================================
		 */
		
		LB_welkom = new JLabel("Welkom bij het ijsberenspel");
		LB_welkom .setForeground(Color.WHITE);
		LB_welkom .setFont(new Font("Tahoma", Font.PLAIN, 20));
		LB_welkom .setBounds(159, 10, 260, 30);
		frame.getContentPane().add(LB_welkom );
		
		BTN_nieuwSpel = new JButton("Nieuw Spel");
		BTN_nieuwSpel.setBounds(465, 10, 100, 30);
		frame.getContentPane().add(BTN_nieuwSpel);
		
	}
}
