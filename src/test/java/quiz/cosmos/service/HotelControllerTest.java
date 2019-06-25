package quiz.cosmos.service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HotelControllerTest {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws Exception {
		// comment others to test one
		testRooms();
		testBookRoom();
	}

	private static void prepForGet(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		URIBuilder builder = new URIBuilder(url);
		for (String key : params.keySet()) {
			builder.addParameter(key, params.get(key).toString());
		}
		URI uri = builder.build();
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = client.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity, "utf-8"));
			EntityUtils.consume(entity);
		}
	}
	
	private static void testBookRoom() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("fromDate", "2019-05-08");
		params.put("toDate", "2019-05-25");
		params.put("userId", "2");
		params.put("roomId", "1");
		String url = "http://localhost:8089/reserve";
		prepForGet(url, params);
	}

	// test find available rooms
	private static void testRooms() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("city", "SZ");
		params.put("fromDate", "2019-02-05");
		params.put("toDate", "2019-04-21");
		params.put("low", "200");
		params.put("high", "900");
		String url = "http://localhost:8089/rooms";
		prepForGet(url, params);
	}
}
