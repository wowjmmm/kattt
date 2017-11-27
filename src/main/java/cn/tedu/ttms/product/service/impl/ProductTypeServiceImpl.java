package cn.tedu.ttms.product.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Resource
	private ProductTypeDao productTypeDao;
	@Override
	public void saveObject(ProductType entity) {
		//1.对数据进行业务逻辑验证
		if(entity==null)
		throw new ServiceException("保存数据不能为空");
		//2.保存数据到数据库
		int rows=productTypeDao.insertObject(entity);
		//3.验证结果
		if(rows<=0)
		throw new ServiceException("保存失败");
	}
	@Override
	public List<Node> findZtreeNodes(){
		return productTypeDao.findZtreeNodes();
	}
	
	@Override
	public List<Map<String, Object>> 
	                findTreeGridNodes() {
		return productTypeDao.findTreeGridNodes();
	}
	
	
	@Override
	public void deleteById(Integer id) {
		//1.判定id的有效性
		if(id==null||id<1)
		throw new ServiceException("id值无效");
		//2.统计此id对应的记录的子元素
		int count=productTypeDao.hasChilds(id);
		if(count>0)
		throw new ServiceException("此记录有子元素不允许删除");
		//3.删除此元素
		int rows=productTypeDao.deleteById(id);
		//4.验证删除结果
		if(rows<=0)
		throw new ServiceException("删除失败,可能此元素已经不存在");
	}
}










