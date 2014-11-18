<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form action="save" id="createEventForm" method="POST">
        <input type="hidden" id="isMilestone" value="false" name="isMilestone">
        <div class="controls">
                <div class="span6">
                私有任务&nbsp;
                <input type="hidden" name="isPrivate"><input type="checkbox" id="isPrivateCheckBox" name="isPrivate">&nbsp;&nbsp;
                发送短信通知&nbsp;
                <input type="hidden" name="sendSms"><input type="checkbox" id="sendSms" name="sendSms">
                <label style="margin-top: 10px" for="select_masterUser">主要负责人(不选择表示自己)</label>
                <select data-placeholder="请选择主要负责人" class="selectpicker" data-size="8" title="请选择主要负责人" 
                data-live-search="true" data-rel="chosen" name="masterUser.id"  id="select_masterUser" style="display: none;">
<option value="16">中外建海南</option>
<option value="5">顾艳芳</option>
<option value="29">丁菲</option>
<option value="13">初培坤</option>
<option selected="selected" value="1">系统管理员</option>
</select>
                <label for="title">标题</label>
                <input type="text" required="" placeholder="请输入标题..." value="测试标题" maxlength="30" name="title" id="title">

                <label for="eventTypeId">类型</label><select id="eventTypeId" name="eventType.id">
<option value="1">日常事务</option>
<option value="2">会议</option>
<option value="3">通知</option>
</select>
                <br>
                <label for="eventLevel">优先级</label>
<select id="eventLevel" name="eventLevel" class="selectpicker" style="display: none;">
    <option data-content="&lt;span class='severity1'&gt;1&lt;/span&gt;" value="1">1</option>
    <option data-content="&lt;span class='severity2'&gt;2&lt;/span&gt;" selected="" value="2">2</option>
    <option data-content="&lt;span class='severity3'&gt;3&lt;/span&gt;" value="3">3</option>
    <option data-content="&lt;span class='severity4'&gt;4&lt;/span&gt;" value="4">4</option>
</select><div class="btn-group bootstrap-select"><button data-toggle="dropdown" class="btn dropdown-toggle btn-default" type="button" data-id="eventLevel"><div class="filter-option pull-left"><span class="severity2">2</span></div>&nbsp;<div class="caret"></div></button><div class="dropdown-menu open"><ul role="menu" class="dropdown-menu inner"><li rel="0"><a style="" class="" tabindex="0"><span class="severity1">1</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li><li rel="1" class="selected"><a style="" class="" tabindex="0"><span class="severity2">2</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li><li rel="2"><a style="" class="" tabindex="0"><span class="severity3">3</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li><li rel="3"><a style="" class="" tabindex="0"><span class="severity4">4</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li></ul></div></div>
                <br>任务短信提醒&nbsp;&nbsp;<input type="hidden" name="isNotify"><input type="checkbox" id="isNotifyCheckBox" onchange="notifyCheckBox(this)" name="isNotify">
                &nbsp;&nbsp;
                <div style="margin-top: 10px;display: none" id="notifyDiv">
                    提醒频率：<select name="eventNotifyType" onchange="notifyTypeChange('#eventNotifyType')" id="eventNotifyType">
                                <option value="ONCE">仅一次</option>
                                <option value="EVERY_DAY">每天</option>

                             </select>
                    <br>
                    提醒时间：<div id="notifyValueDiv">
                                <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" required="" name="notifyTime">
                             </div>
                </div>

            </div>
            <div class="span6">
                <label id="buildProjectLabel">选择工程<select value="1" name="buildProject.id" style="display: none" id="buildProjectSelect" class="buildProjectSelect">
 <option value="1">默认建设项目</option></select></label>
                
                是否平行任务&nbsp;
                <input type="hidden" name="_separateReport"><input type="checkbox" id="separateReport" name="separateReport">
                <label style="margin-top: 10px" for="select_participants">请选择参与人</label>
                <select multiple="true" data-placeholder="请选择参与人" class="selectpicker" data-size="8"
                 title="请选择参与人" data-live-search="true" data-rel="chosen" name="participants" id="select_participants" >
