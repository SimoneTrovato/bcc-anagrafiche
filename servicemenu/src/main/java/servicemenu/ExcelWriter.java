package servicemenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;

import content.MenuContent;
import content.MenuNode;

public class ExcelWriter {

	static int controw = 0;
	static int depth =-1;
	static int maxDepth=0;

	// metodo per settare valore celle 
	public static void recursive(List<MenuNode> nodes, Sheet sheet) {
		if (nodes !=null) {
			depth++;
			for(MenuNode currentNode : nodes) {

				Row row = sheet.createRow(++controw);
				int cellnum = 0;

				row.createCell(depth).setCellValue("X");

				if(currentNode.getNodeType().contains("service") ) {
					row.createCell(++cellnum+maxDepth).setCellValue(currentNode.getNodeId());
					sheet.autoSizeColumn(cellnum+maxDepth);

				}
				else {
					++cellnum;
				}
				row.createCell(++cellnum+maxDepth).setCellValue(currentNode.getNodeName());
				sheet.autoSizeColumn(cellnum+maxDepth);
				row.createCell(++cellnum+maxDepth).setCellValue(currentNode.getNodeType());
				sheet.autoSizeColumn(cellnum+maxDepth);

				row.createCell(++cellnum+maxDepth).setCellValue(currentNode.getGroupType());
				sheet.autoSizeColumn(cellnum+maxDepth);

				row.createCell(++cellnum+maxDepth).setCellValue(currentNode.getFlowType());
				sheet.autoSizeColumn(cellnum+maxDepth);

				if(currentNode.getNodes() == null & currentNode.getResource() != null) {
					row.createCell(++cellnum+maxDepth).setCellValue(currentNode.getResource().getId());	
					sheet.autoSizeColumn(cellnum+maxDepth);
				}				
				recursive(currentNode.getNodes(), sheet);
			}				
			depth--;	
		}	
	}

	// metodo per trovare massima profondit� dei nodi
	public static void calcMaxDepth(List<MenuNode> nodes) {
		if (nodes !=null) {
			depth++;
			for(MenuNode currentNode : nodes) {
				if(maxDepth<=depth) {
					maxDepth=depth;
				}				
				calcMaxDepth(currentNode.getNodes());
			}				
			depth--;	
		}	
	}

	public static void main(String[] args) throws IOException {

		XSSFWorkbook wb = new XSSFWorkbook();
		Gson gson = new Gson();
		Reader reader;
		//leggo properties
		FileReader fReader=new FileReader("."+File.separator+"path.properties");
		Properties p = new Properties();
		p.load(fReader); 

		try {
			//leggo il path
			Path inputPath = Paths.get("."+File.separator+p.getProperty("inputFolder"), p.getProperty("jsonFile"));

			reader = Files.newBufferedReader(inputPath);
			MenuContent mc = gson.fromJson(reader,MenuContent.class);			
			Sheet sheet = wb.createSheet("Menu ".concat(mc.getVersion()));

			List<String> colonne = new ArrayList<>();	
			colonne.addAll(Arrays.asList("ServiceId" , "NodeName", "NodeType", "GroupType" , "FlowType", "ResourceId"));	
			Row row = sheet.createRow(0);

			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setBold(true);
			style.setFont(font);

			// calcolo la massima profondità dell'albero e lo setto 
			// nella variabile profonditaMax
			calcMaxDepth(mc.getNodes());

			// crea prime colonne numerate
			for(int i=0; i<=maxDepth; i++) {
				Cell cell = row.createCell(i);{
					cell.setCellValue(i);
					cell.setCellStyle(style);
					sheet.autoSizeColumn(i);
				}
			}
			//crea colonne con campi
			for( int i=0; i<colonne.size(); i++) {
				Cell cell = row.createCell(i+maxDepth+1);
				cell.setCellValue(colonne.get(i));
				cell.setCellStyle(style);
				sheet.autoSizeColumn(i+maxDepth+1);

			}

			recursive(mc.getNodes(), sheet);
			//file output			
			Path outputFile = Paths.get("."+File.separator+p.getProperty("outputFolder"));
			String output = outputFile.toString();
			File file = new File(outputFile.toString());
			if(!file.exists()) {
				file.mkdirs();
			}
			try  (FileOutputStream fileOut = new FileOutputStream(output.concat(File.separator).concat(p.getProperty("excelFile"))))
			{

				wb.write(fileOut);
				wb.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch(NoSuchFileException n) {
			System.out.println("PATH DI INPUT ERRATO");
		}
		System.out.println("finito");	}
}


