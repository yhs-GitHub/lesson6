<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="student.title.${cmd}">
    <jsp:body>
        <center>
            <table>
                <tr align="center">
                    <td colspan="3">
                        选课
                    </td>
                </tr>
                <tr>
                    <td>
                        <select id="fb_list" name="fb_list"  multiple="multiple"
                                size="8" style="width: 300px; height:200px;">
                            <c:forEach items="${unchoose}" var="subject">
                                <option value="${subject.cmd}" name="unchoose">${subject.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="button" id="selectDown" name="selectDown" value="下移∨" />
                        <br />
                        <input type="button" id="selectup" name="selectup" value="上移∧" />
                        <br />
                        <input type="button" id="add" name="add" value="向右>>" />
                        <br />
                        <input type="button" id="delete" name="delete" value="<<向左" />
                        <br />
                        <input type="button" id="selectAllRight" name="selectAllRight" value="全部右边>>" />
                        <br />
                        <input type="button" id="selectAllLeft" name="selectAllLeft" value="<<全部向左" />
                    </td>
                    <td>
                        <select id="select_list" name="select_list" multiple="multiple"
                                size="8" style="width: 300px; height:200px;">
                            <c:forEach items="${choose}" var="subject">
                                <option value="${subject.cmd}" name="choose">${subject.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </center>






        <script>
            //向右移动
            $(function(){
                $("#add").click(function(){
                    var  selectList=true;
                    if($("#fb_list option:selected").length>0){                                     //判断右边有没有重复的val值
                        for(var i=0;i<$("#select_list option").length;i++){
                            if($("#fb_list option:selected").val()==$("#select_list option:eq("+i+")").val()){
                                alert("第二个列表已经有了");
                                selectList=false;
                            }

                        }
                        if(selectList==true){
                            $("#select_list").append("<option name='choose' value='"+$("#fb_list option:selected").val()+"'>"+$("#fb_list option:selected").text()+"</option>");
                            $("#fb_list option:selected").remove();
                            return false;
                        }

                    }else{
                        alert("请选择数据");
                    }
                })
            })
            //向左移动
            $(function(){
                $("#delete").click(function(){
                    var selectList=true;
                    if($("#select_list option:selected").length>0){
                        for(var i=0;i<$("#fb_list option").length;i++){
                            if($("#select_list option:selected").val()==$("#fb_list option:eq("+i+")").val()){
                                alert("第一个列表已经有了");
                                selectList=false;
                            }
                        }
                        if(selectList==true){
                            $("#fb_list").append("<option name='unchoose' value='"+$("#select_list option:selected").val()+"'>"+$("#select_list option:selected").text()+"</option>");
                            $("#select_list option:selected").remove();
                            return false;
                        }
                    }else{
                        alert("请选择数据");
                    }
                })
            })
            //向上移动
            $(function(){
                $("#selectup").click(function(){
                    $("select option:selected").each(function(){
                        $(this).prev().before($(this));
                        return false;
                    });
                    $("select").bind("change",function(){
                        var that = $(this);
                        var tempDom = that.find("option:selected");
                        $("select").find("option").removeAttr("selected");
                        tempDom.attr("selected","selected");
                    });
                });
            });
            //向下移动
            $(function(){
                $("#selectDown").click(function(){
                    $("select option:selected").each(function(){
                        $(this).insertAfter($(this).next());
                        return false;
                    });
                    $("select").bind("change",function(){
                        var that = $(this);
                        var tempDom = that.find("option:selected");
                        $("select").find("option").removeAttr("selected");
                        tempDom.attr("selected","selected");
                    });
                });
            });

            //全部移到右边
            $('#selectAllRight').click(function(){
                var selectList=true;
                if($("#fb_list option:selected").length>0){
                    for(var i=0;i<$("#select_list option").length;i++){
                        for(var j=0;j<$("#fb_list option").length;j++){
                            if($("#select_list option:eq("+i+")").val()==$("#fb_list option:eq("+j+")").val()){
                                alert("第二个列表已经有了");
                                selectList=false;
                            }
                        }
                    }
                    if(selectList==true){
                        //获取全部的选项,删除并追加给对方
                        $('#fb_list option').appendTo('#select_list');
                        return false;
                    }

                }else{
                    alert("请选择数据");
                }

            });
            //全部移到左边
            $('#selectAllLeft').click(function(){
                var selectList=true;
                if($("#select_list option:selected").length>0){
                    for(var i=0;i<$("#fb_list option").length;i++){
                        for(var j=0;j<$("#select_list option").length;j++){
                            if($("#select_list  option:eq("+i+")").val()==$("#fb_list option:eq("+j+")").val()){
                                alert("第一个列表已经有了");
                                selectList=false;
                            }
                        }
                    }
                    if(selectList==true){
                        //获取全部的选项,删除并追加给对方
                        $('#select_list option').appendTo('#fb_list');
                    }
                }else{
                    alert("请选择数据");
                }

            });


        </script>
    </jsp:body>
</lesson:page>