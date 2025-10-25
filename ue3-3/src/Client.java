void main() {
  final var scanner = new Scanner(System.in);
  try (var socket = new Socket(InetAddress.getLocalHost(), 6868)) {
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
}
