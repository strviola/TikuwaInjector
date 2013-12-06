package strviola.injector.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public abstract class HTTPBaseClient {

	protected URL url;
	// select localhost or tikuwa site
	public static boolean LOCAL = false;

	// tikuwa server status
	public static final String TIKUWA_USER_AGENT = "TikuwaAppADTE2209";
	public static final String TIKUWA_SCHEME = "http"; // possible "https"
	public static final String TIKUWA_DOMAIN = "tikuwa.adte.tv";
	// test for localhost
	public static final String LOCAL_HOST = "10.0.2.2";
	public static final int LOCAL_PORT = 8000;

	// cookie and context
	private static BasicCookieStore cookieStore = new BasicCookieStore();
	private static BasicHttpContext localContext = new BasicHttpContext();

	protected HTTPBaseClient(String scheme, String domain, String path) {
		try {
			this.url = new URL(scheme, domain, path);

		} catch (MalformedURLException e) {
			System.err.println(String.format("Given URL %s://%s/%s is invalid!",
				scheme, domain, path));
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
	protected String httpConnect(HttpRequestBase method)
		throws IOException {

		// set user-agent for security
		
		HttpClient client = HttpClients.createDefault();
		method.setHeader("user_agent", TIKUWA_USER_AGENT);
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		final String res;
		System.out.println("Start connecting " + method.getURI());
		res = client.execute(method, new ResponseHandler<String>() {

			@Override
			public String handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
				System.out.println("execute request");
				String result = EntityUtils.toString(response.getEntity());

				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// connection success
					System.out.println("Connection success!");

				} else {
					// connection failed - print logs
					System.out.println(response.getStatusLine().toString());
					System.out.println(result);

				}
				return result;

			}

		}, localContext);

		((AbstractHttpClient) client).close();
		System.out.println(res.toString());
		return res;
	}
}
