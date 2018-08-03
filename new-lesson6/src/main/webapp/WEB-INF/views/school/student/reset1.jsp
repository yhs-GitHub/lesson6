<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="student.title.${cmd}">
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
                            <form action="school/student/save_choose.do" method="post" class="form-horizontal"
                                  role="form"
                                  id="admin-add-form">
                                <div class="form-group">
                                   <%-- <label class="col-sm-3 control-label no-padding-top" for="duallist"> Dual
                                        listbox</label>--%>
                                    <div class="col-sm-8">
                                        <div class="bootstrap-duallistbox-container row moveonselect">


                                            <c:forEach items="${subjects}" var="subject">
                                                <c:forEach items="${courses}" var="course">
                                                    ${subject.cmd}
                                                    ${course.subject.cmd}
                                                    ${subject.cmd==course.subject.cmd}
                                                </c:forEach>
                                            </c:forEach>
                                            <hr>








                                            <div class="box1 col-md-6">
                                                <div class="btn-group buttons">
                                                    <button type="button"
                                                            class="btn moveall btn-white btn-bold btn-info"
                                                            title="Move all">
                                                        <i class="fa fa-arrow-right"></i>
                                                        <i class="fa fa-arrow-right"></i>
                                                    </button>
                                                    <button type="button" class="btn move btn-white btn-bold btn-info"
                                                            title="Move selected">
                                                        <i class="fa fa-arrow-right"></i>
                                                    </button>
                                                </div>
                                                <select multiple="multiple"
                                                        id="bootstrap-duallistbox-nonselected-list_duallistbox_demo1[]"
                                                        class="form-control" name="duallistbox_demo1[]_helper1"
                                                        style="height: 270px;">
                                                    <c:forEach items="${subjects}" var="subject">
                                                        <c:forEach items="${courses}" var="course">
                                                            ${subject.cmd}
                                                            ${course.subject.cmd}
                                                            ${subject.cmd==course.subject.cmd}
                                                            <c:if test="${subject.cmd!=course.subject.cmd}">
                                                                <option value="${subject.cmd}">${subject.name}</option>
                                                            </c:if>
                                                            <%--<option value="${subject.cmd}" ${subject.cmd==course.subject.cmd?"":"selected"}>${subject.name}</option>
                                                        --%></c:forEach>
                                                    </c:forEach>
                                                </select></div>















                                            <div class="box2 col-md-6">
                                                <div class="btn-group buttons">
                                                    <button type="button" class="btn remove btn-white btn-bold btn-info"
                                                            title="Remove selected">
                                                        <i class="fa fa-arrow-left"></i>
                                                    </button>
                                                    <button type="button"
                                                            class="btn removeall btn-white btn-bold btn-info"
                                                            title="Remove all">
                                                        <i class="fa fa-arrow-left"></i>
                                                        <i class="fa fa-arrow-left"></i>
                                                    </button>
                                                </div>
                                                <select multiple="multiple"
                                                        id="bootstrap-duallistbox-selected-list_duallistbox_demo1[]"
                                                        class="form-control" name="duallistbox_demo1[]_helper2"
                                                        style="height: 270px;">
                                                    <option value="option3" selected="selected">Option 3</option>
                                                    <option value="option6" selected="selected">Option 6</option>
                                                </select>
                                                <select multiple="multiple" size="10" name="duallistbox_demo1[]"
                                                        id="duallist" style="display: none;">
                                                    <option value="option1">Option 1</option>
                                                    <option value="option2">Option 2</option>
                                                    <option value="option3" selected="selected">Option 3</option>
                                                    <option value="option4">Option 4</option>
                                                    <option value="option5">Option 5</option>
                                                    <option value="option6" selected="selected">Option 6</option>
                                                    <option value="option7">Option 7</option>
                                                    <option value="option8">Option 8</option>
                                                    <option value="option9">Option 9</option>
                                                    <option value="option0">Option 10</option>
                                                </select>
                                                <div class="hr hr-16 hr-dotted"></div>
                                            </div>





















                                        </div>
                                            <%-- <sec:authorize ifAnyGranted="OPT_USER_ADD,OPT_USER_EDIT">--%>
                                        <div class="clearfix form-actions">
                                            <div class="text-center">
                                                <button id="confirm" class="btn btn-info" type="submit">
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