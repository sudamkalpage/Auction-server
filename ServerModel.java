import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class ServerModel {

	static HashMap<String, Item> map = new HashMap<String, Item>();
	static HashMap<Integer, Item> bid_log = new HashMap<Integer, Item>();
	static Integer log_iterator = 0;
	static String line = "";

	public static void readCSV(String filename) {
		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");

				// create an object
				Item new_item = new Item();
				new_item.setSymbol(row[0]);
				new_item.setSecurity_name(row[1]);
				new_item.setMarket_category(row[2]);
				new_item.setTest_issue(row[3]);
				new_item.setFinancial_status(row[4]);
				new_item.setRound_lot_size(row[5]);
				if (row.length > 6)
					new_item.setOther(row[6]);

				// create instance of Random class
				Random rand = new Random();
				double random_price = Math.round(rand.nextDouble() * 10000.0);
				new_item.setPrice(random_price / 100.0);

				// create object hash map
				map.put(new_item.getSymbol(), new_item);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void newLog(String symbol, double new_price, String client_name) {
		//add to bid_log
		log_iterator++;
		Item new_log = new Item();
		new_log.setSymbol(symbol);
		new_log.setPrice(new_price);
		new_log.setBid_caller(client_name);
		bid_log.put(log_iterator, new_log);

		//update item hash map 
		ServerModel.map.get(symbol).setPrice(new_price);
		ServerModel.map.get(symbol).setBid_caller(client_name);
		
		//update GUI 
		ServerView.UpdateLog(symbol, new_price, client_name);
	}

	
	public HashMap<String, Item> getMap() {
		return map;
	}
	public static void setMap(HashMap<String, Item> map) {
		ServerModel.map = map;
	}

}
