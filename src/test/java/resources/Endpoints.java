package resources;

public enum Endpoints {
	HealthCheckApi("/ping"),
	AuthApi("/auth"),
	BookingApi("/booking");
	
	private String endpoint;

	Endpoints(String endpoint) {
		this.endpoint=endpoint;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
}
