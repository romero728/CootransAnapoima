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

import com.romero278.kernel.connection.SQLRoute;
import com.romero278.kernel.connection.SQLTypeVehicle;

public class ListRoutes extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany, idRoute;
	String[] dataRoute;
	
	@SuppressWarnings("serial")
	public ListRoutes(String nameComp) {
		nameCompany = nameComp;
		
		setTitle("Listado de rutas");
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
		JLabel title = new JLabel("Listado de rutas");
		JLabel lSelectRoute1 = new JLabel("Selecciona una ruta");
		JLabel lSelectRoute2 = new JLabel("para ver su información");
		JLabel lPlaceEnd = new JLabel("Destino: ");
		JLabel lDuration = new JLabel("Duración: ");
		JLabel lActive = new JLabel("Activo: ");
		JLabel lType = new JLabel("Tipo: ");
		JTextField tfPlaceEnd = new JTextField(18);
		JTextField tfDuration = new JTextField(6);
		JTextField tfActive = new JTextField(2);
		JTextArea taList = new JTextArea();
		JTextArea taType = new JTextArea();
		JScrollPane scroll = new JScrollPane(taList);
		JScrollPane scrollType = new JScrollPane(taType);
		JComboBox<String> cbRoute = new JComboBox<String>();
		JButton btnSelect = new JButton("Seleccionar");
		JButton btnAdd = new JButton("Agregar ruta", new ImageIcon(getClass().getClassLoader().getResource("add-dark.png")));
		JButton btnModify = new JButton("Modificar", new ImageIcon(getClass().getClassLoader().getResource("modify.png")));
		JButton btnDelete = new JButton("Eliminar", new ImageIcon(getClass().getClassLoader().getResource("delete.png")));
		JButton btnBack = new JButton("Atrás", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		tfPlaceEnd.setEditable(false);
		tfDuration.setEditable(false);
		tfActive.setEditable(false);
		taType.setEditable(false);
		
		tfPlaceEnd.setBorder(null);
		tfDuration.setBorder(null);
		tfActive.setBorder(null);
		
		taList.setEditable(false);
		taList.setLineWrap(true);
		
		scroll.setPreferredSize(new Dimension(320, 240));
//		scroll.setBorder(null);
		
		scrollType.setPreferredSize(new Dimension(100, 60));
		scrollType.setBorder(null);
		
		cbRoute.setPreferredSize(new Dimension(250, 30));
		
		lPlaceEnd.setVisible(false);
		tfPlaceEnd.setVisible(false);
		lDuration.setVisible(false);
		tfDuration.setVisible(false);
		lActive.setVisible(false);
		tfActive.setVisible(false);
		lType.setVisible(false);
		taType.setVisible(false);
		btnModify.setVisible(false);
		btnDelete.setVisible(false);
		
		btnAdd.setPreferredSize(new Dimension(250, 36));
		btnModify.setPreferredSize(new Dimension(150, 30));
		btnDelete.setPreferredSize(new Dimension(150, 30));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, scroll, 130, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 175, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 430, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectRoute1, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectRoute1, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectRoute2, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectRoute2, 270, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cbRoute, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbRoute, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelect, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lPlaceEnd, 740, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPlaceEnd, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfPlaceEnd, 830, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfPlaceEnd, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDuration, 740, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDuration, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfDuration, 840, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDuration, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lActive, 740, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lActive, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfActive, 820, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfActive, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lType, 740, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lType, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, taType, 820, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, taType, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModify, 740, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnModify, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 910, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnDelete, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(scroll);
		container.add(btnAdd);
		container.add(lSelectRoute1);
		container.add(lSelectRoute2);
		container.add(cbRoute);
		container.add(btnSelect);
		container.add(lPlaceEnd);
		container.add(tfPlaceEnd);
		container.add(lDuration);
		container.add(tfDuration);
		container.add(lActive);
		container.add(tfActive);
		container.add(lType);
		container.add(taType);
		container.add(btnModify);
		container.add(btnDelete);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		taList.setFont(new Font("Arial", Font.BOLD, 18));
		taList.setForeground(new Color (116, 128, 148));
		taList.setBackground(new Color (238, 238, 238));
		taList.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(new Color (116, 128, 148));
		btnAdd.setBackground(new Color(243, 227, 124));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lSelectRoute1.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectRoute1.setForeground(new Color (116, 128, 148));
		
		lSelectRoute2.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectRoute2.setForeground(new Color (116, 128, 148));
		
		cbRoute.setFont(new Font("Arial", Font.PLAIN, 20));
		cbRoute.setForeground(new Color (116, 128, 148));
		cbRoute.setBackground(new Color(250, 244, 207));
		
		btnSelect.setFont(new Font("Arial", Font.BOLD, 20));
		btnSelect.setForeground(Color.WHITE);
		btnSelect.setBackground(new Color(136, 212, 152));
		btnSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lPlaceEnd.setFont(new Font("Arial", Font.BOLD, 20));
		lPlaceEnd.setForeground(new Color (116, 128, 148));
		
		tfPlaceEnd.setFont(new Font("Arial", Font.PLAIN, 20));
		tfPlaceEnd.setForeground(new Color (116, 128, 148));
		tfPlaceEnd.setOpaque(false);
		
		lDuration.setFont(new Font("Arial", Font.BOLD, 20));
		lDuration.setForeground(new Color (116, 128, 148));
		
		tfDuration.setFont(new Font("Arial", Font.PLAIN, 20));
		tfDuration.setForeground(new Color (116, 128, 148));
		tfDuration.setOpaque(false);
		
		lActive.setFont(new Font("Arial", Font.BOLD, 20));
		lActive.setForeground(new Color (116, 128, 148));
		
		tfActive.setFont(new Font("Arial", Font.PLAIN, 20));
		tfActive.setForeground(new Color (116, 128, 148));
		tfActive.setOpaque(false);
		
		lType.setFont(new Font("Arial", Font.BOLD, 20));
		lType.setForeground(new Color (116, 128, 148));
		
		taType.setFont(new Font("Arial", Font.PLAIN, 20));
		taType.setForeground(new Color (116, 128, 148));
		taType.setBackground(new Color (238, 238, 238));
		taType.setOpaque(false);
		scrollType.setOpaque(false);
		scrollType.getViewport().setOpaque(false);
		
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
		
		btnAdd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		btnSelect.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelect.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelect.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSelect.setBackground(new Color(109, 186, 163));
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
		
		SQLRoute route = new SQLRoute();
		SQLTypeVehicle typeVehicle = new SQLTypeVehicle();
		ArrayList<String> alRoutes = new ArrayList<String>();
		ArrayList<String> alIdRoutes = new ArrayList<String>();
		String acumList = "", replaceRoute = "";
		String[] split;
		
		alRoutes = route.listRoutes();
		
		for(int i = 0; i < alRoutes.size(); i++) {
			replaceRoute = alRoutes.get(i).replace('.', '|');
			split = replaceRoute.split(Pattern.quote("|"));
			alIdRoutes.add(split[0]);
			cbRoute.addItem(split[1]);
			acumList += "Anapoima - " + split[1] + "\n";
		}
		
		taList.setText(acumList);
		
		if(alRoutes.isEmpty()) {
			btnSelect.setVisible(false);
		} else {
			btnSelect.setVisible(true);
		}
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AddRoute addr = new AddRoute(nameCompany);
				addr.setVisible(true);
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoRoute = route.selectRoute(alIdRoutes.get(cbRoute.getSelectedIndex()));
				dataRoute = infoRoute.split(Pattern.quote("|"));
				
				idRoute = dataRoute[0];
				tfPlaceEnd.setText(dataRoute[2]);
				tfDuration.setText(dataRoute[3]);
				tfActive.setText(dataRoute[4]);
				
				String[] types = route.getTypeVehicle(idRoute);
				String acumTypes = "";
				
				if(types.length == 3) {
					taType.setText("Todos");
				} else {
					for(int i = 0; i < types.length; i++) {
						acumTypes += typeVehicle.getNameTypeVehicle(types[i]) + " ";
					}
					
					taType.setText(acumTypes);
				}
				
				lPlaceEnd.setVisible(true);
				tfPlaceEnd.setVisible(true);
				lDuration.setVisible(true);
				tfDuration.setVisible(true);
				lActive.setVisible(true);
				tfActive.setVisible(true);
				lType.setVisible(true);
				taType.setVisible(true);
				btnModify.setVisible(true);
				btnDelete.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				ModifyRoute mod = new ModifyRoute(nameCompany, dataRoute);
				mod.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar esta ruta? Se eliminará el recorrido que esté asociado", "Ruta - Eliminar", JOptionPane.YES_NO_OPTION);
				
				if(answer == JOptionPane.YES_OPTION) {
					String request = route.deleteRoute(idRoute);
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, "Ruta eliminada con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
							goBack();
							break;
						case "error delete":
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
		ConfigMenu conMenu = new ConfigMenu(nameCompany);
		conMenu.setVisible(true);
//		ConfigOptions conOps = new ConfigOptions(flag, option);
//		conOps.setVisible(true);
	}
}
