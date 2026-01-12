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

