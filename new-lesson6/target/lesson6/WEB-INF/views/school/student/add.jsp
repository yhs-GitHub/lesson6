<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="student.title.${cmd}">
    <jsp:attribute name="script">
		<%--<script src="static-resource/ace/assets/js/moment.min.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
		<script src="static-resource/ace/assets/js/daterangepicker.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-clockpicker.min.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-multiselect.min.js"></script>--%>
        <script type="text/javascript">
            jQuery(function ($) {
                $('.date-picker').datepicker({
                    autoclose: true,
                    format: 'yyyy-mm-dd',
                    todayHighlight: true,
                    zIndex: 9999,
                    'z-index': 9999,
                })
                //ow datepicker when clicking on the icon
                    .next().on(ace.click_event, function () {
                        $(this).prev().focus();
                    });
            });
        </script>
        <%--<script type="text/javascript">
            $(function(){
                $('#dateRangePicker').daterangepicker({
                    autoUpdateInput:false,
                    "autoApply": false,
                    locale: {
                        format: 'YYYY-MM-DD'
                    }
                },function(start, end) {
                    var startDate = start.format("YYYY-MM-DD");
                    var endDate = end.format("YYYY-MM-DD");
                    $("#startTime").val(startDate);
                    $("#endTime").val(endDate);
                    $("#dateRangePicker").val(startDate + " - " + endDate);
                }).on('cancel.daterangepicker', function(ev, picker) {
                    $("#startTime").val("");
                    $("#endTime").val("");
                    $("#dateRangePicker").val("");
                });
            });
        </script>--%>
    </jsp:attribute>
    <jsp:body>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="student.title.${cmd}"/>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                    <a href="javascript:history.go(-1)" class="btn btn-sm btn-primary"><i
                                            class="ace-icon fa fa-angle-left"></i>
                                        <spring:message code="common.back"/>
                                    </a>
                                </span>
                            </h3>
                            <form action="school/student/save_${cmd}.do" method="post" class="form-horizontal" role="form"
                                  id="admin-add-form">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="id">
                                        <spring:message code="学号"/>
                                    </label>
                                    <div class="col-sm-3">
                                        <div class="input-group">
                                            <input ${empty student.id ? '' : 'readonly'} type="text" autocomplete="off" id="id" name="id" value="${student.id}" class="required form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="name">
                                        <spring:message code="姓名"/>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="name" name="name" value="${student.name}" class="required form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="gender">
                                        <spring:message code="性别"/>
                                    </label>
                                    <div class="col-sm-3" id="gender" name="gender" class="required form-control">
                                        男<input type="radio" name="gender" value="男" ${student.gender=="女"?"":"checked"}/>
                                        女<input type="radio" name="gender" value="女" ${student.gender=="女"?"checked":""}/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="birthday">
                                        <spring:message code="出生日期"/>
                                    </label>
                                   <div class="col-sm-3">
                                       <input type="text" data-date-format="yyyy-mm-dd" id="birthday" name="birthday" value="${student.birthday}" role="textbox" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="code">
                                        <spring:message code="所在班级"/>
                                    </label>
                                    <div class="col-sm-3">
                                        <%--<input type="text" autocomplete="off" id="code" name="code" value="${student.grade.code}" maxlength="50" class=" form-control"/>--%>
                                            <select name="code" id="code" class="form-control">
                                                <option ${empty student?"selected":""}>-------------------------请选择-------------------------</option>
                                                <c:forEach items="${grades}" var="grade">
                                                    <option value="${grade.code}" ${student.grade.code==grade.code?"selected":""}>${grade.name}</option>
                                                </c:forEach>
                                            </select>
                                    </div>
                                </div>
                               <%-- <sec:authorize ifAnyGranted="OPT_USER_ADD,OPT_USER_EDIT">--%>
                                    <div class="clearfix form-actions">
                                        <div class="text-center">
                                            <button id="confirm" class="btn btn-info" type="submit" >
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                <spring:message code="button.submit"/>
                                            </button>

                                        </div>
                                    </div>
                                <%--</sec:authorize>--%>
                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>