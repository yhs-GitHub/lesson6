<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="grade.title.${cmd}">
    <jsp:body>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="grade.title.${cmd}"/>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                    <a href="javascript:history.go(-1)" class="btn btn-sm btn-primary"><i
                                            class="ace-icon fa fa-angle-left"></i>
                                        <spring:message code="common.back"/>
                                    </a>
                                </span>
                            </h3>
                            <form action="school/grade/save_${cmd}.do" method="post" class="form-horizontal" role="form"
                                  id="admin-add-form">
                                <input type="text" value="${grade.code}" name="code" hidden>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="name">
                                        <spring:message code="班级名"/>
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="name" name="name" value="${grade.name}" class="required form-control"/>
                                    </div>
                                </div>
                                <div class="clearfix form-actions">
                                    <div class="text-center">
                                        <button id="confirm" class="btn btn-info" type="submit" >
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            <spring:message code="button.submit"/>
                                        </button>

                                    </div>
                                </div>
                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>