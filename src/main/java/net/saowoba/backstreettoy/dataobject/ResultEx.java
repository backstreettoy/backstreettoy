package net.saowoba.backstreettoy.dataobject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.saowoba.backstreettoy.utils.check.NotNullChecker;

public class ResultEx {
	
	private ResultEntry finalResult;
	private LinkedHashMap<String, ResultEntry> subProcessResult;
	private ResultStrategy resultStrategy;
	
	/**
	 * 
	 * @param rs 根据各个子节点的返回结果生成最终结果的策略
	 */
	public ResultEx(ResultStrategy rs) {
		NotNullChecker.check(rs);
		this.resultStrategy = rs;
	}
	
	
	public ResultEntry generateNextResult() {
		int index = getSubProcessResult().size();
		return generateNextResult(String.valueOf(index));
	}
	
	public ResultEntry generateNextResult(String name) {
		ResultEntry ret = new ResultEntry();
		getSubProcessResult().put(name, ret);
		return ret;
	}
	
	
	public ResultEntry generateFinalResult() {
		this.finalResult = resultStrategy.generateFinalResult(getSubProcessResult());
		return this.finalResult;
	}

	public LinkedHashMap<String, ResultEntry> getSubProcessResult() {
		if(subProcessResult == null) {
			subProcessResult = new LinkedHashMap<String, ResultEx.ResultEntry>();
		}
		return subProcessResult;
	}
	
	
	/**
	 * 最终结果的生成策略
	 * @author shl
	 *
	 */
	public interface ResultStrategy extends Serializable {
		
		public ResultEntry generateFinalResult(LinkedHashMap<String, ResultEntry> subProcessResult);
	}
	

	public static class ResultEntry 
	implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -5017776963802581720L;
		
		private boolean success;
		private String message;
		private Map<String,Object> intermediateInfo;
		
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Map<String,Object> getIntermediateInfo() {
			if (intermediateInfo == null) {
				intermediateInfo = new HashMap<String, Object>();
			}
			return intermediateInfo;
		}
		public void addIntermediateInfo(String key, Object value) {
			getIntermediateInfo().put(key, value);
		}
		public void addIntermediateInfos(Map<String,Object> map) {
			getIntermediateInfo().putAll(map);
		}
		
	}
}
 