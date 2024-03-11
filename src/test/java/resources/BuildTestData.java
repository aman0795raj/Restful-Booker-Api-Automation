package resources;

import ReqPojo.AuthApiReqPojo;

public class BuildTestData {
	public AuthApiReqPojo TokenPayload(String username,String password) {
		AuthApiReqPojo AuthReqPayload = new AuthApiReqPojo();
		AuthReqPayload.setUsername(username);
		AuthReqPayload.setPassword(password);
		return AuthReqPayload;
	}

}
