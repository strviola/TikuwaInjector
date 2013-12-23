package strviola.injector.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;

public class HTTPGetClient extends HTTPBaseClient {
	private final HashMap<String, String> queryParam;

	public HTTPGetClient(String path) {
		super(path);
		this.queryParam = new HashMap<String, String>();
	}

	@Override
	public HTTPBaseClient addParameter(String key, String value) {
		queryParam.put(key, value);
		return this;
	}

	@Override
	public String execute() throws ConnectionException {
		String getUrl = url.toString();
		// http://www.example.com/path/to/method

		if (queryParam.size() > 0) {
			getUrl += "?";
			// http://www.example.com/path/to/method?

			for (Map.Entry<String, String> kv : queryParam.entrySet()) {
				getUrl += String.format("%s=%s&", kv.getKey(), kv.getValue());
				// http://www.example.com/path/to/method?key1=value1&key2=value2&...
			}
		}

		HttpGet client = new HttpGet(getUrl);
		try {
			return httpConnect(client);

		} catch (IOException e) {
			throw new ConnectionException();
		}
	}

}
