final int PORT = 6868;

void main(String[] args) throws UnknownHostException {
    assert args.length < 3;
    var port = switch (args.length) {
        case 1 -> Integer.parseInt(args[0]);
        case 2 -> Integer.parseInt(args[1]);
        default -> PORT;
    };
    var host = args.length == 2 ? InetAddress.getByName(args[0]) : InetAddress.getLocalHost();
    try (var serverSocket = new ServerSocket(port, 1, host)) {
        while (true) {
            var socket = serverSocket.accept();
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var writer = new PrintWriter(socket.getOutputStream());

            writer.println("Hallo"); // oder print(("Hallo\n");
            writer.flush();

            String antwort = reader.readLine();
            System.out.println("Antwort der Gegenseite: " + antwort);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
