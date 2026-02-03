<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer Support</title>
<link rel="stylesheet" href="rescon.css">
</head>
<body>

<h2>Customer Support</h2>

<form action="support" method="post" class="support-form">
    <label>Your Name:</label>
    <input type="text" name="name" required>

    <label>Your Phone:</label>
    <input type="text" name="phone" required>

    <label>Your Issue:</label>
    <textarea name="message" placeholder="Write your problem..." required></textarea>

    <button type="submit">Submit Issue</button>
</form>

</body>
</html>


</body>
</html>