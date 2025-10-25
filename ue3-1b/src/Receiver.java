final int PORT = 6868;
void main() {
    var buffer = new byte[100];
    try (var socket = new DatagramSocket(PORT)) {
        while (true) {
            try {
                socket.receive(new DatagramPacket(buffer, buffer.length));
                IO.println(new String(buffer, StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    } catch (SocketException e) {
        throw new RuntimeException(e);
    }
}
