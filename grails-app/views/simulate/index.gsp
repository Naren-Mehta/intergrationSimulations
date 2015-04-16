<%@ page import="enums.Enums" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Simulation</title>
</head>

<body>
<div class="container">
    <div class="row">

        <div class="form-group text-center">
            <h2>User Registration</h2>
        </div>

        <div class="col-md-12">
            <div class="col-md-2"></div>

            <div class="col-md-10">
                <g:form controller="registration" action="saveRegistration" class="form-horizontal" method="post">

                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-2">
                            <g:hasErrors bean="${registerCO}">
                                <ul class="alert alert-danger" role="alert" style="list-style: none">
                                    <g:eachError bean="${registerCO}" var="error">
                                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                                error="${error}"/></li>
                                    </g:eachError>
                                </ul>
                            </g:hasErrors>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputFirstName" class="col-sm-2 control-label">First Name</label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputFirstName" name="firstName"
                                   placeholder="First Name" value="${registerCO?.firstName}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputLastName" class="col-sm-2 control-label">Last Name</label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputLastName" name="lastName"
                                   placeholder="Last Name" value="${registerCO?.lastName}">
                        </div>
                    </div>


                    <div class="form-group col-lg-3 ${hasErrors(bean: signUpCO, field: 'gender', 'has-error')}">
                        <label for="gender">Gender</label>
                        <g:select from="${Enums.Gender}" name="gender" valueMessagePrefix="users.user.signup.gender"
                                  data-parsley-required="true" data-parsley-group="blockGeneralInfo"
                                  noSelection="['': 'Select an option']" class="form-control" id="gender"
                                  value="${signUpCO?.gender}"/>
                    </div>


                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">Email Address</label>

                        <div class="col-sm-6">
                            <input type="email" class="form-control" id="inputEmail" name="email"
                                   placeholder="Email" value="${registerCO?.email}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputConfirmEmail" class="col-sm-2 control-label">Confirm Email Address</label>

                        <div class="col-sm-6">
                            <input type="email" class="form-control" id="inputConfirmEmail" name="confirmEmail"
                                   placeholder="Confirm Email" value="${registerCO?.confirmEmail}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-2 control-label">Password</label>

                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="inputPassword" name="password"
                                   placeholder="Password" value="${registerCO?.password}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputConfirmPassword" class="col-sm-2 control-label">Confirm Password</label>

                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="inputConfirmPassword" name="confirmPassword"
                                   placeholder="Confirm Password" value="${registerCO?.confirmPassword}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputAddress" class="col-sm-2 control-label">Address</label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputAddress" placeholder="Address"
                                   value="${registerCO?.address}"
                                   name="address">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputState" class="col-sm-2 control-label">State</label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputState" placeholder="State"
                                   value="${registerCO?.state}"
                                   name="state">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputCity" class="col-sm-2 control-label">City</label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputCity" placeholder="City"
                                   value="${registerCO?.city}"
                                   name="city">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputCountry" class="col-sm-2 control-label">Country</label>

                        <div class="col-sm-6">
                            <g:countrySelect name="country" value="${registerCO?.country}" id="inputCountry"
                                             class="form-control"
                                             noSelection="['': '---- Choose your country ----']"/>

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" class="btn btn-primary btn-lg" value="Register"/>
                        </div>
                    </div>
                </g:form>

            </div>

            %{--<div class="col-md-2"></div>--}%

        </div>

    </div>
</div>

</body>
</html>