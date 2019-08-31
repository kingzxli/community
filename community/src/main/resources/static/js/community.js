function post() {
	var questionId=$("#question_id").val();
	var content=$("#comment_content").val();
	if(!content){
		alert("回复内容不能为空~~~");
	}
	$.ajax({			
			  type: "POST",
			  url: "/comment",
			 contentType:'application/json',
			  data: JSON.stringify({
					"parentId":questionId,
					"content":content,
					"type":1
					
				}),
			  success: function(obj){
					if(obj.code==200){
						window.location.reload();
						/*$("#comment_section").hide();		*/		
					}else{
						alert(obj.message);
					}
					
				},
			  dataType: "json"
	});
}