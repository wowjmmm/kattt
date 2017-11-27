$(document).ready(function(){
	doGetObjects();
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-valid,.btn-invalid",doValidById)
	.on("click",".btn-add,.btn-update",doShowEditDialog)
});
function doShowEditDialog(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加项目信息"
	}
	if($(this).hasClass("btn-update")){
		//获取button所在行的id值
		var id=$(this).parent().parent().data("id");
		//将id值绑定到模态框上(根据此id判定要执行添加还是修改)
		$("#modal-dialog").data("id",id);
		title="修改项目信息,id="+id;
	}
	var url="project/editUI.do";//project_edit.jsp
	$("#modal-dialog .modal-body")
	.load(url,function(){
		$("#myModalLabel").html(title);
		//显示模态框
		$("#modal-dialog").modal('show');
	});
}

/*禁用或启用项目信息*/
function doValidById(){
	console.log("doValidById");
	//1.获取向服务端要传递的参数数据
	//1.1根据操作(禁用,启动)设置状态信息
	var valid;
	if($(this).hasClass("btn-valid")){
		valid=1;
	}
	if($(this).hasClass("btn-invalid")){
		valid=0;
	}
	console.log("valid="+valid);
	//1.2获取选中(checkbox)的项目id值
	var checkedIds=getCheckedIds();
	if(checkedIds==""){
		alert("请至少选择一个");
		return;
	}
	//2.发起异步请求,禁用或启用项目信息?
	var url="project/doValidById.do";
	var params={"checkedIds":checkedIds,"valid":valid};
	$.post(url,params,function(result){
		if(result.state==1){
			alert("update ok");
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}



function getCheckedIds(){
	var checkedIds="";
	$("#tbodyId input[name=checkItem]")
	.each(function(){
		//this 代表的是input对象
		if($(this).prop("checked")){//checked等于true表示选中了
			if(checkedIds==""){//1,2,3,4
				checkedIds+=$(this).val();
			}else{
				checkedIds+=","+$(this).val();
			}
		}
	});
	console.log("checkedIds="+checkedIds);
	return checkedIds;
}
function doQueryObjects(){
	//1.初始化当前页码信息
	$('#pageId').data("pageCurrent",1);
	//2.执行查询操作
	//2.1获得表单数据
	//2.2提交表单数据执行查询
	doGetObjects();
}
/*获取表单数据*/
function getQueryFormData(){
	var params={
		"name":$("#searchNameId").val(),
		"valid":$("#searchValidId").val()
	}
	return params;
}
/*异步(ajax)加载服务端数据*/
function doGetObjects(){
	
	//1.定义访问项目信息的url
	var url="project/doFindPageObjects.do";
	//2.获取表单数据(查询时用)
	var params=getQueryFormData();
	//3.动态设置分页页码数据
	var pageCurrent=$('#pageId').data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	params.pageCurrent=pageCurrent;
	//4.发起异步ajax请求{name:"",valid:"",pageCurrent:1}
	$.get(url,params,function(result){//result-->JsonResult-->{}
		debugger
		//console.log(result);
		if(result.state==1){
		  //设置当前页数据
		  setTableBodyRows(result.data.list);
		  //设置分页信息(setPagination方法在page.js中)
		  //console.log(result.pageObject);//undefined
		  setPagination(result.data.pageObject);
		}else{
		  alert(result.message);
		}
	});
}
/*将服务端返回的json对象数据显示在页面上*/
function setTableBodyRows(result){
	//debugger
	//1.获得具体dom对象(显示数据位置的那个dom)
	var tBody=$("#tbodyId");//jquery的函数
	tBody.empty();
	//2.迭代result对象
	for(var i in result){//for(var i=0;i<result.length;i++)
	  //2.1构建tr对象
		var tr=$("<tr></tr>");
		tr.data("id",result[i].id);
	  //2.2构建td对象
		var state=result[i].valid?"启用":"禁用";
		var tds=
	    "<td><input type='checkbox'  name='checkItem' value='"+result[i].id+"'></td>"+
	    "<td>"+result[i].code+"</td>"+
	    "<td>"+result[i].name+"</td>"+
	    "<td>"+result[i].beginDate+"</td>"+
	    "<td>"+result[i].endDate+"</td>"+
	    "<td>"+state+"</td>"+
	    "<td><button type='button' class='btn btn-default btn-update'>修改</button></td>";
	  //2.3将td对象添加到tr中
		tr.append(tds);
	  //2.4将tr对象添加到tBody中
	    tBody.append(tr);
	}
}
