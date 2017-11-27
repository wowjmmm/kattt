package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeService {
	
	 void saveObject(ProductType entity);
	 
	 List<Node> findZtreeNodes();
	 List<Map<String,Object>> 
     findTreeGridNodes();
	 /**根据id执行分类删除操作*/
	 void deleteById(Integer id);
}
