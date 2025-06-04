package Projects.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	

	public List<HashMap<String, String>> getJsonDataToMao() throws IOException {
		
		//Reading json to String 
		String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\Projects\\Data\\PurchaseOrder.json"), 
				StandardCharsets.UTF_8);
		
		//Converting  string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
		
		//We are asking the jason to red the data and create a new list of hashmaps. 
		List<HashMap<String, String>> data = mapper.readValue(JsonContent, 
				new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;

	}
}
