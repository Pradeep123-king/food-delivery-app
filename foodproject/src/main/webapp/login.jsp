<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login | food delivery</title>
        <!-- Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <!-- Google Fonts - Keeping Outfit for consistency -->
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800;900&display=swap"
            rel="stylesheet">

        <style>
            :root {
                --brand-orange: #ff5200;
                --text-dark: #2d2d32;
                --text-gray: #7e808c;
                --border-color: #d4d5d9;
                --bg-page: #ffffff;
                --transition: all 0.3s ease;
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Outfit', sans-serif;
            }

            body {
                height: 100vh;
                background-color: var(--bg-page);
                display: flex;
                justify-content: center;
                align-items: center;
                color: var(--text-dark);
                -webkit-font-smoothing: antialiased;
            }

            /* 1. THE "WOW" MINIMALIST CONTAINER */
            .login-box {
                width: 100%;
                max-width: 480px;
                padding: 40px;
                text-align: left;
                /* Authentic left-aligned forms look more professional */
                animation: fadeIn 0.8s ease;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(10px);
                }

                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            /* 2. THE TOP BRANDING - Matching your logo */
            .brand-logo {
                font-size: 32px;
                font-weight: 900;
                color: var(--brand-orange);
                margin-bottom: 50px;
                letter-spacing: -1.5px;
                display: block;
            }

            .login-box h2 {
                font-size: 36px;
                font-weight: 800;
                margin-bottom: 12px;
                letter-spacing: -1px;
                color: #000;
            }

            .login-box .sub-title {
                font-size: 16px;
                color: var(--text-gray);
                margin-bottom: 40px;
                font-weight: 500;
            }

            /* 3. INPUT FIELDS - Clean, Border-Focused */
            .field-group {
                margin-bottom: 30px;
            }

            .field-group label {
                display: block;
                font-size: 13px;
                font-weight: 800;
                color: #1a1a1a;
                margin-bottom: 12px;
                text-transform: uppercase;
                letter-spacing: 0.5px;
            }

            .field-group input {
                width: 100%;
                padding: 20px;
                border: 2px solid #edeff2;
                border-radius: 12px;
                font-size: 16px;
                font-weight: 600;
                color: var(--text-dark);
                transition: var(--transition);
                background: #fbfbfb;
            }

            .field-group input:focus {
                background: #fff;
                border-color: var(--brand-orange);
                outline: none;
            }

            /* 4. THE ACTION BUTTON - High Impact */
            .submit-btn {
                width: 100%;
                padding: 22px;
                background: var(--brand-orange);
                color: white;
                border: none;
                border-radius: 14px;
                font-size: 16px;
                font-weight: 800;
                text-transform: uppercase;
                letter-spacing: 1.5px;
                cursor: pointer;
                transition: var(--transition);
                box-shadow: 0 10px 30px rgba(255, 82, 0, 0.15);
                margin-top: 10px;
            }

            .submit-btn:hover {
                background: #e64a00;
                transform: translateY(-3px);
                box-shadow: 0 15px 40px rgba(255, 82, 0, 0.25);
            }

            .submit-btn:active {
                transform: translateY(0);
            }

            /* 5. ERROR STATE */
            .error-notif {
                color: #e53e3e;
                background: #fff5f5;
                padding: 15px;
                border-radius: 10px;
                margin-bottom: 30px;
                font-size: 14px;
                font-weight: 700;
                text-align: center;
                border: 1px solid rgba(229, 62, 62, 0.1);
            }

            /* 6. FOOTER LINKS */
            .register-footer {
                margin-top: 40px;
                text-align: left;
                font-size: 15px;
                font-weight: 500;
                color: var(--text-gray);
            }

            .register-footer a {
                color: var(--brand-orange);
                text-decoration: none;
                font-weight: 800;
                margin-left: 5px;
            }

            .register-footer a:hover {
                text-decoration: underline;
            }

            /* RESPONSIVE */
            @media (max-width: 600px) {
                .login-box {
                    padding: 30px 20px;
                }

                .login-box h2 {
                    font-size: 28px;
                }

                .brand-logo {
                    margin-bottom: 30px;
                }
            }
        </style>
    </head>

    <body>

        <div class="login-box">
            <span class="brand-logo">food delivery</span>

            <h2>Login</h2>
            <p class="sub-title">Sign in to your account for the best experience.</p>

            <% String error=(String) request.getAttribute("error"); if (error !=null) { %>
                <div class="error-notif">
                    <i class="fas fa-exclamation-circle"></i>
                    <%= error %>
                </div>
                <% } %>

                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="field-group">
                            <label>Email Address</label>
                            <input type="text" name="email" placeholder="example@email.com" required>
                        </div>

                        <div class="field-group">
                            <label>Password</label>
                            <input type="password" name="password" placeholder="••••••••" required>
                        </div>

                        <button type="submit" class="submit-btn">Continue to Food</button>
                    </form>

                    <div class="register-footer">
                        Don't have an account?
                        <a href="register.jsp">Create for free</a>
                    </div>
        </div>

    </body>

    </html>