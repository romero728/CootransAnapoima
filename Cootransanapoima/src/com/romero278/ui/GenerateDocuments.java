package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.romero278.kernel.connection.SQLTour;

public class GenerateDocuments extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany;
	ArrayList<ArrayList<String>> alPlan = new ArrayList<>();
	ArrayList<ArrayList<String>> alTours = new ArrayList<>();
	ArrayList<ArrayList<String>> alToursMonday = new ArrayList<>();
	ArrayList<ArrayList<String>> alToursTuesday = new ArrayList<>();
	ArrayList<ArrayList<String>> alToursWednesday = new ArrayList<>();
	ArrayList<ArrayList<String>> alToursThursday = new ArrayList<>();
	ArrayList<ArrayList<String>> alToursFriday = new ArrayList<>();
	ArrayList<ArrayList<String>> alToursSaturday = new ArrayList<>();
	ArrayList<ArrayList<String>> alToursSunday = new ArrayList<>();
	ArrayList<String> alMobiles = new ArrayList<>();
	
	public GenerateDocuments(String comp, ArrayList<ArrayList<String>> ap) {
		nameCompany = comp;
		alPlan = ap;
		
		setTitle("Generar documentos");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("GENERAR DOCUMENTOS");
		JLabel subtitle = new JLabel("Elige una opción");
		JButton btnGeneral = new JButton("Documento general");
		JButton btnEach = new JButton("Documento de cada móvil");
		JButton btnBack = new JButton("Atrás");
		
		btnGeneral.setPreferredSize(new Dimension(300, 36));
		btnEach.setPreferredSize(new Dimension(300, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnGeneral, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnGeneral, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnEach, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnEach, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnGeneral);
		container.add(btnEach);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnGeneral.setFont(new Font("Arial", Font.BOLD, 20));
		btnGeneral.setForeground(new Color (116, 128, 148));
		
		btnEach.setFont(new Font("Arial", Font.BOLD, 20));
		btnEach.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		btnGeneral.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getData();
				separatePerDays();
				try {
					modifyDocumentGeneral();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Documento generado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				
//				System.out.println(alMobiles.size() + " " + alTours.size());
				
//				for(int i = 0; i < alMobiles.size(); i++) {
//					System.out.println(alTours.get(i).get(1) + " " + alTours.get(i).get(2) + ":" + alTours.get(i).get(3) + " " + alMobiles.get(i) + " Anapoima - " + alTours.get(i).get(0));
//				}
				
//				for(int i = 0; i < alToursMonday.size(); i++) {
//					System.out.println(alToursMonday.get(i).get(0) + " " + alToursMonday.get(i).get(1) + ":" + alToursMonday.get(i).get(2) + " " + alToursMonday.get(i).get(3) + " Anapoima - " + alToursMonday.get(i).get(4));
//				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(null, "¿Deseas regresar? No se podrá volver a acceder a este plan de rodamiento", "Aviso", JOptionPane.YES_NO_OPTION);
				
				if(ans == JOptionPane.YES_OPTION) {
					goBack();
				}
			}
		});
	}
	
	void getData() {
		SQLTour tour = new SQLTour();
		
		String currentTour = "";
		String[] splitTour = null;
		String[] splitHour = null;
		ArrayList<String> alTourCurrent = new ArrayList<>();
		
		for(int i = 0; i < alPlan.size(); i++) {
			alMobiles.add(alPlan.get(i).get(0));
			
			currentTour = tour.selectTour(alPlan.get(i).get(1));
			splitTour = currentTour.split(Pattern.quote("|"));
			alTourCurrent.add(splitTour[1]);
			alTourCurrent.add(splitTour[2]);
			
			splitHour = splitTour[3].split(Pattern.quote(":"));
			alTourCurrent.add(splitHour[0]);
			alTourCurrent.add(splitHour[1]);
			
			alTours.add(alTourCurrent);
			
			alTourCurrent = new ArrayList<>();
			currentTour = "";
			splitTour = null;
			splitHour = null;
		}
	}
	
	void separatePerDays() {
		ArrayList<String> alTourCurrent = new ArrayList<>();
		
		for(int i = 0; i < alTours.size(); i++) {
			alTourCurrent.add(alTours.get(i).get(1));
			alTourCurrent.add(alTours.get(i).get(2));
			alTourCurrent.add(alTours.get(i).get(3));
			alTourCurrent.add(alMobiles.get(i));
			alTourCurrent.add("Anapoima -- ");
			alTourCurrent.add(alTours.get(i).get(0));
			
			switch (alTours.get(i).get(1)) {
				case "Lunes":
					alToursMonday.add(alTourCurrent);
					break;
				case "Martes":
					alToursTuesday.add(alTourCurrent);
					break;
				case "Miércoles":
					alToursWednesday.add(alTourCurrent);
					break;
				case "Jueves":
					alToursThursday.add(alTourCurrent);
					break;
				case "Viernes":
					alToursFriday.add(alTourCurrent);
					break;
				case "Sábado":
					alToursSaturday.add(alTourCurrent);
					break;
				case "Domingo":
					alToursSunday.add(alTourCurrent);
					break;
				default:
					break;
			}
			
			alTourCurrent = new ArrayList<>();
		}
	}
	
	void modifyDocumentGeneral() throws IOException { 
		FileInputStream file = new FileInputStream(new File("C:\\Users\\DAVID  ROMERO M\\Google Drive\\David\\Profesional\\Independiente\\Cootransanapoima\\Documentación\\Rutas\\PlantillaTestJava.xlsx"));
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 14);
		
		XSSFFont fontMobile = wb.createFont();
		fontMobile.setFontHeightInPoints((short) 14);
		fontMobile.setBold(true);
		
		CellStyle styleDayHour = wb.createCellStyle();
		styleDayHour.setAlignment(HorizontalAlignment.CENTER);
		styleDayHour.setVerticalAlignment(VerticalAlignment.CENTER);
		styleDayHour.setBorderBottom(BorderStyle.THIN);
		styleDayHour.setBorderLeft(BorderStyle.THIN);
		styleDayHour.setBorderRight(BorderStyle.THIN);
		styleDayHour.setBorderTop(BorderStyle.THIN);
		styleDayHour.setFont(font);
		
		CellStyle styleMobile = wb.createCellStyle();
		styleMobile.setAlignment(HorizontalAlignment.CENTER);
		styleMobile.setVerticalAlignment(VerticalAlignment.CENTER);
		styleMobile.setBorderBottom(BorderStyle.THIN);
		styleMobile.setBorderLeft(BorderStyle.THIN);
		styleMobile.setBorderRight(BorderStyle.THIN);
		styleMobile.setBorderTop(BorderStyle.THIN);
		styleMobile.setFont(fontMobile);
		
		CellStyle styleRouteOne = wb.createCellStyle();
		styleRouteOne.setAlignment(HorizontalAlignment.RIGHT);
		styleRouteOne.setVerticalAlignment(VerticalAlignment.CENTER);
		styleRouteOne.setBorderBottom(BorderStyle.THIN);
		styleRouteOne.setBorderLeft(BorderStyle.THIN);
		styleRouteOne.setBorderTop(BorderStyle.THIN);
		styleRouteOne.setFont(font);
		
		CellStyle styleRouteTwo = wb.createCellStyle();
		styleRouteTwo.setAlignment(HorizontalAlignment.LEFT);
		styleRouteTwo.setVerticalAlignment(VerticalAlignment.CENTER);
		styleRouteTwo.setBorderBottom(BorderStyle.THIN);
		styleRouteTwo.setBorderRight(BorderStyle.THIN);
		styleRouteTwo.setBorderTop(BorderStyle.THIN);
		styleRouteTwo.setFont(font);
		
		sheet = wb.getSheet("LUNES");
		
		for(int i = 0; i < alToursMonday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 6; j++) {
				cell = row.createCell(j);;
				
				switch(j) {
					case 0:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(alToursMonday.get(i).get(j));
						break;
					case 1:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursMonday.get(i).get(j)));
						break;
					case 2:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursMonday.get(i).get(j)));
						break;
					case 3:
						cell.setCellStyle(styleMobile);
						cell.setCellValue(Integer.parseInt(alToursMonday.get(i).get(j)));
						break;
					case 4:
						cell.setCellStyle(styleRouteOne);
						cell.setCellValue(alToursMonday.get(i).get(j));
						break;
					case 5:
						cell.setCellStyle(styleRouteTwo);
						cell.setCellValue(alToursMonday.get(i).get(j));
						break;
					default:
						break;
				}
			}
		}
		
		sheet = wb.getSheet("MARTES");
		
		for(int i = 0; i < alToursTuesday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 6; j++) {
				cell = row.createCell(j);;
				
				switch(j) {
					case 0:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(alToursTuesday.get(i).get(j));
						break;
					case 1:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursTuesday.get(i).get(j)));
						break;
					case 2:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursTuesday.get(i).get(j)));
						break;
					case 3:
						cell.setCellStyle(styleMobile);
						cell.setCellValue(Integer.parseInt(alToursTuesday.get(i).get(j)));
						break;
					case 4:
						cell.setCellStyle(styleRouteOne);
						cell.setCellValue(alToursTuesday.get(i).get(j));
						break;
					case 5:
						cell.setCellStyle(styleRouteTwo);
						cell.setCellValue(alToursTuesday.get(i).get(j));
						break;
					default:
						break;
				}
			}
		}
		
		sheet = wb.getSheet("MIÉRCOLES");
		
		for(int i = 0; i < alToursWednesday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 6; j++) {
				cell = row.createCell(j);;
				
				switch(j) {
					case 0:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(alToursWednesday.get(i).get(j));
						break;
					case 1:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursWednesday.get(i).get(j)));
						break;
					case 2:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursWednesday.get(i).get(j)));
						break;
					case 3:
						cell.setCellStyle(styleMobile);
						cell.setCellValue(Integer.parseInt(alToursWednesday.get(i).get(j)));
						break;
					case 4:
						cell.setCellStyle(styleRouteOne);
						cell.setCellValue(alToursWednesday.get(i).get(j));
						break;
					case 5:
						cell.setCellStyle(styleRouteTwo);
						cell.setCellValue(alToursWednesday.get(i).get(j));
						break;
					default:
						break;
				}
			}
		}
		
		sheet = wb.getSheet("JUEVES");
		
		for(int i = 0; i < alToursThursday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 6; j++) {
				cell = row.createCell(j);;
				
				switch(j) {
					case 0:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(alToursThursday.get(i).get(j));
						break;
					case 1:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursThursday.get(i).get(j)));
						break;
					case 2:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursThursday.get(i).get(j)));
						break;
					case 3:
						cell.setCellStyle(styleMobile);
						cell.setCellValue(Integer.parseInt(alToursThursday.get(i).get(j)));
						break;
					case 4:
						cell.setCellStyle(styleRouteOne);
						cell.setCellValue(alToursThursday.get(i).get(j));
						break;
					case 5:
						cell.setCellStyle(styleRouteTwo);
						cell.setCellValue(alToursThursday.get(i).get(j));
						break;
					default:
						break;
				}
			}
		}
		
		sheet = wb.getSheet("VIERNES");
		
		for(int i = 0; i < alToursFriday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 6; j++) {
				cell = row.createCell(j);;
				
				switch(j) {
					case 0:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(alToursFriday.get(i).get(j));
						break;
					case 1:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursFriday.get(i).get(j)));
						break;
					case 2:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursFriday.get(i).get(j)));
						break;
					case 3:
						cell.setCellStyle(styleMobile);
						cell.setCellValue(Integer.parseInt(alToursFriday.get(i).get(j)));
						break;
					case 4:
						cell.setCellStyle(styleRouteOne);
						cell.setCellValue(alToursFriday.get(i).get(j));
						break;
					case 5:
						cell.setCellStyle(styleRouteTwo);
						cell.setCellValue(alToursFriday.get(i).get(j));
						break;
					default:
						break;
				}
			}
		}
		
		sheet = wb.getSheet("SÁBADO");
		
		for(int i = 0; i < alToursSaturday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 6; j++) {
				cell = row.createCell(j);;
				
				switch(j) {
					case 0:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(alToursSaturday.get(i).get(j));
						break;
					case 1:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursSaturday.get(i).get(j)));
						break;
					case 2:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursSaturday.get(i).get(j)));
						break;
					case 3:
						cell.setCellStyle(styleMobile);
						cell.setCellValue(Integer.parseInt(alToursSaturday.get(i).get(j)));
						break;
					case 4:
						cell.setCellStyle(styleRouteOne);
						cell.setCellValue(alToursSaturday.get(i).get(j));
						break;
					case 5:
						cell.setCellStyle(styleRouteTwo);
						cell.setCellValue(alToursSaturday.get(i).get(j));
						break;
					default:
						break;
				}
			}
		}
		
		sheet = wb.getSheet("DOMINGO");
		
		for(int i = 0; i < alToursSunday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 6; j++) {
				cell = row.createCell(j);;
				
				switch(j) {
					case 0:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(alToursSunday.get(i).get(j));
						break;
					case 1:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursSunday.get(i).get(j)));
						break;
					case 2:
						cell.setCellStyle(styleDayHour);
						cell.setCellValue(Integer.parseInt(alToursSunday.get(i).get(j)));
						break;
					case 3:
						cell.setCellStyle(styleMobile);
						cell.setCellValue(Integer.parseInt(alToursSunday.get(i).get(j)));
						break;
					case 4:
						cell.setCellStyle(styleRouteOne);
						cell.setCellValue(alToursSunday.get(i).get(j));
						break;
					case 5:
						cell.setCellStyle(styleRouteTwo);
						cell.setCellValue(alToursSunday.get(i).get(j));
						break;
					default:
						break;
				}
			}
		}
		
		file.close();
		
		FileOutputStream output = new FileOutputStream("C:\\Users\\DAVID  ROMERO M\\Google Drive\\David\\Profesional\\Independiente\\Cootransanapoima\\Documentación\\Rutas\\PlantillaTestJava.xlsx");
		wb.write(output);
		output.close();
		wb.close();
	}
	
	void goBack() {
		setVisible(false);
		
		GeneratePlan genPlan = new GeneratePlan(nameCompany);
		genPlan.setVisible(true);
	}
}
