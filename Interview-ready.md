# 🎯 Java Web Development — Interview Prep Kit
> **Level:** Fresher / Entry Level  
> **Format:** Q&A — Read the question, recall the answer, then verify  
> **Topics:** Web Basics → Servlet → Session → JSP → JDBC → MVC → Security

---

# 🌐 SECTION 1 — Web Basics & Tomcat

---

**Q1. What is the difference between a static and dynamic web page?**

> A static web page always shows the same content to everyone — it's a fixed HTML file.  
> A dynamic web page generates content at runtime based on user input or database data.  
> Example: A news article is static. Your Facebook feed is dynamic — different for every user.

---

**Q2. What is a Web Container? What does Tomcat do?**

> A Web Container (like Apache Tomcat) is responsible for managing the lifecycle of Servlets.  
> It receives HTTP requests from the browser, finds the correct Servlet, calls its methods, and sends the response back.  
> Tomcat is not just a server — it is a **Servlet Container**. Without it, Servlets cannot run.

---

**Q3. What is a Servlet?**

> A Servlet is a Java class that runs on the server side, handles HTTP requests, and generates dynamic responses.  
> It extends `HttpServlet` and overrides methods like `doGet()` or `doPost()`.  
> It lives inside a Web Container like Tomcat.

---

**Q4. What is `web.xml`? Is it mandatory?**

> `web.xml` is the **Deployment Descriptor** — an XML configuration file that maps URLs to Servlet classes.  
> Since Servlet 3.0, it is **not mandatory**. You can use `@WebServlet` annotation instead.  
> Both do the same job — tell Tomcat which Servlet to call for which URL.

---

**Q5. What is the difference between `web.xml` mapping and `@WebServlet` annotation?**

> Both map a URL pattern to a Servlet class.  
> `web.xml` is XML-based (old approach), kept in `WEB-INF/`.  
> `@WebServlet("/url")` is annotation-based (modern approach), written directly above the class.  
> `@WebServlet` is cleaner and preferred in modern development.

---

# 🧩 SECTION 2 — Servlet Lifecycle & Core

---

**Q6. Explain the Servlet Lifecycle.**

> The Servlet lifecycle has 3 phases:
> 1. **`init()`** — Called once when the Servlet is first loaded into memory. Used for one-time setup like DB connections.
> 2. **`service()`** — Called on every request. It checks the HTTP method and routes to `doGet()` or `doPost()`.
> 3. **`destroy()`** — Called once when the server shuts down or Servlet is removed. Used for cleanup.
>
> Key point: `init()` and `destroy()` run once. `service()` runs for every single request.

---

**Q7. Who calls `init()`, `service()`, and `destroy()`?**

> The **Web Container (Tomcat)** calls all three — not the developer.  
> We only override them to add our logic. Tomcat manages when they are called.

---

**Q8. Can we override `service()` directly instead of `doGet()` / `doPost()`?**

> Technically yes, but it is not recommended.  
> If you override `service()`, you lose the automatic routing to `doGet()` and `doPost()` that `HttpServlet` provides.  
> Best practice is to override `doGet()` and `doPost()` separately.

---

**Q9. What is the difference between GET and POST?**

> GET sends data in the URL (visible, bookmarkable, limited size). Used for fetching/searching.  
> POST sends data in the request body (hidden, secure, no size limit). Used for login, form submissions.  
> Sensitive data like passwords should always go via POST.

---

**Q10. What does `getParameter()` do?**

> `request.getParameter("fieldName")` reads the value of a form field or URL query parameter sent by the client.  
> It always returns a `String`. If the parameter is missing, it returns `null`.

---

**Q11. What is `PrintWriter` and how is it used in Servlets?**

> `PrintWriter` is used to send text data (HTML) back to the browser as a response.  
> You get it from `response.getWriter()` and then call `out.println()` to write content.  
> You must set `response.setContentType("text/html")` before getting the writer.

---

**Q12. What is `HttpServletRequest` and `HttpServletResponse`?**

> `HttpServletRequest` represents the incoming request from the browser — it carries parameters, headers, session info, and more.  
> `HttpServletResponse` represents the outgoing response to the browser — used to set content type, write output, and send redirects.  
> Think of Request as the customer's order and Response as the food going back.

---

# 🔀 SECTION 3 — Navigation, Session & Cookies

---

