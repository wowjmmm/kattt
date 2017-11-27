$(function(){
	$("#queryFormId").on("click",".btn-search",doQueryObjects);
	doGetObjects();
});
function doQueryObjects(){
	$("#pageId").data("pageCurrent",1);
	doGetObjects();
}
function doGetObjects(){
	var url="team/doFindPageObjects.do";
	var params={"projectName":$("#searchNameId").val()};
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	params.pageCurrent=pageCurrent;
	console.log(params)
	$.getJSON(url,params,function(result){
		if(result.state==1){
			setTableBodyRows(result.data.list);
			setPagination(result.data.pageObject)
		}else{
			alert(result.message);
		}
	})
}
function setTableBodyRows(list){
	var tBody=$("#tbodyId");
	tBody.empty();
	var tds=
	"<td><input type='checkbox' name='checkItem' " +
	"value='[id]'></td>"+
	"<td>[name]</td>"+
	"<td>[projectName]</td>"+
	"<td>[state]</td>"+
	"<td>修改</td>"
	for(var i in list){
		var tr=$("<tr></tr>");
		tBody.append(tr.append(tds.replace('[id]',list[i].id)
		   .replace('[name]',list[i].name)
		   .replace('[projectName]',list[i].projectName)
		   .replace('[state]',list[i].valid?"启用":"禁用")))
	}
}











