package quiz.cosmos.util;

import quiz.cosmos.constants.ResponseStatus;
import quiz.cosmos.model.ResultBean;

public class Util {
	public static <T> ResultBean<T> buildResultBean(T data, String errMsg, ResponseStatus rs) {
		return new ResultBean<T>(data, errMsg, rs);
	}
}
