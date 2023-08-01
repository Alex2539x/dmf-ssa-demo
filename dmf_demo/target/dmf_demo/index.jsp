<html>
<head>
    <title>DMF Search Demo</title>
</head>
<style>
    .center {
        margin: auto;
        width: 38%;
        padding: 10px;
    }
</style>

<br>
<br>

<div class="center">
    Numerous financial institutions, as well as various other credit firms,
    utilize the data provided by the Death Master File (DMF) in order to track
    client records and prevent fraud. This program demonstrator incorporates a
    number of web-based and app-based server applications in order to conveniently
    display relevant information to a user by parsing a CSV file
    (fields extracted from the DMF).
</div>

<br>

<form align="center" name="dmfsearch" method="get" action="dmfSearch">
    Search by SSN: <input type="text" name="ssn">
    <input type="submit" value="search">
</form>
</html>