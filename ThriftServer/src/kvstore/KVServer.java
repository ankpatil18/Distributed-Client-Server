package kvstore;

import org.apache.thrift.server.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.transport.TServerTransport;

import java.util.HashMap;

import static java.lang.System.exit;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
public class KVServer {
	public static void main(String[] args){
		HashMap<String,String> store= new HashMap<String,String>();
		try{
			KVServiceHandler handler= new KVServiceHandler(store);
			KVStore.Processor processor= new KVStore.Processor(handler);
			TServerTransport transport= new TServerSocket(9090);
			TServer server=new TSimpleServer(new TServer.Args(transport).processor(processor));

			System.out.println("Starting with the server....");
			server.serve();
		}
		catch(Exception ex){
			System.out.println(ex.getStackTrace());
			exit(0);
		}

		exit(0);
	}
}

