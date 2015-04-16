<%@ page import="enums.Enums" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Simulation</title>
</head>

<body>
<div class="container">
    <div class="row">

        <div class="col-md-12">
            <div class="col-md-2"></div>

            <div class="col-md-8">
                <g:form controller="simulate" action="calculateSimulation" class="form-horizontal"
                        enctype="multipart/form-data" method="post">

                    <div class="form-group">
                        <g:hasErrors bean="${simulateCO}">
                            <ul class="alert alert-danger" role="alert" style="list-style: none">
                                <g:eachError bean="${simulateCO}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                            error="${error}"/></li>
                                </g:eachError>
                            </ul>
                        </g:hasErrors>
                        <g:if test="${flash.message}">
                            <ul class="alert alert-danger" role="alert">
                                ${flash.message}
                            </ul>
                        </g:if>
                        ${flash.clear()}

                    </div>

                    <div class="form-group">

                        <g:render template="/simulateCommon/aboutUserDetails"/>
                        <a href="#" role="button" class="btn btn-info"
                           data-target="#aboutUserDetails"
                           data-toggle="modal"
                    >About</a>

                    </div>

                    <div class="form-group">
                        <h2>IP Cache Simulation Parameters</h2>

                        <label for="cacheType">
                            Cache Type
                            <small style="color: gray">Enter the Cache Type '1' for Direct Mapped, '2' for Associative.</small>

                        </label>

                        <g:select name="cacheType" class="form-control" from="${["1", "2"]}"
                                  noSelection="['': '-select cache type-']" value="${simulateCO?.cacheType}"/>
                    </div>

                    <div class="form-group">
                        <label for="cacheSize">
                            Cache Size
                            <small
                                    style="color: gray">Enter Cache Size in units of K: 1, 2, 4....[e.g. 2 for 2K]</small>

                        </label>

                        <g:select name="cacheSize" class="form-control" from="${["1", "2", "4", "8", "16"]}"
                                  noSelection="['': '-select cache size-']" value="${simulateCO?.cacheSize}"/>
                    </div>

                    <div class="form-group">
                        <label for="blockSize">
                            Block Size
                            <small
                                    style="color: gray">Enter Block Size 1,2,4,8,16 ( Block Size)</small>

                        </label>

                        <g:select name="blockSize" class="form-control" from="${["1", "2", "4", "8", "16"]}"
                                  noSelection="['': '-select block size-']" value="${simulateCO?.blockSize}"/>
                    </div>

                    <div class="form-group">
                        <label for="degreeOfAssociates">
                            Degree of Associativity
                            <small
                                    style="color: gray">Enter Degree of Associativity 1,2,4,8 or 16... [1 for direct mapped. 2, 4 , 8 for set associative . 16 for fully associative.]</small>
                        </label>

                        <g:select name="degreeOfAssociates" class="form-control"
                                  from="${["1", "2", "4", "8", "16"]}" value="${simulateCO?.degreeOfAssociates}"
                                  noSelection="['': '-select Degree of Associativity-']"/>
                    </div>

                    <div class="form-group">
                        <label for="replacementPolicy">
                            Replacement Policy

                            <small
                                    style="color: gray">Enter Replacement Policy: 0 for random 1 for LRU</small>
                        </label>

                        <g:select name="replacementPolicy" class="form-control" from="${["0", "1"]}"
                                  noSelection="['': '-select Replacement policy-']"
                                  value="${simulateCO?.replacementPolicy}"/>
                    </div>

                    <div class="form-group">
                        <label for="fileSelection">File selection menu</label>

                        <g:select name="fileSelection" class="form-control"
                                  noSelection="['': '-Select File Type -']"
                                  value="${simulateCO?.fileSelection}"
                                  from="${["Hexadecimal address List","IP Address List"]}"/>

                    </div>

                    <div class="form-group">

                        <label for="cacheType">Enter a file</label>

                        <input type="file" name="uploadFile">
                    </div>

                    <div class="form-group">
                        <label for="batch">Enter the number of Entries to process at a time</label>

                        <g:select name="batch" class="form-control"
                                  from="${["50", "100", "200", "300", "400", "500", "600", "700", "800", "900", "1000"]}"
                                  noSelection="['': '-select batch size-']"
                                  value="${simulateCO?.batch}"/>
                    </div>


                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-lg" value="Calculate"/>
                    </div>
                </g:form>

            </div>

            <div class="col-md-2"></div>

        </div>

    </div>
</div>

<style>

label {
    font-size: 12px;
}

</style>

</body>
</html>