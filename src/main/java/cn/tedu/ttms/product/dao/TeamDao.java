package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Team;

public interface TeamDao {
      /**
       * 分页获取团目信息(例如环球游30日团,40日团,...)
       * 要求查询结果中包含项目名称
       * @param projectName 页面上的查询条件,
       *                    根据项目进行查询
       * @param startIndex  查询当前页数据的其实位置
       * @param pageSize    当前页面大小(
       *                    每页最多显示多少条数据)
       * @return
       */
	  List<Map<String,Object>> 
	  findPageObjects(
			  @Param("projectName")String projectName,
			  @Param("startIndex")Integer startIndex,
			  @Param("pageSize")Integer pageSize);
	  
	  int getRowCount(
			  @Param("projectName")String projectName);

	   
	  //int insertObject(Team entity);
	  //int updateObject(Team entity);
	  
}





