package codigo;

import java.io.*;
import java.io.IOException;

public class Arquivo {

	public String lerArquivo() throws IOException  {
		String dir = System.getProperty("user.dir");
		FileReader arq = new FileReader(dir + "\\ip.txt");
		try (BufferedReader lerArq = new BufferedReader(arq)) {
			String linha = lerArq.readLine();
			return linha;
		}
		
	}
	
}
