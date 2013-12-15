package strviola.injector.http;

public class RequestSender {

	private static enum Method {
		get, post,
	}

	private static String senderBase(Method type, String path, String[]... args) {

		HTTPBaseClient client;
		switch (type) {
		case get:
			client = new HTTPGetClient(path);
			break;
		case post:
			client = new HTTPPostClient(path);
			break;
		default:
			client = null;
			break;
		}

		for (String[] pair : args) {
			client.addParameter(pair[0], pair[1]);
		}

		if (client != null) {
			try {
				return client.execute();
			} catch (ConnectionException e) {
				e.printStackTrace();
				return e.toString();
			}
		} else {
			return null;
		}
	}

	public static String sendLogin(String token) {
		return senderBase(Method.post, "/auth/login/",
				new String[]{"token", token});
	}

	public static String sendLogin(String name, String psw) {
		return senderBase(Method.post, "/auth/info/", 
				new String[]{"gmail", name},
				new String[]{"password", psw});
	}

	public static String sendTikuwa(String id, String num) {
		return senderBase(Method.post, "/tikuwa/update/",
				new String[]{"tid", id},
				new String[]{"hasnum", num});
	}

	public static String sendGetMoney() {
		return senderBase(Method.post, "/tikuwa/money/");
	}

	public static String sendMoney(String userID, String money) {
		return senderBase(Method.post, "/user/friend/",
				new String[]{"uid", userID},
				new String[]{"money", money});
	}

	public static void main(String[] args) {
		// Secret.java is ignored! :-P
		sendLogin(Secret.gmail2, Secret.password2);
		sendTikuwa("0", "15");
	}
}
