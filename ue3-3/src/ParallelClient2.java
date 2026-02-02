import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ParallelClient2 {
  static void main() {
    try (final var c = new Socket(InetAddress.getLocalHost(), 6868);
        final var sc = new Scanner(System.in);
        final var r = new BufferedReader(new InputStreamReader(c.getInputStream()));
        final var w = new PrintWriter(c.getOutputStream()); ) {
      new Thread(() -> {
        String line;
        while (true) {
          try {
            if ((line = r.readLine()) == null) break;
          } catch (IOException e) {
            throw new RuntimeException(e);
          }

          IO.println(line);
        }
      }).start();
      while (sc.hasNextLine()) {
        w.println(sc.nextLine());
        w.flush();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