**Q13. What is the difference between `forward()` and `sendRedirect()`?**

> `forward()` is a server-side operation. The request goes from one Servlet to another internally. The URL in the browser does NOT change. The same request object is shared.  
> `sendRedirect()` tells the browser to make a new request to a different URL. The URL in the browser CHANGES. The original request object is lost.  
> Use `forward()` to pass data to a JSP. Use `sendRedirect()` after login or logout.

---

**Q14. What is `RequestDispatcher`?**

> `RequestDispatcher` is an object that allows a Servlet to forward a request to another Servlet or JSP, or to include the content of another resource.  
> Obtained via `request.getRequestDispatcher("target.jsp")`.  
> Then call `.forward(request, response)` to transfer control.

---

**Q15. What is `setAttribute()` and `getAttribute()`?**

> `setAttribute("key", value)` stores an object in the request scope — so it can be accessed in another Servlet or JSP after a forward.  
> `getAttribute("key")` retrieves that stored object.  
> These only work across `forward()` — not across `sendRedirect()` since redirect creates a new request.

---

**Q16. Why is HTTP called stateless? What is session tracking?**

> HTTP is stateless because the server does not remember previous requests. Each request is treated as brand new.  
> Session tracking is a technique to maintain state (remember the user) across multiple requests.  
> The 3 main methods are: HttpSession, Cookies, and URL Rewriting.

---

**Q17. What is `HttpSession`? How does it work?**

> `HttpSession` is a server-side object that stores user-specific data across multiple requests.  
> When a user logs in, you store data in the session. On every next request, Tomcat identifies the user via a session ID (stored in a cookie called `JSESSIONID`).  
> `session.setAttribute("user", name)` to store. `session.getAttribute("user")` to read. `session.invalidate()` on logout.

---

**Q18. What is a Cookie? How is it different from a Session?**

> A Cookie is a small piece of data stored on the **client's browser**.  
> A Session stores data on the **server**.  
> Cookies persist even after the browser is closed (based on `maxAge`). Sessions expire when the browser closes or after a timeout.  
> Cookies can be disabled by the user. Sessions are more secure since data stays on the server.

---

**Q19. What is URL Rewriting?**

> URL Rewriting is a session tracking technique used when cookies are disabled in the browser.  
> The session ID is appended to every URL as a query parameter.  
> Example: `dashboard.jsp;jsessionid=ABC123`  
> `response.encodeURL("dashboard.jsp")` handles this automatically.

---

**Q20. What is `session.invalidate()`?**

> It destroys the current session and removes all attributes stored in it.  
> Called during logout to ensure the user's data is completely cleared from the server.  
> After `invalidate()`, calling `session.getAttribute()` on the old session object will throw an `IllegalStateException`.

---

**Q21. What is the difference between `ServletConfig` and `ServletContext`?**

> `ServletConfig` is specific to one Servlet — it holds init parameters defined for that Servlet only in `web.xml` using `<init-param>`.  
> `ServletContext` is shared across the entire application — holds context parameters defined using `<context-param>`.  
> Use `ServletConfig` for Servlet-specific settings. Use `ServletContext` for app-wide settings like database URLs.

---

**Q22. What is a Filter in Servlet? Why is it used?**

> A Filter is a Java class that intercepts requests before they reach a Servlet and responses before they reach the browser.  
> Common uses: authentication checks, logging, encoding, caching headers.  
> A Filter implements the `Filter` interface and overrides `doFilter()`. You call `chain.doFilter()` to let the request pass through.

---

**Q23. What is a Filter Chain?**

> Multiple filters can be applied to a single request in sequence — this is called a Filter Chain.  
> Each filter calls `chain.doFilter()` to pass control to the next filter.  
> The request goes through all filters before reaching the Servlet, and the response passes back through them in reverse order.

---

# 📄 SECTION 4 — JSP

---

**Q24. What is JSP? How is it different from a Servlet?**

> JSP (Java Server Pages) is a technology to create dynamic web pages using HTML with embedded Java code.  
> A Servlet is Java code that generates HTML. JSP is HTML that contains Java code.  
> JSP is easier to write for views/UI. Servlets are better for logic/control.  
> Internally, Tomcat converts every JSP into a Servlet before executing it.

---

**Q25. How does JSP work internally?**

