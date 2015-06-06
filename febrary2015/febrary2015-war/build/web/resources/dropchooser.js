//пробую добавить дропбокс
var ssilka;
var HTMLreader = "<a href='%data'>Ссылка на вашу книгу</a>";
var HTMLpdf = "<p:media id='mypdf' value='%data' player='pdf' height='600px' width='100%'/>";
//file = {
//
//    // Name of the file.
//    name: "filename.txt",
//
//    // URL to access the file, which varies depending on the linkType specified when the
//    // Chooser was triggered.
//    link: "https://...",
//
//    // Size of the file in bytes.
//    bytes: 464,
//
//    // URL to a 64x64px icon for the file based on the file's extension.
//    icon: "https://...",
//
//    // A thumbnail URL generated when the user selects images and videos.
//    // If the user didn't select an image or video, no thumbnail will be included.
//    thumbnailLink: "https://...?bounding_box=75&mode=fit",
//};

options = {

    // Required. Called when a user selects an item in the Chooser.
    success: function(files) {
        files.forEach(function(file) {
        add_img_to_list(file);
      });
    },

    // Optional. Called when the user closes the dialog without selecting a file
    // and does not include any parameters.
    cancel: function() {
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
  document.getElementById("div2").innerHTML += HTMLreader.replace("%data", file.link);
  document.getElementById("in1").value = "web/viewer.html?file=" + file.link;
  document.getElementById("in2").value = file.name;
  document.getElementById("refURL").href = "web/viewer.html?file=" + file.link;
  document.getElementById("refURL").href += "#page=2"; /*это не отрабатывает, хотя в index по ссылке JS reader, local link работает нормально*/
  document.getElementById("readerButton").action = "web/viewer.html?file=" + file.link;
}
