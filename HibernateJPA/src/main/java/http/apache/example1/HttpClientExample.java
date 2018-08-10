package http.apache.example1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpClientExample {

	private String cookies;
	private HttpClient client = HttpClientBuilder.create().build();
	private final String USER_AGENT = "Mozilla/5.0";

	private static final Logger logger = LogManager.getLogger(HttpClientExample.class);
	
	public static void main(String[] args) throws Exception {

		String urls = "http://10.10.86.48/itop/webservices/export-v2.php?format=spreadsheet&login_mode=basic&date_format=Y-m-d+H%3Ai%3As&query=10";
		String url = "http://10.10.86.48/itop/pages/UI.php"; 

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		HttpClientExample http = new HttpClientExample();

		String page = http.GetPageContent(url);

//		logger.debug("page: "+page);
		
		List<NameValuePair> postParams = http.getFormParams(page, "yohan", "12345");

		logger.debug("postParams: "+postParams);
		
		http.sendPost(url, postParams);
		http.sendPost(urls, postParams);

//		String result = http.GetPageContent(urls);
//		System.out.println(result);

		System.out.println("Done");
	}

	private void sendPost(String url, List<NameValuePair> postParams) throws Exception {

		HttpPost post = new HttpPost(url);

		logger.debug(getCookies().toString());
		// add header
		post.setHeader("Host", "139.255.57.82");
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.setHeader("Accept-Language", "en-US,en;q=0.9,id;q=0.8,ms;q=0.7");
		post.setHeader("Cookie", getCookies());
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Referer", "http://139.255.57.82/itop/pages/logoff.php?portal=");
		post.setHeader("Content-Type", "text/html; charset=UTF-8");

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = client.execute(post);

		int responseCode = response.getStatusLine().getStatusCode();

		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// System.out.println(result.toString());

	}

	private String GetPageContent(String url) throws Exception {

		HttpGet request = new HttpGet(url);

		request.setHeader("User-Agent", USER_AGENT);
		request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		request.setHeader("Accept-Language", "en-US,en;q=0.9,id;q=0.8,ms;q=0.7");

		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// set cookies
		setCookies(
				response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString());

		
		return result.toString();

	}

	public List<NameValuePair> getFormParams(String html, String username, String password)
			throws UnsupportedEncodingException {

		System.out.println("Extracting form's data...");

		Document doc = Jsoup.parse(html);

		// Google form id
		Element loginform = doc.getElementById("login");		
		logger.debug("loginform: "+loginform);
		
		Elements inputElements = loginform.getElementsByTag("input");
		logger.debug("inputElements: "+inputElements);

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();

		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");

			if (key.equals("auth_user")) {
				value = username;
			} else if (key.equals("auth_pwd")) {
				value = password;
			} else {
				
			}

			if(key.equals("auth_user")||key.equals("auth_pwd")) {
				paramList.add(new BasicNameValuePair(key, value));
			}
			
			logger.debug("key: "+key+" - "+value);

		}

		return paramList;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}
