var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}
$(function(){
	$("#editTypeForm")
	.on("click",".load-product-type",doLoadZtreeNodes)
	
	$("#btn-save").click(doSaveOrUpdate)
	
	$("#typeLayer")
	.on("click",".btn-cancle",doHideZtree)
	.on("click",".btn-confirm",doSetSelectedNode)
});
function doSaveOrUpdate(){
	debugger
	//1.验证表单数据是否为空
	if(!$("#editTypeForm").valid())return;
	//2.获取表单数据
	var params=getEditFormData();
	//3.异步提交表单数据
	var url="type/doSaveObject.do";
	$.post(url,params,function(result){
		if(result.state==1){
			alert(result.message);
			//回到列表页面
			$(".content").load("type/listUI.do");
		}else{
			alert(result.message);
		}
	});
}
/*获取页面表单数据*/
function getEditFormData(){
	var params={
	    "name":$("#typeNameId").val(),
	    "parentId":$("#editTypeForm").data("parentId"),
	    "note":$("#typeNoteId").val(),
	    "sort":$("#typeSortId").val()
	}
	return params;
}

/*设置上级分类信息*/
function doSetSelectedNode(){
	//1.获得选中的节点对象(数组)
	var nodes=zTree.getSelectedNodes();
	if(nodes.length>0){
	 //2.获得具体节点对象
	 var node=nodes[0];
	 //3.将id和name设置到对应的dom对象上
	 $("#editTypeForm").data("parentId",node.id);
	 $("#parentNameId").val(node.name);
	}
	//4.隐藏zTree对象
	doHideZtree();
}
function doHideZtree(){
	$("#typeLayer").css("display","none")
}
/*点击上级分类时异步加载树结构信息*/
function doLoadZtreeNodes(){
	debugger
	//显示树结构(Ztree)
	$("#typeLayer").css("display","block")
	//异步加载数据
	var url="type/doFindZtreeNodes.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			zTree=$.fn.zTree.init(
			$("#typeTree"),setting,result.data);
		}else{
		    alert(result.message);
		}
	})
}












