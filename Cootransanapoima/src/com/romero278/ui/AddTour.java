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
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLDay;
import com.romero278.kernel.connection.SQLRoute;
import com.romero278.kernel.connection.SQLTour;

public class AddTour extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany;
	
	@SuppressWarnings("serial")
	public AddTour(String nameComp) {
		nameCompany = nameComp;
		
		setTitle("Recorrido - Agregar");
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
		JLabel title = new JLabel("Agregar recorrido");
		JLabel subtitle = new JLabel("Ingresa los datos");
		JLabel lRoute = new JLabel("Ruta: ");
		JLabel lDay = new JLabel("Día: ");
		JLabel lHour = new JLabel("Hora: ");
		JLabel lHour2 = new JLabel("(hh:mm)");
		JTextField tfHour = new JTextField(6);
		JComboBox<String> cbRoute = new JComboBox<String>();
		JComboBox<String> cbDay = new JComboBox<String>();
		JButton btnAdd = new JButton("Agregar", new ImageIcon(getClass().getClassLoader().getResource("add.png")));
		JButton btnBack = new JButton("Atrás", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		btnAdd.setPreferredSize(new Dimension(200, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lRoute, 520, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lRoute, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbRoute, 580, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbRoute, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDay, 420, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDay, 310, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbDay, 470, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbDay, 310, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lHour, 660, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lHour, 310, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfHour, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfHour, 310, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lHour2, 735, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lHour2, 290, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lRoute);
		container.add(cbRoute);
		container.add(lDay);
		container.add(cbDay);
		container.add(lHour);
		container.add(tfHour);
		container.add(lHour2);
		container.add(btnAdd);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		lRoute.setFont(new Font("Arial", Font.BOLD, 20));
		lRoute.setForeground(new Color (116, 128, 148));
		
		cbRoute.setFont(new Font("Arial", Font.PLAIN, 18));
		cbRoute.setForeground(new Color (116, 128, 148));
		cbRoute.setBackground(new Color(250, 244, 207));
		
		lDay.setFont(new Font("Arial", Font.BOLD, 20));
		lDay.setForeground(new Color (116, 128, 148));
		
		cbDay.setFont(new Font("Arial", Font.PLAIN, 18));
		cbDay.setForeground(new Color (116, 128, 148));
		cbDay.setBackground(new Color(250, 244, 207));
		
		lHour.setFont(new Font("Arial", Font.BOLD, 20));
		lHour.setForeground(new Color (116, 128, 148));
		
		tfHour.setFont(new Font("Arial", Font.PLAIN, 18));
		tfHour.setForeground(new Color (116, 128, 148));
		
		lHour2.setFont(new Font("Arial", Font.BOLD, 14));
		lHour2.setForeground(new Color (116, 128, 148));
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(136, 212, 152));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
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
		
		SQLTour tour = new SQLTour();
		SQLRoute route = new SQLRoute();
		SQLDay day = new SQLDay();
		
		ArrayList<String> alRoutes = route.listRoutes();
		ArrayList<String[]> alTourRoute = new ArrayList<String[]>();
		
		String aux;
		String[] split = null;
		
		for(int i = 0; i < alRoutes.size(); i++) {
			aux = alRoutes.get(i).replace('.', '|');
			split = aux.split(Pattern.quote("|"));
			alTourRoute.add(split);
			cbRoute.addItem(split[1]);
		}
		
		ArrayList<String> alDays = day.listDays();
		ArrayList<String[]> alTourDay = new ArrayList<String[]>();		
		split = null;
		
		for(int i = 0; i < alDays.size(); i++) {
			split = alDays.get(i).split(Pattern.quote("|"));
			alTourDay.add(split);
			cbDay.addItem(split[1]);
		}
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfHour.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String request;
					
					String route = cbRoute.getSelectedItem().toString();
					String idRoute = "";
					String[] arAux = null;
					
					for(int i = 0; i < alTourRoute.size(); i++) {
						arAux = alTourRoute.get(i); 
						
						if(arAux[1].equals(route)) {
							idRoute = arAux[0];
						}
					}
					
					String day = cbDay.getSelectedItem().toString();
					String idDay = "";
					arAux = null;
					
					for(int i = 0; i < alTourDay.size(); i++) {
						arAux = alTourDay.get(i); 
						
						if(arAux[1].equals(day)) {
							idDay = arAux[0];
						}
					}
					
					request = tour.insertTour(idRoute, idDay, tfHour.getText());
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, "Recorrido agregado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
							goBack();
							break;
						case "equals":
							JOptionPane.showMessageDialog(null, "El recorrido ya ha sido registrado previamente", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case "error insert":
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, inténtalo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
							break;
					}
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
	
	void goBack() {
		setVisible(false);
		ListTours listt = new ListTours(nameCompany);
		listt.setVisible(true);
//		ConfigOptions conOps = new ConfigOptions(flag, option);
//		conOps.setVisible(true);
	}
}
