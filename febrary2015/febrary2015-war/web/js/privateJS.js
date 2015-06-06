options = {
    // Required. Called when a user selects an item in the Chooser.
    success: function (files) {
        files.forEach(function (file) {
            add_img_to_list(file);
        });
    },
    // Optional. Called when the user closes the dialog without selecting a file
    // and does not include any parameters.
    cancel: function () {
        alert("Why?");
    },
    // Optional. "preview" (default) is a preview link to the document for sharing,
    // "direct" is an expiring link to download the contents of the file. For more
    // information about link types, see Link types below.
    linkType: "direct", // or "direct"

    // Optional. A value of false (default) limits selection to a single file, while
    // true enables multiple file selection.
    multiselect: true, // or true

    // Optional. This is a list of file extensions. If specified, the user will
    // only be able to select files with these extensions. You may also specify
    // file types, such as "video" or "images" in the list. For more information,
    // see File types below. By default, all extensions are allowed.
    extensions: ['.pdf', '.doc', '.docx']
};

var button = Dropbox.createChooseButton(options);
document.getElementById("container").appendChild(button);

function add_img_to_list(file) {
    var xhr = new XMLHttpRequest();
    //xhr.open('GET', '../webresources/gen/getab?name=' + page, true);
    xhr.open('GET', '../webresources/generic/addbook?title=' + file.name + '&link=' + file.link, true);
    xhr.send(null);
    //попытка обновления страницы
    document.location.reload();
//    document.getElementById("div2").innerHTML += HTMLreader.replace("%data", file.link);
//    document.getElementById("in1").value = "web/viewer.html?file=" + file.link;
//    document.getElementById("in2").value = file.name;
//    document.getElementById("refURL").href = "web/viewer.html?file=" + file.link;
//    document.getElementById("refURL").href += "#page=2"; /*это не отрабатывает, хотя в index по ссылке JS reader, local link работает нормально*/
//    document.getElementById("readerButton").action = "web/viewer.html?file=" + file.link;
}

var MY_SPECIAL_BOOK_ID;
function forRefOnClick(lastpage) {
    MY_SPECIAL_BOOK_ID = lastpage;
    alert("asfgagsad".replace("sad", "AAA"));
}

function opa() {
    document.getElementById("result").innerHTML = getCookie("name");
}


function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}



function setCookie(name, value, options) {
    options = {
        path: '/',
        expires: 3600
    };
    name = "name";
    options = options || {};

    var expires = options.expires;

    if (typeof expires === "number" && expires) {
        var d = new Date();
        d.setTime(d.getTime() + expires * 1000);
        expires = options.expires = d;
    }
    if (expires && expires.toUTCString) {
        options.expires = expires.toUTCString();
    }

    value = encodeURIComponent(value);

    var updatedCookie = name + "=" + value;

    for (var propName in options) {
        updatedCookie += "; " + propName;
        var propValue = options[propName];
        if (propValue !== true) {
            updatedCookie += "=" + propValue;
        }
    }

    document.cookie = updatedCookie;
}

