<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="grade.title.list">
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
                                    url: "school/grade/delete.do",
                                    type: "post",
                                    data: {
                                        id: $($this).prop("value"),
                                    },
                                    dataType: "json",
                                    success: function (res) {
                                        if (res.code === 200) {
                                            window.location.href = "school/grade/list.do"
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
        <h3 class="header smaller lighter blue">班级管理
            <span class="hidden-sm hidden-xs btn-group pull-right">
                <a href="school/grade/add.do" class="btn btn-sm btn-primary">
                    <i class="ace-icon glyphicon glyphicon-plus"></i>
                    <spring:message code="button.add"/>
                </a>
            </span>
        </h3>
        <table id="simple-table" class="table  table-bordered table-hover" style="text-align:text-align;">
            <thead>
            <tr>
                <th>序号</th>
                <th>班级名</th>
                <th>人数</th>
                <th>平均分</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${grades}" var="grade" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${grade.name}</td>
                    <td>5</td>
                    <td>65</td>
                    <td>
                        <div class="hidden-sm hidden-xs btn-group">
                            <a href="/school/grade/edit.do?id=${grade.id}" class="btn btn-sm btn-primary">
                                <button class="btn btn-xs btn-info" >
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </a>
                            <a class="btn btn-sm btn-primary">
                                <button class="btn1 btn btn-xs btn-danger" value="${grade.id}">
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
