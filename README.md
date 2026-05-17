# SpringBoot-Concepts

# 🚀 Deep Dive into Java Web: From Raw Servlets to Spring Boot

This repository explores the internal mechanics of Java Web Development — starting from low-level Raw Servlets and gradually moving toward the abstraction-driven architecture of Spring Boot.

Through a series of practical labs, the project demonstrates how Java web applications handle:

* HTTP requests & responses
* Request lifecycle
* Filters & interceptors
* Thread safety
* REST APIs
* Spring Boot abstractions

---

# 📂 Table of Contents

* Lab 01 — Hello API using Raw Servlet
* Lab 02 — Receiving Form Data & Input Validation
* Lab 03 — Logging Filter & Request Lifecycle
* Lab 04 — Thread Safety & Race Conditions
* Lab 05 — Raw Servlet vs Spring RestController

---

# 🛠️ Lab 01 — Hello API using Raw Servlet

## 🎯 Objective

Build an HTTP endpoint directly using `HttpServlet` to understand how Spring Boot internally operates on top of the Servlet API.

## 💻 Implementation Highlights

* Created custom servlet:

```java
public class MyRawServlet extends HttpServlet
```

* Registered servlet using:

```java
@WebServlet(urlPatterns = "/raw")
```

* Enabled servlet scanning using:

```java
@ServletComponentScan
```

---

## 🧠 Core Learning

### DispatcherServlet

Spring Boot internally uses a central servlet called:

```text
DispatcherServlet
```

It acts as the main request dispatcher and routes incoming traffic to controllers.

---

### Embedded Tomcat

Spring Boot automatically provisions an embedded servlet container such as:

* Apache Tomcat

This removes the need for external server deployment.

---

# 🛠️ Lab 02 — Receiving Form Data & Input Validation

## 🎯 Objective

Handle incoming POST requests, validate user input, and manually manage HTTP status codes.

---

## 💻 Key Concepts

### Reading Form Data

```java
request.getParameter("name")
```

### Manual Validation

* Empty input → `400 Bad Request`
* Valid input → `200 OK`

### Manual JSON Response

```java
response.getWriter().write("{\"message\":\"Success\"}");
```

---

## 📌 Architectural Insights

### Why POST Instead of GET?

`GET` exposes data inside the URL.

`POST` sends data inside the Request Body:

* Better for sensitive data
* No URL length limitation
* Cleaner API design

---

### HTTP Status Codes

Setting proper status codes helps frontend clients correctly detect errors.

Example:

```java
response.setStatus(400);
```

---

# 🛠️ Lab 03 — Logging Filter & Request Lifecycle

## 🎯 Objective

Understand request interception using Java Servlet Filters.

---

## 💻 Filter Flow

```java
System.out.println("[FILTER - INCOMING] Request Received");

chain.doFilter(request, response);

System.out.println("[FILTER - OUTGOING] Response Sent");
```

---

## 🧠 Core Learning

Filters execute:

1. Before controller execution
2. After controller execution

This mechanism powers many enterprise features such as:

* Authentication
* Authorization
* Request logging
* Rate limiting

---

## 🔐 Connection to Spring Security

Spring Security internally relies heavily on Filter chains.

JWT authentication filters:

* Validate tokens
* Allow or block requests
* Intercept requests before controller execution

---

# 🛠️ Lab 04 — Thread Safety & Race Conditions

## 🎯 Objective

Understand how servlets behave in a multi-threaded environment.

---

## ⚠️ The Problem — Race Condition

Example:

```java
private int counter = 0;
```

Multiple concurrent requests can corrupt shared state because:

```java
counter++
```

is NOT atomic.

This may cause:

* Duplicate values
* Missing increments
* Inconsistent state

---

## ✅ The Solution — AtomicInteger

```java
private final AtomicInteger counter = new AtomicInteger(0);

counter.incrementAndGet();
```

This guarantees thread-safe operations using atomic CPU-level instructions.

---

# 🎯 Important Spring Framework Rule

## 💡 Spring Beans Should Be Stateless

Spring components such as:

* Services
* Controllers
* Repositories

are Singleton by default.

That means:

```text
One shared object instance
```

Using mutable instance variables can create:

* Race conditions
* Data leaks
* Security vulnerabilities

---

## ✅ Best Practice

Store request-specific data inside:

* Method-local variables

NOT inside:

* Class-level fields

---

# 🛠️ Lab 05 — Raw Servlet vs Spring RestController

## 🎯 Objective

Compare low-level servlet development with modern Spring Boot abstractions.

---

# 📊 Comparative Analysis

| Feature            | Raw Servlet            | Spring Boot RestController |
| ------------------ | ---------------------- | -------------------------- |
| Boilerplate        | High                   | Very Low                   |
| Request Handling   | Manual                 | Annotation Driven          |
| JSON Serialization | Manual String Building | Automatic via Jackson      |
| Response Headers   | Manual Configuration   | Automatically Managed      |
| Readability        | Complex                | Cleaner & Maintainable     |
| Development Speed  | Slower                 | Faster                     |

---

## 🔥 Example

### Raw Servlet

```java
response.setContentType("application/json");
response.getWriter().write("{\"name\":\"Sihab\"}");
```

### Spring Boot

```java
@GetMapping
public Student getStudent() {
    return new Student("Sihab");
}
```

Spring Boot automatically:

* Converts object → JSON
* Sets response headers
* Handles serialization

---

# 🚀 Technologies Used

* Java
* Servlet API
* Spring Boot
* Embedded Tomcat
* Maven

---

# 🎯 Final Takeaway

This repository demonstrates the evolution from:

```text
Low-Level Java Web Development
                ↓
Modern Spring Boot Architecture
```

Understanding raw servlets helps reveal:

* How Spring Boot works internally
* How request lifecycles operate
* Why abstraction frameworks are powerful
* The importance of thread safety in backend systems

---

⭐ Exploring fundamentals deeply builds stronger backend engineering intuition.
