<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration | foodie</title>
        <!-- Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800;900&display=swap"
            rel="stylesheet">

        <style>
            :root {
                --primary: #ff5200;
                --secondary: #282c3f;
                --success: #48c479;
                --bg-body: #f7f9fc;
                --white: #ffffff;
                --gray-light: #f1f3f6;
                --gray-text: #686b78;
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Outfit', sans-serif;
            }

            body {
                background-color: var(--bg-body);
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 50px 20px;
                background-image: radial-gradient(circle at 10% 10%, rgba(255, 82, 0, 0.05) 0%, transparent 20%),
                    radial-gradient(circle at 90% 90%, rgba(72, 196, 121, 0.05) 0%, transparent 20%);
            }

            /* 1. THE "WOW" HUB CARD */
            .register-hub {
                width: 100%;
                max-width: 500px;
                background: var(--white);
                border-radius: 48px;
                padding: 50px;
                box-shadow: 0 40px 90px rgba(0, 0, 0, 0.07);
                position: relative;
                animation: hubSlide 0.8s cubic-bezier(0.16, 1, 0.3, 1);
            }

            @keyframes hubSlide {
                from {
                    opacity: 0;
                    transform: translateY(30px);
                }

                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            /* Top Decoration */
            .top-orb {
                position: absolute;
                top: -20px;
                right: 40px;
                width: 60px;
                height: 60px;
                background: var(--primary);
                border-radius: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                color: white;
                font-size: 24px;
                box-shadow: 0 15px 30px rgba(255, 82, 0, 0.3);
                transform: rotate(-10deg);
            }

            header {
                margin-bottom: 40px;
            }

            header h2 {
                font-size: 34px;
                font-weight: 900;
                color: var(--secondary);
                letter-spacing: -1.5px;
                line-height: 1;
            }

            header p {
                color: var(--gray-text);
                font-size: 15px;
                margin-top: 10px;
                font-weight: 500;
            }

            /* 2. MODERN FIELDSET */
            .form-stack {
                display: flex;
                flex-direction: column;
                gap: 20px;
            }

            .input-row {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 15px;
            }

            .field-box {
                position: relative;
            }

            .field-box label {
                display: block;
                font-size: 11px;
                font-weight: 900;
                text-transform: uppercase;
                letter-spacing: 1px;
                color: var(--secondary);
                margin-bottom: 8px;
                margin-left: 5px;
            }

            .field-box input,
            .field-box select {
                width: 100%;
                padding: 16px 20px;
                background: var(--gray-light);
                border: 2px solid transparent;
                border-radius: 18px;
                font-size: 15px;
                font-weight: 600;
                color: var(--secondary);
                transition: 0.3s;
                outline: none;
            }

            .field-box input:focus,
            .field-box select:focus {
                background: #fff;
                border-color: var(--primary);
                box-shadow: 0 10px 20px rgba(255, 82, 0, 0.05);
            }

            /* 3. THE "LOCATION PIN" ADDRESS (THE ANOTHER WAY) */
            .location-block {
                background: #fdfdfd;
                border: 2px solid #edeff2;
                border-radius: 24px;
                padding: 20px;
                margin-top: 10px;
                position: relative;
                transition: 0.3s;
            }

            .location-block:focus-within {
                border-color: var(--primary);
                background: #fff;
            }

            .location-label {
                display: flex;
                align-items: center;
                gap: 8px;
                font-size: 12px;
                font-weight: 800;
                color: var(--gray-text);
                margin-bottom: 12px;
            }

            .location-label i {
                color: var(--primary);
                font-size: 16px;
            }

            .location-block textarea {
                width: 100%;
                background: transparent;
                border: none;
                resize: none;
                outline: none;
                font-size: 15px;
                font-weight: 600;
                color: var(--secondary);
            }

            /* 4. PREMIUM SUBMIT BUTTON */
            .btn-wow {
                width: 100%;
                padding: 22px;
                margin-top: 25px;
                background: var(--secondary);
                color: white;
                border: none;
                border-radius: 20px;
                font-size: 15px;
                font-weight: 800;
                text-transform: uppercase;
                letter-spacing: 2px;
                cursor: pointer;
                transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
                box-shadow: 0 15px 35px rgba(40, 44, 63, 0.2);
            }

            .btn-wow:hover {
                background: var(--primary);
                transform: translateY(-5px);
                box-shadow: 0 20px 45px rgba(255, 82, 0, 0.3);
            }

            /* 5. ERROR MSG */
            .err-status {
                background: #fff1f2;
                color: #e11d48;
                padding: 15px;
                border-radius: 12px;
                margin-bottom: 25px;
                text-align: center;
                font-size: 14px;
                font-weight: 700;
                border: 1px solid rgba(225, 29, 72, 0.1);
            }

            /* 6. LOGIN FOOTER */
            .footer-shout {
                text-align: center;
                margin-top: 35px;
                font-size: 14px;
                font-weight: 600;
                color: var(--gray-text);
            }

            .footer-shout a {
                color: var(--primary);
                text-decoration: none;
                font-weight: 800;
                margin-left: 5px;
            }

            @media (max-width: 500px) {
                .register-hub {
                    padding: 40px 25px;
                    border-radius: 36px;
                }

                .input-row {
                    grid-template-columns: 1fr;
                }

                header h2 {
                    font-size: 28px;
                }
            }
        </style>
    </head>

    <body>

        <div class="register-hub">
            <!-- Decoration -->
            <div class="top-orb">
                <i class="fas fa-pizza-slice"></i>
            </div>

            <header>
                <h2>Create Account</h2>
                <p>Ready to join the world of flavors?</p>
            </header>

            <% String msg=(String) request.getAttribute("msg"); if (msg !=null) { %>
                <div class="err-status">
                    <i class="fas fa-circle-exclamation"></i>
                    <%= msg %>
                </div>
                <% } %>

                    <form action="register" method="post" onsubmit="return verifyPass()">
                        <div class="form-stack">
                            <div class="field-box">
                                <label>Full Name</label>
                                <input type="text" name="username" placeholder="Pradeep" required>
                            </div>

                            <div class="input-row">
                                <div class="field-box">
                                    <label>Email Address</label>
                                    <input type="email" name="email" placeholder="name@gmail.com" required>
                                </div>
                                <div class="field-box">
                                    <label>Mobile</label>
                                    <input type="text" name="phonenumber" placeholder="10-digit">
                                </div>
                            </div>

                            <div class="field-box">
                                <label>Role</label>
                                <select name="role" required>
                                    <option value="Customer">Customer</option>
                                    <option value="Restaurant_Admin">Restaurant Admin</option>
                                    <option value="Delivery_Agent">Delivery Agent</option>
                                </select>
                            </div>

                            <div class="input-row">
                                <div class="field-box">
                                    <label>Password</label>
                                    <input type="password" name="password" id="p1" placeholder="••••••••" required>
                                </div>
                                <div class="field-box">
                                    <label>Confirm</label>
                                    <input type="password" name="confirm_password" id="p2" placeholder="••••••••"
                                        required>
                                </div>
                            </div>

                            <!-- LOCATION HUB (THE ANOTHER WAY) -->
                            <div class="location-block">
                                <div class="location-label">
                                    <i class="fas fa-location-dot"></i>
                                    <span>Default Delivery Address</span>
                                </div>
                                <textarea name="address" rows="2"
                                    placeholder="Street, Building, Landmark..."></textarea>
                            </div>
                        </div>

                        <button type="submit" class="btn-wow" id="regSubmit">Join Now</button>
                    </form>

                    <div class="footer-shout">
                        Already have an account? <a href="login.jsp">Log In</a>
                    </div>
        </div>

        <script>
            function verifyPass() {
                var pass = document.getElementById('p1').value;
                var conf = document.getElementById('p2').value;
                if (pass !== conf) {
                    alert("Passwords do not match. Please verify.");
                    return false;
                }
                return true;
            }
        </script>
    </body>

    </html>