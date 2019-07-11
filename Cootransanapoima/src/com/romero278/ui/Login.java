package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLLogin;

public class Login extends JFrame {	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("serial")
	public Login() {		
		setTitle("Cootransanapoima");
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo_anapoima.png")));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lImageLogo = new JLabel(); 
		JLabel lInfo1 = new JLabel("Cooperativa integral de transportadores de Anapoima");
		JLabel lInfo2 = new JLabel("COOTRANSANAPOIMA");
		JLabel lInfo3 = new JLabel("RES: Personer�a jur�dica No. 3340 del 04/11/94");
		JLabel lInfo4 = new JLabel("NIT. 808.000.081-8");
		JLabel title = new JLabel("Ingresa los datos para continuar ");
		JLabel lUser = new JLabel("Usuario: ");
		JLabel lPassword = new JLabel("Contrase�a: ");
		JTextField tfUser = new JTextField(20);
		JPasswordField pfPassword = new JPasswordField(20);
		JButton btnNext = new JButton("Ingresar", new ImageIcon(getClass().getClassLoader().getResource("signin.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		lImageLogo.setPreferredSize(new Dimension(200, 200));
		
		btnNext.setPreferredSize(new Dimension(200, 36));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 30, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 240, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lUser, 420, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lUser, 310, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfUser, 545, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfUser, 310, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lPassword, 420, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPassword, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, pfPassword, 545, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, pfPassword, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnNext, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnNext, 390, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lInfo1, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lInfo1, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lInfo2, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lInfo2, 475, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lInfo3, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lInfo3, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lInfo4, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lInfo4, 525, SpringLayout.NORTH, container);
		container.add(title);
		container.add(lImageLogo);
		container.add(lUser);
		container.add(tfUser);
		container.add(lPassword);
		container.add(pfPassword);
		container.add(btnNext);
		container.add(lInfo1);
		container.add(lInfo2);
		container.add(lInfo3);
		container.add(lInfo4);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		lUser.setFont(new Font("Arial", Font.BOLD, 20));
		lUser.setForeground(new Color (116, 128, 148));
		
		lPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lPassword.setForeground(new Color (116, 128, 148));
		
		tfUser.setFont(new Font("Arial", Font.PLAIN, 18));
		tfUser.setForeground(new Color (116, 128, 148));
		
		pfPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		pfPassword.setForeground(new Color (116, 128, 148));
		
		btnNext.setFont(new Font("Arial", Font.BOLD, 18));
		btnNext.setForeground(new Color (116, 128, 148));
		btnNext.setBackground(new Color(243, 227, 124));
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lInfo1.setFont(new Font("Arial", Font.BOLD, 16));
		lInfo1.setForeground(new Color (116, 128, 148));
		
		lInfo2.setFont(new Font("Arial", Font.BOLD, 16));
		lInfo2.setForeground(new Color (116, 128, 148));
		
		lInfo3.setFont(new Font("Arial", Font.BOLD, 16));
		lInfo3.setForeground(new Color (116, 128, 148));
		
		lInfo4.setFont(new Font("Arial", Font.BOLD, 16));
		lInfo4.setForeground(new Color (116, 128, 148));
		
		btnNext.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnNext.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnNext.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnNext.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/* --- Logic Part --- */
		
		btnNext.addActionListener(new ActionListener() {			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfUser.getText().isEmpty() || pfPassword.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					SQLLogin log = new SQLLogin();
					boolean request = log.login(tfUser.getText(), pfPassword.getText());
					
					if(request) {
						setVisible(false);
						
						MainMenu mainMenu = new MainMenu(tfUser.getText());
						mainMenu.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Nombre de la empresa o contrase�a err�nea", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
		});
	}
}