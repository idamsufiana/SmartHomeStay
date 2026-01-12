# SmartHomeStay
Smart HomeStay event driven microservice

<img align="left" alt="SmartHomeStay" src="https://github.com/idamsufiana/SmartHomeStay/blob/main/homestay.jpg?raw=true" />

<br clear="all" />

---
## 🟢 Reservation Events

| Event Name            | Producer            | Consumers                          |
|-----------------------|---------------------|------------------------------------|
| ReservationCreated    | Reservation Service | Billing Service                    |
| ReservationConfirmed  | Reservation Service | Notification Service *(optional)*  |
| ReservationCancelled  | Reservation Service | Billing Service                    |

## 🧾 Billing Events

| Event Name   | Producer         | Consumers                           |
|--------------|------------------|-------------------------------------|
| BillCreated  | Billing Service  | Payment Service                     |
| BillPaid     | Billing Service  | Reporting Service *(optional)*      |
| BillFailed   | Billing Service  | Reservation Service                 |

---

## 💳 Payment Events

| Event Name        | Producer        | Consumers                              |
|-------------------|-----------------|----------------------------------------|
| PaymentInitiated  | Payment Service | —                                      |
| PaymentSucceeded  | Payment Service | Billing Service, Reservation Service   |
| PaymentFailed     | Payment Service | Billing Service, Reservation Service   |
| PaymentExpired    | Payment Service | Billing Service, Reservation Service   |


