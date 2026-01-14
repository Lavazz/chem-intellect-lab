# ChemIntellect Lab 🧪🤖
**Enterprise-grade микросервисная платформа химических экспериментов**  
Java 21 • Spring Boot 3 • Kafka • Keycloak • Docker • AI Assistant

---

## 🎯 Цели проекта
ChemIntellect Lab — это система управления химической лабораторией:
- управление реагентами
- постановка и проведение экспериментов
- журналирование и аудит
- пользовательские роли и безопасность
- аналитика экспериментов
- AI-ассистент для подсказок и анализа

Проект демонстрирует продакшн-архитектуру уровня Senior:
- микросервисы
- event-driven взаимодействие
- централизованная авторизация
- fault tolerance
- observability

---

## 🏗 Архитектура
Микросервисы:

| Service | Назначение |
|--------|-----------|
| Gateway | входная точка, маршрутизация |
| Identity Service | пользователи, роли, Keycloak / JWT |
| Reagent Service | управление реагентами |
| Experiment Service | эксперименты, статусы, результаты |
| Analytics Service | аналитика, статистика, отчёты |
| AI Assistant Service | интеллектуальные подсказки |
| Notification Service | email / события |
| Common | shared kernel (DTO, события, ошибки) |

Инфраструктура:
- PostgreSQL
- Kafka
- Redis (кеш + rate limit)
- Keycloak

---

## 📦 Стек технологий
- **Java 21**
- **Spring Boot 3 / Spring Cloud**
- **Kafka**
- **PostgreSQL**
- **Redis**
- **Keycloak**
- **Docker + Docker Compose**
- **Maven Multimodule Monorepo**
