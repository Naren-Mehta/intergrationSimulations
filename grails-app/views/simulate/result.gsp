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
            <div class="col-md-1"></div>

            <div class="col-md-10">
                <div class="form-group">

                    <g:link controller="simulate" action="simulateViewPage"
                            class="btn btn-primary">Again Simulate</g:link>

                    <g:render template="/simulateCommon/aboutResultPage"/>
                    <a href="#" role="button" class="btn btn-info"
                       data-target="#aboutResultPage"
                       data-toggle="modal">About</a>

                </div>

                <div class="form-group">
                    <h1>Result of you simulation</h1>

                    <table class="table table-responsive table-responsive">
                        <tr>
                            <th>Cache Type</th>
                            <th>Cache Size(in units)</th>
                            <th>Block Size</th>
                            <th>Replacement policy</th>
                            <th>Associativity</th>
                            <th>File Selection</th>
                        </tr>

                        <tr>
                            <td>${simulate?.cacheType == 0 ? "Direct Mapped" : "Associative"}</td>
                            <td>${simulate?.cacheSize} K</td>
                            <td>${simulate?.blockSize}</td>
                            <td>${simulate?.replacementPolicy ? "Random" : "LRU"}</td>
                            <td>
                                <g:if test="${simulate?.degreeOfAssociates == 1}">
                                    Direct mapped
                                </g:if>
                                <g:elseif test="${simulate?.degreeOfAssociates in [2, 4, 8]}">
                                    Set associative
                                </g:elseif>
                                <g:else>
                                    Fully associative
                                </g:else>
                            <td>${simulate?.fileSelection}</td>
                        </tr>

                    </table>

                    <table id="simulateList" class="table table-responsive table-responsive">
                        <tr>
                            <th>Entries/Addresses</th>
                            <th>Hits</th>
                            <th>Hit %</th>
                            <th>Compulsory Miss</th>
                            <th>Capacity Miss</th>
                            <th>Conflict Miss</th>
                            <th>Total Miss</th>
                        </tr>
                        <g:each in="${list}" var="output">
                            <tr>
                                <td>${output?.count}</td>
                                <td>${output?.hits}</td>
                                <td>${output?.count ? (output?.hits / output?.count) : output?.count}</td>
                                <td>${output?.Comp_Miss}</td>
                                <td>${output?.Capac_Miss}</td>
                                <td>${output?.Conf_Miss}</td>
                                <td>${output?.total}</td>
                            </tr>
                        </g:each>

                    </table>

                </div>

            </div>


            <div class="col-md-1"></div>
        </div>
    </div>
</div>
</body>
</html>
