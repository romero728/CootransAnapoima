package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.plan.DataPlan;

public class GeneratePlan extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	boolean confirm = false;
	String nameCompany, rangeDate;
	ArrayList<ArrayList<String>> alPlan = new ArrayList<>();
	
	@SuppressWarnings("serial")
	public GeneratePlan(String comp) {
		nameCompany = comp;
		
		setTitle("Generar plan");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel() {
			 protected void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            Color color1 = new Color(250, 244, 207);
		            Color color2 = new Color(200, 235, 208);
		            Graphics2D g2d = (Graphics2D) g;
		            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		            GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
		            g2d.setPaint(gp);
		            g2d.fillRect(0, 0, getWidth(), getHeight());
		        }
		};
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo_anapoima.png")).getScaledInstance(48, 48, java.awt.Image.SCALE_AREA_AVERAGING));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lImageLogo = new JLabel();
		JLabel title = new JLabel("GENERAR RODAMIENTO");
		JLabel subtitle = new JLabel("Ingresa el rango de la fecha");
		JLabel lGenerate = new JLabel("Se asignarán los móviles activos a los recorridos correspondientes");
		JLabel lPrint = new JLabel("Podrás ver los resultados generales o de cada móvil en formato Excel");
		JLabel progress = new JLabel("Este proceso durará 20 segundos aprox.");
//		JProgressBar progress = new JProgressBar();
		JTextField tfDate = new JTextField(30);
		JButton btnGenerate = new JButton("Generar plan", new ImageIcon(getClass().getClassLoader().getResource("start.png")));
		JButton btnPrint = new JButton("Generar documento de Excel", new ImageIcon(getClass().getClassLoader().getResource("excel.png")));
		JButton btnBack = new JButton("Atrás", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		lPrint.setVisible(false);
		btnPrint.setVisible(false);
		progress.setVisible(false);
		
//		progress.setIndeterminate(true);
		
		tfDate.setHorizontalAlignment(JTextField.CENTER);
		
		btnGenerate.setPreferredSize(new Dimension(200, 36));
		btnPrint.setPreferredSize(new Dimension(350, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 230, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tfDate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDate, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnGenerate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lGenerate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lGenerate, 360, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnPrint, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnPrint, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lPrint, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPrint, 440, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, progress, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, progress, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(tfDate);
		container.add(btnGenerate);
		container.add(lGenerate);
		container.add(btnPrint);
		container.add(lPrint);
		container.add(progress);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 24));
		subtitle.setForeground(new Color (116, 128, 148));
		
		tfDate.setFont(new Font("Arial", Font.PLAIN, 18));
		tfDate.setForeground(new Color (116, 128, 148));
		
		btnGenerate.setFont(new Font("Arial", Font.BOLD, 20));
		btnGenerate.setForeground(new Color (116, 128, 148));
		btnGenerate.setBackground(new Color(243, 227, 124));
		btnGenerate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lGenerate.setFont(new Font("Arial", Font.BOLD, 12));
		lGenerate.setForeground(new Color (116, 128, 148));
		
		btnPrint.setFont(new Font("Arial", Font.BOLD, 20));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBackground(new Color(136, 212, 152));
		btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lPrint.setFont(new Font("Arial", Font.BOLD, 12));
		lPrint.setForeground(new Color (116, 128, 148));
		
		progress.setFont(new Font("Arial", Font.BOLD, 32));
		progress.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		btnGenerate.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnGenerate.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnGenerate.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnGenerate.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnPrint.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPrint.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPrint.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPrint.setBackground(new Color(109, 186, 163));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/* --- Logic part --- */
		
		btnGenerate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				
				progress.setVisible(true);
				btnPrint.setVisible(false);
				lPrint.setVisible(false);
				
				int ans = JOptionPane.showConfirmDialog(null, "¿Deseas iniciar el proceso?", "Confirmar", JOptionPane.YES_NO_OPTION);
				
				if(ans == JOptionPane.YES_OPTION) {
					if(tfDate.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor ingresa la fecha", "Error", JOptionPane.ERROR_MESSAGE);
						progress.setVisible(false);
						contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					} else {
						rangeDate = tfDate.getText();
						
						do {
							 newPlan();
						} while (confirm == false);
						
						if(confirm) {
							JOptionPane.showMessageDialog(null, "Rodamiento generado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
							lPrint.setVisible(true);
							btnPrint.setVisible(true);
							progress.setVisible(false);
							contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						} else {
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, por favor vuelve a generar el rodamiento", "Error", JOptionPane.ERROR_MESSAGE);
							progress.setVisible(false);
							contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
				} else {
					progress.setVisible(false);
					contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				setVisible(false);
				
				GenerateDocuments genDoc = new GenerateDocuments(nameCompany, rangeDate, alPlan);
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
//			System.out.println("iteration: " + iterations);
			
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
		
		System.out.println("----------");
		System.out.println("All tours: " + sizeTours);
		System.out.println("Plan size: " + alPlan.size());
		System.out.println("First mobile: " + alPlan.get(0).get(0));
		
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
