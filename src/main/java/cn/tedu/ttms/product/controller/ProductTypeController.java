package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@Controller
@RequestMapping("/type/")
public class ProductTypeController {
	 private Logger log=Logger.getLogger(
	 ProductTypeController.class.getSimpleName());
     @Autowired
	 private ProductTypeService productTypeService;
     
     @RequestMapping("listUI")
     public String listUI(){
    	 return "product/type_list";
     }
     @RequestMapping("editUI")
     public String editUI(){
    	 return "product/type_edit";
     }
     @RequestMapping("doSaveObject")
     @ResponseBody
     public JsonResult doSaveObject(ProductType entity){
    	 productTypeService.saveObject(entity);
    	 return new JsonResult();
     }
     
     @RequestMapping("doFindZtreeNodes")
     @ResponseBody
     public JsonResult doFindZtreeNodes(){
    	 return new JsonResult(
    	productTypeService.findZtreeNodes());
     }
     
     @RequestMapping("doDeleteById")
     @ResponseBody
     public JsonResult doDeleteById(Integer id){
    	 //System.out.println("id="+id);
    	 productTypeService.deleteById(id);
    	 return new JsonResult();//{state:1,message:ok}
     }
     @RequestMapping("doFindTreeGridNodes")
     @ResponseBody
	 public JsonResult doFindTreeGridNodes(){
    	 List<Map<String,Object>> list=
    	 productTypeService.findTreeGridNodes();
    	 //System.out.println(list);
    	 log.log(Level.INFO, list.toString());
		 return new JsonResult(list);
	 }
}





