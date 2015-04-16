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
            <h2>32 bit-IP Cache Simulation</h2>
        </div>

        <div class="span4">
            <div class="">
                <dl class="dl-horizontal">
                    <dt>Objective</dt>
                    <dd>To write a cache simulator and to examine the performance of various cache organizations from a given trace file</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>Architecture</dt>
                    <dd>Cache simulator has the following inputs and outputs
                        <div>  <br>    </div>

                        <div class="span4">
                            <div class="">
                                <dl class="dl-horizontal">
                                    <dt>inputs</dt>
                                    <dd>
                                        a. Memory trace
                                        b. Cache size
                                        c. Block size
                                        d. Degree of associativity
                                        e. Replacement policy

                                    </dd>
                                </dl>
                                <dl class="dl-horizontal">
                                    <dt>Outputs</dt>
                                    <dd>
                                        a. Total miss rate
                                        b. Compulsory miss rate
                                        c. Capacity miss rate
                                        d. Conflict miss rate
                                    </dd>
                                </dl>
                            </div>
                        </div>

                    </dd>
                </dl>

                <dl class="dl-horizontal">
                    <dt>Memory Trace</dt>
                    <dd>IP List</dd>
                    <dd>Syslog</dd>
                    <dd>HEX CPU requests</dd>
                </dl>

            </div>
        </div>
        <br>
        <br>


        <div class="col-md-12">
            <div class="col-md-2"></div>

            <div class="col-md-16">

                <dl class="dl-horizontal">
                    <dt>Block Diagram</dt>
                </dl>


                <div class="form-group text-center">
                    <img src="${resource(dir: "images/project", file: "one.jpg")}"/>
                </div>

                <dl class="dl-horizontal">
                    <dt>Flow Chart</dt>
                </dl>


                <div class="form-group text-center">
                    <img src="${resource(dir: "images/project", file: "two.jpg")}"/>

                </div>


                <div class="form-group text-center">
                    <g:render template="/simulateCommon/aboutDiagrams"/>
                    <a href="#" role="button" class="btn btn-info"
                       data-target="#aboutUserDetails"
                       data-toggle="modal">About The digrams</a>
                </div>


                <div class="form-group text-center">
                    <g:link controller="simulate" action="simulateViewPage"
                            class="btn btn-primary btn-lg">Enter Simulation Parameters</g:link>
                </div>

            </div>

            <div class="col-md-2"></div>

        </div>
    </div>
</div>
</body>
</html>