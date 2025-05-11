<%@ include file="include\base.jsp"%>

<%@ include file="include\tasknav.jsp"%>



<div class="form-content">
	<div class="row">
		<div class="col m3"></div>
		<div class="col s12 m6">
			<div class="card form-box">
				<div class="card-content">
					<span class="card-title"><i class="material-icons icon4343">directions_bike</i>
						Task Form</span>


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
						<form:form action="/addtask" modelAttribute="userTask" method="post"
							class="col s12">

							<div class="row">
								<div class="input-field col s12">
									<i class="material-icons prefix">playlist_add</i> 
									<input id="name" type="text" name="tName" /> 
									<label for="name">Task Name</label>
									<form:errors path="tName" cssClass="errors"></form:errors>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<i class="material-icons prefix">date_range</i> 
									<input id="date" type="date" name="tDate"/> 
									<label for="date">Task Date</label>
									<form:errors path="tDate" cssClass="errors" />
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<i class="material-icons prefix">access_time</i> 
									<input id="time" type="time" name="tTime"/> 
									<label for="time">Task Time</label>
									<form:errors path="tTime" cssClass="errors" />
								</div>
							</div>


							<div class="row">
								<div class="input-field col s12">
									<label> <input type="checkbox" name="checkbtn" /> <span>Check
											me out</span>
									</label>

								</div>

							</div>
							<form:errors path="checkbtn" cssClass="errors" />


							<div class="row">
								<div class="col s4"></div>
								<div class="col s5">
									<button class="btn waves-effect waves-light  buttons"
										type="submit" name="action">
										Add Task<i class="material-icons right">play_arrow</i>
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>