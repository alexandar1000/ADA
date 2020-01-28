package extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.SourceFile;

import java.io.FileNotFoundException;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {

		
		//String SRC_DIRECTORY_PATH = "/home/mrhmisu/UCL-MS/Test-Project/src";
		String SRC_DIRECTORY_PATH="/home/mrhmisu/UCL-MS/ADA-test-simple-Java-project-master/src";

		String SRC_FILE_PATH = SRC_DIRECTORY_PATH + "/ServiceCentre.java";
		
		SourceParser sp=new SourceParser(SRC_DIRECTORY_PATH, SRC_FILE_PATH);
		Set<SourceFile> sourceSet=sp.parseSource();
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = Obj.writeValueAsString(sourceSet);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonStr);

	}

}
