# SmartHomeStay
Smart HomeStay event driven microservice 


<img align="left" alt="GIF" src="https://github.com/idamsufiana/SmartHomeStay/blob/main/homestay.jpg?raw=true" />

---
---

## 🟢 Reservation Events

| Event Name              | Producer             | Consumers                         |
|-------------------------|----------------------|-----------------------------------|
| ReservationCreated      | Reservation Service  | Billing Service                   |
| ReservationConfirmed   | Reservation Service  | Notification Service *(optional)*  |
| ReservationCancelled   | Reservation Service  | Billing Service                

