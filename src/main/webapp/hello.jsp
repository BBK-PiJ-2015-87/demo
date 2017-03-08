<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<input type="button" value="Submit Cards" onclick="submitCreditCards();" />
<br>
<textarea id="cards-list" rows="10" cols="50" name="cards"></textarea>

<br>
<br>
<input id="clickMe" type="button" value="Fetch Cards" onclick="fetchCreditCards();" />
<br>
<div id="render"></div>


</body>

<script type="text/javascript">

    function submitCreditCards()
    {
        fetch('./addCards', {
            method: 'post',
            body: document.getElementById("cards-list").value
        });

        document.getElementById("cards-list").value = ""
    }

    function fetchCreditCards()
    {
        fetch('./getCards')
            .then(
                function(response) {
                    response.json().then(function(data) {
                        document.getElementById("render").innerHTML = JSON.stringify(data);
                    });
                }
            )
            .catch(function(err) {
                console.log('There was an error fetching cards', err);
            });
    }

</script>
</html>