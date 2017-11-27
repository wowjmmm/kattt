package beans.product;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;

public class TestProjectService {
     ClassPathXmlApplicationContext ctx;
	 @Before
	 public void init(){
		 ctx=new ClassPathXmlApplicationContext(
		"spring-mvc.xml","spring-mybatis.xml");
	 }
	 @Test
	 public void testSaveObject(){
		 ProjectService projectService=
		 ctx.getBean("projectServiceImpl",
				 ProjectService.class);
		 Project p=new Project();
		 p.setName("东欧游");
		 p.setCode("tt-20171116-CN-BJ-001");
		 p.setBeginDate(new Date());//java.util.Date
		 p.setEndDate(new Date());
		 p.setValid(1);
		 p.setNote("东欧游....");
		 projectService.saveObject(p);
		 System.out.println("insert ok");
	 }
	 
	 @Test
	 public void testFindPageObjects(){
		 ProjectService projectService=
				 ctx.getBean("projectServiceImpl",
						 ProjectService.class);
	     
	 }
	 
	 @After
	 public void destroy(){
		 ctx.close();
	 }
}
