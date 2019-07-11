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
import java.text.ParseException;
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

import com.romero278.kernel.connection.SQLOwner;

public class ListOwners extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany, idOwner, completeNameOwner, documentOwner, birthdayOwner, addressOwner, cityOwner, phoneOwner, emailOwner;
	String[] dataOwner;
	
	@SuppressWarnings("serial")
	public ListOwners(String nameComp) {		
		setTitle("Listado de propietarios");
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
		JLabel title = new JLabel("Listado de propietarios");
		JLabel lSelectOwner1 = new JLabel("Selecciona un propietario");
		JLabel lSelectOwner2 = new JLabel("para ver su información");
		JLabel lName = new JLabel("Nombre:");
		JLabel lDocument = new JLabel("Documento:");
		JLabel lBirthdate = new JLabel("Fecha de nacimiento:");
		JLabel lAddress = new JLabel("Dirección:");
		JLabel lCity = new JLabel("Ciudad:");
		JLabel lPhone = new JLabel("Teléfono:");
		JLabel lEmail = new JLabel("Correo:");
		JTextField tfName = new JTextField(23);
		JTextField tfDocument = new JTextField(15);
		JTextField tfBirthdate = new JTextField(10);
		JTextField tfAddress = new JTextField(18);
		JTextField tfCity = new JTextField(15);
		JTextField tfPhone = new JTextField(15);
		JTextField tfEmail = new JTextField(20);
		JTextArea taList = new JTextArea();
		JScrollPane scroll = new JScrollPane(taList);
		JComboBox<String> cbOwner = new JComboBox<String>();
		JButton btnSelect = new JButton("Seleccionar");
		JButton btnAdd = new JButton("Agregar propietario", new ImageIcon(getClass().getClassLoader().getResource("add-dark.png")));
		JButton btnModify = new JButton("Modificar", new ImageIcon(getClass().getClassLoader().getResource("modify.png")));
		JButton btnDelete = new JButton("Eliminar", new ImageIcon(getClass().getClassLoader().getResource("delete.png")));
		JButton btnBack = new JButton("Atrás", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		tfName.setEditable(false);
		tfDocument.setEditable(false);
		tfBirthdate.setEditable(false);
		tfAddress.setEditable(false);
		tfCity.setEditable(false);
		tfPhone.setEditable(false);
		tfEmail.setEditable(false);
		
		taList.setEditable(false);
		taList.setLineWrap(true);
		
		scroll.setPreferredSize(new Dimension(350, 240));
//		scroll.setBorder(null);
		
		lName.setVisible(false);
		lDocument.setVisible(false);
		lBirthdate.setVisible(false);
		lAddress.setVisible(false);
		lCity.setVisible(false);
		lPhone.setVisible(false);
		lEmail.setVisible(false);
		tfName.setVisible(false);
		tfDocument.setVisible(false);
		tfBirthdate.setVisible(false);
		tfAddress.setVisible(false);
		tfCity.setVisible(false);
		tfPhone.setVisible(false);
		tfEmail.setVisible(false);
		btnModify.setVisible(false);
		btnDelete.setVisible(false);
		
		tfName.setBorder(null);
		tfDocument.setBorder(null);
		tfBirthdate.setBorder(null);
		tfAddress.setBorder(null);
		tfCity.setBorder(null);
		tfPhone.setBorder(null);
		tfEmail.setBorder(null);
		
		cbOwner.setPreferredSize(new Dimension(250, 30));
		btnAdd.setPreferredSize(new Dimension(250, 36));
		btnModify.setPreferredSize(new Dimension(150, 30));
		btnDelete.setPreferredSize(new Dimension(150, 30));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, scroll, 80, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 125, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 430, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectOwner1, 580, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectOwner1, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectOwner2, 580, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectOwner2, 270, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cbOwner, 580, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbOwner, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelect, 580, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lName, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lName, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfName, 810, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfName, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDocument, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDocument, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfDocument, 840, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDocument, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lBirthdate, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lBirthdate, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfBirthdate, 930, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfBirthdate, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lAddress, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lAddress, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfAddress, 820, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfAddress, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lCity, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lCity, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfCity, 800, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfCity, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lPhone, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPhone, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfPhone, 815, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfPhone, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lEmail, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEmail, 420, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfEmail, 800, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfEmail, 420, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModify, 740, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnModify, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 910, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnDelete, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(scroll);
		container.add(btnAdd);
		container.add(lSelectOwner1);
		container.add(lSelectOwner2);
		container.add(cbOwner);
		container.add(btnSelect);
		container.add(lName);
		container.add(tfName);
		container.add(lDocument);
		container.add(tfDocument);
		container.add(lBirthdate);
		container.add(tfBirthdate);
		container.add(lAddress);
		container.add(tfAddress);
		container.add(lCity);
		container.add(tfCity);
		container.add(lPhone);
		container.add(tfPhone);
		container.add(lEmail);
		container.add(tfEmail);
		container.add(btnModify);
		container.add(btnDelete);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		taList.setFont(new Font("Arial", Font.BOLD, 16));
		taList.setForeground(new Color (116, 128, 148));
		taList.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(new Color (116, 128, 148));
		btnAdd.setBackground(new Color(243, 227, 124));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lSelectOwner1.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectOwner1.setForeground(new Color (116, 128, 148));
		
		lSelectOwner2.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectOwner2.setForeground(new Color (116, 128, 148));
		
		cbOwner.setFont(new Font("Arial", Font.PLAIN, 20));
		cbOwner.setForeground(new Color (116, 128, 148));
		cbOwner.setBackground(new Color(250, 244, 207));
		
		btnSelect.setFont(new Font("Arial", Font.BOLD, 20));
		btnSelect.setForeground(Color.WHITE);
		btnSelect.setBackground(new Color(136, 212, 152));
		btnSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lName.setFont(new Font("Arial", Font.BOLD, 20));
		lName.setForeground(new Color (116, 128, 148));
		
		tfName.setFont(new Font("Arial", Font.PLAIN, 20));
		tfName.setForeground(new Color (116, 128, 148));
		tfName.setOpaque(false);
		
		lDocument.setFont(new Font("Arial", Font.BOLD, 20));
		lDocument.setForeground(new Color (116, 128, 148));
		
		tfDocument.setFont(new Font("Arial", Font.PLAIN, 20));
		tfDocument.setForeground(new Color (116, 128, 148));
		tfDocument.setOpaque(false);
		
		lBirthdate.setFont(new Font("Arial", Font.BOLD, 20));
		lBirthdate.setForeground(new Color (116, 128, 148));
		
		tfBirthdate.setFont(new Font("Arial", Font.PLAIN, 20));
		tfBirthdate.setForeground(new Color (116, 128, 148));
		tfBirthdate.setOpaque(false);
		
		lAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lAddress.setForeground(new Color (116, 128, 148));
		
		tfAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		tfAddress.setForeground(new Color (116, 128, 148));
		tfAddress.setOpaque(false);
		
		lCity.setFont(new Font("Arial", Font.BOLD, 20));
		lCity.setForeground(new Color (116, 128, 148));
		
		tfCity.setFont(new Font("Arial", Font.PLAIN, 20));
		tfCity.setForeground(new Color (116, 128, 148));
		tfCity.setOpaque(false);
		
		lPhone.setFont(new Font("Arial", Font.BOLD, 20));
		lPhone.setForeground(new Color (116, 128, 148));
		
		tfPhone.setFont(new Font("Arial", Font.PLAIN, 20));
		tfPhone.setForeground(new Color (116, 128, 148));
		tfPhone.setOpaque(false);
		
		lEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lEmail.setForeground(new Color (116, 128, 148));
		
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		tfEmail.setForeground(new Color (116, 128, 148));
		tfEmail.setOpaque(false);
		
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
		
		SQLOwner ow = new SQLOwner();
		ArrayList<String> alOwners = new ArrayList<String>();
		ArrayList<String> alIdOwner = new ArrayList<String>();
		String acumList = "", replaceOwner;
		String[] split;
		
		alOwners = ow.listOwners();
		
		for(int i = 0; i < alOwners.size(); i++) {
			replaceOwner = alOwners.get(i).replace('.', '|');
			split = replaceOwner.split(Pattern.quote("|"));
			alIdOwner.add(split[0]);
			cbOwner.addItem(split[1]);
			acumList += split[1] + "\n";
		}
		
		taList.setText(acumList);
		
		if(alOwners.isEmpty()) {
			btnSelect.setVisible(false);
		} else {
			btnSelect.setVisible(true);
		}
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AddOwner addo = new AddOwner(nameCompany);
				addo.setVisible(true);
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoOwner = ow.selectOwner(alIdOwner.get(cbOwner.getSelectedIndex()));
				dataOwner = infoOwner.split(Pattern.quote("|"));
				
				idOwner = dataOwner[0];
				completeNameOwner = dataOwner[1] + " " + dataOwner[2];
				documentOwner = dataOwner[3] + " " + dataOwner[4];
				birthdayOwner = dataOwner[5];
				addressOwner = dataOwner[6];
				cityOwner = dataOwner[7];
				phoneOwner = dataOwner[8];
				emailOwner = dataOwner[9];
				
				lName.setVisible(true);
				lDocument.setVisible(true);
				lBirthdate.setVisible(true);
				lAddress.setVisible(true);
				lCity.setVisible(true);
				lPhone.setVisible(true);
				lEmail.setVisible(true);
				tfName.setVisible(true);
				tfDocument.setVisible(true);
				tfBirthdate.setVisible(true);
				tfAddress.setVisible(true);
				tfCity.setVisible(true);
				tfPhone.setVisible(true);
				tfEmail.setVisible(true);
				btnModify.setVisible(true);
				btnDelete.setVisible(true);
				
				tfName.setText(completeNameOwner);
				tfDocument.setText(documentOwner);
				tfBirthdate.setText(birthdayOwner);
				tfAddress.setText(addressOwner);
				tfCity.setText(cityOwner);
				tfPhone.setText(phoneOwner);
				tfEmail.setText(emailOwner);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				ModifyOwner mod = null;
				try {
					mod = new ModifyOwner(nameCompany, dataOwner);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				mod.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar este propietario? Se eliminará el móvil que esté asociado", "Propietario - Eliminar", JOptionPane.YES_NO_OPTION);
				
				if(answer == JOptionPane.YES_OPTION) {							
					String request;
					request = ow.deleteOwner(idOwner);
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, "Propietario eliminado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
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
