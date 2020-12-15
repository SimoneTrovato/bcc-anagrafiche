package servicemenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;

public class Test {

	static int controw = 0;
	static int depth =-1;
	static int max=0;
//metodo per settare valore celle 
	public static void ricorsivo(List<MenuNode> nodes, Sheet sheet) {
		if (nodes !=null) {
			depth++;
			for(MenuNode nodi : nodes) {
				//	System.out.println(depth);
				if(max<=depth) {
					max=depth;
				}
				Row row = sheet.createRow(++controw);
				int cellnum = -1;

				row.createCell(depth).setCellValue("X");

				if(nodi.nodeType.contains("service") ) {
					row.createCell(++cellnum+profondita(nodes, sheet)).setCellValue(nodi.nodeId);
				}
				else {
					++cellnum;
				}
				row.createCell(++cellnum+profondita(nodes, sheet)).setCellValue(nodi.nodeName);
				row.createCell(++cellnum+profondita(nodes, sheet)).setCellValue(nodi.nodeType);
				row.createCell(++cellnum+profondita(nodes, sheet)).setCellValue(nodi.groupType);
				row.createCell(++cellnum+profondita(nodes, sheet)).setCellValue(nodi.flowType);

				if( nodi.nodes == null & nodi.resource != null) {
					row.createCell(++cellnum+profondita(nodes, sheet)).setCellValue(nodi.resource.id);
					//	System.out.println(nodi.resource.id);
				}				
				ricorsivo(nodi.nodes, sheet);
			}				
			depth--;	
		}	
	}

//metodo per trovare massima profondità dei nodi
	public static int profondita(List<MenuNode> nodes, Sheet sheet) {
		if (nodes !=null) {
			depth++;
			for(MenuNode nodi : nodes) {
				//	System.out.println(depth);
				if(max<=depth) {
					max=depth;
				}				
				profondita(nodi.nodes, sheet);
			}				
			depth--;	
		}		//	System.out.println(max);
		return max;
	}

	public static void main(String[] args) throws IOException {

		Workbook wb = new HSSFWorkbook();
		Gson gson = new Gson();
		Reader reader;
		//leggo properties
		FileReader fReader=new FileReader(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"esercizio"+File.separator+"path.properties");
		Properties p = new Properties();
		p.load(fReader); 
	//	System.out.println(p.getProperty("inputFolder"));
		try {
			//leggo il path
			reader = Files.newBufferedReader(Paths.get(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"esercizio"+File.separator+p.getProperty("inputFolder")+File.separator+p.getProperty("jsonFile")));
			MenuContent mc = gson.fromJson(reader,MenuContent.class);			
			Sheet sheet = wb.createSheet("Menu ".concat(mc.getVersion()));

			List<String> colonne = new ArrayList<>();	
			colonne.addAll(Arrays.asList("ServiceId" , "NodeName", "NodeType", "GroupType" , "FlowType", "ResourceId"));	
			Row row = sheet.createRow(0);


			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setBold(true);
			style.setFont(font);

			//crea prime colonne numerate
			for(int i=0; i<=profondita(mc.getNodes(), sheet); i++) {
				Cell cell = row.createCell(i);{
					cell.setCellValue(i);
					cell.setCellStyle(style);
				}
			}
			//crea colonne con campi
			for( int i=0; i<colonne.size(); i++) {
				Cell cell = row.createCell(i+profondita(mc.getNodes(), sheet)+1);
				cell.setCellValue(colonne.get(i));
				cell.setCellStyle(style);

			}
			
			ricorsivo(mc.getNodes(), sheet);
//file output
			String path = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"esercizio"+File.separator+p.getProperty("outputFolder")+File.separator;
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			try  (OutputStream fileOut = new FileOutputStream(path.concat(p.getProperty("excelFile")))) 
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


