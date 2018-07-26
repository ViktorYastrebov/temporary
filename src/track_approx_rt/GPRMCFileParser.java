package track_approx_rt;

import java.io.*;

public class GPRMCFileParser {
	BufferedReader bf = null;
	
	public GPRMCFileParser(String path) {
		try {
			File f = new File(path); 
			bf = new BufferedReader(new FileReader(f));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void process() {
		GPRMCParser parser = new GPRMCParser();
		try {
			String line = bf.readLine();
			while(line != null) {
				if(parser.parse(line)) {
					System.out.println("Latitude : " + Double.toString(parser.getLat()) + 
							           " Longitude : " + Double.toString(parser.getLng()));
				}
				line = bf.readLine();
			}
			bf.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
