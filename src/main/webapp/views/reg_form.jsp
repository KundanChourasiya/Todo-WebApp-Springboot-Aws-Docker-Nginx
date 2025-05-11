<%@ include file="include\base.jsp"%>

<%@ include file="include\nav.jsp"%>


<div class="form-content">
	<div class="row">
		<div class="col m3"></div>
		<div class="col s12 m6">
			<div class="card form-box">
				<div class="card-content">
					<span class="card-title"><i class="material-icons icon4343">account_box</i>
						User Registration Form</span>

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
						<form:form class="col s12" action="/" method="Post"
							modelAttribute="userRegister">
							<div class="row">
								<div class="input-field col s12">
									<i class="tiny material-icons prefix">person</i> <input
										id="name" type="text" name="uName" class="validate" />
									<form:errors path="uName" cssClass="errors" />
									<label for="name">User Name</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<i class="tiny material-icons prefix">email</i> <input
										id="email" type="email" name="uEmail" class="validate" />
									<form:errors path="uEmail" cssClass="errors" />
									<label for="email">User Email</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s4">
									<i class="tiny material-icons prefix">account_circle</i> <label
										for="gender">User Gender.</label>
								</div>
								<div class="input-field col s4">
									<label> <input class="with-gap" id="gender"
										type="radio" name="uGender" value="male" class="validate" />
										<span>Male</span>
									</label>
								</div>
								<div class="input-field col s4">
									<label> <input class="with-gap" id="gender"
										type="radio" name="uGender" value="female" /> <span>Female</span>
									</label>
								</div>
							</div>
							<form:errors path="uGender" cssClass="errors" />

							<div class="row">
								<div class="input-field col s12">
									<i class="tiny material-icons prefix">call</i> <input
										id="phone" type="text" name="uPhone" maxlength="10"
										class="validate" />
									<form:errors path="uPhone" cssClass="errors" />
									<label for="phone">User Phone No.</label>
								</div>
							</div>

							<div class="row">
								<div class="input-field col s12">
									<i class="tiny material-icons prefix">vpn_key</i> <input
										id="password" type="password" name="uPassword"
										class="validate" />
									<form:errors path="uPassword" cssClass="errors" />
									<label for="password">User Password</label>
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
									<button class="btn waves-effect waves-light buttons"
										type="submit" name="action">
										Register<i class="material-icons right">person_add</i>
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