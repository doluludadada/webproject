// homepage.js
document.addEventListener('DOMContentLoaded', function() {
    var toggleNav = document.getElementById('toggleNav');
    if (toggleNav) {
        toggleNav.addEventListener('click', function() {
            var navMenu = document.getElementById('navMenu');
            navMenu.classList.toggle('active');

            // 取得按鈕中的圖標元素
            var navIcon = this.querySelector('.nav-toggle-icon');
            if (navIcon) {
                // 切換圖標內容
                navIcon.textContent = navMenu.classList.contains('active') ? '×' : '☰';
            }
        });
    } else {
        console.error('Element with ID "toggleNav" was not found.');
    }
});
