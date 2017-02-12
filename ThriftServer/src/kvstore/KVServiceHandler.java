package kvstore;

import org.apache.thrift.TException;

import java.util.HashMap;
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
public class KVServiceHandler implements KVStore.Iface {

	HashMap<String,String> store= new HashMap<String,String>();
	
	public KVServiceHandler(HashMap<String,String> store){
		this.store = store;
	}
	
	@Override
	public Result kvset(String key, String value) throws TException {
		Result result = new Result();
		try {
			store.put(key, value);
			result.value = value;
			result.error = ErrorCode.kSuccess;
		}catch(Exception ex){
			result.error = ErrorCode.kError;
			result.errortext = ex.getMessage();;
		}
		return result;
	}

	@Override
	public Result kvget(String key) throws TException {
		Result result = new Result();
		try{
			if(!key.isEmpty() && key != null ){
				if(store.containsKey(key)){
					result.value = store.get(key);
					result.error = ErrorCode.kSuccess;
					return result;
				}
				else{
					result.value= "Key not found";
					result.error = ErrorCode.kKeyNotFound;
					return result;
				}
			}
			result.value ="Key empty or null";
			result.error = ErrorCode.kError;
		}catch (Exception ex){
			result.errortext = ex.getMessage();
			result.error = ErrorCode.kError;
		}
		return result;
	}

	@Override
	public Result kvdelete(String key) throws TException {
		Result result = new Result();
		try{
			if(!key.isEmpty() && key != null ){
				if(store.containsKey(key)){
					store.remove(key);
					result.error = ErrorCode.kSuccess;
					return result;
				}
				else{
					result.value= "Key not found";
					result.error = ErrorCode.kKeyNotFound;
					return result;
				}
			}
			result.value ="Key empty or null";
			result.error = ErrorCode.kError;

		}catch (Exception ex){
			result.errortext =ex.getMessage();
			result.error = ErrorCode.kError;
		}
		return result;
	}

}
