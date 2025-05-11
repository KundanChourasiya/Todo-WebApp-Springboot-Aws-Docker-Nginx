<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="include\base.jsp"%>

<%@ include file="include\tasknav.jsp"%>

<div class="form-content">
    <div class="row">
        <div class="col m2"></div>
        <div class="col s12 m8">
            <div class="card form-box">
                <div class="card-content">
                    <span class="card-title">
                        <i class="material-icons icon4343">directions_bike</i>
                        All Task list 
                        <a href="addtask" class="btn waves-effect waves-light btn-small right">
                            <i class="material-icons left">playlist_add</i>Add Task
                        </a>
                    </span>
                    
                    <%
					String msg = (String) request.getAttribute("msg");
					if (msg != null) {
					%>

					<div class="chip">
						<%=msg%>
						<i class="close material-icons">close</i>
					</div>
					<%
					}
					%>

                    <div class="row">
                        <div class="col m12">
                            <table class="highlight">
                                <thead>
                                    <tr>
                                        <th>Sl. No</th>
                                        <th>Task Name</th>
                                        <th>Task Date</th>
                                        <th>Task Time</th>
                                        <th>Task Completed</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Check if the task list is not empty -->
                                    <c:if test="${not empty getallTask}">
                                        <c:forEach var="task" items="${getallTask}" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${task.TName}</td>
                                                <td>${task.TDate}</td>
                                                <td>${task.TTime}</td>
                                                <td>
                                                    <a href="/yourtask/delete/${task.TId}" class="btn waves-effect waves-light btn-small red">
                                                        <i class="material-icons">playlist_add_check</i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>

                                    <!-- Show "No data found" if the task list is empty -->
                                    <c:if test="${empty getallTask}">
                                        <tr>
                                            <td colspan="6">
                                                <p align="center">No data found</p>
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>