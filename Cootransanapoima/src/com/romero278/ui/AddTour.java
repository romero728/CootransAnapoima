package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
import com.romero278.kernel.connection.SQLTypeVehicle;

public class AddTour extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option;
	
	public AddTour(String fg, String op) {
		flag = fg;
		option = op;
		
		setTitle(option + " - Agregar");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Agregar " + option.toLowerCase());
		JLabel subtitle = new JLabel("Ingresa los datos");
		JLabel lRoute = new JLabel("Ruta: ");
		JLabel lDay = new JLabel("Día: ");
		JLabel lHour = new JLabel("Hora: ");
		JLabel lType = new JLabel("Tipo de vehículo: ");
		JTextField tfHour = new JTextField(6);
		JComboBox<String> cbRoute = new JComboBox<String>();
		JComboBox<String> cbDay = new JComboBox<String>();
		JComboBox<String> cbType = new JComboBox<String>();
		JButton btnAdd = new JButton("Agregar");
		JButton btnBack = new JButton("Atrás");
		
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
		springLayout.putConstraint(SpringLayout.NORTH, lDay, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbDay, 470, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbDay, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lHour, 660, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lHour, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfHour, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfHour, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lType, 460, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lType, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbType, 630, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbType, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 400, SpringLayout.NORTH, container);
		
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
		container.add(lType);
		container.add(cbType);
		container.add(btnAdd);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		lRoute.setFont(new Font("Arial", Font.BOLD, 20));
		lRoute.setForeground(new Color (116, 128, 148));
		
		cbRoute.setFont(new Font("Arial", Font.PLAIN, 18));
		cbRoute.setForeground(new Color (116, 128, 148));
		
		lDay.setFont(new Font("Arial", Font.BOLD, 20));
		lDay.setForeground(new Color (116, 128, 148));
		
		cbDay.setFont(new Font("Arial", Font.PLAIN, 18));
		cbDay.setForeground(new Color (116, 128, 148));
		
		lHour.setFont(new Font("Arial", Font.BOLD, 20));
		lHour.setForeground(new Color (116, 128, 148));
		
		tfHour.setFont(new Font("Arial", Font.PLAIN, 18));
		tfHour.setForeground(new Color (116, 128, 148));
		
		lType.setFont(new Font("Arial", Font.BOLD, 20));
		lType.setForeground(new Color (116, 128, 148));
		
		cbType.setFont(new Font("Arial", Font.PLAIN, 18));
		cbType.setForeground(new Color (116, 128, 148));
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		SQLTour tour = new SQLTour();
		SQLRoute route = new SQLRoute();
		SQLTypeVehicle typeVehicle = new SQLTypeVehicle();
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
		
		ArrayList<String> alTypes = typeVehicle.listTypeVehicles();
		ArrayList<String[]> alTourType = new ArrayList<String[]>();
		split = null;
		
		for(int i = 0; i < alTypes.size(); i++) {
			split = alTypes.get(i).split(Pattern.quote("|"));
			alTourType.add(split);
			cbType.addItem(split[1]);
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
					
					String type = cbType.getSelectedItem().toString();
					String idType = "";
					arAux = null;
					
					for(int i = 0; i < alTourType.size(); i++) {
						arAux = alTourType.get(i); 
						
						if(arAux[1].equals(type)) {
							idType = arAux[0];
						}
					}
					
					request = tour.insertTour(idRoute, idDay, tfHour.getText(), idType);
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " agregado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
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
		
		ConfigOptions conOps = new ConfigOptions(flag, option);
		conOps.setVisible(true);
	}
}
