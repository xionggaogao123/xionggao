// 1>左边选项菜单选中后的样式
$('.manager-nav a').click(function  () {
    $(this).parent().addClass('act');
    $(this).parent().siblings().removeClass('act');
});





