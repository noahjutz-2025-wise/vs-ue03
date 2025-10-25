import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server {
    public static void main(String[] args) {
        assert args.length < 3;
        var port = Args.parsePort(args);
        var host = Args.parseAddr(args);
        try (var serverSocket = new ServerSocket(port, 1, host)) {
            while (true) {
                var socket = serverSocket.accept();
                var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var writer = new PrintWriter(socket.getOutputStream());

                new Thread(() -> {
                    while (true) {
                        try {
                            var line = reader.readLine();
                            writer.println(line.chars().map(c -> c + 1)
                                    .collect(
                                            StringBuilder::new,
                                            (sb, i) -> sb.append((char) i),
                                            StringBuilder::append
                                    )
                                    .toString());
                            writer.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}