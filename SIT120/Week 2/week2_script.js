(function (glob) {
    var statics = function () {
        this.clips = [ //make an array (or dictionary or whatever this is) of the audio clips
            new Howl({
                src: ["guh144.opus"],
                volume: 0.8
            }),
            new Howl({
                src: ["guh161.opus"],
                volume: 0.8
            }),
            new Howl({
                src: ["guh187.opus"],
                volume: 0.8
            })];
        this.moriSize = 0;
    }
    var stats = new statics();

    // note that guh numbering does not match calliID numbering
    const imageList = [];
    imageList[0] = "moriguh1.png";
    imageList[1] = "moriguh2.png";
    imageList[2] = "moriguh3.png";

    glob.body.addEventListener("click", function (event) { //checks for user clicks (may not work on iOS?)
        var calliID = Math.floor(Math.random() * stats.clips.length);
        stats.clips[calliID].play(); //every click, play audio based on the random number generated (calliID)
        var x = event.clientX, y = event.clientY, width = 50 + (1 * stats.moriSize), height = 50 + (1 * stats.moriSize); //image scales up with clicks based on the moriSize++ at end of script
        var mori = document.createElement("img");

        // do a lookup for the right image using imageList with calliID as the index number
        mori.src = imageList[calliID];

        // location isn't working the way I expect it to, but it's just a general demo so I guess it doesn't matter
        mori.style.top = (y - height / 2).toString() + "px";
        mori.style.left = (x - width / 2).toString() + "px";
        mori.style.height = height.toString() + "px";
        mori.style.width = width.toString() + "px";
        glob.body.appendChild(mori);
        stats.moriSize++; //increases size of image displayed each click

        // the top and left of each mori seem to work as expected (based on where you click), but nonetheless their actual displayed location doesn't match this
        console.log(mori.style.top);
        console.log(mori.style.left);
    });
})(window.document);

// canvas stuff
const canvas = document.getElementById("maincanvas");
const ctx = canvas.getContext("2d");

ctx.fillStyle = "orange";
ctx.fillRect(25, 5, 300, 100);