> When a JSP is requested for the first time:  
> 1. Tomcat translates the `.jsp` file into a Java Servlet class.  
> 2. It compiles that class into a `.class` file.  
> 3. It executes the class and sends the response.  
> From the second request onwards, the compiled class is reused — so it is fast.  
> This is why the first request to a JSP is slightly slower.

---

**Q26. What are JSP Scripting Elements?**

> There are 4 types:
> - **Directive `<%@ %>`** — Page-level settings like imports, error pages, encoding.
> - **Declaration `<%! %>`** — Declare instance variables or methods. Runs once.
> - **Scriptlet `<% %>`** — Write Java logic. Runs on every request.
> - **Expression `<%= %>`** — Print a value directly to the page. Equivalent to `out.print()`.

---

**Q27. What is the difference between a Scriptlet and a Declaration in JSP?**

> A Scriptlet `<% %>` becomes part of the `service()` method — it runs on every request.  
> A Declaration `<%! %>` becomes an instance variable or method of the generated Servlet class — it is initialized once and shared across requests.  
> Using declarations for counters can cause thread-safety issues.

---

**Q28. What are JSP Implicit Objects?**

> Implicit objects are pre-created objects available in every JSP without any declaration.  
> Most important ones:  
> - `request` — HttpServletRequest  
> - `response` — HttpServletResponse  
> - `out` — JspWriter to print output  
> - `session` — HttpSession  
> - `application` — ServletContext (app-wide)  
> - `exception` — available only in error pages

---

**Q29. What is the difference between `<%@ include %>` and `<jsp:include />`?**

> `<%@ include file="header.jsp" %>` is a **static include** — happens at compile time. The content of header.jsp is merged into the current JSP before compilation. Both become one Servlet.  
> `<jsp:include page="footer.jsp" />` is a **dynamic include** — happens at runtime. A separate request is made to footer.jsp during execution.  
> Use static include for fixed content like headers/footers. Use dynamic include when the included page changes.

---

**Q30. What is the `errorPage` directive in JSP?**

> `<%@ page errorPage="error.jsp" %>` tells Tomcat: if any unhandled exception occurs on this page, forward the user to `error.jsp`.  
> On `error.jsp`, you must declare `<%@ page isErrorPage="true" %>` — this gives access to the `exception` implicit object to display the error message.

---

**Q31. What is JSTL?**

> JSTL (JavaServer Pages Standard Tag Library) is a collection of tags that replace Java Scriptlet code in JSP.  
> Instead of writing `<% if (...) { %>` in HTML, you write `<c:if test="...">` — much cleaner.  
> Common tags: `<c:if>`, `<c:forEach>`, `<c:choose>`, `<c:set>`, `<c:out>`.  
> Requires adding the JSTL dependency and `<%@ taglib %>` directive.

---

**Q32. What is Expression Language (EL)?**

> EL allows you to access data stored in scopes using `${}` syntax — without writing Java code.  
> Example: `${sessionScope.username}` reads the `username` attribute from the session.  
> `${requestScope.user.name}` reads the `name` property of a `user` object in request scope.  
> EL works perfectly with JSTL and is the modern way to display data in JSP.

---

**Q33. Why should we avoid Scriptlets in JSP?**

> Scriptlets mix Java logic with HTML — making the code hard to read, test, and maintain.  
> It violates the principle of separation of concerns (View should only display, not contain logic).  
> Modern best practice: use EL `${}` for reading data and JSTL tags for logic in JSP.

---

# 🗄️ SECTION 5 — JDBC, DAO & MVC

---

**Q34. What is JDBC?**

> JDBC (Java Database Connectivity) is a Java API that allows Java programs to connect to and interact with relational databases like MySQL or PostgreSQL.  
> It provides classes like `Connection`, `PreparedStatement`, and `ResultSet` to perform database operations.

---

**Q35. What are the steps to connect to a database using JDBC?**

> The 7 steps are:
> 1. Load the JDBC Driver — `Class.forName("com.mysql.cj.jdbc.Driver")`
> 2. Create a Connection — `DriverManager.getConnection(url, user, pass)`
> 3. Create a Statement — `PreparedStatement ps = con.prepareStatement(sql)`
> 4. Set Parameters — `ps.setString(1, value)`
> 5. Execute the Query — `ps.executeQuery()` or `ps.executeUpdate()`
> 6. Process the ResultSet — `while(rs.next()) { rs.getString("col") }`
> 7. Close all resources — `rs.close()`, `ps.close()`, `con.close()`

