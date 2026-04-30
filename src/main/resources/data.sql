-- 1. Insert 6 Production Lines (A-F)
INSERT INTO `line` (id, name) VALUES
(1, 'Line A'), (2, 'Line B'), (3, 'Line C'),
(4, 'Line D'), (5, 'Line E'), (6, 'Line F');

-- 2. Insert Meters (2-3 per line)
INSERT INTO `meter` (id, line_id, name) VALUES
-- Line A
(1, 1, 'A-Main-Incomer'), (2, 1, 'A-Welding-Unit'), (3, 1, 'A-Conveyor'),
-- Line B
(4, 2, 'B-Main-Incomer'), (5, 2, 'B-Packaging'),
-- Line C
(6, 3, 'C-Main-Incomer'), (7, 3, 'C-Cooling-Tower'), (8, 3, 'C-Assembly'),
-- Line D
(9, 4, 'D-Main-Incomer'), (10, 4, 'D-Robotics-Arm'),
-- Line E
(11, 5, 'E-Main-Incomer'), (12, 5, 'E-Press-Machine'), (13, 5, 'E-Lighting'),
-- Line F
(14, 6, 'F-Main-Incomer'), (15, 6, 'F-HVAC-System');

-- 3. Insert Users
INSERT INTO `users` (id, username, password, role, approved, line_id) VALUES
(1, 'admin', '123', 'ROLE_ADMIN', b'1', NULL),
(2, 'testa', '123', 'ROLE_USER', b'1', 1),
(3, 'testb', '123', 'ROLE_USER', b'1', 2);

-- 4. Energy Readings (8:00 AM to 3:00 PM, 30-min intervals)
-- We'll focus on Line A (Meters 1, 2, 3) to give you a detailed chart immediately.
-- You can duplicate this pattern for other meters if needed.

INSERT INTO `energyreading` (meter_id, kwh, ts, k_wh) VALUES
-- Meter 1 (Line A Main) - Simulating a climb towards a peak
(1, 45.2, '2026-04-30 08:00:00', 45.2),
(1, 48.5, '2026-04-30 08:30:00', 48.5),
(1, 55.0, '2026-04-30 09:00:00', 55.0),
(1, 62.1, '2026-04-30 09:30:00', 62.1),
(1, 115.4, '2026-04-30 10:00:00', 115.4), -- ALERT TRIGGER (>100)
(1, 120.2, '2026-04-30 10:30:00', 120.2), -- PEAK
(1, 98.5, '2026-04-30 11:00:00', 98.5),
(1, 85.3, '2026-04-30 11:30:00', 85.3),
(1, 75.0, '2026-04-30 12:00:00', 75.0),
(1, 40.2, '2026-04-30 12:30:00', 40.2), -- LUNCH BREAK DIP
(1, 38.5, '2026-04-30 13:00:00', 38.5),
(1, 88.2, '2026-04-30 13:30:00', 88.2),
(1, 92.4, '2026-04-30 14:00:00', 92.4),
(1, 90.1, '2026-04-30 14:30:00', 90.1),
(1, 85.5, '2026-04-30 15:00:00', 85.5),

-- Meter 2 (Line A Welding) - Highly variable load
(2, 20.1, '2026-04-30 08:00:00', 20.1),
(2, 25.5, '2026-04-30 09:00:00', 25.5),
(2, 60.2, '2026-04-30 10:00:00', 60.2),
(2, 15.1, '2026-04-30 12:30:00', 15.1),
(2, 45.5, '2026-04-30 15:00:00', 45.5),

-- Meter 4 (Line B Main) - Stable load
(4, 70.0, '2026-04-30 08:00:00', 70.0),
(4, 72.5, '2026-04-30 10:00:00', 72.5),
(4, 71.2, '2026-04-30 12:00:00', 71.2),
(4, 75.8, '2026-04-30 14:00:00', 75.8);