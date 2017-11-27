var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];

$(function(){
	doGetObjects();
	$("#formHead")
	.on("click",".btn-delete",doDeleteById)
	.on("click",".btn-add",doLoadEditUI)
});
function doLoadEditUI(){
	var url="type/editUI.do";
	$(".content").load(url,function(){
		$("#typeEditTitleId").html("添加分类");
	})
}

function doDeleteById(){
	debugger
	//1.获取选中记录的id值(获取方法可以查源码或者看官方demo)
	//1.1获取选中的节点(可能是多个)
	var selectNodes=//[object,....]
	$("#typeTable")
	.bootstrapTreeTable("getSelections");
	//1.2判定是否有选中的
	if(selectNodes.length==0){
	  alert("请先选择");
	  return;
	}
	//1.3获取选中的id值:selectNodes[0]＝object{id:"1"}
	var selectId=selectNodes[0].id;//(因为咱们实现的是单选)
	console.log("selectId="+selectId)
	//2.发送异步请求删除记录
	var url="type/doDeleteById.do";
	var params={"id":selectId};
	$.post(url,params,function(result){
		//debugger
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}


function doGetObjects(){
	var tableId="typeTable";//type_list.jsp中的tableid
	var url="type/doFindTreeGridNodes.do";
	//构建treeTable对象
	var treeTable=
    new TreeTable(tableId, url, columns);
	treeTable.setExpandColumn(2);
	//初始化treeTable对象
	treeTable.init();
}











