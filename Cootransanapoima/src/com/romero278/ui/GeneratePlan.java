package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.plan.DataPlan;

public class GeneratePlan extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	boolean confirm = false;
	String nameCompany;
	ArrayList<ArrayList<String>> alPlan = new ArrayList<>();
	
	public GeneratePlan(String comp) {
		nameCompany = comp;
		
		setTitle("Generar plan");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("GENERAR RODAMIENTO");
		JLabel subtitle = new JLabel("Elige una opción");
		JButton btnGenerate = new JButton("Generar plan");
		JButton btnPrint = new JButton("Generar documento de Excel");
		JButton btnBack = new JButton("Atrás");
	
		btnPrint.setVisible(false);
		
		btnGenerate.setPreferredSize(new Dimension(200, 36));
		btnPrint.setPreferredSize(new Dimension(350, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnGenerate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnPrint, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnPrint, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnGenerate);
		container.add(btnPrint);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnGenerate.setFont(new Font("Arial", Font.BOLD, 20));
		btnGenerate.setForeground(new Color (116, 128, 148));
		
		btnPrint.setFont(new Font("Arial", Font.BOLD, 20));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBackground(new Color (119, 204, 116));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				do {
					 newPlan();
				} while (confirm == false);
				
				if(confirm) {
					JOptionPane.showMessageDialog(null, "Rodamiento generado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					btnPrint.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error, por favor vuelve a generar el rodamiento", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				setVisible(false);
				
				GenerateDocuments genDoc = new GenerateDocuments(nameCompany, alPlan);
				genDoc.setVisible(true);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
	}
	
	void newPlan() {		
		alPlan = new ArrayList<>();
		
		DataPlan plan = new DataPlan();
		
		ArrayList<ArrayList<String>> alActiveMobiles = plan.getActiveMobiles();
		ArrayList<ArrayList<String>> alActiveMobiles2 = new ArrayList<>();
		ArrayList<ArrayList<String>> alTours = plan.getTours();
		ArrayList<String> alTypeRoute = new ArrayList<>();
		
		HashMap<String, Integer> hashBegin = new HashMap<String, Integer>();
		HashMap<String, Integer> hashFinish = new HashMap<String, Integer>();
		
		for(int i = 0; i < alActiveMobiles.size(); i++) {
			if(alActiveMobiles.get(i).get(1).equals("1")) {
				hashBegin.put(alActiveMobiles.get(i).get(0), 0);
				hashFinish.put(alActiveMobiles.get(i).get(0), 0);
			}
		}
		
		int sizeMobiles = alActiveMobiles.size();
		int sizeMobiles2 = 0;
		int sizeTypeRoute = 0;
		int sizeTours = alTours.size();
		int randomBegin = 0;
		int iterations = 0;
		
		while(sizeTours != alPlan.size()) {
			System.out.println("iteration: " + iterations);
			
			if(iterations > 10) {
				iterations = 0;
				confirm = false;
				break;
			} else {
				randomBegin = (int) (Math.random() * sizeMobiles);
				
				for(int i = 0; i < sizeMobiles; i++) {
					if(i >= randomBegin) {
						alActiveMobiles2.add(alActiveMobiles.get(i));
					}
				}
				
				sizeMobiles2 = alActiveMobiles2.size();
				
				boolean bolBegin, bolFinish;
				
				for(int i = 0; i < sizeTours; i++) {
					alTypeRoute = plan.getTypeRoute(alTours.get(i).get(1));
					sizeTypeRoute = alTypeRoute.size();
					
					if(alActiveMobiles2.isEmpty()) {
						alActiveMobiles2.addAll(alActiveMobiles);
						sizeMobiles2 = alActiveMobiles2.size();
					}
					
					for(int j = 0; j < sizeMobiles2; j++) {
						for(int k = 0; k < sizeTypeRoute; k++) {
							if(alActiveMobiles2.get(j).get(1).equals(alTypeRoute.get(k))) {
								ArrayList<String> current = new ArrayList<>();
								
								if(alTours.get(i).get(3).equals("04:45:00") || alTours.get(i).get(3).equals("21:45:00")) {
									bolBegin = false;
									bolFinish = false;
									
									if(alTours.get(i).get(3).equals("04:45:00")) {
										if(hashBegin.get(alActiveMobiles2.get(j).get(0)) > 0) {
											j++;
											break;
										} else {
											hashBegin.put(alActiveMobiles2.get(j).get(0), Integer.parseInt(alTours.get(i).get(2)));
											
											current.add(alActiveMobiles2.get(j).get(0));
											current.add(alTours.get(i).get(0));
											
											alPlan.add(current);
											
											alActiveMobiles2.remove(j);
											
											k = sizeTypeRoute;
											j = sizeMobiles2;
											
											bolBegin = true;
										}
									}

									if(alTours.get(i).get(3).equals("21:45:00")) {								
										if(hashFinish.get(alActiveMobiles2.get(j).get(0)) > 0) {
											j++;
											break;
										} else {
											if(hashBegin.get(alActiveMobiles2.get(j).get(0)) != Integer.parseInt(alTours.get(i).get(2))) {
												hashFinish.put(alActiveMobiles2.get(j).get(0), Integer.parseInt(alTours.get(i).get(2)));
												
												current.add(alActiveMobiles2.get(j).get(0));
												current.add(alTours.get(i).get(0));
												
												alPlan.add(current);
												
												alActiveMobiles2.remove(j);
												
												k = sizeTypeRoute;
												j = sizeMobiles2;
												
												bolFinish = true;
											} else {
												j++;									
												break;
											}
										}
									}
									
									if(bolBegin == false && bolFinish == false) {
										alActiveMobiles2.addAll(alActiveMobiles);
										i--;
										k = sizeTypeRoute;
										j = sizeMobiles2;
									}
								} else {							
									current.add(alActiveMobiles2.get(j).get(0));
									current.add(alTours.get(i).get(0));
									
									alPlan.add(current);
									
									alActiveMobiles2.remove(j);
									
									k = sizeTypeRoute;
									j = sizeMobiles2;
								}
							} else {
								if(j == sizeMobiles2-1 && k == sizeTypeRoute-1) {
									alActiveMobiles2.addAll(alActiveMobiles);
									i--;
								}
							}
						}
					}
					
					if(alPlan.get(alPlan.size()-1).get(1) != alTours.get(i).get(0)) {
						alActiveMobiles2.addAll(alActiveMobiles);
						i--;
					}
					
					sizeMobiles2 = alActiveMobiles2.size();
					
					if(sizeMobiles2 > 50) {
						i = sizeTours;
					}
				}
			}
			
			iterations++;
		}				
		
//		for(String key:hashBegin.keySet()) {
//			System.out.println("Mobile: " + key + ", day begin: " + hashBegin.get(key));
//			System.out.println("Mobile: " + key + ", day finish: " + hashFinish.get(key));
//		}
		
//		for(int j = 0; j < alPlan.size(); j++) {
//			System.out.println(alPlan.get(j).get(0) + " - " + alPlan.get(j).get(1));
//			
//			switch (alPlan.get(j).get(1)) {
//				case "121":
//					System.out.println("\n ----- Martes ----- \n");
//					break;
//				case "242":
//					System.out.println("\n ----- Miércoles ----- \n");
//					break;
//				case "369":
//					System.out.println("\n ----- Jueves ----- \n");
//					break;
//				case "490":
//					System.out.println("\n ----- Viernes ----- \n");
//					break;
//				case "611":
//					System.out.println("\n ----- Sábado ----- \n");
//					break;
//				case "732":
//					System.out.println("\n ----- Domingo ----- \n");
//					break;
//				default:
//					break;
//			}
//		}
		
//		System.out.println("All tours: " + sizeTours);
//		System.out.println("Plan size: " + alPlan.size());
		
		if(sizeTours == alPlan.size() && alPlan.get(alPlan.size()-1).get(1).equals(alTours.get(alTours.size()-1).get(0))) {
			confirm = true;
		}
	}
	
	void goBack() {
		setVisible(false);
		
		MainMenu menu = new MainMenu(nameCompany);
		menu.setVisible(true);
	}
}
