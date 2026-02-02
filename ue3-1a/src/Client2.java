import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
  static void main() {
    try (final var c = new Socket(InetAddress.getByName("localhost"), 8081);
        final var inp = new Scanner(System.in);
        final var r = new BufferedReader(new InputStreamReader(c.getInputStream()));
        final var w = new PrintWriter(c.getOutputStream(), true); ) {
      while (inp.hasNextLine()) {
        final var line = inp.nextLine();
        w.println(line);
        IO.println(r.readLine());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