---

**Q36. What is `PreparedStatement`? Why use it instead of `Statement`?**

> `PreparedStatement` is a precompiled SQL statement with placeholders (`?`) for parameters.  
> `Statement` directly concatenates user input into SQL — which is vulnerable to **SQL Injection** attacks.  
> `PreparedStatement` automatically escapes input, preventing SQL Injection. It is also faster for repeated queries.  
> Always use `PreparedStatement` in production.

---

**Q37. What is SQL Injection? How does `PreparedStatement` prevent it?**

> SQL Injection is an attack where a user enters malicious SQL code in a form field to manipulate the database query.  
> Example: entering `' OR '1'='1` in a login field can bypass authentication.  
> `PreparedStatement` treats all user input as data (not as SQL code), so the injected characters are escaped and have no effect.

---

**Q38. What is the DAO Pattern?**

> DAO (Data Access Object) is a design pattern that separates database logic from business logic.  
> You create a separate class (e.g., `UserDAO`) that contains all JDBC operations (insert, select, delete, update).  
> The Servlet or service class just calls `UserDAO` methods — it does not contain any SQL.  
> This makes code cleaner, easier to test, and easier to change the database later.

---

**Q39. What is MVC Architecture in Java Web Applications?**

> MVC stands for Model-View-Controller — a pattern to separate concerns:
> - **Model** — Java classes and DAOs that handle data and business logic.
> - **View** — JSP files that only display data.
> - **Controller** — Servlets that receive requests, call the Model, and forward results to the View.  
>
> Flow: Browser → Servlet (Controller) → DAO/Model → setAttribute → JSP (View) → Response.  
> The key benefit is that View and Logic are completely separate — easier to maintain.

---

**Q40. Why do we use MVC pattern?**

> Without MVC, business logic, database calls, and HTML are all mixed together — making code messy and hard to change.  
> MVC ensures each layer has a single responsibility.  
> Changing the UI (JSP) does not affect the logic (Servlet/DAO), and vice versa.  
> It is the foundation of every modern Java web framework like Spring MVC.

---

**Q41. What is Maven? What is `pom.xml`?**

> Maven is a build and dependency management tool for Java projects.  
> Instead of manually downloading JAR files, you declare dependencies in `pom.xml` and Maven automatically downloads and manages them.  
> `pom.xml` is the Maven configuration file — it defines the project structure, dependencies, and build settings.

---

# 🔐 SECTION 6 — Security & Practical Features

---

**Q42. How do you implement a login system in Servlet?**

> 1. Create a login form (POST method) with username and password fields.
> 2. In `LoginServlet.doPost()`, read the parameters using `getParameter()`.
> 3. Validate credentials against the database using a DAO.
> 4. If valid — create a session, store the username, redirect to dashboard.
> 5. If invalid — redirect back to login page with an error message.

---

**Q43. How do you implement logout?**

> In `LogoutServlet.doGet()`, call `request.getSession().invalidate()` to destroy the session.  
> Then redirect the user to the login page using `response.sendRedirect("login.jsp")`.  
> This ensures all session data is cleared from the server.

---

**Q44. How do you protect pages from unauthorized access?**

> On every protected Servlet or JSP, check if the session exists and contains the user attribute.  
> `HttpSession session = request.getSession(false)` — passing `false` means do not create a new session if one doesn't exist.  
> If the session is null or the user attribute is missing, redirect to the login page.  
> Best practice: put this check in a Filter that applies to all protected URLs automatically.

---

**Q45. What is the Back-Button problem after logout? How do you fix it?**

> After logout, if the user presses the browser's Back button, the browser may show the cached version of the protected page — even though the session is destroyed.  
> This is a browser caching issue, not a session issue.  
> Fix: Add these response headers on every protected page:  
> `Cache-Control: no-cache, no-store, must-revalidate`  
> `Pragma: no-cache`  
> `Expires: 0`  
> This tells the browser never to cache the page.

---

**Q46. What is file upload in Servlet? What annotation is required?**

> To handle file uploads, the Servlet must be annotated with `@MultipartConfig`.  
> The HTML form must use `method="post"` and `enctype="multipart/form-data"`.  
> In the Servlet, use `request.getPart("fieldName")` to get the uploaded file as a `Part` object.  
> Then call `part.write("savePath/fileName")` to save it to the server.

