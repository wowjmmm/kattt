package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.service.TeamService;
@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamDao teamDao;
	@Override
	public Map<String, Object> findPageObjects(
			String projectName, 
			Integer pageCurrent) {
		System.out.println("projectName="+projectName);
		//1.进行业务逻辑参数验证
		if(pageCurrent==null||pageCurrent<=0)
		throw new ServiceException("当前页面值无效");
		//2.获取当前数据
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<Map<String,Object>> list=
		teamDao.findPageObjects(projectName,
				startIndex,
				pageSize);
		//3.获取总记录数,并计算封装分页信息
		int rowCount=
		teamDao.getRowCount(projectName);
		PageObject pageObject=new PageObject();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setStartIndex(startIndex);
		//4.封装当前页数据及分页信息到map集合.
		Map<String,Object> map=new HashMap<>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

}








