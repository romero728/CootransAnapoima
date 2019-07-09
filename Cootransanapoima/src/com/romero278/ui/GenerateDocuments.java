package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
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
	
	String nameCompany, rangeDate, nameGeneral, nameEach;
	boolean repeatData = false;
	boolean repeatDays = false;
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
	
	@SuppressWarnings("serial")
	public GenerateDocuments(String comp, String rngDate, ArrayList<ArrayList<String>> ap) {
		nameCompany = comp;
		rangeDate = rngDate;
		alPlan = ap;
		
		setTitle("Generar documentos");
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo_anapoima.png").getScaledInstance(48, 48, java.awt.Image.SCALE_AREA_AVERAGING));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lImageLogo = new JLabel();
		JLabel title = new JLabel("GENERAR DOCUMENTOS");
		JLabel subtitle = new JLabel("Elige una opci�n");
		JLabel lGeneral = new JLabel("Se genera documento para los despachadores");
		JLabel lEach = new JLabel("Se genera documento para los conductores");
		JButton btnGeneral = new JButton("Documento general", new ImageIcon("img/excel-dark.png"));
		JButton btnEach = new JButton("Documento de cada m�vil", new ImageIcon("img/excel.png"));
		JButton btnBack = new JButton("Atr�s", new ImageIcon("img/back.png"));
		
		ImageIcon logo = new ImageIcon(new ImageIcon("img/logo_anapoima.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		btnGeneral.setPreferredSize(new Dimension(320, 36));
		btnEach.setPreferredSize(new Dimension(320, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 120, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 190, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnGeneral, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnGeneral, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lGeneral, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lGeneral, 290, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnEach, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnEach, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lEach, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEach, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnGeneral);
		container.add(lGeneral);
		container.add(btnEach);
		container.add(lEach);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnGeneral.setFont(new Font("Arial", Font.BOLD, 20));
		btnGeneral.setForeground(new Color (116, 128, 148));
		btnGeneral.setBackground(new Color(243, 227, 124));
		btnGeneral.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lGeneral.setFont(new Font("Arial", Font.BOLD, 12));
		lGeneral.setForeground(new Color (116, 128, 148));
		
		btnEach.setFont(new Font("Arial", Font.BOLD, 20));
		btnEach.setForeground(Color.WHITE);
		btnEach.setBackground(new Color(136, 212, 152));
		btnEach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lEach.setFont(new Font("Arial", Font.BOLD, 12));
		lEach.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		btnGeneral.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnGeneral.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnGeneral.setBackground(new Color(243, 227, 124));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnGeneral.setBackground(new Color(243, 211, 74));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		btnEach.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnEach.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnEach.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnEach.setBackground(new Color(109, 186, 163));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		/* --- Logic part --- */
		
		btnGeneral.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(null, "�Deseas iniciar el proceso?", "Confirmar", JOptionPane.YES_NO_OPTION);
				
				if(ans == JOptionPane.YES_OPTION) {
					contentPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					
					if(!repeatDocGen) {
						getInfo();
						
						try {
							generateDocumentGeneral();
							repeatDocGen = true;							
							contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							try {
								Desktop.getDesktop().open(new File("C:\\Users\\DAVID  ROMERO M\\Desktop\\Gestor de rutas\\Rutas\\Generales\\" + nameGeneral));
							} catch (IOException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
								
								JOptionPane.showMessageDialog(null, "El documento general no se ha podido visualizar", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error generando este documento", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ya has generado este documento", "Error", JOptionPane.ERROR_MESSAGE);
						contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				} else {
					contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		
		btnEach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int ans = JOptionPane.showConfirmDialog(null, "�Deseas iniciar el proceso?", "Confirmar", JOptionPane.YES_NO_OPTION);
				
				if(ans == JOptionPane.YES_OPTION) {
					contentPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					
					if(!repeatDocEach) {
						getInfo();
						
						try {
							generateDocumentEachMobile();
							repeatDocEach = true;							
							contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							
							try {
								Desktop.getDesktop().open(new File("C:\\Users\\DAVID  ROMERO M\\Desktop\\Gestor de rutas\\Rutas\\Individuales\\" + nameEach));
							} catch (IOException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
								
								JOptionPane.showMessageDialog(null, "El documento individual no se ha podido visualizar", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error generando este documento", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ya has generado este documento", "Error", JOptionPane.ERROR_MESSAGE);
						contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				} else {
					contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(null, "�Deseas regresar? No se podr� volver a acceder a este plan de rodamiento", "Aviso", JOptionPane.YES_NO_OPTION);
				
				if(ans == JOptionPane.YES_OPTION) {
					goBack();
				}
			}
		});
	}
	
	void getInfo() {
		if(!repeatData && !repeatDays) {
			getData();
			separatePerDays();
		}
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
		
		repeatData = true;
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
				case "Mi�rcoles":
					alToursWednesday.add(alTourCurrent);
					break;
				case "Jueves":
					alToursThursday.add(alTourCurrent);
					break;
				case "Viernes":
					alToursFriday.add(alTourCurrent);
					break;
				case "S�bado":
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
		
		repeatDays = true;
	}
	
	void generateDocumentGeneral() throws IOException {		
		FileInputStream file = new FileInputStream(new File("C:\\Users\\DAVID  ROMERO M\\Desktop\\Gestor de rutas\\Plantillas\\PlantillaPlanGeneral.xlsx"));
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 14);
		
		XSSFFont fontMobile = wb.createFont();
		fontMobile.setFontHeightInPoints((short) 14);
		fontMobile.setBold(true);
		
		XSSFFont fontTitle = wb.createFont();
		fontTitle.setFontName("Arial");
		fontTitle.setFontHeightInPoints((short) 14);
		
		CellStyle styleTitle = wb.createCellStyle();
		styleTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
		styleTitle.setFont(fontTitle);
		
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
		
		row = sheet.getRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Rodamiento semanal del " + rangeDate);
		cell.setCellStyle(styleTitle);
		
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
		
		row = sheet.getRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Rodamiento semanal del " + rangeDate);
		cell.setCellStyle(styleTitle);
		
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
		
		sheet = wb.getSheet("MI�RCOLES");
		
		row = sheet.getRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Rodamiento semanal del " + rangeDate);
		cell.setCellStyle(styleTitle);
		
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
		
		row = sheet.getRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Rodamiento semanal del " + rangeDate);
		cell.setCellStyle(styleTitle);
		
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
		
		row = sheet.getRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Rodamiento semanal del " + rangeDate);
		cell.setCellStyle(styleTitle);
		
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
		
		sheet = wb.getSheet("S�BADO");
		
		row = sheet.getRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Rodamiento semanal del " + rangeDate);
		cell.setCellStyle(styleTitle);
		
		for(int i = 0; i < alToursSaturday.size(); i++) {
			row = sheet.getRow(i+7);
			
			for(int j = 0; j < 8; j++) {
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
		
		row = sheet.getRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Rodamiento semanal del " + rangeDate);
		cell.setCellStyle(styleTitle);
		
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
		 		
		String nameFile = rangeDate.replace(" ", "");
		String nameFile2 = nameFile.replace("al", "-");
		nameGeneral = "RodGen_" + nameFile2 + ".xlsx";
		
		FileOutputStream output = new FileOutputStream("C:\\Users\\DAVID  ROMERO M\\Desktop\\Gestor de rutas\\Rutas\\Generales\\" + nameGeneral);
		wb.write(output);
		output.close();
		wb.close();
	}
	
	void generateDocumentEachMobile() throws IOException {
		alActiveMobiles = plan.getActiveMobiles();
		
		FileInputStream file = new FileInputStream(new File("C:\\Users\\DAVID  ROMERO M\\Desktop\\Gestor de rutas\\Plantillas\\PlantillaPlanIndividual.xlsx"));
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		
		int numRow = 0;
		int numColumn = 0;
		String ownerName = "";
		
		XSSFFont fontOwner = wb.createFont();
		fontOwner.setFontHeightInPoints((short) 18);
		fontOwner.setFontName("Arial");
		
		XSSFFont fontMobile = wb.createFont();
		fontMobile.setFontHeightInPoints((short) 18);
		fontMobile.setFontName("Arial");
		fontMobile.setBold(true);
		
		XSSFFont fontTitle = wb.createFont();
		fontTitle.setFontHeightInPoints((short) 13);
		fontTitle.setFontName("Arial");
		fontTitle.setBold(true);
		
		XSSFFont fontNormal = wb.createFont();
		fontNormal.setFontHeightInPoints((short) 13);
		
		XSSFFont fontTitleWeek = wb.createFont();
		fontTitleWeek.setFontName("Arial");
		fontTitleWeek.setFontHeightInPoints((short) 14);
		
		CellStyle styleTitleWeek = wb.createCellStyle();
		styleTitleWeek.setAlignment(HorizontalAlignment.CENTER);
		styleTitleWeek.setVerticalAlignment(VerticalAlignment.CENTER);
		styleTitleWeek.setFont(fontTitleWeek);
		
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
			
			numRow = 3;
			
			row = sheet.getRow(numRow);
			cell = row.createCell(0);
			cell.setCellValue("Rodamiento semanal del " + rangeDate);
			cell.setCellStyle(styleTitleWeek);
			numRow++;
			
			ownerName = plan.getCompleteNameOwner(alActiveMobiles.get(i).get(0));
			
			row = sheet.getRow(numRow);
			cell = row.createCell(0);
			cell.setCellValue(ownerName.toUpperCase());
			cell.setCellStyle(styleOwner);
			numRow++;
			
			row = sheet.getRow(numRow);			
			cell = row.createCell(0);
			cell.setCellValue("M�vil " + alActiveMobiles.get(i).get(0));
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
			cell.setCellValue("Mi�rcoles");
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
			cell.setCellValue("S�bado");
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
		
		String nameFile = rangeDate.replace(" ", "");
		String nameFile2 = nameFile.replace("al", "-");
		nameEach = "RodInd _" + nameFile2 + ".xlsx";
		
		FileOutputStream output = new FileOutputStream("C:\\Users\\DAVID  ROMERO M\\Desktop\\Gestor de rutas\\Rutas\\Individuales\\" + nameEach);
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
