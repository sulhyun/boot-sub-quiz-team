// 관리자 사이드바 길이 컨텐츠에 길이에 맞게 설정
$(window).on('resize load', function() {
    const sidebar = $('#admin-sidebar');
    const content = $('#content');
    const contentHeight = content.outerHeight();
    const viewportHeight = $(window).height();

    sidebar.css('height', Math.max(contentHeight, viewportHeight - 80) + 'px');
});