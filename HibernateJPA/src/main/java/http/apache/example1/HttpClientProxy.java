package http.apache.example1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
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

public class HttpClientProxy {

	private String cookies;
	private HttpClient client = HttpClientBuilder.create().build();
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36";

	private static final Logger logger = LogManager.getLogger(HttpClientExample.class);

	public static void main(String args[]) throws Exception {

		String urls = "http://139.255.57.82/itop/webservices/export-v2.php?format=spreadsheet&login_mode=basic&date_format=Y-m-d+H%3Ai%3As&query=10";
		String url = "http://139.255.57.82/itop/pages/UI.php";

		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());

		HttpClientProxy http = new HttpClientProxy();

		String page = http.GetPageContent(url);

		List<NameValuePair> postParams = http.getFormParams(page, "yohan", "12345");

		logger.debug("postParams: " + postParams);

//		http.sendPost(url, postParams);
		http.sendGet(urls);

		logger.debug("done...");

	}

	private RequestConfig getRequestConfig() {
		HttpHost proxy = new HttpHost("10.10.8.118", 8080, "http");

		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

		return config;

	}

	private String GetPageContent(String url) throws Exception {

		HttpHost target = new HttpHost(url, 80, "http");

		HttpGet request = new HttpGet(url);
		request.setConfig(getRequestConfig());
		
		// add header
		request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		request.setHeader("Accept-Encoding","gzip, deflate");
		request.setHeader("Accept-Language", "en-US,en;q=0.9,id;q=0.8,ms;q=0.7");
		request.setHeader("Cache-Control", "max-age=0");		
		request.setHeader("Host", "139.255.57.82");
		request.setHeader("Proxy-Connection", "keep-alive");
		request.setHeader("Upgrade-Insecure-Requests", "1");
		request.setHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();

		logger.debug("\nSending 'GET' request to URL : " + url);
		logger.debug("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// set cookies
		setCookies(
				response.getFirstHeader("Set-Cookie") == null ? "" : response.getFirstHeader("Set-Cookie").toString());

		// logger.debug("result: " +result);
		logger.debug("cookies: " + getCookies());

		return result.toString();

	}

	public List<NameValuePair> getFormParams(String html, String username, String password)
			throws UnsupportedEncodingException {

		System.out.println("Extracting form's data...");

		Document doc = Jsoup.parse(html);

		// Google form id
		Element loginform = doc.getElementById("login");

		Elements inputElements = loginform.getElementsByTag("input");
		logger.debug("inputElements: " + inputElements);

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

			if (key.equals("auth_user") || key.equals("auth_pwd")) {
				paramList.add(new BasicNameValuePair(key, value));
			}

			logger.debug("key: " + key + " - " + value);

		}

		return paramList;
	}

	private void sendPost(String url, List<NameValuePair> postParams) throws Exception {

		HttpPost post = new HttpPost(url);
		post.setConfig(getRequestConfig());

		// add header
		// add header
		post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.setHeader("Accept-Encoding","gzip, deflate");
		post.setHeader("Accept-Language", "en-US,en;q=0.9,id;q=0.8,ms;q=0.7");
		post.setHeader("Cache-Control", "max-age=0");		
		post.setHeader("Cookie", getCookies());
		post.setHeader("Host", "139.255.57.82");
		post.setHeader("Proxy-Connection", "keep-alive");
		post.setHeader("Upgrade-Insecure-Requests", "1");
		post.setHeader("User-Agent", USER_AGENT);

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = client.execute(post);

		int responseCode = response.getStatusLine().getStatusCode();

		logger.debug("\nSending 'POST' request to URL : " + url);
		logger.debug("Post parameters : " + postParams);
		logger.debug("Response Code : " + responseCode);
		logger.debug(getCookies().toString());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// System.out.println(result.toString());

	}

	private void sendGet(String url) throws Exception {

		HttpGet get = new HttpGet(url);
		get.setConfig(getRequestConfig());

		// add header
		// add header
		get.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		get.setHeader("Accept-Encoding", "gzip, deflate");
		get.setHeader("Accept-Language", "en-US,en;q=0.9,id;q=0.8,ms;q=0.7");
		get.setHeader("Authorization", "Basic eW9oYW46MTIzNDU=");
		get.setHeader("Cache-Control", "max-age=0");
		get.setHeader("Cookie", getCookies());
		get.setHeader("Host", "139.255.57.82");
		get.setHeader("Proxy-Connection", "keep-alive");
		get.setHeader("Upgrade-Insecure-Requests", "1");
		get.setHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(get);

		int responseCode = response.getStatusLine().getStatusCode();

		logger.debug("\nSending 'GET' request to URL : " + url);
		logger.debug("Response Code : " + responseCode);
		logger.debug(getCookies().toString());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		 System.out.println(result.toString());

	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}
