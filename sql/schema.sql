CREATE DATABASE IF NOT EXISTS travel_booking;
USE travel_booking;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    role VARCHAR(10) DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE travel_packages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    description TEXT,
    duration_days INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    available_slots INT NOT NULL DEFAULT 20,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    package_id INT NOT NULL,
    num_travelers INT NOT NULL DEFAULT 1,
    total_price DECIMAL(10,2) NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    travel_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (package_id) REFERENCES travel_packages(id)
);

-- Default admin user (password: admin123)
INSERT INTO users (username, email, password, full_name, phone, role)
VALUES ('admin', 'admin@travel.com', 'admin123', 'Administrator', '9999999999', 'admin');

-- Sample travel packages
INSERT INTO travel_packages (name, destination, description, duration_days, price, available_slots) VALUES
('Golden Triangle Tour', 'Delhi-Agra-Jaipur', 'Explore the iconic Golden Triangle covering Delhi, Agra and Jaipur. Visit Taj Mahal, Red Fort, Hawa Mahal and more.', 6, 15000.00, 25),
('Goa Beach Getaway', 'Goa', 'Relax on the beautiful beaches of Goa. Enjoy water sports, nightlife and Portuguese architecture.', 4, 12000.00, 30),
('Kerala Backwaters', 'Kerala', 'Cruise through the serene backwaters of Kerala. Experience houseboats, spice gardens and Ayurvedic treatments.', 5, 18000.00, 20),
('Himalayan Adventure', 'Manali-Leh', 'Thrilling adventure through the Himalayas. Trekking, camping and breathtaking mountain views.', 7, 25000.00, 15),
('Rajasthan Heritage', 'Udaipur-Jodhpur-Jaisalmer', 'Discover the royal heritage of Rajasthan. Desert safaris, majestic forts and vibrant culture.', 8, 22000.00, 20),
('Andaman Islands', 'Port Blair-Havelock', 'Tropical island paradise with crystal clear waters. Snorkeling, scuba diving and pristine beaches.', 5, 30000.00, 18);
