

function previewFiles() {
  console.log("abx");
    var preview = document.querySelector('#photos');
    var files   = document.querySelector('input[type=file]').files;
  
    function readAndPreview(file) {
  
      // Make sure `file.name` matches our extensions criteria
      if ( /\.(jpe?g|png|gif)$/i.test(file.name) ) {
        var reader = new FileReader();
  
        reader.addEventListener("load", function () {
          var image = new Image();
          image.title = file.name;
          image.src = this.result;
          preview.appendChild( image );
        }, false);
  
        reader.readAsDataURL(file);
      }
  
    }
  
    if (files) {
      [].forEach.call(files, readAndPreview);
    }
  
}

window.addEventListener("resize",resizeWindow);


function resizeWindow(){
    var width = window.innerWidth;
    console.log(width);
    if(width>=1200 || (width>=992 && width<1200))
        changeColumn(5);
    else if(width<992 && width>768)
            changeColumn(4);
            else 
                changeColumn(3);
}


function changeColumn(column){
    var style = document.getElementById("photos").style;
    style.columnCount = column;
    style.webkitColumnCount = column;

}

function deleteImg(){
    var imgs = document.getElementById("photos");
    imgs.innerHTML=''
    var inputFiles = document.getElementById("browse");
    inputFiles.value ='';

}


