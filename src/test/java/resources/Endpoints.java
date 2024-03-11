package resources;

public enum Endpoints {
	HealthCheckApi("/ping"),
	AuthApi("/auth"),
	CreateBookingApi("/booking"),
	BookingApi("/booking"),
	GetBookingApi("/booking"),
	UpdateBookingApi("/booking"),
	PartialUpdateBookingApi("/booking"),
	DeleteBookingApi("/booking");
	
	private String endpoint;

	Endpoints(String endpoint) {
		this.endpoint=endpoint;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
}
