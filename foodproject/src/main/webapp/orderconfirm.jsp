<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Confirmed | Foodie</title>
        <!-- Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800;900&display=swap"
            rel="stylesheet">

        <style>
            :root {
                --primary: #ff5200;
                --secondary: #282c3f;
                --success: #60b246;
                --bg: #ffffff;
                --text: #282c3f;
                --light-gray: #f2f4f7;
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Outfit', sans-serif;
            }

            body {
                background-color: var(--bg);
                color: var(--text);
                height: 100vh;
                overflow: hidden;
                display: flex;
                justify-content: center;
                align-items: center;
                perspective: 2000px;
                /* For 3D WOW effect */
            }

            /* 1. LAYERED 3D ENTRANCE */
            .order-scene {
                position: relative;
                width: 100%;
                max-width: 480px;
                padding: 20px;
                transform-style: preserve-3d;
                animation: sceneEnter 1.2s cubic-bezier(0.16, 1, 0.3, 1) both;
            }

            @keyframes sceneEnter {
                0% {
                    opacity: 0;
                    transform: translateZ(-500px) rotateX(15deg);
                }

                100% {
                    opacity: 1;
                    transform: translateZ(0) rotateX(0);
                }
            }

            /* 2. THE FLOATING HERO CARD */
            .hero-card {
                background: #ffffff;
                border-radius: 40px;
                padding: 60px 40px;
                text-align: center;
                box-shadow: 0 40px 100px rgba(0, 0, 0, 0.08);
                border: 1px solid rgba(0, 0, 0, 0.02);
                position: relative;
                z-index: 2;
            }

            /* 3. THE "PULSING" SUCCESS RING */
            .success-orbit {
                width: 120px;
                height: 120px;
                margin: 0 auto 40px;
                position: relative;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .ring {
                position: absolute;
                width: 100%;
                height: 100%;
                border-radius: 50%;
                border: 2px solid var(--success);
                opacity: 0;
                animation: ringPulse 2.5s infinite;
            }

            .ring:nth-child(2) {
                animation-delay: 0.8s;
            }

            .ring:nth-child(3) {
                animation-delay: 1.6s;
            }

            @keyframes ringPulse {
                0% {
                    transform: scale(0.6);
                    opacity: 0;
                }

                50% {
                    opacity: 0.4;
                }

                100% {
                    transform: scale(1.4);
                    opacity: 0;
                }
            }

            .check-icon {
                width: 80px;
                height: 80px;
                background: var(--success);
                color: white;
                border-radius: 50%;
                display: flex;
                justify-content: center;
                align-items: center;
                font-size: 36px;
                box-shadow: 0 10px 30px rgba(96, 178, 70, 0.4);
                z-index: 5;
                animation: checkBounce 1.5s cubic-bezier(0.175, 0.885, 0.32, 1.275) both;
            }

            @keyframes checkBounce {
                0% {
                    transform: scale(0);
                }

                100% {
                    transform: scale(1);
                }
            }

            /* 4. TYPOGRAPHY */
            h1 {
                font-size: 36px;
                font-weight: 900;
                margin-bottom: 12px;
                color: var(--secondary);
                letter-spacing: -1px;
            }

            p {
                font-size: 16px;
                color: var(--gray);
                line-height: 1.6;
                margin-bottom: 40px;
            }

            /* 5. DYNAMIC STATUS LABEL */
            .status-pill {
                display: inline-flex;
                align-items: center;
                gap: 10px;
                background: var(--light-gray);
                padding: 10px 24px;
                border-radius: 100px;
                font-weight: 800;
                font-size: 13px;
                letter-spacing: 0.5px;
                text-transform: uppercase;
                color: var(--secondary);
                margin-bottom: 40px;
            }

            .dot {
                width: 8px;
                height: 8px;
                background: var(--success);
                border-radius: 50%;
                animation: blink 1s infinite alternate;
            }

            @keyframes blink {
                from {
                    opacity: 0.3;
                    transform: scale(0.8);
                }

                to {
                    opacity: 1;
                    transform: scale(1.1);
                }
            }

            /* 6. WOW ACTION BUTTONS */
            .cta-group {
                display: flex;
                flex-direction: column;
                gap: 15px;
            }

            .btn-primary {
                background: var(--primary);
                color: white;
                padding: 20px;
                border-radius: 20px;
                text-decoration: none;
                font-weight: 800;
                font-size: 15px;
                text-transform: uppercase;
                letter-spacing: 1px;
                transition: 0.4s;
                box-shadow: 0 15px 35px rgba(255, 82, 0, 0.25);
                display: block;
            }

            .btn-primary:hover {
                transform: translateY(-5px) scale(1.02);
                box-shadow: 0 20px 45px rgba(255, 82, 0, 0.35);
            }

            .btn-secondary {
                color: var(--gray);
                font-size: 13px;
                font-weight: 700;
                text-decoration: none;
                text-transform: uppercase;
                letter-spacing: 1px;
                transition: 0.3s;
                padding: 10px;
            }

            .btn-secondary:hover {
                color: var(--primary);
            }

            /* Responsive */
            @media (max-width: 480px) {
                .hero-card {
                    padding: 40px 20px;
                    border-radius: 30px;
                }

                h1 {
                    font-size: 28px;
                }
            }
        </style>
    </head>

    <body>

        <div class="order-scene">

            <div class="hero-card">
                <div class="success-orbit">
                    <div class="ring"></div>
                    <div class="ring"></div>
                    <div class="ring"></div>
                    <div class="check-icon">
                        <i class="fas fa-check"></i>
                    </div>
                </div>

                <h1>Everything's Set!</h1>
                <p>Your order has been successfully placed and is now on the priority cooking list.</p>

                <div class="status-pill">
                    <div class="dot"></div>
                    Restaurant confirmed
                </div>

                <div class="cta-group">
                    <a href="Restaurants" class="btn-primary">
                        Continue to Home
                    </a>

                    <form action="logout" method="post" id="lgt">
                        <a href="javascript:document.getElementById('lgt').submit()" class="btn-secondary">
                            Sign Out
                        </a>
                    </form>
                </div>
            </div>
        </div>

    </body>

    </html>