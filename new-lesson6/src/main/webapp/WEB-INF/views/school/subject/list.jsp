<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="subject.title.list">
        <script language="JavaScript">
            $(function () {
                $(".btn1").click(function () {
                    var $this = this;
                    bootbox.confirm({
                        title: "删除提示",
                        message: "确认删除 " + $($this).prop("value") + "吗?",
                        buttons: {
                            confirm: {
                                label: "确定"
                            },
                            cancel:{
                                label:"取消"
                            }
                        },
                        callback: function(result) {

                            if (result === true) {
                                $.ajax({
                                    url: "school/subject/delete.do",
                                    type: "post",
                                    data: {
                                        id: $($this).prop("value"),
                                    },
                                    dataType: "json",
                                    success: function (res) {
                                        if (res.code === 200) {
                                            window.location.href = "school/subject/list.do"
                                        }
                                    }
                                });
                            }
                        }
                    })
                });
            });
        </script>
    <div class="col-xs-12">
        <h3 class="header smaller lighter blue">学科管理
            <span class="hidden-sm hidden-xs btn-group pull-right">
                <a href="school/subject/add.do" class="btn btn-sm btn-primary">
                    <i class="ace-icon glyphicon glyphicon-plus"></i>
                    <spring:message code="button.add"/>
                </a>
            </span>
        </h3>
        <table id="simple-table" class="table  table-bordered table-hover" style="text-align:text-align;">
            <thead>
            <tr>
                <th>序号</th>
                <th>学科名</th>
                <th>选修人数</th>
                <th>平均分</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subjects}" var="subject" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${subject.name}</td>
                    <td>${subject.courses.size()}</td>
                    <td>${avg.get(i.index)}</td>
                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                            <a href="school/subject/edit.do?cmd=${subject.cmd}" class="btn btn-sm btn-primary">
                                <button class="btn btn-xs btn-info" >
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </a>
                            <a class="btn btn-sm btn-primary">
                                <button class="btn1 btn btn-xs btn-danger" value="${subject.cmd}">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</lesson:page>
