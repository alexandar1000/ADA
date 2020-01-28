package extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.SourceFile;

import java.io.FileNotFoundException;
import java.util.Set;

public class SimpleParser {

	public String getParsedSourceInJSON(String src_dir, String file_path) throws FileNotFoundException {

		SourceParser sp=new SourceParser(src_dir, file_path);
		Set<SourceFile> sourceSet = sp.parseSource();
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = Obj.writeValueAsString(sourceSet);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

}
