import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class Receiver2 {
  static void main() {
    try (final var ds = new DatagramSocket(6868); ) {
      byte[] buffer = new byte[100];
      var dp = new DatagramPacket(buffer, buffer.length);
      while (true) {
        Arrays.fill(buffer, (byte)0);
        ds.receive(dp);
        var s = new String(dp.getData(), 0, dp.getLength());
        if (s.equals("Ende")) {
          break;
        }
        IO.println(s);
      }
    } catch (Exception _) {
    }
  }
}
