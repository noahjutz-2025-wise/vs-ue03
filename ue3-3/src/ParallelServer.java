void main(String[] args) {
  final var port = Args.parsePort(args);
  final var host = Args.parseAddr(args);

  final var scanner = new Scanner(System.in);
  try (var server = new ServerSocket(port, 1, host)) { // +
    try (var socket = server.accept()) {
      final var writer = new PrintWriter(socket.getOutputStream());
      final var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      new Thread(() -> {
        while (true) {
          try {
            final var line = reader.readLine();
            IO.println(line);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }).start();
      while (true) {
        final var line = scanner.nextLine();
        writer.println(line);
        writer.flush();
      }
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  } catch (IOException e) {
    throw new RuntimeException(e);
  }
}