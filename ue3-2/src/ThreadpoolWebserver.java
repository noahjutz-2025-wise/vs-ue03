final int PORT = 8080;
void main() {
  try (
    final var server = new ServerSocket(PORT);
    final var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  ) {
    while (true) {
      final var socket = server.accept();
      final var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      final var writer = new PrintWriter(socket.getOutputStream());

      executor.execute(() -> {
        while (true) {
          try {
            var request = reader.readLine();
            if (request != null && request.equals("GET / HTTP/1.1")) {
              IO.println("Request received!");
              writer.write(
                "HTTP/1.1 200 OK\n" +
                  "Content-Type: text/html\n" +
                  "Hello world!"
              );
              writer.flush();
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      });
    }
  } catch (IOException e) {
    throw new RuntimeException(e);
  }
}
