package cloud.autotests.tests.api;

public class RequestAuthSuccessModelPojo {
	String access_token;
	String refresh_token;
	String scope;
	String token_type;
	Integer expires_in;

	public RequestAuthSuccessModelPojo(String access_token, String refresh_token, String scope, String token_type, Integer expires_in) {
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.scope = scope;
		this.token_type = token_type;
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public String getScope() {
		return scope;
	}

	public String getToken_type() {
		return token_type;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
}