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

import com.romero278.kernel.connection.SQLMobile;

public class ListMobiles extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, idMobile;
	String[] dataMobile;
	
	@SuppressWarnings("serial")
	public ListMobiles(String fg, String op) {
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
		JLabel lSelectMobile1 = new JLabel("Selecciona un " + option.toLowerCase());
		JLabel lSelectMobile2 = new JLabel("para ver su información");
		JLabel lNumber = new JLabel("Número: ");
		JLabel lCompany = new JLabel("Empresa: ");
		JLabel lOwner = new JLabel("Propietario: ");
		JLabel lBrand = new JLabel("Marca: ");
		JLabel lModel = new JLabel("Modelo: ");
		JLabel lLicensePlate = new JLabel("Placa: ");
		JLabel lCapacity = new JLabel("Capacidad: ");
		JLabel lType = new JLabel("Tipo: ");
		JLabel lActive = new JLabel("Activo: ");
		JTextArea taList = new JTextArea();
		JTextField tfNumber = new JTextField(4);
		JTextField tfCompany = new JTextField(12);
		JTextField tfOwner = new JTextField(20);
		JTextField tfBrand = new JTextField(12);
		JTextField tfModel = new JTextField(12);
		JTextField tfLicensePlate = new JTextField(8);
		JTextField tfCapacity = new JTextField(4);
		JTextField tfType = new JTextField(14);
		JTextField tfActive = new JTextField(4);
		JScrollPane scroll = new JScrollPane(taList);
		JComboBox<String> cbMobile = new JComboBox<String>();
		JButton btnSelect = new JButton("Seleccionar");
		JButton btnModify = new JButton("Modificar", new ImageIcon(getClass().getClassLoader().getResource("modify.png")));
		JButton btnDelete = new JButton("Eliminar", new ImageIcon(getClass().getClassLoader().getResource("delete.png")));
		JButton btnBack = new JButton("Atrás", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		tfNumber.setEditable(false);
		tfCompany.setEditable(false);
		tfOwner.setEditable(false);
		tfBrand.setEditable(false);
		tfModel.setEditable(false);
		tfLicensePlate.setEditable(false);
		tfCapacity.setEditable(false);
		tfType.setEditable(false);
		tfActive.setEditable(false);
		
		tfNumber.setBorder(null);
		tfCompany.setBorder(null);
		tfOwner.setBorder(null);
		tfBrand.setBorder(null);
		tfModel.setBorder(null);
		tfLicensePlate.setBorder(null);
		tfCapacity.setBorder(null);
		tfType.setBorder(null);
		tfActive.setBorder(null);
		
		taList.setEditable(false);
		taList.setLineWrap(true);
		
		scroll.setPreferredSize(new Dimension(120, 300));
		scroll.setBorder(null);
		
		lNumber.setVisible(false);
		tfNumber.setVisible(false);
		lCompany.setVisible(false);
		tfCompany.setVisible(false);
		lOwner.setVisible(false);
		tfOwner.setVisible(false);
		lBrand.setVisible(false);
		tfBrand.setVisible(false);
		lModel.setVisible(false);
		tfModel.setVisible(false);
		lLicensePlate.setVisible(false);
		tfLicensePlate.setVisible(false);
		lCapacity.setVisible(false);
		tfCapacity.setVisible(false);
		lType.setVisible(false);
		tfType.setVisible(false);
		lActive.setVisible(false);
		tfActive.setVisible(false);
		btnModify.setVisible(false);
		btnDelete.setVisible(false);
		
		btnModify.setPreferredSize(new Dimension(150, 30));
		btnDelete.setPreferredSize(new Dimension(150, 30));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, scroll, 250, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectMobile1, 500, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectMobile1, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectMobile2, 500, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectMobile2, 270, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cbMobile, 500, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbMobile, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelect, 500, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lNumber, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lNumber, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfNumber, 690, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfNumber, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lCompany, 800, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lCompany, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfCompany, 900, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfCompany, 180, SpringLayout.NORTH, container);
				
		springLayout.putConstraint(SpringLayout.WEST, lOwner, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lOwner, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfOwner, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfOwner, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lBrand, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lBrand, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfBrand, 680, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfBrand, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lModel, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lModel, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfModel, 690, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfModel, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lLicensePlate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lLicensePlate, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfLicensePlate, 670, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfLicensePlate, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lCapacity, 830, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lCapacity, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfCapacity, 950, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfCapacity, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lType, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lType, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfType, 660, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfType, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lActive, 900, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lActive, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfActive, 980, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfActive, 380, SpringLayout.NORTH, container);
				
		springLayout.putConstraint(SpringLayout.WEST, btnModify, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnModify, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 890, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnDelete, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(scroll);
		container.add(lSelectMobile1);
		container.add(lSelectMobile2);
		container.add(cbMobile);
		container.add(btnSelect);
		container.add(lNumber);
		container.add(tfNumber);
		container.add(lCompany);
		container.add(tfCompany);
		container.add(lOwner);
		container.add(tfOwner);
		container.add(lBrand);
		container.add(tfBrand);
		container.add(lModel);
		container.add(tfModel);
		container.add(lLicensePlate);
		container.add(tfLicensePlate);
		container.add(lCapacity);
		container.add(tfCapacity);
		container.add(lType);
		container.add(tfType);
		container.add(lActive);
		container.add(tfActive);
		container.add(btnModify);
		container.add(btnDelete);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		taList.setFont(new Font("Arial", Font.BOLD, 20));
		taList.setForeground(new Color (116, 128, 148));
		taList.setBackground(new Color (238, 238, 238));
		taList.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		lSelectMobile1.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectMobile1.setForeground(new Color (116, 128, 148));
		
		lSelectMobile2.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectMobile2.setForeground(new Color (116, 128, 148));
		
		cbMobile.setFont(new Font("Arial", Font.PLAIN, 20));
		cbMobile.setForeground(new Color (116, 128, 148));
		cbMobile.setBackground(new Color(250, 244, 207));
		
		btnSelect.setFont(new Font("Arial", Font.BOLD, 20));
		btnSelect.setForeground(Color.WHITE);
		btnSelect.setBackground(new Color(136, 212, 152));
		btnSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		
		lNumber.setFont(new Font("Arial", Font.BOLD, 20));
		lNumber.setForeground(new Color (116, 128, 148));
		
		tfNumber.setFont(new Font("Arial", Font.PLAIN, 20));
		tfNumber.setForeground(new Color (116, 128, 148));
		tfNumber.setOpaque(false);
		
		lCompany.setFont(new Font("Arial", Font.BOLD, 20));
		lCompany.setForeground(new Color (116, 128, 148));
		
		tfCompany.setFont(new Font("Arial", Font.PLAIN, 20));
		tfCompany.setForeground(new Color (116, 128, 148));
		tfCompany.setOpaque(false);
		
		lOwner.setFont(new Font("Arial", Font.BOLD, 20));
		lOwner.setForeground(new Color (116, 128, 148));
		
		tfOwner.setFont(new Font("Arial", Font.PLAIN, 20));
		tfOwner.setForeground(new Color (116, 128, 148));
		tfOwner.setOpaque(false);
		
		lBrand.setFont(new Font("Arial", Font.BOLD, 20));
		lBrand.setForeground(new Color (116, 128, 148));
		
		tfBrand.setFont(new Font("Arial", Font.PLAIN, 20));
		tfBrand.setForeground(new Color (116, 128, 148));
		tfBrand.setOpaque(false);
		
		lModel.setFont(new Font("Arial", Font.BOLD, 20));
		lModel.setForeground(new Color (116, 128, 148));
		
		tfModel.setFont(new Font("Arial", Font.PLAIN, 20));
		tfModel.setForeground(new Color (116, 128, 148));
		tfModel.setOpaque(false);
		
		lLicensePlate.setFont(new Font("Arial", Font.BOLD, 20));
		lLicensePlate.setForeground(new Color (116, 128, 148));
		
		tfLicensePlate.setFont(new Font("Arial", Font.PLAIN, 20));
		tfLicensePlate.setForeground(new Color (116, 128, 148));
		tfLicensePlate.setOpaque(false);
		
		lCapacity.setFont(new Font("Arial", Font.BOLD, 20));
		lCapacity.setForeground(new Color (116, 128, 148));
		
		tfCapacity.setFont(new Font("Arial", Font.PLAIN, 20));
		tfCapacity.setForeground(new Color (116, 128, 148));
		tfCapacity.setOpaque(false);
		
		lType.setFont(new Font("Arial", Font.BOLD, 20));
		lType.setForeground(new Color (116, 128, 148));
		
		tfType.setFont(new Font("Arial", Font.PLAIN, 20));
		tfType.setForeground(new Color (116, 128, 148));
		tfType.setOpaque(false);
		
		lActive.setFont(new Font("Arial", Font.BOLD, 20));
		lActive.setForeground(new Color (116, 128, 148));
		
		tfActive.setFont(new Font("Arial", Font.PLAIN, 20));
		tfActive.setForeground(new Color (116, 128, 148));
		tfActive.setOpaque(false);
		
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
		
		SQLMobile mob = new SQLMobile();
		ArrayList<String> alMobiles = new ArrayList<String>();
		String acumList = "";
		
		alMobiles = mob.listMobiles(); 
		
		for(int i = 0; i < alMobiles.size(); i++) {
			cbMobile.addItem(alMobiles.get(i));
			acumList += alMobiles.get(i) + "\n";
		}
		
		taList.setText(acumList);
		
		if(alMobiles.isEmpty()) {
			btnSelect.setVisible(false);
		} else {
			btnSelect.setVisible(true);
		}
		
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoMobile = mob.selectMobile(cbMobile.getSelectedItem().toString());
				dataMobile = infoMobile.split(Pattern.quote("|"));
				
				idMobile = dataMobile[0];
				tfNumber.setText(dataMobile[1]);
				tfCompany.setText(dataMobile[2]);
				tfOwner.setText(dataMobile[3]);
				tfBrand.setText(dataMobile[4]);
				tfModel.setText(dataMobile[5]);
				tfLicensePlate.setText(dataMobile[6]);
				tfCapacity.setText(dataMobile[7]);
				tfType.setText(dataMobile[8]);
				tfActive.setText(dataMobile[9]);
				
				lNumber.setVisible(true);
				tfNumber.setVisible(true);
				lCompany.setVisible(true);
				tfCompany.setVisible(true);
				lOwner.setVisible(true);
				tfOwner.setVisible(true);
				lBrand.setVisible(true);
				tfBrand.setVisible(true);
				lModel.setVisible(true);
				tfModel.setVisible(true);
				lLicensePlate.setVisible(true);
				tfLicensePlate.setVisible(true);
				lCapacity.setVisible(true);
				tfCapacity.setVisible(true);
				lType.setVisible(true);
				tfType.setVisible(true);
				lActive.setVisible(true);
				tfActive.setVisible(true);
				btnModify.setVisible(true);
				btnDelete.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				ModifyMobile modMob = new ModifyMobile(flag, option, dataMobile);
				modMob.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar este " + option.toLowerCase() + "?", option + " - Eliminar", JOptionPane.YES_NO_OPTION);
				
				if(answer == JOptionPane.YES_OPTION) {							
					String request = mob.deleteMobile(dataMobile[0]);
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " eliminado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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
		
		ConfigOptions conOps = new ConfigOptions(flag, option);
		conOps.setVisible(true);
	}
}
