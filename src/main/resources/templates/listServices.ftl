<!DOCTYPE html>
<html>
    <head>
        <title>Services</title>
    </head>
    <body>
        <h1>Listing Services:</h1>
        <ol>
        <#list service as key, value>
            <li><a href="/services/${service[key].name}?wsdl">${service[key].name}</a> - ${service[key].active?c}</li>
        </#list>
        </ol>
    </body>
</html>