<option value="28">施工单位5</option>
<option value="6">施工单位4</option>
<option value="1">系统管理员</option>
</select>
                <label for="startDate">开始时间</label>
                    <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,startDate:'%y-%M-01 00:00:00'})" data-format="yyyy-MM-dd HH:mm:ss" name="startDate" id="startDate">
                <label for="endDate">截止时间</label>

                    <input type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-format="yyyy-MM-dd HH:mm:ss" name="endDate" id="endDate">
                <label for="description">任务描述</label>
                <textarea rows="4" cols="3" placeholder="任务说明..." name="description" id="description"></textarea>
                <br><br>
                <div class="pull-left">
                    <a href="javaScript:void(0);" onclick="saveEvent('/api/v1/event/create.do',jQuery('#createEventForm'),'renderEvent')" id="eventSubmitBtn" class="btn btn-info">
                        创建</a>
                    <a data-dismiss="modal" onclick="cancelCreateEvent()" id="eventCancelBtn" href="#" class="btn">
                    取消
                    </a>
                </div>
        </div>

        </div>
    </form>
    <script type="text/javascript">
/**
 * action renderEvent updateEvent
 * @param url
 * @param $form
 * @param action
 */
function saveEvent(url,$form,action){
    if($form.validate().form()){
        var params = $form.serialize();
        if($("#startDate").val()==$("#endDate").val()){
            alert("结束时间应该大于开始时间");
            return false;
        }
        jQuery.ajax({
            type:'POST',
            data:$form.serialize(),
            url:url,
            success:function(data,textStatus){
                console.log(data);
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){}
        });
    }
}
function notifyCheckBox(checkBox){
    if(checkBox.checked){
       $("#notifyDiv").show()
    }else{
        $("#notifyDiv").hide()
    }
}
var onceHtml = '<input name="notifyTime" required onClick="WdatePicker'+"({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  +'" type="text"/>'
function notifyTypeChange(selectId){
    var selectValue = $(selectId).val().trim();
    if(selectValue=="ONCE"){
        $("#notifyValueDiv").html(onceHtml);
    }
    if(selectValue=="EVERY_DAY"){
	   var now = new Date()
	   var hour = now.getHours()
	   var minute = now.getMinutes(hour,minute)
	   var hoursSelectHtml = genHoursSelectHtml (hour,minute)
       $("#notifyValueDiv").html(hoursSelectHtml);
    }
    if(selectValue=="EVERY_WEEK"){
	   var weeksSelectHtml = genWeeksSelectHtml();
       $("#notifyValueDiv").html(weeksSelectHtml);
    }
}
function genHoursSelectHtml(hour,minute){
	   var html = "<select name='hour' style='width: 50px' class='hourSelect'>"
	   for(var i=0;i<24;i++){
	       if(i==hour){
	           html=html+"\n"+"   <option selected value='"+i+"'>"+i+"</option>";
	       }else{
	           html=html+"\n"+"   <option value='"+i+"'>"+i+"</option>";
	       }
	   }
	   html = html + "\n</select>时"
	   html = html + "\n<select name='minute' style='width: 50px' class='minuteSelect'>"
	   for(var i=0;i<60;i++){
	       if(i==minute){
	           html=html+"\n"+"   <option selected value='"+i+"'>"+i+"</option>";
	       }else{
	           html=html+"\n"+"   <option value='"+i+"'>"+i+"</option>";
	       }
	   }
	   html = html + "\n</select>分"
	   return html
	}
	function genWeeksSelectHtml(){
	    var html = "<select name='week' style='width: 80px' class='weekSelect'>"
	    html=html+"\n"+"   <option  value='"+2+"'>每周一</option>";
	    html=html+"\n"+"   <option  value='"+3+"'>每周二</option>";
	    html=html+"\n"+"   <option  value='"+4+"'>每周三</option>";
	    html=html+"\n"+"   <option  value='"+5+"'>每周四</option>";
	    html=html+"\n"+"   <option  value='"+6+"'>每周五</option>";
	    html=html+"\n"+"   <option  value='"+7+"'>每周六</option>";
	    html=html+"\n"+"   <option  value='"+1+"'>每周日</option>";
	    html = html + "\n</select>"
	    return  (html+"\n"+genHoursSelectHtml())
	}
</script>