import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Server2 {
  static void main() {
    try (final var server =
        new ServerSocket(8081, 1, InetAddress.getByName("0.0.0.0"))) {
      while (true) {
        final var client = server.accept();
        new Thread(
                () -> {
                  try (client;
                      final var r =
                          new BufferedReader(new InputStreamReader(client.getInputStream()));
                      final var w = new PrintWriter(client.getOutputStream()); ) {
                    String line;
                    while ((line = r.readLine()) != null) {
                      final var modifiedLine = "\"" + line + "\"";
                      w.println(modifiedLine);
                      w.flush();
                    }
                  } catch (IOException _) {
                  }
                })
            .start();
      }
    } catch (IOException _) {
    }
  }
}
