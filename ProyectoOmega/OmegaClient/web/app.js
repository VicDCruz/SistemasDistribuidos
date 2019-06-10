var user = document.getElementById('session').innerHTML;
user = user.substring(0, user.length - 1);
var start = 0;
var limit = 0;
var mainUrl = "http://localhost:8080/OmegaRest/webresources/Operations";

function createNewRow(tableName) {
    var table = document.getElementById(tableName).getElementsByTagName("tbody")[0];
    var row = table.insertRow(table.rows.length);
    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);

    cell0.innerHTML = "<input type='checkbox' name='isPrimary' id='isPrimary' onclick='setPrimary(this)'>";
    cell1.innerHTML = "<input type='text' name='arg' id='arg'>";
    cell2.innerHTML = '<select name="type" id="type">' +
        '<option value="varchar(50)">Texto</option>' +
        '<option value="int">Entero</option>' +
        '<option value="decimal">Decimal</option>' +
        '<option value="date">Fecha</option>' +
        '</select>';
}

function deleteLast(tableName) {
    var table = document.getElementById(tableName).getElementsByTagName("tbody")[0];
    var tableLength = table.rows.length;
    if (tableLength > 1)
        table.deleteRow(tableLength - 1);
}

function deleteAll(tableName, until, create) {
    var table = document.getElementById(tableName).getElementsByTagName("tbody")[0];
    var tableLength = table.rows.length;
    while (tableLength > until) {
        deleteLast(tableName);
        tableLength--;
    }
    if (table.rows.length == 1)
        table.deleteRow(0);
    if (create)
        createNewRow(tableName);
}

function setPrimary(checbox) {
    var checboxes = document.getElementsByName(checbox.name);
    checboxes.forEach(function (item) {
        if (item != checbox)
            item.checked = false;
    });
}

function sendTable(tableName) {
    var table = document.getElementById(tableName).getElementsByTagName("tbody")[0];
    var tableToSend = document.getElementById("tableName").value;
    if (tableToSend != "") {
        var data = {
            "type": "table",
            "user": user,
            "table": tableToSend,
            "attributes": "",
            "types": "",
            "values": "_"
        };
        var canSend = true;
        for (let index = 0; index < table.rows.length; index++) {
            const row = table.rows[index].cells;

            var isPrimary = row[0].childNodes[0].checked;
            var attribute = row[1].childNodes[0].value;
            if (attribute != "")
                data["attributes"] += attribute + ",";
            else
                canSend = false;
            data["types"] += row[2].childNodes[0].value + ",";
            if (isPrimary && attribute != "") {
                data["attributes"] += "primary,";
                data["types"] += "key(" + attribute + "),";
            }
        }
        if (data["attributes"] != ",")
            data["attributes"] = data["attributes"].substring(0, data["attributes"].length - 1);
        if (data["types"] != ",")
            data["types"] = data["types"].substring(0, data["types"].length - 1);
        if (canSend)
            sendRequest("POST", mainUrl, data, getTablesName);
        else
            alert("Hay un campo vacio para crear la tabla");
    }
    else
        alert("Poner un nombre de la tabla");
}

function sendRequest(method, url, data, callback) {
    const Http = new XMLHttpRequest;
    var output = "";
    Http.open(method, url, true);
    Http.setRequestHeader('Content-Type', 'application/json');
    // Http.setRequestHeader('Access-Control-Allow-Origin', '*');
    if (!isEmptyObject(data)) {
        Http.send(JSON.stringify(data));
    } else {
        Http.send();
    }

    Http.onreadystatechange = function () {
        if (Http.readyState == 4 &&
            (Http.status == 200 || Http.status == 204)) {
            if (callback != null)
                if (Http.responseText.charAt(0) == '<')
                    callback(user, Http.responseText);
                else
                    callback(user, JSON.parse(Http.responseText));
        }
    }
}

function getTablesName(userName, data) {
    if (userName == null)
        userName = user;

    sendRequest("GET", mainUrl + "?type=name&user=" + userName, {}, printTablesName);

    if (!isEmptyObject(data)) {
        if (data.hasOwnProperty('result') && data["result"])
            if (data["method"] === "post")
                alert("Nueva tabla creada");
            else if (data["method"] === "delete")
                alert("Tabla eliminada");
            else
                alert("No se creo nueva tabla");
    }
}

