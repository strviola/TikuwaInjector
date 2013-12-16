package strviola.injector.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public abstract class HTTPBaseClient {

	protected URL url;
	// select localhost or tikuwa site
	public static boolean LOCAL = true;

	// tikuwa server status
	public static final String TIKUWA_USER_AGENT = "TikuwaAppADTE2209";
	public static final String TIKUWA_SCHEME = "http"; // possible "https"
	public static final String TIKUWA_DOMAIN = "tikuwa.adte.tv";
	// test for localhost
	public static final String LOCAL_HOST = "127.0.0.1";
	public static final int LOCAL_PORT = 8000;

	// cookie and context
	private static BasicCookieStore cookieStore = new BasicCookieStore();
	private static HttpClientContext localContext = HttpClientContext.create();

	protected HTTPBaseClient(String scheme, String domain, String path) {
		try {
			this.url = new URL(scheme, domain, path);

		} catch (MalformedURLException e) {
			System.err.println(String.format(
					"Given URL %s://%s/%s is invalid!", scheme, domain, path));
		}
	}

	protected HTTPBaseClient(String path) {
		try {
			if (LOCAL) {
				this.url = new URL(TIKUWA_SCHEME, LOCAL_HOST, LOCAL_PORT, path);
			} else {
				this.url = new URL(TIKUWA_SCHEME, TIKUWA_DOMAIN, path);
			}

		} catch (MalformedURLException e) {
			System.err.println(String.format(
					"Given URL http://tikuwa.adte.tv/%s is invalid!", path));
		}
	}

	/**
	 * HTTPリクエストにパラメータを追加する。GETとPOSTで実装が異なる
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract HTTPBaseClient addParameter(String key, String value);

	public HTTPBaseClient addParameter(String key, int value) {
		return addParameter(key, String.valueOf(value));
	}

	public abstract String execute() throws ConnectionException;

	/**
	 * HTTPリクエストを処理
	 * 
	 * @param method
	 *            クライアント (URL, request parameter)
	 * @return レスポンス
	 * @throws IOException
	 *             通信に失敗した時
	 * @throws ConnectionException
	 * @throws ClientProtocolException
	 */
	protected String httpConnect(HttpRequestBase method) throws IOException {

		// set user-agent for security
		method.setHeader("USER_AGENT", TIKUWA_USER_AGENT);

		// make client with cookie
		CloseableHttpClient client = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		localContext.setCookieStore(cookieStore);

		System.out.println("Start connecting " + method.getURI());
		String res = client.execute(method, new ResponseHandler<String>() {

			@Override
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				System.out.println("execute request");
				String result = EntityUtils.toString(response.getEntity());

				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// connection success
					System.out.println("Connection success!");

				} else {
					// connection failed - output logs
					System.err.println(response.getStatusLine().toString());
					OutputStreamWriter errorWriter = new OutputStreamWriter(
							new FileOutputStream("error.html"), "utf-8");
					errorWriter.write(result);
					errorWriter.close();
				}
				return result;
			}

		}, localContext);

		client.close();
		return res;
	}
}
