
function checkBillingAddress(){
    if($("#theSameAsShippingAddress").is(":checked")) {
        $(".billingAddress").prop("disabled", true);
    }   else {
        $(".billingAddress").prop("disabled", false);
    }
}

function checkPasswordMatch(){
    var password = $("#newPassword").val();
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

function checkCreatePasswordMatch(){
    var createPassword = $("#createPassword").val();
    var txtConfirmCreatePassword = $("#txtConfirmCreatePassword").val();

    if(createPassword == "" && txtConfirmCreatePassword ==""){
        $("#checkCreatePasswordMatch").html("");
        $("#registration").prop('disabled', false);
    }else{
        if(createPassword != txtConfirmCreatePassword){
            $("#checkCreatePasswordMatch").html("Passwords do not match!");
            $("#registration").prop('disabled', true);
        }else{
            $("#checkCreatePasswordMatch").html("Passwords match!");
            $("#registration").prop('disabled', false);
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
        $("#newPassword").keyup(checkPasswordMatch);
        $("#createPassword").keyup(checkCreatePasswordMatch);
        $("#txtConfirmCreatePassword").keyup(checkCreatePasswordMatch);

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

$("#shippingMethod").change(function() {
  if ($(this).val() == "toAddress") {
    $('#toAddress').show();
    $('#shippingAddressName').attr('required', '');
    $('#shippingAddressName').attr('data-error', 'This field is required.');
    $('#shippingAddressAddress').attr('required', '');
    $('#shippingAddressAddress').attr('data-error', 'This field is required.');
    $('#shippingAddressPhone').attr('required', '');
    $('#shippingAddressPhone').attr('data-error', 'This field is required.');
    $('#shippingAddressCity').attr('required', '');
    $('#shippingAddressCity').attr('data-error', 'This field is required.');
    $('#toOffice').hide();
    $('#shippingAddressNameEkont').removeAttr('required');
    $('#shippingAddressNameEkont').removeAttr('data-error');
    $('#shippingAddressAddressEkont').removeAttr('required');
    $('#shippingAddressAddressEkont').removeAttr('data-error');
    $('#shippingAddressPhoneEkont').removeAttr('required');
    $('#shippingAddressPhoneEkont').removeAttr('data-error');
    $('#shippingAddressCityEkont').removeAttr('required');
    $('#shippingAddressCityEkont').removeAttr('data-error');
  } else {
    $('#toOffice').show();
    $('#shippingAddressNameEkont').attr('required', '');
    $('#shippingAddressNameEkont').attr('data-error', 'This field is required.');
    $('#shippingAddressAddressEkont').attr('required', '');
    $('#shippingAddressAddressEkont').attr('data-error', 'This field is required.');
    $('#shippingAddressPhoneEkont').attr('required', '');
    $('#shippingAddressPhoneEkont').attr('data-error', 'This field is required.');
    $('#shippingAddressCityEkont').attr('required', '');
    $('#shippingAddressCityEkont').attr('data-error', 'This field is required.');
    $('#toAddress').hide();
    $('#shippingAddressName').removeAttr('required');
    $('#shippingAddressName').removeAttr('data-error');
    $('#shippingAddressAddress').removeAttr('required');
    $('#shippingAddressAddress').removeAttr('data-error');
    $('#shippingAddressPhone').removeAttr('required');
    $('#shippingAddressPhone').removeAttr('data-error');
    $('#shippingAddressCity').removeAttr('required');
    $('#shippingAddressCity').removeAttr('data-error');
  }
});
$("#shippingMethod").trigger("change");


