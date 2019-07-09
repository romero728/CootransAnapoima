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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLDay;
import com.romero278.kernel.connection.SQLRoute;
import com.romero278.kernel.connection.SQLTour;

public class ListTours extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, idTour;
	String[] dataTour;
	String[] split = null;
	
	ArrayList<String> alTours = new ArrayList<String>();
	
	@SuppressWarnings("serial")
	public ListTours(String fg, String op) {
		flag = fg;
		option = op;
		
		setTitle("Listado de " + flag.toLowerCase());
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
		JLabel title = new JLabel("Listado de " + flag.toLowerCase());
		JLabel subtitle = new JLabel("Selecciona el d�a y la ruta para ver los recorridos");
		JLabel lSelectTour1 = new JLabel("Selecciona un " + option.toLowerCase());
		JLabel lSelectTour2 = new JLabel("para ver su informaci�n");
		JLabel lSelectDay = new JLabel("D�a: ");
		JLabel lSelectRoute = new JLabel("Ruta: ");
		JLabel lRoute = new JLabel("Ruta: ");
		JLabel lDay = new JLabel("D�a: ");
		JLabel lHour = new JLabel("Hora: ");
		JTextArea taList = new JTextArea();
		JScrollPane scroll = new JScrollPane(taList);
		JTextField tfRoute = new JTextField(16);
		JTextField tfDay = new JTextField(10);
		JTextField tfHour = new JTextField(6);
		JComboBox<String> cbSelectDay = new JComboBox<String>();
		JComboBox<String> cbSelectRoute = new JComboBox<String>();
		JComboBox<String> cbTour = new JComboBox<String>();
		JButton btnSelectFilter = new JButton("Seleccionar");
		JButton btnSelectRoute = new JButton("Seleccionar");
		JButton btnModify = new JButton("Modificar", new ImageIcon(getClass().getClassLoader().getResource("modify.png")));
		JButton btnDelete = new JButton("Eliminar", new ImageIcon(getClass().getClassLoader().getResource("delete.png")));
		JButton btnBack = new JButton("Atr�s", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		scroll.setVisible(false);
		lSelectTour1.setVisible(false);
		lSelectTour2.setVisible(false);
		cbTour.setVisible(false);
		btnSelectRoute.setVisible(false);
		lRoute.setVisible(false);
		tfRoute.setVisible(false);
		lDay.setVisible(false);
		tfDay.setVisible(false);
		lHour.setVisible(false);
		tfHour.setVisible(false);
		btnModify.setVisible(false);
		btnDelete.setVisible(false);
		
		tfRoute.setEditable(false);
		tfDay.setEditable(false);
		tfHour.setEditable(false);
		
		tfRoute.setBorder(null);
		tfDay.setBorder(null);
		tfHour.setBorder(null);
		
		taList.setEditable(false);
		taList.setLineWrap(true);
		
		scroll.setPreferredSize(new Dimension(150, 280));
		scroll.setBorder(null);
		
		btnModify.setPreferredSize(new Dimension(150, 30));
		btnDelete.setPreferredSize(new Dimension(150, 30));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 120, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lSelectDay, 420, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectDay, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbSelectDay, 460, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbSelectDay, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lSelectRoute, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectRoute, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbSelectRoute, 660, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbSelectRoute, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelectFilter, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelectFilter, 190, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, scroll, 300, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectTour1, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectTour1, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectTour2, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectTour2, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cbTour, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbTour, 360, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelectRoute, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelectRoute, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lRoute, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lRoute, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfRoute, 780, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfRoute, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDay, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDay, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfDay, 770, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDay, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lHour, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lHour, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfHour, 780, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfHour, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModify, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnModify, 420, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 890, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnDelete, 420, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lSelectDay);
		container.add(cbSelectDay);
		container.add(lSelectRoute);
		container.add(cbSelectRoute);
		container.add(btnSelectFilter);
		container.add(scroll);
		container.add(lSelectTour1);
		container.add(lSelectTour2);
		container.add(cbTour);
		container.add(btnSelectRoute);
		container.add(lRoute);
		container.add(tfRoute);
		container.add(lDay);
		container.add(tfDay);
		container.add(lHour);
		container.add(tfHour);
		container.add(btnModify);
		container.add(btnDelete);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 16));
		subtitle.setForeground(new Color (116, 128, 148));
		
		lSelectDay.setFont(new Font("Arial", Font.BOLD, 20));
		lSelectDay.setForeground(new Color (116, 128, 148));
		
		cbSelectDay.setFont(new Font("Arial", Font.PLAIN, 20));
		cbSelectDay.setForeground(new Color (116, 128, 148));
		cbSelectDay.setBackground(new Color(250, 244, 207));
		
		lSelectRoute.setFont(new Font("Arial", Font.BOLD, 20));
		lSelectRoute.setForeground(new Color (116, 128, 148));
		
		cbSelectRoute.setFont(new Font("Arial", Font.PLAIN, 20));
		cbSelectRoute.setForeground(new Color (116, 128, 148));
		cbSelectRoute.setBackground(new Color(250, 244, 207));
		
		btnSelectFilter.setFont(new Font("Arial", Font.BOLD, 20));
		btnSelectFilter.setForeground(new Color(116, 128, 148));
		btnSelectFilter.setBackground(new Color(243, 227, 124));
		btnSelectFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		taList.setFont(new Font("Arial", Font.BOLD, 18));
		taList.setForeground(new Color (116, 128, 148));
		taList.setBackground(new Color (238, 238, 238));
		taList.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		lSelectTour1.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectTour1.setForeground(new Color (116, 128, 148));
		
		lSelectTour2.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectTour2.setForeground(new Color (116, 128, 148));
		
		cbTour.setFont(new Font("Arial", Font.PLAIN, 20));
		cbTour.setForeground(new Color (116, 128, 148));
		cbTour.setBackground(new Color(250, 244, 207));
		
		btnSelectRoute.setFont(new Font("Arial", Font.BOLD, 20));
		btnSelectRoute.setForeground(Color.WHITE);
		btnSelectRoute.setBackground(new Color(136, 212, 152));
		btnSelectRoute.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lRoute.setFont(new Font("Arial", Font.BOLD, 20));
		lRoute.setForeground(new Color (116, 128, 148));
		
		tfRoute.setFont(new Font("Arial", Font.PLAIN, 20));
		tfRoute.setForeground(new Color (116, 128, 148));
		tfRoute.setOpaque(false);
		
		lDay.setFont(new Font("Arial", Font.BOLD, 20));
		lDay.setForeground(new Color (116, 128, 148));
		
		tfDay.setFont(new Font("Arial", Font.PLAIN, 20));
		tfDay.setForeground(new Color (116, 128, 148));
		tfDay.setOpaque(false);
		
		lHour.setFont(new Font("Arial", Font.BOLD, 20));
		lHour.setForeground(new Color (116, 128, 148));
		
		tfHour.setFont(new Font("Arial", Font.PLAIN, 20));
		tfHour.setForeground(new Color (116, 128, 148));
		tfHour.setOpaque(false);
		
		btnModify.setFont(new Font("Arial", Font.BOLD, 20));
		btnModify.setForeground(Color.WHITE);
		btnModify.setBackground(new Color (25, 127, 210));
		btnModify.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnDelete.setFont(new Font("Arial", Font.BOLD, 20));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color (235, 132, 20));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		btnSelectFilter.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelectFilter.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelectFilter.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelectFilter.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnSelectRoute.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelectRoute.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelectRoute.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelectRoute.setBackground(new Color(109, 186, 163));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		btnModify.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnModify.setBackground(new Color (25, 127, 210));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnModify.setBackground(new Color (25, 127, 210));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnModify.setBackground(new Color(35, 81, 152));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		btnDelete.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnDelete.setBackground(new Color (235, 132, 20));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnDelete.setBackground(new Color (235, 132, 20));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnDelete.setBackground(new Color(177, 122, 41));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		/* --- Logic Part --- */
		
		SQLDay day = new SQLDay();
		SQLRoute route = new SQLRoute();
		SQLTour tour = new SQLTour();
		
		ArrayList<String> alIdTours = new ArrayList<>();		
		ArrayList<String> alDays = day.listDays();		
		ArrayList<String[]> alTourDay = new ArrayList<String[]>();
		
		for(int i = 0; i < alDays.size(); i++) {
			split = alDays.get(i).split(Pattern.quote("|"));
			alTourDay.add(split);
			cbSelectDay.addItem(split[1]);
		}
		
		ArrayList<String> alRoutes = route.listRoutes();
		ArrayList<String[]> alTourRoute = new ArrayList<String[]>();
		
		String aux;
		split = null;
		
		for(int i = 0; i < alRoutes.size(); i++) {
			aux = alRoutes.get(i).replace('.', '|');
			split = aux.split(Pattern.quote("|"));
			alTourRoute.add(split);
			cbSelectRoute.addItem(split[1]);
		}
		
		btnSelectFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String itemDay = cbSelectDay.getSelectedItem().toString();
				String itemRoute = cbSelectRoute.getSelectedItem().toString();
				String idDay = "", idRoute = "";
				String[] arAux = null;
				
				for(int i = 0; i < alTourDay.size(); i++) {
					arAux = alTourDay.get(i); 
					
					if(arAux[1].equals(itemDay)) {
						idDay = arAux[0];
					}
				}
				
				arAux = null;
				
				for(int i = 0; i < alTourRoute.size(); i++) {
					arAux = alTourRoute.get(i); 
					
					if(arAux[1].equals(itemRoute)) {
						idRoute = arAux[0];
					}
				}
				
				alTours = tour.listTours(idDay, idRoute);
				
				if(alTours.isEmpty()) {
					btnSelectRoute.setVisible(false);
				} else {
					btnSelectRoute.setVisible(true);
				}
				
				String acumList = "";
				String[] splHour = null;
				cbTour.removeAllItems();
				
				for(int i = 0; i < alTours.size(); i++) {
					split = alTours.get(i).split(Pattern.quote("|"));
					alIdTours.add(split[0]);
					splHour = split[1].split(Pattern.quote(":"));
					cbTour.addItem(splHour[0] + ":" + splHour[1]);
					acumList += splHour[0] + ":" + splHour[1] + "\n";
				}
				
				taList.setText(acumList);
				
				scroll.setVisible(true);
				lSelectTour1.setVisible(true);
				lSelectTour2.setVisible(true);
				cbTour.setVisible(true);
			}
		});
		
		btnSelectRoute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoTour = tour.selectTour(alIdTours.get(cbTour.getSelectedIndex()));
				dataTour = infoTour.split(Pattern.quote("|"));
				
				idTour = cbTour.getSelectedItem().toString();
				
				tfRoute.setText(dataTour[1]);
				tfDay.setText(dataTour[2]);
				
				String[] splHour = null;
				splHour = dataTour[3].split(Pattern.quote(":"));
				tfHour.setText(splHour[0] + ":" + splHour[1]);
				dataTour[3] = tfHour.getText();
				
				lRoute.setVisible(true);
				tfRoute.setVisible(true);
				lDay.setVisible(true);
				tfDay.setVisible(true);
				lHour.setVisible(true);
				tfHour.setVisible(true);
				btnModify.setVisible(true);
				btnDelete.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				ModifyTour mod = new ModifyTour(flag, option, dataTour);
				mod.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "�Deseas eliminar este " + option.toLowerCase() + "?", option + " - Eliminar", JOptionPane.YES_NO_OPTION);
				
				if(answer == JOptionPane.YES_OPTION) {
					System.out.println(idTour);
					
					String request = tour.deleteTour(idTour);
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " eliminado con �xito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
							goBack();
							break;
						case "error delete":
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, int�ntalo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		ConfigOptions conOps = new ConfigOptions(flag, option);
		conOps.setVisible(true);
	}
}
