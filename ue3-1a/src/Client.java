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

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
