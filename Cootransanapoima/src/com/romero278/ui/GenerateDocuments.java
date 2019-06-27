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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.romero278.kernel.connection.SQLTour;
import com.romero278.kernel.plan.DataPlan;

public class GenerateDocuments extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany;
	boolean repeatDocGen = false;
	boolean repeatDocEach = false;
	
	DataPlan plan = new DataPlan();
	ArrayList<ArrayList<String>> alActiveMobiles = new ArrayList<>();
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
		JLabel lGeneral = new JLabel("Se genera documento para los despachadores");
		JLabel lEach = new JLabel("Se genera documento para los conductores");
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
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lGeneral, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lGeneral, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnEach, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnEach, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lEach, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEach, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnGeneral);
		container.add(lGeneral);
		container.add(btnEach);
		container.add(lEach);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnGeneral.setFont(new Font("Arial", Font.BOLD, 20));
		btnGeneral.setForeground(new Color (116, 128, 148));
		
		lGeneral.setFont(new Font("Arial", Font.BOLD, 12));
		lGeneral.setForeground(new Color (116, 128, 148));
		
		btnEach.setFont(new Font("Arial", Font.BOLD, 20));
		btnEach.setForeground(new Color (116, 128, 148));
		
		lEach.setFont(new Font("Arial", Font.BOLD, 12));
		lEach.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		btnGeneral.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!repeatDocGen) {
					getData();
					separatePerDays();
					
					try {
						modifyDocumentGeneral();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Documento generado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					repeatDocGen = true;
				} else {
					JOptionPane.showMessageDialog(null, "Ya has generado este documento", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnEach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!repeatDocEach) {
					getData();
					separatePerDays();
					
					try {
						modifyDocumentEachMobile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Documento generado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					repeatDocEach = true;
				} else {
					JOptionPane.showMessageDialog(null, "Ya has generado este documento", "Error", JOptionPane.ERROR_MESSAGE);
				}
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
			
			for(int j = 0; j < 8; j++) {
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
						cell.setCellStyle(styleDayHour);
						cell.setCellValue("");
						break;
				}
			}
		}
		
		sheet = wb.getSheet("MARTES");
		
		for(int i = 0; i < alToursTuesday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 8; j++) {
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
						cell.setCellStyle(styleDayHour);
						cell.setCellValue("");
						break;
				}
			}
		}
		
		sheet = wb.getSheet("MIÉRCOLES");
		
		for(int i = 0; i < alToursWednesday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 8; j++) {
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
						cell.setCellStyle(styleDayHour);
						cell.setCellValue("");
						break;
				}
			}
		}
		
		sheet = wb.getSheet("JUEVES");
		
		for(int i = 0; i < alToursThursday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 8; j++) {
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
						cell.setCellStyle(styleDayHour);
						cell.setCellValue("");
						break;
				}
			}
		}
		
		sheet = wb.getSheet("VIERNES");
		
		for(int i = 0; i < alToursFriday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 8; j++) {
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
						cell.setCellStyle(styleDayHour);
						cell.setCellValue("");
						break;
				}
			}
		}
		
		sheet = wb.getSheet("SÁBADO");
		
		for(int i = 0; i < alToursSaturday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 9; j++) {
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
						cell.setCellStyle(styleDayHour);
						cell.setCellValue("");
						break;
				}
			}
		}
		
		sheet = wb.getSheet("DOMINGO");
		
		for(int i = 0; i < alToursSunday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 8; j++) {
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
						cell.setCellStyle(styleDayHour);
						cell.setCellValue("");
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
	
	void modifyDocumentEachMobile() throws IOException {
		alActiveMobiles = plan.getActiveMobiles();
		
		FileInputStream file = new FileInputStream(new File("C:\\Users\\DAVID  ROMERO M\\Google Drive\\David\\Profesional\\Independiente\\Cootransanapoima\\Documentación\\Rutas\\PlantillaPlanIndividual.xlsx"));
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		
		int numRow = 0;
		int numColumn = 0;
		String ownerName = "";
		
		XSSFFont fontOwner = wb.createFont();
		fontOwner.setFontHeightInPoints((short) 20);
		fontOwner.setFontName("Arial");
		
		XSSFFont fontMobile = wb.createFont();
		fontMobile.setFontHeightInPoints((short) 20);
		fontMobile.setFontName("Arial");
		fontMobile.setBold(true);
		
		XSSFFont fontTitle = wb.createFont();
		fontTitle.setFontHeightInPoints((short) 14);
		fontTitle.setFontName("Arial");
		
		XSSFFont fontNormal = wb.createFont();
		fontNormal.setFontHeightInPoints((short) 14);
		
		CellStyle styleOwner = wb.createCellStyle();
		styleOwner.setAlignment(HorizontalAlignment.CENTER);
		styleOwner.setVerticalAlignment(VerticalAlignment.CENTER);
		styleOwner.setFont(fontOwner);
		
		CellStyle styleMobile = wb.createCellStyle();
		styleMobile.setAlignment(HorizontalAlignment.CENTER);
		styleMobile.setVerticalAlignment(VerticalAlignment.CENTER);
		styleMobile.setFont(fontMobile);
		
		CellStyle styleTitle = wb.createCellStyle();
		styleTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
		styleTitle.setBorderBottom(BorderStyle.THIN);
		styleTitle.setBorderLeft(BorderStyle.THIN);
		styleTitle.setBorderRight(BorderStyle.THIN);
		styleTitle.setBorderTop(BorderStyle.THIN);
		styleTitle.setFont(fontTitle);
		styleTitle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    
	    CellStyle styleHour = wb.createCellStyle();
		styleHour.setAlignment(HorizontalAlignment.CENTER);
		styleHour.setVerticalAlignment(VerticalAlignment.CENTER);
		styleHour.setBorderBottom(BorderStyle.THIN);
		styleHour.setBorderLeft(BorderStyle.THIN);
		styleHour.setBorderRight(BorderStyle.THIN);
		styleHour.setBorderTop(BorderStyle.THIN);
		styleHour.setFont(fontNormal);
		
		CellStyle styleRouteOne = wb.createCellStyle();
		styleRouteOne.setAlignment(HorizontalAlignment.RIGHT);
		styleRouteOne.setVerticalAlignment(VerticalAlignment.CENTER);
		styleRouteOne.setBorderBottom(BorderStyle.THIN);
		styleRouteOne.setBorderLeft(BorderStyle.THIN);
		styleRouteOne.setBorderTop(BorderStyle.THIN);
		styleRouteOne.setFont(fontNormal);
		
		CellStyle styleRouteTwo = wb.createCellStyle();
		styleRouteTwo.setAlignment(HorizontalAlignment.LEFT);
		styleRouteTwo.setVerticalAlignment(VerticalAlignment.CENTER);
		styleRouteTwo.setBorderBottom(BorderStyle.THIN);
		styleRouteTwo.setBorderRight(BorderStyle.THIN);
		styleRouteTwo.setBorderTop(BorderStyle.THIN);
		styleRouteTwo.setFont(fontNormal);
		
		for(int i = 0; i < alActiveMobiles.size(); i++) {
			sheet = wb.getSheet(alActiveMobiles.get(i).get(0));
			
			if(sheet == null) {
				sheet = wb.createSheet(alActiveMobiles.get(i).get(0));
			}
			
			ownerName = plan.getCompleteNameOwner(alActiveMobiles.get(i).get(0)); 
			
			numRow = 4;
			
			row = sheet.getRow(numRow);			
			cell = row.createCell(0);
			cell.setCellValue(ownerName.toUpperCase());
			cell.setCellStyle(styleOwner);
			numRow++;
			
			row = sheet.getRow(numRow);			
			cell = row.createCell(0);
			cell.setCellValue("Móvil " + alActiveMobiles.get(i).get(0));
			cell.setCellStyle(styleMobile);
			numRow++;
			
			// --- LUNES
			
			numColumn = 0;
			row = sheet.getRow(numRow);
			cell = row.createCell(numColumn);
			cell.setCellValue("hh");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("mm");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Lunes");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			sheet.addMergedRegionUnsafe(new CellRangeAddress(numRow, numRow, 2, 3));
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Ok");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Observaciones");
			cell.setCellStyle(styleTitle);
			
			for(int j = 0; j < alToursMonday.size(); j++) {
				if(alToursMonday.get(j).get(3).equals(alActiveMobiles.get(i).get(0))) {
					numRow++;
					row = sheet.getRow(numRow);
					
					for(int k = 0; k < 6; k++) {
						cell = row.createCell(k);
						
						switch (k) {
							case 0:
								cell.setCellValue(Integer.parseInt(alToursMonday.get(j).get(1)));
								cell.setCellStyle(styleHour);
								break;
							case 1:
								cell.setCellValue(Integer.parseInt(alToursMonday.get(j).get(2)));
								cell.setCellStyle(styleHour);
								break;
							case 2:
								cell.setCellValue(alToursMonday.get(j).get(4));
								cell.setCellStyle(styleRouteOne);
								break;
							case 3:
								cell.setCellValue(alToursMonday.get(j).get(5));
								cell.setCellStyle(styleRouteTwo);
								break;
							default:
								cell.setCellValue("");
								cell.setCellStyle(styleHour);
								break;
						}
					}
				}
			}
			
			// --- MARTES
			
			numRow++;
			numRow++;
			
			numColumn = 0;
			row = sheet.getRow(numRow);
			cell = row.createCell(numColumn);
			cell.setCellValue("hh");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("mm");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Martes");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			sheet.addMergedRegionUnsafe(new CellRangeAddress(numRow, numRow, 2, 3));
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Ok");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Observaciones");
			cell.setCellStyle(styleTitle);
			
			for(int j = 0; j < alToursTuesday.size(); j++) {
				if(alToursTuesday.get(j).get(3).equals(alActiveMobiles.get(i).get(0))) {
					numRow++;
					row = sheet.getRow(numRow);
					
					for(int k = 0; k < 6; k++) {
						cell = row.createCell(k);
						
						switch (k) {
							case 0:
								cell.setCellValue(Integer.parseInt(alToursTuesday.get(j).get(1)));
								cell.setCellStyle(styleHour);
								break;
							case 1:
								cell.setCellValue(Integer.parseInt(alToursTuesday.get(j).get(2)));
								cell.setCellStyle(styleHour);
								break;
							case 2:
								cell.setCellValue(alToursTuesday.get(j).get(4));
								cell.setCellStyle(styleRouteOne);
								break;
							case 3:
								cell.setCellValue(alToursTuesday.get(j).get(5));
								cell.setCellStyle(styleRouteTwo);
								break;
							default:
								cell.setCellValue("");
								cell.setCellStyle(styleHour);
								break;
						}
					}
				}
			}	
			
			// --- MIERCOLES
			
			numRow++;
			numRow++;
			
			numColumn = 0;
			row = sheet.getRow(numRow);
			cell = row.createCell(numColumn);
			cell.setCellValue("hh");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("mm");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Miércoles");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			sheet.addMergedRegionUnsafe(new CellRangeAddress(numRow, numRow, 2, 3));
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Ok");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Observaciones");
			cell.setCellStyle(styleTitle);
			
			for(int j = 0; j < alToursWednesday.size(); j++) {
				if(alToursWednesday.get(j).get(3).equals(alActiveMobiles.get(i).get(0))) {
					numRow++;
					row = sheet.getRow(numRow);
					
					for(int k = 0; k < 6; k++) {
						cell = row.createCell(k);
						
						switch (k) {
							case 0:
								cell.setCellValue(Integer.parseInt(alToursWednesday.get(j).get(1)));
								cell.setCellStyle(styleHour);
								break;
							case 1:
								cell.setCellValue(Integer.parseInt(alToursWednesday.get(j).get(2)));
								cell.setCellStyle(styleHour);
								break;
							case 2:
								cell.setCellValue(alToursWednesday.get(j).get(4));
								cell.setCellStyle(styleRouteOne);
								break;
							case 3:
								cell.setCellValue(alToursWednesday.get(j).get(5));
								cell.setCellStyle(styleRouteTwo);
								break;
							default:
								cell.setCellValue("");
								cell.setCellStyle(styleHour);
								break;
						}
					}
				}
			}
			
			// --- JUEVES
			
			numRow++;
			numRow++;
			
			numColumn = 0;
			row = sheet.getRow(numRow);
			cell = row.createCell(numColumn);
			cell.setCellValue("hh");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("mm");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Jueves");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			sheet.addMergedRegionUnsafe(new CellRangeAddress(numRow, numRow, 2, 3));
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Ok");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Observaciones");
			cell.setCellStyle(styleTitle);
			
			for(int j = 0; j < alToursThursday.size(); j++) {
				if(alToursThursday.get(j).get(3).equals(alActiveMobiles.get(i).get(0))) {
					numRow++;
					row = sheet.getRow(numRow);
					
					for(int k = 0; k < 6; k++) {
						cell = row.createCell(k);
						
						switch (k) {
							case 0:
								cell.setCellValue(Integer.parseInt(alToursThursday.get(j).get(1)));
								cell.setCellStyle(styleHour);
								break;
							case 1:
								cell.setCellValue(Integer.parseInt(alToursThursday.get(j).get(2)));
								cell.setCellStyle(styleHour);
								break;
							case 2:
								cell.setCellValue(alToursThursday.get(j).get(4));
								cell.setCellStyle(styleRouteOne);
								break;
							case 3:
								cell.setCellValue(alToursThursday.get(j).get(5));
								cell.setCellStyle(styleRouteTwo);
								break;
							default:
								cell.setCellValue("");
								cell.setCellStyle(styleHour);
								break;
						}
					}
				}
			}
			
			// --- VIERNES
			
			numRow++;
			numRow++;
			
			numColumn = 0;
			row = sheet.getRow(numRow);
			cell = row.createCell(numColumn);
			cell.setCellValue("hh");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("mm");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Viernes");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			sheet.addMergedRegionUnsafe(new CellRangeAddress(numRow, numRow, 2, 3));
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Ok");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Observaciones");
			cell.setCellStyle(styleTitle);
			
			for(int j = 0; j < alToursFriday.size(); j++) {
				if(alToursFriday.get(j).get(3).equals(alActiveMobiles.get(i).get(0))) {
					numRow++;
					row = sheet.getRow(numRow);
					
					for(int k = 0; k < 6; k++) {
						cell = row.createCell(k);
						
						switch (k) {
							case 0:
								cell.setCellValue(Integer.parseInt(alToursFriday.get(j).get(1)));
								cell.setCellStyle(styleHour);
								break;
							case 1:
								cell.setCellValue(Integer.parseInt(alToursFriday.get(j).get(2)));
								cell.setCellStyle(styleHour);
								break;
							case 2:
								cell.setCellValue(alToursFriday.get(j).get(4));
								cell.setCellStyle(styleRouteOne);
								break;
							case 3:
								cell.setCellValue(alToursFriday.get(j).get(5));
								cell.setCellStyle(styleRouteTwo);
								break;
							default:
								cell.setCellValue("");
								cell.setCellStyle(styleHour);
								break;
						}
					}
				}
			}
			
			// --- SABADO
			
			numRow++;
			numRow++;
			
			numColumn = 0;
			row = sheet.getRow(numRow);
			cell = row.createCell(numColumn);
			cell.setCellValue("hh");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("mm");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Sábado");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			sheet.addMergedRegionUnsafe(new CellRangeAddress(numRow, numRow, 2, 3));
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Ok");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Observaciones");
			cell.setCellStyle(styleTitle);
			
			for(int j = 0; j < alToursSaturday.size(); j++) {
				if(alToursSaturday.get(j).get(3).equals(alActiveMobiles.get(i).get(0))) {
					numRow++;
					row = sheet.getRow(numRow);
					
					for(int k = 0; k < 6; k++) {
						cell = row.createCell(k);
						
						switch (k) {
							case 0:
								cell.setCellValue(Integer.parseInt(alToursSaturday.get(j).get(1)));
								cell.setCellStyle(styleHour);
								break;
							case 1:
								cell.setCellValue(Integer.parseInt(alToursSaturday.get(j).get(2)));
								cell.setCellStyle(styleHour);
								break;
							case 2:
								cell.setCellValue(alToursSaturday.get(j).get(4));
								cell.setCellStyle(styleRouteOne);
								break;
							case 3:
								cell.setCellValue(alToursSaturday.get(j).get(5));
								cell.setCellStyle(styleRouteTwo);
								break;
							default:
								cell.setCellValue("");
								cell.setCellStyle(styleHour);
								break;
						}
					}
				}
			}
			
			// --- DOMINGO
			
			numRow++;
			numRow++;
			
			numColumn = 0;
			row = sheet.getRow(numRow);
			cell = row.createCell(numColumn);
			cell.setCellValue("hh");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("mm");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Domingo");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			sheet.addMergedRegionUnsafe(new CellRangeAddress(numRow, numRow, 2, 3));
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Ok");
			cell.setCellStyle(styleTitle);
			numColumn++;
			
			cell = row.createCell(numColumn);
			cell.setCellValue("Observaciones");
			cell.setCellStyle(styleTitle);
			
			for(int j = 0; j < alToursSunday.size(); j++) {
				if(alToursSunday.get(j).get(3).equals(alActiveMobiles.get(i).get(0))) {
					numRow++;
					row = sheet.getRow(numRow);
					
					for(int k = 0; k < 6; k++) {
						cell = row.createCell(k);
						
						switch (k) {
							case 0:
								cell.setCellValue(Integer.parseInt(alToursSunday.get(j).get(1)));
								cell.setCellStyle(styleHour);
								break;
							case 1:
								cell.setCellValue(Integer.parseInt(alToursSunday.get(j).get(2)));
								cell.setCellStyle(styleHour);
								break;
							case 2:
								cell.setCellValue(alToursSunday.get(j).get(4));
								cell.setCellStyle(styleRouteOne);
								break;
							case 3:
								cell.setCellValue(alToursSunday.get(j).get(5));
								cell.setCellStyle(styleRouteTwo);
								break;
							default:
								cell.setCellValue("");
								cell.setCellStyle(styleHour);
								break;
						}
					}
				}
			}
		}
		
		file.close();
		
		FileOutputStream output = new FileOutputStream("C:\\Users\\DAVID  ROMERO M\\Google Drive\\David\\Profesional\\Independiente\\Cootransanapoima\\Documentación\\Rutas\\PlantillaTestIndJava.xlsx");
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
