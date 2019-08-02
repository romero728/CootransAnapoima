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

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany;
	
	@SuppressWarnings("serial")
	public MainMenu(String nameComp) {
		nameCompany = nameComp;
		
		setTitle("Menú principal");
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo_cootransanapoima.png")).getScaledInstance(48, 48, java.awt.Image.SCALE_AREA_AVERAGING));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lImageLogo = new JLabel(); 
		JLabel title = new JLabel(nameCompany.toUpperCase());
		JLabel subtitle = new JLabel("Elige una opción");
		JLabel lGenerate = new JLabel("Puedes generar el plan de rodamiento semanal");
		JLabel lEdit = new JLabel("Puedes configurar las rutas, los móviles, entre otros aspectos");
		JButton btnGenerate = new JButton("Generar plan", new ImageIcon(getClass().getClassLoader().getResource("start.png")));
		JButton btnEdit = new JButton("Configuración", new ImageIcon(getClass().getClassLoader().getResource("config.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_cootransanapoima.png")).getImage().getScaledInstance(70, 60, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		lImageLogo.setPreferredSize(new Dimension(70, 60));
		
		btnGenerate.setPreferredSize(new Dimension(200, 36));
		btnEdit.setPreferredSize(new Dimension(200, 36));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnGenerate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate, 280, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lGenerate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lGenerate, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnEdit, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnEdit, 370, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lEdit, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEdit, 410, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lGenerate);
		container.add(lEdit);
		container.add(btnGenerate);
		container.add(btnEdit);
		container.add(lImageLogo);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnGenerate.setFont(new Font("Arial", Font.BOLD, 20));
		btnGenerate.setForeground(new Color (116, 128, 148));
		btnGenerate.setBackground(new Color(243, 227, 124));
		btnGenerate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		
		lGenerate.setFont(new Font("Arial", Font.BOLD, 12));
		lGenerate.setForeground(new Color (116, 128, 148));
		
		btnEdit.setFont(new Font("Arial", Font.BOLD, 20));
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBackground(new Color(136, 212, 152));
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lEdit.setFont(new Font("Arial", Font.BOLD, 12));
		lEdit.setForeground(new Color (116, 128, 148));
		
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
		
		btnEdit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnEdit.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnEdit.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnEdit.setBackground(new Color(109, 186, 163));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/* --- Logic Part --- */
		
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnGenerate.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));				
				setVisible(false);
				
				GeneratePlan plan = new GeneratePlan(nameCompany);
				plan.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				ConfigMenu config = new ConfigMenu(nameCompany);
				config.setVisible(true);
			}
		});
	}

}
