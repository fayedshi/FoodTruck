package quiz.cosmos.model;

import quiz.cosmos.constants.ResponseStatus;

public class ResultBean<T> {
	private T data;
	private String errMsg;
	private ResponseStatus status;

	public ResultBean() {

	}

	public ResultBean(T data, String msg, ResponseStatus st) {
		this.data = data;
		this.errMsg = msg;
		this.status = st;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}
}
