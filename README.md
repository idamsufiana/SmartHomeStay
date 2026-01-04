# SmartHomeStay
Smart HomeStay monolith version 

ROOM
----
id (PK)
room_number
room_type
capacity
price_per_night
active
   │
   │ 1
   │
   │ *
RESERVATION
-----------
id (PK)
user_id
room_id (FK → ROOM.id)
start_date
end_date
status
created_at
   │
   │ 1
   │
   │ *
RESERVATION_FACILITY
--------------------
id (PK)
reservation_id (FK → RESERVATION.id)
facility_id (FK → FACILITY.id)
qty
unit_price_snapshot
   │
   │ *
   │
   │ 1
FACILITY
--------
id (PK)
code
name
price


RESERVATION
-----------
   │
   │ 1
   │
   │ 1
BILL
----
id (PK)
reservation_id (FK → RESERVATION.id)
subtotal
tax
total
status
   │
   │ 1
   │
   │ *
PAYMENT
-------
id (PK)
bill_id (FK → BILL.id)
amount
method
reference
paid_at

