document.addEventListener('DOMContentLoaded', function() {
    // 切換到註冊表單的按鈕
    const signUpButton = document.getElementById('kt_login_signup');
    if (signUpButton) {
        signUpButton.addEventListener('click', function(e) {
            e.preventDefault();
            window.location.href = '/login?showSignupForm=true';
        });
    }

    // 取消按鈕（返回登錄表單）
    const cancelButton = document.getElementById('kt_login_signup_cancel');
    if (cancelButton) {
        cancelButton.addEventListener('click', function(e) {
            e.preventDefault();
            window.location.href = '/login';
        });
    }

    // 登錄表單驗證
    const loginForm = document.getElementById('kt_login_signin_form');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            const username = this.querySelector('input[name="username"]').value.trim();
            const password = this.querySelector('input[name="password"]').value.trim();

            if (!username || !password) {
                e.preventDefault();
                alert('Username and password are required!');
                return false;
            }
        });
    }

    // 註冊表單驗證
    const signupForm = document.getElementById('kt_login_signup_form');
    if (signupForm) {
        signupForm.addEventListener('submit', function(e) {
            const username = this.querySelector('input[name="username"]').value.trim();
            const email = this.querySelector('input[name="email"]').value.trim();
            const password = this.querySelector('input[name="password"]').value.trim();
            const confirmPassword = this.querySelector('input[name="cpassword"]').value.trim();

            // 驗證所有欄位是否為空
            if (!username || !email || !password || !confirmPassword) {
                e.preventDefault();
                alert('All fields are required!');
                return false;
            }

            // 驗證密碼是否一致
            if (password !== confirmPassword) {
                e.preventDefault();
                alert('Passwords do not match!');
                return false;
            }
        });
    }

    // 密碼可見性切換
    const passwordToggle = document.getElementById('toggle-password');
    const passwordInput = document.getElementById('in-password');

    if (passwordToggle && passwordInput) {
        passwordToggle.addEventListener('click', function() {
            const type = passwordInput.type === 'password' ? 'text' : 'password';
            passwordInput.type = type;

            // 切換眼睛圖標
            this.classList.toggle('fa-eye');
            this.classList.toggle('fa-eye-slash');
        });
    }
});
