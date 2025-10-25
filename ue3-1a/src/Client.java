void main(String[] args) {
    assert args.length < 3;
    var port = Args.parsePort(args);
    var host = Args.parseAddr(args);

    var scanner = new Scanner(System.in);
    try {
        var socket = new Socket(host, port);
        var writer = new PrintWriter(socket.getOutputStream());
        var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            var line = scanner.nextLine();
            writer.println(line);
            writer.flush();
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
