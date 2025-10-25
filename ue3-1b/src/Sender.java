final int RECEIVER_PORT = 6868;

void main() {
    try (var socket = new DatagramSocket()) {
        var scanner = new Scanner(System.in);
        while (true) {
            var line = scanner.nextLine();
            var bytes = line.getBytes(StandardCharsets.UTF_8);
            try {
                socket.send(
                        new DatagramPacket(bytes, bytes.length,
                                InetAddress.getLocalHost(),
                                RECEIVER_PORT
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    } catch (SocketException e) {
        throw new RuntimeException(e);
    }
}