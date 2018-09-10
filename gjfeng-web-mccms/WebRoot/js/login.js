$(document).ready(function(){
    $('.input-group').each(function(){
        $(this).on('click',function(){
            $(this).children('.input-val').hide();
            $(this).children('input').focus();
            $(this).siblings().children('.input-val').show();
            if($('input').val() != ''){
                $('.input-val').hide();
            }
        });
    });
});












