$(document).ready(function() {
    $("button").click(function() {
        $("#select_box").removeClass("active")
    });
    optionClick();
});

function optionClick() {
    $(".option").click(function() {
        var hientai = $(this).text();
        console.log(hientai)
        $("#input_selected").text(hientai)
        $("#select_box").addClass("active")

    });
}