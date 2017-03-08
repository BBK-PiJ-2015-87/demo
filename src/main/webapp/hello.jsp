<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>

        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                text-align: left;
                padding: 8px;
            }

            th {
                background-color: #53b4ff;
            }

            tr:nth-child(even){background-color: #f2f2f2}
        </style>

    </head>
    <body>

        <input type="button" value="Submit Cards" onclick="submitCreditCards();" />
        <br>
        <textarea id="cards-list" rows="10" cols="80" name="cards"></textarea>

        <br>
        <br>
        <input id="clickMe" type="button" value="Fetch Cards" onclick="fetchCreditCards();" />
        <br>
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
            document.getElementById("render").innerHTML = ""

            fetch('./getCards')
                .then(
                    function(response) {
                        response.json().then(function(data) {
                            var render = document.getElementById("render");
                            var table = document.createElement("table");

                            var headerRow = document.createElement("tr");
                            var hcel1= document.createElement("th");
                            hcel1.appendChild(document.createTextNode("Bank"));
                            var hcel2= document.createElement("th");
                            hcel2.appendChild(document.createTextNode("Card Number"));
                            var hcel3= document.createElement("th");
                            hcel3.appendChild(document.createTextNode("Expiry Date"));

                            headerRow.appendChild(hcel1);
                            headerRow.appendChild(hcel2);
                            headerRow.appendChild(hcel3);
                            table.appendChild(headerRow);

                            data.forEach(function (card) {
                                var row = document.createElement("tr");

                                var cel1= document.createElement("td");
                                cel1.appendChild(document.createTextNode(card["bankName"]));

                                var cel2= document.createElement("td");
                                cel2.appendChild(document.createTextNode(card["cardNumber"]));

                                var cel3= document.createElement("td");
                                cel3.appendChild(document.createTextNode(card["expiryDate"]));

                                row.appendChild(cel1);
                                row.appendChild(cel2);
                                row.appendChild(cel3);

                                table.appendChild(row)
                            })
                            render.appendChild(table)
                            //                        document.getElementById("render").innerHTML = JSON.stringify(data);
                        });
                    }
                )
                .catch(function(err) {
                    console.log('There was an error fetching cards', err);
                });
        }

    </script>
</html>