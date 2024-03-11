package resources;

public enum Endpoints {
	HealthCheckApi("/ping");
	private String endpoint;

	Endpoints(String endpoint) {
		this.endpoint=endpoint;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
}
