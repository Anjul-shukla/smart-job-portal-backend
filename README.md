# 🚀 Smart Job Portal Backend System

A production-grade **Spring Boot backend system** for a modern Job Portal platform inspired by LinkedIn Jobs and Naukri. It supports authentication, job management, applications pipeline, AI-like matching, recommendations, admin analytics, caching, email notifications, audit logging, and scheduled tasks.

---

## ⚙️ Tech Stack
Java 17+ • Spring Boot • Spring Security (JWT) • Spring Data JPA • MySQL • Maven • Spring Cache • JavaMailSender • @Async • @Scheduled

---

## 🏗️ Architecture
Controller → Service → Repository → Database (DTO Pattern enforced)

---

## 👥 Roles
- **ADMIN** → Platform control + analytics  
- **COMPANY** → Post/manage jobs, review applicants  
- **CANDIDATE** → Profile, apply jobs, get recommendations  

---

## 🔐 Authentication
- JWT-based login/register  
- Role-based authorization  
- BCrypt password encryption  

---

## 💼 Core Features
- Job CRUD + advanced search (location, skills, salary, experience)
- Candidate application pipeline:
  APPLIED → REVIEWED → SHORTLISTED → INTERVIEW → HIRED/REJECTED
- Prevent duplicate applications

---

## 🧠 Matching Engine
AI-like scoring based on:
- Skills (50%)
- Experience (20%)
- Education (10%)
- Profile completeness (10%)
- Activity score (10%)

---

## 🔥 Recommendations
- Job recommendations based on skills & profile history
- Stored recommendation tracking

---

## 📧 Notifications
- Email alerts for application status updates
- Powered by JavaMailSender + @Async (non-blocking)

---

## 📊 Admin Analytics
- Total users, jobs, applications
- Conversion rate (applied → hired)
- Most demanded skills

---

## 🧾 Audit Logging
Tracks system actions:
- Login attempts
- Job creation
- Application updates

Stored securely in database.

---

## ⚡ Performance
- Spring Cache for job search optimization
- Faster repeated queries

---

## ⏰ Scheduler
- Daily job expiration cleanup
- Automated maintenance tasks using @Scheduled

---

## 🌐 API Overview

**Auth**
- POST /api/auth/register
- POST /api/auth/login

**Jobs**
- POST /api/jobs
- GET /api/jobs
- GET /api/jobs/search

**Applications**
- POST /api/apply/{jobId}
- PUT /api/applications/{id}/status

**Profile**
- GET /api/profile/me
- PUT /api/profile

**Admin**
- GET /api/admin/dashboard

---

## 🛠️ Setup

```bash
git clone https://github.com/your-username/smart-job-portal-backend.git
cd smart-job-portal-backend
mvn spring-boot:run
