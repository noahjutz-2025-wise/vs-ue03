final int PORT = 6868;

void main() {
    var buffer = new byte[100];
    try (var socket = new DatagramSocket(PORT)) {
        while (true) {
            try {
                final var packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                var msg = new String(Arrays.copyOfRange(buffer, 0, packet.getLength()), StandardCharsets.UTF_8);
                IO.println(msg);
                if (msg.equals("Ende")) {
                    break;
                }
                Arrays.fill(buffer, (byte) 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    } catch (SocketException e) {
        throw new RuntimeException(e);
    }
}