function printTablesName(user, data) {
    var dll = document.getElementById("myBd");
    for (var i = dll.options.length - 1; i >= 1; i--) {
        dll.remove(i);
    }

    if (!isEmptyObject(data)) {
        data["result"].forEach(name => {
            var option = document.createElement("option");
            var indexLowScore = name.indexOf("_");
            option.text = name.substring(0, indexLowScore).toUpperCase();
            option.value = name;
            dll.add(option);
        });
    }
}

function getTableColumns(select) {
    if (select.value != "") {
        sendRequest("GET", mainUrl + "?type=columns&table=" + select.value, {}, displayNewTuple);
        length = document.getElementById("lengthDll").value;
        document.getElementById("deleteTableBtn").disabled = false;
        if (length !== "0")
            sendRequest("GET", mainUrl + "?type=scroll&user=" + user + "&table=" + select.value + "&start=" + start + "&length=" + length, {}, printTable);
        else
            alert("Selecciona una longitud");
        sendRequest("GET", mainUrl + "?type=length&table=" + select.value, {}, (user, data) => {
            if (data.hasOwnProperty("result")) {
                limit = parseInt(data["result"], 10);
            }
        });
    } else
        alert("Selecciona una opción");
}

function displayNewTuple(user, data) {
    var table = document.getElementById('myAttributes').getElementsByTagName("tbody")[0];
    if (table.rows.length > 0)
        deleteAll('myAttributes', 0, false);
    if (!isEmptyObject(data)) {
        if (data.hasOwnProperty('result') && data.hasOwnProperty('columnsTypes')) {
            var count = 0;
            data["result"].forEach(element => {
                var row = table.insertRow(table.rows.length);
                var cell0 = row.insertCell(0);
                var cell1 = row.insertCell(1);
                var cell2 = row.insertCell(2);

                cell0.innerHTML = "<span>" + element + "</span>";
                cell1.innerHTML = "<input type='text' name='arg' id='arg'>";
                cell2.innerHTML = "<span>" + data["columnsTypes"][count] + "</span>";
                count++;
            });
        }
        document.getElementById('newTupleBtn').disabled = false;
    } else
        console.error("Sin datos");
}

function sendTuple(tableName) {
    var table = document.getElementById(tableName).getElementsByTagName("tbody")[0];
    var tableToSend = document.getElementById("myBd").value
    if (tableToSend != "") {
        var data = {
            "type": "tuple",
            "user": user,
            "table": tableToSend,
            "attributes": "_",
            "types": "_",
            "values": ""
        };
        var canSend = true;
        for (let index = 0; index < table.rows.length; index++) {
            const row = table.rows[index].cells;

            var val = row[1].childNodes[0].value;
            var type = row[2].childNodes[0].innerHTML;
            if (val != "")
                data["values"] += ((type === "VARCHAR") ? "'" + val + "'" : val) + ",";
            else
                canSend = false;
        }
        if (data["values"] != ",")
            data["values"] = data["values"].substring(0, data["values"].length - 1);
        if (canSend) {
            sendRequest("POST", mainUrl, data, updateScroll);
            limit++;
        } else
            alert("Hay un campo vacio para crear la tupla");
    } else
        alert("Selecciona una BD");
}

function printTable(user, data) {
    console.log(data)
    var select = document.getElementById('myBd');
    var table = document.getElementById('scrollTable').getElementsByTagName("tbody")[0];
    if (!isEmptyObject(data)) {
        if (table.rows.length > 0)
            deleteAll('scrollTable', 0, false);
        data["result"].forEach(rowStr => {
            var row = table.insertRow(table.rows.length);
            var cell = row.insertCell(0);
            cell.innerHTML = "<input type='checkbox' onclick='enable(" + '"deleteBtn"' + "); enable(" + '"updateBtn"' + ")'>";
            var count = 1;
            rowStr.split("|").forEach(element => {
                row.insertCell(count).innerHTML = "<input type='text' onchange='hasChange(this)' onkeyup='hasChange(this)' value='" + element + "'>";
                count++;
            });
        });
        sendRequest("GET", mainUrl + "?type=columns&table=" + select.value, {}, printTableHeaders);
    }
}

