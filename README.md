#rest-ws

##Task description:

1. Implement REST endpoints for ticket booking operations(required) and user/event CRUD operations(optional) via annotation based controllers. When implementing controllers, use REST design principles.

2. Implement HttpMessageConverter which can write Ticket objects into application/pdf MIME type. Read support is not needed (canRead method should just return false)

3. Configure ContentNegotiationViewResolver via MVC namespace. Add two types of message converters - 1.newly implemented application/pdf http message converter. 2. Jackson JSON message converter.

4. Implement test client using RestTemplate. By default client should use JSON representation to communicate with REST service.

5. Create test HTTP requests that have Accept header value as application/pdf, test that content negotiation works properly, and PDF representation is returned on any ticket booking request that is supposed to return Ticket object or list of Ticlet objects.