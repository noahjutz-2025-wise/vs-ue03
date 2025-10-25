import java.net.InetAddress;
import java.net.UnknownHostException;

public class Args {
    public static final int PORT = 6868;
    public static int parsePort(String[] args) {
        return switch (args.length) {
            case 1 -> Integer.parseInt(args[0]);
            case 2 -> Integer.parseInt(args[1]);
            default -> PORT;
        };
    }
    public static InetAddress parseAddr(String[] args) {
        try {
            return args.length == 2 ? InetAddress.getByName(args[0]) : InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
