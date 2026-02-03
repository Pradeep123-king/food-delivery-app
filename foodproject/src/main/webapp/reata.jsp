<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.tap.model.Restaurant" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Discovery | Spice Symphony</title>

                <!-- CSS File -->
                <link rel="stylesheet" href="rescon.css">

                <!-- Icons -->
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

            </head>

            <body>

                <header class="navbar">
                    <a href="Restaurants"><div class="logo">food<span>delivery</span></div></a>
                    <nav>
                        <a href="login.jsp">Login</a>
                        <a href="register.jsp">Register</a>
                        <a href="cart.jsp" class="Cart-btn"><i class="fas fa-shopping-basket"></i> Cart</a>
                        <a href="orderList" class="List-btn">🛒 CartList</a>
                        <a href="logout" class="logout-link">Log out</a>
                    </nav>
                </header>

                <section class="location-bar">
                    <div class="hero-overlay">
                        <h2>A Symphony of <span>Indian Flavors</span></h2>
                        <p class="hero-info-text">Discover India's finest culinary treasures, from heritage kitchens to
                            modern fusion gems.</p>
                        <div class="search-box">
                            <form action="search" method="get">
                                <input type="text" name="keyword" placeholder="Search restaurants or dishes..."
                                    required>
                                <button type="submit"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                    </div>
                </section>

                <!-- THE LUXE INFO SECTION (Primary) -->
                <section class="info-primary">
                    <div class="info-item">
                        <i class="fas fa-wind"></i>
                        <h4>Our Story.</h4>
                        <p>We are redefining how India eats, bringing the finest kitchens directly to your doorstep with
                            speed, safety, and a touch of magic.</p>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-award"></i>
                        <h4>Passion for Flavors</h4>
                        <p>At Gourmet Hub, we believe that food is not just about calories—it's about culture, memories,
                            and the love that goes into every dish. We partner only with the most authentic restaurants.
                        </p>
                    </div>
                    <div class="info-item">
                        <i class="fas fa-gem"></i>
                        <h4>Sustainably Sourced</h4>
                        <p>We are committed to the future of our planet. From eco-friendly packaging to optimized
                            delivery routes that reduce carbon footprint, we care about what we leave behind.</p>
                    </div>
                </section>


                <section class="restaurants">
                    <div class="section-head">
                        <h3>Popular Restaurants Near You</h3>
                        <p class="section-subtext">Handpicked for their quality, flavor, and service excellence.</p>
                    </div>

                    <div class="rest-container">
                        <% List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");

                                if (restaurants == null || restaurants.isEmpty()) {
                                %>
                                <div class="empty-state" style="grid-column: 1/-1; text-align: center; padding: 100px;">
                                    <i class="fas fa-utensils"
                                        style="font-size: 50px; opacity: 0.1; margin-bottom: 20px;"></i>
                                    <p>Reaching out to our partners... check back in a moment.</p>
                                </div>
                                <% } else { int count=0; for(Restaurant r : restaurants) { count++; %>

                                    <a href="menu?restaurantId=<%=r.getRestaurantId()%>" class="rest-card"
                                        style="animation: fadeInUp 0.8s ease backwards; animation-delay: <%= count * 0.1 %>s">
                                        <div class="rest-img">
                                            <img src="<%=r.getImageURL()%>" alt="<%= r.getRestaurantName() %>">
                                        </div>

                                        <div class="card-content">
                                            <h4 class="restaurant-name">
                                                <%= r.getRestaurantName() %>
                                            </h4>
                                            <p class="restaurant-cuisine">
                                                <%= r.getCuisine() %>
                                            </p>

                                            <div class="card-footer">
                                                <div class="rating-badge">
                                                    <i class="fas fa-star"></i>
                                                    <%= r.getRating() %>
                                                </div>
                                                <div class="delivery-time">
                                                    <%= r.getDeliveryTime() %>
                                                </div>
                                            </div>
                                        </div>
                                    </a>

                                    <% } } %>
                    </div>
                </section>

                <footer class="footer">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div class="logo" style="color: white;">food<span>delivery</span></div>
                        <p style="opacity: 0.5; font-size: 14px;">© 2025 PREMIUM DELIVERY CONCIERGE</p>
                    </div>
                </footer>

                <style>
                    @keyframes fadeInUp {
                        from {
                            opacity: 0;
                            transform: translateY(30px);
                        }

                        to {
                            opacity: 1;
                            transform: translateY(0);
                        }
                    }
                </style>

            </body>

            </html>