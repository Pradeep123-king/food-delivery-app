<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.tap.model.Order" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Order Registry | foodie</title>
                <!-- Icons -->
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
                <!-- Google Fonts -->
                <link
                    href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800;900&display=swap"
                    rel="stylesheet">

                <style>
                    :root {
                        --primary: #ff5200;
                        --secondary: #1a1a1a;
                        --success: #00b14f;
                        --pending: #ffb800;
                        --ghost-gray: #f2f4f7;
                        --text-main: #2d3436;
                        --text-sub: #636e72;
                        --white: #ffffff;
                        --transition: all 0.5s cubic-bezier(0.23, 1, 0.32, 1);
                    }

                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                        font-family: 'Outfit', sans-serif;
                    }

                    body {
                        background-color: #f7f9fc;
                        color: var(--text-main);
                        min-height: 100vh;
                        padding: 60px 20px;
                    }

                    .registry-wrapper {
                        max-width: 900px;
                        margin: auto;
                    }

                    /* 1. REGISTRY HEADER */
                    .registry-header {
                        margin-bottom: 40px;
                        display: flex;
                        align-items: center;
                        justify-content: space-between;
                        animation: fadeIn 1s ease;
                    }

                    @keyframes fadeIn {
                        from {
                            opacity: 0;
                            transform: translateY(-10px);
                        }

                        to {
                            opacity: 1;
                            transform: translateY(0);
                        }
                    }

                    .header-content h1 {
                        font-size: 38px;
                        font-weight: 900;
                        letter-spacing: -1.5px;
                        color: var(--secondary);
                        margin-bottom: 5px;
                    }

                    .header-content span {
                        color: var(--text-sub);
                        font-weight: 600;
                        font-size: 15px;
                    }

                    .btn-home {
                        text-decoration: none;
                        padding: 12px 24px;
                        background: var(--white);
                        border: 1.5px solid #e1e4e8;
                        border-radius: 100px;
                        color: var(--secondary);
                        font-weight: 800;
                        font-size: 13px;
                        transition: var(--transition);
                    }

                    .btn-home:hover {
                        border-color: var(--primary);
                        color: var(--primary);
                        transform: translateX(-5px);
                    }

                    /* 2. SUMMARY STRIP */
                    .summary-strip {
                        display: grid;
                        grid-template-columns: repeat(3, 1fr);
                        gap: 20px;
                        margin-bottom: 40px;
                        animation: slideUp 0.8s ease backwards;
                    }

                    .stat-box {
                        background: var(--white);
                        padding: 20px;
                        border-radius: 24px;
                        border: 1.5px solid #f0f2f5;
                        display: flex;
                        align-items: center;
                        gap: 15px;
                    }

                    .stat-box i {
                        width: 45px;
                        height: 45px;
                        background: #fff0e6;
                        color: var(--primary);
                        border-radius: 14px;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        font-size: 18px;
                    }

                    .stat-info .count {
                        font-size: 18px;
                        font-weight: 900;
                    }

                    .stat-info .label {
                        font-size: 12px;
                        font-weight: 700;
                        color: var(--text-sub);
                        text-transform: uppercase;
                    }

                    /* 3. THE REGISTRY LIST */
                    .order-list {
                        display: flex;
                        flex-direction: column;
                        gap: 15px;
                    }

                    .list-item {
                        background: var(--white);
                        padding: 25px 30px;
                        border-radius: 28px;
                        display: flex;
                        align-items: center;
                        justify-content: space-between;
                        border: 1.5px solid #f0f2f5;
                        transition: var(--transition);
                        animation: slideUp 0.8s ease backwards;
                    }

                    @keyframes slideUp {
                        from {
                            opacity: 0;
                            transform: translateY(20px);
                        }

                        to {
                            opacity: 1;
                            transform: translateY(0);
                        }
                    }

                    .list-item:hover {
                        transform: scale(1.02);
                        box-shadow: 0 20px 40px rgba(0, 0, 0, 0.04);
                        border-color: var(--primary);
                    }

                    /* Left side: Date & ID */
                    .info-col {
                        display: flex;
                        flex-direction: column;
                        gap: 5px;
                    }

                    .item-ref {
                        font-size: 13px;
                        font-weight: 800;
                        color: var(--primary);
                        text-transform: uppercase;
                    }

                    .item-date {
                        font-size: 16px;
                        font-weight: 700;
                        color: var(--secondary);
                    }

                    /* Middle side: Payment */
                    .pay-col {
                        text-align: left;
                    }

                    .pay-method {
                        display: flex;
                        align-items: center;
                        gap: 8px;
                        font-weight: 700;
                        color: var(--text-sub);
                        font-size: 14px;
                    }

                    /* Right side: Amount & Status */
                    .action-col {
                        text-align: right;
                        display: flex;
                        flex-direction: column;
                        align-items: flex-end;
                        gap: 8px;
                    }

                    .item-total {
                        font-size: 20px;
                        font-weight: 900;
                        color: var(--secondary);
                    }

                    .status-pill {
                        padding: 6px 12px;
                        border-radius: 100px;
                        font-size: 11px;
                        font-weight: 800;
                        text-transform: uppercase;
                    }

                    .status-pill.success {
                        background: #e6f7ef;
                        color: var(--success);
                    }

                    .status-pill.warning {
                        background: #fff8e6;
                        color: var(--pending);
                    }

                    /* Empty State */
                    .empty-hero {
                        text-align: center;
                        padding: 100px 40px;
                        background: white;
                        border-radius: 40px;
                        border: 2px dashed #e1e4e8;
                    }

                    .empty-hero i {
                        font-size: 60px;
                        color: var(--primary);
                        opacity: 0.1;
                        margin-bottom: 25px;
                    }

                    @media (max-width: 768px) {
                        .summary-strip {
                            grid-template-columns: 1fr;
                        }

                        .list-item {
                            flex-direction: column;
                            align-items: flex-start;
                            gap: 20px;
                            text-align: left;
                        }

                        .action-col {
                            align-items: flex-start;
                            text-align: left;
                        }
                    }
                </style>
            </head>

            <body>

                <div class="registry-wrapper">
                    <!-- Header -->
                    <div class="registry-header">
                        <div class="header-content">
                            <h1>Order Registry</h1>
                            <span>History of your food journey at foodie</span>
                        </div>
                        <a href="Restaurants" class="btn-home">
                            <i class="fas fa-home"></i> Dashboard
                        </a>
                    </div>

                    <% List<Order> orders = (List<Order>) request.getAttribute("orders");
                            int totalOrders = (orders != null) ? orders.size() : 0;
                            %>

                            <!-- Summary Stats -->
                            <div class="summary-strip">
                                <div class="stat-box">
                                    <i class="fas fa-shopping-bag"></i>
                                    <div class="stat-info">
                                        <div class="count">
                                            <%= totalOrders %>
                                        </div>
                                        <div class="label">Total Orders</div>
                                    </div>
                                </div>
                                <div class="stat-box">
                                    <i class="fas fa-check-circle"></i>
                                    <div class="stat-info">
                                        <div class="count">Verified</div>
                                        <div class="label">Status Check</div>
                                    </div>
                                </div>
                                <div class="stat-box">
                                    <i class="fas fa-wallet"></i>
                                    <div class="stat-info">
                                        <div class="count">Multiple</div>
                                        <div class="label">Payment Types</div>
                                    </div>
                                </div>
                            </div>

                            <!-- Registry List -->
                            <div class="order-list">
                                <% if (orders !=null && !orders.isEmpty()) { int idx=0; for (Order o : orders) { idx++;
                                    boolean isPending=o.getStatus().equalsIgnoreCase("Pending"); %>
                                    <div class="list-item" style="animation-delay: <%= idx * 0.1 %>s">
                                        <div class="info-col">
                                            <span class="item-ref">Ref ID: #<%= o.getOrderId() %></span>
                                            <span class="item-date">
                                                <%= o.getOrderDate() %>
                                            </span>
                                        </div>

                                        <div class="pay-col">
                                            <div class="pay-method">
                                                <i class="fas fa-credit-card"></i>
                                                <%= o.getPaymentMethod() %>
                                            </div>
                                        </div>

                                        <div class="action-col">
                                            <span class="item-total">₹<%= o.getTotalAmount() %></span>
                                            <div class="status-pill <%= isPending ? " warning" : "success" %>">
                                                <%= o.getStatus() %>
                                            </div>
                                        </div>
                                    </div>
                                    <% } } else { %>
                                        <div class="empty-hero">
                                            <i class="fas fa-box"></i>
                                            <h2>No Orders Recorded</h2>
                                            <p>Start ordering to see your culinary history appear here.</p>
                                            <a href="Restaurants"
                                                style="color: var(--primary); font-weight: 800; text-decoration: none; display: inline-block; margin-top: 15px;">Order
                                                Now ➔</a>
                                        </div>
                                        <% } %>
                            </div>
                </div>

            </body>

            </html>