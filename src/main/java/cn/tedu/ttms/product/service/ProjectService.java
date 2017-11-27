package cn.tedu.ttms.product.service;
import java.util.Map;

import cn.tedu.ttms.product.entity.Project;
public interface ProjectService {
	/**
	 * 更新项目信息
	 * @param entity
	 * @return
	 */
	 void updateObject(Project entity);
	
	 /**
	  * 根据id查找project对象
	  * @param id
	  * @return
	  */
	 Project findObjectById(Integer id);
	 
	
	  /**
	   * 保存项目信息
	   * @param entity
	   */
	  void saveObject(Project entity);
	  
	  /**禁用启用项目信息*/
	  void validById(
			  String checkedIds,
			  Integer valid);
	  /**
	   * @param pageCurrent 当前页码的值
	   * @return 返回当前页数据以及分页信息
	   */
	  Map<String,Object> 
	  findPageObjects(String name,
			          Integer valid,
			          Integer pageCurrent);
}