---

**Q47. What is `@MultipartConfig`?**

> It is an annotation applied to a Servlet to tell Tomcat that this Servlet will handle multipart (file upload) requests.  
> Without this annotation, `request.getPart()` will not work.  
> You can also set limits like `maxFileSize` and `maxRequestSize` inside the annotation.

---

# 💡 SECTION 7 — Commonly Asked Tricky Questions

---

**Q48. How many times is a Servlet instantiated?**

> By default, Tomcat creates only **one instance** of each Servlet — regardless of how many requests come in.  
> All requests are served by the same single instance, but on different threads.  
> This means Servlet instance variables are NOT thread-safe. Always use local variables inside `doGet()`/`doPost()`.

---

**Q49. Is JSP faster than Servlet?**

> No. JSP is actually converted into a Servlet by the container before execution.  
> The first request to a JSP is slightly slower due to translation and compilation.  
> After the first request, both perform similarly.  
> JSP is preferred for View (UI), not for performance.

---

**Q50. What is the difference between `request.getSession()` and `request.getSession(false)`?**

> `request.getSession()` — returns the existing session, or **creates a new one** if none exists.  
> `request.getSession(false)` — returns the existing session, or returns **null** if none exists. Does NOT create a new session.  
> Use `getSession(false)` for session validation checks — so you don't accidentally create a session for unauthenticated users.

---

**Q51. What is the difference between `include` directive and `jsp:include`?**

> `<%@ include file="a.jsp" %>` — Static. Happens at translation time. Content is merged. One Servlet is generated.  
> `<jsp:include page="a.jsp" />` — Dynamic. Happens at request time. Separate Servlet call. Changes in included file are reflected immediately without recompiling.

---

**Q52. What happens if you call `forward()` after already writing to the response?**

> If the response buffer has already been flushed (i.e., some output was sent to the browser), calling `forward()` throws an `IllegalStateException`.  
> Best practice: always call `forward()` or `sendRedirect()` before writing any output to the response.

---

**Q53. What is the purpose of `setContentType()` in Servlet?**

> It tells the browser what type of content is being sent in the response.  
> `response.setContentType("text/html")` — for HTML pages.  
> `response.setContentType("application/json")` — for JSON data (REST APIs).  
> Must be called before `getWriter()` or `getOutputStream()`.

---

**Q54. Can a Servlet handle both GET and POST with the same logic?**

> Yes. You can implement `doGet()` and make `doPost()` simply call `doGet()`:
> ```
> doPost() → calls → doGet()
> ```
> This way the same logic runs for both methods — useful for forms that can be submitted via either method.

---

**Q55. What is the role of `WEB-INF` folder?**

> `WEB-INF` is a protected directory — its contents are **not directly accessible** from the browser.  
> Files inside `WEB-INF` (like JSPs, `web.xml`) can only be accessed via a Servlet forward — never directly via URL.  
> This is a security feature — sensitive JSP views should be kept inside `WEB-INF`.

---

# 📋 Quick Revision — One-Liners

| Question | One-line Answer |
|----------|----------------|
| What is a Servlet? | Java class that handles HTTP requests on the server |
| What does Tomcat do? | Manages Servlet lifecycle and HTTP requests |
| `init()` runs how many times? | Once — when Servlet is first loaded |
| `service()` runs how many times? | Every request |
| GET vs POST? | GET = URL data, visible. POST = body data, hidden |
| `forward()` vs `sendRedirect()`? | Forward = server internal, URL stays. Redirect = browser makes new request |
| Session vs Cookie? | Session = server-side. Cookie = client-side |
| `getSession(false)` returns? | Existing session or null — never creates new |
| What is DAO? | Class that separates all DB logic from Servlet |
| What is MVC? | Model (data) + View (JSP) + Controller (Servlet) |
| Why PreparedStatement? | Prevents SQL Injection |
| Why JSTL/EL? | Replace Java code in JSP for clean separation |
| `@MultipartConfig` for? | File upload handling |
| How to prevent back-button after logout? | Cache-Control: no-store headers |
| Where to keep sensitive JSPs? | Inside `WEB-INF/` folder |

---

*🎯 Read question → close answer → try to recall → then verify. Repeat until confident.*
