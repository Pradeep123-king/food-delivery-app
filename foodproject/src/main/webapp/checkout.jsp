<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.*, com.tap.model.Cart, com.tap.model.CartItem" %>

        <% Cart cart=(Cart) session.getAttribute("cart"); if (cart==null) { cart=new Cart(); } double total=0; %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Secure Checkout | food delivery</title>

                <!-- CSS and Fonts -->
                <link rel="stylesheet" href="checkout.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
            </head>

            <body>

                <div class="checkout-page">
                    <div class="checkout-container">

                        <!-- Left Section: Delivery & Payment -->
                        <div class="checkout-left">
                            <header class="checkout-header">
                                <h2>Secure Checkout</h2>
                                <p>Complete your order by providing delivery details</p>
                            </header>

                            <form action="checkout" method="post" class="delivery-form">
                                <div class="form-section">
                                    <h3><i class="fas fa-map-marker-alt"></i> Delivery Address</h3>
                                    <div class="input-group">
                                        <label>Delivery Address</label>
                                        <textarea name="address" rows="3"
                                            placeholder="Flat / House No. / Street / Landmark" required></textarea>
                                    </div>
                                </div>

                                <div class="form-section">
                                    <h3><i class="fas fa-credit-card"></i> Payment Method</h3>
                                    <div class="payment-options">
                                        <label class="payment-card">
                                            <input type="radio" name="paymentMethod" value="COD" checked>
                                            <div class="card-content">
                                                <i class="fas fa-truck"></i>
                                                <span>Cash on Delivery</span>
                                            </div>
                                        </label>
                                        <label class="payment-card">
                                            <input type="radio" name="paymentMethod" value="ONLINE">
                                            <div class="card-content">
                                                <i class="fas fa-wallet"></i>
                                                <span>Online Payment</span>
                                            </div>
                                        </label>
                                    </div>
                                </div>

                                <button type="submit" class="place-order-btn">
                                    Place Your Order <i class="fas fa-arrow-right"></i>
                                </button>
                            </form>
                        </div>

                        <!-- Right Section: Order Summary -->
                        <div class="checkout-right">
                            <div class="summary-card">
                                <h3>Your Order Summary</h3>
                                <div class="order-items">
                                    <% for (CartItem item : cart.getItems().values()) { double price=item.getPrice() *
                                        item.getQuantity(); total +=price; %>
                                        <div class="order-item">
                                            <div class="item-info">
                                                <span class="item-name">
                                                    <%= item.getName() %>
                                                </span>
                                                <span class="item-qty">x <%= item.getQuantity() %></span>
                                            </div>
                                            <span class="item-price">₹<%= price %></span>
                                        </div>
                                        <% } %>
                                </div>

                                <div class="summary-footer">
                                    <div class="subtotal">
                                        <span>Items Total</span>
                                        <span>₹<%= total %></span>
                                    </div>
                                    <div class="delivery-fee">
                                        <span>Delivery Fee</span>
                                        <span class="free">FREE</span>
                                    </div>
                                    <div class="grand-total">
                                        <span>Grand Total</span>
                                        <span>₹<%= total %></span>
                                    </div>
                                </div>

                                <p class="secure-note"><i class="fas fa-shield-alt"></i> 100% Safe & Secure Payments</p>
                                <a href="cart.jsp" class="back-to-cart">Edit Cart</a>
                            </div>
                        </div>

                    </div>
                </div>

            </body>

            </html>