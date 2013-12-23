package strviola.injector.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public class HTTPPostClient extends HTTPBaseClient {

	private final ArrayList<BasicNameValuePair> params =
		new ArrayList<BasicNameValuePair>();

	public HTTPPostClient(String path) {
		super(path);
	}

	@Override
	public HTTPBaseClient addParameter(String key, String value) {
		params.add(new BasicNameValuePair(key, value));
		return this;
	}

	@Override
	public String execute() throws ConnectionException {

		try {
			// send post request
			HttpPost client = new HttpPost(url.toString());
			client.setEntity(new UrlEncodedFormEntity(params));
			return httpConnect(client);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;

		} catch (IOException e) {
			throw new ConnectionException();
		}
	}
}
