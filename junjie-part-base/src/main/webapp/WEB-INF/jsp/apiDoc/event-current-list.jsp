<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>url: <a href="javaScript:void(0);" onclick="listClick()" >/api/v1/event/list.do</a></h3>
<script type="text/javascript">
/**
 * action renderEvent updateEvent
 * @param url
 * @param $form
 * @param action
 */
function listClick(){
	   var now = new Date();
	   var startTime = now.getTime() - (3600*24*10*1000);
	   var endTime= now.getTime() + (3600*24*10*1000);
        jQuery.ajax({
            type:'POST',
            data:{max:10,offset:0,startTime:startTime,endTime:endTime},
            url:"/api/v1/event/list.do",
            success:function(data,textStatus){
                console.log(data);
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){}
        });
}

</script>