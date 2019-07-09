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

public class ConfigMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany;
	
	@SuppressWarnings("serial")
	public ConfigMenu(String nameComp) {
		nameCompany = nameComp; 
		
		setTitle("Configuración");
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo_anapoima.png").getScaledInstance(48, 48, java.awt.Image.SCALE_AREA_AVERAGING));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lImageLogo = new JLabel();
		JLabel title = new JLabel("CONFIGURACIÓN");
		JLabel subtitle = new JLabel("Elige una opción");
		JLabel lOwners = new JLabel("Propietarios de los móviles");
		JLabel lMobile = new JLabel("Móviles que pertenecen a la empresa");
		JLabel lPlaces = new JLabel("Lugares a los que se accede por medio de las rutas");
		JLabel lRoutes = new JLabel("Rutas que realizan los móviles");
		JLabel lTours = new JLabel("Día y hora en el que se realizan las rutas");
		JButton btnOwners = new JButton("Propietarios", new ImageIcon("img/owner.png"));
		JButton btnMobile = new JButton("Móviles", new ImageIcon("img/mobile.png"));
		JButton btnPlaces = new JButton("Lugares", new ImageIcon("img/place.png"));
		JButton btnRoutes = new JButton("Rutas", new ImageIcon("img/route.png"));
		JButton btnTours = new JButton("Recorridos", new ImageIcon("img/tour.png"));
		JButton btnBack = new JButton("Atrás", new ImageIcon("img/back.png"));
		
		ImageIcon logo = new ImageIcon(new ImageIcon("img/logo_anapoima.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		btnOwners.setPreferredSize(new Dimension(200, 36));
//		btnOwners.setPreferredSize(new Dimension(180, 90));
		btnMobile.setPreferredSize(new Dimension(200, 36));
		btnPlaces.setPreferredSize(new Dimension(200, 36));
		btnRoutes.setPreferredSize(new Dimension(200, 36));
		btnTours.setPreferredSize(new Dimension(200, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnOwners, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnOwners, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lOwners, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lOwners, 240, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnMobile, 450, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnMobile, 280, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lMobile, 450, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lMobile, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnPlaces, 750, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnPlaces, 280, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lPlaces, 750, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPlaces, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnRoutes, 450, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnRoutes, 360, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lRoutes, 450, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lRoutes, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnTours, 750, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnTours, 360, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lTours, 750, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lTours, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnOwners);
		container.add(lOwners);
		container.add(btnMobile);
		container.add(lMobile);
		container.add(btnPlaces);
		container.add(lPlaces);
		container.add(btnRoutes);
		container.add(lRoutes);
		container.add(btnTours);
		container.add(lTours);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnOwners.setFont(new Font("Arial", Font.BOLD, 20));
		btnOwners.setForeground(new Color (116, 128, 148));
		btnOwners.setBackground(new Color(243, 227, 124));
		btnOwners.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnOwners.setVerticalTextPosition(JButton.BOTTOM);
//		btnOwners.setHorizontalTextPosition(JButton.CENTER);
		
		lOwners.setFont(new Font("Arial", Font.BOLD, 12));
		lOwners.setForeground(new Color (116, 128, 148));
		
		btnMobile.setFont(new Font("Arial", Font.BOLD, 20));
		btnMobile.setForeground(new Color (116, 128, 148));
		btnMobile.setBackground(new Color(243, 227, 124));
		btnMobile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lMobile.setFont(new Font("Arial", Font.BOLD, 12));
		lMobile.setForeground(new Color (116, 128, 148));
		
		btnPlaces.setFont(new Font("Arial", Font.BOLD, 20));
		btnPlaces.setForeground(new Color (116, 128, 148));
		btnPlaces.setBackground(new Color(243, 227, 124));
		btnPlaces.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lPlaces.setFont(new Font("Arial", Font.BOLD, 12));
		lPlaces.setForeground(new Color (116, 128, 148));
		
		btnRoutes.setFont(new Font("Arial", Font.BOLD, 20));
		btnRoutes.setForeground(new Color (116, 128, 148));
		btnRoutes.setBackground(new Color(243, 227, 124));
		btnRoutes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lRoutes.setFont(new Font("Arial", Font.BOLD, 12));
		lRoutes.setForeground(new Color (116, 128, 148));
		
		btnTours.setFont(new Font("Arial", Font.BOLD, 20));
		btnTours.setForeground(new Color (116, 128, 148));
		btnTours.setBackground(new Color(243, 227, 124));
		btnTours.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lTours.setFont(new Font("Arial", Font.BOLD, 12));
		lTours.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		btnOwners.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnOwners.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnOwners.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnOwners.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnMobile.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnMobile.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnMobile.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnMobile.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnPlaces.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPlaces.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPlaces.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPlaces.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		btnRoutes.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnRoutes.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnRoutes.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnRoutes.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnTours.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnTours.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnTours.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnTours.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/* --- Logic part --- */
		
		btnOwners.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				goToOptions("Propietarios");
			}
		});
		
		btnMobile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goToOptions("Móviles");
			}
		});
		
		btnPlaces.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goToOptions("Lugares");
			}
		});
		
		btnRoutes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goToOptions("Rutas");
			}
		});
		
		btnTours.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goToOptions("Recorridos");
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
	}
	
	void goToOptions(String flag) {
		setVisible(false);
		
		ConfigOptions options = new ConfigOptions(flag, nameCompany);
		options.setVisible(true);
	}
	
	void goBack() {
		setVisible(false);
		
		MainMenu menu = new MainMenu(nameCompany);
		menu.setVisible(true);
	}
}
