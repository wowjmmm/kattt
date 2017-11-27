package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.entity.ProductType;

/**产品类目管理持久层对象*/
public interface ProductTypeDao {
	
	  int insertObject(ProductType entity);
	
	  /**获取所有分类信息,这些信息在
	   * 页面上要以zTree结构显示,
	   * 使用一个Node对象代表树结构
	   * 中的一个节点信息*/
	  List<Node> findZtreeNodes();
	
      /**查询所有分类及这个分类的父类信息(
       * 只要获取父类名称即可)
       * 说明:此模块不做分页查询
       * */
	  List<Map<String,Object>> 
	     findTreeGridNodes();
	  
	  /**根据id删除对应记录
	   * @param id 表示某条记录的id
	   * 1)假如此分类下还有子元素不允许删除
	   * 2)假如此分类下有产品也不允许删除(暂时先不考虑)
	   * */
	  int deleteById(Integer id);
	  /**
	   * 统计此id下对应的子分类,返回值为分类的个数
	   * @param id
	   * @return
	   */
	  int hasChilds(Integer id);
	  
}





