
function checkBillingAddress(){
    if($("#theSameAsShippingAddress").is(":checked")) {
        $(".billingAddress").prop("disabled", true);
    }   else {
        $(".billingAddress").prop("disabled", false);
    }
}

function checkPasswordMatch(){
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();

    if(password == "" && confirmPassword ==""){
        $("#checkPasswordMatch").html("");
        $("#updateUserInfoButton").prop('disabled', false);
    }else{
        if(password != confirmPassword){
            $("#checkPasswordMatch").html("Passwords do not match!");
            $("#updateUserInfoButton").prop('disabled', true);
        }else{
            $("#checkPasswordMatch").html("Passwords match!");
            $("#updateUserInfoButton").prop('disabled', false);
        }
    }
}


$(document).ready(function(){
        $(".cartItemQty").on('change', function(){
            var id=this.id;

            $('#update-item-'+id).css('display', 'inline-block');
        });
        $("#theSameAsShippingAddress").on('click', checkBillingAddress);
        $("#txtConfirmPassword").keyup(checkPasswordMatch);
        $("#txtNewPassword").keyup(checkPasswordMatch);
});

/*
    Carousel
*/
$('#carousel-example').on('slide.bs.carousel', function (e) {
    /*
        CC 2.0 License Iatek LLC 2018 - Attribution required
    */
    var $e = $(e.relatedTarget);
    var idx = $e.index();
    var itemsPerSlide = 5;
    var totalItems = $('.carousel-item').length;

    if (idx >= totalItems-(itemsPerSlide-1)) {
        var it = itemsPerSlide - (totalItems - idx);
        for (var i=0; i<it; i++) {
            // append slides to end
            if (e.direction=="left") {
                $('.carousel-item').eq(i).appendTo('.carousel-inner');
            }
            else {
                $('.carousel-item').eq(0).appendTo('.carousel-inner');
            }
        }
    }
});