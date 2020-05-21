
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


$(document).ready(function(){
    $('.bestSelling').slick({

        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 4000,
        arrows: true,
        dots: false,
        pauseOnHover: true,
        draggable:true,
        prevArrow:"<button type='button' style='margin: 0;position: absolute;z-index: 5;top: 50%;left:2%;background:transparent;border:none;-ms-transform: translate(-50%, -50%);transform: translate(-50%, -50%);' class='slick-prev pull-left'><i class='fa fa-angle-left' aria-hidden='true'></i></button>",
        nextArrow:"<button type='button' style='margin: 0;position: absolute;top: 50%;right:-0%;background:transparent;border:none;-ms-transform: translate(-50%, -50%);transform: translate(-50%, -50%);' class='slick-next pull-right'><i class='fa fa-angle-right' aria-hidden='true'></i></button>",
        responsive: [{
            breakpoint: 768,
            settings: {
                slidesToShow: 2
            }
        }, {
            breakpoint: 520,
            settings: {
                slidesToShow: 1
            }
        }]
    });
});


