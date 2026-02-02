import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

public class ThreadpoolWebserver2 {
  static void main() {
    try (final var s = new ServerSocket(8081)) {
      while (true) {
        final var c = s.accept();
        new Thread(
                () -> {
                  try (c;
                      final var r = new BufferedReader(new InputStreamReader(c.getInputStream()));
                      final var w = new PrintWriter(c.getOutputStream()); ) {
                    String line;
                    final var header = new StringBuilder();
                    while ((line = r.readLine()) != null) {
                      header.append(line).append("\n");
                      if (line.isBlank()) break;
                    }
                    w.write(
                        String.format(
                            """
                          HTTP/1.1 200 OK
                          Content-Type: text/plain
                          Content-Length: %d

                          %s"""
                                .trim(),
                            header.toString().getBytes(StandardCharsets.UTF_8).length,
                            header));
                    IO.println(header);
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                })
            .start();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
