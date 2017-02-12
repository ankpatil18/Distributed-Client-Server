package kvstore;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TTransport;

import static java.lang.System.exit;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
public class KVClient {

    public static void main(String[] args) {
        String[] server_port = args[1].split(":");
        String cmd = args[2];
        Result res = new Result();
        try {
            TTransport transport = new TSocket(server_port[0], Integer.parseInt(server_port[1]));
            TProtocol protocol = new TBinaryProtocol(transport);
            KVStore.Client client = new KVStore.Client(protocol);
            transport.open();
            try {
                try {
                    switch (cmd) {
                        case "-set":
                            res = client.kvset(args[3], args[4]);
                            if(res.error == ErrorCode.kSuccess) {
                            System.out.printf("Value for the given key has been set: %s   " , res.getValue());
                        }
                            break;

                        case "-get":
                            res = client.kvget(args[3]);
                            if(res.error == ErrorCode.kSuccess) {
                                System.out.printf("Value for the given key :%s  ", res.getValue());
                            }
                            break;

                        case "-del":
                            res = client.kvdelete(args[3]);
                            if(res.error == ErrorCode.kSuccess) {
                                System.out.print("Given Key Value pair has been deleted  ");
                            }
                            break;

                        default:
                            System.out.print("Invalid option");
                            exit(ErrorCode.kError.ordinal());
                            break;
                    }
                    if (res.error != ErrorCode.kSuccess) {
                        if (res.error == ErrorCode.kKeyNotFound)
                            System.out.print("Key Not Found");
                        else
                            System.out.print("Error");
                    }
                } catch (Exception io) {
                    System.err.print("Invalid Operation:" + io.toString());
                }
            } finally {
                transport.close();
            }

        } catch (Exception ex) {
            System.err.print("Invalid Operation:" + ex.toString());
        }
        exit(res.error.ordinal());
    }



}

