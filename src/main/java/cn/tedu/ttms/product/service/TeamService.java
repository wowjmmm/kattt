package cn.tedu.ttms.product.service;

import java.util.Map;

public interface TeamService {
	/***
	 * 分页查询图团目信息
	 * @param projectName
	 * @param pageCurrent
	 * @return
	 */
	Map<String,Object> findPageObjects(
			String projectName,
			Integer pageCurrent);
	
	
	
}
