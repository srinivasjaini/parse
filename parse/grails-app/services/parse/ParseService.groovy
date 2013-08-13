package parse

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class ParseService {
	
	def config = org.codehaus.groovy.grails.commons.ConfigurationHolder.config
	def transactional = false
	
	
	public String sendNotification(String requestData) throws Exception {
		HttpsURLConnection conn = getConnection();
		String response = "";
		try {

			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(requestData);
			wr.flush();
			wr.close();
			response= conn.getResponseMessage();
			System.out.println(" Response Received :" + response);
		}finally {
			if(conn!=null){
				conn.disconnect();
			}
		}
		return response;
	}

	
	
	private  HttpsURLConnection getConnection() throws IOException{
		URL url = new URL("https://api.parse.com/1/push");
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		
		 // Allow Inputs
		conn.setDoInput(true);

		// Allow Outputs
		conn.setDoOutput(true);

		// Don't use a cached copy.
		conn.setUseCaches(false);

		// Use a post method.
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		
		conn.setRequestProperty("X-Parse-Application-Id", config.parse.parseApplicationId)//"8rOOQ5Q2a54cTdhwcohTgenTeDsVojZToRHktguz");
		conn.setRequestProperty("X-Parse-REST-API-Key", config.parse.parseRestApiKey)// "p9u9RbMM088BPqa7u1L5gRdGcRmSnVYtaoipsKsK");
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}
	
}