function printTableHeaders(user, data) {
    var table = document.getElementById('scrollTable').getElementsByTagName("thead")[0];
    deleteChildren(table);

    if (!isEmptyObject(data)) {
        if (data.hasOwnProperty('result') && data.hasOwnProperty('columnsTypes')) {
            var row = table.insertRow(table.rows.length);
            row.insertCell(0).innerHTML = "";
            var count = 1;
            data["result"].forEach(element => {
                row.insertCell(count).outerHTML = "<th>" + element + "</th>";
                count++;
            });
        }
    } else
        console.error("Sin datos");
}

function deleteChildren(object) {
    var fc = object.firstChild;
    while (fc) {
        object.removeChild(fc);
        fc = object.firstChild;
    }
}

function updateScroll(isNew, result) {
    var select = document.getElementById('myBd');
    length = document.getElementById("lengthDll").value;
    if ((typeof isNew === 'boolean' && isNew) || typeof isNew === 'string')
        start = 0;
    else if (typeof result === 'string') {
        lengthNum = parseInt(length, 10);
        if (result === '-' && start >= lengthNum)
            start -= lengthNum
        if (result === '+' && start + lengthNum < limit)
            start += lengthNum
    }
    console.log("Casi enviando");
    if (length !== "0")
        sendRequest("GET", mainUrl + "?type=scroll&user=" + user + "&table=" + select.value + "&start=" + start + "&length=" + length, {}, printTable);
}

function deleteTable() {
    if (window.confirm("¿Seguro que quieres borrar la tabla?")) {
        var myBd = document.getElementById('myBd').value;

        var data = {
            "type": "table",
            "user": user,
            "table": myBd,
            "conditions": "_"
        };
        sendRequest("DELETE", mainUrl + "/" + JSON.stringify(data), {}, getTablesName);
        deleteAll('myAttributes', 0, false);
        deleteAll('scrollTable', 0, false);
    }
}

function hasChange(input) {
    input.parentNode.style.backgroundColor = "#F5E773";
    input.parentNode.parentNode.cells[0].childNodes[0].checked = true;
    enable('deleteBtn');
    enable('updateBtn');
}

function enable(id) {
    var button = document.getElementById(id);
    if (noneSelected())
        button.disabled = true;
    else
        button.disabled = false;
}

function noneSelected() {
    var table = document.getElementById('scrollTable').getElementsByTagName("tbody")[0];
    var output = 0;
    for (let index = 0; index < table.rows.length; index++) {
        const row = table.rows[index].cells;

        var isSelected = row[0].childNodes[0].checked;
        if (isSelected)
            output++;
    }

    return output == 0;
}

function modifySelection(method) {
    var table = document.getElementById('scrollTable').getElementsByTagName("tbody")[0];
    var tableHeader = document.getElementById('scrollTable').getElementsByTagName("thead")[0];
    var tableAttributes = document.getElementById('myAttributes').getElementsByTagName("tbody")[0];
    var tableToSend = document.getElementById("myBd").value
    var data = {
        "user": user,
        "table": tableToSend,
        "conditions": ""
    };

    if (method === "DELETE") {
        data["type"] = "tuple";
    } else if (method === "PUT") {
        data["values"] = "";
    }

    const elementHeader = tableHeader.rows[0].cells;
    for (let i = 0; i < table.rows.length; i++) {
        const row = table.rows[i].cells;

        var isSelected = row[0].childNodes[0].checked;
        if (isSelected) {
            var type = tableAttributes.rows[0].cells[2].childNodes[0].innerHTML;
            data["conditions"] += elementHeader[1].innerHTML + "=" +
                ((type === "VARCHAR") ?
                    "'" + row[1].childNodes[0].value + "'" :
                    row[1].childNodes[0].value);
            if (method === "PUT") {
                for (let j = 1; j < row.length; j++) {
                    const newValue = row[j].childNodes[0].value;
                    var type = tableAttributes.rows[j - 1].cells[2].childNodes[0].innerHTML;
                    data["values"] += elementHeader[j].innerHTML + "=" + ((type === "VARCHAR") ? "'" + newValue + "'" : newValue) + ",";
                }
                data["values"] = data["values"].substring(0, data["values"].length - 1);
            }
            if (method === "PUT")
                sendRequest(method, mainUrl, data, updateScroll);
            else if (method === "DELETE")
                sendRequest(method, mainUrl + "/" + JSON.stringify(data), {}, updateScroll);
        }
    }
}

function isEmptyObject(obj) {
    return JSON.stringify(obj) === '{}';
}