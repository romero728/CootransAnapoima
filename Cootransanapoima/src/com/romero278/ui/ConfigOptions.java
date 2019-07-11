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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class ConfigOptions extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany;
	
	@SuppressWarnings("serial")
	public ConfigOptions(String flag, String nameComp) {
		nameCompany = nameComp;
		
		String option = "";
		setTitle(flag + " - Opciones");
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
		
		switch(flag) {
			case "Propietarios":
				option = owner(flag);
				break;
			case "Móviles":
				option = mobile(flag);
				break;
			case "Lugares":
				option = place(flag);
				break;
			case "Rutas":
				option = route(flag);
				break;
			case "Recorridos":
				option = tour(flag);
				break;
		}
		
		JLabel lImageLogo = new JLabel();
		JLabel title = new JLabel(flag.toUpperCase());
		JLabel subtitle = new JLabel("Elige una opción");
		JLabel lAdd = new JLabel("Puedes agregar un nuevo " + option.toLowerCase());
		JLabel lList = new JLabel("Puedes ver, modificar o borrar un " + option.toLowerCase());
		JButton btnList = new JButton("Ver todos los " + flag.toLowerCase(), new ImageIcon(getClass().getClassLoader().getResource("list.png")));
		JButton btnAdd = new JButton("Agregar " + option.toLowerCase(), new ImageIcon(getClass().getClassLoader().getResource("add.png")));
		JButton btnBack = new JButton("Atrás", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		btnAdd.setPreferredSize(new Dimension(350, 36));
		btnList.setPreferredSize(new Dimension(350, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 120, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnList, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnList, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lList, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lList, 290, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lAdd, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnList);
		container.add(lList);
		container.add(btnAdd);
		container.add(lAdd);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnList.setFont(new Font("Arial", Font.BOLD, 20));
		btnList.setForeground(new Color (116, 128, 148));
		btnList.setBackground(new Color(243, 227, 124));
		btnList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lList.setFont(new Font("Arial", Font.BOLD, 12));
		lList.setForeground(new Color (116, 128, 148));
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(136, 212, 152));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lAdd.setFont(new Font("Arial", Font.BOLD, 12));
		lAdd.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		btnList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnList.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnList.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnList.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		btnAdd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(109, 186, 163));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/* --- Logic part --- */
		
		final String fOption = option;
		
		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				switch (fOption) {
//					case "Propietario":
//						ListOwners listo = new ListOwners(flag, fOption);
//						listo.setVisible(true);
//						break;
//					case "Móvil":
//						ListMobiles listmob = new ListMobiles(flag, fOption);
//						listmob.setVisible(true);
//						break;
//					case "Lugar":
//						ListPlaces listpl = new ListPlaces(flag, fOption);
//						listpl.setVisible(true);
//						break;
//					case "Ruta":
//						ListRoutes listrou = new ListRoutes(flag, fOption);
//						listrou.setVisible(true);
//						break;
//					case "Recorrido":
//						ListTours listtou = new ListTours(flag, fOption);
//						listtou.setVisible(true);
//						break;
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				switch(fOption) {
//					case "Propietario":
//						AddOwner addo = new AddOwner(flag, fOption);
//						addo.setVisible(true);
//						break;
//					case "Móvil":
//						AddMobile addmo = new AddMobile(flag, fOption);
//						addmo.setVisible(true);
//						break;
//					case "Lugar":
//						AddPlace addpl = new AddPlace(flag, fOption);
//						addpl.setVisible(true);
//						break;
//					case "Ruta":
//						AddRoute addRou = new AddRoute(flag, fOption);
//						addRou.setVisible(true);
//						break;
//					case "Recorrido":
//						AddTour addTour = new AddTour(flag, fOption);
//						addTour.setVisible(true);
//						break;
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});		
	}
	
	String owner(String flag) {		
		return "Propietario";
	}
	
	String mobile(String flag) {
		return "Móvil";
	}
	
	String place(String flag) {
		return "Lugar";
	}
	
	String route(String flag) {
		return "Ruta";
	}
	
	String tour(String flag) {
		return "Recorrido";
	}
	
	void goBack() {
		setVisible(false);
		
		ConfigMenu conMenu = new ConfigMenu(nameCompany);
		conMenu.setVisible(true);
	}
}
